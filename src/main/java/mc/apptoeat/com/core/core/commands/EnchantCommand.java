package mc.apptoeat.com.core.core.commands;

import mc.apptoeat.com.core.utils.commands.Command;
import mc.apptoeat.com.core.utils.message.Color;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.util.NumberConversions;

public class EnchantCommand extends Command {

    public EnchantCommand() {
        super("enchant", "enchants your item", "&fYour item has been enchanted.");
    }

    @Override
    public void executeCommand(Player player, String commandLabel, String[] args) {
        if (player.hasPermission("core.enchant")) {
            if (args.length <= 0) {
                player.sendMessage(Color.code("&7Please use &f/enchant [enchant id] [level]"));
                return;
            }

            int enchantId = Integer.parseInt(args[0]);
            String levelString = "1";
            if (args.length > 1) levelString = args[1];

            Enchantment enchant = Enchantment.getById(enchantId);
            if (enchant == null) {
                player.sendMessage(Color.code("&cEnchant was not found, id: " + enchantId));
                return;
            }
            int level = Integer.parseInt(levelString);

            player.getInventory().getItemInHand().addUnsafeEnchantment(enchant, level);
            player.sendMessage(Color.code("7You have enchanted your item to level &b" + level));
        } else {
            player.sendMessage(Color.code("&b&lAppToEat &8≫ &fUnknown command."));
        }
    }
}
