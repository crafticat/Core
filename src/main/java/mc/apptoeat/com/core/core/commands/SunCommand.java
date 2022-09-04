package mc.apptoeat.com.core.core.commands;

import mc.apptoeat.com.core.utils.commands.Command;
import mc.apptoeat.com.core.utils.message.Color;
import org.bukkit.entity.Player;

public class SunCommand extends Command {
    public SunCommand() {
        super("sun", "Makes sun shines", "The sun shall shine");
    }

    @Override
    public void executeCommand(Player player, String commandLabel, String[] args) {
        if (player.hasPermission("core.sun")) {
            player.getWorld().setTime(0);
            player.getWorld().setStorm(false);
        } else {
            player.sendMessage(Color.code("&b&lAppToEat &8≫ &fUnknown command."));
        }
    }
}
