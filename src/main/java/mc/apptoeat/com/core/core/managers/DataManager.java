package mc.apptoeat.com.core.core.managers;

import lombok.Getter;
import mc.apptoeat.com.core.CoreAPI;
import mc.apptoeat.com.core.core.data.DataPlayer;
import mc.apptoeat.com.core.core.staff.StaffPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;

@Getter
public class DataManager implements Listener {
    private final Map<Player, DataPlayer> dataMap;

    public DataManager() {
        dataMap = new HashMap<>();
        Bukkit.getServer().getPluginManager().registerEvents(this, CoreAPI.getPlugin());
    }

    public void addPlayer(Player player) {
        DataPlayer data;

        //Creates a staff player if the player has the right perms.
        if (player.hasPermission("core.staff")) {
            data = new StaffPlayer(player);
        } else data = new DataPlayer(player);

        //Updates the data
        dataMap.put(player,data);
    }

    public void removePlayer(Player player) {
        dataMap.put(player,null);
    }

    public DataPlayer getPlayer(Player player) {
        if (dataMap.containsKey(player)) return dataMap.get(player);
        return null;
    }

    public boolean isPlayer(Entity player) {
        if (!(player instanceof Player)) return false;
        return dataMap.containsKey((Player) player);
    }

    public boolean isStaffPlayer(Player player) {
        return dataMap.containsKey(player) && dataMap.get(player) instanceof StaffPlayer;
    }

    public StaffPlayer getStaffPlayer(Player player) {
        if (dataMap.containsKey(player) && dataMap.get(player) instanceof StaffPlayer) return (StaffPlayer) dataMap.get(player);
        return null;
    }

    @EventHandler
    public void join(PlayerJoinEvent e) {
        addPlayer(e.getPlayer());
    }

    @EventHandler
    public void quit(PlayerQuitEvent e) {
        removePlayer(e.getPlayer());
    }
}
