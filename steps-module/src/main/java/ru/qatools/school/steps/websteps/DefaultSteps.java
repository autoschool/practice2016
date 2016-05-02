package ru.qatools.school.steps.websteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.MainPage;
import ru.yandex.qatools.allure.annotations.Step;

import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

/**
 * Created by kurau.
 */
public class DefaultSteps {

    private static final String MAIN_PAGE_WITH_WIDGET = "http://weather.lanwen.ru/#?cities=%s";
    private static final String MAIN_PAGE = "http://weather.lanwen.ru";

    private WebDriver driver;


    public DefaultSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открываем главную страницу")
    public void openMainPage() {
        driver.get(MAIN_PAGE);
    }

    @Step("Открываем главную страницу для города «{0}»")
    public void openMainPageWithCity(String city) {
        driver.get(format(MAIN_PAGE_WITH_WIDGET, city));
    }

    @Step("Должны видеть на странице «{0}»")
    public void shouldSee(WebElement element) {
        assertThat("Должны видеть элемент", element, isDisplayed());
    }

    @Step("Добавить виджет на страницу")
    public void addOneWidget() {
        onMainPage().getButtonAddWidget().click();
    }

    @Step("Должны увидеть n виджетов")
    public void shouldSeeWidgets(int count) {
        assertThat(onMainPage().getWeatherWidget().size(), is(count));
    }

    @Step("Получить количество виджетов на странице")
    public int getCountWidgets() {
        return onMainPage().getWeatherWidget().size();
    }

    @Step("Должны увидеть виджет с городом {0}")
    public void shouldSeeCurrentCity(String city) {
        assertThat(onMainPage().getWeatherWidget().get(0).getWidgetTitle().getCityName().getText(), is(city));
    }

    @ru.yandex.qatools.allure.annotations.TestCaseId("10")
    @Step("Должны увидеть кнопку добавления виджета")
    public void shouldSeeButtonAddWidget() {
        assertThat("Должны увидеть кнопку добавления виджета", onMainPage().getButtonAddWidget(), isDisplayed());
    }

    @Step("Удаление виджета со страницы")
    public void deleteWidget() {
        onMainPage().getWeatherWidget().get(0).getButtonDeleteWidget().click();
    }

    @Step("Пишем название города в виджете")
    public void writeCityName(String city) {
        onMainPage().getWeatherWidget().get(0).getWidgetTitle().getCityName().click();
        onMainPage().getWeatherWidget().get(0).getWidgetTitle().getCityName().sendKeys(city);
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
