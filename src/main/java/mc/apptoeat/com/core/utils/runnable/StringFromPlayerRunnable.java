package mc.apptoeat.com.core.utils.runnable;

import org.bukkit.entity.Player;

import java.util.List;

@FunctionalInterface
public interface StringFromPlayerRunnable {
    List<String> run(Player var1);
}
