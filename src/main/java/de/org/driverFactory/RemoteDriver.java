package de.org.driverFactory;

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
        URL url = null;
        try {
            url = new URI("http://localhost:4444/wd/hub").toURL();
        } catch (URISyntaxException | MalformedURLException e) {
            e.getMessage();
        }
        return new RemoteWebDriver(url, options);
    }
}
