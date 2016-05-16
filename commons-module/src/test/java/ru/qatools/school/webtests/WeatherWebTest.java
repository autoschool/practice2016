package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.data.FormatVerify;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.qatools.school.tp.TPInformerRule;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.annotations.Title;

public class WeatherWebTest {

    public static final String MOSCOW = "Moscow";
    public static final String OMSK = "Omsk";
    public static final String NEW_WIDGET_TITLE = "What a city?";
    public static final String CELSIUS = "°C";


    private DefaultSteps defaultSteps;

    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();

    @Rule
    public TPInformerRule tms = new TPInformerRule("bahek091");

    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
    }


    @Test
    @TestCaseId("1")
    @Title("Должны видеть главную страницу с кнопкой добавления виджета")
    public void shouldOpenMainPage() {
        defaultSteps.openMainPage();
        defaultSteps.shouldSee(onMainPage().getAddWidgetButton());
        defaultSteps.shouldBeEqualsCountOfWidgets(0);
    }

    @Test
    @TestCaseId("2")
    @Title("Должны видеть виджет на главной странице")
    public void shouldSeeWidgetOnMainPage() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSee(onMainPage().getWeatherWidgets().get(0));
    }

    @Test
    @TestCaseId("3")
    @Title("Должны видеть город {1} в элементе {2}")
    public void shouldSeeCity() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.strShouldBeInElement(MOSCOW, onMainPage().getWeatherWidgets().get(0).widgetTitle().getCity());
    }

    @Test
    @TestCaseId("4")
    @Title("Поле с показанием температуры первого виджета на странице не должно быть пустым")
    public void shoudBeTemperatureOnWidget(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldBeNotEmpty(onMainPage().getWeatherWidgets().get(0).widgetText().digitTemperature());
    }

    @Test
    @TestCaseId("5")
    @Title("Должны видеть у первого виджета кнопку удаления {0}")
    public void shoudSeeRemoveWidgetButton(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSee(onMainPage().getWeatherWidgets().get(0).getActions().removeBtn());
    }

    @Test
    @TestCaseId("6")
    @Title("Должны видеть на странице кнопку добавления виджета {0}")
    public void shoudSeeAddWidgetButton(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSee(onMainPage().getAddWidgetButton());
    }

    @TestCaseId("7")
    @Test
    @Title("Должен добавляться еще один виджет")
    public void shouldBeAbleToAddWidget() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        int numOfWidgets = onMainPage().getWeatherWidgets().size();
        defaultSteps.clickButton(onMainPage().getAddWidgetButton());
        defaultSteps.shouldBeEqualsCountOfWidgets(numOfWidgets + 1);
    }

    @Test
    @TestCaseId("8")
    @Title("После удаления первого из пары виджетов должен остаться один")
    public void shouldBeAbleToRemoveWidget() {
        defaultSteps.openMainPageWithTwoCities(MOSCOW, OMSK);
        int numOfWidgets = onMainPage().getWeatherWidgets().size();
        defaultSteps.clickButton(onMainPage().getWeatherWidgets().get(0).getActions().removeBtn());
        defaultSteps.shouldBeEqualsCountOfWidgets(numOfWidgets - 1);
    }

    @Test
    @TestCaseId("9")
    @Title("Температура отображатся в допустимых пределах")
    public void shouldBeCorrectTemperature() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldBeBetweenMinAndMax(onMainPage().getWeatherWidgets().get(0).widgetText().digitTemperature(), -80, 80);
    }
   @Test
    @TestCaseId("10")
    @Title("Должны видеть 'What a city' в заголовке нового виджета")
    public void shouldSeeNewCityTitle() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.clickButton(onMainPage().getAddWidgetButton());
        defaultSteps.strShouldBeInElement(NEW_WIDGET_TITLE, onMainPage().getWeatherWidgets().get(0).widgetTitle().getCity());
    }

    @Test
    @TestCaseId("11")
    @Title("Формат вывода температуры в градусах цельсия должен соответствовать заданному формату")
    public void celsiusTemperatureShouldBeInCorrectFormat(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldMatchToRegExp(onMainPage().getWeatherWidgets().get(0).widgetText().temperature(), format.getRegExpForCelsius());
    }

    @Test
    @TestCaseId("12")
    @Title("Время и дата в заголовке соответствуют заданному формату")
    public void dateShouldBeInCorrectFormat(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldMatchToRegExp(onMainPage().getWeatherWidgets().get(0).widgetTitle().getDate(), format.getRegExpForDate());
    }

    @Test
    @TestCaseId("13")
    @Title("Время восхода солнца в первом виджет соответствуют заданному формату")
    public void sunriseTimeShouldBeInCorrectFormat(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldMatchToRegExp(onMainPage().getWeatherWidgets().get(0).widgetText().sunriseLine(), format.getRegExpForSunriseTime());
    }

    @Test
    @TestCaseId("14")
    @Title("Время захода солнца в первом виджет соответствуют заданному формату")
    public void sunsetTimeShouldBeInCorrectFormat(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldMatchToRegExp(onMainPage().getWeatherWidgets().get(0).widgetText().sunsetLine(), format.getRegExpForSunsetTime());
    }

    @Test
    @TestCaseId("15")
    @Title("Скорость ветра в первом виджете соответствуют заданному формату")
    public void windSpeedShouldBeInCorrectFormat(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldMatchToRegExp(onMainPage().getWeatherWidgets().get(0).widgetText().windLine(), format.getRegExpForWind());
    }

    @Test
    @TestCaseId("16")
    @Title("Влажность воздуха в первом виджете соответствуют заданному формату")
    public void humiditySpeedShouldBeInCorrectFormat(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldMatchToRegExp(onMainPage().getWeatherWidgets().get(0).widgetText().humidityLine(), format.getRegExpForHumidity());
    }

    @Test
    @TestCaseId("17")
    @Title("Формат вывода температуры в градусах Кельвина должен соответствовать заданному формату")
    public void kelvinTemperatureShouldBeInCorrectFormat(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.clickButton(onMainPage().getWeatherWidgets().get(0).widgetText().temperature());
        defaultSteps.shouldMatchToRegExp(onMainPage().getWeatherWidgets().get(0).widgetText().temperature(), format.getRegExpForKelvin());
    }

    @Test
    @TestCaseId("18")
    @Title("Формат вывода температуры в градусах по-кайфу должен соответствовать заданному формату")
    public void kaifTemperatureShouldBeInCorrectFormat(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.clickButton(onMainPage().getWeatherWidgets().get(0).widgetText().temperature());
        defaultSteps.clickButton(onMainPage().getWeatherWidgets().get(0).widgetText().temperature());
        defaultSteps.clickButton(onMainPage().getWeatherWidgets().get(0).widgetText().temperature());
        defaultSteps.shouldMatchToRegExp(onMainPage().getWeatherWidgets().get(0).widgetText().temperature(), format.getRegExpForKaif());
    }

    @Test
    @TestCaseId("19")
    @Title("Формат вывода температуры в градусах Фаренгейта должен соответствовать заданному формату")
    public void farenheitTemperatureShouldBeInCorrectFormat(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.clickButton(onMainPage().getWeatherWidgets().get(0).widgetText().temperature());
        defaultSteps.clickButton(onMainPage().getWeatherWidgets().get(0).widgetText().temperature());
        defaultSteps.shouldMatchToRegExp(onMainPage().getWeatherWidgets().get(0).widgetText().temperature(), format.getRegExpForFarenheit());
    }

    @Test
    @TestCaseId("20")
    @Title("Новый виджет добавляется на первое место")
    public void newWidgetShouldBeFirst(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.clickButton(onMainPage().getAddWidgetButton());
        defaultSteps.strShouldBeInElement(NEW_WIDGET_TITLE,onMainPage().getWeatherWidgets().get(0).widgetTitle().getCity());
    }




    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }
    private FormatVerify format = new FormatVerify();

}
