package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.yandex.qatools.allure.annotations.Title;

public class WeatherWebTest {

    private String city = "Moscow";

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
        defaultSteps.openMainPageWithCity(city);
        defaultSteps.shouldSee(onMainPage().getWeatherWidget().get(0));
    }

    @Test
    @Title("Проверяем город в погоде")
    public void shouldSeeChosenCity() {
        defaultSteps.openMainPageWithCity(city);
        defaultSteps.shouldSeeCurrentCity(city);
    }

    @Test
    @Title("Должны видеть на один виджет больше")
    public void shouldSeeNewWidget() {
        defaultSteps.openMainPageWithCity(city);
        int countWidgets = defaultSteps.getCountWidgets();
        defaultSteps.addOneWidget();
        defaultSteps.shouldSeeWidgets(countWidgets+1);
    }



    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

}
