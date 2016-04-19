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

    private static final String LIPETSK_CITY = "Lipetsk";
    private static final String NEW_WIDGET_TITLE = "What a city?";

    private DefaultSteps defaultSteps;

    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();

    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
    }


    @Test
    @Title("Передаём в GET «Lipetsk», должны увидеть виджет с городом «Lipetsk»")
    public void shouldSeeWidgetWithCityFromGetParameters() {
        defaultSteps.openMainPageWithCity(LIPETSK_CITY);
        WeatherWidget firstWidget = getFirstWeatherWidget();
        defaultSteps.shouldSee(firstWidget);
        defaultSteps.shouldSeeWidgetWithTitle(firstWidget, LIPETSK_CITY);
    }

    @Test
    @Title("Должны добавить и увидеть новый виджет")
    public void shouldSeeAddedWidget() {
        defaultSteps.openMainPageWithCity(LIPETSK_CITY);
        int widgetsCountBefore = onMainPage().getWeatherWidgets().size();
        defaultSteps.pressNewWidgetButton();
        defaultSteps.shouldHaveWidgetsCount(widgetsCountBefore + 1);
        defaultSteps.shouldSeeWidgetWithTitle(getFirstWeatherWidget(), NEW_WIDGET_TITLE);
    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

    private WeatherWidget getFirstWeatherWidget() {
        return onMainPage().getWeatherWidgets().get(0);
    }
}
