package de.org.ui.verifications;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BaseVerifications {

    protected WebDriver webDriver;

    public BaseVerifications(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void verifyElementIsVisible(WebElement element) {
        Assertions.assertThat(element.isDisplayed())
                .as("Element '%s' is visible", element)
                .isTrue();
    }

    public void verifyText(WebElement element, String expectedText) {
        Assertions.assertThat(element.getText())
                .as("Element '%s' contains text", element)
                .contains(expectedText);
    }
}
