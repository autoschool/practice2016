package ru.qatools.school.webtests;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import ru.qatools.school.data.City;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.qatools.school.tp.TPInformerRule;
import ru.yandex.qatools.allure.annotations.Title;

import static ru.qatools.school.data.City.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(DataProviderRunner.class)
public class WeatherWebTest {

    private DefaultSteps defaultSteps;

    @DataProvider
    public static List<Object> getCities(){
        ArrayList<Object> cities = new ArrayList<>(Arrays.asList(City.values()));
        return cities;
    }

    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();

    @Rule
    public TPInformerRule tms = new TPInformerRule("OnoDee87");

    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
    }

    @Test
//    @TestCaseId("1")
    @Title("Должны видеть виджет на главной странице")
    public void shouldSeeWidgetOnMainPage() {
        defaultSteps.openMainPageWithCity(MOSCOW.getName());
        defaultSteps.shouldSee(onMainPage().getWeatherWidgets().get(0));
    }

    @Test
//    @TestCaseId("5")
    @UseDataProvider("getCities")
    @Title("Должный видеть виджет с указанным городом")
    public void shouldSeeWidgetWithCurrentCity(City city){
        defaultSteps.openMainPageWithCity(city.getName());
        defaultSteps.shouldSeeCity(city.getName());
    }

    @Test
//    @TestCaseId("4")
    @Title("Должны видеть виджетов на один больше")
    public void shouldSeeWidgetIncrement(){
        defaultSteps.openMainPageWithCity(MOSCOW.getName());
        int countWidget = onMainPage().getWeatherWidgets().size();
        WebElement newCard = onMainPage().getNewCard();

        defaultSteps.clickOn(newCard);
        defaultSteps.shouldSeeCountWidget(countWidget + 1);
    }

    @Test
//    @TestCaseId("6")
    @Title("Должны видеть виджетов на один меньше")
    public void shouldSeeWidgetDecrement(){
        defaultSteps.openMainPageWithCity(MOSCOW.getName());
        int countWidget = onMainPage().getWeatherWidgets().size();
        WebElement removeCard = onMainPage().getWeatherWidgets().get(0).getWidgetActions().getRemoveCard();

        defaultSteps.clickOn(removeCard);
        defaultSteps.shouldSeeCountWidget(countWidget - 1);
    }

    @Test
//    @TestCaseId("7")
    @Title("Должный видеть смену единиц измерения температуры")
    public void shouldSeeChangeTemperatureUnits(){
        defaultSteps.openMainPageWithCity(MOSCOW.getName());
        WebElement temperatureUnit = onMainPage().getWeatherWidgets().get(0).getWidgetText().getTemperatureUnit();

        defaultSteps.clickOn(temperatureUnit);
        defaultSteps.shouldSeeTemperatureIn(temperatureUnit.getText());
    }

    @Test
//    @TestCaseId("8")
    @Title("Должны увидеть смену города в виджете")
    public void shouldSeeChangeCityInWidget(){
        defaultSteps.openMainPageWithCity(MOSCOW.getName());
        defaultSteps.clickOn(onMainPage().getWeatherWidgets().get(0).getWidgetTitle().text());
        defaultSteps.inputText(KALUGA.getName());
        defaultSteps.shouldSeeCity(KALUGA.getName());
    }

    @Test
    @Title("")
    public void shouldSeeScreenshot(){
        defaultSteps.openMainPageWithCity(MOSCOW.getName());
        defaultSteps.takeScreenshot();
        defaultSteps.shouldSeeEqualsImage();
    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

}
