package mc.apptoeat.com.core.core.commands;

import mc.apptoeat.com.core.utils.commands.Command;
import mc.apptoeat.com.core.utils.message.Color;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class TeleportCommand extends Command {

    public TeleportCommand() {
        super("teleport", "Teleports you to a player", "&fYou have been teleported to &b%target%", "tp");
    }

    @Override
    public void executeCommand(Player player, String commandLabel, String[] args) {
        String arg0 = null;
        if (args.length > 0) {
            arg0 = args[0];
        }

        if (arg0 == null) {
            player.sendMessage(Color.code("&7Please specify a player to teleport to."));
            return;
        }

        Player target = Bukkit.getPlayer(arg0);
        if (target == null) {
            player.sendMessage(Color.code("&7The player you were looking for was not online"));
            return;
        }

        sendMessage(player,target.getName(),"null");
        Location tpPos = target.getLocation().getBlock().getLocation();
        tpPos.setYaw(player.getLocation().getYaw());
        tpPos.setPitch(player.getLocation().getPitch());
        tpPos.setX(tpPos.getX() + 0.5);
        tpPos.setZ(tpPos.getZ() + 0.5);
        player.teleport(tpPos);
    }
}
