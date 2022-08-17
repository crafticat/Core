package mc.apptoeat.com.core.utils.blocker;

import mc.apptoeat.com.core.CoreAPI;
import mc.apptoeat.com.core.utils.config.FileManager;
import mc.apptoeat.com.core.utils.message.Color;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Blocker implements Listener {

    private String message;

    public Blocker(String message) {
        this.message = Color.code(message);
        Bukkit.getServer().getPluginManager().registerEvents(this, CoreAPI.getPlugin());
    }

    public void allowConfig(String path) {
        message = FileManager.getOrDefault(CoreAPI.getInstance().getConfigManager().getMessageConfig(), path, message);
    }

    public void sendBlock(Player player) {
        player.sendMessage(message.replace("%player%",player.getName()));
    }
}
