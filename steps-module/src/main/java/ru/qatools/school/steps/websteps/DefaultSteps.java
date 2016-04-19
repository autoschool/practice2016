package ru.qatools.school.steps.websteps;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.pages.blocks.WeatherWidget;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.exists;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

/**
 * Created by kurau.
 * Changed by onegines (Eugene Kirienko).
 */
public class DefaultSteps {

    private static final String MAIN_PAGE = "http://weather.lanwen.ru/#?cities=%s";

    private WebDriver driver;

    public DefaultSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открываем главную страницу для города «{0}»")
    public void openMainPageWithCity(String city) {
        driver.get(format(MAIN_PAGE, city));
    }

    @Step("Должны видеть на странице элемент «{0}»")
    public void shouldSee(WebElement element) {
        assertThat("Должны видеть элемент", element, both(exists()).and(isDisplayed()));
    }

    @Step("Должны видеть виджет с городом «{1}»")
    public void shouldSeeWidgetWithCity(WeatherWidget widget, String expected) {
        String actual = widget.getWidgetTitle().getCityName().getText();
        assertThat("Ожидали другой город", actual, is(expected));
    }

    @Step("Должны увидеть {0} виджета(ов)")
    public void shouldSeeThisNumberOfWidgets(int count) {
        assertThat("Количество виджетов не соответствует ожидаемому", onMainPage().getWeatherWidget().size(), is(count));
    }

    @Step("Первому виджету назначаем город «{0}», вводя полное название и нажимая 'Return'")
    public void editFirstWidgetWithEnterFullName(String cityName) {
        onMainPage().getFirstWidget().getWidgetTitle().getCityName().click();
        onMainPage().getFirstWidget().getWidgetTitle().getCityName().clear();
        onMainPage().getFirstWidget().getWidgetTitle().getCityName().sendKeys(cityName);
        onMainPage().getFirstWidget().getWidgetTitle().getCityName().sendKeys(Keys.RETURN);
    }

    @Step("Вводим название города «{0}»")
    public void enterCityName(String cityName) {
        onMainPage().getFirstWidget().getWidgetTitle().getCityName().click();
        onMainPage().getFirstWidget().getWidgetTitle().getCityName().clear();
        onMainPage().getFirstWidget().getWidgetTitle().getCityName().sendKeys(cityName);
    }

    @Step("Кликаем по элементу {0}")
    public void clickOn(HtmlElement element) {
        element.click();
    }

    @Step("Элемент «{0}» должен содержать текст «{1}»")
    public void shouldHaveText(HtmlElement element, String text) {
        assertThat("Текст в элементе не соответствует ожидаемому", element, hasText(text));
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
