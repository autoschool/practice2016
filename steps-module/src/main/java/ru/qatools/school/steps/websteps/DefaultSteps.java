package ru.qatools.school.steps.websteps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.MainPage;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.regex.Pattern;

import static java.lang.String.format;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;


public class DefaultSteps {

    public static final String MAIN_PAGE = "http://weather.lanwen.ru/#?cities=%s";
    public static final String EMPTY_MAIN_PAGE = "http://weather.lanwen.ru/";

    private WebDriver driver;

    public DefaultSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открываем главную страницу")
    public void openMainPage() {
        driver.get(EMPTY_MAIN_PAGE);
    }

    @Step("Открываем главную страницу для города «{0}»")
    public void openMainPageWithCity(String city) {
        driver.get(format(MAIN_PAGE, city));
    }

    @Step("Открываем главную страницу для двух городов: «{0}» и «{1}»")
    public void openMainPageWithTwoCities(String city1, String city2) {
        driver.get(format(MAIN_PAGE, city1 + ","+city2));
    }

    @Step("Должны видеть на странице «{0}»")
    public void shouldSee(WebElement element) {
        assertThat("Должны видеть элемент", element, isDisplayed());
    }

    @Step("Элемент «{1}» должен содержать строку «{0}»")
    public void strShouldBeInElement(String str, WebElement currElement){
        assertThat("Строка в элементе должна совпадать с ожидаемой", currElement.getText(), is(str));
    }

    @Step("Клик на кнопку «{0}»")
    public void clickButton(WebElement el){
        el.click();
    }

    @Step("Число «{0}» должно совпадать с количеством виджетов на странице")
    public void shouldBeEqualsCountOfWidgets(int expectCount){
        assertEquals("Количество виджетов на странице должно совпадать с ожидаемым", expectCount, onMainPage().getWeatherWidgets().size());
    }

    @Step("Поле элемента «{0}» не должно быть пустым")
    public void shouldBeNotEmpty(WebElement currElement){
        assertThat(currElement.getText(),not(isEmptyOrNullString()));
    }

    @Step("Поле элемента «{0}» должно соответствовать регулярному выражению в «{1}»")
    public void shouldMatchToRegExp(WebElement currElement, String regExp){
        System.out.println("Text = " + currElement.getText() + " result = " + Pattern.matches(regExp, currElement.getText()));
        assertTrue("Содержиме элемента должно соответствовать шаблону", Pattern.matches(regExp, currElement.getText()));
    }


    @Step("Значение элемента «{0}» должно принадлежать интервалу «{1}» и «{2}»")
    public void shouldBeBetweenMinAndMax(WebElement currElement, double d1, double d2){
        assertThat(Double.valueOf(currElement.getText()),greaterThanOrEqualTo(Math.min(d1, d2)));
        assertThat(Double.valueOf(currElement.getText()),lessThanOrEqualTo(Math.max(d1, d2)));
    }


    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
