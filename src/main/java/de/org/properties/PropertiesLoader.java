package de.org.properties;

import lombok.Getter;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertiesLoader {

    private Properties properties = loadProperties();

    private static Properties loadProperties() {
        Properties decripted = new Properties();
        try (InputStream testProps = PropertiesLoader.class.getClassLoader().getResourceAsStream("test.properties")) {
            if (testProps == null) {
                throw new RuntimeException("The file test.properties was not found in the classpath");
            }
            decripted.putAll(loadDecryptedProperties("key", testProps));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        decripted.putAll(System.getProperties());

        return decripted;
    }

    static Properties loadDecryptedProperties(String key, InputStream testProps) throws IOException {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(key);
        encryptor.setAlgorithm("PBEWithMD5AndDES");

        Properties props = new EncryptableProperties(encryptor);

        props.load(testProps);

        return props;
    }

    public Properties getProperties() {
        return this.properties;
    }
}
