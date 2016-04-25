package ru.qatools.school.webtests;

/* @author arrumm (Arkhipov Roman) */

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.qatools.school.tp.TPInformerRule;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.annotations.TestCaseId;

import java.util.List;

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
        defaultSteps.shouldSeeElement(defaultSteps.getNewCardButton());
    }

    @Test
    @Title("Should see widget on the main page")
    @TestCaseId("7")
    public void shouldSeeWidgetOnMainPage() {
        defaultSteps.openMainPageWithCity(null);
        defaultSteps.shouldSeeElement(defaultSteps.getFirstWidget());
    }

    @Test
    @Title("Should see picture on widget shows weather")
    @TestCaseId("11")
    public void shouldSeeWeatherPictOnMainPage() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSeeElement(defaultSteps.getFirstWidget().getWidgetText().getWeatherImage());
    }



    @Test
    @Title("Should see city name at widget title like in URL on the main page")
    @TestCaseId("1")
    public void shouldSeeWidgetWithCityInURLOnMainPage() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSeeElementTextIsSameToText(
                defaultSteps.getFirstWidget().getWidgetTitle().getCityNameElement(),
                CITY);
    }

    @Test
    @Title("Should see +1 widget on da main page after adding")
    @TestCaseId("8")
    public void shouldDetectOnceMoreWidgetOnThePage() {
        defaultSteps.openMainPageWithCity(CITY);
        int widgetsQ = onMainPage().getWeatherWidgetList().size();
        defaultSteps.onClickElement(onMainPage().getNewCardButton());
        defaultSteps.shouldBeWidgetsQuantityOnPage(onMainPage().getWeatherWidgetList(), widgetsQ + 1);
        defaultSteps.shouldSeeAllElementFromList(onMainPage().getWeatherWidgetList());
    }

    @Ignore
    @Test
    @Title("Should see widget title")
    @TestCaseId("12")
    public void shouldSeeWidgetTitle() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSeeElement(defaultSteps.getFirstWidget());
    }

    @Test
    @Title("Should see field on the widget shows temperature")
    @TestCaseId("10")
    public void shouldSeeTemperatureOnMainPage() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSeeElement(defaultSteps.getFirstWidget().getWidgetText().getTemperature());
    }

    @Test
    @Title("Should see same city in the title as entered by kboard")
    @TestCaseId("9")
    public void shouldSeeWidgetWithCityWhichEnter() {
        defaultSteps.openMainPageWithCity(null);
        defaultSteps.onClickElement(onMainPage().getNewCardButton());
        defaultSteps.onClickElement(defaultSteps.getFirstWidget().getWidgetTitle().getCityNameElement());
        defaultSteps.getFirstWidget().getWidgetTitle().getCityNameElement().clear();
        defaultSteps.getFirstWidget().getWidgetTitle().getCityNameElement().sendKeys(CITY);
        defaultSteps.getFirstWidget().getWidgetTitle().getCityNameElement().sendKeys(Keys.ENTER);
        defaultSteps.shouldSeeElementTextIsSameToText(
                defaultSteps.getFirstWidget().getWidgetTitle().getCityNameElement(),
                CITY);
    }


    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

}