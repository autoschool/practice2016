package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.yandex.qatools.allure.annotations.Title;

/**
 * @author kurau
 * @author gladnik (Nikolai Gladkov)
 */
public class WeatherWebTest {

    private static final String MOSCOW = "Moscow";
    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();
    private DefaultSteps defaultSteps;

    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
    }

    @Test
    @Title("Должны видеть виджет на главной странице")
    public void shouldSeeWidgetOnMainPage() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSee(onMainPage().getWeatherWidget().get(0));
    }

    @Test
    @Title("В виджете отображается заданный город")
    public void widgetTitleShouldMatchCity() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.expectText(onMainPage().getWeatherWidget().get(0)
                .getWidgetTitle().getCityName(), MOSCOW);
    }

    @Test
    @Title("Отображается два виджета после нажатия на кнопку добавления виджета")
    public void shouldSeeTwoWidgetsWhenAddOne() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.clickElement(onMainPage().getButtonAddWidget());
        defaultSteps.shouldSee(onMainPage().getWeatherWidget().get(0));
        defaultSteps.shouldSee(onMainPage().getWeatherWidget().get(1));
    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }
}
