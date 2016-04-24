package ru.qatools.school.steps.websteps;

import com.jcabi.matchers.RegexMatchers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.RepresentationPatterns;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.pages.blocks.widgetblocks.WidgetTitle;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import static java.lang.String.format;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;


/**
 * Created by kurau.
 */
public class DefaultSteps {

    public static final String MAIN_PAGE = "http://weather.lanwen.ru/";
    public static final String MAIN_PAGE_WITH_CITY = "http://weather.lanwen.ru/#?cities=%s";

    private WebDriver driver;


    public DefaultSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открываем главную страницу для города «{0}»")
    public void openMainPageWithCity(String city) {
        driver.get(format(MAIN_PAGE_WITH_CITY, city));
    }

    @Step("Открываем главную страницу без города")
    public void openMainPage() {
        driver.get(MAIN_PAGE);
    }

    @Step("Должны видеть на странице «{0}»")
    public void shouldSee(WebElement element) {
        assertThat("Должны видеть элемент", element, isDisplayed());
    }

    @Step("Заголовок должен быть «{1}»")
    public void cityIntitleShouldBe(WidgetTitle widgetTitle, String title) {
        assertThat("Город должен быть", widgetTitle.getTitleCityElement(), hasText(title));
    }

    @Step("Количество виджетов должно быть «{0}»")
    public void widgetsAmountShouldBe(int n) {
        assertThat("Количество виджетов должно быть", onMainPage().getWeatherWidget().size(), is(n));
    }

    @Step("Жмем кнопку добавить виджет «{0}» раз")
    public void clickAddWidgetButtonNTimes(int n) {
        while (n-- > 0) {
            onMainPage().getNewWidgetButton().click();
        }
    }

    @Step("Жмем на элемент")
    public void click(HtmlElement el) {
        el.click();
    }


    @Step("Проверяем что представление элемента соответствует паттерну {1}")
    public void shouldBeRepresentedAs(String representation, RepresentationPatterns pattern) {
        assertThat("Представление должно соответствовать регулярному выражение",
                representation, RegexMatchers.containsPattern(pattern.getPattern()));

    }


    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}




