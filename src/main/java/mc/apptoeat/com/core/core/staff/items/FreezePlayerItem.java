package mc.apptoeat.com.core.core.staff.items;

import mc.apptoeat.com.core.CoreAPI;
import mc.apptoeat.com.core.core.data.DataPlayer;
import mc.apptoeat.com.core.core.managers.DataManager;
import mc.apptoeat.com.core.core.staff.StaffPlayer;
import mc.apptoeat.com.core.utils.item.Item;
import mc.apptoeat.com.core.utils.message.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class FreezePlayerItem extends Item {
    public FreezePlayerItem() {
        super(new ItemStack(Material.ICE), 4);
        changeItemName(Color.code("&b&lFreeze &7(Right click)"));
    }

    @EventHandler
    public void useEntity(PlayerInteractEntityEvent e) {
        Entity entity = e.getRightClicked();
        Player player = e.getPlayer();

        DataManager dataManager = CoreAPI.getInstance().getDataManager();
        if (!dataManager.isStaffPlayer(player)) return;
        StaffPlayer staffPlayer = dataManager.getStaffPlayer(player);
        ItemStack stack = e.getPlayer().getInventory().getItemInHand();
        if (stack == null || stack.getItemMeta() == null) return;
        if (!stack.getItemMeta().equals(item.getItemMeta())) return;

        if (!(entity instanceof Player)) return;

        DataPlayer data = dataManager.getPlayer((Player) entity);
        if (data == null) return;
        data.setFrozen(!data.isFrozen());

        player.sendMessage(Color.code("&fYou have set frozen mode for &b&l" + data.getPlayer().getName()) + " &fto &b" + data.isFrozen());
    }

    @EventHandler
    public void move(PlayerMoveEvent e) {
        DataManager dataManager = CoreAPI.getInstance().getDataManager();
        Player player = e.getPlayer();
        if (!dataManager.isPlayer(player)) return;
        if (!dataManager.getPlayer(player).isFrozen()) return;

        Location fromLocation = e.getFrom();
        Location toLocation = e.getTo();
        if(toLocation == null || fromLocation.toVector().distance(toLocation.toVector()) < 0.01) {
            return;
        }

        e.setTo(fromLocation);
        player.sendMessage(Color.code("&fYou cant move while frozen"));
    }

    @EventHandler
    public void move(PlayerInteractEvent e) {
        DataManager dataManager = CoreAPI.getInstance().getDataManager();
        Player player = e.getPlayer();
        if (!dataManager.isPlayer(player)) return;
        if (!dataManager.getPlayer(player).isFrozen()) return;

        e.setCancelled(true);
        e.getPlayer().sendMessage("You cant interact while frozen");
    }
}
