package de.org.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static de.org.properties.Props.IS_REMOTE_RUN;

public interface IConfiguration {

    default WebDriver createDriver(String browser) {
        WebDriver driver;

        if(IS_REMOTE_RUN) {
            ChromeOptions options =  new ChromeOptions();
            options.addArguments("start-maximized");
            options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS);
            options.setBrowserVersion("latest");
//            options.setCapability("enableVNC", true);
//            options.setCapability("enableVideo", true);
            URL url = null;
            try {
                url = new URI("http://localhost:4444/wd/hub").toURL();
            } catch (URISyntaxException | MalformedURLException e) {
                e.getMessage();
            }
            return new RemoteWebDriver(url, options);
        }

        driver = switch (browser.toLowerCase()) {
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
