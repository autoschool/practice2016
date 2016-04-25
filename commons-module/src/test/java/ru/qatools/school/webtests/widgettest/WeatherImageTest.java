package ru.qatools.school.webtests.widgettest;

import org.junit.Test;
import ru.qatools.school.webtests.BaseWeatherAppTest;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.annotations.Title;

/**
 * Created by vananos on 4/21/16.
 */
public class WeatherImageTest extends BaseWeatherAppTest {

    @Test
    @Title("Проверяет наличие картинки погоды")
    @TestCaseId("9")
    public void widgetImageTest() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSee(onMainPage().getWeatherWidgetList().get(0).getWidgetText().getWeatherImage());
    }
}
