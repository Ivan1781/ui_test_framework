package de.org.client;

import de.org.base.BaseTest;
import de.org.ui.pages.login.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UiTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeClass
    public void precondition() {
        loginPage = new LoginPage(webDriver);
    }

    @Test
    @Description()
    @Severity(SeverityLevel.CRITICAL)
    public void test() {
        baseSteps.login();
        loginPage.navigateTo("https://en.wikipedia.org/wiki/Morbid_Angel");
    }
}
