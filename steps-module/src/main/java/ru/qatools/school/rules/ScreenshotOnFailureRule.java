package ru.qatools.school.rules;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Attachment;

/**
 * @author gladnik (Nikolai Gladkov)
 */
public class ScreenshotOnFailureRule extends TestWatcher {

    private WebDriver driver;

    public ScreenshotOnFailureRule(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    protected void failed(Throwable e, Description description) {
        makeScreenshotOnFailure();
    }

    @Attachment(type = "image/png")
    private byte[] makeScreenshotOnFailure() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
