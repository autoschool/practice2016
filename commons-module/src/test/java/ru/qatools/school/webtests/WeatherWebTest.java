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
    @Title("Должна открыться требуемая страница")
    @ru.yandex.qatools.allure.annotations.TestCaseId("6")
    public void shouldBeOnCorrectPage() {
        defaultSteps.openMainPage();
        defaultSteps.shouldBeUrl("http://weather.lanwen.ru/");
        defaultSteps.shouldBeTitle("Weather");
    }

    @Test
    @Title("Должны видеть кнопку '+' на странице, открытой без параметров")
    @ru.yandex.qatools.allure.annotations.TestCaseId("5")
    public void shouldSeeAddWidgetButtonOnPageWithNoQuery() {
        defaultSteps.openMainPage();
        defaultSteps.shouldSee(onMainPage().getAddWidgetButton());
    }

    @Test
    @Title("Должны видеть кнопку '+' на странице, открытой с указанием города в в параметрах")
    @ru.yandex.qatools.allure.annotations.TestCaseId("7")
    public void shouldSeeAddWidgetButtonOnPageWithQuery() {
        defaultSteps.openMainPageWithCities(CITY);
        defaultSteps.shouldSee(onMainPage().getAddWidgetButton());
    }

    @Test
    @Title("Должны видеть два виджета на главной странице после загрузки")
    @ru.yandex.qatools.allure.annotations.TestCaseId("14")
    public void shouldSeeTwoWidgetsOnMainPage() {
        defaultSteps.openMainPageWithCities(CITY, CITY2);
        defaultSteps.shouldBeThisNumberOfElements(onMainPage().getWeatherWidgets(), 2);
        defaultSteps.shouldSee(onMainPage().getWeatherWidgets());
    }

    @Test
    @Title("Должны видеть виджет города из запроса")
    @ru.yandex.qatools.allure.annotations.TestCaseId("8")
    public void shouldSeeWidgetFromQuery() {
        defaultSteps.openMainPageWithCities(CITY);
        defaultSteps.shouldHaveText(onMainPage().getFirstWidget().getWidgetTitle().getCityName(), CITY);
    }

    @Test
    @Title("Должны видеть все компоненты виджета")
    @ru.yandex.qatools.allure.annotations.TestCaseId("9")
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
    @ru.yandex.qatools.allure.annotations.TestCaseId("19")
    public void shouldSeeCelsiusInWidgetOnMainPage() {
        defaultSteps.openMainPageWithCities(CITY);
        defaultSteps.shouldHaveText(onMainPage().getFirstWidget().getWidgetText().getWeatherTemperatureUnit(), "°C");
    }

    @Test
    @Title("Должны видеть в виджете градусы Кельвина после одного нажатия на указатель температуры")
    @ru.yandex.qatools.allure.annotations.TestCaseId("20")
    public void shouldSeeKelvinInWidgetAfterOneClick() {
        defaultSteps.openMainPageWithCities(CITY);
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetText().getWeatherTemperature());
        defaultSteps.shouldHaveText(onMainPage().getFirstWidget().getWidgetText().getWeatherTemperatureUnit(), "°K");
    }

    @Test
    @Title("Должны видеть в виджете градусы Фаренгейта после двух нажатий на указатель температуры")
    @ru.yandex.qatools.allure.annotations.TestCaseId("21")
    public void shouldSeeFahrenheitInWidgetAfterTwoClick() {
        defaultSteps.openMainPageWithCities(CITY);
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetText().getWeatherTemperature());
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetText().getWeatherTemperature());
        defaultSteps.shouldHaveText(onMainPage().getFirstWidget().getWidgetText().getWeatherTemperatureUnit(), "°F");
    }

    @Test
    @Title("Должны видеть в виджете градусы по кайфу после трёх нажатий на указатель температуры")
    @ru.yandex.qatools.allure.annotations.TestCaseId("22")
    public void shouldSeeKaifInWidgetAfterThreeClick() {
        defaultSteps.openMainPageWithCities(CITY);
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetText().getWeatherTemperature());
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetText().getWeatherTemperature());
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetText().getWeatherTemperature());
        defaultSteps.shouldHaveText(onMainPage().getFirstWidget().getWidgetText().getWeatherTemperatureUnit(), "°Kaif");
    }

    @Test
    @Title("Должны видеть в виджете градусы Цельсия после четырёх нажатий на указатель температуры")
    @ru.yandex.qatools.allure.annotations.TestCaseId("23")
    public void shouldSeeCelsiusInWidgetAfterFourClick() {
        defaultSteps.openMainPageWithCities(CITY);
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetText().getWeatherTemperature());
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetText().getWeatherTemperature());
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetText().getWeatherTemperature());
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetText().getWeatherTemperature());
        defaultSteps.shouldHaveText(onMainPage().getFirstWidget().getWidgetText().getWeatherTemperatureUnit(), "°C");
    }

    @Test
    @Title("Должен добавиться новый виджет после щелчка на кнопке '+'")
    @ru.yandex.qatools.allure.annotations.TestCaseId("4")
    public void shouldBeAddedNewWidget() {
        defaultSteps.openMainPageWithCities(CITY);
        int numberOfWidgets = onMainPage().getWeatherWidgets().size();
        defaultSteps.clickOn(onMainPage().getAddWidgetButton());
        defaultSteps.shouldBeThisNumberOfElements(onMainPage().getWeatherWidgets(), numberOfWidgets + 1);
    }

    @Test
    @Title("Должен измениться город в первом виджете после ввода полного названия города и Return")
    @ru.yandex.qatools.allure.annotations.TestCaseId("10")
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
    @Title("Должны видеть саджест после ввода начала названия города")
    @ru.yandex.qatools.allure.annotations.TestCaseId("13")
    public void shouldSeeSuggestAfterEnterCityNameBegin() {
        String cityNameBegin = CITY2.substring(0, (CITY2.length() > 4 ? 4 : CITY2.length()));

        defaultSteps.openMainPageWithCities(CITY);
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.eraseText(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.enterText(onMainPage().getFirstWidget().getWidgetTitle().getCityName(), cityNameBegin);
        defaultSteps.waitUntilElementReady(onMainPage().getFirstWidget().getWidgetTitle().getFirstSuggest(), 2);
        defaultSteps.shouldSee(onMainPage().getFirstWidget().getWidgetTitle().getCitySuggests());
    }

    @Test
    @Title("Должен измениться город в виджете после выбора города в саджесте (на выбранный)")
    @ru.yandex.qatools.allure.annotations.TestCaseId("15")
    public void shouldSeeCityInWidgetChosenInSuggest() {
        String cityNameBegin = CITY2.substring(0, (CITY2.length() > 4 ? 4 : CITY2.length()));

        defaultSteps.openMainPageWithCities(CITY);
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.eraseText(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.enterText(onMainPage().getFirstWidget().getWidgetTitle().getCityName(), cityNameBegin);

        String cityNameInSuggest = onMainPage().getFirstWidget().getWidgetTitle().getFirstSuggest().getCityName().getText();

        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetTitle().getFirstSuggest());
        defaultSteps.waitUntilElementReady(onMainPage().getFirstWidget().getWidgetTitle().getCityName(), 2);
        defaultSteps.shouldHaveText(onMainPage().getFirstWidget().getWidgetTitle().getCityName(), cityNameInSuggest);

    }

    @Test
    @Title("Должен исчезнуть саджест после выбора города в нём")
    @ru.yandex.qatools.allure.annotations.TestCaseId("24")
    public void shouldSeeNoSuggestAfterChooseCityInIt() {
        String cityNameBegin = CITY2.substring(0, (CITY2.length() > 4 ? 4 : CITY2.length()));

        defaultSteps.openMainPageWithCities(CITY);
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.eraseText(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.enterText(onMainPage().getFirstWidget().getWidgetTitle().getCityName(), cityNameBegin);
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetTitle().getFirstSuggest());
        defaultSteps.shouldNotSee(onMainPage().getFirstWidget().getWidgetTitle().getCitySuggestBlock());
    }

    @Test
    @Title("Должен исчезнуть саджест при клике мимо него")
    @ru.yandex.qatools.allure.annotations.TestCaseId("25")
    public void shouldSeeNoSuggestAfterClickNotOnIt() {
        String cityNameBegin = CITY2.substring(0, (CITY2.length() > 4 ? 4 : CITY2.length()));

        defaultSteps.openMainPageWithCities(CITY);
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.eraseText(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.enterText(onMainPage().getFirstWidget().getWidgetTitle().getCityName(), cityNameBegin);
        defaultSteps.clickOn(onMainPage().getBody());
        defaultSteps.shouldNotSee(onMainPage().getFirstWidget().getWidgetTitle().getCitySuggestBlock());

    }

    @Test
    @Title("Должны видеть поле с названием города после после удаления текста в нём")
    @ru.yandex.qatools.allure.annotations.TestCaseId("12")
    public void shouldSeeCityNameInTitleAfterEraseTextInIt() {
        defaultSteps.openMainPageWithCities(CITY);
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.eraseText(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.confirmText(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.shouldSee(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
    }

    @Test
    @Title("Должен удалиться виджет после нажатия на кнопку удаления")
    @ru.yandex.qatools.allure.annotations.TestCaseId("11")
    public void shouldBeDeletedOneWidget() {
        defaultSteps.openMainPageWithCities(CITY, CITY2);
        int numberOfWidgets = onMainPage().getWeatherWidgets().size();
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetActions().getRemoveWidgetButton());
        defaultSteps.shouldBeThisNumberOfElements(onMainPage().getWeatherWidgets(), numberOfWidgets - 1);
    }

    @Test
    @Title("Должны видеть 0 виджетов и кнопку '+' после удаления последнего виджета")
    @ru.yandex.qatools.allure.annotations.TestCaseId("16")
    public void shouldSeeNoWidgetsAfterDeleteLastOne() {
        defaultSteps.openMainPageWithCities(CITY);
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetActions().getRemoveWidgetButton());
        defaultSteps.shouldBeThisNumberOfElements(onMainPage().getWeatherWidgets(), 0);
        defaultSteps.shouldSee(onMainPage().getAddWidgetButton());
    }

    @Test
    @Title("После изменения города в виджете должен поменяться URL")
    @ru.yandex.qatools.allure.annotations.TestCaseId("17")
    public void shouldChangeUrlAfterWidgetRename() {
        defaultSteps.openMainPageWithCities(CITY);
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.eraseText(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.enterText(onMainPage().getFirstWidget().getWidgetTitle().getCityName(), CITY2);
        defaultSteps.confirmText(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.shouldBeUrl("http://weather.lanwen.ru/#?cities=" + CITY2);
    }

    @Test
    @Title("После удаления виджета должен поменяться URL")
    @ru.yandex.qatools.allure.annotations.TestCaseId("18")
    public void shouldChangeUrlAfterWidgetRemove() {
        String city2UrlEncode = CITY2.replace(" ", "%20");

        defaultSteps.openMainPageWithCities(CITY, CITY2);
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetActions().getRemoveWidgetButton());
        defaultSteps.shouldBeUrl("http://weather.lanwen.ru/#?cities=" + city2UrlEncode);
    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

}
