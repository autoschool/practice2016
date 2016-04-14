package ru.qatools.school.steps.websteps;

import org.openqa.selenium.Keys;
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

    @Step("Добавляем еще один город")
    public void addNewCity(String anotherCity) {
        onMainPage().getAddCityButton().click();
        onMainPage().getWeatherWidget().get(0).getWidgetTitle().getCity().sendKeys(Keys.DELETE);
        onMainPage().getWeatherWidget().get(0).getWidgetTitle().getCity().sendKeys(anotherCity);
    }

    @Step("Должны видеть на странице «{0}»")
    public void shouldSee(WebElement element) {
        assertThat("Должны видеть элемент", element, isDisplayed());
    }

    @Step("Должны видеть верный текст: «{0}»")
    public void shouldSeeText(String actual, String expected) {
        assertThat("Должны видеть текст", actual, is(expected));
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
