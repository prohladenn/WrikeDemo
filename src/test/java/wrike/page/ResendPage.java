package wrike.page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ResendPage extends BasePage {

    private final By SURVEY = By.xpath(".//*[@class='survey']");
    private final By ANS_1_1 = By.xpath(".//*[@data-code='interest_in_solution']//*[contains(text(),'Very interested')]");
    private final By ANS_1_2 = By.xpath(".//*[@data-code='interest_in_solution']//*[contains(text(),'Just looking')]");
    private final By ANS_2_1 = By.xpath(".//*[@data-code='team_members']//*[contains(text(),'1–5')]");
    private final By ANS_2_2 = By.xpath(".//*[@data-code='team_members']//*[contains(text(),'6–15')]");
    private final By ANS_2_3 = By.xpath(".//*[@data-code='team_members']//*[contains(text(),'16–25')]");
    private final By ANS_2_4 = By.xpath(".//*[@data-code='team_members']//*[contains(text(),'26–50')]");
    private final By ANS_2_5 = By.xpath(".//*[@data-code='team_members']//*[contains(text(),'50+')]");
    private final By ANS_3_1 = By.xpath(".//*[@data-code='primary_business']//*[contains(text(),'Yes')]");
    private final By ANS_3_2 = By.xpath(".//*[@data-code='primary_business']//*[contains(text(),'No')]");
    private final By ANS_3_3 = By.xpath(".//*[@data-code='primary_business']//*[contains(text(),'Other')]");
    private final By SUBMIT = By.xpath(".//*[@class='submit wg-btn wg-btn--navy js-survey-submit']");
    private final By SHOW_THANKS = By.xpath(".//*[@class='survey-success'][@style='display: block;']");

    private final By SENT_EMAIL_TEXT = By.xpath(".//*[@class='h4 subtitle']");
    private final By SENT_EMAIL_TEXT_AGAIN = By.xpath(".//*[@class='section section-resend-main section-resend-main-va " +
            "section-resend-main--survey section-resend-main--again']");
    private final By RESEND_EMAIL = By.xpath(".//*[@class='wg-btn wg-btn--white wg-btn--hollow button js-button']");

    private final By FOLLOW_US = By.xpath(".//*[@class='wg-footer__social-block']");
    private final By TWITTER = By.xpath(".//*[@class='wg-footer__social-item']//*[contains(@href,'twitter')]");

    public void check(WebDriver driver) {
        // Проверка загрузки страницы
        Assert.assertTrue("Не дождались раздела с вопросами",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(SURVEY),
                        10,
                        500));
        Assert.assertTrue("Не дождались раздела с текстом об отправке",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(SENT_EMAIL_TEXT),
                        10,
                        500));
        Assert.assertTrue("Не дождались блока FOLLOW US",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(FOLLOW_US),
                        10,
                        500));
    }

    public ResendPage(WebDriver driver) {
        super.driver = driver;
        check(driver);
    }

    public void answerQuestions() {
        // Отвечаем случайно на первый вопрос
        switch ((int) (Math.random() * 2)) {
            case 0:
                Assert.assertTrue(
                        "Не удалось найти первый ответ на первый вопрос",
                        isElementPresent(ANS_1_1));
                driver.findElement(ANS_1_1).click();
                break;
            case 1:
                Assert.assertTrue(
                        "Не удалось найти второй ответ на первый вопрос",
                        isElementPresent(ANS_1_2));
                driver.findElement(ANS_1_2).click();
                break;
        }
        // Отвечаем случайно на второй вопрос
        switch ((int) (Math.random() * 5)) {
            case 0:
                Assert.assertTrue(
                        "Не удалось найти первый ответ на второй вопрос",
                        isElementPresent(ANS_2_1));
                driver.findElement(ANS_2_1).click();
                break;
            case 1:
                Assert.assertTrue(
                        "Не удалось найти второй ответ на второй вопрос",
                        isElementPresent(ANS_2_2));
                driver.findElement(ANS_2_2).click();
                break;
            case 2:
                Assert.assertTrue(
                        "Не удалось найти третий ответ на второй вопрос",
                        isElementPresent(ANS_2_3));
                driver.findElement(ANS_2_3).click();
                break;
            case 3:
                Assert.assertTrue(
                        "Не удалось найти четвёртый ответ на второй вопрос",
                        isElementPresent(ANS_2_4));
                driver.findElement(ANS_2_4).click();
                break;
            case 4:
                Assert.assertTrue(
                        "Не удалось найти пятый ответ на второй вопрос",
                        isElementPresent(ANS_2_5));
                driver.findElement(ANS_2_5).click();
                break;
        }
        // Отвечаем случайно на третий вопрос
        switch ((int) (Math.random() * 3)) {
            case 0:
                Assert.assertTrue(
                        "Не удалось найти первый ответ на третий вопрос",
                        isElementPresent(ANS_3_1));
                driver.findElement(ANS_3_1).click();
                break;
            case 1:
                Assert.assertTrue(
                        "Не удалось найти второй ответ на третий вопрос",
                        isElementPresent(ANS_3_2));
                driver.findElement(ANS_3_2).click();
                break;
            case 2:
                Assert.assertTrue(
                        "Не удалось найти третий ответ на третий вопрос",
                        isElementPresent(ANS_3_3));
                driver.findElement(ANS_3_3).click();
                break;
        }
        // Отправляем наши ответы
        Assert.assertTrue(
                "Не удалось найти submit",
                isElementPresent(SUBMIT));
        driver.findElement(SUBMIT).click();
        // Смотрим что видна благодарность за отправу
        Assert.assertTrue(
                "Благодарность не найдена или не видна",
                isElementPresent(SHOW_THANKS));
    }

    public void resendEmail() {
        // Отправляем повторно
        Assert.assertTrue(
                "Не удалось найти resend email",
                isElementPresent(RESEND_EMAIL));
        driver.findElement(RESEND_EMAIL).click();
        // Проверяем наличие слова AGAIN
        Assert.assertTrue(
                "Не удалось найти resend email",
                isElementPresent(SENT_EMAIL_TEXT_AGAIN));
    }

    public void checkTwitterLink() {
        // Проверяем наличие ссылки на твиттер
        Assert.assertTrue(
                "Не удалось найти twitter",
                isElementPresent(TWITTER));
        // Проверяем корректность ссылки
        Assert.assertEquals(
                "https://twitter.com/wrike",
                driver.findElement(TWITTER).getAttribute("href"));
    }

}
