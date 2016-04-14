package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.yandex.qatools.allure.annotations.Title;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;


@RunWith(DataProviderRunner.class)

public class TitleTest {

    @DataProvider
    public static Object[][] dataProviderCity() {
        return new Object[][] {
                { "Moscow"},
                { "Kiev"},
                { "Berlin"},
                { "New York"}
        };
    }

    private DefaultSteps defaultSteps;

    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();

    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
    }

    @Test
    @Title("В названии погоды должен быть город «{0}»")
    @UseDataProvider("dataProviderCity")
    public void titleShouldBeCity(String city) {
        defaultSteps.openMainPageWithCity(city);
        defaultSteps.titleShouldBe(onMainPage().getWeatherWidget().get(0).getWidgetTitle(), city);
    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

}
