package ru.qatools.school.webtests;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.DataProviders;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import ru.qatools.school.data.WeatherCharacteristics;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.pages.blocks.WeatherWidget;
import ru.qatools.school.pages.blocks.widgetblocks.WeatherInfo;
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
@RunWith(DataProviderRunner.class)
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
        defaultSteps.shouldHaveWidgetsCount(0);
        defaultSteps.shouldSee(onMainPage().getNewWidgetButton());
    }

    @Test
    @TestCaseId("5")
    @Title("Открываем страницу для города «Lipetsk»")
    public void shouldSeeWidgetWithCityFromUrl() {
        defaultSteps.openMainPageWithCity(CITY);
        WeatherWidget firstWidget = onMainPage().getFirstWeatherWidget();
        defaultSteps.shouldSee(firstWidget);
        defaultSteps.shouldSeeWidgetWithTitle(firstWidget, CITY);
    }

    @Test
    @TestCaseId("6")
    @Title("Должны добавить и увидеть новый виджет с заголовком " + NEW_WIDGET_TITLE)
    public void shouldSeeAddedWidget() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.clickOn(onMainPage().getNewWidgetButton());
        defaultSteps.shouldSeeWidgetWithTitle(onMainPage().getFirstWeatherWidget(), NEW_WIDGET_TITLE);
    }

    @Test
    @TestCaseId("21")
    @Title("После добавления виджета их количество должно увеличиться на единицу")
    public void shouldIncreaseNumberOfWidgetsByOne() {
        defaultSteps.openMainPageWithCity(CITY);
        int widgetsCountBefore = onMainPage().getWeatherWidgets().size();
        defaultSteps.clickOn(onMainPage().getNewWidgetButton());
        defaultSteps.shouldHaveWidgetsCount(widgetsCountBefore + 1);
    }

    @Test
    @TestCaseId("7")
    @Title("Должны удалить виджет")
    public void shouldRemoveWidget() {
        defaultSteps.openMainPageWithCity(CITY);
        int widgetsCountBefore = onMainPage().getWeatherWidgets().size();
        defaultSteps.clickOn(onMainPage().getFirstWeatherWidget().getActions().getRemovingWidgetButton());
        defaultSteps.shouldHaveWidgetsCount(widgetsCountBefore - 1);
    }

    @Test
    @TestCaseId("007")
    @Title("Должны увидеть дату")
    public void shouldContainDate() {
        defaultSteps.openMainPageWithCity(CITY);
        int widgetsCountBefore = onMainPage().getWeatherWidgets().size();
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


    @DataProvider
    public static Object[][] temperature() {
        return new Object[][]{
                {0, CELSIUS.toString()},
                {1, KELVIN.toString()},
                {2, FAHRENHEIT.toString()},
                {3, KAIF.toString()},
                {4, CELSIUS.toString()}};
    }

    @Test
    @TestCaseId("9")
    @UseDataProvider("temperature")
    @Title("Должны кликнуть на температуру {0} раз и получить температуру в {1}")
    public void shouldSwitchMeasureUnits(int numberOfClicks, String representation) {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSee(getTemperatureWidget());
        defaultSteps.clickNTimesOn(getTemperatureWidget(), numberOfClicks);
        defaultSteps.shouldMatchRepresentation(getTemperatureWidget().getWeatherTemperatureUnit(), representation);
    }

    @DataProvider
    public static Object[][] weatherInfo() {
        return DataProviders.testForEach(WeatherCharacteristics.class);
    }

    @Test
    @TestCaseId("12")
    @UseDataProvider("weatherInfo")
    @Title("Видны названия погодных характеристик")
    public void shouldSeeAdditionalWeatherInformation(WeatherCharacteristics characteristic) {
        defaultSteps.openMainPageWithCity(CITY);
        WebElement weatherInfo = getWeatherCharacteristicsWidget(characteristic).getLabel();
        defaultSteps.shouldSee(weatherInfo);
        defaultSteps.shouldMatchRepresentation(weatherInfo, characteristic.toString());
    }

    @Test
    @TestCaseId("22")
    @UseDataProvider("weatherInfo")
    @Title("Видны иконки погодных характеристик")
    public void shouldSeeAdditionalWeatherInformationIcon(WeatherCharacteristics characteristic) {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSee(getWeatherCharacteristicsWidget(characteristic).getImage());
    }

    @Test
    @TestCaseId("23")
    @UseDataProvider("weatherInfo")
    @Title("Видны значения погодных характеристик")
    public void shouldSeeAdditionalWeatherInformationValue(WeatherCharacteristics characteristic) {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSee(getWeatherCharacteristicsWidget(characteristic).getValue());
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

    private WeatherInfo getWeatherCharacteristicsWidget(WeatherCharacteristics characteristic) {
        return onMainPage().getFirstWeatherWidget().getWidgetText().getInfo(characteristic);
    }

}
