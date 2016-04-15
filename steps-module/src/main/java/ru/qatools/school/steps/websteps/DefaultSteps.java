package ru.qatools.school.steps.websteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.MainPage;
import ru.yandex.qatools.allure.annotations.Step;

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

    @Step("Должны видеть на странице «{0}»")
    public void shouldSee(WebElement element) {
        assertThat("Должны видеть элемент", element, isDisplayed());
    }

    @Step("Должны видеть виджет с городом «{0}»")
    public void shouldSeeWidgetWithCity(String actual, String expected) {
        assertThat("Ожидали другой город", actual, is(expected));
    }

    @Step("Добавляем еще один виджет")
    public void addNewWidget() {
        onMainPage().getAddWidgetButton().click();
    }

    @Step("Должны увидеть {0} виджета(ов)")
    public void shouldSeeThisNumberOfWidgets(int count) {
        assertThat("Количество виджетов не соответствует ожидаемому", onMainPage().getWeatherWidget().size(), is(count));
    }

    @Step("Последнему виджету назначаем город «{0}»")
    public void editLastWidget(String cityName) {
        onMainPage().getWeatherWidget().get(0).getWidgetTitle().getCityNameInLastWidget().click();
        onMainPage().getWeatherWidget().get(0).getWidgetTitle().getCityNameInLastWidget().clear();
        onMainPage().getWeatherWidget().get(0).getWidgetTitle().getCityNameInLastWidget().sendKeys(cityName);

//        Actions actions = new Actions(driver);
//        actions.moveToElement(onMainPage().getPopUpMenu(), 1, 1).click().build().perform();
        onMainPage().getPopUpMenu().click();
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
