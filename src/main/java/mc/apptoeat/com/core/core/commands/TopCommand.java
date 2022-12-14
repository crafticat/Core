package mc.apptoeat.com.core.core.commands;

import mc.apptoeat.com.core.utils.commands.Command;
import mc.apptoeat.com.core.utils.message.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class TopCommand extends Command {
    public TopCommand() {
        super("top", "teleports you to the top", "&fYou are now on top of the world");
    }

    @Override
    public void executeCommand(Player player, String commandLabel, String[] args) {
        if (player.hasPermission("core.top")) {
            Location location = player.getLocation();
            location.setY(player.getWorld().getMaxHeight());

            while (location.getBlock().getType().equals(Material.AIR)) {
                if (location.getY() < player.getLocation().getY()) {
                    location = null;
                    break;
                }

                location.setY(location.getY() - 1);
            }

            if (location == null) {
                player.sendMessage(Color.code("&7Could not find anything above you"));
                return;
            }

            player.teleport(location);
            sendMessage(player, "null", "null");
        } else {
            player.sendMessage(Color.code("&b&lAppToEat &8≫ &fUnknown command."));
        }
    }
}
