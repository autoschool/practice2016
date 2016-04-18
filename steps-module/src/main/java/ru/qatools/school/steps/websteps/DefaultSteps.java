package ru.qatools.school.steps.websteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.String.format;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.CombinableMatcher.both;
import static org.hamcrest.core.Every.everyItem;
import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;
import static ru.yandex.qatools.htmlelements.matchers.common.DoesElementExistMatcher.exists;
import static ru.yandex.qatools.htmlelements.matchers.common.HasTextMatcher.hasText;

/**
 * @author kurau
 * @author gladnik (Nikolai Gladkov)
 */
public class DefaultSteps {

    private static final String MAIN_PAGE = "http://weather.lanwen.ru/#?cities=%s";
    private static final String BLANK_PAGE = "about:blank";

    private WebDriver driver;

    public DefaultSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открываем пустую страницу")
    public void openBlankPage() {
        driver.get(BLANK_PAGE);
    }

    @Step("Открываем главную страницу для города «{0}»")
    public void openMainPageWithCity(String city) {
        driver.get(format(MAIN_PAGE, city));
    }

    @Step("Должны видеть на странице «{0}»")
    public void shouldSee(HtmlElement element) {
        assertThat("Должны видеть элемент", element, isDisplayed());
    }

    @Step("Должны видеть каждый из «{0}»")
    public void shouldSee(List<? extends WebElement> elements) {
        assertThat("Должны видеть элементы", new ArrayList<>(elements), everyItem(both(exists()).and(isDisplayed())));
    }

    @Step("Текст элемента «{0}» должен быть «{1}»")
    public void expectText(HtmlElement element, String text) {
        assertThat("Неверный текст элемента", element, hasText(text));
    }

    @Step("Подождём секунду - наш тест слишком быстрый")
    public void waitASecond() throws InterruptedException {
        Thread.sleep(1000);
    }

    @Step("Количество элементов «{0}» должно быть «{1}»")
    public void expectSize(Collection<? extends WebElement> elements, int size) {
        assertThat("Неверное количество элементов", elements, hasSize(size));
    }

    @Step("Кликаем по [{0}]")
    public void clickOn(HtmlElement element) {
        element.click();
    }
}
