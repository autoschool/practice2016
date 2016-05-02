package ru.qatools.school.steps.websteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.blocks.WeatherWidget;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

import static java.lang.String.format;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Every.everyItem;
import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.*;

/**
 * Created by kurau.
 *
 * @author arrumm (Arkhipov Roman)
 */
public class DefaultSteps {

    private static final String MAIN_PAGE = "http://weather.lanwen.ru/#?cities=%s";

    private static final String MAIN_PAGE_WITHOUTPARAMETERS = "http://weather.lanwen.ru/";

    private WebDriver driver;

    public DefaultSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Open main page with city «{0}»")
    public void openMainPageWithCity(String city) {
        driver.get(format(MAIN_PAGE, city));
    }

    @Step("Open main page without parameters")
    public void openMainPageWithoutParams() {
        driver.get(MAIN_PAGE_WITHOUTPARAMETERS);
    }

    @Step("Should see on the page «{0}»")
    public void shouldSeeElement(WebElement element) {
        assertThat("Don't see element", element, both(exists()).and(isDisplayed()));
    }

    @Step("Should see all list elements on the page «{0}»")
    public void shouldSeeAllElementFromList(List<? extends WebElement> wwList) {
        assertThat("Don't see all elements", (List<WebElement>)wwList, everyItem(both(exists()).and(isDisplayed())));
    }

    @Step("Text «{0}» should be «{1}»")
    public void shouldSeeTextInElement(WebElement webElement, String text) {
        assertThat("Expected and actual texts don't match", webElement, hasText(text));
    }

    @Step("Clicking element «{0}»")
    public void clickOn(WebElement element) {
        element.click();
    }

    @Step("Number of widgets on the page should be «{0}»")
    public void shouldBeWidgetsQuantity(List<WeatherWidget> wwList, int widgetsQuantity) {
        assertThat("Number of widgets on the page and expected don't match", wwList, hasSize(widgetsQuantity));
    }

    @Step("Clear element «{0}»")
    public void clearIt(WebElement element) {
        element.clear();
    }

    @Step("Send keys to element «{0}»")
    public void sendKeysToElement(WebElement element, CharSequence ... keys) {
        element.sendKeys(keys);
    }


}
