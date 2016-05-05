package ru.qatools.school.steps.websteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.MainPage;
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

    @Step("Открываем главную страницу для города «{0}»")
    public void openMainPageWithCity(String city) {
        driver.get(format(MAIN_PAGE, city));
    }

    @Step("Открываем главную страницу как обычный пользователь")
    public void openMainPageWithoutParams() {
        driver.get(MAIN_PAGE_WITHOUTPARAMETERS );
    }

    @Step("Должны видеть на странице «{0}»")
    public void shouldSee(WebElement element) {
        assertThat("Должны видеть элемент", element, both(exists()).and(isDisplayed()));
    }

    @Step("Должны видеть на странице все элементы списка «{0}»")
    public void shouldSeeAllFrom(List<? extends WebElement> widgets) {
        assertThat("Видим не все элементы", (List<WebElement>) widgets, everyItem(both(exists()).and(isDisplayed())));
    }

    @Step("Текст «{0}» элемента должен быть «{1}»")
    public void shouldSeeTextInElement(WebElement webElement, String text) {
        assertThat("Текст элемента и ожидаемый не совпадают", webElement, hasText(text));
    }

    @Step("Должен кликнуться элемент «{0}»")
    public void clickOn(WebElement element) {
        element.click();
    }

    @Step("Количество виджетов на странице должно быть «{0}»")
    public void shouldBeWidgetsQuantityOnPage(List<WeatherWidget> widgets, int widgetsQuantity) {
        assertThat("Количество виджетов на странице не равно ожидаемому", widgets, hasSize(widgetsQuantity));
    }

    @Step("Send keys to element «{0}»")
    public void sendKeysTo(WebElement element, CharSequence ... keys) {
        element.sendKeys(keys);
    }

    @Step("Clear element «{0}»")
    public void clearIt(WebElement element) {
        element.clear();
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
