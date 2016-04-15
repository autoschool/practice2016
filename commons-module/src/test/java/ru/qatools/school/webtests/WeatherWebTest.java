package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.yandex.qatools.allure.annotations.Title;

public class WeatherWebTest {

    public static final String MOSCOW = "Moscow";

    private DefaultSteps defaultSteps;

    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();

    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
    }

    @Test
    @Title("Widget on main page must be seen")
    public void shouldSeeWidgetOnMainPage() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldBeSeen(onMainPage().getWeatherWidgets().get(0));
    }

    @Test
    @Title("City from widget must match city from URL")
    public void shouldWidgetCityBeLinkCity() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldBeWeatherOfLinkCity(MOSCOW, onMainPage().getWeatherWidgets().get(0).getWidgetTitle().getWidgetCity());
    }

    @Test
    @Title("Widget must be added after pushing \"add new widget\" button")
    public void shouldWidgetBeAdded() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        int buttonTimesPushed = 5;
        defaultSteps.pushNewWeatherButtonNTimes(buttonTimesPushed);
        defaultSteps.shouldExistAsMuchWidgetsAs(buttonTimesPushed + 1, onMainPage().getWeatherWidgets());
        defaultSteps.shouldBeSeen(onMainPage().getWeatherWidgets());
    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

}
