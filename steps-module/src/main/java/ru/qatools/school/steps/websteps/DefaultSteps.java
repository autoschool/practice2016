package ru.qatools.school.steps.websteps;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.MainPage;
import ru.yandex.qatools.allure.annotations.Step;

import static java.lang.String.format;
import static org.junit.Assert.*;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

/**
 * Created by kurau.
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

    @Step("Должны видеть на странице «{0}»")
    public void shouldSee(WebElement element) {
        assertThat("Должны видеть элемент", element, isDisplayed());
    }

    @Step("Должны видеть виджет с заданым городом «{0}»")
    public void shouldSeeWidgetWithCity(String city) {
        assertTrue("Должен быть город: " + city + ", но видим: " + onMainPage().getWeatherWidget().get(0).findElement(By.cssSelector(".inplace")).getText(), onMainPage().getWeatherWidget().get(0).findElement(By.cssSelector(".inplace")).getText().equals(city));
    }

    @Step("Нажатие кнопки Добавить + в виджет тянется заданный город")
    public void addNewWidgetWithCity(String city) {
        driver.findElement(By.cssSelector(".new-card")).click();
        onMainPage().getWeatherWidget().get(0).findElement(By.cssSelector(".inplace")).click();
        onMainPage().getWeatherWidget().get(0).findElement(By.cssSelector(".inplace_editable")).clear();
        onMainPage().getWeatherWidget().get(0).findElement(By.cssSelector(".inplace_editable")).sendKeys(city);
        onMainPage().getWeatherWidget().get(0).sendKeys(Keys.ENTER);
    }

    @Step("Должны увидеть добавленный виджет")
    public void shouldSeeNewWidget(int beforeAddWidget, int afterAddWidget) {
        assertEquals(beforeAddWidget + 1, afterAddWidget);
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
