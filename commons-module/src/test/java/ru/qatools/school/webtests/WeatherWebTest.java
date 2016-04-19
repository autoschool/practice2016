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
        defaultSteps.shouldSee(onMainPage().getWeatherWidgetList().get(0));
    }

    @Test
    @Title("Должны видеть в заголовке виджета запрашиваемый город")
    public void shouldSeeWidgetWithOurCity(){
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSeeText(onMainPage().getWeatherWidgetList().get(0).getWidgetTitle().getCity(), CITY);
    }

    @Test
    @Title("Должны видеть отличное от начального кол-во виджетов")
    public void shouldSeeDifferentCountOfWidgets(){
        defaultSteps.openMainPageWithCity(CITY);
        int widgetCount = onMainPage().getWeatherWidgetList().size();
        defaultSteps.pushButton(onMainPage().getButton());
        defaultSteps.shouldSeeOtherCountOfWidgets(widgetCount + 1);
    }


    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

}
