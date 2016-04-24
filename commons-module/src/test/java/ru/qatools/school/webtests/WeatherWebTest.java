package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
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
    public TPInformerRule tms = new TPInformerRule("bahek091");

    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
    }

    @ru.yandex.qatools.allure.annotations.TestCaseId("1")
    @Features("Виджет погоды")
    @Stories("Проверка загрузки главной страницы")
    @Test
    @Title("Должны видеть виджет на главной странице")
    public void shouldOpenMainPage() {
        defaultSteps.openMainPageWithCity(MOSCOW);
    }

    @ru.yandex.qatools.allure.annotations.TestCaseId("2")
    @Features("Виджет погоды")
    @Stories("Проверка отображения виджета на главной странице")
    @Test
    @Title("Должны видеть виджет на главной странице")
    public void shouldSeeWidgetOnMainPage() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSee(onMainPage().getWeatherWidgets().get(0));
    }

    @ru.yandex.qatools.allure.annotations.TestCaseId("3")
    @Features("Виджет погоды")
    @Test
    @Title("Должны видеть город {1} в элементе {2}")
    public void shouldSeeCity() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.strShouldBeInElement(MOSCOW, onMainPage().getWeatherWidgets().get(0).WidgetTitle().getCity());
    }

    @ru.yandex.qatools.allure.annotations.TestCaseId("4")
    @Features("Виджет погоды")
    @Test
    @Title("Поле с показанием температуры первого виджета на странице не должно быть пустым")
    public void shoudBeTemperatureOnWidget(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldBeNotEmpty(onMainPage().getWeatherWidgets().get(0).WidgetText().digitTemperature());
    }

    @ru.yandex.qatools.allure.annotations.TestCaseId("5")
    @Features("Виджет погоды")
    @Test
    @Title("Должны видеть у первого виджета кнопку удаления {0}")
    public void shoudSeeRemoveWidgetButton(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSee(onMainPage().getWeatherWidgets().get(0).getActions().removeBtn());
    }

    @ru.yandex.qatools.allure.annotations.TestCaseId("6")
    @Features("Виджет погоды")
    @Test
    @Title("Должны видеть на странице кнопку добавления виджета {0}")
    public void shoudSeeAddWidgetButton(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSee(onMainPage().getAddWidgetButton());
    }

    @ru.yandex.qatools.allure.annotations.TestCaseId("7")
    @Features("Виджет погоды")
    @Test
    @Title("Должен добавляться еще один виджет")
    public void shouldBeAbleToAddWidget() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        int numOfWidgets = onMainPage().getWeatherWidgets().size();
        defaultSteps.clickButton(onMainPage().getAddWidgetButton());
        defaultSteps.shouldBeEqualsCountOfWidgets(numOfWidgets + 1);
    }

    @ru.yandex.qatools.allure.annotations.TestCaseId("8")
    @Features("Виджет погоды")
    @Test
    @Title("После удаления первого из пары виджетов должен остаться один")
    public void shouldBeAbleToRemoveWidget() {
        defaultSteps.openMainPageWithTwoCities(MOSCOW, OMSK);
        int numOfWidgets = onMainPage().getWeatherWidgets().size();
        defaultSteps.clickButton(onMainPage().getWeatherWidgets().get(0).getActions().removeBtn());
        defaultSteps.shouldBeEqualsCountOfWidgets(numOfWidgets - 1);
    }

    @ru.yandex.qatools.allure.annotations.TestCaseId("11")
    @Features("Виджет погоды")
    @Test
    @Title("Единицы измерения температуры должны быть градусы цельсия")
    public void unitsShouldBe(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.strShouldBeInElement(CELSIUS, onMainPage().getWeatherWidgets().get(0).WidgetText().unitsTemperature());
    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

}
