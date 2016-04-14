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

    @Step("Open main page of city «{0}»")
    public void openMainPageWithCity(String city) {
        driver.get(format(MAIN_PAGE, city));
    }

    @Step("Should see this element: «{0}»")
    public void shouldSee(WebElement element) {
        assertThat("Should see element", element, isDisplayed());
    }

    @Step("Should be city, mentioned in URL")
    public void shouldBeWeatherOfRightCity(String linkCity, WebElement widgetCity){ assertThat("Should be same city, that expected", linkCity, is(widgetCity.getText()));}

    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
