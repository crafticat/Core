package mc.apptoeat.com.core.utils.rank;

import mc.apptoeat.com.core.utils.message.Color;
import mc.apptoeat.com.core.utils.temp.NameTagChanger;
import mc.apptoeat.com.core.utils.temp.TeamAction;
import me.clip.placeholderapi.PlaceholderAPI;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;


public class RankSystem implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        String group = LuckPermsProvider.get().getUserManager().getUser(e.getPlayer().getName()).getPrimaryGroup();
        User user = LuckPermsProvider.get().getUserManager().getUser(e.getPlayer().getName());

        if (user != null) {
            String groupPrefix = user.getCachedData().getMetaData().getPrefix();

            e.getPlayer().setPlayerListName(Color.code(groupPrefix + e.getPlayer().getName()));
            if (LuckPermsProvider.get().getUserManager().getUser(e.getPlayer().getName()).getPrimaryGroup().equalsIgnoreCase("rep")) {
                NameTagChanger.changePlayerName(e.getPlayer(), groupPrefix, "", TeamAction.CREATE);
            } else {
                NameTagChanger.changePlayerName(e.getPlayer(), groupPrefix, "", TeamAction.CREATE);
            }
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        String group = LuckPermsProvider.get().getUserManager().getUser(e.getPlayer().getName()).getPrimaryGroup();
        User user = LuckPermsProvider.get().getUserManager().getUser(e.getPlayer().getName());

        if (user != null) {
            String groupPrefix = user.getCachedData().getMetaData().getPrefix();

            if (group.equalsIgnoreCase("default")) {
                e.setFormat(Color.code(groupPrefix + "" + e.getPlayer().getDisplayName() + "&7: &7") + e.getMessage());
            } else {
                e.setFormat(Color.code(groupPrefix + "" + e.getPlayer().getDisplayName() + "&7: &r") + e.getMessage());
            }
        }
    }

    /*
    CustomConfig players, ranks;

    public RankSystem() {
        super("rank","creates an rank","");
        players = new CustomConfig("players");
        ranks = new CustomConfig("ranks");
    }

    @Override
    public void executeCommand(Player player, String commandLabel, String[] args) {
        if (player.hasPermission("core.ranks")) {
            if (args.length > 0) {
                //Create a new rank
                if (args[0].equals("create")) {
                    if (args.length == 3) {
                        if (!ranks.getConfig().contains(args[1])) {
                            player.sendMessage(Color.code("&fYou made &b" + args[1] + "&f rank!"));
                            ranks.getConfig().set(args[1] + ".prefixtab",Color.code(args[2]));
                            ranks.getConfig().set(args[1] + ".prefixchat",Color.code(args[2]));
                            ranks.getConfig().set(args[1] + ".prefixtag",Color.code(args[2]));
                            ranks.saveConfig();
                            LuckPermsProvider.get().getGroupManager().createAndLoadGroup(args[1]);
                        } else {
                            player.sendMessage(Color.code("&cThere is already rank with this name"));
                        }
                    } else player.sendMessage(Color.code("&7Please use &f/rank create [name] [prefix])"));
                }

                //Prefix command
                if (args[0].equals("prefix")) {
                    if (args.length == 4) {
                        if (ranks.getConfig().contains(args[1])) {
                            boolean found = false;

                            if (args[2].equals("tab")) {
                                ranks.getConfig().set(args[1] + ".prefixtab", Color.code(args[3]));
                                ranks.saveConfig();
                                found = true;
                            }
                            if (args[2].equals("chat")) {
                                ranks.getConfig().set(args[1] + ".prefixchat", Color.code(args[3]));
                                ranks.saveConfig();
                                found = true;
                            }
                            if (args[2].equals("tag")) {
                                ranks.getConfig().set(args[1] + ".prefixtag", Color.code(args[3]));
                                ranks.saveConfig();
                                found = true;
                            }

                            if (found) player.sendMessage(Color.code("faYou changed &b" + args[1] + "&f rank prefix to " + args[2]));
                            if (!found) {
                                player.sendMessage(Color.code("&8--------------------------"));
                                player.sendMessage("");
                                player.sendMessage(Color.code("&f- &f/ranks prefix [rankname] tab [prefix] &b- Set the tab rank prefix"));
                                player.sendMessage(Color.code("&f- &f/ranks prefix [rankname] chat [prefix] &b- Set the chat rank prefix"));
                                player.sendMessage(Color.code("&f- &f/ranks prefix [rankname] tag [prefix] &b- Set the tag rank prefix"));
                                player.sendMessage("");
                                player.sendMessage(Color.code("&8--------------------------"));
                            }
                        } else {
                            player.sendMessage(Color.code("&cNo rank with that name found!"));
                        }
                    } else {
                        player.sendMessage(Color.code("&8--------------------------"));
                        player.sendMessage("");
                        player.sendMessage(Color.code("&f- &f/ranks prefix [rankname] tab [prefix] &b- Set the tab rank prefix"));
                        player.sendMessage(Color.code("&f- &f/ranks prefix [rankname] chat [prefix] &b- Set the chat rank prefix"));
                        player.sendMessage(Color.code("&f- &f/ranks prefix [rankname] tag [prefix] &b- Set the tag rank prefix"));
                        player.sendMessage("");
                        player.sendMessage(Color.code("&8--------------------------"));
                    }
                }


                if (args[0].equals("player")) {
                    if (args.length == 3) {
                        if (Bukkit.getServer().getPlayer(args[1]).isOnline()) {
                            Player p = Bukkit.getServer().getPlayer(args[1]);
                            player.sendMessage(Color.code("&a" + args[1] + " rank is now set to " + args[2]) + "!");
                            players.set(p.getName() + ".rank", args[2]);
                            p.setPlayerListName(Color.code(ranks.get(players.get(p.getName() + ".rank") + ".prefixtab") + " " + p.getName()));
                            NameTagChanger.changePlayerName(p, "" + ranks.get(players.get(p.getName() + ".rank") + ".prefixtag"), "", TeamAction.CREATE);
                            LuckPermsProvider.get().getUserManager().getUser(args[1]).setPrimaryGroup(args[2]);
                            try {
                                players.save(playersf);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            // NametagChanger.changePlayerName(player);
                        } else {
                            player.sendMessage(Color.code("&cThis player is not online!"));
                        }
                    }
                }

                return;
            }
        }

        player.sendMessage(Color.code("&8--------------------------"));
        player.sendMessage("");
        player.sendMessage(Color.code("&f- &f/ranks create [rankname] [prefix] &b- Create new rank"));
        player.sendMessage(Color.code("&f- &f/ranks player [player] [rankname] &b- Set rank to a player"));
        player.sendMessage(Color.code("&f- &f/ranks prefix [rankname] [tab/chat/tag] [prefix] &b- Set rank to a player"));
        player.sendMessage(Color.code("&f- &f/ranks perm [add/remove] [rankname] [permname] &b- Add/Remove perm to a rank"));
        player.sendMessage("");
        player.sendMessage(Color.code("&8--------------------------"));
    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (!e.getPlayer().hasPlayedBefore()) {
            players.getConfig().set(player.getName() + ".rank", "default");
            NameTagChanger.changePlayerName(player, "" + ranks.getConfig().get(players.getConfig().get(player.getName() + ".rank") + ".prefixtag"), "", TeamAction.CREATE);
            player.setPlayerListName(Color.code(ranks.getConfig().get(players.getConfig().get(player.getName() + ".rank") + ".prefixtab") + " " + player.getName()));
            players.saveConfig();
            LuckPermsProvider.get().getUserManager().getUser(player.getName()).setPrimaryGroup("default");
            // NametagChanger.changePlayerName(e.getPlayer());
        } else {
            player.setPlayerListName(Color.code(ranks.getConfig().get(players.get(player.getName() + ".rank") + ".prefixtab") + " " + player.getName()));
            NameTagChanger.changePlayerName(player, "" + ranks.get(players.get(player.getName() + ".rank") + ".prefixtag"), "", TeamAction.CREATE);
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        String playerName = e.getPlayer().getName();
        String playerRank = "" + players.get(playerName + ".rank");
        if (playerRank == "default") {
            e.setFormat(Color.code(ranks.get(playerRank + ".prefixchat") + " " + e.getPlayer().getDisplayName() + "&7: &r") + e.getMessage());
        } else {
            e.setFormat(Color.code(ranks.get(playerRank + ".prefixchat") + " " + e.getPlayer().getDisplayName() + "&7: &7") + e.getMessage());
        }
    }

     */
}
