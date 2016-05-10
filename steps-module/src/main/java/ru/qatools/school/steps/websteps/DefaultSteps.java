package ru.qatools.school.steps.websteps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.MainPage;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.String.format;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.exists;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

/**
 * @author kormyshov
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

    @Step("Должны видеть элемент «{0}»")
    public void shouldSee(WebElement element) {
        assertThat("Должны видеть элемент", element, both(exists()).and(isDisplayed()));
    }

    @Step("Должно быть «{1}» элементов в «{0}»")
    public void shouldHaveSize(Collection<? extends WebElement> elements, int size){
        assertThat("Неверное количество элементов", elements, hasSize(size));
    }

    @Step("Должны видеть каждый из «{0}»")
    public void shouldSee(List<? extends WebElement> elements) {
        assertThat("Должны видеть элементы", new ArrayList<>(elements), everyItem(both(exists()).and(isDisplayed())));
    }

    @Step("Должен быть текст «{1}» у элемента «{0}»")
    public void shouldSeeText(WebElement element, String text) {
        assertThat("Неверный текст элемента", element.getText(), is(text));
    }

    @Step("Кликаем по «{0}»")
    public void clickOn(HtmlElement element){
        element.click();
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
