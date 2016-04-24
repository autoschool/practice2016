package ru.qatools.school.webtests.pagetest;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.qatools.school.webtests.BaseWeatherAppTest;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.annotations.Title;


@RunWith(DataProviderRunner.class)
public class AddWidgetButtonTest extends BaseWeatherAppTest {

    @DataProvider
    public static Object[][] dataProviderNumber() {
        return new Object[][]{
                {1}, {2}
        };
    }


    @Test
    @Title("Жмем на кнопку n раз, должно быть n+1 виджетов")
    @UseDataProvider("dataProviderNumber")
    @TestCaseId("1")
    public void shouldSeeNPlusOneWidgetsAfterNClickByAddButton(int n) {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.clickAddWidgetButtonNTimes(n);
        defaultSteps.widgetsAmountShouldBe(n + 1);
    }

}
