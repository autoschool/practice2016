package ru.qatools.school.mobiletests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.rules.MobileDriverRule;
import ru.qatools.school.screens.MainScreen;
import ru.qatools.school.screens.SelectStationScreen;
import ru.qatools.school.steps.mobilesteps.DefaultSteps;
import ru.yandex.qatools.allure.annotations.Title;

/**
 * @author by onegines (Eugene Kirienko)
 */
public class MobileTests {

    private static final String STATION_1 = "Arbatskaya";
    private static final String STATION_2 = "Borisovo";
    private static final int MIN_TIME = 10;

    private DefaultSteps defaultSteps;

    @Rule
    public MobileDriverRule mobileDriverRule = new MobileDriverRule();

//    @Rule
//    public TPInformerRule tms = new TPInformerRule("onegines");

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
    @Title("Время в пути от Арбатской до Борисово должно быть больше 10 минут")
//    @TestCaseId("##")
    public void shouldSeeAddWidgetButtonOnPageWithNoQuery() {
        defaultSteps.clickOn(onMainScreen().fromField());
        defaultSteps.enterText(onSelectStationScreen().stationNameField(), STATION_1);
        defaultSteps.clickOn(onSelectStationScreen().firstResult());

        defaultSteps.clickOn(onMainScreen().toField());
        defaultSteps.enterText(onSelectStationScreen().stationNameField(), STATION_2);
        defaultSteps.clickOn(onSelectStationScreen().firstResult());

        defaultSteps.shouldSeeTimeMoreThan(onMainScreen().timeField(), MIN_TIME);
    }

}
