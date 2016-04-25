package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.yandex.qatools.allure.annotations.Title;

public class WeatherWebTest {

    public static final String CITI = "Moscow";

    private DefaultSteps defaultSteps;

    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();

    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
    }

    @Test
    @Title("Должны видеть хотя бы один виджет на главной странице")
    public void shouldSeeWidgetOnMainPage() {
        defaultSteps.openMainPageWithCity(CITI);
        defaultSteps.shouldSee(onMainPage().getWeatherWidgetList().get(0));
    }

    @Test
    @Title("Должны видеть виджет c правильным городом на главной странице")
    public void shouldSeeCorrectCityName() {
        defaultSteps.openMainPageWithCity(CITI);
        defaultSteps.shouldSeeCorrectCityName(CITI);
    }

    @Test
    @Title("Виджетов должно стать на один больше")
    public void shouldSeeOneMoreWidget() {
        defaultSteps.openMainPageWithCity(CITI);
        int originCount = onMainPage().getWeatherWidgetList().size();
        defaultSteps.addWidgit();
        defaultSteps.widgetCountShouldBe(originCount + 1);

    }
    @Test
    @Title("Виджетов должно стать на 100 больше")
    public void shouldSeeOneHungredMoreWidget() {
        defaultSteps.openMainPageWithCity(CITI);
        int originCount = onMainPage().getWeatherWidgetList().size();
        for (int i = 0; i < 100; i++) {
            defaultSteps.addWidgit();
        }
        defaultSteps.widgetCountShouldBe(originCount + 100);

    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

}