package ru.qatools.school.mobiletests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.screens.MainScreen;
import ru.qatools.school.screens.SelectStationScreen;
import ru.qatools.school.rules.MobileDriverRule;
import ru.qatools.school.steps.mobilesteps.DefaultSteps;
import ru.yandex.qatools.allure.annotations.Title;

import java.net.MalformedURLException;

public class AndroidMetroTest {

    public static final String MOSCOW = "Moscow";

    private DefaultSteps defaultSteps;

    @Rule
    public MobileDriverRule mobileDriverRule = new MobileDriverRule();

    @Before
    public void initSteps() throws MalformedURLException {
        defaultSteps = new DefaultSteps(mobileDriverRule.getDriver());
    }

    @Test
    @Title("Должно открыться активити From")
    public void shouldOpenActivityFrom() {
        defaultSteps.tapOnFromInMainScreen();
        //defaultSteps.shouldSee(onMainPage().getWeatherWidget().get(0));
    }

    /*private MainScreen onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }*/

}
