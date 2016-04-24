package ru.qatools.school.steps.websteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.pages.blocks.NewWeatherWidgetCard;
import ru.qatools.school.pages.blocks.WeatherWidget;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.element.Button;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static org.hamcrest.core.Every.everyItem;
import static org.hamcrest.core.Is.is;
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

    @Step("Open main page with weather of {0}")
    public void openMainPageWithCity(String city) {
        driver.get(format(MAIN_PAGE, city));
    }

    @Step("Should see weather widget")
    public void shouldSeeWidget(WeatherWidget element) {
        assertThat("Should see element", element, isDisplayed());
    }

    @Step("Should see \"add new widget\" button")
    public void shouldSeeAddWidgetButton(NewWeatherWidgetCard element) {
        assertThat("Should see button", element, isDisplayed());
    }

    @Step("Should see \"remove widget\" button")
    public void shouldSeeRemoveWidgetButton(WebElement button) {
        assertThat("Should see button", button, isDisplayed());
    }

    @Step("Should see all widgets")
    public void shouldSeeWidget(List<WeatherWidget> elements) {
        assertThat("Should see elements", new ArrayList<>(elements), everyItem(isDisplayed()));
    }

    @Step("Should see {0} widgets")
    public void shouldSeeNumWidgets(int num, List<WeatherWidget> elements) {
        assertThat("Must be " + num + " elements", elements.size(), is(num));
        shouldSeeWidget(elements);
    }

    @Step("Widget city should be {0}")
    public void shouldBeWeatherOfLinkCity(String linkCity, WebElement widgetCity) {
        assertThat("Should be same city, that was in URL", linkCity, is(widgetCity.getText()));
    }

    @Step("Push \"add new weather widget\" button")
    public void pushAddWidgetButton() {
        onMainPage().getNewWeatherWidgetCard().get(0).getAddWidgetButton().click();
    }

    @Step("Push \"remove widget\" button")
    public void pushRemoveWidgetButton() {
        onMainPage().getWeatherWidgetList().get(0).getWidgetActions().getRemoveWidgetButton().click();
    }

    @Step("Remove {0} first widgets")
    public void removeNWidgets(int n){
        for(int i=0;i<n;i++){
            pushRemoveWidgetButton();
        }
    }

    @Step("Push \"add new weather widget\" button {0} times")
    public void pushNewWeatherButtonNTimes(int n) {
        for (int i = 0; i < n; i++) {
            pushAddWidgetButton();
        }
    }

    @Step("Should be no widgets")
    public void shouldBeNoWidgets(List<WeatherWidget> widgets){
        assertEquals(0,widgets.size());
    }


    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
