package ru.qatools.school.webtests.widgettest;

import org.junit.Test;
import ru.qatools.school.RepresentationPatterns;
import ru.qatools.school.pages.blocks.TemperatureBlock;
import ru.qatools.school.webtests.BaseWeatherAppTest;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.annotations.Title;


/**
 * Created by vananos on 4/22/16.
 */
public class TemperatureBlockTest extends BaseWeatherAppTest {

    @TestCaseId("5")
    @Test
    @Title("Температура должна отображаться в соответствии с определенным представлением")
    public void temperatureBlockTest() {
        defaultSteps.openMainPageWithCity(CITY);
        TemperatureBlock tb = onMainPage().getWeatherWidget().get(0).getTempBlock();
        defaultSteps.shouldBeRepresentedAs(tb.getRepresentation(), RepresentationPatterns.CELSIUS);
        defaultSteps.click(tb);
        defaultSteps.shouldBeRepresentedAs(tb.getRepresentation(), RepresentationPatterns.KELVIN);
        defaultSteps.click(tb);
        defaultSteps.shouldBeRepresentedAs(tb.getRepresentation(), RepresentationPatterns.FAHRENHEIT);
        defaultSteps.click(tb);
        defaultSteps.shouldBeRepresentedAs(tb.getRepresentation(), RepresentationPatterns.KAIF);
        defaultSteps.click(tb);
        defaultSteps.shouldBeRepresentedAs(tb.getRepresentation(), RepresentationPatterns.CELSIUS);
    }
}
