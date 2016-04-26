package ru.qatools.school.steps.websteps;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.pages.blocks.WeatherWidget;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static java.lang.String.format;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;


public class DefaultSteps {

    private static final String MAIN_PAGE_WITH_CITY = "http://weather.lanwen.ru/#?cities=%s";
    private static final String MAIN_PAGE = "http://weather.lanwen.ru";

    private WebDriver driver;
    private WebDriverWait wait;

    public DefaultSteps(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 100);
    }

    @Step("Открываем главную страницу без указания города")
    public void openMainPageWithoutCity(){
        driver.get(MAIN_PAGE);
    }

    @Step("Открываем главную страницу для города «{0}»")
    public void openMainPageWithCity(String city) {
        driver.get(format(MAIN_PAGE_WITH_CITY, city));
    }

    @Step("Должны видеть на странице «{0}»")
    public void shouldSee(HtmlElement element) {
        assertThat("Должны видеть элемент", element, isDisplayed());
    }

    @Step("Должны видеть элемент {0} с текстом {1}")
    public  void shouldSeeText(HtmlElement element, String text) {
        shouldSee(element);
        assertThat("Должны видеть другой текст у элемента", element, hasText(text));
    }

    @Step("Нажимаем на элемент")
    public void clickElement(HtmlElement element) {
        element.click();
    }

    @Step("Изменяем текущий город на новый")
    public void changeCity(WeatherWidget widget, String newCity){
        clickElement(widget.getWidgetTitle().getCity());
        widget.getWidgetTitle().getEnterCity().clear();
        widget.getWidgetTitle().getEnterCity().sendKeys(newCity);
        widget.getWidgetTitle().getEnterCity().sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.urlContains(newCity));
    }

    @Step("Очищаем поле ввода")
    public void clearCityEntryField(WeatherWidget widget) {
        widget.getWidgetTitle().getEnterCity().clear();
    }


    @Step("Должны видеть {0} виджетов")
    public void shouldSeeCountOfWidgets(int widgetCount) {
        assertThat("Должны видеть другое кол-во виджетов", widgetCount, equalTo(onMainPage().getWeatherWidgetList().size()));
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
