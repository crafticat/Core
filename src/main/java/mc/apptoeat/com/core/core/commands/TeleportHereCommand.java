package mc.apptoeat.com.core.core.commands;

import mc.apptoeat.com.core.utils.commands.Command;
import mc.apptoeat.com.core.utils.message.Color;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TeleportHereCommand extends Command {

    public TeleportHereCommand() {
        super("tphere", "Teleports a player to you", "&b%target% &fwas teleported to you", "s");
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
        target.teleport(player.getLocation());
    }
}