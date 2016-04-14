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
    @Title("В заголовке виджета должны видеть город, указанный в ссылке")
    public void shouldSeeSelectedCityOnWidgetTitle(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSeeRightCityInWidgetsTitle(MOSCOW);
    }

    @Test
    @Title("Должны видеть на один виджет больше после нажатия на кнопку +")
    public void shouldSeeOneMoreWidget(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSeeNewAddedWidget(onMainPage());
    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

}
