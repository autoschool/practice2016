package ru.qatools.school.webtests;

/* @author arrumm (Arkhipov Roman) */

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.yandex.qatools.allure.annotations.Title;

public class WeatherWebTest {

    public static final String CITY = "Tomsk";

    private DefaultSteps defaultSteps;

    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();

    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
    }

    @Test
    @Title("Должны видеть виджет для города в URL на главной странице")
    public void shouldSeeWidgetWithCityInURLOnMainPage() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.expectedElementTextIsSameToText(
                onMainPage().getWeatherWidgetList().get(0).getWidgetTitle().getNameOfCity(),
                CITY);
    }

    @Test
    @Title("Должны видеть виджетов на главной странице на 1 больше после добавления")
    public void shouldDetectOnceMoreWidgetOnThePage() {
        defaultSteps.openMainPageWithCity(CITY);
        int widgetsQ = onMainPage().getWeatherWidgetList().size();
        defaultSteps.pressNewCardButtonOnPage();
        defaultSteps.expectedWidgetsQuantityOnPage(widgetsQ+1);
    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

}