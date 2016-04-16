package ru.qatools.school.webtests;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.qatools.school.data.City;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(DataProviderRunner.class)

/**
 * Created by aasx on 14.04.2016.
 */
public class WidgetCityTest {
    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();
    private DefaultSteps steps;

    @DataProvider
    public static List<Object> dataProviderCities() {
        return new ArrayList<Object>(Arrays.asList(City.values()));
    }

    @Before
    public void initSteps() {
        steps = new DefaultSteps(webDriverRule.getDriver());
    }

    @Test
    @UseDataProvider("dataProviderCities")
    @Title("Должны видеть виджет города, указанного в URL")
    public void expectCityNameEqualsCityNameFromUrl(City cityToUrl) {
        steps.openMainPageWithCity(cityToUrl.toString());
        steps.shouldSeeCorrectCityNameAtWidget(cityToUrl.toString());
    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

}
