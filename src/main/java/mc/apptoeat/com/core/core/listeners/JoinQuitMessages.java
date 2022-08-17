package mc.apptoeat.com.core.core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitMessages implements Listener {

    @EventHandler
    public void join(PlayerJoinEvent e) {
        e.setJoinMessage(null);
    }

    @EventHandler
    public void join(PlayerQuitEvent e) {
        e.setQuitMessage(null);
    }
}
