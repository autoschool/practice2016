package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import ru.qatools.school.data.WeatherCharacteristics;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.pages.blocks.WeatherWidget;
import ru.qatools.school.pages.blocks.widgetblocks.WidgetTemperature;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.qatools.school.tp.TPInformerRule;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.annotations.Title;

import static ru.qatools.school.data.TemperatureRepresentation.*;

/**
 * @author raipc
 */
public class MySmokeTestSuite {
    private static final String CITY = "Lipetsk";
    private static final String NEW_WIDGET_TITLE = "What a city?";

    private DefaultSteps defaultSteps;

    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();

    @Rule
    public TPInformerRule tms = new TPInformerRule("raipc");

    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
    }

    @Test
    @TestCaseId("4")
    @Title("Должны открыть пустую главную страницу и увидеть кнопку добавления виджета")
    public void shouldSeeEmptyPageWithNewWidgetButton() {
        defaultSteps.openMainPage();
        defaultSteps.shouldSee(onMainPage().getNewWidgetButton());
    }

    @Test
    @TestCaseId("5")
    @Title("Открываем страницу для города «Lipetsk»")
    public void shouldSeeWidgetWithCityFromGetParameters() {
        defaultSteps.openMainPageWithCity(CITY);
        WeatherWidget firstWidget = onMainPage().getFirstWeatherWidget();
        defaultSteps.shouldSee(firstWidget);
        defaultSteps.shouldSeeWidgetWithTitle(firstWidget, CITY);
    }

    @Test
    @TestCaseId("6")
    @Title("Должны добавить и увидеть новый виджет")
    public void shouldSeeAddedWidget() {
        defaultSteps.openMainPageWithCity(CITY);
        int widgetsCountBefore = onMainPage().getWeatherWidgets().size();
        defaultSteps.clickOn(onMainPage().getNewWidgetButton());
        defaultSteps.shouldHaveWidgetsCount(widgetsCountBefore + 1);
        defaultSteps.shouldSeeWidgetWithTitle(onMainPage().getFirstWeatherWidget(), NEW_WIDGET_TITLE);
    }

    @Test
    @TestCaseId("7")
    @Title("Должны удалить виджет")
    public void shouldRemoveWidget() {
        defaultSteps.openMainPageWithCity(CITY);
        int widgetsCountBefore = onMainPage().getWeatherWidgets().size();
        WebElement first = onMainPage().getFirstWeatherWidget();
        defaultSteps.shouldSee(first);
        WebElement next = onMainPage().getFirstWeatherWidget().getActions();
        defaultSteps.shouldSee(next);
        WebElement more = onMainPage().getFirstWeatherWidget().getActions().getRemovingWidgetButton();
        defaultSteps.shouldSee(more);
        defaultSteps.clickOn(onMainPage().getFirstWeatherWidget().getActions().getRemovingWidgetButton());
        defaultSteps.shouldHaveWidgetsCount(widgetsCountBefore - 1);
    }

    @Test
    @TestCaseId("8")
    @Title("Температура имеет числовой формат")
    public void shouldHaveNumericTemperature() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldMatchRepresentation(
                getTemperatureWidget().getWeatherTemperatureDigit(),
                DIGITS_PATTERN.toString(),
                HUMAN_READABLE_DIGITS_PATTERN.toString());
    }

    @Test
    @TestCaseId("9")
    @Title("При клике на температуру меняются единицы измерения")
    public void shouldSwitchMeasureUnits() {
        defaultSteps.openMainPageWithCity(CITY);
        WidgetTemperature temperatureWidget = getTemperatureWidget();
        defaultSteps.shouldSee(temperatureWidget);
        defaultSteps.shouldMatchRepresentation(temperatureWidget.getWeatherTemperatureUnit(), CELSIUS.toString(), CELSIUS.toString());
        defaultSteps.clickOn(temperatureWidget);
        defaultSteps.shouldMatchRepresentation(temperatureWidget.getWeatherTemperatureUnit(), KELVIN.toString(), KELVIN.toString());
        defaultSteps.clickOn(temperatureWidget);
        defaultSteps.shouldMatchRepresentation(temperatureWidget.getWeatherTemperatureUnit(), FAHRENHEIT.toString(), FAHRENHEIT.toString());
        defaultSteps.clickOn(temperatureWidget);
        defaultSteps.shouldMatchRepresentation(temperatureWidget.getWeatherTemperatureUnit(), KAIF.toString(), KAIF.toString());
        defaultSteps.clickOn(temperatureWidget);
        defaultSteps.shouldMatchRepresentation(temperatureWidget.getWeatherTemperatureUnit(), CELSIUS.toString(), CELSIUS.toString());
    }

    @Test
    @TestCaseId("12")
    @Title("Видна информация о погоде")
    public void shouldSeeAdditionalWeatherInformation() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldHaveWeatherInfo(
                onMainPage().getFirstWeatherWidget().getWidgetText().getSunriseInfo(),
                WeatherCharacteristics.SUNRISE.toString());
        defaultSteps.shouldHaveWeatherInfo(
                onMainPage().getFirstWeatherWidget().getWidgetText().getSunsetInfo(),
                WeatherCharacteristics.SUNSET.toString());
        defaultSteps.shouldHaveWeatherInfo(
                onMainPage().getFirstWeatherWidget().getWidgetText().getWindInfo(),
                WeatherCharacteristics.WIND.toString());
        defaultSteps.shouldHaveWeatherInfo(
                onMainPage().getFirstWeatherWidget().getWidgetText().getHumidityInfo(),
                WeatherCharacteristics.HUMIDITY.toString());
    }

    @Test
    @TestCaseId("14")
    @Title("Видна картинка текущей погоды")
    public void shouldSeeCurrentWeatherPicture() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSee(
                onMainPage()
                        .getFirstWeatherWidget()
                        .getWidgetText()
                        .getWeatherImage());
    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

    private WidgetTemperature getTemperatureWidget() {
        return onMainPage().getFirstWeatherWidget().getWidgetText().getWeatherTemperatureWidget();
    }

}
