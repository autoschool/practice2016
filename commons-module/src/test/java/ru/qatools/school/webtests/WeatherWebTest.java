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
    @Title("Должны видеть кнопку добавления виджета на главной странице с URL без параметров")
    @TestCaseId("6")
    public void shouldSeeAddWidgetButtonOnMainPageWithoutParams() {
        defaultSteps.openMainPageWithoutParams();
        defaultSteps.shouldSeeElement((WebElement) defaultSteps.getNewCardButton());
    }

    @Test
    @Title("Должны видеть виджет на главной странице")
    @TestCaseId("7")
    public void shouldSeeWidgetOnMainPage() {
        defaultSteps.openMainPageWithCity(null);
        defaultSteps.shouldSeeElement(defaultSteps.getFirstWidget());
    }

    @Test
    @Title("Должны видеть картинку виджета на главной странице")
    @TestCaseId("11")
    public void shouldSeeWeatherPictOnMainPage() {
        defaultSteps.openMainPageWithCity(null);
        defaultSteps.shouldSeeElement(defaultSteps.getFirstWidget().getWidgetText().getWeatherImage());
    }

    @Test
    @Title("Должны видеть поле с температурой на виджете")
    @TestCaseId("10")
    public void shouldSeeTemperatureOnMainPage() {
        defaultSteps.openMainPageWithCity(null);
        defaultSteps.shouldSeeElement(defaultSteps.getFirstWidget().getWidgetText().getTemperature());
    }

    @Test
    @Title("Должны видеть виджет c городом в заголовке таком же, как в URL на главной странице")
    @TestCaseId("1")
    public void shouldSeeWidgetWithCityInURLOnMainPage() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSeeElementTextIsSameToText(
                defaultSteps.getFirstWidget().getWidgetTitle().getCityNameElement(),
                CITY);
    }

    @Test
    @Title("Должны видеть виджетов на главной странице на 1 больше после добавления")
    @TestCaseId("8")
    public void shouldDetectOnceMoreWidgetOnThePage() {
        defaultSteps.openMainPageWithCity(CITY);
        int widgetsQ = onMainPage().getWeatherWidgetList().size();
        defaultSteps.onClick((WebElement) onMainPage().getNewCardButton());
        defaultSteps.shouldBeWidgetsQuantityOnPage(onMainPage().getWeatherWidgetList(), widgetsQ + 1);
        defaultSteps.shouldSeeAllElementFromList(onMainPage().getWeatherWidgetList());
    }

    @Test
    @Title("Должны видеть тот же город в заголовке, который ввели с клавиатуры")
    @TestCaseId("9")
    public void shouldSeeWidgetWithCityWhichEnter() {
        defaultSteps.openMainPageWithCity(null);
        defaultSteps.onClick((WebElement) onMainPage().getNewCardButton());
        defaultSteps.onClick(defaultSteps.getFirstWidget().getWidgetTitle().getCityNameElement());
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