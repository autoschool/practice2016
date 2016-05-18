package ru.qatools.school.steps.mobilesteps;

import org.openqa.selenium.remote.RemoteWebDriver;
import ru.qatools.school.screens.MainScreen;
import ru.qatools.school.screens.SelectStationScreen;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

/**
 * @author totallynotkate (Kate Kocijevska)
 */
public class MobileSteps {
    private RemoteWebDriver driver;

    public MobileSteps(RemoteWebDriver driver) {
        this.driver = driver;
    }

    @Step("Тапаем по элементу {0}")
    public void tap(HtmlElement element) {
        element.click();
    }

    @Step("Переходим на страницу выбора станции с элемента {0}")
    public SelectStationScreen goToSelectStationScreen(HtmlElement element) {
        element.click();
        return new SelectStationScreen(driver);
    }

    @Step("Вводим текст {1} в поле {0}")
    public void input(HtmlElement element, String text) {
        element.sendKeys(text);
    }

    @Step()
    public void shouldSeeTimeGreaterThan(int time) {
        assertThat("Неправильное время поездки",
                Integer.parseInt(onMainScreen().topPanel().rideTimeString().getText().trim().split(" ")[0]),
                greaterThan(time));
    }

    private MainScreen onMainScreen() {
        return new MainScreen(driver);
    }
}
