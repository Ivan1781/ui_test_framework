package de.org.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public interface IConfiguration {

    default WebDriver createDriver(String browser) {
        WebDriver driver;

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(setupChromeOptions());
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(setupFirefoxOptions());
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        return driver;
    }

    private static ChromeOptions setupChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("latest");
        // Default 30000
        options.setScriptTimeout(Duration.of(35, ChronoUnit.SECONDS));
        // Default 300000
        options.setPageLoadTimeout(Duration.of(35, ChronoUnit.SECONDS));
        // Default 0
        options.setImplicitWaitTimeout(Duration.of(2, ChronoUnit.SECONDS));
        // Default dismiss and notify state
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS);
        return options;
    }

    private static FirefoxOptions setupFirefoxOptions() {return new FirefoxOptions();}
}
