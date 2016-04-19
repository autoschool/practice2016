package ru.qatools.school.steps.websteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.pages.blocks.WeatherWidget;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

import static java.lang.String.format;
import static org.hamcrest.core.Is.is;
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

    @Step("Нажимаем на кнопку +")
    public void pressAddWidgetButton() {
        onMainPage().getNewWidgetButton().click();
    }

    @Step("Должны видеть на странице «{0}»")
    public void shouldSeeElement(WebElement element) {
        assertThat("Должны видеть элемент", element, isDisplayed());
    }

    @Step("Должны видеть у элемента {0} текст «{1}»")
    public void shouldHaveText(WebElement element, String expectedText) {
        assertThat("Должны видеть текст", element.getText(), is(expectedText));
    }

    @Step("Количество виджетов на странице должно быть равным {0}")
    public void shouldHaveWidgetNumber(int quantityOfWidgets) {
        assertThat("Название виджета должно быть", onMainPage().getWeatherWidgetList().size(),
                is(quantityOfWidgets));
    }

    @Step("Все виджеты на странице должны быть видимы")
    public void shouldSeeAllWidgets() {
        List<WeatherWidget> weatherWidgets = onMainPage().getWeatherWidgetList();
        for (WeatherWidget widget : weatherWidgets) {
            shouldSeeElement(widget);
        }
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
