package de.org.ui.driverfactory;

import org.openqa.selenium.WebDriver;

public interface DriverFactoryMethod {
    WebDriver createDriver(String browser);
}
