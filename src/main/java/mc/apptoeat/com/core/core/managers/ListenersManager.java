package mc.apptoeat.com.core.core.managers;

import mc.apptoeat.com.core.CoreAPI;
import mc.apptoeat.com.core.core.listeners.JoinQuitMessages;
import mc.apptoeat.com.core.utils.manager.Manager;
import mc.apptoeat.com.core.utils.rank.RankSystem;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class ListenersManager extends Manager<Listener> {

    public ListenersManager() {
        register(new JoinQuitMessages());
        register(new RankSystem());
        // register(new RankSystem());
    }

    @Override
    public void register(Listener listener) {
        super.register(listener);
        Bukkit.getServer().getPluginManager().registerEvents(listener, CoreAPI.getPlugin());
    }
}
