package ru.qatools.school.webtests.widgettest;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.qatools.school.webtests.BaseWeatherAppTest;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.annotations.Title;


@RunWith(DataProviderRunner.class)

public class TitleTest extends BaseWeatherAppTest {

    @DataProvider
    public static Object[][] dataProviderCity() {
        return new Object[][]{
                {"Moscow"},
                {"Kiev"},
                {"Berlin"},
                {"New York"}
        };
    }


    @Test
    @Title("В названии погоды должен быть город «{0}»")
    @UseDataProvider("dataProviderCity")
    @TestCaseId("2")
    public void titleShouldBeCity(String city) {
        defaultSteps.openMainPageWithCity(city);
        defaultSteps.cityIntitleShouldBe(onMainPage().getWeatherWidget().get(0).getWidgetTitle(), city);
    }

}
