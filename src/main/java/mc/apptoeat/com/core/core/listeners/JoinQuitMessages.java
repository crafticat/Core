package mc.apptoeat.com.core.core.listeners;

import mc.apptoeat.com.core.utils.message.Color;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitMessages implements Listener {

    @EventHandler
    public void join(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        e.getPlayer().sendMessage(Color.SHORTER_LINE());
        e.getPlayer().sendMessage(" ");
        e.getPlayer().sendMessage(Color.code(" &b&lWelcome " + e.getPlayer().getName()));
        e.getPlayer().sendMessage(" ");
        e.getPlayer().sendMessage(Color.code(" &f• &bWebsite&7: &fapptoeat.com"));
        e.getPlayer().sendMessage(Color.code(" &f• &bStore&7: &fapptoeat.com/store"));
        e.getPlayer().sendMessage(Color.code(" &f• &bDiscord&7: &fdiscord.gg/QE9CJKGhnA"));
        e.getPlayer().sendMessage(" ");
        e.getPlayer().sendMessage(Color.SHORTER_LINE());
    }

    @EventHandler
    public void join(PlayerQuitEvent e) {
        e.setQuitMessage(null);
    }
}
