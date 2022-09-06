package mc.apptoeat.com.core.core.managers;


import mc.apptoeat.com.core.core.listeners.NameTagChanger;
import mc.apptoeat.com.core.utils.temp.TeamAction;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class TeamManager {

    private static Team team;
    private static Scoreboard scoreboard;

    public static void changePlayerTeam(Player player, String priority, TeamAction action) {
        if (player.getScoreboard() == null || action == null) {
            return;
        }

        for (Player player1 : Bukkit.getServer().getOnlinePlayers()) {
            NameTagChanger.changeNameTag(player1, player, "", "", TeamAction.DESTROY);
        }

        scoreboard = player.getScoreboard();

        if (scoreboard.getTeam(player.getName()) == null) {
            scoreboard.registerNewTeam(player.getName());
        }

        team = scoreboard.getTeam(player.getName());
        team.setDisplayName("" + priority);
        team.setPrefix("" + priority);
        team.setNameTagVisibility(NameTagVisibility.ALWAYS);

//        team.setPrefix(Color(prefix));
//        team.setSuffix(Color(suffix));
//        team.setNameTagVisibility(NameTagVisibility.ALWAYS);

        switch (action) {
            case CREATE:
                team.addPlayer(player);
                break;
            case UPDATE:
                team.unregister();
                scoreboard.registerNewTeam(player.getName());
                team = scoreboard.getTeam(player.getName());
                team.setPrefix("" + priority);
                team.setDisplayName("" + priority);
                team.setNameTagVisibility(NameTagVisibility.ALWAYS);
                team.addPlayer(player);
                break;
            case DESTROY:
                team.unregister();
                break;
        }
    }

    private static String Color(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }
}
