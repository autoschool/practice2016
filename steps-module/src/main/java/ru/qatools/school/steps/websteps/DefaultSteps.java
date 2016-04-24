package ru.qatools.school.steps.websteps;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.pages.blocks.WeatherWidget;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import static java.lang.String.format;
import static org.cthul.matchers.CthulMatchers.matchesPattern;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

/**
 * Created by kurau.
 */
public class DefaultSteps {

    private static final String MAIN_PAGE_FOR_CITY = "http://weather.lanwen.ru/#?cities=%s";
    private static final String EMPTY_MAIN_PAGE = "http://weather.lanwen.ru";

    private WebDriver driver;
    private Actions mouseActions;
    public WebDriverWait wait;

    public DefaultSteps(WebDriver driver) {
        this.driver = driver;
        this.mouseActions = new Actions(driver);
        this.wait = new WebDriverWait(driver, 3);
    }

    @Step("Открываем пустую главную страницу")
    public void openEmptyMainPage() {
        driver.get(EMPTY_MAIN_PAGE);
    }

    @Step("Открываем главную страницу для города «{0}»")
    public void openMainPageWithCity(String city) {
        driver.get(format(MAIN_PAGE_FOR_CITY, city));
    }

    @Step("Кликаем по элементу {0}")
    public void click(WebElement element) {
        element.click();
    }

    @Step("Наводим курсор мыши на элемент {0}")
    public void hover(WebElement element) {
        mouseActions.moveToElement(element).perform();
    }

    @Step("Должны видеть на странице «{0}»")
    public void shouldSee(WebElement element) {
        assertThat("Должны видеть элемент", element, isDisplayed());
    }

    @Step("Количество виджетов на странице должно быть {0}")
    public void shouldSeeWidgetsQuantity(int i) {
        assertThat("Количество виджетов на странице должно быть другим",
                onMainPage().getWeatherWidgetList().size(), is(i));
    }

    @Step("Должны видеть у элемента {0} текст {1}")
    public void shouldSeeText(HtmlElement element, String expectedText) {
        assertThat("Текст у элемента должен быть другим", element.getText(), is(expectedText));
    }

    @Step("Должны видеть строку {0} в формате, который соответствует {1}")
    public void shouldMatchRegex(String checkedString, String regex) {
        assertThat("Текст не соответствует ожидаемому формату", checkedString, matchesPattern(regex));
    }

    @Step("Должны видеть у элемента {0} цвета {1}")
    public void shouldSeeColor(WebElement element, String color) {
        assertThat("Не тот цвет у элемента", element.getCssValue("background-color"), is(color));
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }

    @Step("Меняем текст в поле названия города кликом по нему, удалением старого текста и вводом нового текста")
    public void changeCityNameWithEnterKey(WeatherWidget widget, String cityName) {
        click(widget.getWidgetTitle().getCity());
        widget.getWidgetTitle().getCity().clear();
        widget.getWidgetTitle().getCity().sendKeys(cityName);
        widget.getWidgetTitle().getCity().sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.not(ExpectedConditions.stalenessOf(widget.getWidgetTitle().getCity())));
    }
}
