package de.org.client;

import de.org.base.BaseTest;
import de.org.ui.pages.login.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UiTest extends BaseTest {

    private final ThreadLocal<LoginPage> LOGIN_PAGE = new ThreadLocal<>();

    @BeforeMethod
    public void precondition() {
        LOGIN_PAGE.set(new LoginPage(webDriver()));
    }

    @AfterMethod
    public void runAfter() {
        LOGIN_PAGE.remove();
    }

    public LoginPage loginPage() {
        return LOGIN_PAGE.get();
    }

    @Test
    @Description()
    @Severity(SeverityLevel.CRITICAL)
    public void test() {
        baseSteps().login();
        loginPage().navigateTo("https://en.wikipedia.org/wiki/Morbid_Angel");
    }
}
