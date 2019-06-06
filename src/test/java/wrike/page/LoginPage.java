package wrike.page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private final By GET_STARTED = By.xpath(".//*[@class='wg-header__desktop']//*[@class='wg-header__free-trial-button wg-btn wg-btn--green']");
    private final By WRITE_EMAIL = By.xpath(".//*[@class='modal-form-trial__label']//*[@name='email']");
    private final By CREATE_ACCOUNT = By.xpath(".//*[@class='modal-form-trial__label']//*[@type='submit']");

    public void check(WebDriver driver) {
        // Проверка загрузки страницы
        Assert.assertTrue("Не дождались кнопки Get Started For Free",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(GET_STARTED),
                        10,
                        500));
    }

    public LoginPage(WebDriver driver) {
        super.driver = driver;
        check(driver);
    }

    public LoginPage clickOnGetStarted() {
        // Нажимаем на кнопку «Начать бесплатно» рядом с кнопкой «Войти»
        Assert.assertTrue(
                "Не удалось найти Get Started For Free",
                isElementPresent(GET_STARTED));
        driver.findElement(GET_STARTED).click();
        return this;
    }

    public ResendPage writeEmail(String email) {
        // Вводим емейл, который нам передали
        Assert.assertTrue(
                "Не удалось найти Enter you email",
                isElementPresent(WRITE_EMAIL));
        driver.findElement(WRITE_EMAIL).clear();
        driver.findElement(WRITE_EMAIL).sendKeys(email);
        // Нажимаем кнопку «Создать мою учетную запись Wrike»
        Assert.assertTrue(
                "Не удалось найти Enter you email",
                isElementPresent(CREATE_ACCOUNT));
        driver.findElement(CREATE_ACCOUNT).click();
        // Переходим на новую страницу
        return new ResendPage(driver);
    }

}
