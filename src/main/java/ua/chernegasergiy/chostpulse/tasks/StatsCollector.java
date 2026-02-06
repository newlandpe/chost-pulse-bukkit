package ua.chernegasergiy.chostpulse.tasks;
import org.bukkit.Bukkit;
import ua.chernegasergiy.chostpulse.Main;
import java.util.HashMap;
import java.util.Map;

public class StatsCollector {
    public static Map<String, Object> collect(Main plugin) {
        Map<String, Object> data = new HashMap<>();
        data.put("status", "online");
        data.put("players", Bukkit.getOnlinePlayers().size());
        data.put("maxPlayers", Bukkit.getMaxPlayers());
        try { data.put("tps", Math.round(Bukkit.getTPS()[0] * 100.0) / 100.0); } catch (Exception e) { data.put("tps", 20.0); }
        data.put("version", Bukkit.getBukkitVersion());
        data.put("heartbeatIntervalSec", plugin.getConfig().getInt("interval", 60));
        if (plugin.getConfig().getBoolean("send-software", true)) {
            data.put("software", Bukkit.getName() + " " + Bukkit.getVersion());
        }
        return data;
    }
}
