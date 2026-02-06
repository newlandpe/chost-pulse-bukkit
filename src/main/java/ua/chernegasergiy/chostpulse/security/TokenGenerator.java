package ua.chernegasergiy.chostpulse.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.UUID;

public class TokenGenerator {
    private static final String PREFIX_SECRET = "sk_live_";
    private static final String PREFIX_PUBLIC = "srv_pub_";
    public String generateSecretKey() { return PREFIX_SECRET + UUID.randomUUID().toString(); }
    public String derivePublicId(String secretToken) {
        try {
            String cleanToken = secretToken.replace(PREFIX_SECRET, "");
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(cleanToken.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return PREFIX_PUBLIC + hexString.substring(0, 12);
        } catch (Exception e) { return PREFIX_PUBLIC + "error"; }
    }
}
