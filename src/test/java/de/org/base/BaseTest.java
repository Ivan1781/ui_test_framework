package de.org.base;

import de.org.driverfactory.LocalDriver;
import de.org.driverfactory.RemoteDriver;
import de.org.properties.PropertiesLoader;
import de.org.steps.BaseSteps;
import de.org.verifications.BaseVerifications;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import static de.org.properties.Props.IS_REMOTE_RUN;
@Log4j2
public abstract class BaseTest {

    protected WebDriver webDriver;
    protected BaseSteps baseSteps;
    protected BaseVerifications baseVerifications;

    @BeforeSuite
    public void runBeforeSuite() {
        if(IS_REMOTE_RUN) {
            webDriver = new RemoteDriver().createDriver(PropertiesLoader.getProperty("browser"));
        } else {
            webDriver = new LocalDriver().createDriver(PropertiesLoader.getProperty("browser"));
        }
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
