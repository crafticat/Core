package mc.apptoeat.com.core.core.managers;

import lombok.Getter;
import mc.apptoeat.com.core.CoreAPI;
import mc.apptoeat.com.core.core.tab.TabManager;
import org.bukkit.ChatColor;

@Getter
public class Managers {

    private final CommandsManager commandsManager;
    private final ListenersManager listenersManager;
    private final ItemsManager itemsManager;
    private final TabManager tabManager;

    public Managers() {
        commandsManager = new CommandsManager();
        listenersManager = new ListenersManager();
        itemsManager = new ItemsManager();
        tabManager = new TabManager(CoreAPI.getPlugin());
        tabSetup();
    }

    private void tabSetup() {
        tabManager.setHeader(ChatColor.translateAlternateColorCodes('&', "&f&lAppToEat Network"));
        tabManager.setFooter(ChatColor.translateAlternateColorCodes('&', "&7You are correctly playing on mc.apptoeat.com"));
        tabManager.showTab();
    }
}
