package mc.apptoeat.com.core.core.commands;

import mc.apptoeat.com.core.CoreAPI;
import mc.apptoeat.com.core.utils.commands.Command;
import mc.apptoeat.com.core.utils.config.FileManager;
import mc.apptoeat.com.core.utils.message.Color;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class GamemodeCommand extends Command {
    public GamemodeCommand() {
        super(FileManager.getOrDefault(CoreAPI.getInstance().getConfigManager().getCommandConfig(), "commands.gamemode", "gamemode"), "Lets you change your gamemode","&b%target% &fgamemode has been changed to &b%mode%", "gmc","gms","gma","gmsp","gm","gm1","gm2","gm3","gm0");
        allowConfig("messages.gamemode");
    }

    @Override
    public void executeCommand(Player player, String commandLabel, String[] args) {
        GameMode mode = null;
        Player target = null;
        String arg0 = null;
        String arg1 = null;
        if (args.length > 0) {
            arg0 = args[0];
            if (args.length > 1) {
                arg1 = args[1];
            }
        }

        //Get the gamemode
        if (commandLabel.equalsIgnoreCase("gamemode") || commandLabel.equalsIgnoreCase("gm")) {
            mode = getGamemode(arg0);
            if (arg1 != null) {
                target = Bukkit.getPlayer(arg1);
            }
        }

        if (commandLabel.equalsIgnoreCase("gmc") || commandLabel.equalsIgnoreCase("gm1")) {
            mode = GameMode.CREATIVE;
        }

        if (commandLabel.equalsIgnoreCase("gms") || commandLabel.equalsIgnoreCase("gm0")) {
            mode = GameMode.SURVIVAL;
        }

        if (commandLabel.equalsIgnoreCase("gma") || commandLabel.equalsIgnoreCase("gm2")) {
            mode = GameMode.ADVENTURE;
        }

        if (commandLabel.equalsIgnoreCase("gmsp") || commandLabel.equalsIgnoreCase("gm3")) {
            mode = GameMode.SPECTATOR;
        }

        if (mode == null) {
            player.sendMessage(Color.code("&7Please use &f/gamemode (type)"));
            return;
        }

        //Get the target
        if (target == null && arg0 != null && arg1 != null) {
            target = Bukkit.getPlayer(arg1);
            if (target == null) {
                //Check if the player is online
                player.sendMessage(Color.code("&7The player you were looking for is not online, player &f" + arg1));
                return;
            }
        } else target = player;

        changeGamemode(target,mode);
        sendMessage(player,target.getGameMode().toString(),target.getName());
    }

    public GameMode getGamemode(String type) {
        if (type == null) return null;
        if (type.equals("1") || type.equalsIgnoreCase("c") || type.equalsIgnoreCase("creative")) {
            return GameMode.CREATIVE;
        } else if (type.equals("0") || type.equalsIgnoreCase("s") || type.equalsIgnoreCase("survival")) {
            return GameMode.SURVIVAL;
        } else if (type.equals("3") || type.equalsIgnoreCase("sp") || type.equalsIgnoreCase("spectator")) {
            return GameMode.SPECTATOR;
        } else if (type.equals("2") || type.equalsIgnoreCase("a") || type.equalsIgnoreCase("adventure")) {
            return GameMode.ADVENTURE;
        }

        return null;
    }

    public void changeGamemode(Player player, GameMode mode) {
        player.setGameMode(mode);
    }
}
