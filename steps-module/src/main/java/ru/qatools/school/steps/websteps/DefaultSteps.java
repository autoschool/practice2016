package ru.qatools.school.steps.websteps;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.MainPage;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.*;

/**
 * Created by kurau.
 * Changed by onegines (Eugene Kirienko).
 */
public class DefaultSteps {

    private static final String MAIN_PAGE = "http://weather.lanwen.ru/";
    private static final String QUERY = "#?cities=%s";

    private WebDriver driver;

    public DefaultSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открываем главную страницу без параметров»")
    public void openMainPage() {
        driver.get(MAIN_PAGE);
    }

//    @Step("Открываем главную страницу для города «{0}»")
//    public void openMainPageWithCity(String city) {
//        driver.get(MAIN_PAGE + format(QUERY, city));
//    }

    @Step("Открываем главную страницу для городов «{0}»")
    public void openMainPageWithCities(String... cities) {
        driver.get(MAIN_PAGE + format(QUERY, StringUtils.join(cities, ',')));
    }

    @Step("Должен быть заголовок «{0}»")
    public void shouldBeTitle(String title) {
        assertThat("Заголовок страницы не совпадает", driver.getTitle(), is(title));
    }

    @Step("Должен быть URL «{0}»")
    public void shouldBeUrl(String url) {
        assertThat("URL страницы не совпадает", driver.getCurrentUrl(), is(url));
    }

    @Step("Должны видеть на странице элемент «{0}»")
    public void shouldSee(WebElement element) {
        assertThat("Должны видеть элемент", element, both(exists()).and(isDisplayed()));
    }

    @Step("Должны видеть на странице элементы «{0}»")
    public void shouldSee(List<? extends WebElement> elements) {
        for (WebElement element : elements) {
            assertThat("Должен существовать элемент", element, exists());
            assertThat("Должны видеть элемент", element, isDisplayed());
        }
    }

    @Step("Должны увидеть {0} виджета(ов)")
    public void shouldBeThisNumberOfWidgets(int count) {
        assertThat("Количество виджетов не соответствует ожидаемому", onMainPage().getWeatherWidgets().size(), is(count));
    }

    @Step("Кликаем по элементу {0}")
    public void clickOn(HtmlElement element) {
        element.click();
    }

    @Step("Удаляем текст в элементе «{0}»")
    public void eraseText(WebElement element) {
        element.clear();
    }

    @Step("Вводим текст «{1}» в элемент «{0}»")
    public void enterText(WebElement element, String text) {
        element.sendKeys(text);
    }

    @Step("Подтверждаем нажатием Return ввод в элементе «{0}»")
    public void confirmText(WebElement element) {
        element.sendKeys(Keys.RETURN);
    }

    @Step("Элемент «{0}» должен содержать текст «{1}»")
    public void shouldHaveText(HtmlElement element, String text) {
        assertThat("Текст в элементе не соответствует ожидаемому", element, hasText(text));
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
