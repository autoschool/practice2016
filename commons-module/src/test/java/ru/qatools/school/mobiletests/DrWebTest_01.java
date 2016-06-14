package ru.qatools.school.mobiletests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.drwebscreens.*;
import ru.qatools.school.rules.MobileDriverRule;
import ru.qatools.school.steps.mobilesteps.DefaultSteps;
import ru.yandex.qatools.allure.annotations.Title;

public class DrWebTest_01 {

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

    private AntispamScreen onAntispamScreen() {
        return new AntispamScreen(mobileDriverRule.getDriver());
    }

    private UrlFilterScreen onUrlFilterScreen() {
        return new UrlFilterScreen(mobileDriverRule.getDriver());
    }

    @Before
    public void shouldClickClickLicensScreen() {
        defaultSteps.clickOn(onLicensScreen().getStatisticCheckbox());
        defaultSteps.clickOn(onLicensScreen().getLicensAcceptButton());
    }

    /*@Test
    @Title("Принимаем лицензионное соглашение")
    public void shouldClickClickLicensScreen() {
        defaultSteps.clickOn(onLicensScreen().getStatisticCheckbox());
        defaultSteps.clickOn(onLicensScreen().getLicensAcceptButton());
    }*/

    @Test
    @Title("Прокликиваем сканнер")
    public void shouldClickClickScannerScreen() {
        defaultSteps.clickOn(onMainScreen().getScaner());
        defaultSteps.clickOn(onScannerScreen().getBackButton());
    }

    @Test
    @Title("Прокликиваем ")
    public void shouldClickClickAntispamScreen() {
        defaultSteps.clickOn(onMainScreen().getAntispam());
        defaultSteps.clickOn(onAntispamScreen().getFilteringProfileList().get(1));
    }

    @Test
    @Title("Принимаем лицензию и проходимся по экранам приложения")
    public void shouldClickClickUrlFilterScreen() {
        defaultSteps.clickOn(onUrlFilterScreen().getUrlSwitcher());
        defaultSteps.clickOn(onUrlFilterScreen().getUrlFilterEnableBatton());
        defaultSteps.clickOn(onUrlFilterScreen().getUrlCategoriesList().get(1));
        defaultSteps.clickOn(onUrlFilterScreen().getRestrictAccess());
        defaultSteps.clickOn(onUrlFilterScreen().getBackButton());
    }

}
