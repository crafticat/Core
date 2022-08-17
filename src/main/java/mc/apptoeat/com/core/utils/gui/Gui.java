package mc.apptoeat.com.core.utils.gui;

import lombok.Getter;
import lombok.Setter;
import mc.apptoeat.com.core.CoreAPI;
import mc.apptoeat.com.core.utils.message.Color;
import mc.apptoeat.com.core.utils.runnable.PlayerRunnable;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class Gui implements Listener {
    private final Inventory gui;
    private final int size;
    private HashMap<ItemStack, PlayerRunnable> clickAction;
    private HashMap<ItemStack,PlayerRunnable> rightClickAction;
    private HashMap<ItemStack,PlayerRunnable> shiftAction = new HashMap<>();
    private HashMap<ItemStack,PlayerRunnable> shiftRightAction = new HashMap<>();

    public Gui(String title,int size) {
        gui = Bukkit.createInventory(null, size, Color.code(title));
        this.size = size;

        clickAction = new HashMap<>();
        rightClickAction = new HashMap<>();
        Bukkit.getServer().getPluginManager().registerEvents(this, CoreAPI.getPlugin());
    }

    public void reset() {
        clickAction = new HashMap<>();
        gui.clear();
    }

    @EventHandler
    public void click(InventoryClickEvent e) throws IOException {
        Player player = (Player) e.getWhoClicked();
        if (e.getClickedInventory() == null) return;
        if (!(e.getClickedInventory().equals(gui))) return;
        e.setCancelled(true);

        if (!e.isShiftClick()) {
            if (e.isLeftClick()) {
                ItemStack stack = e.getCurrentItem();
                if (stack == null) return;
                PlayerRunnable runnable = clickAction.get(stack);
                if (runnable == null) return;

                runnable.run(player);
            }

            if (e.isRightClick()) {
                ItemStack stack = e.getCurrentItem();
                if (stack == null) return;
                PlayerRunnable runnable = rightClickAction.get(stack);
                if (runnable == null) return;

                runnable.run(player);
            }
        } else {
            if (e.isLeftClick()) {
                ItemStack stack = e.getCurrentItem();
                if (stack == null) return;
                PlayerRunnable runnable = shiftAction.get(stack);
                if (runnable == null) return;

                runnable.run(player);
            }

            if (e.isRightClick()) {
                ItemStack stack = e.getCurrentItem();
                if (stack == null) return;
                PlayerRunnable runnable = shiftRightAction.get(stack);
                if (runnable == null) return;

                runnable.run(player);
            }
        }
    }

    public void openInventory(final HumanEntity ent) {
        ent.openInventory(gui);
    }

    public void createGuiItem(final Material material, int slot, final String name, String color, PlayerRunnable action, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(Color.code(color + name));

        List<String> newLore = new ArrayList<>();
        for (String s : lore) {
            newLore.add(Color.code(s));
        }

        // Set the lore of the item
        meta.setLore((newLore));

        item.setItemMeta(meta);

        clickAction.put(item,action);

        gui.setItem(slot,item);
    }

    public void createGuiItem(final Material material, int slot, final String name,String color, PlayerRunnable action, final List<String > lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(Color.code(color + name));

        List<String> newLore = new ArrayList<>();
        for (String s : lore) {
            newLore.add(Color.code(s));
        }

        // Set the lore of the item
        meta.setLore((newLore));

        item.setItemMeta(meta);

        clickAction.put(item,action);

        gui.setItem(slot,item);
    }

    public void createGuiItem(final ItemStack stack,int slot, final String name, PlayerRunnable action, final List<String> lore) {
        final ItemStack item = new ItemStack(stack.getType(), stack.getAmount());
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(Color.code(name));

        List<String> newLore = new ArrayList<>();
        for (String s : lore) {
            newLore.add(Color.code(s));
        }

        // Set the lore of the item
        meta.setLore((newLore));

        item.setItemMeta(meta);

        clickAction.put(item,action);

        gui.setItem(slot,item);
    }

    public void createGuiItem(final ItemStack stack,int slot, final String name, PlayerRunnable action,PlayerRunnable rightAction,PlayerRunnable shiftAction,PlayerRunnable shiftRightAction, final List<String> lore) {
        final ItemStack item = new ItemStack(stack.getType(), stack.getAmount());
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(Color.code(name));

        List<String> newLore = new ArrayList<>();
        for (String s : lore) {
            newLore.add(Color.code(s));
        }

        // Set the lore of the item
        meta.setLore((newLore));

        item.setItemMeta(meta);

        clickAction.put(item,action);
        rightClickAction.put(item,rightAction);
        this.shiftAction.put(item,shiftAction);
        this.shiftRightAction.put(item,shiftRightAction);

        gui.setItem(slot,item);
    }

    public void createGuiItem(final ItemStack item,int slot, final String name, PlayerRunnable action, final String... lore) {
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(Color.code(name));

        List<String> newLore = new ArrayList<>();
        for (String s : lore) {
            newLore.add(Color.code(s));
        }

        // Set the lore of the item
        meta.setLore((newLore));

        item.setItemMeta(meta);

        clickAction.put(item,action);

        gui.setItem(slot,item);
    }

    public void createGuiItem(final Material material, int amount, Byte id, int slot, final String name, PlayerRunnable action, final String... lore) {
        final ItemStack item = new ItemStack(material, amount,id);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(Color.code(name));

        List<String> newLore = new ArrayList<>();
        for (String s : lore) {
            newLore.add(Color.code(s));
        }

        // Set the lore of the item
        meta.setLore((newLore));

        item.setItemMeta(meta);

        clickAction.put(item,action);

        gui.setItem(slot,item);
    }

    public void createGuiItem(final ItemStack item, int slot) {
        gui.setItem(slot,item);
    }

    public void setBackGroundColor(int color) {
        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1,(short) color);
        for(int i = 0; i<size;i++) {
            if (gui.getItem(i) == null) {
                gui.setItem(i,glass);
            }
            if (gui.getItem(i) != null) {
                if (gui.getItem(i).getType() == Material.AIR) {
                    gui.setItem(i, glass);
                }
            }
        }
    }
}

