package ru.qatools.school.steps.mobilesteps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

/**
 * @author onegines (Eugene Kirienko)
 */
public class MobileSteps {

    private WebDriver driver;

    public MobileSteps(WebDriver driver) {
        this.driver = driver;
    }


    @Step("Кликаем по элементу {0}")
    public void tapOn(WebElement element) {
        element.click();
    }

    @Step("Вводим текст «{1}» в элемент «{0}»")
    public void enterText(WebElement element, String text) {
        element.sendKeys(text);
    }

    @Step("Время в элементе «{0}» должно быть больше «{1}»")
    public void shouldSeeTimeMoreThan(WebElement element, int minTime) {

        String timeInText = element.getText();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(timeInText);

        int timeInMinutes = 0;
        while (matcher.find()) {
            timeInMinutes = timeInMinutes * 60 + Integer.parseInt(matcher.group());
        }
        assertThat("Неправильное время", timeInMinutes, greaterThan(minTime));
    }
}
