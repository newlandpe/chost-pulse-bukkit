package ua.chernegasergiy.chostpulse;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import ua.chernegasergiy.chostpulse.api.BadgeUrlGenerator;
import ua.chernegasergiy.chostpulse.api.HeartbeatClient;
import ua.chernegasergiy.chostpulse.security.KeyValidator;
import ua.chernegasergiy.chostpulse.security.TokenGenerator;
import ua.chernegasergiy.chostpulse.tasks.HeartbeatTask;
import ua.chernegasergiy.chostpulse.tasks.StatsCollector;

import java.util.Map;

public class Main extends JavaPlugin {

    private String secretToken;
    private String publicId;
    private HeartbeatClient client;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        String configToken = getConfig().getString("token", "");
        if (configToken.isEmpty() || !KeyValidator.isValidSecretToken(configToken)) {
            initializeTokens();
        } else {
            loadTokens(configToken);
        }

        String apiUrl = getConfig().getString("api_url", "https://your-domain.com/api/heartbeat");
        this.client = new HeartbeatClient(apiUrl);

        scheduleHeartbeat();
        displayBadgeUrls();

        getLogger().info("ChostPulse enabled successfully!");
    }

    private void initializeTokens() {
        TokenGenerator generator = new TokenGenerator();
        this.secretToken = generator.generateSecretKey();
        this.publicId = generator.derivePublicId(this.secretToken);

        getConfig().set("token", this.secretToken);
        saveConfig();

        getLogger().info("Generated new secret token: " + this.secretToken);
        getLogger().info("Public ID: " + this.publicId);
    }

    private void loadTokens(String token) {
        this.secretToken = token;
        this.publicId = new TokenGenerator().derivePublicId(token);
        if (getConfig().getBoolean("debug", false)) {
            getLogger().info("Loaded token. Public ID: " + this.publicId);
        }
    }

    private void scheduleHeartbeat() {
        long interval = getConfig().getLong("interval", 60) * 20L;

        new BukkitRunnable() {
            @Override
            public void run() {
                sendHeartbeat();
            }
        }.runTaskTimerAsynchronously(this, 100L, interval);
    }

    private void sendHeartbeat() {
        Map<String, Object> data = StatsCollector.collect(this);
        new HeartbeatTask(client.getUrl(), this.secretToken, data, getConfig().getBoolean("debug", false)).run();
    }

    private void displayBadgeUrls() {
        String baseUrl = "https://your-domain.com/api/badge";
        BadgeUrlGenerator generator = new BadgeUrlGenerator(baseUrl, this.publicId);

        getLogger().info("Your Badge URLs:");
        getLogger().info("Status:   " + generator.getStatusBadge());
        getLogger().info("Players:  " + generator.getPlayersBadge());
        getLogger().info("TPS:      " + generator.getTpsBadge());
        getLogger().info("Software: " + generator.getSoftwareBadge());
        getLogger().info("Version:  " + generator.getVersionBadge());
    }

    @Override
    public void onDisable() {
        getLogger().info("ChostPulse disabled.");
    }
}
