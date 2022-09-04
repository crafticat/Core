package mc.apptoeat.com.core.core.commands;

import mc.apptoeat.com.core.CoreAPI;
import mc.apptoeat.com.core.core.managers.DataManager;
import mc.apptoeat.com.core.utils.commands.Command;
import mc.apptoeat.com.core.utils.message.Color;
import org.bukkit.entity.Player;

public class VanishCommand extends Command {
    public VanishCommand() {
        super("vanish", "Vanish command", "", "v");
    }

    @Override
    public void executeCommand(Player player, String commandLabel, String[] args) {
        if (player.hasPermission("core.vanish")) {
            DataManager dataManager = CoreAPI.getInstance().getDataManager();

            if (!dataManager.isStaffPlayer(player)) return;
            dataManager.getStaffPlayer(player).toggleVanish();
        } else {
            player.sendMessage(Color.code("&b&lAppToEat &8≫ &fUnknown command."));
        }
    }
}
