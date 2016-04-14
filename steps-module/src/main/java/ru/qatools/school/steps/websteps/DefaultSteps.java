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

    @Step("Должны видеть в заголовке город, указанный в ссылке")
    public void shouldSeeRightCityInWidgetsTitle(String cityName){
        assertThat("Город в заголовке совпадает с указанным в ссылке",
                onMainPage().getWeatherWidget().get(0).getWidgetTitle().getPrimaryTitle().getCityName() , is(cityName));
    }

    @Step("Должны видеть на один виджет больше после добавления")
    public void shouldSeeNewAddedWidget(MainPage page){
        int numberOfWidgets = page.getWeatherWidget().size();
        page.getAddNewWidgetButton().click();
        assertThat("Должны видеть на один виджет больше", page.getWeatherWidget().size(), is(numberOfWidgets+1));
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
