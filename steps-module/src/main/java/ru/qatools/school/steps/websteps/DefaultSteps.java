package ru.qatools.school.steps.websteps;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.MainPage;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

/**
 * Created by kurau.
 */
public class DefaultSteps {

    private static final String MAIN_PAGE_WITH_WIDGET = "http://weather.lanwen.ru/#?cities=%s";
    private static final String MAIN_PAGE = "http://weather.lanwen.ru";

    private WebDriver driver;


    public DefaultSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открываем главную страницу")
    public void openMainPage() {
        driver.get(MAIN_PAGE);
    }

    @Step("Открываем главную страницу для города «{0}»")
    public void openMainPageWithCity(String city) {
        driver.get(format(MAIN_PAGE_WITH_WIDGET, city));
    }

    @Step("Должны видеть на странице «{0}»")
    public void shouldSee(WebElement element) {
        assertThat("Должны видеть элемент", element, isDisplayed());
    }

    @Step("Добавить виджет на страницу")
    public void addOneWidget() {
        onMainPage().getButtonAddWidget().click();
    }

    @Step("Должны увидеть n виджетов")
    public void shouldSeeWidgets(int count) {
        assertThat(onMainPage().getWeatherWidget().size(), is(count));
    }

    @Step("Получить количество виджетов на странице")
    public int getCountWidgets() {
        return onMainPage().getWeatherWidget().size();
    }

    @Step("Должны увидеть виджет с городом {0}")
    public void shouldSeeCurrentCity(String city) {
        assertThat(onMainPage().getWeatherWidget().get(0).getWidgetTitle().getCityName().getText(), is(city));
    }
    
    @Step("Должны увидеть кнопку добавления виджета")
    public void shouldSeeButtonAddWidget() {
        assertThat("Должны видеть кнопку добавления виджета", onMainPage().getButtonAddWidget(), isDisplayed());
    }

    @Step("Удаление виджета со страницы")
    public void deleteWidget() {
        onMainPage().getWeatherWidget().get(0).getButtonDeleteWidget().click();
    }

    @Step("Пишем название города в виджете")
    public void writeCityName(String city) {
        WebElement input = onMainPage().getWeatherWidget().get(0).getWidgetTitle().getCityName();
        input.click();
        input.clear();
        input.sendKeys(city);
        input.sendKeys(Keys.ENTER);
    }
    
    @Step("Должны видеть температуру")
    public void shouldSeeCurrentTemperature(){
        assertThat(onMainPage().getWeatherWidget().get(0).getWidgetText().getCurrentTemperature(), isDisplayed());
    }
    
    @Step("Должны видеть картинку погоды")
    public void shouldSeeWeatherImage(){
        assertThat("Не отображается картинка погоды",onMainPage().getWeatherWidget().get(0).getWidgetText().getWeatherImage(), isDisplayed());
    }

    @Step("Должны видеть инфо о рассвете")
    public void shouldSeeSunriseInfo(){
        assertThat("Не отображается надпись sunrise", onMainPage().getWeatherWidget().get(0).getWidgetText().getSunriseInfoLine().getInfoTitle(), isDisplayed());
        assertThat("Не отображается время рассвета", onMainPage().getWeatherWidget().get(0).getWidgetText().getSunriseInfoLine().getInfoValue(), isDisplayed());
        assertThat("Не отображается картинка рассвета", onMainPage().getWeatherWidget().get(0).getWidgetText().getSunriseInfoLine().getInfoImage(), isDisplayed());

    }

    @Step("Должны видеть инфо о закате")
    public void shouldSeeSunsetInfo(){
        assertThat("Не отображается надпись sunset", onMainPage().getWeatherWidget().get(0).getWidgetText().getSunsetInfoLine().getInfoTitle(), isDisplayed());
        assertThat("Не отображается время заката", onMainPage().getWeatherWidget().get(0).getWidgetText().getSunsetInfoLine().getInfoValue(), isDisplayed());
        assertThat("Не отображается картинка заката", onMainPage().getWeatherWidget().get(0).getWidgetText().getSunsetInfoLine().getInfoImage(), isDisplayed());

    }

    @Step("Должны видеть инфо о ветре")
    public void shouldSeeWindInfo(){
        assertThat("Не отображается надпись wind", onMainPage().getWeatherWidget().get(0).getWidgetText().getWindInfoLine().getInfoTitle(), isDisplayed());
        assertThat("Не отображается значение скорости ветра", onMainPage().getWeatherWidget().get(0).getWidgetText().getWindInfoLine().getInfoValue(), isDisplayed());
        assertThat("Не отображается картинка ветра", onMainPage().getWeatherWidget().get(0).getWidgetText().getWindInfoLine().getInfoImage(), isDisplayed());

    }

    @Step("Должны видеть инфо о влажности")
    public void shouldSeeHumidityInfo(){
        assertThat("Не отображается надпись humidity", onMainPage().getWeatherWidget().get(0).getWidgetText().getSunsetInfoLine().getInfoTitle(), isDisplayed());
        assertThat("Не отображается значение влажности", onMainPage().getWeatherWidget().get(0).getWidgetText().getSunsetInfoLine().getInfoValue(), isDisplayed());
        assertThat("Не отображается картинка влажности", onMainPage().getWeatherWidget().get(0).getWidgetText().getSunsetInfoLine().getInfoImage(), isDisplayed());

    }

    @Step("Должны увидеть URL с городом {0}")
    public void shouldSeeURLWithThisCity(String city){
                assertThat(driver.getCurrentUrl(), is(format(MAIN_PAGE_WITH_WIDGET, city)));
    }

    @Step("Должны видеть URL со значением параметра: {0}")
    public void shouldSeeURLParameterValue(String parameterValue){
        assertThat(driver.getCurrentUrl(), is(format(MAIN_PAGE_WITH_WIDGET, parameterValue)));
    }


    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
