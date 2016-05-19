package ru.qatools.school.mobiletests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.rules.MobileDriverRule;
import ru.qatools.school.screens.MainScreen;
import ru.qatools.school.screens.StationsScreen;
import ru.qatools.school.steps.mobilesteps.MobileSteps;
import ru.yandex.qatools.allure.annotations.Title;

/**
 * @author raipc (Anton Rybochkin)
 */
public class MobileTests {

    private static final String ARBATSKAYA_STATION = "Arbatskaya";
    private static final String BORISOVO_STATION = "Borisovo";
    private MobileSteps mobileSteps;

    @Rule
    public MobileDriverRule mobileDriverRule = new MobileDriverRule();

    @Before
    public void initSteps(){
        mobileSteps = new MobileSteps(mobileDriverRule.getDriver());
    }

    @Test
    @Title("Время в пути от Арбатской до Борисово превышает 10 минут")
    public void shouldSeeGreaterTime(){
        StationsScreen stationsScreen = mobileSteps.goToSelectStationScreen(onMainScreen().getFromField());
        mobileSteps.inputInto(stationsScreen.getStationNameInputField(), ARBATSKAYA_STATION);
        mobileSteps.tapOn(stationsScreen.getStations().get(0));
        stationsScreen = mobileSteps.goToSelectStationScreen(onMainScreen().getToField());
        mobileSteps.inputInto(stationsScreen.getStationNameInputField(), BORISOVO_STATION);
        mobileSteps.tapOn(stationsScreen.getStations().get(0));
        mobileSteps.shouldSeeMinutesMoreThan(10);
    }

    private MainScreen onMainScreen(){
        return new MainScreen(mobileDriverRule.getDriver());
    }
}
