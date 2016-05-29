package ru.qatools.school.mobiletests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.rules.MobileDriverRule;
import ru.qatools.school.drwebscreens.MainScreen;
import ru.qatools.school.drwebscreens.LicensScreen;
import ru.qatools.school.drwebscreens.ScannerScreen;
import ru.qatools.school.steps.mobilesteps.DefaultSteps;
import ru.yandex.qatools.allure.annotations.Title;

public class DrWebTest {

    /*private static final String STATION_FROM = "Arbatskaya";
    private static final String STATION_TO = "Borisovo";
    private static final int MINIMUM_TIME = 10;*/

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

    private LicensScreen onLicensScreen() {
        return new LicensScreen(mobileDriverRule.getDriver());
    }

    private ScannerScreen onScannerScreen() {
        return new ScannerScreen(mobileDriverRule.getDriver());
    }

    @Test
    @Title("Принимаем лицензию и заходим в Сканер")
    public void shouldClickClick() {
        defaultSteps.clickOn(onLicensScreen().getStatisticCheckbox());
        defaultSteps.clickOn(onLicensScreen().getLicensAcceptButton());
        defaultSteps.clickOn(onMainScreen().getScaner());
        defaultSteps.clickOn(onScannerScreen().getBackButton());
        //defaultSteps.clickOn(onScannerScreen().getScannerList().get(0));
        /*defaultSteps.clickOn(onMainScreen().getFromStationField());
        defaultSteps.enterText(onSelectStationScreen().getStationNameField(), STATION_FROM);
        defaultSteps.clickOn(onSelectStationScreen().getStationsNameList().get(0));

        defaultSteps.clickOn(onMainScreen().getToStationField());
        defaultSteps.enterText(onSelectStationScreen().getStationNameField(), STATION_TO);
        defaultSteps.clickOn(onSelectStationScreen().getStationsNameList().get(0));

        defaultSteps.shouldSeeTimeLongerThan(onMainScreen().getTimeField(), MINIMUM_TIME);*/

    }

}
