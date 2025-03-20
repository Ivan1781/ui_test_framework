package de.org.driverfactory;

import org.openqa.selenium.WebDriver;

public interface DriverFactoryMethod {
    WebDriver createDriver(String browser);
}
