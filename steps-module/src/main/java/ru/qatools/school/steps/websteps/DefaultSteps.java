package ru.qatools.school.steps.websteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.MainPage;
import ru.yandex.qatools.allure.annotations.Step;

import static java.lang.String.format;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

/**
 * @author kurau
 * @author gladnik (Nikolai Gladkov)
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

    @Step("Текст элемента «{0}» должен быть «{1}»")
    public void expectText(WebElement element, String text) {
        assertThat("Неверный текст элемента", element.getText(), is(text));
    }

    @Step("Кликаем на элемент «{0}»")
    public void clickElement(WebElement element) {
        element.click();
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
