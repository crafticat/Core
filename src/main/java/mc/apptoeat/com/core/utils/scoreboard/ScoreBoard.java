package mc.apptoeat.com.core.utils.scoreboard;

import mc.apptoeat.com.core.CoreAPI;
import mc.apptoeat.com.core.utils.ability.Ability;
import mc.apptoeat.com.core.utils.message.Color;
import mc.apptoeat.com.core.utils.runnable.StringFromPlayerRunnable;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.*;

import java.util.List;

public class ScoreBoard extends Ability {

    private final int updateTicks;

    public ScoreBoard(int updateTicks) {
        super();
        this.updateTicks = updateTicks;
    }

    private final ScoreboardManager manager = Bukkit.getScoreboardManager();
    private final Scoreboard board = manager.getNewScoreboard();
    private final Objective objective = board.registerNewObjective("test", "dummy");

    private String title;
    private StringFromPlayerRunnable stringFromPlayerRunnable;

    public void createBoard(String str, StringFromPlayerRunnable playerRunnable) {
        title = str;
        this.stringFromPlayerRunnable = playerRunnable;
    }

    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(CoreAPI.getPlugin(), new Runnable() {
            public void run() {
                List<String> objects = stringFromPlayerRunnable.run(p);
                if (title != null || objects != null) {

                    ScoreboardManager manager = Bukkit.getScoreboardManager();
                    final Scoreboard board = manager.getNewScoreboard();
                    final Objective objective = board.registerNewObjective("test", "dummy");
                    objective.setDisplaySlot(DisplaySlot.BELOW_NAME.SIDEBAR);
                    objective.setDisplayName(Color.code(title));
                    int index = 0;
                    for (int i = objects.size() - 1; i >= 0; i--) {
                        Score score = objective.getScore(Color.code(objects.get(i)));
                        score.setScore(index);
                        index++;
                    }
                    p.setScoreboard(board);
                }
            }
        },0, updateTicks);
    }

}
