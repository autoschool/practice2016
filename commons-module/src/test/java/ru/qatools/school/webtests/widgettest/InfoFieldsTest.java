package ru.qatools.school.webtests.widgettest;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.qatools.school.RepresentationPatterns;
import ru.qatools.school.pages.blocks.widgetblocks.InfoLine;
import ru.qatools.school.webtests.BaseWeatherAppTest;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

@RunWith(DataProviderRunner.class)
public class InfoFieldsTest extends BaseWeatherAppTest {

    @DataProvider
    public static Object[][] dataProvider() {

        return new Object[][]{
                {0, RepresentationPatterns.SUNRISE},
                {1, RepresentationPatterns.SUNSET},
                {2, RepresentationPatterns.WIND},
                {3, RepresentationPatterns.HUMIDITY}
        };
    }


    @Test
    @Title("Проверяем, что поля соответствуют паттернам")
    @UseDataProvider("dataProvider")
    @TestCaseId("8")
    public void infoFieldsTest(int n, RepresentationPatterns pattern) {
        defaultSteps.openMainPageWithCity(CITY);
        InfoLine infoLine = onMainPage().getWeatherWidgetList().get(0).getWidgetText().getInfoLines().get(n);
        defaultSteps.shouldBeRepresentedAs(infoLine.getTextRepresentation(), pattern);
        defaultSteps.shouldSee(infoLine.getImage());

    }

}