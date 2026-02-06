package ua.chernegasergiy.chostpulse.api;
public class BadgeUrlGenerator {
    private final String baseUrl;
    private final String publicId;
    public BadgeUrlGenerator(String baseUrl, String publicId) {
        this.baseUrl = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1) : baseUrl;
        this.publicId = publicId;
    }
    public String getStatusBadge() { return String.format("%s?id=%s&type=status", baseUrl, publicId); }
    public String getPlayersBadge() { return String.format("%s?id=%s&type=players", baseUrl, publicId); }
    public String getTpsBadge() { return String.format("%s?id=%s&type=tps", baseUrl, publicId); }
    public String getSoftwareBadge() { return String.format("%s?id=%s&type=software", baseUrl, publicId); }
    public String getVersionBadge() { return String.format("%s?id=%s&type=version", baseUrl, publicId); }
}
