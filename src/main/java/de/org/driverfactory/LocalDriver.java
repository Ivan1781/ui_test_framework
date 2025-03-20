package de.org.driverfactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class LocalDriver implements DriverFactoryMethod {
    @Override
    public WebDriver createDriver(String browser) {
        return switch (browser.toLowerCase()) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                yield new ChromeDriver(setupChromeOptions());
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                yield new FirefoxDriver(setupFirefoxOptions());
            }
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
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
