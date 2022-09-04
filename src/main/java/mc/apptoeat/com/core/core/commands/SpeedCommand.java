package mc.apptoeat.com.core.core.commands;

import mc.apptoeat.com.core.utils.commands.Command;
import mc.apptoeat.com.core.utils.message.Color;
import org.bukkit.entity.Player;

import java.text.spi.NumberFormatProvider;

public class SpeedCommand extends Command {
    public SpeedCommand() {
        super("speed", "Lets you change your speed", "Your speed has been set to %mode%", "flyspeed");
    }

    @Override
    public void executeCommand(Player player, String commandLabel, String[] args) {

        if(!player.hasPermission("core.speed")){
            player.sendMessage(Color.code("&b&lAppToEat &8≫ &fUnknown command."));
            return;
        }
        int speed = 1;

        if(commandLabel.length() == 0){
            player.sendMessage(Color.code("&bProvide a number from 1 to 10"));
        }

        try {
            speed = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException e){
            player.sendMessage(Color.code("&bProvide a number from 1 to 10"));
        }

        if(speed < 1 || speed > 10 ){
            player.sendMessage(Color.code("&bProvide a number from 1 to 10"));
        }

        if(player.isFlying()){
            player.setFlySpeed((float) speed / 10);

        }
        else {
            player.setWalkSpeed((float) speed / 5);
        }
        player.sendMessage(Color.code("Speed value has been changed to " + "&a" + speed));

    }
}
