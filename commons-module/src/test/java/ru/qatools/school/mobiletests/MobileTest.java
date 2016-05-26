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
 * @author arrumm 19.05.2016.
 */
public class MobileTest {

    private static final String DEPARTURE_STATION = "Arbatskaya";
    private static final String DESTINATION_STATION = "Borisovo";
    private static final int MIN_TIME = 10;

    private MobileSteps mobileSteps;

    @Rule
    public MobileDriverRule remoteWebDriverRule = new MobileDriverRule();

    @Before
    public void initSteps() {
        mobileSteps = new MobileSteps(remoteWebDriverRule.getDriver());
    }

        @Test
        @Title("Ожидаемое время поездки должно быть больше 10 минут")
        public void shouldSeeGreaterTime() {
            mobileSteps.tapOn(onMainScreen().fromStationField());
            mobileSteps.sendKeysTo(onSelectStationScreen().stationNameField(), DEPARTURE_STATION);
            mobileSteps.tapOn(onSelectStationScreen().getFirstStation());

            mobileSteps.tapOn(onMainScreen().toStationField());
            mobileSteps.sendKeysTo(onSelectStationScreen().stationNameField(), DESTINATION_STATION);
            mobileSteps.tapOn(onSelectStationScreen().getFirstStation());

            mobileSteps.shouldSeeGreaterThan(onMainScreen().timeField(), MIN_TIME);
    }

    private MainScreen onMainScreen() {
        return new MainScreen(remoteWebDriverRule.getDriver());
    }

    private SelectStationScreen onSelectStationScreen() {
        return new SelectStationScreen(remoteWebDriverRule.getDriver());
    }

}
