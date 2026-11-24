package de.org.ui.driverfactory;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class RemoteDriver implements DriverFactoryMethod {

    @Override
    public WebDriver createDriver(String browser) {
        ChromeOptions options =  new ChromeOptions();
        options.addArguments("start-maximized");
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS);
        options.setBrowserVersion("latest");
//            options.setCapability("enableVNC", true);
//            options.setCapability("enableVideo", true);
        try {
            URL url = new URI("http://localhost:4444/wd/hub").toURL();
            return new RemoteWebDriver(url, options);
        } catch (URISyntaxException | MalformedURLException e) {
            throw new RuntimeException("Failed to create remote driver", e);
        }
    }
}
