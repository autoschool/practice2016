package ru.qatools.school.steps.websteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.pages.blocks.WeatherWidget;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.element.Button;

import java.util.List;

import static java.lang.String.format;
import static java.lang.Comparable.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.Every.everyItem;
import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.exists;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

/**
 * Created by kurau.
 *
 * @author arrumm (Arkhipov Roman)
 */
public class DefaultSteps {

    private static final String MAIN_PAGE = "http://weather.lanwen.ru/#?cities=%s";

    private static final String MAIN_PAGE_WOUTPARS = "http://weather.lanwen.ru/";

    private WebDriver driver;

    public DefaultSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открываем главную страницу для города «{0}»")
    public void openMainPageWithCity(String city) {
        driver.get(format(MAIN_PAGE, city));
    }

    @Step("Открываем главную страницу как обычный пользователь")
    public void openMainPageWithoutParams() {
        driver.get(MAIN_PAGE_WOUTPARS);
    }

    @Step("Должны видеть на странице «{0}»")
    public void shouldSeeElement(WebElement element) {
        assertThat("Должны видеть элемент", element, both(exists()).and(isDisplayed()));
    }

    @Step("Должны видеть на странице все элементы списка «{0}»")
    public void shouldSeeAllElementFromList(List<? extends WebElement> wwList) {
        assertThat("Видим не все элементы", (List<WebElement>)wwList, everyItem(both(exists()).and(isDisplayed())));
    }

    @Step("Текст «{0}» элемента должен быть «{1}»")
    public void shouldSeeElementTextIsSameToText(WebElement webElement, String text) {
        assertThat("Текст элемента и ожидаемый не совпадают", webElement, hasText(text));
    }

    @Step("Должен кликнуться элемент «{0}»")
    public void onClickElement(WebElement element) {
        element.click();
    }

    @Step("Должна кликнуться кнопка «{0}»")
    public void onClickButton(Button element) {
        element.click();
    }

    @Step("Количество виджетов на странице должно быть «{0}»")
    public void shouldBeWidgetsQuantityOnPage(List<WeatherWidget> wwList, int widgetsQuantity) {
        assertThat("Количество виджетов на странице не равно ожидаемому", wwList, hasSize(widgetsQuantity));
    }

    public WeatherWidget getFirstWidget() {
        return onMainPage().getWeatherWidgetList().get(0);
    }

    public WebElement getNewCardButton() {
        return onMainPage().getNewCardButton();
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
