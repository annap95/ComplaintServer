<<<<<<< HEAD:src/main/java/complaint/config/security/SecretGenerator.java
package complaint.config.security;

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
=======
package complaint.config.security;

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
>>>>>>> 1d870f4c01ae9426b090104f7d91ff74aa03ccfe:src/main/java/complaint/config/security/SecretGenerator.java
