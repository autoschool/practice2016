package ru.qatools.school.steps.websteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.dom.html.HTMLElement;
import ru.qatools.school.pages.MainPage;
import ru.yandex.qatools.allure.annotations.Step;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
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

    @Step("Должны видеть элемент с текстом {1}")
    public  void shouldSeeText(WebElement element, String text) {
        shouldSee(element);
        assertThat("Должны видеть текст", element, hasText(text));
    }

    @Step("Нажимаем на кнопку +")
    public void pushButton(WebElement element) {
        element.click();
    }

    @Step("Должны видеть на один виджет больше")
    public void shouldSeeOtherCountOfWidgets(int widgetCount) {
        assertEquals("ДДолжны видеть отличное от начального кол-во виджетов", onMainPage().getWeatherWidgetList().size(), widgetCount);
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
