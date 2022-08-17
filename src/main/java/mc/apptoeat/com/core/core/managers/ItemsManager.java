package mc.apptoeat.com.core.core.managers;

import mc.apptoeat.com.core.core.staff.items.FreezePlayerItem;
import mc.apptoeat.com.core.core.staff.items.PlayersItem;
import mc.apptoeat.com.core.core.staff.items.VanishItem;
import mc.apptoeat.com.core.utils.item.Item;
import mc.apptoeat.com.core.utils.manager.Manager;

public class ItemsManager extends Manager<Item> {

    public ItemsManager() {
        register(new PlayersItem());
        register(new VanishItem());
        register(new FreezePlayerItem());
    }
}
