package mc.apptoeat.com.core.core.commands;

import mc.apptoeat.com.core.CoreAPI;
import mc.apptoeat.com.core.core.webhook.Bot;
import mc.apptoeat.com.core.utils.commands.Command;
import mc.apptoeat.com.core.utils.config.CustomConfig;
import mc.apptoeat.com.core.utils.message.Color;
import net.luckperms.api.LuckPermsProvider;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ReportCommand extends Command {

    Map<Player, Integer> cooldown = new HashMap<>();

    public ReportCommand() {
        super("corereport", "report player", "&aYou reported", "r");
        Bukkit.getScheduler().scheduleSyncRepeatingTask(CoreAPI.getPlugin(), new Runnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    cooldown.put(p, cooldown.getOrDefault(p, 0) - 1);
                }
            }
        }, 0L, 20L);
    }

    @Override
    public void executeCommand(Player player, String commandLabel, String[] args) {
        if (cooldown.getOrDefault(player, 0) <= 0) {
            if (args.length >= 1) {
                if (Bukkit.getPlayer(args[0]) != null && Bukkit.getPlayer(args[0]).isOnline()) {
                    cooldown.put(player, 60);

                    if (args.length >= 2) {
                        player.sendMessage(Color.code("&aYou reported " + args[0]) + ". Reason: " + args[1]);
                    } else {
                        player.sendMessage(Color.code("&aYou reported " + args[0]) );
                    }

                    TextComponent message = new TextComponent();
                    TextComponent message2 = new TextComponent();

                    if (args.length >= 2) {
                        Bot.sendReportMessage(player.getName() + " reported " + args[0], args[1], player.getServer().getServerName(), 18, 202, 219);
                    } else {
                        Bot.sendReportMessage(player.getName() + " reported " + args[0], null, player.getServer().getServerName(), 18, 202, 219);
                    }

                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (p.hasPermission("core.report.get")) {
                            p.sendMessage(Color.code("&8&m-----------------------------------"));
                            p.sendMessage("");

                            if (args.length >= 2) {
                                p.sendMessage(Color.code("&b" + args[0] + " &7was reported by &b" + player.getName() + "&7. Reason: " + args[1]));
                            } else {
                                p.sendMessage(Color.code("&b" + args[0] + " &7was reported by &b" + player.getName() + "&7."));
                            }

                            p.sendMessage("");

                            message.setText("Click here to teleport to the reporter");
                            message.setColor(ChatColor.AQUA);
                            message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/minecraft:tp " + player.getName()));
                            message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("[Click to teleport]").color(ChatColor.AQUA).create()));

                            p.spigot().sendMessage(message);

                            p.sendMessage("");

                            message2.setText("Click here to teleport to the victim");
                            message2.setColor(ChatColor.GRAY);
                            message2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/minecraft:tp " + args[0]));
                            message2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("[Click to teleport]").color(ChatColor.GRAY).create()));

                            p.spigot().sendMessage(message2);

                            p.sendMessage("");
                            p.sendMessage(Color.code("&8&m-----------------------------------"));
                        }
                    }
                } else {
                    player.sendMessage(Color.code("&cPlayer is not found or not online"));
                }
            } else {
                player.sendMessage(Color.code("&cPlease do /report <player> <reason>"));
            }
        } else {
            player.sendMessage(Color.code("&cYou need to wait &e" + cooldown.getOrDefault(player, 0) + " &cmore seconds to use this command"));
        }
    }
}
