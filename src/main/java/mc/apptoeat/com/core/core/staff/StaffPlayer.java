package mc.apptoeat.com.core.core.staff;

import mc.apptoeat.com.core.CoreAPI;
import mc.apptoeat.com.core.core.data.DataPlayer;
import mc.apptoeat.com.core.core.managers.DataManager;
import mc.apptoeat.com.core.utils.item.Item;
import mc.apptoeat.com.core.utils.message.Color;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class StaffPlayer extends DataPlayer {

    private ItemStack[] storedItems;
    private boolean staffMode;
    private boolean vanished;

    public StaffPlayer(Player player) {
        super(player);
    }

    public void toggleStaffMode() {
        if (staffMode) {
            disableStaffMode();
            return;
        }
        enableStaffMode();
    }

    public void enableStaffMode() {
        PlayerInventory inventory = getPlayer().getInventory();
        storedItems = inventory.getContents();
        inventory.clear();

        for (Item item : CoreAPI.getInstance().getManagers().getItemsManager().getList()) {
            inventory.setItem(item.getSlot(), item.getItem());
        }

        staffMode = true;
        player.setFlySpeed(0.5f);
        player.setAllowFlight(true);
        player.setFlying(true);
        CoreAPI.getInstance().getDataManager().getPlayer(player).setFly(true);
        enableVanish();
        getPlayer().sendMessage(Color.code("&fYou have toggled staff mode &bon"));
    }

    public void disableStaffMode() {
        PlayerInventory inventory = getPlayer().getInventory();
        inventory.clear();
        staffMode = false;

        //Restore items
        int index = 0;
        for (ItemStack stack : storedItems) {
            if (stack != null) {
                inventory.setItem(index,stack);
            }

            index++;
        }

        player.setFlySpeed(0.1f);
        player.setFlying(false);
        player.setAllowFlight(false);
        CoreAPI.getInstance().getDataManager().getPlayer(player).setFly(false);
        disableVanish();
        getPlayer().sendMessage(Color.code("&fYou have toggled staff mode &boff"));
    }

    public void toggleVanish() {
        if (vanished) {
            disableVanish();
            return;
        }

        enableVanish();
    }

    public void enableVanish() {
        vanished = true;

        Bukkit.getOnlinePlayers().forEach(p -> {
            if (!CoreAPI.getInstance().getDataManager().isStaffPlayer(p)) {
                p.hidePlayer(player);
            }
        });
        player.sendMessage(Color.code("&fYou have toggled vanish &bon"));
    }

    public void disableVanish() {
        vanished = false;

        Bukkit.getOnlinePlayers().forEach(p -> {
            p.showPlayer(player);
        });
        player.sendMessage(Color.code("&fYou have toggled vanish &boff"));
    }

    @EventHandler
    public void join(PlayerJoinEvent e) {
        if (vanished) {
            e.getPlayer().hidePlayer(player);
        }
    }
}
