package ru.qatools.school.webtests;

/* @author arrumm (Arkhipov Roman) */

import org.junit.Before;
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
        defaultSteps.shouldSeeElement(onMainPage().getNewCardButton());
    }

    @Test
    @Title("Should see widget on the main page")
    @TestCaseId("7")
    public void shouldSeeWidgetOnMainPage() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSeeElement(onMainPage().getFirstWidget());
    }

    @Test
    @Title("Should see picture on widget shows weather")
    @TestCaseId("11")
    public void shouldSeeWeatherPict() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSeeElement(onMainPage().getFirstWidget().getWidgetText().getWeatherImage());
    }

    @Test
    @Title("City name at widget title match city in URL")
    @TestCaseId("1")
    public void shouldSeeWidgetWithCityInURLOnMainPage() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSeeTextInElement(
                onMainPage().getFirstWidget().getWidgetTitle().getCityNameElement(),
                CITY);
    }

    @Test
    @Title("Should see once more widget on the main page after adding")
    @TestCaseId("8")
    public void shouldDetectOnceMoreWidgetOnThePage() {
        defaultSteps.openMainPageWithCity(CITY);
        int widgetsQ = onMainPage().getWeatherWidgetList().size();
        defaultSteps.clickOn(onMainPage().getNewCardButton());
        defaultSteps.shouldBeWidgetsQuantity(onMainPage().getWeatherWidgetList(), widgetsQ + 1);
        defaultSteps.shouldSeeAllElementFromList(onMainPage().getWeatherWidgetList());
    }

    @Test
    @Title("Should see widget title")
    @TestCaseId("12")
    public void shouldSeeWidgetTitle() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSeeElement(onMainPage().getFirstWidget().getWidgetTitle());
    }

    @Test
    @Title("Should see temperature on the widget")
    @TestCaseId("10")
    public void shouldSeeTemperatureOnWidget() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSeeElement(onMainPage().getFirstWidget().getWidgetText().getTemperature());
    }

    @Test
    @Title("Should see city in the widget entered by keyboard")
    @TestCaseId("9")
    public void shouldSeeCityInputFromKeyboard() {
        defaultSteps.openMainPageWithCity(null);
        defaultSteps.clickOn(onMainPage().getNewCardButton());
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetTitle().getCityNameElement());
        defaultSteps.clearIt(onMainPage().getFirstWidget().getWidgetTitle().getCityNameElement());
        defaultSteps.sendKeysToElement(onMainPage().getFirstWidget().getWidgetTitle().getCityNameElement(), CITY);
        defaultSteps.sendKeysToElement(onMainPage().getFirstWidget().getWidgetTitle().getCityNameElement(), Keys.ENTER);
        defaultSteps.shouldSeeTextInElement(
                onMainPage().getFirstWidget().getWidgetTitle().getCityNameElement(),
                CITY);
    }


    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

}