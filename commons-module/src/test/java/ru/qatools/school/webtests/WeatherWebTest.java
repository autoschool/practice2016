package ru.qatools.school.webtests;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.qatools.school.data.DataPatterns;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.pages.blocks.WeatherWidget;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.qatools.school.tp.TPInformerRule;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.annotations.Title;

@RunWith(DataProviderRunner.class)
public class WeatherWebTest {

    private static final String MOSCOW = "Moscow";
    private static final String SPB = "Saint Petersburg";
    private static final String PART_OF_CITYNAME = "Saint P";
    private static final String PAGE_TITLE = "Weather";
    private static final String NEW_WIDGET = "What a city?";

    @DataProvider
    public static Object[][] weatherDataFormat() {
        return new Object[][]{
                {0, DataPatterns.SUNRISE},
                {1, DataPatterns.SUNSET},
                {2, DataPatterns.WIND},
                {3, DataPatterns.HUMIDITY}
        };
    }

    @DataProvider
    public static Object[][] temperatureFormat(){
        return new Object[][]{
                {0, DataPatterns.CELSIUS},
                {1, DataPatterns.KELVIN},
                {2, DataPatterns.FAHRENHEIT},
                {3, DataPatterns.KAIF},
                {4, DataPatterns.CELSIUS}
        };
    }

    private DefaultSteps defaultSteps;

    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();

    @Rule
    public TPInformerRule tms = new TPInformerRule("ava1on");

    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
    }

    @Test
    //@TestCaseId("6")
    @Title("Должны видеть только кнопку [+] ")
    public void shouldSeeOnlyAddWidgetButton(){
        defaultSteps.openMainPageWithoutParameters();
        defaultSteps.shouldSee(onMainPage().getAddNewWidgetButton());
        defaultSteps.shouldHaveWidgetNumberOnMainPage(0);
    }

    @Test
    //@TestCaseId("2")
    @Title("Должны видеть виджет на главной странице")
    public void shouldSeeWidgetOnMainPage() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSee(getFirstWidget());
    }

    @Test
    //@TestCaseId("8")
    @Title("В заголовке виджета должны видеть город, указанный в запросе")
    public void shouldSeeSelectedCityOnWidgetTitle() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSeeCityInWidgetsTitle(MOSCOW);
    }

    @Test
    //@TestCaseId("16")
    @Title("Должны видеть новый виджет при открытии страницы без параметров")
    public void shouldSeeNewWidgetOnMainPageWithoutParameters() {
        defaultSteps.openMainPageWithCity("");
        defaultSteps.shouldSeeCityInWidgetsTitle(NEW_WIDGET);
    }

    @Test
    //@TestCaseId("3")
    @Title("Должны видеть на один виджет больше после нажатия на кнопку [+]")
    public void shouldSeeOneMoreWidget() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        int numberOfWidgets = onMainPage().getWeatherWidgets().size();
        defaultSteps.clickOn(onMainPage().getAddNewWidgetButton());
        defaultSteps.shouldHaveWidgetNumberOnMainPage(numberOfWidgets+1);
    }

    @Test
    //@TestCaseId("5")
    @Title("Должны видеть на один виджет меньше после нажатия на кнопку [-]")
    public void shouldSeeLessWidgets(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        int numberOfWidgets = onMainPage().getWeatherWidgets().size();
        defaultSteps.clickOn(getFirstWidget().getRemoveWidgetButton());
        defaultSteps.shouldHaveWidgetNumberOnMainPage(numberOfWidgets-1);
    }

    @Test
    //@TestCaseId("9")
    @Title("Должны видеть время и дату в формате \"Ч AM/PM, дд ммм гг\"")
    public void shouldSeeDateAndTime(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldMatchPattern(getFirstWidget().getWidgetTitle().getCurrentTime(), DataPatterns.TIME_DATE);
    }

    @Test
    //@TestCaseId("11")
    @Title("Должны видеть новое название города после изменения")
    public void shouldSeeNewCityAfterChangeUsingEnterKey(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.changeWidgetTitle(getFirstWidget(), SPB);
        defaultSteps.shouldSeeCityInWidgetsTitle(SPB);
    }

    @Test
    //@TestCaseId("12")
    @Title("Должны видеть список автозаполнения")
    public void shouldSeeSuggestCitiesList(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.suggestList(PART_OF_CITYNAME, getFirstWidget().getWidgetTitle());
        defaultSteps.shouldSee(getFirstWidget().getWidgetTitle().getSuggestedCitiesList());
    }

    @Test
    //@TestCaseId("13")
    @Title("В списке автозаполнения должны отображаться только города, содержащие введенную строку")
    public void shouldSeeSuitableSuggestedCities(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.suggestList(PART_OF_CITYNAME, getFirstWidget().getWidgetTitle());
        defaultSteps.shouldOnlySeeCitiesContaining(PART_OF_CITYNAME);
    }

    @Test
    //@TestCaseId("14")
    @Title("Должны изменить город используя список автозаполения")
    public void shouldSeeNewCityAfterChangeUsingSuggestedList(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.suggestList(PART_OF_CITYNAME, getFirstWidget().getWidgetTitle());
        defaultSteps.selectItemFromSuggestedList(SPB);
        defaultSteps.shouldSeeCityInWidgetsTitle(SPB);
    }

    @Test
    @UseDataProvider("temperatureFormat")
    //@TestCaseId("4")
    @Title("Температура должна отображатся в правильном формате")
    public void shouldSeeTemperature(int numberOfClicks, DataPatterns pattern) {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.clickOnSeveralTimes(getFirstWidget().getWidgetText().getTemperature(), numberOfClicks);
        defaultSteps.shouldMatchPattern(getFirstWidget().getWidgetText().getTemperature(), pattern);
    }

    @Test
    @UseDataProvider("weatherDataFormat")
    //@TestCaseId("7")
    @Title("Погодные данные должны отображаться в правильном формате")
    public void shouldSeeWeatherData(int id, DataPatterns pattern) {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldMatchPattern(getFirstWidget().getWidgetText().getWeatherData().get(id), pattern);
    }

    @Test
    //@TestCaseId("10")
    @Title("Должны видеть заголовок страницы")
    public void shouldSeePageTitle(){
        defaultSteps.openMainPageWithoutParameters();
        defaultSteps.shouldSeeTitle(PAGE_TITLE);
    }

    @Test
    //@TestCaseId("23")
    @Title("Должны видеть иконку погоды")
    public void shouldSeeWeatherImage(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSee(getFirstWidget().getWidgetText().getWeatherImage());
    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

    private WeatherWidget getFirstWidget() {
        return onMainPage().getWeatherWidgets().get(0);
    }
}