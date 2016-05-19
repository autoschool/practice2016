package ru.qatools.school.mobiletests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.rules.MobileDriverRule;
import ru.qatools.school.steps.mobilesteps.MobileSteps;
import ru.yandex.qatools.allure.annotations.Title;

/**
 * Created by merkushevio on 19.05.2016.
 */

public class MobileTest {

    private MobileSteps mobileSteps;

    @Rule
    public MobileDriverRule mobileDriverRule = new MobileDriverRule();

    @Before
    public void initSteps() {
        mobileSteps = new MobileSteps(mobileDriverRule.getDriver());
    }

    @Test
    //@TestCaseId("50")
    @Title("Должны видеть виджет на главной странице")
    public void shouldSeeTimeToGoMoreThanTenMinutes() {
        mobileSteps.shouldSeeTimeMoreThanTenMinutes();
    }
}
