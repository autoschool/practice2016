package ru.qatools.school.steps.websteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.pages.blocks.WeatherWidget;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;
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

    @Step("Добавить виджет на страницу")
    public void addOneWidget()
    {
        onMainPage().getAddWidgetButton().click();
    }

    @Step("Должны увидеть {0} виджета")
    public void shouldSeeWidgets(int count){
        assertThat(onMainPage().getWeatherWidget().size(), is(count));
    }

    @Step("Получить количество виджетов на странице")
    public int getCountWidgets(){
        return onMainPage().getWeatherWidget().size();
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }

    @Step("Должны увидеть виджет с городом {0}")
    public void shouldSeeCurrentCity(String city) {
        assertThat(onMainPage().getWeatherWidget().get(0).getWidgetTitle().getCityName().getText(),is (city));
    }
}
