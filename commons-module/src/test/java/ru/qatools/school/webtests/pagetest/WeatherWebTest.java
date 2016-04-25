package ru.qatools.school.webtests.pagetest;

import org.junit.Test;
import ru.qatools.school.webtests.BaseWeatherAppTest;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.annotations.Title;


public class WeatherWebTest extends BaseWeatherAppTest {


    @Test
    @Title("Должны видеть виджет на главной странице")
    @TestCaseId("4")
    public void shouldSeeWidgetOnMainPage() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSee(onMainPage().getWeatherWidgetList().get(0));
    }

}
