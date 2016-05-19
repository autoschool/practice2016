package ru.qatools.school.steps.mobilesteps;

import org.openqa.selenium.remote.RemoteWebDriver;
import ru.qatools.school.screens.MainScreen;
import ru.qatools.school.screens.StationsScreen;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

/**
 * @author raipc (Anton Rybochkin)
 */

public class MobileSteps {
    private RemoteWebDriver driver;

    public MobileSteps(RemoteWebDriver driver) {
        this.driver = driver;
    }

    private MainScreen onMainScreen() {
        return new MainScreen(driver);
    }

    @Step("Тапаем по элементу {0}")
    public void tapOn(HtmlElement element) {
        element.click();
    }

    @Step("Вводим в поле {0} текст \"{1}\"")
    public void inputInto(HtmlElement element, String text) {
        element.sendKeys(text);
    }

    @Step("Переходим на экран выбора станции с элемента {0}")
    public StationsScreen goToSelectStationScreen(HtmlElement element) {
        element.click();
        return new StationsScreen(driver);
    }

    @Step()
    public void shouldSeeMinutesMoreThan(int minutes) {
        String minutesString = onMainScreen().getTopPanel().getTravelTime().getText().trim().split(" ")[0];
        assertThat("Время поездки неверно",
                Integer.parseInt(minutesString),
                greaterThan(minutes));
    }
}
