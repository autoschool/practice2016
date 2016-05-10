package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.yandex.qatools.allure.annotations.Title;

public class WeatherWebTest {

    public static final String CITYNAME = "Moscow";

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
        defaultSteps.openMainPageWithCity(CITYNAME);
        defaultSteps.shouldSee(onMainPage().getWeatherWidget().get(0));
    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

    @Test
    @Title("Заголовок должен соответствовать введенному названию города")
    public void shouldSeeCorrectCityName() {
        defaultSteps.openMainPageWithCity(CITYNAME);
        defaultSteps.shouldSeeCorrectCityNameOnFirstWidget(CITYNAME);
    }

    @Test
    @Title("Должны видеть на 1 виджет больше")
    public void shouldSeeOneMoreWidget() {
        defaultSteps.openMainPageWithCity(CITYNAME);
        int initialAmount = onMainPage().getWeatherWidget().size();
        defaultSteps.addOneMoreWidget();
        defaultSteps.shouldHaveCorrectWidgetAmount(initialAmount + 1);
    }

}
