package ru.qatools.school.steps.websteps;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.pages.blocks.WeatherWidget;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.String.format;
import static org.cthul.matchers.CthulMatchers.matchesPattern;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.exists;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

/**
 * @author kormyshov
 */
public class DefaultSteps {

    public static final String EMPTY_PAGE = "http://weather.lanwen.ru";
    public static final String MAIN_PAGE  = "http://weather.lanwen.ru/#?cities=%s";

    private WebDriver driver;
    private WebDriverWait wait;

    public DefaultSteps(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5);
    }

    @Step("Открываем главную страницу")
    public void openEmptyMainPage(){
        driver.get(EMPTY_PAGE);
    }

    @Step("Открываем главную страницу для города «{0}»")
    public void openMainPageWithCity(String city) {
        driver.get(format(MAIN_PAGE, city));
    }

    @Step("Открываем главную страницу для городов «{0}» и «{1}»")
    public void openMainPageWithCities(String city1, String city2){
        driver.get(format(MAIN_PAGE, city1 + "," + city2));
    }

    @Step("Должны видеть элемент «{0}»")
    public void shouldSee(WebElement element) {
        assertThat("Должны видеть элемент", element, both(exists()).and(isDisplayed()));
    }

    @Step("Должно быть «{1}» элементов в «{0}»")
    public void shouldHaveSize(Collection<? extends WebElement> elements, int size){
        assertThat("Неверное количество элементов", elements, hasSize(size));
    }

    @Step("Должен видеть каждый из «{0}»")
    public void shouldSee(List<? extends WebElement> elements) {
        assertThat("Должны видеть элементы", new ArrayList<>(elements), everyItem(both(exists()).and(isDisplayed())));
    }

    @Step("Должен быть текст «{1}» у элемента «{0}»")
    public void shouldSeeText(WebElement element, String text) {
        assertThat("Неверный текст элемента", element.getText(), is(text));
    }

    @Step("Ни один элемент «{0}» не должен быть undefined")
    public void shouldNotUndefined(List<? extends WebElement> elements){
        ArrayList<String> lst = new ArrayList<>();
        for(WebElement el : elements) lst.add(el.getText());
        assertThat("Не должно быть undefined", lst, everyItem(not(is("undefined"))));
    }

    @Step("Кликаем по «{0}»")
    public void clickOn(HtmlElement element){
        element.click();
    }

    @Step("Строка «{0}» должна быть формата «{1}»")
    public void shouldMatchRegex(String string, String regex){
        assertThat("Строка не удовлетворяет формату", string, matchesPattern(regex));
    }

    @Step("Меняем текст в поле ввода города и нажимаем Enter")
    public void changeCityNameAndPressEnter(WeatherWidget widget, String city){
        clickOn(widget.getWidgetTitle().getCityName());
        widget.getWidgetTitle().getCityName().clear();
        widget.getWidgetTitle().getCityName().sendKeys(city);
        widget.getWidgetTitle().getCityName().sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.not(ExpectedConditions.stalenessOf(widget.getWidgetTitle().getCityName())));
    }

    @Step("Меняем текст в поле ввода города")
    public void changeCityName(WeatherWidget widget, String city){
        clickOn(widget.getWidgetTitle().getCityName());
        widget.getWidgetTitle().getCityName().clear();
        widget.getWidgetTitle().getCityName().sendKeys(city);
        wait.until(ExpectedConditions.not(ExpectedConditions.stalenessOf(widget.getWidgetTitle().getCitySuggest())));
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
