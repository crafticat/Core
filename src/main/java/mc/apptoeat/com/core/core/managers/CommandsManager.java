package mc.apptoeat.com.core.core.managers;

import mc.apptoeat.com.core.core.commands.*;
import mc.apptoeat.com.core.utils.commands.Command;
import mc.apptoeat.com.core.utils.manager.Manager;

public class CommandsManager extends Manager<Command> {

    public CommandsManager() {
        register(new GamemodeCommand());
        register(new FlyCommand());
        register(new EnchantCommand());
        register(new ReportCommand());
        register(new TopCommand());
        register(new TeleportCommand());
        register(new SunCommand());
        register(new TeleportHereCommand());
        register(new StaffModeCommand());
        register(new SpeedCommand());
        register(new VanishCommand());
    }
}
