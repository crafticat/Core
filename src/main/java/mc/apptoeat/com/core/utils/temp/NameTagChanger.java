package mc.apptoeat.com.core.utils.temp;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class NameTagChanger {

    private static Team team;
    private static Scoreboard scoreboard;

    public static void changePlayerName(Player player, String prefix, TeamAction action) {
        if (player.getScoreboard() == null || prefix == null || action == null) {
            return;
        }

        scoreboard = player.getScoreboard();

        if (scoreboard.getTeam(player.getName()) == null) {
            scoreboard.registerNewTeam(player.getName());
        }

        team = scoreboard.getTeam(player.getName());
        team.setPrefix(prefix);
        team.setNameTagVisibility(NameTagVisibility.ALWAYS);

        switch (action) {
            case CREATE:
                team.addPlayer(player);
                break;
            case UPDATE:
                team.unregister();
                scoreboard.registerNewTeam(player.getName());
                team = scoreboard.getTeam(player.getName());
                team.setPrefix(prefix);
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