package mc.apptoeat.com.core.utils.config;

public class FileManager {

    public static <V> V getOrDefault(CustomConfig config, String path, V def) {
        if (!config.getConfig().isSet(path)) {
            config.getConfig().set(path,def);
            config.saveConfig();
            return def;
        } else {
            return (V) config.getConfig().get(path);
        }
    }

    public static void update(CustomConfig config, String path, Object value) {
        config.getConfig().set(path,value);
        config.saveConfig();
    }
}
