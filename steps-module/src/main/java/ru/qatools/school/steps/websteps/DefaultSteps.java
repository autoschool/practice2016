package ru.qatools.school.steps.websteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.MainPage;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

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
    public void shouldSee(HtmlElement element) {
        assertThat("Должны видеть элемент", element, isDisplayed());
    }

    @Step("Должны увидеть название города «{0}» на виджете ")
    public void shouldSeeCorrectCityNameAtWidget(String cityFromUrl) {
        assertThat("Должны видеть на виджете название города, такое как в URL: «{1}»",
                onMainPage().getWeatherWidget().get(0).getWidgetTitle()
                        .getCityName().getText(), is(cityFromUrl));
    }

    @Step("Кликаем на элемент «{0}»")
    public void clickElement(HtmlElement element) {element.click();}

    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
