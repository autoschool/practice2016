package ru.qatools.school.steps.websteps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.MainPage;
import ru.yandex.qatools.allure.annotations.Step;

import static java.lang.String.format;
import static org.hamcrest.Matchers.is;
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

    @Step("Должны видеть title для заданного города")
    public void shouldSeeCity(WebElement element, String city) {
        assertThat("Должны видеть нужный город", element.findElement(By.className("inplace")).getText() ,is(city));
    }

    @Step("Нажимаем кнопку «Добавить виджет»")
    public void clickAddWidget() {
        driver.findElement(By.className("new-card")).click();
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
