package mc.apptoeat.com.core.utils.tags;

import org.bukkit.inventory.ItemStack;

public class Tag {
    private String name = "";
    private String subfix = "";

    private ItemStack item;

    public Tag(String name, String subfix, ItemStack item) {
        this.name = name;
        this.subfix = subfix;
        this.item = item;
    }

    public String getName() {
        return name;
    }
    public String getSubfix() {
        return subfix;
    }
    public ItemStack getItem() {
        return item;
    }
}
