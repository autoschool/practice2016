package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.qatools.school.tp.TPInformerRule;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

public class WeatherWebTest {

    public static final String MOSCOW = "Moscow";
    public static final String OMSK = "Omsk";
    public static final String CELSIUS = "°C";


    private DefaultSteps defaultSteps;

    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();

    @Rule
    public TPInformerRule tms = new TPInformerRule("bahek091");

    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
    }


    @Test
    @TestCaseId("1")
    @Title("Должны видеть главную страницу с кнопкой добавления виджета")
    public void shouldOpenMainPage() {
        defaultSteps.openMainPage();
        defaultSteps.shouldSee(onMainPage().getAddWidgetButton());
        defaultSteps.shouldBeEqualsCountOfWidgets(0);
    }

    @Test
    @TestCaseId("2")
    @Title("Должны видеть виджет на главной странице")
    public void shouldSeeWidgetOnMainPage() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSee(onMainPage().getWeatherWidgets().get(0));
    }

    @Test
    @TestCaseId("3")
    @Title("Должны видеть город {1} в элементе {2}")
    public void shouldSeeCity() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.strShouldBeInElement(MOSCOW, onMainPage().getWeatherWidgets().get(0).WidgetTitle().getCity());
    }

    @Test
    @TestCaseId("4")
    @Title("Поле с показанием температуры первого виджета на странице не должно быть пустым")
    public void shoudBeTemperatureOnWidget(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldBeNotEmpty(onMainPage().getWeatherWidgets().get(0).WidgetText().digitTemperature());
    }

    @Test
    @TestCaseId("5")
    @Title("Должны видеть у первого виджета кнопку удаления {0}")
    public void shoudSeeRemoveWidgetButton(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSee(onMainPage().getWeatherWidgets().get(0).getActions().removeBtn());
    }

    @Test
    @TestCaseId("6")
    @Title("Должны видеть на странице кнопку добавления виджета {0}")
    public void shoudSeeAddWidgetButton(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSee(onMainPage().getAddWidgetButton());
    }

    @TestCaseId("7")
    @Test
    @Title("Должен добавляться еще один виджет")
    public void shouldBeAbleToAddWidget() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        int numOfWidgets = onMainPage().getWeatherWidgets().size();
        defaultSteps.clickButton(onMainPage().getAddWidgetButton());
        defaultSteps.shouldBeEqualsCountOfWidgets(numOfWidgets + 1);
    }

    @Test
    @TestCaseId("8")
    @Title("После удаления первого из пары виджетов должен остаться один")
    public void shouldBeAbleToRemoveWidget() {
        defaultSteps.openMainPageWithTwoCities(MOSCOW, OMSK);
        int numOfWidgets = onMainPage().getWeatherWidgets().size();
        defaultSteps.clickButton(onMainPage().getWeatherWidgets().get(0).getActions().removeBtn());
        defaultSteps.shouldBeEqualsCountOfWidgets(numOfWidgets - 1);
    }

    @Test
    @TestCaseId("11")
    @Title("Единицы измерения температуры должны быть градусы цельсия")
    public void unitsShouldBe(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.strShouldBeInElement(CELSIUS, onMainPage().getWeatherWidgets().get(0).WidgetText().unitsTemperature());
    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

}
