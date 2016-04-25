package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.qatools.school.tp.TPInformerRule;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.annotations.Title;

/**
 * Created by onegines (Eugene Kirienko)
 */
public class SmokeSuite {

    private static final String CITY = "Saint Petersburg";
    private static final String CITY2 = "Moscow";
    private static final String CITY2_BEGIN = "Mosc";

    private DefaultSteps defaultSteps;

    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();

    @Rule
    public TPInformerRule tms = new TPInformerRule("onegines");

    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

    @Test
    @Title("Должна открыться требуемая страница")
    @TestCaseId("6")
    public void shouldBeOnCorrectPage() {
        defaultSteps.openMainPage();
        defaultSteps.shouldBeUrl("http://weather.lanwen.ru/");
        defaultSteps.shouldBeTitle("Weather");
    }

    @Test
    @Title("Должны видеть кнопку '+' на странице, открытой без параметров")
    @TestCaseId("5")
    public void shouldSeeAddWidgetButtonOnPageWithNoQuery() {
        defaultSteps.openMainPage();
        defaultSteps.shouldSee(onMainPage().getAddWidgetButton());
    }

    @Test
    @Title("Должны видеть кнопку '+' на странице, открытой с указанием города в в параметрах")
    @TestCaseId("7")
    public void shouldSeeAddWidgetButtonOnPageWithQuery() {
        defaultSteps.openMainPageWithCities(CITY);
        defaultSteps.shouldSee(onMainPage().getAddWidgetButton());
    }

    @Test
    @Title("Должны видеть виджет города из запроса")
    @TestCaseId("8")
    public void shouldSeeWidgetFromQuery() {
        defaultSteps.openMainPageWithCities(CITY);
        defaultSteps.shouldHaveText(onMainPage().getFirstWidget().getWidgetTitle().getCityName(), CITY);
    }

    @Test
    @Title("Должны видеть все компоненты виджета")
    @TestCaseId("9")
    public void shouldSeeAllWidgetBlocks() {
        defaultSteps.openMainPageWithCities(CITY);

        defaultSteps.shouldSee(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.shouldSee(onMainPage().getFirstWidget().getWidgetTitle().getCurrentTimeAndData());

        defaultSteps.shouldSee(onMainPage().getFirstWidget().getWidgetText().getWeatherImage());
        defaultSteps.shouldSee(onMainPage().getFirstWidget().getWidgetText().getWeatherTemperatureDigit());
        defaultSteps.shouldSee(onMainPage().getFirstWidget().getWidgetText().getWeatherTemperatureUnit());

        defaultSteps.shouldBeThisNumberOfElements(onMainPage().getFirstWidget().getWidgetText().getInfoLines(), 4);

        defaultSteps.shouldSee(onMainPage().getFirstWidget().getWidgetText().getSunriseLine().getTitle());
        defaultSteps.shouldSee(onMainPage().getFirstWidget().getWidgetText().getSunriseLine().getImage());
        defaultSteps.shouldSee(onMainPage().getFirstWidget().getWidgetText().getSunriseLine().getValue());

        defaultSteps.shouldSee(onMainPage().getFirstWidget().getWidgetText().getSunsetLine().getTitle());
        defaultSteps.shouldSee(onMainPage().getFirstWidget().getWidgetText().getSunsetLine().getImage());
        defaultSteps.shouldSee(onMainPage().getFirstWidget().getWidgetText().getSunsetLine().getValue());

        defaultSteps.shouldSee(onMainPage().getFirstWidget().getWidgetText().getWindLine().getTitle());
        defaultSteps.shouldSee(onMainPage().getFirstWidget().getWidgetText().getWindLine().getImage());
        defaultSteps.shouldSee(onMainPage().getFirstWidget().getWidgetText().getWindLine().getValue());

        defaultSteps.shouldSee(onMainPage().getFirstWidget().getWidgetText().getHumidityLine().getTitle());
        defaultSteps.shouldSee(onMainPage().getFirstWidget().getWidgetText().getHumidityLine().getImage());
        defaultSteps.shouldSee(onMainPage().getFirstWidget().getWidgetText().getHumidityLine().getValue());

        defaultSteps.shouldSee(onMainPage().getFirstWidget().getWidgetActions().getRemoveWidgetButton());
    }

    @Test
    @Title("Должны видеть градусы Цельсия в виджете на странице, открытой с указанием города в параметрах")
    @TestCaseId("19")
    public void shouldSeeCelsiusInWidgetOnMainPage() {
        defaultSteps.openMainPageWithCities(CITY);
        defaultSteps.shouldHaveText(onMainPage().getFirstWidget().getWidgetText().getWeatherTemperatureUnit(), "°C");
    }

    @Test
    @Title("Должен добавиться новый виджет после щелчка на кнопке '+'")
    @TestCaseId("4")
    public void shouldBeAddedNewWidget() {
        defaultSteps.openMainPageWithCities(CITY);
        int numberOfWidgets = onMainPage().getWeatherWidgets().size();
        defaultSteps.clickOn(onMainPage().getAddWidgetButton());
        defaultSteps.shouldBeThisNumberOfElements(onMainPage().getWeatherWidgets(), numberOfWidgets + 1);
    }

    @Test
    @Title("Должен измениться город в первом виджете после ввода полного названия города и Return")
    @TestCaseId("10")
    public void shouldBeRenamedFirstWidgetAfterEnterFullName() {
        defaultSteps.openMainPageWithCities(CITY);
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.eraseText(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.enterText(onMainPage().getFirstWidget().getWidgetTitle().getCityName(), CITY2);
        defaultSteps.confirmText(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.waitUntilElementReady(onMainPage().getFirstWidget().getWidgetTitle().getCityName(), 3);
        defaultSteps.shouldHaveText(onMainPage().getFirstWidget().getWidgetTitle().getCityName(), CITY2);
    }

    @Test
    @Title("Должен удалиться виджет после нажатия на кнопку удаления")
    @TestCaseId("11")
    public void shouldBeDeletedOneWidget() {
        defaultSteps.openMainPageWithCities(CITY, CITY2);
        int numberOfWidgets = onMainPage().getWeatherWidgets().size();
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetActions().getRemoveWidgetButton());
        defaultSteps.shouldBeThisNumberOfElements(onMainPage().getWeatherWidgets(), numberOfWidgets - 1);
    }
}
