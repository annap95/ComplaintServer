package complaint.security;

import java.security.SecureRandom;

public class SecretGenerator {
    public static final int SECRET_LENGTH = 32;

    public static String getNewSecret() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] secret = new byte[SECRET_LENGTH];
        secureRandom.nextBytes(secret);
        return new String(secret);
    }
}
