package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.qatools.school.tp.TPInformerRule;
import ru.yandex.qatools.allure.annotations.Title;

public class WeatherWebTest {

    public static final String CITY = "Moscow";

    private DefaultSteps defaultSteps;

    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();

    @Rule
    public TPInformerRule tms = new TPInformerRule("chibert");

    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
    }

    @Test
    @Title("Должны видеть кнопку добавления виджета на странице, которая открыта без заданного города")
    @ru.yandex.qatools.allure.annotations.TestCaseId("1")
    public void shouldSeeAddWidgetButtonOnPageWithNoCity() {
        defaultSteps.openMainPage();
        defaultSteps.shouldSee(onMainPage().getAddWidgetButton());
    }

    @Test
    @Title("Должны видеть заданный город в виджете на главной странице")
    @ru.yandex.qatools.allure.annotations.TestCaseId("2")
    public void verifyCityInWidgetOnMainPage() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSeeCityInWidget(onMainPage().getWeatherWidgetsList().get(0)
                .getWidgetTitle().getCityName(), CITY);
    }

    @Test
    @Title("После клика по кнопке добавления должны видеть 2 виджета на главной странице")
    @ru.yandex.qatools.allure.annotations.TestCaseId("3")
    public void shouldSeeTwoWidgetsOnMainPageAfterAddOne() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.clickElement(onMainPage().getAddWidgetButton());
        defaultSteps.shouldSee(onMainPage().getWeatherWidgetsList().get(0));
        defaultSteps.shouldSee(onMainPage().getWeatherWidgetsList().get(1));
    }

    @Test
    @Title("Должны видеть все элементы в виджете на главной странице")
    @ru.yandex.qatools.allure.annotations.TestCaseId("6")
    public void shouldSeeAllElementsInWidgetOnMainPage() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.shouldSee(onMainPage().getWeatherWidgetsList().get(0).getWidgetTitle().getCityName());
        defaultSteps.shouldSee(onMainPage().getWeatherWidgetsList().get(0).getWidgetTitle().getTimeAndDate());
        defaultSteps.shouldSee(onMainPage().getWeatherWidgetsList().get(0).getWidgetText().getWeatherImage());
        defaultSteps.shouldSee(onMainPage().getWeatherWidgetsList().get(0).getWidgetText().getTemperature());
        defaultSteps.shouldSee(onMainPage().getWeatherWidgetsList().get(0).getWidgetText().getSunriseInfoLine());
        defaultSteps.shouldSee(onMainPage().getWeatherWidgetsList().get(0).getWidgetText().getSunsetInfoLine());
        defaultSteps.shouldSee(onMainPage().getWeatherWidgetsList().get(0).getWidgetText().getWindInfoLine());
        defaultSteps.shouldSee(onMainPage().getWeatherWidgetsList().get(0).getWidgetText().getHumidityInfoLine());
        defaultSteps.shouldSee(onMainPage().getWeatherWidgetsList().get(0).getWidgetActions().getWidgetDeleteButton());

    }

    @Test
    @Title("Должен удалиться виджет после нажатия на кнопку удаления")
    @ru.yandex.qatools.allure.annotations.TestCaseId("17")
    public void shouldBeDeletedOneWidget() {
        defaultSteps.openMainPageWithCity(CITY);
        int amountOfWidgets = onMainPage().getWeatherWidgetsList().size();
        defaultSteps.clickElement(onMainPage().getWeatherWidgetsList().get(0).getWidgetActions().getWidgetDeleteButton());
        defaultSteps.shouldBeThisNumberOfElements(onMainPage().getWeatherWidgetsList(), amountOfWidgets - 1);
        defaultSteps.shouldSee(onMainPage().getAddWidgetButton());

    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

}
