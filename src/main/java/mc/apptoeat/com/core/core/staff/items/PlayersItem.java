package mc.apptoeat.com.core.core.staff.items;

import mc.apptoeat.com.core.CoreAPI;
import mc.apptoeat.com.core.utils.config.FileManager;
import mc.apptoeat.com.core.utils.gui.GlobalGui;
import mc.apptoeat.com.core.utils.item.Item;
import mc.apptoeat.com.core.utils.message.Color;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class PlayersItem extends Item {
    private final GlobalGui teleportGui;
    private final String banCommand;

    public PlayersItem() {
        super(new ItemStack(Material.COMPASS), 0);
        banCommand = FileManager.getOrDefault(CoreAPI.getInstance().getConfigManager().getCommandConfig(), "actions.banCommand","forward console ban %player%");
        changeItemName("&b&lPlayers &7(Right click)");
        teleportGui = new GlobalGui("&BPlayers gui",9 * 6);
        loadTeleportGui();
    }

    public void loadActionGui(Player target,Player view) {
        GlobalGui actionGui = new GlobalGui("&fAction gui",9 * 3);

        actionGui.setGui(player -> {
            actionGui.reset();

            //Ban Player
            actionGui.createGuiItem(new ItemStack(Material.ANVIL),9 + 3,"&c&lBan " + target.getName(),executedPlayer -> {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),banCommand.replace("%player%",target.getName()));
                player.sendMessage(Color.code("&fYou have banned &b" + target.getName()));
            });

            //Teleport to player
            actionGui.createGuiItem(new ItemStack(Material.ANVIL),9 + 5,"fTeleport to &b&l" + target.getName(),executedPlayer -> {
                player.teleport(target.getLocation());
                player.sendMessage(Color.code("&fYou have been teleported to &b" + target.getName()));
            });

            actionGui.setBackGroundColor(15);
        });

        actionGui.minOpen(view);
    }

    public void loadTeleportGui() {
        teleportGui.setGui((player) -> {
            teleportGui.reset();
            int slot = 0;
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (slot > teleportGui.getSize() - 1) break;

                teleportGui.createGuiItem(getSkull(p),slot++, Color.code("&b" + p.getName()),actionPlayer -> {
                    loadActionGui(p,player);
                });
            }

            teleportGui.setBackGroundColor(15);
        });
    }

    public ItemStack getSkull(Player player) {
        ItemStack stack = new ItemStack(Material.SKULL_ITEM,1,(short) 3);
        SkullMeta meta = (SkullMeta) stack.getItemMeta();

        meta.setOwner(player.getName());
        stack.setItemMeta(meta);

        return stack;
    }

    @Override
    public void rightClick(Player player) {
        teleportGui.minOpen(player);
    }
}
