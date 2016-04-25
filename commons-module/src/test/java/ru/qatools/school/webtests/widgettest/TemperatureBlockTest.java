package ru.qatools.school.webtests.widgettest;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.qatools.school.RepresentationPatterns;
import ru.qatools.school.pages.blocks.widgetblocks.TemperatureBlock;
import ru.qatools.school.webtests.BaseWeatherAppTest;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.annotations.Title;


/**
 * Created by vananos on 4/22/16.
 */
@RunWith(DataProviderRunner.class)
public class TemperatureBlockTest extends BaseWeatherAppTest {

    @DataProvider
    public static Object[][] dataProvider() {
        return new Object[][]{
                {0,RepresentationPatterns.CELSIUS},
                {1,RepresentationPatterns.KELVIN},
                {2, RepresentationPatterns.FAHRENHEIT},
                {3, RepresentationPatterns.KAIF},
                {4, RepresentationPatterns.CELSIUS}
        };
    }


    @Test
    @Title("Температура должна отображаться в соответствии с определенным представлением")
    @UseDataProvider("dataProvider")
    @TestCaseId("5")
    public void temperatureBlockTest(int n,RepresentationPatterns pattern) {
        defaultSteps.openMainPageWithCity(CITY);
        TemperatureBlock temperatureBlock = onMainPage().getWeatherWidgetList()
                .get(0).getWidgetText().getTempBlock();
        defaultSteps.clickNTimes(temperatureBlock,n);
        defaultSteps.shouldBeRepresentedAs(temperatureBlock.getRepresentation(), pattern);


    }
}
