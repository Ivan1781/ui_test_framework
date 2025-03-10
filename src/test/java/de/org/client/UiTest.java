package de.org.client;

import de.org.base.BaseTest;
import de.org.pages.login.LoginPage;
import de.org.properties.PropertiesLoader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UiTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeClass
    public void precondition() {
        loginPage = new LoginPage(webDriver);
    }

    @Test
    public void test() {
        loginPage.navigateTo("https://en.wikipedia.org/wiki/Morbid_Angel");
    }

    @Test
    public void testProps() {
        PropertiesLoader a = new PropertiesLoader();
        a.getProperties().forEach((x,y)-> System.out.println(x + " " + y));
    }
}
