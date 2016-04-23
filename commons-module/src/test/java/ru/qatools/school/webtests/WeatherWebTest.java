package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.qatools.school.tp.TPInformerRule;
import ru.yandex.qatools.allure.annotations.Title;

/**
 * Created by kurau.
 * Changed by onegines (Eugene Kirienko).
 */
public class WeatherWebTest {

    private static final String CITY = "Saint Petersburg";
    private static final String CITY2 = "Moscow";

    private DefaultSteps defaultSteps;

    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();

    @Rule
    public TPInformerRule tms = new TPInformerRule("onegines");

    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
    }

    @Test
    @Title("Должна открыться нужная страница")
//    @ru.yandex.qatools.allure.annotations.TestCaseId("NUM")
    public void shouldBeOnCorrectPage() {
        defaultSteps.openMainPage();
        defaultSteps.shouldBeUrl("http://weather.lanwen.ru/");
        defaultSteps.shouldBeTitle("Weather");
    }

    @Test
    @Title("Должны видеть кнопку '+' на странице, открытой без параметров")
//    @ru.yandex.qatools.allure.annotations.TestCaseId("NUM")
    public void shouldSeeAddWidgetButtonOnPageWithNoQuery() {
        defaultSteps.openMainPage();
        defaultSteps.shouldSee(onMainPage().getAddWidgetButton());
    }

    @Test
    @Title("Должны видеть кнопку '+' на странице, открытой с указанием городов в параметрах")
//    @ru.yandex.qatools.allure.annotations.TestCaseId("NUM")
    public void shouldSeeAddWidgetButtonOnPageWithQuery() {
        defaultSteps.openMainPageWithCities(CITY);
        defaultSteps.shouldSee(onMainPage().getAddWidgetButton());
    }

    @Test
    @Title("Должны видеть два виджета на главной странице после загрузки")
    public void shouldSeeTwoWidgetsOnMainPage() {
        defaultSteps.openMainPageWithCities(CITY, CITY2);
        defaultSteps.shouldBeThisNumberOfElements(onMainPage().getWeatherWidgets(), 2);
        defaultSteps.shouldSee(onMainPage().getWeatherWidgets());
    }

    @Test
    @Title("Должны видеть виджет города из запроса")
    public void shouldSeeWidgetFromQuery() {
        defaultSteps.openMainPageWithCities(CITY);
        defaultSteps.shouldHaveText(onMainPage().getFirstWidget().getWidgetTitle().getCityName(), CITY);
    }

    @Test
    @Title("Должны видеть виджет со всеми компонентами")
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
    @Title("Должен добавиться новый виджет после щелчка на кнопке '+'")
//    @ru.yandex.qatools.allure.annotations.TestCaseId("4")
    public void shouldBeAddedNewWidget() {
        defaultSteps.openMainPageWithCities(CITY);
        int numberOfWidgets = onMainPage().getWeatherWidgets().size();
        defaultSteps.clickOn(onMainPage().getAddWidgetButton());
        defaultSteps.shouldBeThisNumberOfElements(onMainPage().getWeatherWidgets(), numberOfWidgets + 1);
    }

    @Test
    @Title("Должен измениться город в первом виджете после ввода полного названия города и Return")
    public void shouldBeRenamedFirstWidgetAfterEnterFullName() {
        defaultSteps.openMainPageWithCities(CITY);
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.eraseText(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.enterText(onMainPage().getFirstWidget().getWidgetTitle().getCityName(), CITY2);
        defaultSteps.confirmText(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.waitUntilElementReady(onMainPage().getFirstWidget().getWidgetTitle().getCityName(), 10000);
        defaultSteps.shouldHaveText(onMainPage().getFirstWidget().getWidgetTitle().getCityName(), CITY2);
    }

    @Test
    @Title("Должны видеть саджест после ввода начала названия города")
    public void shouldSeeSuggestAfterEnterCityNameBegin() {
        String cityNameBegin = CITY2.substring(0, (CITY2.length() > 4 ? 4 : CITY2.length()));

        defaultSteps.openMainPageWithCities(CITY);
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.eraseText(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.enterText(onMainPage().getFirstWidget().getWidgetTitle().getCityName(), cityNameBegin);
        defaultSteps.waitUntilElementReady(onMainPage().getFirstWidget().getWidgetTitle().getFirstSuggest(), 10000);
        defaultSteps.shouldSee(onMainPage().getFirstWidget().getWidgetTitle().getCitySuggests());
    }

    @Test
    @Title("Должен измениться город в виджете после выбора города в саджесте")
    public void shouldSeeCityInWidgetChosenInSuggest() {
        String cityNameBegin = CITY2.substring(0, (CITY2.length() > 4 ? 4 : CITY2.length()));

        defaultSteps.openMainPageWithCities(CITY);
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.eraseText(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.enterText(onMainPage().getFirstWidget().getWidgetTitle().getCityName(), cityNameBegin);

        String cityNameInSuggest = onMainPage().getFirstWidget().getWidgetTitle().getFirstSuggest().getCityName().getText();

        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetTitle().getFirstSuggest());
        defaultSteps.waitUntilElementReady(onMainPage().getFirstWidget().getWidgetTitle().getCityName(), 10000);
        defaultSteps.shouldHaveText(onMainPage().getFirstWidget().getWidgetTitle().getCityName(), cityNameInSuggest);

    }

    @Test
    @Title("Должны видеть поле с названием города после после удаления текста в нём")
    public void shouldSeeCityNameInTitleAfterEraseTextInIt() {
        defaultSteps.openMainPageWithCities(CITY);
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.eraseText(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.confirmText(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.shouldSee(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
    }

    @Test
    @Title("Должны видеть 0 виджетов после удаления ")
    public void shouldSeeNullWidgetsAfterDelete() {
        defaultSteps.openMainPageWithCities(CITY, CITY2);
        defaultSteps.shouldBeThisNumberOfElements(onMainPage().getWeatherWidgets(), 2);
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetActions().getRemoveWidgetButton());
        defaultSteps.shouldBeThisNumberOfElements(onMainPage().getWeatherWidgets(), 1);
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetActions().getRemoveWidgetButton());
        defaultSteps.shouldBeThisNumberOfElements(onMainPage().getWeatherWidgets(), 0);
    }

    @Test
    @Title("После изменения города в виджете должен поменяться URL")
    public void shouldChangeUrlAfterWidgetRename() {
        defaultSteps.openMainPageWithCities(CITY);
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.eraseText(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.enterText(onMainPage().getFirstWidget().getWidgetTitle().getCityName(), CITY2);
        defaultSteps.confirmText(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.shouldBeUrl("http://weather.lanwen.ru/#?cities=" + CITY2);
    }

    @Test
    @Title("После удаления города в виджете должен поменяться URL")
    public void shouldChangeUrlAfterWidgetRemove() {
        defaultSteps.openMainPageWithCities(CITY, CITY2);
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetActions().getRemoveWidgetButton());
        defaultSteps.shouldBeUrl("http://weather.lanwen.ru/#?cities=" + CITY2.replace(" ", "%20"));
    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

}
