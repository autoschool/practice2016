package ru.qatools.school.steps.websteps;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.pages.blocks.WeatherWidget;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.String.format;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
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

    @Step("Проверяем что заголовок виджета это запрошенный город")
    public void shouldSeeTitleWidgetEqualCity(String city) {
        assertThat("Заголовок города равен названию виджета",
                driver.findElement(By.cssSelector("div .inplace.inplace_displayed")).getText(), equalTo(city));
    }

    @Step("Добавляем виджет на страницу")
    public void addWidgetOnMainPage(String city) {
        driver.findElement(By.cssSelector(".new-card")).click();
        driver.findElement(By.cssSelector("span.inplace.inplace_displayed")).click();
        driver.findElement(By.cssSelector("input.inplace.inplace_editable")).clear();
        driver.findElement(By.cssSelector("input.inplace.inplace_editable")).sendKeys(city);
        driver.findElement(By.cssSelector("input.inplace.inplace_editable")).sendKeys(Keys.RETURN);
    }

    @Step("На главной странице виджеты добавляются")
    public void shouldSeeWidgetAdd(String city) {
        addWidgetOnMainPage(city);
        boolean find = false;
        List<WebElement> spans = driver.findElements(By.cssSelector(".inplace.inplace_displayed"));
        for (WebElement span : spans)
        {
            String text = span.getText();
            if (text.equals(city)) {
                find = true;
                break;
            }
        }
        assertTrue("Виджет с именем " + city + "не добавился", find);
    }

    @Step("На главной странице виджет можно удалить")
    public void shouldSeeWidgetRemove(String removeCity) {
        int count = onMainPage().getWeatherWidget().size();
        List<WeatherWidget> widgets = onMainPage().getWeatherWidget();
        for (WeatherWidget widget : widgets )
        {
            String text = widget.findElement(By.cssSelector(".inplace.inplace_displayed")).getText();
            if (text.equals(removeCity)) {
                widget.findElement(By.cssSelector(".remove-card.btn.btn-default")).click();
                break;
            }
        }
        assertThat("Количество виджетов не уменьшилось на единицу после удаления одного виджета", --count, is(onMainPage().getWeatherWidget().size()));
    }

    private MainPage onMainPage() {
        return new MainPage(driver);
    }
}
