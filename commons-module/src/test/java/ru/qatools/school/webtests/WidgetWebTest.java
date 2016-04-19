package ru.qatools.school.webtests;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.qatools.school.data.City;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@RunWith(DataProviderRunner.class)

/**
 * Created by aasx on 14.04.2016.
 */
public class WidgetWebTest {
    public static final String MOSCOW = "Moscow";

    @DataProvider
   /* public static List<String> dataProviderCities() {
        return new ArrayList<String>(Arrays.asList(City.values()));
    } */
    public static String[] dataProviderCities() {
        return Stream.of(City.values()).map(City::name).toArray(String[]::new);
    }
    @Rule
    public WebDriverRule webDriverRule=new WebDriverRule();
    private static DefaultSteps steps;


    @Before
    public void initSteps() { steps=new DefaultSteps(webDriverRule.getDriver()); }


    @Test
    @UseDataProvider("dataProviderCities")
    @Title("Должны видеть виджет на главной странице")
    public void shouldSeeWidgetOnMainPage(String cityToUrl) {
        steps.openMainPageWithCity(cityToUrl);
        steps.shouldSee(onMainPage().getWeatherWidget().get(0));
    }

    @Test
    @UseDataProvider("dataProviderCities")
    @Title("Должны видеть виджет города [«{0}»], указанного в URL")
    public void expectCityNameEqualsCityNameFromUrl(String cityToUrl) {
        steps.openMainPageWithCity(cityToUrl);
        steps.shouldSeeCorrectCityNameAtWidget(cityToUrl);
    }

    @Test
    @UseDataProvider("dataProviderCities")
    @Title("Отображается на один виджет больше после нажатия на кнопку добавления виджета")
    public void shouldSeeNTimesMoreWidgetsWhenAddNWidgets(String cityToUrl){
        steps.openMainPageWithCity(cityToUrl);
        steps.clickElement(onMainPage().getButtonAddWidget());
        steps.shouldSee(onMainPage().getWeatherWidget().get(0));
        steps.shouldSee(onMainPage().getWeatherWidget().get(1));
    }

    private MainPage onMainPage() {return new MainPage(webDriverRule.getDriver());}

}
