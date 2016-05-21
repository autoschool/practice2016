package ru.qatools.school.steps.mobilesteps;

import org.openqa.selenium.WebDriver;
import ru.qatools.school.screens.MainScreen;
import ru.qatools.school.screens.SelectStationScreen;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

/**
 * @author gladnik (Nikolai Gladkov)
 */
public class MetroAppSteps {

    private WebDriver driver;

    public MetroAppSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Тапаем по [{0}]")
    public static void tapOn(HtmlElement element) {
        element.click();
    }

    @Step("Вводим «{1}» в «{0}»")
    public static void sendText(HtmlElement element, String text) {
        element.sendKeys(text);
    }

    @Step("Меняем станцию отправления на «{0}»")
    public void changeDepartureStationBySelectScreen(String newStationName) {
        tapOn(onMainScreen().tvFromName());
        sendText(onSelectStationScreen().filterTextId(), newStationName);
        tapOn(onSelectStationScreen().firstStation());
    }

    @Step("Меняем станцию назанчения на «{0}»")
    public void changeDestinationStationBySelectScreen(String newStationName) {
        tapOn(onMainScreen().tvToName());
        sendText(onSelectStationScreen().filterTextId(), newStationName);
        tapOn(onSelectStationScreen().firstStation());
    }

    @Step("Прокладываем маршрут от «{0}» к «{1}»")
    public void setRoute(String fromStation, String toStation) {
        changeDepartureStationBySelectScreen(fromStation);
        changeDestinationStationBySelectScreen(toStation);
    }

    @Step("Время в пути должно быть больше {0} минут")
    public void shouldSeeTravelTimeGreaterThan(int timeInMinutes) {
        int travelDurationInMinutes;
        String[] timeDescription = onMainScreen().tvTime().getText().trim().split(" ");
        if (timeDescription.length == 2) {
            travelDurationInMinutes = Integer.parseInt(timeDescription[0]);
        } else {
            travelDurationInMinutes = Integer.parseInt(timeDescription[0]) * 60 + Integer.parseInt(timeDescription[2]);
        }

        assertThat("Время в пути должно быть больше", travelDurationInMinutes, greaterThan(timeInMinutes));
    }

    private MainScreen onMainScreen() {
        return new MainScreen(driver);
    }

    private SelectStationScreen onSelectStationScreen() {
        return new SelectStationScreen(driver);
    }

}
