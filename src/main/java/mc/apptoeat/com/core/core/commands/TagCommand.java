package mc.apptoeat.com.core.core.commands;

import mc.apptoeat.com.core.utils.commands.Command;
import mc.apptoeat.com.core.utils.gui.Gui;
import mc.apptoeat.com.core.utils.message.Color;
import mc.apptoeat.com.core.utils.runnable.PlayerRunnable;
import mc.apptoeat.com.core.utils.tags.Tag;
import mc.apptoeat.com.core.utils.tags.TagUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TagCommand extends Command {

    public TagCommand() {
        super("tags", "tag command", "");
    }

    public void executeCommand(Player player, String commandLabel, String[] args) {

        if (player.hasPermission("tag.create") && args.length > 0) {
            if (args.length >= 3) {
                if (player.getItemInHand() != null && !player.getItemInHand().getType().equals(Material.AIR)) {
                    Tag tag = new Tag(args[1], args[2], player.getItemInHand());
                    TagUtil.addTag(tag);
                    player.sendMessage(Color.code("You created new tag"));
                } else {
                    player.sendMessage(Color.code("&cYou need to hold the tag item"));
                }
            } else {
                player.sendMessage(Color.code("&cPlease do /tag create [name] [subfix]"));
            }
        } else if (player.hasPermission("tag.give") && args.length > 0) {
            if (args.length >= 2) {
                Player p = null;
                if (Bukkit.getPlayer(args[2]) != null) {
                    p = Bukkit.getPlayer(args[2]);
                } else {
                    player.sendMessage(Color.code("&cThere is no player with this name"));
                }
                if (TagUtil.getByName(args[1]) != null) {
                    TagUtil.giveTag(player, TagUtil.getByName(args[1]));
                } else {
                    player.sendMessage(Color.code("&cNo tag with this name found"));
                }
                player.sendMessage(Color.code("You gave tag to " + args[2]));
            } else {
                player.sendMessage(Color.code("&cPlease do /tag give [name] [player]"));
            }
        } else {

            Gui gui = new Gui(Color.code("&bTags"), 54);

            int index = 0;

            for (Tag tag : TagUtil.getTotalTags()) {
                if (TagUtil.getOwnTags(player).contains(tag.getName())) {
                    if (TagUtil.getUsedTag(player) == tag) {
                        gui.createGuiItem(tag.getItem(), index, Color.code(tag.getName()), new PlayerRunnable() {
                            @Override
                            public void run(Player p) {
                                TagUtil.unuseTag(p);
                                p.closeInventory();
                            }
                        }, "&cClick to disable");
                    } else {
                        gui.createGuiItem(tag.getItem(), index, Color.code(tag.getName()), new PlayerRunnable() {
                            @Override
                            public void run(Player p) {
                                TagUtil.useTag(p, tag);
                                p.closeInventory();
                            }
                        }, tag.getSubfix(), "&cClick to enable");
                    }
                } else {
                    gui.createGuiItem(new ItemStack(Material.getMaterial(351)), index, Color.code("&c???"), new PlayerRunnable() {
                        @Override
                        public void run(Player p) {
                            p.closeInventory();
                        }
                    }, "&7Unlock to enable");
                }
                index++;
            }
            gui.openInventory(player);
        }
    }
}
