package ru.qatools.school.steps.websteps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.pages.blocks.WeatherWidget;
import ru.yandex.qatools.allure.annotations.Step;

import static java.lang.String.format;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
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

    @Step("Проверяем что заголовок виджета это запрошенный город")
    public void shouldSeeTitleWidgetEqualCity(String city) {
        assertThat("Заголовок города равен названию виджета",
                driver.findElement(By.cssSelector("#text")).getText(), equalTo(city));
    }

    @Step("Добавляем виджет на страницу")
    public void addWidgetOnMainPage() {
        driver.findElement(By.cssSelector("div>.new-card")).click();
    }

    @Step("Проверяем что на главной странице три виджета")
    public void shouldSeeAddedWidgets(int size) {
        assertThat("Должно быть два виджета", size, is(3));
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
