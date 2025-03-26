package de.org.common.properties;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Properties;


public class PropertiesLoader {

    private final static Properties properties;
    private final static String SECRET_KEY = "1234567890123456";

    static {
        properties = loadProperties();
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream decryptedPropsStream = PropertiesLoader.class.getClassLoader().getResourceAsStream("test.properties")) {
            properties.load(decryptedPropsStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

    public static String getProperty(String key) {
        String prop;
        try {
            prop = decryptProperty(properties.getProperty(key));
        } catch (RuntimeException a) {
            prop = properties.getProperty(key);
        }
        return prop;
    }

    public static String decryptProperty(String data) {
        return handleProp(Cipher.DECRYPT_MODE, data);
    }

    public static String encryptProperty(String data) {
        return handleProp(Cipher.ENCRYPT_MODE, data);
    }

    private static String handleProp(int mode, String data) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            cipher.init(mode, keySpec);
            return mode == Cipher.ENCRYPT_MODE ? Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes())) :
                new String(cipher.doFinal(Base64.getDecoder().decode(data)));
        } catch (NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException |
                 NoSuchPaddingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
