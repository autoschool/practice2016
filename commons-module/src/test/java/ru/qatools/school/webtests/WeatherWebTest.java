package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
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

    @Test
    @Title("Должны видеть виджет на главной странице")
    public void shouldSeeWidgetOnMainPage() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSee(onMainPage().getWeatherWidget().get(0));
    }

    @Test
    @Title("Должны видеть заданный город в виджете на главной странице")
    public void verifyCityInWidgetOnMainPage() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSeeCityInWidget(onMainPage().getWeatherWidget().get(0)
                .getWidgetTitle().getCityName(), CITY);
    }

    @Test
    @Title("После клика по кнопке добавления должны видеть 2 виджета на главной странице")
    public void shouldSee2WidgetsOnMainPage() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.clickElement(onMainPage().getButtonAddWidget());
        defaultSteps.shouldSee(onMainPage().getWeatherWidget().get(0));
        defaultSteps.shouldSee(onMainPage().getWeatherWidget().get(0));
    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

}
