package ua.chernegasergiy.chostpulse.api;

public record HeartbeatClient(String url) {
    public String getUrl() { return url; }
}
