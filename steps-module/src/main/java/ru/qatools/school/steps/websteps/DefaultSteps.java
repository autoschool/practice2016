package ru.qatools.school.steps.websteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.MainPage;
import ru.yandex.qatools.allure.annotations.Step;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
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

    @Step("Создаем виджит")
    public void addWidgit() {
        onMainPage().getAddWidgitButton().click();
    }
    @Step("Желаемый город должен совпасть с действительным")
    public void shouldSeeCorrectCityName(String city) {
        assertEquals(onMainPage().getWeatherWidgetList().get(0).getWidgetTitle().getCity().getText(), city);
    }

    @Step("Количество виджетов должно быть {0}")
    public void widgetCountShouldBe(int new_count) {
        assertEquals("Неправильное количество", onMainPage().getWeatherWidgetList().size(), new_count);
    }
    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
