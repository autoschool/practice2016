package ru.qatools.school.webtests.widgettest;

import org.junit.Test;
import ru.qatools.school.RepresentationPatterns;
import ru.qatools.school.webtests.BaseWeatherAppTest;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.annotations.Title;


/**
 * Created by vananos on 4/22/16.
 */
public class DateTimeTest extends BaseWeatherAppTest {


    @Test
    @Title("Дата должна отображаться в соответствии с определенным представлением")
    @TestCaseId("7")
    public void dateTimeTest() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldBeRepresentedAs(
                onMainPage().getWeatherWidgetList().get(0).getWidgetTitle().getDateTime().getText()
                , RepresentationPatterns.DATE_TIME
        );
    }
}
