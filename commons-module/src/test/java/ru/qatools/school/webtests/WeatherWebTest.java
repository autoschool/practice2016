package ru.qatools.school.webtests;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.*;
import org.junit.runner.RunWith;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.pages.blocks.WeatherWidget;
import ru.qatools.school.rules.ScreenshotOnFailureRule;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.WeatherSteps;
import ru.qatools.school.tp.TPInformerRule;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

import static ru.qatools.school.steps.websteps.util.CheckingSteps.*;

/**
 * @author kurau
 * @author gladnik (Nikolai Gladkov)
 */
@RunWith(DataProviderRunner.class)
public class WeatherWebTest {

    private static final String SOME_CITY = "Moscow";
    private static final String ANOTHER_CITY = "Saint Petersburg";
    private static final String NOT_A_CITY = "!qwerty";
    private static final String CITY_NAME_PLACEHOLDER = "What a city?";

    @ClassRule
    public static WebDriverRule webDriverRule = new WebDriverRule();
    private static WeatherSteps weatherSteps;
    @Rule
    public ScreenshotOnFailureRule screenshotOnFailure = new ScreenshotOnFailureRule(webDriverRule.getDriver());
    //@Rule
    public TPInformerRule tms = new TPInformerRule("gladnik");

    @BeforeClass
    public static void initSteps() {
        weatherSteps = new WeatherSteps(webDriverRule.getDriver());
    }

    @Before
    public void openBlankPage() {
        weatherSteps.openBlankPage();
    }

    @DataProvider
    public static Object[][] temperatureClicksUnitsRange() {
        return new Object[][]{
                {0, "°C", -100, 100},
                {1, "°K", 173, 373},
                {2, "°F", -150, 150},
                {3, "°Kaif", 20, 25},
                {4, "°C", -100, 100},
        };
    }

    @Ignore("Ignore for now - takes too long time")
    @Test
    @TestCaseId("2")
    @Title("Должны видеть кнопку добавления виджета на главной странице")
    public void shouldSeeAddWidgetButtonOnMainPage() {
        weatherSteps.openMainPage();
        shouldSee(onMainPage().addWidgetButton());
    }

    @Test
    @TestCaseId("28")
    @Title("Должны видеть название текущей страницы Weather")
    public void shouldSeeWeatherOnPageTitle() {
        weatherSteps.openMainPage();
        weatherSteps.shouldGetPageTitle("Weather");
    }

    @Test
    @TestCaseId("4")
    @Title("Должен отображаться один виджет при открытии главной страницы с одним городом")
    public void shouldSeeOneWidgetOnMainPageWithCity() {
        weatherSteps.openMainPageWithCity(SOME_CITY);
        shouldHaveSize(onMainPage().weatherWidgets(), 1);
        shouldSee(firstWidget());
    }

    @Test
    @TestCaseId("5")
    @Title("Должны отображаться два виджета после нажатия на кнопку добавления виджета")
    public void shouldSeeTwoWidgetsWhenAddOne() {
        weatherSteps.openMainPageWithCity(SOME_CITY);
        weatherSteps.clickOn(onMainPage().addWidgetButton());
        shouldHaveSize(onMainPage().weatherWidgets(), 2);
        shouldSee(onMainPage().weatherWidgets());
    }

    @Test
    @TestCaseId("6")
    @Title("Должен отображаться тот город в виджете с которым открываем страницу")
    public void shouldSeeQueriedCityName() {
        weatherSteps.openMainPageWithCity(SOME_CITY);
        shouldHaveText(firstWidget().widgetTitle().cityName(), SOME_CITY);
    }

    @Test
    @TestCaseId("7")
    @Title("Должны отображаться все элементы виджета")
    public void shouldSeeAllWidgetElements() {
        weatherSteps.openMainPageWithCity(SOME_CITY);
        weatherSteps.waitElementToHaveText(firstWidget().widgetTitle().cityName(), SOME_CITY);
        List<HtmlElement> widgetElements = weatherSteps.listAllWidgetElements(firstWidget());
        shouldSee(widgetElements);
    }

    @Test
    @TestCaseId("8")
    @Title("Должны корректно переключать шкалу и видеть соответсвующие единицы измерения температуры")
    @UseDataProvider("temperatureClicksUnitsRange")
    public void shouldChangeTemperatureUnitsWhenClickOnTemperature(int nClicks, String units, int lowest, int highest) {
        weatherSteps.openMainPageWithCity(SOME_CITY);
        weatherSteps.waitElementToHaveText(firstWidget().widgetTitle().cityName(), SOME_CITY);

        weatherSteps.clickOn(firstWidget().widgetText().temperatureValue(), nClicks);
        shouldHaveText(firstWidget().widgetText().temperatureUnit(), units);
    }

    @Test
    @TestCaseId("13")
    @Title("Должны видеть температуру в допустимом диапазоне")
    @UseDataProvider("temperatureClicksUnitsRange")
    public void shouldSeeTemperatureValuesInRange(int nClicks, String units, int lowest, int highest) {
        weatherSteps.openMainPageWithCity(SOME_CITY);
        weatherSteps.waitElementToHaveText(firstWidget().widgetTitle().cityName(), SOME_CITY);

        weatherSteps.clickOn(firstWidget().widgetText().temperatureValue(), nClicks);
        weatherSteps.shouldHaveTemperatureValueInRange(firstWidget(), lowest, highest);
    }

    @Test
    @TestCaseId("17")
    @Title("Должен меняться город в виджете при вводе другого города")
    public void shouldHaveCityNameChangedWhenChangeIt() {
        weatherSteps.openMainPageWithCity(SOME_CITY);
        weatherSteps.waitElementToHaveText(firstWidget().widgetTitle().cityName(), SOME_CITY);
        weatherSteps.changeCityName(firstWidget(), ANOTHER_CITY);
        shouldSee(firstWidget().widgetTitle().cityName());
        shouldHaveText(firstWidget().widgetTitle().cityName(), ANOTHER_CITY);
    }

