package mc.apptoeat.com.core.core.commands;

import mc.apptoeat.com.core.utils.commands.Command;
import mc.apptoeat.com.core.utils.message.Color;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.util.NumberConversions;

public class EnchantCommand extends Command {

    public EnchantCommand() {
        super("enchant", "enchants your item", "&fYou item has been enchanted.");
    }

    @Override
    public void executeCommand(Player player, String commandLabel, String[] args) {
        if (player.hasPermission("core.enchant")) {
            if (args.length <= 0) {
                player.sendMessage(Color.code("&7Please use &f/enchant [enchant] [level]"));
                return;
            }

            String enchantString = args[0];
            String levelString = "1";
            if (args.length > 1) levelString = args[1];

            Enchantment enchant = Enchantment.getByName(enchantString);
            if (enchant == null) {
                player.sendMessage(Color.code("&cEnchant was not found, name " + enchantString));
                return;
            }
            int level = NumberConversions.toInt(levelString);

            player.getInventory().getItemInHand().addEnchantment(enchant, level);
            player.sendMessage(Color.code("7You have enchanted your item to level &b" + level));
        } else {
            player.sendMessage(Color.code("&b&lAppToEat &8≫ &fUnknown command."));
        }
    }
}
