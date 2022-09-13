package mc.apptoeat.com.core.core.webhook;

import net.md_5.bungee.api.ChatColor;

import java.awt.*;

public class Bot {
    private static String webhookURL = "https://discord.com/api/webhooks/1019242162371698709/Df8BRfffQMHEq22RUOqmwMDwdKTFvthbdFqBOXqjhTWIRoKxCw5UeZN2_9bSjaG8ddit";

    private static DiscordWebhook webhook = new DiscordWebhook(webhookURL);

    public static void sendReportMessage(String message, String reason, String server, int c1, int c2, int c3) {
        if (reason == null) {
            webhook.addEmbed(new DiscordWebhook.EmbedObject().setTitle("Report").setDescription(message + " on server " + server + ".").setColor(new Color(c1, c2, c3)));
        } else {
            webhook.addEmbed(new DiscordWebhook.EmbedObject().setTitle("Report").setDescription(message + " on server " + server + ". Reason: " + reason).setColor(new Color(c1, c2, c3)));
        }
        try {
            webhook.execute();
            webhook.clearEmbeds();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
