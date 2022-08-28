package mc.apptoeat.com.core.core.tab;

import mc.apptoeat.com.core.Core;
import mc.apptoeat.com.core.CoreAPI;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TabManager {

    private ChatComponentText header;
    private ChatComponentText footer;

    private Core plugin;

    public TabManager(Core plugin) {
        this.plugin = plugin;
    }

    public void showTab() {
        if (header == null || footer == null) return;

        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

            PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();

            @Override
            public void run() {
                try {

                    Field a = packet.getClass().getDeclaredField("a");
                    a.setAccessible(true);
                    Field b = packet.getClass().getDeclaredField("b");
                    b.setAccessible(true);

                    a.set(packet, header);
                    b.set(packet, footer);

                    if (Bukkit.getOnlinePlayers().size() != 0) {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 10, 40);
    }

    public void setHeader(String newHeader) {
        header = new ChatComponentText(newHeader);
    }

    public void setFooter(String newFooter) {
        footer = new ChatComponentText(newFooter);
    }
}
