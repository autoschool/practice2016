package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.annotations.Features;

public class WeatherWebTest {

    public static final String CITY = "Krasnoyarsk";

    private DefaultSteps defaultSteps;

    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();

    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
    }

    @Test
    @Title("Должен быть виден виджет на главной странице")
    public void shouldSeeWidgetOnMainPage() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSee(onMainPage().getFirstWidget());
    }

    @Test
    @Title("Должен быть виден виджет для переданного города")
    public void shouldSeeCityFromGETQuery() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSeeText(onMainPage().getFirstWidget().getWidgetTitle().getCityName(), CITY);
    }

    @Test
    @Title("Должны быть видны два виджета на странице")
    public void shouldSeeTwoWidgetsAfterAddOne() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.clickOn(onMainPage().getNewWidgetButton());
        defaultSteps.shouldHaveSize(onMainPage().getWeatherWidgets(), 2);
        defaultSteps.shouldSee(onMainPage().getWeatherWidgets());
    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

}
