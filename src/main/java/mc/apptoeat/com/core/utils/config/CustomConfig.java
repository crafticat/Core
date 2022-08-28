package mc.apptoeat.com.core.utils.config;

import lombok.Getter;
import mc.apptoeat.com.core.CoreAPI;
import mc.apptoeat.com.core.utils.message.Color;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

@Getter
public class CustomConfig {

    private final FileConfiguration config;
    private final File file;

    public CustomConfig(String name) {
        file = new File(CoreAPI.getPlugin().getDataFolder(), name + ".yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ignore) {}
        }

        config = YamlConfiguration.loadConfiguration(file);
        saveConfig();
    }

    public void saveConfig() {
        try {
            config.save(file);
        } catch (IOException e) {
            Bukkit.broadcastMessage(Color.code("&7Couldnt save file"));
        }
    }
}
