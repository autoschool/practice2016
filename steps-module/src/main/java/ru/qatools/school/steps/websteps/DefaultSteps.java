package ru.qatools.school.steps.websteps;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.qatools.school.pages.MainPage;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.Every.everyItem;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.*;

/**
 * Created by kurau.
 * Changed by onegines (Eugene Kirienko).
 */
public class DefaultSteps {

    public static final String MAIN_PAGE = "http://weather.lanwen.ru/";
    public static final String QUERY = "#?cities=%s";

    private WebDriver driver;

    public DefaultSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открываем главную страницу без параметров»")
    public void openMainPage() {
        driver.get(MAIN_PAGE);
    }

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
        assertThat("Должны видеть элемент", element, allOf(exists(), isDisplayed()));
    }

    @Step("Должны видеть на странице элементы «{0}»")
    public void shouldSee(List<? extends WebElement> elements) {
        assertThat("Должны видеть элемент", elements, everyItem(allOf(exists(), isDisplayed())));
    }

    @Step("Должны не видеть на странице элемент «{0}»")
    public void shouldNotSee(WebElement element) {
        assertThat("Должны не видеть элемент", element, not(isDisplayed()));
    }

    @Step("Должны увидеть «{1}» элементов(ов) в списке «{0}»")
    public void shouldBeThisNumberOfElements(List elements, int count) {
        assertThat("Количество элементов не соответствует ожидаемому", elements.size(), is(count));
    }

    @Step("Кликаем по элементу {0}")
    public void clickOn(HtmlElement element) {
        element.click();
    }

    @Step("Кликаем по элементу {0} {1} раз")
    public void clickOn(HtmlElement element, int n) {
        for (int i = 0; i < n; i++) {
            element.click();
        }
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

    @Step("Элемент «{0}» не должен содержать текст «{1}»")
    public void shouldNotHaveText(HtmlElement element, String text) {
        assertThat("Текст в элементе должен быть другим", element, not(hasText(text)));
    }

    @Step("Элементы в «{0}» не должны содержать текст «{1}»")
    public void shouldNotHaveText(List<? extends WebElement> elements, String text) {
        assertThat("Текст в элементе должен быть другим", new ArrayList<>(elements), everyItem(not(hasText(text))));
    }

    @Step("Ждём элемент «{0}» максимум «{1}» секунд(ы)")
    public void waitUntilElementReady(HtmlElement element, int timeOut) {
        (new WebDriverWait(driver, timeOut))
                .until(ExpectedConditions.and(
                        ExpectedConditions.not(ExpectedConditions.stalenessOf(element)),
                        ExpectedConditions.visibilityOf(element))
                );
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
