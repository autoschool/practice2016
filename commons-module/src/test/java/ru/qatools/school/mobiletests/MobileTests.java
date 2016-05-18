package ru.qatools.school.mobiletests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.rules.MobileDriverRule;
import ru.qatools.school.screens.MainScreen;
import ru.qatools.school.screens.SelectStationScreen;
import ru.qatools.school.steps.mobilesteps.MobileSteps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

/**
 * @author totallynotkate (Kate Kocijevska)
 */
public class MobileTests {

    public static final String FIRST_STATION = "Arbatskaya";
    public static final String SECOND_STATION = "Borisovo";
    private MobileSteps mobileSteps;

    @Rule
    public MobileDriverRule mobileDriverRule = new MobileDriverRule();

    @Before
    public void initSteps(){
        mobileSteps = new MobileSteps(mobileDriverRule.getDriver());
    }

    @Test
    public void shouldSeeTime(){
        SelectStationScreen selectStationScreen = mobileSteps.goToSelectStationScreen(onMainScreen().fromField());
        mobileSteps.input(selectStationScreen.stationNameInput(), FIRST_STATION);
        mobileSteps.tap(selectStationScreen.stationsList().get(0));
        selectStationScreen = mobileSteps.goToSelectStationScreen(onMainScreen().toField());
        mobileSteps.input(selectStationScreen.stationNameInput(), SECOND_STATION);
        mobileSteps.tap(selectStationScreen.stationsList().get(0));
        assertThat(Integer.parseInt(onMainScreen().topPanel().rideTimeString().getText().trim().split(" ")[0]),
                greaterThan(10));
    }

    private MainScreen onMainScreen(){
        return new MainScreen(mobileDriverRule.getDriver());
    }
}
