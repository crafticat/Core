package mc.apptoeat.com.core.core.managers;

import lombok.Getter;
import mc.apptoeat.com.core.CoreAPI;
import org.bukkit.ChatColor;

@Getter
public class Managers {

    private final CommandsManager commandsManager;
    private final ListenersManager listenersManager;
    private final ItemsManager itemsManager;

    public Managers() {
        commandsManager = new CommandsManager();
        listenersManager = new ListenersManager();
        itemsManager = new ItemsManager();
    }
}
