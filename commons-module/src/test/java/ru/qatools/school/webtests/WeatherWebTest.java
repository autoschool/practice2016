package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.data.RegexPattern;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.qatools.school.tp.TPInformerRule;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class WeatherWebTest {

    private static final String DEFAULT_CITY_NAME = "Moscow";
    private static final String ANOTHER_VALID_CITY_NAME = "Chita";

    private DefaultSteps defaultSteps;

    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();

    @Rule
    public TPInformerRule tms = new TPInformerRule("totallynotkate");

    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
    }

    @Test
    @TestCaseId("18")
    @Title("Должны видеть главную страницу с кнопкой «+» и без виджета")
    public void shouldSeeButtonAndNoWidgetsOnMainPage() {
        defaultSteps.openEmptyMainPage();
        defaultSteps.shouldSee(onMainPage().getAddWidgetButton());
        defaultSteps.shouldSeeWidgetsQuantity(0);
    }

    @Test
    @Title("Должны видеть виджет на главной странице")
    @TestCaseId("1")
    public void shouldSeeWidgetOnMainPage() {
        openPageWithDefaultCity();
        defaultSteps.shouldSee(onMainPage().getFirstWeatherWidget());
    }

    @Test
    @TestCaseId("11")
    @Title("Должны видеть виджет для города, указанного в URL")
    public void shouldSeeCityAtWidget() {
        openPageWithDefaultCity();
        defaultSteps.shouldSeeText(getFirstWidgetCity(), DEFAULT_CITY_NAME);
    }

    @Test
    @TestCaseId("3")
    @Title("Должны видеть у виджета строку со временем вида «3 AM, 25 Aug 72 (+03:00)»")
    public void shouldSeeTimeAtWidget() {
        openPageWithDefaultCity();
        defaultSteps.shouldSee(onMainPage().getFirstWeatherWidget().getWidgetTitle().getTitleTimeBlock());
        defaultSteps.shouldMatchRegex(onMainPage().getFirstWeatherWidget().getWidgetTitle()
                .getTitleTimeBlock().getText(), RegexPattern.TIME_AND_DATE.getRegexPattern());
    }

    @Test
    @TestCaseId("21")
    @Title("Должны видеть у виджета пиктограмму погоды")
    public void shouldSeePictogramAtWidget() {
        openPageWithDefaultCity();
        defaultSteps.shouldSee(onMainPage().getFirstWeatherWidget().getWidgetText().getWeatherImage());
    }

    @Test
    @TestCaseId("5")
    @Title("Должны видеть у виджета текущую температуру в формате «-1.0 °С»")
    public void shouldSeeCurrentTemperatureCelsius() {
        openPageWithDefaultCity();
        defaultSteps.shouldMatchRegex(onMainPage().getFirstWeatherWidget().getWidgetText().getTemperature().getText(),
                RegexPattern.CELSIUS.getRegexPattern());
    }

    @Test
    @TestCaseId("16")
    @Title("Должны видеть горизонтальную полосу на виджете")
    public void shouldSeeDividerLine() {
        openPageWithDefaultCity();
        defaultSteps.shouldSee(onMainPage().getFirstWeatherWidget().getWidgetText().getDividerLine());
    }

    @Test
    @TestCaseId("6")
    @Title("Должны видеть у виджета строку с временем рассвета в формате «Sunrise 05:34»")
    public void shouldSeeSunriseInfo() {
        openPageWithDefaultCity();
        defaultSteps.shouldMatchRegex(onMainPage().getFirstWeatherWidget().getWidgetText()
                .getSunriseInfoLine().getText(), RegexPattern.SUNRISE.getRegexPattern());
    }

    @Test
    @TestCaseId("7")
    @Title("Должны видеть у виджета строку с временем заката в формате «Sunset 19:51»")
    public void shouldSeeSunsetInfo() {
        openPageWithDefaultCity();
        defaultSteps.shouldMatchRegex(onMainPage().getFirstWeatherWidget().getWidgetText()
                .getSunsetInfoLine().getText(), RegexPattern.SUNSET.getRegexPattern());
    }

    @Test
    @TestCaseId("8")
    @Title("Должны видеть у виджета строку с силой ветра в формате «Wind 2.57 m/s»")
    public void shouldSeeWindInfo() {
        openPageWithDefaultCity();
        defaultSteps.shouldMatchRegex(onMainPage().getFirstWeatherWidget().getWidgetText().getWindInfoLine().getText(),
                RegexPattern.WIND.getRegexPattern());
    }

    @Test
    @TestCaseId("9")
    @Title("Должны видеть у виджета строку с влажностью в формате «Humidity 82 %»")
    public void shouldSeeHumidityInfo() {
        openPageWithDefaultCity();
        defaultSteps.shouldMatchRegex(onMainPage().getFirstWeatherWidget().getWidgetText()
                .getHumidityInfoLine().getText(), RegexPattern.HUMIDITY.getRegexPattern());
    }

    @Test
    @TestCaseId("10")
    @Title("Должны удалять виджет нажатием на кнопку «-»")
    public void shouldBeAbleToDeleteWidget() {
        openPageWithDefaultCity();
        int numberOfWidgets = onMainPage().getWeatherWidgetList().size();
        defaultSteps.click(onMainPage().getFirstWeatherWidget().getWidgetActions().getWidgetDeleteButton());
        defaultSteps.shouldSeeWidgetsQuantity(numberOfWidgets - 1);
    }

    @Test
    @TestCaseId("2")
    @Title("Должны добавлять виджет на главной странице нажатием на «+»")
    public void shouldBeAbleToAddWidget() {
        openPageWithDefaultCity();
        int numberOfWidgets = onMainPage().getWeatherWidgetList().size();
        defaultSteps.click(onMainPage().getAddWidgetButton());
        defaultSteps.shouldSeeWidgetsQuantity(numberOfWidgets + 1);
    }

    @Test
    @TestCaseId("22")
    @Title("Должны менять город у виджета")
    public void shouldBeAbleToChangeCity() {
        openPageWithDefaultCity();
        defaultSteps.changeCityNameWithEnterKey(onMainPage().getFirstWeatherWidget(), ANOTHER_VALID_CITY_NAME);
        defaultSteps.shouldSeeText(onMainPage().getFirstWeatherWidget().getWidgetTitle().getCity(),
                ANOTHER_VALID_CITY_NAME);
    }

    @Test
    @TestCaseId("23")
    @Title("Должны видеть температуру в градусах Кельвина после одного клика на температуру в градусах Цельсия")
    public void shouldSeeKelvinTemperature() {
        openPageWithDefaultCity();
        defaultSteps.click(onMainPage().getFirstWeatherWidget().getWidgetText().getTemperature());
        defaultSteps.shouldMatchRegex(onMainPage().getFirstWeatherWidget().getWidgetText().getTemperature().getText(),
                RegexPattern.KELVIN.getRegexPattern());
    }

    @Test
    @TestCaseId("24")
    @Title("Должны видеть температуру в градусах Фаренгейта после двух кликов на температуру в градусах Цельсия")
    public void shouldSeeFahrenheitTemperature() {
        openPageWithDefaultCity();
        defaultSteps.click(onMainPage().getFirstWeatherWidget().getWidgetText().getTemperature());
        defaultSteps.click(onMainPage().getFirstWeatherWidget().getWidgetText().getTemperature());
        defaultSteps.shouldMatchRegex(onMainPage().getFirstWeatherWidget().getWidgetText().getTemperature().getText(),
                RegexPattern.FAHRENHEIT.getRegexPattern());
    }

    @Test
    @TestCaseId("25")
    @Title("Должны видеть комфортную температуру после трех кликов на температуру в градусах Цельсия")
    public void shouldSeeKaifTemperature() {
        openPageWithDefaultCity();
        defaultSteps.click(onMainPage().getFirstWeatherWidget().getWidgetText().getTemperature());
        defaultSteps.click(onMainPage().getFirstWeatherWidget().getWidgetText().getTemperature());
        defaultSteps.click(onMainPage().getFirstWeatherWidget().getWidgetText().getTemperature());
        defaultSteps.shouldMatchRegex(onMainPage().getFirstWeatherWidget().getWidgetText().getTemperature().getText(),
                RegexPattern.KAIF.getRegexPattern());
    }

    @Test
    @TestCaseId("26")
    @Title("Должны видеть температуру в градусах Цельсия после четырех кликов на температуру в градусах Цельсия")
    public void shouldReturnToCelsiusTemperature(){
        openPageWithDefaultCity();
        defaultSteps.click(onMainPage().getFirstWeatherWidget().getWidgetText().getTemperature());
        defaultSteps.click(onMainPage().getFirstWeatherWidget().getWidgetText().getTemperature());
        defaultSteps.click(onMainPage().getFirstWeatherWidget().getWidgetText().getTemperature());
        defaultSteps.click(onMainPage().getFirstWeatherWidget().getWidgetText().getTemperature());
        defaultSteps.shouldMatchRegex(onMainPage().getFirstWeatherWidget().getWidgetText().getTemperature().getText(),
                RegexPattern.CELSIUS.getRegexPattern());
    }


    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

    private void openPageWithDefaultCity() {
        defaultSteps.openMainPageWithCity(DEFAULT_CITY_NAME);
    }

    private HtmlElement getFirstWidgetCity() {
        return onMainPage().getFirstWeatherWidget().getWidgetTitle().getCity();
    }
}
