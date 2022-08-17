package mc.apptoeat.com.core.core.config;

import lombok.Getter;
import mc.apptoeat.com.core.utils.config.CustomConfig;
import mc.apptoeat.com.core.utils.manager.Manager;

@Getter
public class ConfigManager extends Manager<CustomConfig> {

    private final CustomConfig dataConfig;
    private final CustomConfig itemsConfig;
    private final CustomConfig messageConfig;
    private final CustomConfig commandConfig;
    private final CustomConfig ranksConfig;

    public ConfigManager() {
        register(commandConfig = new CustomConfig("commands"));
        register(dataConfig = new CustomConfig("data"));
        register(itemsConfig = new CustomConfig("items"));
        register(messageConfig = new CustomConfig("messages"));
        register(ranksConfig = new CustomConfig("ranks"));
    }
}
