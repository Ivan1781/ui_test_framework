package de.org.client;

import de.org.properties.PropertiesLoader;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class UtilTests {

    @Test
    public void testPropEncrypt() {
        String encryptedProp = PropertiesLoader.encryptProperty("selenium");
        Assertions.assertThat(encryptedProp.equals("FvCGKfRTOYFtNgtEd/g7FQ==")).as("Property %s verification", encryptedProp).isTrue();
    }

    @Test
    public void testPropDecrypt() {
        String decryptedProp = PropertiesLoader.getProperty("prop");
        Assertions.assertThat(decryptedProp.equals("selenium")).isTrue();
    }

    @Test
    public void testProp() {
        String decryptedProp = PropertiesLoader.getProperty("prop2");
        Assertions.assertThat(decryptedProp.equals("prop")).isTrue();
    }
}
