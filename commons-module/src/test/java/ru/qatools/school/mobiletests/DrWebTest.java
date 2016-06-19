package ru.qatools.school.mobiletests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.drwebscreens.*;
import ru.qatools.school.rules.MobileDriverRule;
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

    private AntispamScreen onAntispamScreen() {
        return new AntispamScreen(mobileDriverRule.getDriver());
    }

    private UrlFilterScreen onUrlFilterScreen() {
        return new UrlFilterScreen(mobileDriverRule.getDriver());
    }

    private AntitheftScreen onAntitheftScreen() {
        return new AntitheftScreen(mobileDriverRule.getDriver());
    }

    private FirewallScreen onFirewallScreen() {
        return new FirewallScreen(mobileDriverRule.getDriver());
    }

    private SecurityAuditorScreen onSecurityAuditorScreen() {
        return new SecurityAuditorScreen(mobileDriverRule.getDriver());
    }

    @Test
    @Title("Принимаем лицензию и проходимся по экранам приложения")
    public void shouldClickClick() {
        defaultSteps.clickOn(onLicensScreen().getStatisticCheckbox());
        defaultSteps.clickOn(onLicensScreen().getLicensAcceptButton());

        /*defaultSteps.clickOn(onMainScreen().getScaner());
        defaultSteps.clickOn(onScannerScreen().getBackButton());

        defaultSteps.clickOn(onMainScreen().getAntispam());
        defaultSteps.clickOn(onAntispamScreen().getFilteringProfileList().get(1));

        defaultSteps.clickOn(onMainScreen().getUrlFilter());
        defaultSteps.clickOn(onUrlFilterScreen().getUrlSwitcher());
        defaultSteps.clickOn(onUrlFilterScreen().getUrlFilterEnableBatton());
        defaultSteps.clickOn(onUrlFilterScreen().getUrlCategoriesList().get(4));
        defaultSteps.clickOn(onUrlFilterScreen().getRestrictAccess());
        defaultSteps.clickOn(onUrlFilterScreen().getBackButton());*/

        //defaultSteps.clickOn(onMainScreen().getAntitheft());
        /*defaultSteps.clickOn(onAntitheftScreen().getAntitheftSwitcher());
        defaultSteps.enterText(onAntitheftScreen().getPasswordEditField(), "pppp");
        defaultSteps.clickOn(onAntitheftScreen().getAntitheftWizardBattonContinue());
        defaultSteps.enterText(onAntitheftScreen().getPasswordEditField(), "pppp");
        defaultSteps.clickOn(onAntitheftScreen().getAntitheftWizardBattonContinue());
        defaultSteps.clickOn(onAntitheftScreen().getAntitheftWizardBattonBack());
        defaultSteps.clickOn(onAntitheftScreen().getCrossButton());*/

        /*defaultSteps.clickOn(onAntitheftScreen().getAntitheftEnableBatton());
        defaultSteps.enterText(onAntitheftScreen().getPasswordEditField(), "pppp");
        defaultSteps.clickOn(onAntitheftScreen().getAntitheftWizardBattonContinue());
        defaultSteps.enterText(onAntitheftScreen().getPasswordEditField(), "pppp");
        defaultSteps.clickOn(onAntitheftScreen().getShowPasswordButton());
        defaultSteps.clickOn(onAntitheftScreen().getAntitheftWizardBattonContinue());
        defaultSteps.clickOn(onAntitheftScreen().getAddContactButton());
        defaultSteps.clickOn(onAntitheftScreen().getNewContactCategory());
        defaultSteps.enterText(onAntitheftScreen().getPhoneNumberEditorField(), "12345");
        defaultSteps.clickOn(onAntitheftScreen().getSaveBatton());
        defaultSteps.clickOn(onAntitheftScreen().getAntitheftWizardBattonContinue());
        defaultSteps.enterText(onAntitheftScreen().getEmailEditField(), "123@ya.ru");
        defaultSteps.clickOn(onAntitheftScreen().getAntitheftWizardBattonContinue());
        defaultSteps.enterText(onAntitheftScreen().getBlockTextField(), " Ururu!!! 777");
        defaultSteps.clickOn(onAntitheftScreen().getAntitheftWizardBattonContinue());
        defaultSteps.clickOn(onAntitheftScreen().getCancelBatton());

        defaultSteps.clickOn(onMainScreen().getAntitheft());
        defaultSteps.enterText(onAntitheftScreen().getPasswordEditField(), "pppp");
        defaultSteps.clickOn(onAntitheftScreen().getAntitheftWizardBattonContinue());*/

        defaultSteps.clickOn(onMainScreen().getFirewall());
        /*defaultSteps.clickOn(onFirewallScreen().getFirewallEnableBatton());
        defaultSteps.clickOn(onFirewallScreen().getVPNTrustCheckbox());
        defaultSteps.clickOn(onFirewallScreen().getVPNOkButton());
        defaultSteps.clickOn(onFirewallScreen().getFirewallSwitcher());*/
        defaultSteps.clickOn(onFirewallScreen().getFirewallTabList().get(1));
        defaultSteps.clickOn(onFirewallScreen().getFirewallTabList().get(2));
        defaultSteps.clickOn(onFirewallScreen().getFirewallTabList().get(0));
        defaultSteps.clickOn(onFirewallScreen().getBackButton());

        defaultSteps.clickOn(onMainScreen().getAuditor());
        defaultSteps.clickOn(onSecurityAuditorScreen().getBackButton());

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
