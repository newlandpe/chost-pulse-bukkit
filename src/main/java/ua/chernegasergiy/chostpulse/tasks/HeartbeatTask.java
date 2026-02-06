package ua.chernegasergiy.chostpulse.tasks;

import com.google.gson.Gson;
import org.bukkit.Bukkit;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HeartbeatTask implements Runnable {
    private final String apiUrl;
    private final String token;
    private final Map<String, Object> data;
    private final boolean debug;

    public HeartbeatTask(String url, String token, Map<String, Object> data, boolean debug) {
        this.apiUrl = url; this.token = token; this.data = data; this.debug = debug;
    }

    @Override
    public void run() {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(apiUrl).openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            con.setConnectTimeout(5000);
            Map<String, Object> payload = new HashMap<>();
            payload.put("token", token);
            payload.put("data", data);
            try (OutputStream os = con.getOutputStream()) { os.write(new Gson().toJson(payload).getBytes(StandardCharsets.UTF_8)); }
            int code = con.getResponseCode();
            if (debug) Bukkit.getLogger().info("ChostPulse: Sent. Code: " + code);
            con.disconnect();
        } catch (Exception e) { Bukkit.getLogger().warning("ChostPulse failure: " + e.getMessage()); }
    }
}
