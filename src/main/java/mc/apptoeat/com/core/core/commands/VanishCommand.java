package mc.apptoeat.com.core.core.commands;

import mc.apptoeat.com.core.CoreAPI;
import mc.apptoeat.com.core.core.managers.DataManager;
import mc.apptoeat.com.core.utils.commands.Command;
import org.bukkit.entity.Player;

public class VanishCommand extends Command {
    public VanishCommand() {
        super("vanish", "Vanish command", "", "v");
    }

    @Override
    public void executeCommand(Player player, String commandLabel, String[] args) {
        DataManager dataManager = CoreAPI.getInstance().getDataManager();

        if (!dataManager.isStaffPlayer(player)) return;
        dataManager.getStaffPlayer(player).toggleVanish();
    }
}
