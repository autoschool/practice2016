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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static java.lang.String.format;

/**
 * Created by onegines (Eugene Kirienko)
 */
public class RegressionSuite {

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
    @Title("Должны видеть два виджета на главной странице после загрузки")
    @TestCaseId("14")
    public void shouldSeeTwoWidgetsOnMainPage() {
        defaultSteps.openMainPageWithCities(CITY, CITY2);
        defaultSteps.shouldBeThisNumberOfElements(onMainPage().getWeatherWidgets(), 2);
        defaultSteps.shouldSee(onMainPage().getWeatherWidgets());
    }

    @Test
    @Title("Должны видеть в виджете градусы Кельвина после одного нажатия на указатель температуры")
    @TestCaseId("20")
    public void shouldSeeKelvinInWidgetAfterOneClick() {
        defaultSteps.openMainPageWithCities(CITY);
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetText().getWeatherTemperature(), 1);
        defaultSteps.shouldHaveText(onMainPage().getFirstWidget().getWidgetText().getWeatherTemperatureUnit(), "°K");
    }

    @Test
    @Title("Должны видеть в виджете градусы Фаренгейта после двух нажатий на указатель температуры")
    @TestCaseId("21")
    public void shouldSeeFahrenheitInWidgetAfterTwoClick() {
        defaultSteps.openMainPageWithCities(CITY);
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetText().getWeatherTemperature(), 2);
        defaultSteps.shouldHaveText(onMainPage().getFirstWidget().getWidgetText().getWeatherTemperatureUnit(), "°F");
    }

    @Test
    @Title("Должны видеть в виджете градусы по кайфу после трёх нажатий на указатель температуры")
    @TestCaseId("22")
    public void shouldSeeKaifInWidgetAfterThreeClick() {
        defaultSteps.openMainPageWithCities(CITY);
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetText().getWeatherTemperature(), 3);
        defaultSteps.shouldHaveText(onMainPage().getFirstWidget().getWidgetText().getWeatherTemperatureUnit(), "°Kaif");
    }

    @Test
    @Title("Должны видеть в виджете градусы Цельсия после четырёх нажатий на указатель температуры")
    @TestCaseId("23")
    public void shouldSeeCelsiusInWidgetAfterFourClick() {
        defaultSteps.openMainPageWithCities(CITY);
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetText().getWeatherTemperature(), 4);
        defaultSteps.shouldHaveText(onMainPage().getFirstWidget().getWidgetText().getWeatherTemperatureUnit(), "°C");
    }

    @Test
    @Title("Должны видеть 0 виджетов и кнопку '+' после удаления последнего виджета")
    @TestCaseId("16")
    public void shouldSeeNoWidgetsAfterDeleteLastOne() {
        defaultSteps.openMainPageWithCities(CITY);
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetActions().getRemoveWidgetButton());
        defaultSteps.shouldBeThisNumberOfElements(onMainPage().getWeatherWidgets(), 0);
        defaultSteps.shouldSee(onMainPage().getAddWidgetButton());
    }

    @Test
    @Title("После изменения города в виджете должен поменяться URL")
    @TestCaseId("17")
    public void shouldChangeUrlAfterWidgetRename() throws UnsupportedEncodingException {
        String city2UrlEncode = URLEncoder.encode(CITY2, "UTF-8");

        defaultSteps.openMainPageWithCities(CITY);
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.eraseText(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.enterText(onMainPage().getFirstWidget().getWidgetTitle().getCityName(), CITY2);
        defaultSteps.confirmText(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.shouldBeUrl(DefaultSteps.MAIN_PAGE + format(DefaultSteps.QUERY, city2UrlEncode));
    }

    @Test
    @Title("После удаления виджета должен поменяться URL")
    @TestCaseId("18")
    public void shouldChangeUrlAfterWidgetRemove() throws UnsupportedEncodingException {
        String city2UrlEncode = URLEncoder.encode(CITY2, "UTF-8");

        defaultSteps.openMainPageWithCities(CITY, CITY2);
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetActions().getRemoveWidgetButton());
        defaultSteps.shouldBeUrl("http://weather.lanwen.ru/#?cities=" + city2UrlEncode);
        defaultSteps.shouldBeUrl(DefaultSteps.MAIN_PAGE + format(DefaultSteps.QUERY, city2UrlEncode));
    }

    @Test
    @Title("Должны видеть саджест после ввода начала названия города")
    @TestCaseId("13")
    public void shouldSeeSuggestAfterEnterCityNameBegin() {
        defaultSteps.openMainPageWithCities(CITY);
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.eraseText(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.enterText(onMainPage().getFirstWidget().getWidgetTitle().getCityName(), CITY2_BEGIN);
        defaultSteps.waitUntilElementReady(onMainPage().getFirstWidget().getWidgetTitle().getFirstSuggest(), 2);
        defaultSteps.shouldSee(onMainPage().getFirstWidget().getWidgetTitle().getCitySuggests());
    }

    @Test
    @Title("Должен измениться город в виджете после выбора города в саджесте (на выбранный)")
    @TestCaseId("15")
    public void shouldSeeCityInWidgetChosenInSuggest() {
        defaultSteps.openMainPageWithCities(CITY);
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.eraseText(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.enterText(onMainPage().getFirstWidget().getWidgetTitle().getCityName(), CITY2_BEGIN);

        String cityNameInSuggest = onMainPage().getFirstWidget().getWidgetTitle().getFirstSuggest().getCityName().getText();

        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetTitle().getFirstSuggest());
        defaultSteps.waitUntilElementReady(onMainPage().getFirstWidget().getWidgetTitle().getCityName(), 2);
        defaultSteps.shouldHaveText(onMainPage().getFirstWidget().getWidgetTitle().getCityName(), cityNameInSuggest);

    }

    @Test
    @Title("Должен исчезнуть саджест после выбора города в нём")
    @TestCaseId("24")
    public void shouldSeeNoSuggestAfterChooseCityInIt() {
        defaultSteps.openMainPageWithCities(CITY);
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.eraseText(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.enterText(onMainPage().getFirstWidget().getWidgetTitle().getCityName(), CITY2_BEGIN);
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetTitle().getFirstSuggest());
        defaultSteps.shouldNotSee(onMainPage().getFirstWidget().getWidgetTitle().getCitySuggestBlock());
    }

    @Test
    @Title("Должен исчезнуть саджест при клике мимо него")
    @TestCaseId("25")
    public void shouldSeeNoSuggestAfterClickNotOnIt() {
        defaultSteps.openMainPageWithCities(CITY);
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.eraseText(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.enterText(onMainPage().getFirstWidget().getWidgetTitle().getCityName(), CITY2_BEGIN);
        defaultSteps.clickOn(onMainPage().getBody());
        defaultSteps.shouldNotSee(onMainPage().getFirstWidget().getWidgetTitle().getCitySuggestBlock());

    }

    @Test
    @Title("Должен остаться прежний город в виджете при клике мимо саджеста")
    @TestCaseId("26")
    public void shouldSeeSameWidgetCityAfterClickNotOnSuggest() {
        defaultSteps.openMainPageWithCities(CITY);

        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.eraseText(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.enterText(onMainPage().getFirstWidget().getWidgetTitle().getCityName(), CITY2_BEGIN);
        defaultSteps.clickOn(onMainPage().getBody());
        defaultSteps.waitUntilElementReady(onMainPage().getFirstWidget().getWidgetTitle().getCityName(), 2);
        defaultSteps.shouldHaveText(onMainPage().getFirstWidget().getWidgetTitle().getCityName(), CITY);
    }

    @Test
    @Title("В саджесте не должны присутствовать undefined элементы")
    @TestCaseId("27")
    public void shouldSeeNoUndefinedInSuggest() {
        defaultSteps.openMainPageWithCities(CITY);

        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.eraseText(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.enterText(onMainPage().getFirstWidget().getWidgetTitle().getCityName(), CITY2_BEGIN);

        defaultSteps.shouldNotHaveText(onMainPage().getFirstWidget().getWidgetTitle().getCitySuggests(), "undefined");
    }

    @Test
    @Title("Должны видеть поле с названием города после после удаления текста в нём")
    @TestCaseId("12")
    public void shouldSeeCityNameInTitleAfterEraseTextInIt() {
        defaultSteps.openMainPageWithCities(CITY);
        defaultSteps.clickOn(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.eraseText(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.confirmText(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
        defaultSteps.shouldSee(onMainPage().getFirstWidget().getWidgetTitle().getCityName());
    }
}
