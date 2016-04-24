package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.qatools.school.tp.TPInformerRule;
import ru.yandex.qatools.allure.annotations.Title;

public class WeatherWebTest {

    public static final String MOSCOW = "Moscow";

    private DefaultSteps defaultSteps;

    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();

    @Rule
    public TPInformerRule tms = new TPInformerRule("ava1on");

    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
    }

    @Test
    @ru.yandex.qatools.allure.annotations.TestCaseId("6")
    public void shouldSeeOnlyAddWidgetButton(){
        defaultSteps.openMainPageWithoutParameters();
        defaultSteps.shouldSee(onMainPage().getAddNewWidgetButton());
        defaultSteps.shouldHaveWidgetNumberOnMainPage(0);
    }

    @Test
    @ru.yandex.qatools.allure.annotations.TestCaseId("2")
    @Title("Должны видеть виджет на главной странице")
    public void shouldSeeWidgetOnMainPage() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSee(onMainPage().getWeatherWidget().get(0));
    }

    @Test
    @ru.yandex.qatools.allure.annotations.TestCaseId("8")
    @Title("В заголовке виджета должны видеть город, указанный в запросе")
    public void shouldSeeSelectedCityOnWidgetTitle() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSeeRightCityInWidgetsTitle(MOSCOW);
    }

    @Test
    @ru.yandex.qatools.allure.annotations.TestCaseId("3")
    @Title("Должны видеть на один виджет больше после нажатия на кнопку [+]")
    public void shouldSeeOneMoreWidget() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        int numberOfWidgets = onMainPage().getWeatherWidget().size();
        defaultSteps.clickOn(onMainPage().getAddNewWidgetButton());
        defaultSteps.shouldHaveWidgetNumberOnMainPage(numberOfWidgets+1);
    }

    @Test
    @ru.yandex.qatools.allure.annotations.TestCaseId("5")
    @Title("Должны видеть на один виджет меньше после нажатия на кнопку [-]")
    public void shouldSeeLessWidgets(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        int numberOfWidgets = onMainPage().getWeatherWidget().size();
        defaultSteps.clickOn(onMainPage().getWeatherWidget().get(0).getRemoveWidgetButton());
        defaultSteps.shouldHaveWidgetNumberOnMainPage(numberOfWidgets-1);
    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

}
