package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.yandex.qatools.allure.annotations.Title;

public class WeatherWebTest {

    public static final String MOSCOW = "Moscow";
    public static final String NEMOSCOW = "Omsk";


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
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSee(onMainPage().getWeatherWidget().get(0));
    }

    @Test
    @Title("Должны видеть в заголовке искомый город")
    public void shouldBeTrueCity() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.cityShoudBe(NEMOSCOW);
    }

    @Test
    @Ignore
    @Title("Должны получить ошибку по несоответствию города")
    public void shouldBeFalseCity() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.cityShoudBe(NEMOSCOW);
    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

}
