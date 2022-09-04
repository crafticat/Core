package mc.apptoeat.com.core.core.commands;

import mc.apptoeat.com.core.CoreAPI;
import mc.apptoeat.com.core.core.managers.DataManager;
import mc.apptoeat.com.core.utils.commands.Command;
import mc.apptoeat.com.core.utils.message.Color;
import org.bukkit.entity.Player;

public class StaffModeCommand extends Command {

    public StaffModeCommand() {
        super("staff", "switch between staff modes", "", "staffmode","a");
    }

    @Override
    public void executeCommand(Player player, String commandLabel, String[] args) {
        if (player.hasPermission("core.staff")) {
            DataManager dataManager = CoreAPI.getInstance().getDataManager();
            if (dataManager.isStaffPlayer(player)) {
                dataManager.getStaffPlayer(player).toggleStaffMode();
                return;
            }
        } else {
            player.sendMessage(Color.code("&b&lAppToEat &8≫ &fUnknown command."));
        }
    }
}
