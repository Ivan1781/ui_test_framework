package de.org.properties;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Properties;


public class PropertiesLoader {

    private Properties properties;
    private static String SECRET_KEY = "1234567890123456";

    public PropertiesLoader() {
        this.properties = loadProperties();
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

    public String getProperty(String key) {
        return decryptProperty(this.properties.getProperty(key));
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
