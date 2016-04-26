package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.data.RegexPattern;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.pages.blocks.widgetblocks.WidgetTemperature;
import ru.qatools.school.pages.blocks.widgetblocks.WidgetText;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.qatools.school.tp.TPInformerRule;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.annotations.Title;

public class WeatherWebTest {

    public static final String CITY = "Krasnoyarsk";
    public static final String ANOTHER_CITY = "Moscow";

    private DefaultSteps defaultSteps;

    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();

    @Rule
    public TPInformerRule tms = new TPInformerRule("kormyshov");

    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
    }

    @Test
    @TestCaseId("3")
    @Title("Должен видеть пустую страницу с кнопкой добавления виджета")
    public void shouldSeeAddNewWidgetButtonWithoutWidgetOnMainPage(){
        defaultSteps.openEmptyMainPage();
        defaultSteps.shouldSee(onMainPage().getNewWidgetButton());
        defaultSteps.shouldHaveSize(onMainPage().getWeatherWidgets(), 0);
    }

    @Test
    @TestCaseId("24")
    @Title("Должен быть виден виджет на главной странице")
    public void shouldSeeWidgetOnMainPage() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSee(onMainPage().getFirstWidget());
    }

    @Test
    @TestCaseId("1")
    @Title("Должен быть виден виджет для переданного города")
    public void shouldSeeCityFromGETQuery() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSeeText(onMainPage().getFirstWidget().getWidgetTitle().getCityName(), CITY);
    }

    @Test
    @TestCaseId("33")
    @Title("Должны быть видны два виждета для переданных городов")
    public void shouldSeeTwoCitiesFromGETQuery(){
        defaultSteps.openMainPageWithCities(CITY, ANOTHER_CITY);
        defaultSteps.shouldHaveSize(onMainPage().getWeatherWidgets(), 2);
        defaultSteps.shouldSee(onMainPage().getWeatherWidgets());
    }

    @Test
    @TestCaseId("25")
    @Title("Должен быть виден виджет с пустым полем для ввода города")
    public void shouldSeeEmptyWidget(){
        defaultSteps.openMainPageWithCity("");
        defaultSteps.shouldSeeText(onMainPage().getFirstWidget().getWidgetTitle().getCityName(), "");
    }

    @Test
    @TestCaseId("26")
    @Title("Должна быть видна пиктограмма на виджете")
    public void shouldSeeImageOnWidget(){
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSee(onMainPage().getFirstWidget().getWidgetText().getWeatherImage());
    }

    @Test
    @TestCaseId("2")
    @Title("Должны быть видны два виджета на странице")
    public void shouldSeeTwoWidgetsAfterAddOne() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.clickOn(onMainPage().getNewWidgetButton());
        defaultSteps.shouldHaveSize(onMainPage().getWeatherWidgets(), 2);
        defaultSteps.shouldSee(onMainPage().getWeatherWidgets());
    }

    @Test
    @TestCaseId("27")
    @Title("Должна быть видна лишь кнопка добавления виджета после удаления виджета")
    public void shouldSeeZeroWidgetAfterDelete(){
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.clickOn(onMainPage().getFirstWidget().getActions().getRemoveButton());
        defaultSteps.shouldHaveSize(onMainPage().getWeatherWidgets(), 0);
        defaultSteps.shouldSee(onMainPage().getNewWidgetButton());
    }

    @Test
    @TestCaseId("28")
    @Title("Должна быть видна строка со временем на виджете")
    public void shouldSeeTimeOnWidget(){
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSee(onMainPage().getFirstWidget().getWidgetTitle().getDate());
        defaultSteps.shouldMatchRegex(onMainPage().getFirstWidget().getWidgetTitle().getDate().getText(), RegexPattern.DATE_TIME.getPattern());
    }

    @Test
    @TestCaseId("29")
    @Title("Поля на виджете должны соответствовать шаблонам")
    public void shouldSeeInfoFieldsOnWidget(){
        defaultSteps.openMainPageWithCity(CITY);
        WidgetText widgetText = onMainPage().getFirstWidget().getWidgetText();

        defaultSteps.shouldSee(widgetText.getSunriseInfoLine());
        defaultSteps.shouldMatchRegex(widgetText.getSunriseInfoLine().getText(), RegexPattern.SUNRISE.getPattern());

        defaultSteps.shouldSee(widgetText.getSunsetInfoLine());
        defaultSteps.shouldMatchRegex(widgetText.getSunsetInfoLine().getText(), RegexPattern.SUNSET.getPattern());

        defaultSteps.shouldSee(widgetText.getWindInfoLine());
        defaultSteps.shouldMatchRegex(widgetText.getWindInfoLine().getText(), RegexPattern.WIND.getPattern());

        defaultSteps.shouldSee(widgetText.getHumidityInfoLine());
        defaultSteps.shouldMatchRegex(widgetText.getHumidityInfoLine().getText(), RegexPattern.HUMIDITY.getPattern());
    }

    @Test
    @TestCaseId("30")
    @Title("Температура должна отображаться в правильном формате и переключаться шкала измерения при кликах")
    public void shouldSeeTemperatureOnWidget(){
        defaultSteps.openMainPageWithCity(CITY);
        WidgetTemperature widgetTemperature = onMainPage().getFirstWidget().getWidgetTemperature();

        defaultSteps.shouldSee(widgetTemperature);
        defaultSteps.shouldMatchRegex(widgetTemperature.toString(), RegexPattern.CELSIUS.getPattern());
        defaultSteps.clickOn(widgetTemperature);
        defaultSteps.shouldMatchRegex(widgetTemperature.toString(), RegexPattern.KELVIN.getPattern());
        defaultSteps.clickOn(widgetTemperature);
        defaultSteps.shouldMatchRegex(widgetTemperature.toString(), RegexPattern.FAHRENHEIT.getPattern());
        defaultSteps.clickOn(widgetTemperature);
        defaultSteps.shouldMatchRegex(widgetTemperature.toString(), RegexPattern.KAIF.getPattern());
        defaultSteps.clickOn(widgetTemperature);
        defaultSteps.shouldMatchRegex(widgetTemperature.toString(), RegexPattern.CELSIUS.getPattern());
    }

    @Test
    @TestCaseId("31")
    @Title("Должен смениться город у виджета")
    public void shouldSeeAnotherCityAfterChange(){
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.changeCityNameAndPressEnter(onMainPage().getFirstWidget(), ANOTHER_CITY);
        defaultSteps.shouldSeeText(onMainPage().getFirstWidget().getWidgetTitle().getCityName(), ANOTHER_CITY);
    }

    @Test
    @TestCaseId("32")
    @Title("Предлагаемые города должны быть не undefined")
    public void shouldNotSeeUndefinedInSuggestCities(){
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.changeCityName(onMainPage().getFirstWidget(), ANOTHER_CITY.substring(0, ANOTHER_CITY.length()-1));
        defaultSteps.shouldNotUndefined(onMainPage().getFirstWidget().getWidgetTitle().getCitySuggest().getList());
    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

}
