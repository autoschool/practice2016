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
import ru.qatools.school.pages.PageMethods;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.qatools.school.tp.TPInformerRule;
import ru.yandex.qatools.allure.annotations.Title;

@RunWith(DataProviderRunner.class)
public class WeatherWebTest {

    public static final String MOSCOW = "Moscow";
    public static final String SPB = "Saint Petersburg";
    public static final String PART_OF_CITYNAME = "Saint P";
    public static final String PAGE_TITLE = "Weather";
    public static final String NEW_WIDGET = "What a city?";

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
                {3, DataPatterns.KAIF}
        };
    }

    private DefaultSteps defaultSteps;
    private PageMethods pageMethods;

    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();

    @Rule
    public TPInformerRule tms = new TPInformerRule("ava1on");

    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
        pageMethods = new PageMethods(webDriverRule.getDriver());
    }

    @Test
    @ru.yandex.qatools.allure.annotations.TestCaseId("6")
    @Title("Должны видеть только кнопку [+] ")
    public void shouldSeeOnlyAddWidgetButton(){
        defaultSteps.openMainPageWithoutParameters();
        defaultSteps.shouldSee(onMainPage().getAddNewWidgetButton());
        defaultSteps.shouldHaveWidgetNumberOnMainPage(0);
    }

    @Test
    @ru.yandex.qatools.allure.annotations.TestCaseId("2")
    @Title("Должны видеть виджет на главной странице")
    public void shouldSeeWidgetOnMainPage() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSee(onMainPage().getWeatherWidget().get(0));
    }

    @Test
    @ru.yandex.qatools.allure.annotations.TestCaseId("8")
    @Title("В заголовке виджета должны видеть город, указанный в запросе")
    public void shouldSeeSelectedCityOnWidgetTitle() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSeeRightCityInWidgetsTitle(MOSCOW);
    }

    @Test
    @ru.yandex.qatools.allure.annotations.TestCaseId("16")
    @Title("Должны видеть новый виджет при открытии страницы без параметров")
    public void shouldSeeNewWidgetOnMainPageWithoutParameters() {
        defaultSteps.openMainPageWithCity("");
        defaultSteps.shouldSeeRightCityInWidgetsTitle(NEW_WIDGET);
    }

    @Test
    @ru.yandex.qatools.allure.annotations.TestCaseId("3")
    @Title("Должны видеть на один виджет больше после нажатия на кнопку [+]")
    public void shouldSeeOneMoreWidget() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        int numberOfWidgets = onMainPage().getWeatherWidget().size();
        pageMethods.clickOn(onMainPage().getAddNewWidgetButton());
        defaultSteps.shouldHaveWidgetNumberOnMainPage(numberOfWidgets+1);
    }

    @Test
    @ru.yandex.qatools.allure.annotations.TestCaseId("5")
    @Title("Должны видеть на один виджет меньше после нажатия на кнопку [-]")
    public void shouldSeeLessWidgets(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        int numberOfWidgets = onMainPage().getWeatherWidget().size();
        pageMethods.clickOn(onMainPage().getWeatherWidget().get(0).getRemoveWidgetButton());
        defaultSteps.shouldHaveWidgetNumberOnMainPage(numberOfWidgets-1);
    }

    @Test
    @ru.yandex.qatools.allure.annotations.TestCaseId("9")
    @Title("Должны видеть время и дату в установленном в системе формате")
    public void shouldSeeDateAndTime(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSeeDateEqualToSystemDate();
    }

    @Test
    @ru.yandex.qatools.allure.annotations.TestCaseId("11")
    @Title("Должны видеть новое название города после изменения")
    public void shouldSeeNewCityAfterChangeUsingEnterKey(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.changeWidgetTitle(MOSCOW,SPB);
        defaultSteps.shouldSeeRightCityInWidgetsTitle(SPB);
    }

    @Test
    @ru.yandex.qatools.allure.annotations.TestCaseId("12")
    @Title("Должны видеть список автозаполнения")
    public void shouldSeeSuggestCitiesList(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.suggestList(PART_OF_CITYNAME);
        defaultSteps.shouldSee(onMainPage().getWeatherWidget().get(0).getWidgetTitle().getSuggestedCitiesList());
    }

    @Test
    @ru.yandex.qatools.allure.annotations.TestCaseId("13")
    @Title("В списке автозаполнения должны отображаться только подходящие введенному значению города")
    public void shouldSeeSuitableSuggestedCities(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.suggestList(PART_OF_CITYNAME);
        defaultSteps.suggestedCitiesListItems(PART_OF_CITYNAME);
    }

    @Test
    @ru.yandex.qatools.allure.annotations.TestCaseId("14")
    @Title("Должны изменить город используя список автозаполения")
    public void shouldSeeNewCityAfterChangeUsingSuggestedList(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.suggestList(PART_OF_CITYNAME);
        pageMethods.selectElementFromSuggestedList(SPB);
        defaultSteps.shouldSeeRightCityInWidgetsTitle(SPB);
    }

    @Test
    @UseDataProvider("temperatureFormat")
    @ru.yandex.qatools.allure.annotations.TestCaseId("4")
    @Title("Погодные данные должны отображаться в правильном формате")
    public void shouldSeeTemperatureInCorrectFormat(int id, DataPatterns pattern) {
        defaultSteps.openMainPageWithCity(MOSCOW);
        pageMethods.clickOnSeveralTimes(onMainPage().getWeatherWidget().get(0).getWidgetText().getTemperature(), id);
        defaultSteps.shouldSeeMatchingValueForTemperature(pattern.toString());
    }

    @Test
    @UseDataProvider("weatherDataFormat")
    @ru.yandex.qatools.allure.annotations.TestCaseId("7")
    @Title("Погодные данные должны отображаться в правильном формате")
    public void shouldSeeWeatherDataInCorrectFormat(int id, DataPatterns pattern) {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSeeMatchingValueForWeatherData(id, pattern.toString());
    }

    @Test
    @ru.yandex.qatools.allure.annotations.TestCaseId("10")
    @Title("Должеы видеть правильны заголовок страницы")
    public void shouldSeePageTitle(){
        defaultSteps.openMainPageWithoutParameters();
        defaultSteps.shouldSeeTitle(PAGE_TITLE);
    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }
}
