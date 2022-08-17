package mc.apptoeat.com.core.utils.item;

import lombok.Getter;
import lombok.Setter;
import mc.apptoeat.com.core.CoreAPI;
import mc.apptoeat.com.core.utils.config.FileManager;
import mc.apptoeat.com.core.utils.gui.GlobalGui;
import mc.apptoeat.com.core.utils.message.Color;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@Getter
@Setter
public class Item implements Listener {

    protected ItemStack item;
    private boolean config;
    private GlobalGui gui;
    private boolean disableMovements = true;
    private int slot;

    public Item(ItemStack item,int slot) {
        this.slot = slot;
        this.item = item;
        Bukkit.getServer().getPluginManager().registerEvents(this,CoreAPI.getPlugin());
    }

    public void changeItemName(String name) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Color.code(name));
        item.setItemMeta(meta);
    }

    public void allowConfig( String path) {
        item = FileManager.getOrDefault(CoreAPI.getInstance().getConfigManager().getItemsConfig(), path,item);
    }

    public void setItemGui(GlobalGui gui) {
        this.gui = gui;
    }

    @EventHandler
    public void rightClick(PlayerInteractEvent e) {
        if (!(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) return;
        if (e.getItem() != null && e.getItem().getItemMeta() != null && e.getItem().getItemMeta().equals(item.getItemMeta())) {
            rightClick(e.getPlayer());
            if (gui != null) gui.minOpen(e.getPlayer());
        }
    }

    //Disables item movements
    @EventHandler
    public void move(InventoryMoveItemEvent e) {
        if (disableMovements && e.getItem().getItemMeta().equals(item.getItemMeta())) e.setCancelled(true);
    }

    @EventHandler
    public void move(InventoryDragEvent e) {
        if (disableMovements && e.getCursor().getItemMeta().equals(item.getItemMeta())) e.setCancelled(true);
    }


    public void rightClick(Player player) {}
}
