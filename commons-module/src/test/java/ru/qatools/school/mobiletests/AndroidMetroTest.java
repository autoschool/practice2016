package ru.qatools.school.mobiletests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.screens.MainScreen;
import ru.qatools.school.screens.SelectStationScreen;
import ru.qatools.school.rules.MobileDriverRule;
import ru.qatools.school.steps.mobilesteps.DefaultSteps;
import ru.yandex.qatools.allure.annotations.Title;

import java.net.MalformedURLException;

public class AndroidMetroTest {

    private static final String STATION_FROM = "Arbatskaya";
    private static final String STATION_TO = "Borisovo";
    private static final int MINIMUM_TIME = 10;

    private DefaultSteps defaultSteps;

    @Rule
    public MobileDriverRule mobileDriverRule = new MobileDriverRule();

    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(mobileDriverRule.getDriver());
    }

    private MainScreen onMainScreen() {
        return new MainScreen(mobileDriverRule.getDriver());
    }

    private SelectStationScreen onSelectStationScreen() {
        return new SelectStationScreen(mobileDriverRule.getDriver());
    }

    @Test
    @Title("Время в пути от Арбатской до Борисово должно быть более 10 минут")
    public void shouldSeeCorrectTimeTravel() {
        defaultSteps.clickOn(onMainScreen().getFromStationField());
        defaultSteps.enterText(onSelectStationScreen().getStationNameField(), STATION_FROM);
        defaultSteps.clickOn(onSelectStationScreen().getStationsNameList().get(0));

        defaultSteps.clickOn(onMainScreen().getToStationField());
        defaultSteps.enterText(onSelectStationScreen().getStationNameField(), STATION_TO);
        defaultSteps.clickOn(onSelectStationScreen().getStationsNameList().get(0));

        defaultSteps.shouldSeeTimeLongerThan(onMainScreen().getTimeField(), MINIMUM_TIME);

    }

}
