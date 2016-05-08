package ru.qatools.school.webtests;

/* @author arrumm (Arkhipov Roman) */

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.Keys;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.qatools.school.tp.TPInformerRule;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.annotations.Title;

public class WeatherWebTest {

    private static final String CITY = "Tomsk";

    private DefaultSteps defaultSteps;

    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();

    @Rule
    public TPInformerRule tms = new TPInformerRule("arrumm");

    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
    }


    @Test
    @Title("Should see add widget button on the main page (URL without params)")
    @TestCaseId("6")
    public void shouldSeeAddWidgetButtonOnMainPageWithoutParams() {
        defaultSteps.openMainPageWithoutParams();
        defaultSteps.shouldSee(onMainPage().newCardButton());
    }

    @Test
    @Title("Should see widget on the main page")
    @TestCaseId("7")
    public void shouldSeeWidgetOnMainPage() {
        defaultSteps.openMainPageWithCity(null);
        defaultSteps.shouldSee(onMainPage().getFirstWidget());
    }

    @Test
    @Title("Should see picture on widget shows weather")
    @TestCaseId("11")
    public void shouldSeeWeatherPicture() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSee(onMainPage().getFirstWidget().getWidgetText().getWeatherImage());
    }


    @Test
    @Title("City name at widget title should match URL")
    @TestCaseId("1")
    public void shouldSeeWidgetWithCityInURL() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSeeTextInElement(
                onMainPage().getFirstWidget().getWidgetTitle().getCityNameElement(),
                CITY);
    }

    @Test
    @Title("Should see one more widget on the main page after adding")
    @TestCaseId("8")
    public void shouldDetectOnceMoreWidget() {
        defaultSteps.openMainPageWithCity(CITY);
        int widgetsQ = onMainPage().widgets().size();
        defaultSteps.clickOn(onMainPage().newCardButton());
        defaultSteps.shouldBeWidgetsQuantityOnPage(onMainPage().widgets(), widgetsQ + 1);
        defaultSteps.shouldSeeAllFrom(onMainPage().widgets());
    }

    //@Ignore
    @Test
    @Title("Should see widget title")
    @TestCaseId("12")
    public void shouldSeeWidgetTitle() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSee(onMainPage().getFirstWidget());
    }

    @Test
    @Title("Should see temperature on the widget")
    @TestCaseId("10")
    public void shouldSeeTemperatureOnWidget() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSee(onMainPage().getFirstWidget().getWidgetText().getTemperature());
    }

    @Test
    @Title("Should see same city in the title entered by keyboard")
    @TestCaseId("9")
    public void shouldSeeCityInputFromKeyboard() {
        defaultSteps.openMainPageWithCity(null);
        defaultSteps.clickOn(onMainPage().newCardButton());
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetTitle().getCityNameElement());
        defaultSteps.clearIt(onMainPage().getFirstWidget().getWidgetTitle().getCityNameElement());
        defaultSteps.sendKeysTo(onMainPage().getFirstWidget().getWidgetTitle().getCityNameElement(), CITY);
        defaultSteps.sendKeysTo(onMainPage().getFirstWidget().getWidgetTitle().getCityNameElement(), Keys.ENTER);
        defaultSteps.shouldSeeTextInElement(
                onMainPage().getFirstWidget().getWidgetTitle().getCityNameElement(),
                CITY);
    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

}