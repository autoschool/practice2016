package ru.qatools.school.steps.websteps.util;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.concurrent.TimeUnit;

/**
 * @author gladnik (Nikolai Gladkov)
 */
public class BasicActionSteps {

    private static final int POLLING_INTERVAL = 50;

    @Step("Пытаемся кликнуть по [{0}] в течение {2} секунд(ы)")
    public static void safeClick(HtmlElement element, WebDriver driver, int timeOutInSeconds) {
        new WebDriverWait(driver, timeOutInSeconds)
                .pollingEvery(POLLING_INTERVAL, TimeUnit.MILLISECONDS)
                .until(
                        (WebDriver webDriver) -> {
                            try {
                                element.click();
                                return true;
                            } catch (WebDriverException exception) {
                                return false;
                            }
                        }
                );
    }

    @Step("Пытаемся стереть текст из «{0}» в течение {2} секунд(ы)")
    public static void safeClear(HtmlElement element, WebDriver driver, int timeOutInSeconds) {
        new WebDriverWait(driver, timeOutInSeconds)
                .pollingEvery(POLLING_INTERVAL, TimeUnit.MILLISECONDS)
                .until(
                        (WebDriver webDriver) -> {
                            try {
                                element.clear();
                                return true;
                            } catch (WebDriverException exception) {
                                return false;
                            }
                        }
                );
    }

    @Step("Пытаемся отправить элементу «{0}» '{1}' в течение {2} секунд(ы)")
    public static void safeSendKeys(HtmlElement element, CharSequence keys, WebDriver driver, int timeOutInSeconds) {
        new WebDriverWait(driver, timeOutInSeconds)
                .pollingEvery(POLLING_INTERVAL, TimeUnit.MILLISECONDS)
                .until(
                        (WebDriver webDriver) -> {
                            try {
                                element.sendKeys(keys);
                                return true;
                            } catch (WebDriverException exception) {
                                return false;
                            }
                        }
                );
    }

    @Step("Ждём пока элемент «{0}» не станет кликабельным, или {2} секунд")
    public static void waitElementToBeClickableBasic(HtmlElement element, WebDriver driver, long timeOutInSeconds) {
        (new WebDriverWait(driver, timeOutInSeconds))
                .pollingEvery(POLLING_INTERVAL, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    @Step("Ждём пока элемент «{0}» не устареет или {2} секунд")
    public static void waitElementToStaleBasic(HtmlElement element, WebDriver driver, long timeOutInSeconds) {
        try {
            (new WebDriverWait(driver, timeOutInSeconds))
                    .pollingEvery(POLLING_INTERVAL, TimeUnit.MILLISECONDS)
                    .until(ExpectedConditions.stalenessOf(element));
        } catch (TimeoutException timeoutException) {
        }
    }

    @Step("Переводим текст «{0}» в Double")
    public static double convertToDoubleElementText(HtmlElement element) {
        return Double.parseDouble(element.getText());
    }
}
