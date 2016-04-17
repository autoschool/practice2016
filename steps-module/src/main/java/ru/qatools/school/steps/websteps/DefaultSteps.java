package ru.qatools.school.steps.websteps;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.pages.blocks.WeatherWidget;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.concurrent.TimeUnit;

import static java.lang.String.format;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

/**
 * Created by kurau.
 * Changed by onegines (Eugene Kirienko).
 */
public class DefaultSteps {

    private static final String MAIN_PAGE = "http://weather.lanwen.ru/#?cities=%s";

    private WebDriver driver;

    public DefaultSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открываем главную страницу для города «{0}»")
    public void openMainPageWithCity(String city) {
        driver.get(format(MAIN_PAGE, city));
    }

    @Step("Должны видеть на странице «{0}»")
    public void shouldSee(WebElement element) {
        assertThat("Должны видеть элемент", element, isDisplayed());
    }

    @Step("Должны видеть виджет с городом «{1}»")
    public void shouldSeeWidgetWithCity(WeatherWidget widget, String expected) {
        String actual = widget.getWidgetTitle().getCityNameInTitle().getText();
        assertThat("Ожидали другой город", actual, is(expected));
    }

    @Step("Добавляем еще один виджет")
    public void addNewWidget() {
        onMainPage().getAddWidgetButton().click();
    }

    @Step("Должны увидеть {0} виджета(ов)")
    public void shouldSeeThisNumberOfWidgets(int count) {
        assertThat("Количество виджетов не соответствует ожидаемому", onMainPage().getWeatherWidget().size(), is(count));
    }

    @Step("Первому виджету назначаем город «{0}», вводя полное название и нажимая 'Return'")
    public void editLastWidgetWithEnterFullName(String cityName) {
        onMainPage().getFirstWidget().getWidgetTitle().getCityNameInTitle().click();
        onMainPage().getFirstWidget().getWidgetTitle().getCityNameInTitle().clear();
        onMainPage().getFirstWidget().getWidgetTitle().getCityNameInTitle().sendKeys(cityName + Keys.RETURN);
    }

    @Step("Первому виджету назначаем город «{0}, вводя начало название и кликая по всплывающему меню»")
    public void editLastWidgetWithPopup(String cityName) {
        String beginOfCityName = cityName.substring(0, (cityName.length() > 4 ? 4 : cityName.length()));

        onMainPage().getFirstWidget().getWidgetTitle().getCityNameInTitle().click();
        onMainPage().getFirstWidget().getWidgetTitle().getCityNameInTitle().clear();
        onMainPage().getFirstWidget().getWidgetTitle().getCityNameInTitle().sendKeys(beginOfCityName);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        onMainPage().getCityChoosePopup().click();
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
