package mc.apptoeat.com.core.utils.tags;

import mc.apptoeat.com.core.utils.config.CustomConfig;
import mc.apptoeat.com.core.utils.message.Color;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import javax.security.auth.login.Configuration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TagUtil {
    private static CustomConfig config = new CustomConfig("tags");
    private static CustomConfig savedTags = new CustomConfig("savedTags");
    private static List<Tag> totalTags = new ArrayList<>();

    public static void loadSavedTags() {
        for (String name : (List<String>) savedTags.getConfig().getList("tags")) {
            Tag tag = new Tag(name, savedTags.getConfig().getString("tags." + name + ".subfix"), savedTags.getConfig().getItemStack("tags." + name + ".item"));
            addTag(tag);
        }
    }

    public static void addTag(Tag tag) {
        totalTags.add(tag);
        savedTags.getConfig().set("tags." + tag.getName() + ".subfix", tag.getSubfix());
        savedTags.getConfig().set("tags." + tag.getName() + ".item", tag.getItem());
        savedTags.saveConfig();
    }

    public static void removeTag(Tag tag) {
        totalTags.remove(tag);
    }

    public static List<Tag> getTotalTags() {
        return totalTags;
    }

    public static List<String> getOwnTags(Player player) {
        return (List<String>) config.getConfig().getList("players." + player.getUniqueId() + ".ownTags");
    }
    public static Tag getByName(String name) {
        Tag tag = null;
        for (Tag t : totalTags) {
            if (Objects.equals(t.getName(), name)) tag = t;
        }
        return tag;
    }
    public static Tag getUsedTag(Player player) {
        return getByName((String) config.getConfig().get("players." + player.getUniqueId() + ".usedTag"));
    }
    public static void giveTag(Player player, Tag tag) {
        List<String > tags = new ArrayList<>();
        if (config.getConfig().getList("players." + player.getUniqueId() + ".ownTags") != null || !config.getConfig().getList("players." + player.getUniqueId() + ".ownTags").isEmpty()) {
            tags = (List<String>) config.getConfig().getList("players." + player.getUniqueId() + ".ownTags");
        }
        tags.add(tag.getName());
        config.getConfig().set("players." + player.getUniqueId() + ".ownTags", tags);
        config.saveConfig();
    }
    public static void removeTag(Player player, Tag tag) {
        List<String> tags = (List<String>) config.getConfig().getList("players." + player.getUniqueId() + ".ownTags");
        tags.remove(tag.getName());
        config.getConfig().set("players." + player.getUniqueId() + ".ownTags", tags);
        config.saveConfig();
    }
    public static void useTag(Player player, Tag tag) {
        if (config.getConfig().getList("players." + player.getName() + ".ownTags").contains(tag.getName())) {
            config.getConfig().set("players." + player.getName() + ".usedTag", tag.getName());
            player.sendMessage(Color.code("&aYour tag changed to " + tag.getName()));
            config.saveConfig();
        } else {
            player.sendMessage(Color.code("&cYou don't have " + tag.getName() + " &atag"));
        }
    }
    public static void unuseTag(Player player) {
        config.getConfig().set("players." + player.getName() + ".usedTag", null);
        player.sendMessage(Color.code("&aYou disabled your tag"));
        config.saveConfig();
    }
}
