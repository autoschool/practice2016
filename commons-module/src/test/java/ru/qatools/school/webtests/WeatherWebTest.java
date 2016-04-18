package ru.qatools.school.webtests;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import ru.qatools.school.data.City;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.yandex.qatools.allure.annotations.Title;

import ru.qatools.school.data.City;

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

    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
        defaultSteps.openMainPageWithCity(City.MOSCOW.getName());
    }


    @Test
    @Title("Должны видеть виджет на главной странице")
    public void shouldSeeWidgetOnMainPage() {
        defaultSteps.shouldSee(onMainPage().getWeatherWidget().get(0));
    }

    @Ignore
    @Test
    @UseDataProvider("getCities")
    @Title("Должный видеть виджет с указанным городом")
    public void shouldSeeWidgetWithCurrentCity(City city){
        defaultSteps.shouldSeeCity(city.getName());
    }

    @Test
    @Title("Должны видеть виджетов на один больше")
    public void shouldSeeWidgetIncrement(){
        int countWidget = onMainPage().getWeatherWidget().size();
        WebElement newCard = onMainPage().getNewCard();
        defaultSteps.clickOn(newCard);
        defaultSteps.shouldSeeCountWidget(countWidget + 1);
    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

}
