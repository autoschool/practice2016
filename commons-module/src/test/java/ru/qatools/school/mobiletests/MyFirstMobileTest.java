package ru.qatools.school.mobiletests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.rules.MobileDriverRule;
import ru.qatools.school.screens.MainScreen;
import ru.qatools.school.screens.SelectStationScreen;
import ru.qatools.school.steps.mobilesteps.MobileSteps;
import ru.yandex.qatools.allure.annotations.Title;

import java.net.MalformedURLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by aasx on 18.05.2016.
 */
public class MyFirstMobileTest {

    private static final String STATION_FROM="Aeroport";
    private static final String STATION_TO="Akademicheskaya";

    private MobileSteps steps;


    @Rule
    public MobileDriverRule mobileDriverRule = new MobileDriverRule();

    @Before
    public void initSteps() throws MalformedURLException { steps = new MobileSteps(mobileDriverRule.getDriver())  ;}

    @Test
    @Title("Время в пути между станциями {0} и {1} должно быть не менее 10 минут")
    public void expectTravelTimeBetweenTwoDifferentStationMoreThatTenMinutes () throws MalformedURLException {
        steps.clickOn(onMainScreen().getStationFrom());
        steps.clickOn(onSelectStationScreen().getEditText());
        steps.enterText(onSelectStationScreen().getEditText(),STATION_FROM);
        steps.clickOn(onSelectStationScreen().getFirstCityFromList());
        steps.clickOn(onMainScreen().getStationTo());
        steps.clickOn(onSelectStationScreen().getEditText());
        steps.enterText(onSelectStationScreen().getEditText(), STATION_TO);
        steps.clickOn(onSelectStationScreen().getFirstCityFromList());
        assertThat("время в пути должно быть больше 10 минут", onMainScreen().getTimeNeededInMinutes()<10 );
    }


    private MainScreen onMainScreen() throws MalformedURLException {
        return new MainScreen(mobileDriverRule.getDriver());}
    private SelectStationScreen onSelectStationScreen() throws MalformedURLException {
        return new SelectStationScreen(mobileDriverRule.getDriver());}
}
