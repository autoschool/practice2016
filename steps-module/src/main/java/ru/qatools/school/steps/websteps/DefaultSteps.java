package ru.qatools.school.steps.websteps;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.qatools.school.data.DataPatterns;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.pages.PageMethods;
import ru.qatools.school.pages.blocks.WeatherWidget;
import ru.qatools.school.pages.blocks.widgetblocks.WidgetTitle;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.ArrayList;

import static java.lang.String.format;
import static org.cthul.matchers.CthulMatchers.containsPattern;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.*;
import static ru.yandex.qatools.matchers.webdriver.TextMatcher.text;

/**
 * Created by kurau.
 */
public class DefaultSteps {

    public static final String MAIN_PAGE = "http://weather.lanwen.ru/#?cities=%s";
    public static final String MAIN_PAGE_WITHOUT_PARAMETERS = "http://weather.lanwen.ru";

    private WebDriver driver;
    private WebDriverWait webDriverWait;
    private PageMethods pageMethods;

    public DefaultSteps(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, 5);
        pageMethods = new PageMethods(driver);
    }

    @Step("Открываем главную страницу для города «{0}»")
    public void openMainPageWithCity(String city) {
        driver.get(format(MAIN_PAGE, city));
    }

    @Step("Открываем главную страницу приложения")
    public void openMainPageWithoutParameters(){
        driver.get(MAIN_PAGE_WITHOUT_PARAMETERS);
    }

    @Step("Должны видеть на странице «{0}»")
    public void shouldSee(HtmlElement element) {
        assertThat("Должны видеть элемент", element, allOf(exists(),isDisplayed()));
    }

    @Step("Должны видеть в заголовке виджета текст: {0}")
    public void shouldSeeCityInWidgetsTitle(String city){
        assertThat("Должны видеть текст",
                onMainPage().getWeatherWidgets().get(0).getWidgetTitle().getCityName(), hasText(city));
    }

    @Step("Число виджетов на странице должно быть равно: {0}")
    public void shouldHaveWidgetNumberOnMainPage(int numberOfWidgets){
        assertThat("Должны видеть виджетов", onMainPage().getWeatherWidgets(), hasSize(numberOfWidgets));
    }

    @Step("Изменяем город в заголовке")
    public void changeWidgetTitle(WeatherWidget weatherWidget, String newCity){
        pageMethods.clickOn(weatherWidget.getWidgetTitle().getCityName());
        pageMethods.enterText(newCity, weatherWidget.getWidgetTitle().getCityName());
        weatherWidget.getWidgetTitle().getCityName().sendKeys(Keys.RETURN);
    }

    @Step("Ввод текста в заголовок и ожидание появления списка автозаполнения")
    public void suggestList(String part, WidgetTitle widgetTitle){
        pageMethods.clickOn(widgetTitle.getCityName());
        pageMethods.enterText(part, widgetTitle.getCityName());
        webDriverWait.until(ExpectedConditions.elementToBeClickable(widgetTitle.getSuggestedCitiesList()));
    }

    @Step("В списке должны быть только города, содержащие {0}")
    public void shouldOnlySeeCitiesContaining(String part){
        assertThat("", new ArrayList<>(onMainPage().getWeatherWidgets().get(0).getWidgetTitle().getSuggestedCities()),
                everyItem(text(containsString(part))));
    }

    @Step("Данные в {0} отображаются в формате {1}")
    public void shouldMatchPattern(HtmlElement element, DataPatterns pattern){
        assertThat("Данные отображаются в неверном формате", element.getText(), containsPattern(pattern.toString()));
    }

    @Step("Должны видеть в заголовке страницы {0}")
    public void shouldSeeTitle(String text){
        assertThat("Неверный заголовок страницы", driver.getTitle(), is(text));
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
