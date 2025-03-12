package de.org.client;

import de.org.properties.PropertiesLoader;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class UtilTests {

    @Test
    public void testPropsEncrypt() {
        String encryptedProp = PropertiesLoader.encryptProperty("selenium");
        Assertions.assertThat(encryptedProp.equals("FvCGKfRTOYFtNgtEd/g7FQ==")).as("Property %s verification", encryptedProp).isTrue();
    }

    @Test
    public void testPropsDecrypt() {
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        String decryptedProp = propertiesLoader.getProperty("prop");
        Assertions.assertThat(decryptedProp.equals("selenium")).isTrue();
    }
}
