package ru.qatools.school.steps.websteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.data.TemperatureRepresentation;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.pages.blocks.WeatherWidget;
import ru.qatools.school.pages.blocks.widgetblocks.WeatherInfo;
import ru.qatools.school.pages.blocks.widgetblocks.WidgetTemperature;
import ru.yandex.qatools.allure.annotations.Step;

import static java.lang.String.format;
import static org.cthul.matchers.CthulMatchers.matchesPattern;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

/**
 * Created by kurau.
 */
public class DefaultSteps {

    private static final String MAIN_PAGE_FOR_CITY = "http://weather.lanwen.ru/#?cities=%s";
    private static final String MAIN_PAGE = "http://weather.lanwen.ru/";

    private WebDriver driver;

    public DefaultSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открываем главную страницу")
    public void openMainPage() {
        driver.get(MAIN_PAGE);
    }

    @Step("Открываем главную страницу для города «{0}»")
    public void openMainPageWithCity(String city) {
        driver.get(format(MAIN_PAGE_FOR_CITY, city));
    }

    @Step("Должны видеть на странице {0}")
    public void shouldSee(WebElement element) {
        assertThat("Должны видеть элемент", element, isDisplayed());
    }

    @Step("{0} должен называться «{1}»")
    public void shouldSeeWidgetWithTitle(WeatherWidget widget, String cityName) {
        assertThat(widget.getWidgetTitle().getWeatherTitle(), hasText(cityName));
    }

    @Step("Кликаем на элемент «{0}»")
    public void clickOn(WebElement element) {
        element.click();
    }

    @Step("Количество виджетов должно быть равным {0}")
    public void shouldHaveWidgetsCount(int widgetsCount) {
        assertThat("Количество виджетов должно быть", onMainPage().getWeatherWidgets().size(), is(widgetsCount));
    }

    @Step("Элемент «{0}» должен выглядеть по шаблону {2}")
    public void shouldMatchRepresentation(WebElement element, String regex, String explaination) {
        assertThat("Элемент должен следовать шаблону", element.getText(), matchesPattern(regex));
    }

    public void shouldHaveTemperatureRepresentation(WidgetTemperature temperatureWidget, String updatedMeasureUnit) {
        shouldMatchRepresentation(
                temperatureWidget.getWeatherTemperatureDigit(),
                TemperatureRepresentation.DIGITS_PATTERN.toString(),
                TemperatureRepresentation.HUMAN_READABLE_DIGITS_PATTERN.toString());
        shouldMatchRepresentation(temperatureWidget.getWeatherTemperatureUnit(), updatedMeasureUnit, updatedMeasureUnit);
    }

    @Step("Элемент «{1}» должен показывать информацию")
    public void shouldHaveWeatherInfo(WeatherInfo weatherInfo, String weatherInfoName) {
        assertThat("Имя параметра не совпадает с ожидаемым", weatherInfo.getLabelWidget().getText(), is(weatherInfoName));
        assertThat("Иконка не видна", weatherInfo.getImageWidget(), isDisplayed());
        assertThat("Значение не видно", weatherInfo.getValueWidget(), isDisplayed());
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
