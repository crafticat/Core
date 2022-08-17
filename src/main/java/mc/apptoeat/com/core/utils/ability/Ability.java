package mc.apptoeat.com.core.utils.ability;

import mc.apptoeat.com.core.CoreAPI;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class Ability implements Listener {

    protected boolean enabled;

    public Ability() {
        Bukkit.getServer().getPluginManager().registerEvents(this, CoreAPI.getPlugin());
    }
}
