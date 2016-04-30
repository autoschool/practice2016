package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.pages.blocks.WeatherWidget;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.yandex.qatools.allure.annotations.Title;

public class MyFirstWebTest {

    private static final String CITY = "Lipetsk";
    private static final String NEW_WIDGET_TITLE = "What a city?";

    private DefaultSteps defaultSteps;

    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();

    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
    }


    @Test
    @Title("Открываем страницу для города «Lipetsk»")
    public void shouldSeeWidgetWithCityFromGetParameters() {
        defaultSteps.openMainPageWithCity(CITY);
        WeatherWidget firstWidget = onMainPage().getFirstWeatherWidget();
        defaultSteps.shouldSee(firstWidget);
        defaultSteps.shouldSeeWidgetWithTitle(firstWidget, CITY);
    }

    @Test
    @Title("Должны добавить и увидеть новый виджет")
    public void shouldSeeAddedWidget() {
        defaultSteps.openMainPageWithCity(CITY);
        int widgetsCountBefore = onMainPage().getWeatherWidgets().size();
        defaultSteps.clickOn(onMainPage().getNewWidgetButton());
        defaultSteps.shouldHaveWidgetsCount(widgetsCountBefore + 1);
        defaultSteps.shouldSeeWidgetWithTitle(onMainPage().getFirstWeatherWidget(), NEW_WIDGET_TITLE);
    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }
}
