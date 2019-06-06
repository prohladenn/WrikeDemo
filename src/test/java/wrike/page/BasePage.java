package wrike.page;

import com.google.common.base.Preconditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public abstract class BasePage {

    public abstract void check(WebDriver driver);

    WebDriver driver;

    public boolean isElementPresent(By by) {
        try {
            this.driver.findElement(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean explicitWait(
            final ExpectedCondition<?> condition,
            long maxCheckTimeInSeconds,
            long millisecondsBetweenChecks) {
        Preconditions.checkNotNull(
                condition,
                "Condition must be not null");
        Preconditions.checkArgument(
                TimeUnit.MINUTES.toSeconds(3) > maxCheckTimeInSeconds,
                "Max check time in seconds should be less than 3 minutes");
        checkConditionTimeouts(
                maxCheckTimeInSeconds,
                millisecondsBetweenChecks);
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            WebDriverWait explicitWait = new WebDriverWait(driver, maxCheckTimeInSeconds, millisecondsBetweenChecks);
            explicitWait.until(condition);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            assert driver != null;
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }

    private void checkConditionTimeouts(long maxCheckTimeInSeconds, long millisecondsBetweenChecks) {
        Preconditions.checkState(
                maxCheckTimeInSeconds > 0,
                "maximum check time in seconds must be not 0");
        Preconditions.checkState(
                millisecondsBetweenChecks > 0,
                "milliseconds count between checks must be not 0");
        Preconditions.checkState(
                millisecondsBetweenChecks < (maxCheckTimeInSeconds * 1000),
                "Millis between checks must be less than max seconds to wait");
    }

}
