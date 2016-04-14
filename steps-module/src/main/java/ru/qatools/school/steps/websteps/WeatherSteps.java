package ru.qatools.school.steps.websteps;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.pages.blocks.WeatherWidget;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static ru.qatools.school.steps.websteps.util.BasicActionSteps.*;
import static ru.qatools.school.steps.websteps.util.CheckingSteps.shouldBeConvertibleToDouble;
import static ru.qatools.school.steps.websteps.util.CheckingSteps.shouldBeInRange;

/**
 * @author kurau
 * @author gladnik (Nikolai Gladkov)
 */
public class WeatherSteps {

    private static final String MAIN_PAGE = "http://weather.lanwen.ru";
    private static final String CITY_QUERY = MAIN_PAGE + "/#?cities=%s";
    private static final String BLANK_PAGE = "about:blank";
    private static final int WAIT_TIMEOUT = 10;
    private static final int ELEMENT_REFRESH_TIMEOUT = 1;

    private WebDriver driver;

    public WeatherSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открываем пустую страницу")
    public void openBlankPage() {
        driver.get(BLANK_PAGE);
    }

    @Step("Открываем главную страницу")
    public void openMainPage() {
        driver.get(MAIN_PAGE);
    }

    @Step("Открываем главную страницу для города «{0}»")
    public void openMainPageWithCity(String city) {
        driver.get(format(CITY_QUERY, city));
    }

    @Step("Ждём доступности элемента «{0}»")
    public void waitElementToBeClickable(HtmlElement element) {
        waitElementToBeClickableBasic(element, driver, WAIT_TIMEOUT);
    }

    @Step("Ждём что элемент «{0}» устареет")
    public void waitElementToStale(HtmlElement element) {
        waitElementToStaleBasic(element, driver, ELEMENT_REFRESH_TIMEOUT);
    }

    @Step("Кликаем по [{0}]")
    public void clickOn(HtmlElement element) {
        safeClick(element, driver, WAIT_TIMEOUT);
    }

    @Step("Кликаем по [{0}] {1} раз(а)")
    public void clickOn(HtmlElement element, int nTimes) {
        while (nTimes > 0) {
            clickOn(element);
            nTimes--;
        }
    }

    @Step("Стираем текст из «{0}»")
    public void clearText(HtmlElement element) {
        safeClick(element, driver, WAIT_TIMEOUT);
        safeClear(element, driver, WAIT_TIMEOUT);
        safeSendKeys(element, Keys.ENTER, driver, WAIT_TIMEOUT);
    }

    @Step("Меняем текст в «{0}» на '{1}'")
    public void changeText(HtmlElement element, String text) {
        safeClick(element, driver, WAIT_TIMEOUT);
        safeClear(element, driver, WAIT_TIMEOUT);
        safeSendKeys(element, text, driver, WAIT_TIMEOUT);
        safeSendKeys(element, Keys.ENTER, driver, WAIT_TIMEOUT);
    }

    @Step("Меняем название города в виджете «{0}» на «{1}»")
    public void changeCityName(WeatherWidget widget, String newName) {
        changeText(widget.widgetTitle().cityName(), newName);
    }

    @Step("Открываем подсказку с городами")
    public void openSuggest() {
        clickOn(onMainPage().firstWidget().widgetTitle().cityName());
        onMainPage().firstWidget().widgetTitle().cityName().sendKeys(Keys.ARROW_DOWN);
    }

    @Step("Добавляем новый виджет с городом «{0}»")
    public void addWidgetWithCity(String cityName) {
        clickOn(onMainPage().addWidgetButton());
        changeCityName(onMainPage().firstWidget(), cityName);
    }

    @Step("Делаем список из всех элементов виджета «{0}»")
    public List<HtmlElement> listAllWidgetElements(WeatherWidget widget) {
        List<HtmlElement> elements = new ArrayList<>(Arrays.asList(
                widget.widgetTitle().cityName(),
                widget.widgetTitle().dateTimeLine(),
                widget.widgetText().weatherImage(),
                widget.widgetText().temperatureValue(),
                widget.widgetText().temperatureUnit(),
                widget.widgetText().dividerLine(),
                widget.widgetActions().removeWidgetButton()));

        elements.addAll(widget.widgetText().sunriseInfo().allElements());
        elements.addAll(widget.widgetText().sunsetInfo().allElements());
        elements.addAll(widget.widgetText().windInfo().allElements());
        elements.addAll(widget.widgetText().humidityInfo().allElements());
        return elements;
    }

    @Step("Получаем URL страницы")
    public String getPageUrl() {
        return driver.getCurrentUrl();
    }


    @Step("Сравниваем название страницы с «{0}»")
    public void shouldGetPageTitle(String pageTitle) {
        assertThat("Название страницы неправильное", driver.getTitle(), is(pageTitle));
    }

    @Step("Проверяем что у виджета «{0}» значение температуры находится в диапазоне от {1} до {2}")
    public void shouldHaveTemperatureValueInRange(WeatherWidget widget, int lowest, int highest) {
        shouldBeConvertibleToDouble(widget.widgetText().temperatureValue().getText());
        double temperatureValue = convertToDoubleElementText(widget.widgetText().temperatureValue());
        shouldBeInRange(temperatureValue, lowest, highest);
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
