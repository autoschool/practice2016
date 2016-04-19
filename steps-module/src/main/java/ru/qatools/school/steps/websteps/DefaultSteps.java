package ru.qatools.school.steps.websteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.pages.blocks.WeatherWidget;
import ru.yandex.qatools.allure.annotations.Step;

import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;
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

    @Step("{0} должен называться «{1}»")
    public void shouldSeeWidgetWithTitle(WeatherWidget widget, String cityName) {
        assertThat(widget.getWidgetTitle().getWeatherTitle(), hasText(cityName));
    }

    @Step("Нажимаем на кнопку добавления виджета")
    public void pressNewWidgetButton() {
        onMainPage().getNewWidgetButton().click();
    }

    @Step("Количество виджетов должно быть равным {0}")
    public void shouldHaveWidgetsCount(int widgetsCount) {
        assertThat("Количество виджетов должно быть", onMainPage().getWeatherWidgets().size(), is(widgetsCount));
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
