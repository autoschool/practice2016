package ru.qatools.school.steps.websteps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.MainPage;
import ru.yandex.qatools.allure.annotations.Step;

import static java.lang.String.format;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

/**
 * Created by kurau.
 */
public class DefaultSteps {

    public static final String MAIN_PAGE = "http://weather.lanwen.ru/#?cities=%s";

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
        assertThat(element, isDisplayed());
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }

    @Step("Должны видеть название города  «{0}»")
    public void shouldSeeCorrectCityNameOnFirstWidget(String city) {
        assertThat(onMainPage().getWeatherWidget().get(0).getCityName().getText(), is(city) );
    }

    @Step("Добавить один виджет")
    public void addOneMoreWidget() {
        onMainPage().getAddWidgetButton().click();
    }

    @Step("Количество виджетов на странице должно быть равно «{0}»")
    public void shouldHaveCorrectWidgetAmount(int widgetAmount) {
        assertThat(onMainPage().getWeatherWidget().size(), is(widgetAmount));
    }

}
