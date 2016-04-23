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
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.annotations.Title;

import static ru.yandex.qatools.allure.annotations.*;

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

//    @Rule
//    public TPInformerRule tms = new TPInformerRule("TESTPRJ");

    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
    }

    @Test
    @TestCaseId("1")
    @Title("Должны видеть виджет на главной странице")
    public void shouldSeeWidgetOnMainPage() {
        defaultSteps.openMainPageWithCity(City.MOSCOW.getName());
        defaultSteps.shouldSee(onMainPage().getWeatherWidget().get(0));
    }

    @Test
    @UseDataProvider("getCities")
    @Title("Должный видеть виджет с указанным городом")
    public void shouldSeeWidgetWithCurrentCity(City city){
        defaultSteps.openMainPageWithCity(city.getName());
        defaultSteps.shouldSeeCity(city.getName());
    }

    @Test
    @Title("Должны видеть виджетов на один больше")
    public void shouldSeeWidgetIncrement(){
        defaultSteps.openMainPageWithCity(City.MOSCOW.getName());
        int countWidget = onMainPage().getWeatherWidget().size();
        WebElement newCard = onMainPage().getNewCard();

        defaultSteps.clickOn(newCard);
        defaultSteps.shouldSeeCountWidget(countWidget + 1);
    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

}
