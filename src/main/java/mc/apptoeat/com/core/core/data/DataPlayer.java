package mc.apptoeat.com.core.core.data;

import lombok.Getter;
import lombok.Setter;
import mc.apptoeat.com.core.CoreAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

@Getter
@Setter
public class DataPlayer implements Listener {
    protected final Player player;
    private boolean fly;
    private boolean frozen;

    public DataPlayer(Player player) {
        this.player = player;
        Bukkit.getServer().getPluginManager().registerEvents(this, CoreAPI.getPlugin());
    }
}
