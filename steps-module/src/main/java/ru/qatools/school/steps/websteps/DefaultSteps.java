package ru.qatools.school.steps.websteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.pages.PageMethods;
import ru.qatools.school.pages.blocks.WeatherWidget;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import static java.lang.String.format;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

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
        assertThat("Должны видеть элемент", element, isDisplayed());
    }

    @Step("Должны видеть в заголовке виджета текст: {0}")
    public void shouldSeeRightCityInWidgetsTitle(String city){
        assertThat("Должны видеть текст",
                onMainPage().getWeatherWidget().get(0).getWidgetTitle().getCityName(), hasText(city));
    }

    @Step("Число виджетов на странице должно быть равно: {0}")
    public void shouldHaveWidgetNumberOnMainPage(int numberOfWidgets){
        assertThat("Должны видеть виджетов", onMainPage().getWeatherWidget().size(), is(numberOfWidgets));
    }

    @Step("Должны видеть дату соответствующую системной")
    public void shouldSeeDateEqualToSystemDate() {
        assertThat("Время или дата не соответствую установленным в системе",
                onMainPage().getWeatherWidget().get(0).getWidgetTitle().getCurrentTime(), hasText(pageMethods.getCurrentDate()));
    }

    @Step("Должны изменить город в заголовке")
    public void changeWidgetTitle(String oldCity, String newCity){
        WeatherWidget element = pageMethods.findWidgetByName(oldCity);
        pageMethods.clickOn(element.getWidgetTitle().getCityName());
        pageMethods.enterText(newCity, element.getWidgetTitle().getCityNameEditable());
    }

    @Step("Должны увидеть список автозаполнения")
    public void suggestList(String part){
        pageMethods.clickOn(onMainPage().getWeatherWidget().get(0).getWidgetTitle().getCityName());
        onMainPage().getWeatherWidget().get(0).getWidgetTitle().getCityNameEditable().clear();
        onMainPage().getWeatherWidget().get(0).getWidgetTitle().getCityNameEditable().sendKeys(part);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(onMainPage().getWeatherWidget().get(0)
                .getWidgetTitle().getSuggestedCitiesList()));
    }

    @Step("В списке должны быть только города, соответствующие введенному значению")
    public void suggestedCitiesListItems(String part){
        assertThat("В списке отображается неподходящий элемент",
                onMainPage().getWeatherWidget().get(0).getWidgetTitle().getSugesstedCities(),
                everyItem(pageMethods.regexMatcher(part)));
    }

    @Step("Температура должна отображаться в правильном формате")
    public void shouldSeeMatchingValueForTemperature(String pattern){
        assertThat("Температура отображается в неверном формате", onMainPage().getWeatherWidget().get(0).getWidgetText().getTemperature(),
                pageMethods.regexMatcher(pattern));
    }

    @Step("Погодные данные должны отображаться в правильном формате")
    public void shouldSeeMatchingValueForWeatherData(int index, String pattern){
        assertThat("Погодные данные отображается в неверном формате", onMainPage().getWeatherWidget().get(0).getWidgetText()
                .getWeatherData().get(index), pageMethods.regexMatcher(pattern));
    }

    @Step("Должны видеть правильный текст в заголовке страницы")
    public void shouldSeeTitle(String text){
        assertThat("Неверный заголовок страницы", driver.getTitle(), is(text));
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
