package de.org.steps;

import org.openqa.selenium.WebDriver;

public class BaseSteps {

    protected WebDriver webDriver;

    public BaseSteps(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void login() {

    }
}
