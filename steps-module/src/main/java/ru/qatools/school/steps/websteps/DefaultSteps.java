package ru.qatools.school.steps.websteps;

import org.hamcrest.CoreMatchers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.pages.blocks.WeatherWidget;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.everyItem;
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

    @Step("Open main page of city given")
    public void openMainPageWithCity(String city) {
        driver.get(format(MAIN_PAGE, city));
    }

    @Step("Element given should be seen")
    public void shouldBeSeen(WeatherWidget element) {
        assertThat("Should see element", element, isDisplayed());
    }

    @Step("All elements should be seen")
    public void shouldBeSeen(List<WeatherWidget> elements) {
        elements.forEach(this::shouldBeSeen);

//        assertThat("Should see elements", elements, everyItem(is(isDisplayed());   why not work?
    }

    @Step("Exact number of elements should exist")
    public void shouldExistAsMuchWidgetsAs(int num, List<WeatherWidget> elements) {
        assertThat("Must be " + num + " elements", elements.size(), is(num));
    }

    @Step("Should be city, mentioned in URL")
    public void shouldBeWeatherOfLinkCity(String linkCity, WebElement widgetCity) {
        assertThat("Should be same city, that was in URL", linkCity, is(widgetCity.getText()));
    }

    @Step("Push \"add new weather widget\" button")
    public void pushNewWeatherButton() {
        onMainPage().getNewWeatherWidget().get(0).getNewWeatherWidgetButton().click();
    }

    @Step("Push \"add new weather widget\" button N times")
    public void pushNewWeatherButtonNTimes(int n) {
        for (int i = 0; i < n; i++) {
            pushNewWeatherButton();
        }
    }


    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
