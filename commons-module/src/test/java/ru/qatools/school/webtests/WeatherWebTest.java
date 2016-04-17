package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import static org.junit.Assert.assertThat;

public class WeatherWebTest {

    public static final String MOSCOW = "Moscow";
    public static final String SPB = "Saint Petersburg";

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
    @Title("Должны увидеть виджет с именем города на главной странице")
    public void shouldSeeWidgetWithTitleCity() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSeeTitleWidgetEqualCity(MOSCOW);
    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

    @Test
    @Title("Должны видеть добавленный виджет на главной странице")
    public void shouldSeeAddWidgetOnMainPage() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSeeWidgetAdd(SPB);
    }

    @Test
    @Title("Должны увидеть виджеты, кроме удаленного на главной странице")
    public void shouldSeeRemoveWidgetOnMainPage() {
        defaultSteps.openMainPageWithCity(SPB);
        defaultSteps.shouldSeeWidgetRemove(SPB);
    }
}

