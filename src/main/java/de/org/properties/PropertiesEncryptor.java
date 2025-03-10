package de.org.properties;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class PropertiesEncryptor {

    static void encryptProperties(String key) {
        StandardPBEStringEncryptor textEncryptor = new StandardPBEStringEncryptor();
        textEncryptor.setPassword(key);
        textEncryptor.setAlgorithm("PBEWithMD5AndDES");
        String encryptedUsername = textEncryptor.encrypt("");
        System.out.println("Encrypted Username: " + encryptedUsername);
    }
}
