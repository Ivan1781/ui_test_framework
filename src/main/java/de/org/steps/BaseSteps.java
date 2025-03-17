package de.org.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class BaseSteps {

    protected WebDriver webDriver;

    public BaseSteps(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step
    public void login() {

    }
}
