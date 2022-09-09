package mc.apptoeat.com.core.utils.message;

import org.bukkit.ChatColor;

public class Color {
    public static String code(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }
    public static String SHORTER_LINE() {
        return ChatColor.translateAlternateColorCodes('&', "&8&m---------------------------------------");
    }
}
