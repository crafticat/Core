package mc.apptoeat.com.core.core.webhook;

import net.md_5.bungee.api.ChatColor;

import java.awt.*;

public class Bot {
    private static String webhookURL = "https://discord.com/api/webhooks/1019214891216818177/3xHWiiDTibeYfeZL6rasCBuPo7b6EkUWyJf9n9CGobaPX2h0eNMnmE3w09lY1tQbcLIR";

    private static DiscordWebhook webhook = new DiscordWebhook(webhookURL);

    public static void sendReportMessage(String message, String reason, int c1, int c2, int c3) {
        if (reason == null) {
            webhook.addEmbed(new DiscordWebhook.EmbedObject().setTitle("Report").setDescription(message + ".").setColor(new Color(c1, c2, c3)));
        } else {
            webhook.addEmbed(new DiscordWebhook.EmbedObject().setTitle("Report").setDescription(message + ". Reason: " + reason).setColor(new Color(c1, c2, c3)));
        }
        try {
            webhook.execute();
            webhook.clearEmbeds();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
