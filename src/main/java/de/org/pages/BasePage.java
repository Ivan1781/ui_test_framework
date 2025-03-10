package de.org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected final By elem1 = By.xpath("//*[@...='....']");
    protected final By elem2 = By.xpath("//*[@...='....']");
    protected final By elem3 = By.xpath("//*[@...='....']");
    protected final By elem4 = By.xpath("//*[@...='....']");

    protected WebDriver webDriver;
    protected WebDriverWait wait;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
    }

    public void navigateTo(String url) {
        webDriver.get(url);
    }

    public void closeBrowser() {
        webDriver.close();
    }

    public void waitForVisibility(By element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void waitForClickable(By element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

//    public boolean isPresent(By element) {
//
//    }

    public void type(By locator, String text) {
        waitForVisibility(locator);
        WebElement webElement = webDriver.findElement(locator);
        webElement.clear();
        webElement.sendKeys(text);
    }


    public WebElement find(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement find2(By locator) {
        wait.withTimeout(Duration.ofSeconds(3))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(ElementNotInteractableException.class);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
