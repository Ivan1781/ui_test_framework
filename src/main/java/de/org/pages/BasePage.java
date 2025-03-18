package de.org.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected final By elem1 = By.xpath("//*[@...='....']");
    protected final By elem2 = By.xpath("//*[@...='....']");
    protected final By elem3 = By.xpath("//*[@...='....']");
    protected final By elem4 = By.xpath("//*[@...='....']");

    protected WebDriver webDriver;
    protected WebDriverWait wait;
    protected Actions actions;
    JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        Actions actions = new Actions(this.webDriver);
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

    public void hoverToTheElement(By element) {
       WebElement webElement = find(element);
       actions.moveToElement(webElement).perform();
    }

    public void selectByTextInDropdown(By element, String text) {
        WebElement dropdownElement = find(element);
        Select select = new Select(dropdownElement);
        select.selectByValue(text);
    }

    public void scrollToElement(By element) {
        WebElement webElement = find(element);
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

}
