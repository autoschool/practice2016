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
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import static java.lang.String.format;
import static java.util.Arrays.asList;

/**
 * @author by onegines (Eugene Kirienko)
 */
public class WebTests {

    private static final String CITY = "Saint Petersburg";
    private static final String CITY2 = "Moscow";
    private static final String CITY2_BEGIN = "Mosc";
    private static final String TITLE = "Weather";

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
        defaultSteps.shouldBeUrl(DefaultSteps.MAIN_PAGE);
        defaultSteps.shouldBeTitle(TITLE);
    }

    @Test
    @Title("Должны видеть кнопку '+' на странице, открытой без параметров")
    @TestCaseId("5")
    public void shouldSeeAddWidgetButtonOnPageWithNoQuery() {
        defaultSteps.openMainPage();
        defaultSteps.shouldSee(onMainPage().getAddWidgetButton());
    }

    @Test
    @Title("Должны видеть кнопку '+' на странице, открытой с указанием города в параметрах")
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

        List<HtmlElement> allWidgetBlocks = asList(
                onMainPage().getFirstWidget().getWidgetTitle().getCityName(),
                onMainPage().getFirstWidget().getWidgetTitle().getCurrentTimeAndDate(),

                onMainPage().getFirstWidget().getWidgetText().getWeatherImage(),
                onMainPage().getFirstWidget().getWidgetText().getWeatherTemperatureDigit(),
                onMainPage().getFirstWidget().getWidgetText().getWeatherTemperatureUnit(),

                onMainPage().getFirstWidget().getWidgetText().getSunriseLine().getTitle(),
                onMainPage().getFirstWidget().getWidgetText().getSunriseLine().getImage(),
                onMainPage().getFirstWidget().getWidgetText().getSunriseLine().getValue(),

                onMainPage().getFirstWidget().getWidgetText().getSunsetLine().getTitle(),
                onMainPage().getFirstWidget().getWidgetText().getSunsetLine().getImage(),
                onMainPage().getFirstWidget().getWidgetText().getSunsetLine().getValue(),

                onMainPage().getFirstWidget().getWidgetText().getWindLine().getTitle(),
                onMainPage().getFirstWidget().getWidgetText().getWindLine().getImage(),
                onMainPage().getFirstWidget().getWidgetText().getWindLine().getValue(),

                onMainPage().getFirstWidget().getWidgetText().getHumidityLine().getTitle(),
                onMainPage().getFirstWidget().getWidgetText().getHumidityLine().getImage(),
                onMainPage().getFirstWidget().getWidgetText().getHumidityLine().getValue(),

                onMainPage().getFirstWidget().getWidgetActions().getRemoveWidgetButton()
        );

        defaultSteps.shouldSee(allWidgetBlocks);
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
