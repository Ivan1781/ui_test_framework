package de.org.driverFactory;

import org.openqa.selenium.WebDriver;

public interface DriverFactoryMethod {
    WebDriver createDriver(String browser);
}
