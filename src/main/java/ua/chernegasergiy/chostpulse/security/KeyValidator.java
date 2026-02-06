package ua.chernegasergiy.chostpulse.security;
public class KeyValidator {
    private static final String PREFIX_SECRET = "sk_live_";
    private static final int MIN_LENGTH = 40;
    public static boolean isValidSecretToken(String token) {
        if (token == null || !token.startsWith(PREFIX_SECRET) || token.length() < MIN_LENGTH) return false;
        String uuidPart = token.substring(PREFIX_SECRET.length());
        return uuidPart.matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
    }
}
