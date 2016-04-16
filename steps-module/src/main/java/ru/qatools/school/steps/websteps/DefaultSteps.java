package ru.qatools.school.steps.websteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.MainPage;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.element.Button;

import static java.lang.String.format;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

/**
 * Created by kurau.
 * @author arrumm (Arkhipov Roman)
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

    @Step("Текст «{0}» элемента должен быть «{1}»")
    public void expectedElementTextIsSameToText(WebElement webElement, String text) {
        assertThat("Текст элемента и ожидаемый не совпадают", webElement.getText(), is(text));
    }

    @Step("Должен добавиться виджет «{0}»")
    public void pressNewCardButtonOnPage() {
        onMainPage().getNewCardButton().click();
    }

    @Step("Количество виджетов на странице должно быть «{0}»")
    public void expectedWidgetsQuantityOnPage(int widgetsQuantity) {
        assertThat("Количество виджетов на странице не равно ожидаемому",
                onMainPage().getWeatherWidgetList().size(),is(widgetsQuantity));
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
