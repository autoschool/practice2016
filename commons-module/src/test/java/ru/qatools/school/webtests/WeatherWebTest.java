package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.yandex.qatools.allure.annotations.Title;

public class WeatherWebTest {

    public static final String MOSCOW = "Moscow";
    public static final String PITER = "Saint Petersburg";

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
    @Title("Город в виджете должен совпадать с городом в URL'e")
    public void shouldSeeCityOnMainPage() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldBeWidgetWithCity(MOSCOW);
        defaultSteps.shouldSee(onMainPage().getWeatherWidget().get(0));
    }

    @Test
    @Title("Должен добавить новый виджет, город нового виджета должен совпадать с городом в URL'e")
    public void shouldAddNewWidgetOnMainPage() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        int beforeAddWidget = onMainPage().getWeatherWidget().size();
        defaultSteps.addNewWidgetWithCity(PITER);
        int afterAddWidget = onMainPage().getWeatherWidget().size();
        defaultSteps.shouldBeOneMoreWidget(beforeAddWidget, afterAddWidget);
        defaultSteps.shouldBeWidgetWithCity(PITER);
        defaultSteps.shouldSee(onMainPage().getWeatherWidget().get(0));
    }


    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

}
