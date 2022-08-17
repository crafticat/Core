package mc.apptoeat.com.core;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Core extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        CoreAPI coreAPI = new CoreAPI(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
