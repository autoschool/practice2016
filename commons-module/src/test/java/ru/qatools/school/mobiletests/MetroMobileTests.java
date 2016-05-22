package ru.qatools.school.mobiletests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.rules.MobileDriverRule;
import ru.qatools.school.screens.MainScreen;
import ru.qatools.school.screens.SelectStationScreen;
import ru.qatools.school.steps.mobilesteps.MobileSteps;
import ru.yandex.qatools.allure.annotations.Title;

/**
 * @author ava1on
 */
public class MetroMobileTests {

    private MobileSteps mobileSteps;
    private final String STATION1 = "Arbatskaya";
    private final String STATION2 = "Borisovo";
    private final int MINIMUM_TRAVEL_TIME = 10;

    @Rule
    public MobileDriverRule mobileDriverRule = new MobileDriverRule();

    @Before
    public void initsteps(){
        mobileSteps = new MobileSteps(mobileDriverRule.getDriver());
    }

    @Test
    @Title("Время в пути должно быть больше мимимального")
    public void travelTimeShouldBeGreaterThanMinimum(){
        mobileSteps.fillStationField(onMainScreen().getFrom(), STATION1);
        mobileSteps.fillStationField(onMainScreen().getTo(), STATION2);
        mobileSteps.travelTimeShouldBeGreaterThen(MINIMUM_TRAVEL_TIME);
    }

    private MainScreen onMainScreen(){
        return new MainScreen(mobileDriverRule.getDriver());
    }

    private SelectStationScreen onSelectionStationScreen(){
        return new SelectStationScreen(mobileDriverRule.getDriver());
    }
}
