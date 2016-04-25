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
public class RemoveButtonTest extends BaseWeatherAppTest {

    @DataProvider
    public static Object[][] dataProviderNumber() {
        return new Object[][]{
                {0}, {3}
        };
    }


    @Test
    @Title("Жмем на кнопку добавления n раз, Затем удаляем средний виджет")
    @UseDataProvider("dataProviderNumber")
    @TestCaseId("3")
    public void shouldSeeNWidgetsAfterOneClickByRemoveButton(int n) {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.clickNTimes(onMainPage().getNewWidgetButton(), n);
        defaultSteps.click(onMainPage().getWeatherWidgetList().get(n / 2).getActionBlock().getRemoveButton());
        defaultSteps.widgetsAmountShouldBe(n);
    }

}