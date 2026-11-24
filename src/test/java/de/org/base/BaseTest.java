package de.org.base;

import de.org.ui.driverfactory.LocalDriver;
import de.org.ui.driverfactory.RemoteDriver;
import de.org.common.properties.PropertiesLoader;
import de.org.ui.steps.BaseSteps;
import de.org.ui.verifications.BaseVerifications;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static de.org.common.properties.Props.IS_REMOTE_RUN;

public abstract class BaseTest {

    private final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    private final ThreadLocal<BaseSteps> STEPS = new ThreadLocal<>();
    private final ThreadLocal<BaseVerifications> VERIFICATIONS = new ThreadLocal<>();

    protected WebDriver webDriver() {
        return DRIVER.get();
    }

    protected BaseSteps baseSteps() {
        return STEPS.get();
    }

    protected BaseVerifications baseVerifications() {
        return VERIFICATIONS.get();
    }

    @BeforeMethod
    public void runBeforeMethod() {
        if(IS_REMOTE_RUN) {
            DRIVER.set(new RemoteDriver().createDriver(PropertiesLoader.getProperty("browser")));
        } else {
            DRIVER.set(new LocalDriver().createDriver(PropertiesLoader.getProperty("browser")));
        }
        STEPS.set(new BaseSteps(DRIVER.get()));
        VERIFICATIONS.set(new BaseVerifications(DRIVER.get()));
    }

    @AfterMethod
    public void runAfterMethod() {
        try {
            webDriver().quit();
        } finally {
            DRIVER.remove();
            STEPS.remove();
            VERIFICATIONS.remove();
        }
    }
}
