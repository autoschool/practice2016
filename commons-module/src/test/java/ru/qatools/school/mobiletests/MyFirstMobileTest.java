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
import static org.hamcrest.Matchers.greaterThan;

/**
 * Created by aasx on 18.05.2016.
 */
public class MyFirstMobileTest {

    private static final String STATION_FROM = "Арбатская";
    private static final String STATION_TO = "Борисово";
    private static final int MINIMUM_TRAVEL_TIME = 10;

    @Rule
    public MobileDriverRule mobileDriverRule = new MobileDriverRule();
    private MobileSteps steps;

    @Before
    public void initSteps() throws MalformedURLException {
        steps = new MobileSteps(mobileDriverRule.getDriver());
    }

    @Test
    @Title("Время в пути между станциями должно быть не менее 10 минут")
    public void expectTravelTimeBetweenTwoDifferentStationMoreThatTenMinutes() throws MalformedURLException {
        steps.clickOn(onMainScreen().getStationFrom());
        steps.enterText(onSelectStationScreen().getStationName(), STATION_FROM);
        steps.clickOn(onSelectStationScreen().getFirstCityFromSuggestList());
        steps.clickOn(onMainScreen().getStationTo());
        steps.enterText(onSelectStationScreen().getStationName(), STATION_TO);
        steps.clickOn(onSelectStationScreen().getFirstCityFromSuggestList());
        assertThat("время в пути должно быть больше минимального времени между двумя станциями",
                onMainScreen().getTimeNeededInMinutes(), greaterThan(MINIMUM_TRAVEL_TIME));

    }


    private MainScreen onMainScreen() throws MalformedURLException {
        return new MainScreen(mobileDriverRule.getDriver());
    }

    private SelectStationScreen onSelectStationScreen() throws MalformedURLException {
        return new SelectStationScreen(mobileDriverRule.getDriver());
    }
}
