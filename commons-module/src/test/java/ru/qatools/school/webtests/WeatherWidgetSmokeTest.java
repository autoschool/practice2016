package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.annotations.Title;


public class WeatherWidgetSmokeTest {

    public static final String CITY = "Winnipeg";

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

    private DefaultSteps defaultSteps;

    //@Rule
    //public TPInformerRule tms = new TPInformerRule("thebaldsoprano");

    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();

    @Before
    public void initSteps() { defaultSteps = new DefaultSteps(webDriverRule.getDriver());}

    @Test
    @Title("Should open main page with widget")
    @TestCaseId("2")
    public void shouldOpenMainPageWithWidget(){
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSeeWidget(onMainPage().getWeatherWidgetList().get(0));
    }

    @Test
    @Title("Should see \"add new widget\" button")
    @TestCaseId("3")
    public void shouldSeeAddWidgetButton(){
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSeeAddWidgetButton(onMainPage().getNewWeatherWidgetCard().get(0));
    }

    @Test
    @Title("Should see \"remove widget\" button")
    @TestCaseId("4")
    public void shouldSeeRemoveWidgetButton(){
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSeeRemoveWidgetButton(onMainPage().getWeatherWidgetList().get(0).getWidgetActions().getRemoveWidgetButton());
    }

    @Test
    @Title("\"Add new widget\" button should add widget")
    @TestCaseId("5")
    public void shouldAddNewWidget(){
        defaultSteps.openMainPageWithCity(CITY);
        int buttonPushes = 3;
        defaultSteps.pushNewWeatherButtonNTimes(buttonPushes);
        defaultSteps.shouldSeeNumWidgets(buttonPushes + 1, onMainPage().getWeatherWidgetList());
    }

    @Test
    @Title("Should remove all widgets")
    @TestCaseId("6")
    public void shouldRemoveWidgets(){
        defaultSteps.openMainPageWithCity(CITY);
        int buttonPushes = 1;           //why not work on bigger clicks
        defaultSteps.pushNewWeatherButtonNTimes(buttonPushes);
        defaultSteps.removeNWidgets(buttonPushes+1);
        defaultSteps.shouldBeNoWidgets(onMainPage().getWeatherWidgetList());
    }
}
