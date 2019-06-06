package wrike;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import wrike.page.LoginPage;
import wrike.page.ResendPage;

import java.util.concurrent.TimeUnit;

public class MainTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void test() {
        driver.get("https://www.wrike.com/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnGetStarted();
        String login = Util.generateLogin();
        ResendPage resendPage = loginPage.writeEmail(login);
        resendPage.answerQuestions();
        resendPage.resendEmail();
        resendPage.checkTwitterLink();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
