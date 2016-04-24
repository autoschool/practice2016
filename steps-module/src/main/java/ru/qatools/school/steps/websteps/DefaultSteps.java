package ru.qatools.school.steps.websteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.MainPage;
import ru.yandex.qatools.allure.annotations.Step;

import static java.lang.String.format;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;
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

    @Step("Должны видеть в заголовке виджета текст: {0}")
    public void shouldSeeRightCityInWidgetsTitle(String cityName){
        assertThat("Должны видеть текст",
                onMainPage().getWeatherWidget().get(0).getWidgetTitle().getCityName(), hasText(cityName));
    }

    @Step("Нажимаем на кнопку добавления виджета")
    public void addNewWidgetOnMainPage(){
        onMainPage().getAddNewWidgetButton().click();
    }

    @Step("Число виджетов на странице должно быть равно: {0}")
    public void shouldHaveWidgetNumberOnMainPage(int numberOfWidgets){
        assertThat("Должны видеть виджетов", onMainPage().getWeatherWidget().size(), is(numberOfWidgets));
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
