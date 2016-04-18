package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.pages.blocks.WeatherWidget;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.yandex.qatools.allure.annotations.Title;

/**
 * @author kurau
 * @author gladnik (Nikolai Gladkov)
 */
public class WeatherWebTest {

    private static final String DEFAULT_CITY = "Moscow";
    @ClassRule
    public static WebDriverRule webDriverRule = new WebDriverRule();
    private static DefaultSteps defaultSteps;

    @BeforeClass
    public static void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
    }

    @Before
    public void openBlankPage() {
        defaultSteps.openBlankPage();
    }

    @Test
    @Title("Должны видеть виджет на главной странице")
    public void shouldSeeWidgetOnMainPage() {
        defaultSteps.openMainPageWithCity(DEFAULT_CITY);
        defaultSteps.shouldSee(firstWidget());
    }

    @Test
    @Title("В виджете отображается заданный город")
    public void widgetTitleShouldMatchCity() throws InterruptedException {
        defaultSteps.openMainPageWithCity(DEFAULT_CITY);
        defaultSteps.waitASecond();
        defaultSteps.expectText(firstWidget().widgetTitle().cityName(), DEFAULT_CITY);
    }

    @Test
    @Title("Отображается два виджета после нажатия на кнопку добавления виджета")
    public void shouldSeeTwoWidgetsWhenAddOne() {
        defaultSteps.openMainPageWithCity(DEFAULT_CITY);
        defaultSteps.clickOn(onMainPage().getAddWidgetButton());
        defaultSteps.expectSize(onMainPage().allWeatherWidgets(), 2);
        defaultSteps.shouldSee(onMainPage().allWeatherWidgets());
    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

    private WeatherWidget firstWidget() {
        return onMainPage().allWeatherWidgets().get(0);
    }
}
