package de.org.client;

import de.org.base.BaseTest;
import de.org.pages.login.LoginPage;
import de.org.steps.BaseSteps;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UiTest extends BaseTest {

    private LoginPage loginPage;
    private BaseSteps baseSteps;

    @BeforeClass
    public void precondition() {
        loginPage = new LoginPage(webDriver);
        baseSteps = new BaseSteps(webDriver);
    }

    @Test
    @Description()
    @Severity(SeverityLevel.CRITICAL)
    public void test() {
        baseSteps.login();
        loginPage.navigateTo("https://en.wikipedia.org/wiki/Morbid_Angel");
    }
}
