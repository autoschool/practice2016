package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Title;

public class WeatherWebTest {

    public static final String CITY = "Moscow";

    private DefaultSteps defaultSteps;

    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();

    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
    }

    @Features("Веб-тесты")
    @Test
    @Title("Должны видеть виджет на главной странице")
    public void shouldSeeWidgetOnMainPage() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSeeElement(onMainPage().getWeatherWidgetList().get(0));
    }

    @Features("Веб-тесты")
    @Test
    @Title("Должен отображаться город, переданный в запросе")
    public void shouldSeeCityFromGETQuery() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSeeCityName(onMainPage().getWeatherWidgetList().get(0).getWidgetTitle(), CITY);
    }

    @Features("Веб-тесты")
    @Test
    @Title("Должен добавляться еще один виджет")
    public void shouldBeAbleToAddWidget() {
        defaultSteps.openMainPageWithCity(CITY);
        int initialNumberOfWidgets = onMainPage().getWeatherWidgetList().size();
        defaultSteps.pressAddWidgetButton();
        defaultSteps.shouldHaveWidgetNumber(initialNumberOfWidgets + 1);
        defaultSteps.shouldSeeAllElements(onMainPage().getWeatherWidgetList());
    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

}
