package ru.qatools.school.steps.mobilesteps;

import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertThat;

import static org.hamcrest.Matchers.greaterThan;


public class DefaultSteps {

    private WebDriver driver;

    public DefaultSteps(WebDriver driver) {
        this.driver = driver;
    }


    @Step("Тап по элементу {0}")
    public void clickOn(HtmlElement element) {
        element.click();
    }

    @Step("Вводим в поле «{0}» текст «{1}»")
    public void enterText(HtmlElement element, String text) {
        element.sendKeys(text);
    }

    @Step("Время в «{0}» должно быть больше, чем «{1}»")
    public void shouldSeeTimeLongerThan(HtmlElement element, int minTime) {

        String timeText = element.getText();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(timeText);
        matcher.find();
        int timeMinut = Integer.parseInt(matcher.group());
        assertThat("Неправильное расчетное время поездки!", timeMinut, greaterThan(minTime));

    }

}