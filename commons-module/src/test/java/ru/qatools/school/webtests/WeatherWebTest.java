package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.yandex.qatools.allure.annotations.Title;

/**
 * Created by kurau.
 * Changed by onegines (Eugene Kirienko).
 */
public class WeatherWebTest {

    private static final String CITY = "Moscow";
    private static final String CITY2 = "Saint Petersburg";
    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();
    private DefaultSteps defaultSteps;

    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
    }

    @Test
    @Title("Должны видеть виджет на главной странице")
    public void shouldSeeWidgetOnMainPage() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSee(onMainPage().getFirstWidget());
    }

    @Test
    @Title("Должны видеть виджет города из запроса")
    public void shouldSeeWidgetFromQuery() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSeeWidgetWithCity(onMainPage().getFirstWidget(), CITY);
    }

    @Test
    @Title("Должен добавиться новый виджет")
    public void shouldBeAdditionalWidget() {
        defaultSteps.openMainPageWithCity(CITY);
        int numberOfWidgets = onMainPage().getWeatherWidget().size();
        defaultSteps.addNewWidget();
        defaultSteps.shouldSeeThisNumberOfWidgets(numberOfWidgets + 1);
    }

    @Test
    @Title("Должен измениться город в первом виджете: после ввода полного названия города и Return")
    public void shouldBeRenamedFirstWidgetAfterEnterFullName() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.editLastWidgetWithEnterFullName(CITY2);
        defaultSteps.shouldSeeWidgetWithCity(onMainPage().getFirstWidget(), CITY2);
    }

    @Test
    @Title("Должен измениться город в первом виджете: после ввода начала названия и выбора пункта во всплывающем меню")
    public void shouldBeRenamedFirstWidgetWithChooseItemInPopup() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.editLastWidgetWithPopup(CITY2);
        defaultSteps.shouldSeeWidgetWithCity(onMainPage().getFirstWidget(), CITY2);
    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

}
