package ru.qatools.school.steps.mobilesteps;

import org.openqa.selenium.WebDriver;
import ru.qatools.school.screens.MainScreen;
import ru.qatools.school.screens.SelectStationScreen;
import ru.yandex.qatools.allure.annotations.Step;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

/**
 * Created by merkushevio on 19.05.2016.
 */
public class MobileSteps {
    private final static String BEGIN_STATION = "Arbatskaya";
    private final static String END_STATION = "Borisovo";

    private WebDriver driver;

    public MobileSteps(WebDriver driver) {
        this.driver = driver;
    }

    public MainScreen getMainScreen() {
        return new MainScreen(driver);
    }

    public SelectStationScreen getSelectStationScreen() {
        return new SelectStationScreen(driver);
    }


    @Step("Вводим станцию отправления")
    private void enterBeginStation() {
        getMainScreen().getFromStation().click();
        getSelectStationScreen().getNameStation().click();
        getSelectStationScreen().getNameStation().sendKeys(BEGIN_STATION);
        getSelectStationScreen().getFirstFoundStation().click();
    }

    @Step("Вводим станцию прибытия")
    private void enterEndStation() {
        getMainScreen().getToStation().click();
        getSelectStationScreen().getNameStation().click();
        getSelectStationScreen().getNameStation().sendKeys(END_STATION);
        getSelectStationScreen().getFirstFoundStation().click();
    }

    @Step("Проверяем время в пути должно быть больше 10 минут")
    public void shouldSeeTimeMoreThanTenMinutes() {
        enterBeginStation();
        enterEndStation();
        assertThat("Время в пути меньше 10 минут", Integer.parseInt(getMainScreen().getTimeToGo().getText().split(" ")[1]), greaterThan(10));
    }

}
