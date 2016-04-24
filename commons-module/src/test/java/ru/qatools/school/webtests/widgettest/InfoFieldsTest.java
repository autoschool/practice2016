package ru.qatools.school.webtests.widgettest;

import org.junit.Test;
import ru.qatools.school.RepresentationPatterns;
import ru.qatools.school.pages.blocks.widgetblocks.InfoLine;
import ru.qatools.school.webtests.BaseWeatherAppTest;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.List;


public class InfoFieldsTest extends BaseWeatherAppTest {


    @Test
    @Title("Проверяем, что поля соответствуют паттернам")
    @TestCaseId("8")
    public void infoFieldsTest() {
        defaultSteps.openMainPageWithCity(CITY);
        List<InfoLine> il = onMainPage().getWeatherWidget().get(0).getWidgetText().getInfoLines();
        defaultSteps.shouldBeRepresentedAs(il.get(0).getTextRepresentation(), RepresentationPatterns.SUNRISE);
        defaultSteps.shouldSee(il.get(0).getImage());
        defaultSteps.shouldBeRepresentedAs(il.get(1).getTextRepresentation(), RepresentationPatterns.SUNSET);
        defaultSteps.shouldSee(il.get(1).getImage());
        defaultSteps.shouldBeRepresentedAs(il.get(2).getTextRepresentation(), RepresentationPatterns.WIND);
        defaultSteps.shouldSee(il.get(2).getImage());
        defaultSteps.shouldBeRepresentedAs(il.get(3).getTextRepresentation(), RepresentationPatterns.HUMIDITY);
        defaultSteps.shouldSee(il.get(3).getImage());
    }

}