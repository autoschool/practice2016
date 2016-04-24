package ru.qatools.school.webtests.pagetest;

import org.junit.Test;
import ru.qatools.school.webtests.BaseWeatherAppTest;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.annotations.Title;


public class WeatherWebTest extends BaseWeatherAppTest {

    @TestCaseId("4")
    @Test
    @Title("Должны видеть виджет на главной странице")
    public void shouldSeeWidgetOnMainPage() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSee(onMainPage().getWeatherWidget().get(0));
    }

}
