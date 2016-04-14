package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.yandex.qatools.allure.annotations.Title;

public class AddWidgetWebTest {

    public static final String Krasnoyarsk = "Krasnoyarsk";

    private DefaultSteps defaultSteps;

    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();

    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
    }

    @Test
    @Title("Должны видеть два виджета на странице")
    public void shouldSeeWidgetOnMainPage() {
        defaultSteps.openMainPageWithCity(Krasnoyarsk);
        defaultSteps.clickAddWidget();
        defaultSteps.shouldSee(onMainPage().getWeatherWidget().get(0));
        defaultSteps.shouldSee(onMainPage().getWeatherWidget().get(1));
    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

}
