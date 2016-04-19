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

import java.util.stream.Stream;

@RunWith(DataProviderRunner.class)

/**
 * Created by aasx on 14.04.2016.
 */

public class WidgetWebTest {
    public static WebDriver driver;
    private static DefaultSteps steps;

    @ClassRule
    public static ExternalResource resource = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            driver = new FirefoxDriver();
        }

        @Override
        protected void after() {
            driver.close();
            driver.quit();

        }
    };

    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule(driver);


    @DataProvider
    public static String[] dataProviderCities() {
        return Stream.of(City.values()).map(City::name).toArray(String[]::new);
    }

    @Before
    public void initSteps() {
        steps = new DefaultSteps(driver);
    }

    @Test
    @UseDataProvider("dataProviderCities")
    @Title("Должны видеть виджет на главной странице")
    public void shouldSeeWidgetOnMainPage(String cityToUrl) {
        steps.openMainPageWithCity(cityToUrl);
        steps.shouldSee(onMainPage().getFirstWidget());
    }

    @Test
    @UseDataProvider("dataProviderCities")
    @Title("Должны видеть виджет города, указанного в URL")
    public void expectCityNameEqualsCityNameFromUrl(String cityToUrl) {
        steps.openMainPageWithCity(cityToUrl);
        steps.shouldSeeCorrectCityNameAtWidget(cityToUrl);
    }

    @Test
    @UseDataProvider("dataProviderCities")
    @Title("Отображается на один виджет больше после нажатия на кнопку добавления виджета")
    public void shouldSeeNewWidgetWhenAddWidget(String cityToUrl) {
        steps.openMainPageWithCity(cityToUrl);
        int numberOfWidget = onMainPage().getWeatherWidget().size();
        steps.clickElement(onMainPage().getButtonAddWidget());
        steps.expectNumberOfWeatherWidgets(numberOfWidget + 1);
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }

}
