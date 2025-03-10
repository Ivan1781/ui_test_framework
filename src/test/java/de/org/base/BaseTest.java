package de.org.base;

import de.org.steps.BaseSteps;
import de.org.verifications.BaseVerifications;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

@Log4j2
public abstract class BaseTest implements IConfiguration {

    protected WebDriver webDriver;
    protected BaseSteps baseSteps;
    protected BaseVerifications baseVerifications;

    @BeforeSuite
    public void runBeforeSuite() {
        // to change for reading the property
        webDriver = createDriver("chrome");
        baseSteps = new BaseSteps(webDriver);
        baseVerifications = new BaseVerifications(webDriver);
    }

    @BeforeClass
    public void runBeforeClass() {

    }

    @AfterMethod
    public void runAfterMethod() {
        webDriver.close();
    }

    @AfterClass
    public void runAfterClass() {

    }
}
