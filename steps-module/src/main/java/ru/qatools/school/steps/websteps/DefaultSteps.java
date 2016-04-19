package ru.qatools.school.steps.websteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.MainPage;
import ru.yandex.qatools.allure.annotations.Step;

import java.awt.*;

import static java.lang.String.format;
import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.*;

/**
 * Created by kurau.
 */
public class DefaultSteps {

    public static final String MAIN_PAGE = "http://weather.lanwen.ru/#?cities=%s";
    MainPage mainPage;
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

    @Step("Должны видеть в заголовке виджета название города")
    public void shouldSeeCityName(String city) {
        mainPage = new MainPage(driver);
        WebElement cityName = mainPage.getWeatherWidget().get(0).getWidgetTitle().getCityName();
        assertThat("Название города в заголовке совпадает с названием искомого города", cityName, hasText(city));
    }

    @Step("После нажатия кнопки Добавить появляется еще один виджет")
    public void addNewWidget(Integer widgetNumber) {
        mainPage = new MainPage(driver);
        mainPage.getAddWidgetButton().click();
        assertThat("Появился новый виджет", mainPage.getWeatherWidget().get(widgetNumber), isDisplayed());
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
