package mc.apptoeat.com.core;

import lombok.Getter;
import mc.apptoeat.com.core.core.config.ConfigManager;
import mc.apptoeat.com.core.core.managers.DataManager;
import mc.apptoeat.com.core.core.managers.Managers;
import mc.apptoeat.com.core.core.tab.TabManager;
import mc.apptoeat.com.core.utils.rank.RankSystem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

@Getter
public class CoreAPI {

    @Getter private static Core plugin;
    @Getter private static CoreAPI instance;
    private final ConfigManager configManager;
    private final Managers managers;
    private final DataManager dataManager;
    private final TabManager tab;

    public CoreAPI(Core core) {
        plugin = core;
        instance = this;
        configManager = new ConfigManager();
        managers = new Managers();
        dataManager = new DataManager();
        this.tab = new TabManager(core);
    }
}
