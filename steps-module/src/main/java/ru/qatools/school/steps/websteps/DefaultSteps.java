package ru.qatools.school.steps.websteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.MainPage;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.matchers.webdriver.TextMatcher;

import static org.hamcrest.Matchers.*;
import static java.lang.String.format;
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
        assertThat("Должны видеть элемент", element, isDisplayed());
    }

    @Step("Должны видеть на странице город «{0}»")
    public void shouldSeeCity(String city){
        String actualCity = "no such city onMainPage";
        for (WebElement element : onMainPage().getWeatherCities())
            if (element.getText().equals(city)) {
                actualCity = city;
                break;
            }
//        assertThat(onMainPage().getWeatherCities(), hasItem(city));
        assertThat(actualCity, is(city));
    }

    @Step("Нажимаем на «{0}»")
    public void clickOn(WebElement element){
        element.click();
    }

    @Step("Должны видеть количество виджетов - «{0}»")
    public void shouldSeeCountWidget(int expectedCountWidget){
        int actualCountWidget = onMainPage().getWeatherWidget().size();
        assertThat("Должны видеть верное количество виджетов ", actualCountWidget, is(expectedCountWidget));
    }
    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
