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

import java.util.List;

import static java.lang.String.format;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

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
        assertThat("Должны видеть элемент", element, isDisplayed());
    }

    @Step("Должны не видеть на странице элемент «{0}»")
    public void shouldNotSee(WebElement element) {
        assertThat("Должны не видеть элемент", element, not(isDisplayed()));
    }

    @Step("Должны видеть на странице элементы «{0}»")
    public void shouldSee(List<? extends WebElement> elements) {
        for (WebElement element : elements) {
            assertThat("Должны видеть элемент", element, isDisplayed());
        }
    }

    @Step("Должны увидеть «{1}» элементов(ов) в списке «{0}»")
    public void shouldBeThisNumberOfElements(List elements, int count) {
        assertThat("Количество элементов не соответствует ожидаемому", elements.size(), is(count));
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

    @Step("Ждём элемент «{0}» максимум «{1}» секунд(ы)")
    public void waitUntilElementReady(HtmlElement element, int timeOut) {
//        int pause = 10; // milliseconds
//        for (int milliSeconds = 0; ; milliSeconds += pause) {
//            try {
//                element.isDisplayed();
//                System.out.println(element.getText());
//                break;
//            } catch (StaleElementReferenceException e) {
//                if (milliSeconds > timeOut * 1000) {
//                    System.out.println("Всё пропало!");
//                    throw e;
//                }
//                try {
//                    System.out.println("Ждём: " + milliSeconds);
//                    Thread.sleep(pause);
//                } catch (InterruptedException e2) {
//                    e.printStackTrace();
//                }
//            }
//        }

        (new WebDriverWait(driver, timeOut)).
                until(ExpectedConditions.and(
                        ExpectedConditions.not(ExpectedConditions.stalenessOf(element)),
                        ExpectedConditions.visibilityOf(element))
                );
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
