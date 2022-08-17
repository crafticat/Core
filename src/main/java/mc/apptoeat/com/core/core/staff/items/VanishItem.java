package mc.apptoeat.com.core.core.staff.items;

import mc.apptoeat.com.core.CoreAPI;
import mc.apptoeat.com.core.core.managers.DataManager;
import mc.apptoeat.com.core.core.staff.StaffPlayer;
import mc.apptoeat.com.core.utils.item.Item;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;

public class VanishItem extends Item {
    public VanishItem() {
        super(new ItemStack(Material.INK_SACK), 8);
        changeItemName("&8&lVanish &7(Right click)");
    }

    @Override
    public void rightClick(Player player) {
        DataManager dataManager = CoreAPI.getInstance().getDataManager();
        if (!dataManager.isStaffPlayer(player)) return;
        StaffPlayer staffPlayer = dataManager.getStaffPlayer(player);

        staffPlayer.toggleVanish();
    }
}
