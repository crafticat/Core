package mc.apptoeat.com.core.core.commands;

import mc.apptoeat.com.core.utils.commands.Command;
import org.bukkit.entity.Player;

public class SunCommand extends Command {
    public SunCommand() {
        super("sun", "Makes sun shines", "The sun shall shine");
    }

    @Override
    public void executeCommand(Player player, String commandLabel, String[] args) {
        player.getWorld().setTime(0);
        player.getWorld().setStorm(false);
    }
}