    @Test
    @TestCaseId("36")
    @Title("Должно отображаться поле для ввода города после заполнения с ошибкой")
    public void shouldSeeCityNameWhenFilledWithError() {
        weatherSteps.openMainPageWithCity(SOME_CITY);
        weatherSteps.waitElementToHaveText(firstWidget().widgetTitle().cityName(), SOME_CITY);
        weatherSteps.changeCityName(firstWidget(), NOT_A_CITY);
        shouldSee(firstWidget().widgetTitle().cityName());
    }

    @Test
    @TestCaseId("37")
    @Title("Должно отображаться поле для ввода города после стирания из него текста")
    public void shouldSeeCityNameWhenCleared() {
        weatherSteps.openMainPageWithCity(SOME_CITY);
        weatherSteps.waitElementToHaveText(firstWidget().widgetTitle().cityName(), SOME_CITY);
        weatherSteps.clearText(firstWidget().widgetTitle().cityName());
        shouldSee(firstWidget().widgetTitle().cityName());
    }

    @Test
    @TestCaseId("38")
    @Title("Должен удаляться последний виджет на странице")
    public void shouldRemoveTheLastWidget() {
        weatherSteps.openMainPageWithCity(SOME_CITY);
        weatherSteps.clickOn(firstWidget().widgetActions().removeWidgetButton());
        shouldHaveSize(onMainPage().weatherWidgets(), 0);
    }

    @Test
    @TestCaseId("21")
    @Title("Должен удаляться последний добавленный виджет")
    public void shouldRemoveLastAddedWidget() {
        weatherSteps.openMainPageWithCity(SOME_CITY);
        weatherSteps.addWidgetWithCity(ANOTHER_CITY);
        weatherSteps.clickOn(firstWidget().widgetActions().removeWidgetButton());
        shouldHaveSize(onMainPage().weatherWidgets(), 1);
        shouldHaveText(firstWidget().widgetTitle().cityName(), SOME_CITY);
    }

    @Test
    @TestCaseId("39")
    @Title("Должен удаляться первый добавленный виджет")
    public void shouldRemoveFirstAddedWidget() {
        weatherSteps.openMainPageWithCity(SOME_CITY);
        weatherSteps.addWidgetWithCity(ANOTHER_CITY);
        weatherSteps.clickOn(onMainPage().weatherWidgets().get(1).widgetActions().removeWidgetButton());
        shouldHaveSize(onMainPage().weatherWidgets(), 1);
        shouldHaveText(firstWidget().widgetTitle().cityName(), ANOTHER_CITY);
    }


    @Test
    @TestCaseId("23")
    @Title("Должен меняться URL при смене города")
    public void shouldHaveUrlChangedWhenChangeCity() {
        weatherSteps.openMainPageWithCity(ANOTHER_CITY);
        weatherSteps.changeCityName(firstWidget(), SOME_CITY);
        String pageUrl = weatherSteps.getPageUrl();
        shouldEndWith(pageUrl, SOME_CITY);
    }

    @Test
    @TestCaseId("40")
    @Title("Должен меняться URL при добавлении виджета")
    public void shouldHaveUrlChangedWhenAddWidget() {
        weatherSteps.openMainPageWithCity(SOME_CITY);
        String pageUrl = weatherSteps.getPageUrl();
        weatherSteps.clickOn(onMainPage().addWidgetButton());
        shouldEndWith(pageUrl, CITY_NAME_PLACEHOLDER + "," + SOME_CITY);
    }

    @Test
    @TestCaseId("41")
    @Title("Должен оставаться плесхолдер на месте названия города при открытии страницы с ним вместо названия города")
    public void shouldNotTransformPlaceholderWhenLoadPageWithIt() {
        weatherSteps.openMainPageWithCity(CITY_NAME_PLACEHOLDER);
        weatherSteps.waitElementToStale(firstWidget().widgetTitle().cityName());
        shouldHaveText(firstWidget().widgetTitle().cityName(), CITY_NAME_PLACEHOLDER);
    }

    @Test
    @TestCaseId("26")
    @Title("Должны отображаться два нужных виджета при открытии страницы с двумя городами")
    public void shouldSeeTwoWidgetsWhenOpenPageForTwo() {
        weatherSteps.openMainPageWithCity(SOME_CITY + "," + ANOTHER_CITY);
        shouldHaveSize(onMainPage().weatherWidgets(), 2);
        shouldHaveText(firstWidget().widgetTitle().cityName(), SOME_CITY);
        shouldHaveText(onMainPage().weatherWidgets().get(1).widgetTitle().cityName(), ANOTHER_CITY);
    }

    @Test
    @TestCaseId("42")
    @Title("Должен появляться саджест при клике на название города и нажатии стрелки вниз")
    public void shouldSeeSuggestWhenClickAndPressArrowDown() {
        weatherSteps.openMainPageWithCity(SOME_CITY);
        weatherSteps.waitElementToHaveText(firstWidget().widgetTitle().cityName(), SOME_CITY);
        weatherSteps.openSuggest();
        shouldSee(firstWidget().suggest());
    }

    @Test
    @TestCaseId("56")
    @Title("Должны видеть поле для ввода названия города когда открываем страницу не указав название города")
    public void shouldSeeCityNameFieldWhenEnterNoCityName() throws InterruptedException {
        weatherSteps.openMainPageWithCity("");
        Thread.sleep(1000);
        shouldSee(firstWidget().widgetTitle().cityName());
    }


    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

    private WeatherWidget firstWidget() {
        return onMainPage().firstWidget();
    }
}
