package ru.qatools.school.steps.websteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.MainPage;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

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

    @Step("Открываем главную страницу без заданного города")
    public void openMainPage() {
        driver.get(MAIN_PAGE);
    }

    @Step("Открываем главную страницу для города «{0}»")
    public void openMainPageWithCity(String city) {
        driver.get(format(MAIN_PAGE, city));
    }

    @Step("Должны видеть на странице «{0}»")
    public void shouldSee(WebElement element) {
        assertThat("Должны видеть элемент", element, isDisplayed());
    }

    @Step("В виджете «{0}» должны видеть город «{1}»")
    public void shouldSeeCityInWidget(WebElement element, String city) {
        assertThat("В виджете отбражается не тот город!", element.getText(), is(city));
    }

    @Step("Кликаем по «{0}»")
    public void clickElement(WebElement element) {
        element.click();
    }

    @Step("Должны увидеть «{1}» элементов(ов) в списке «{0}»")
    public void shouldBeThisNumberOfElements(List elements, int count) {
        assertThat("Количество элементов не соответствует ожидаемому!", elements.size(), is(count));
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
