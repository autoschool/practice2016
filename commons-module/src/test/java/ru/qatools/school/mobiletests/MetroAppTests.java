package ru.qatools.school.mobiletests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.rules.MobileDriverRule;
import ru.qatools.school.steps.mobilesteps.MetroAppSteps;
import ru.yandex.qatools.allure.annotations.Title;

/**
 * @author gladnik (Nikolai Gladkov)
 */
public class MetroAppTests {

    private static final String FIRST_STATION = "Arbatskaya";
    private static final String SECOND_STATION = "Borisovo";
    private static final int MIN_TIME = 10;

    private MetroAppSteps metroSteps;

    @Rule
    public MobileDriverRule androidDriverRule = new MobileDriverRule();

    @Before
    public void initSteps() {
        metroSteps = new MetroAppSteps(androidDriverRule.getDriver());
    }

    @Test
    @Title("Время в пути между станциями должно быть больше минимального")
    public void shouldGetRouteExceedingMinTime() {
        metroSteps.setRoute(FIRST_STATION, SECOND_STATION);
        metroSteps.shouldSeeTravelTimeGreaterThan(MIN_TIME);
    }

}
