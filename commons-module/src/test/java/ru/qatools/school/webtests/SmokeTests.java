package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.qatools.school.tp.TPInformerRule;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.annotations.TestCaseId;

public class SmokeTests {
    private String city = "Moscow";
    private DefaultSteps defaultSteps;

    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();

    @Rule
    public TPInformerRule tms = new TPInformerRule("master2106");

    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
    }

    @Test
    @Title("Должны видеть виджет на главной странице")
    @TestCaseId("9")
    public void shouldSeeWidgetOnMainPage() {
        defaultSteps.openMainPageWithCity("Moscow");
        defaultSteps.shouldSee(onMainPage().getWeatherWidget().get(0));
    }

    @Test
    @Title("При открытии главной страницы должна отображаться кнопка добавления виджета")
    @TestCaseId("10")
    public void shouldSeeNewWidgetButton(){
        defaultSteps.openMainPage();
        defaultSteps.shouldSeeButtonAddWidget();
    }

    @Test
    @Title("Должен добавиться один виджет")
    @TestCaseId("11")
    public void shouldBeAddedOneWidget(){
        defaultSteps.openMainPage();
        defaultSteps.addOneWidget();
        defaultSteps.shouldSeeWidgets(1);
    }

    @Test
    @Title("Должны видеть на один виджет больше")
    @TestCaseId("15")
    public void shouldSeeNewWidget() {
        defaultSteps.openMainPageWithCity("Moscow");
        int countWidgets = defaultSteps.getCountWidgets();
        defaultSteps.addOneWidget();
        defaultSteps.shouldSeeWidgets(countWidgets+1);
    }

    @Test
    @Title("Удаление виджета со страницы")
    @TestCaseId("12")
    public void shouldCanBeDeleted(){
        defaultSteps.openMainPageWithCity("Saratov");
        int count = defaultSteps.getCountWidgets();
        defaultSteps.deleteWidget();
        defaultSteps.shouldSeeWidgets(count-1);
    }
    
    @Test
    @Title("Изменение названия города в виджете")
    @ru.yandex.qatools.allure.annotations.TestCaseId("13")
    public void shouldSeeChangedCity(){
        defaultSteps.openMainPageWithCity(city);
        defaultSteps.writeCityName("Saratov");
        defaultSteps.shouldSeeCurrentCity("Saratov");
    }

    @Test
    @Title("Проверяем город в виджете погоды")
    @TestCaseId("26")
    public void shouldSeeChosenCity() {
        defaultSteps.openMainPageWithCity(city);
        defaultSteps.shouldSeeCurrentCity(city);
    }

    @Test
    @Title("У виджета должна быть заполнена информация о погоде")
    @TestCaseId("18")
    public void shouldSeeWeatherInfo(){
        defaultSteps.openMainPageWithCity(city);
        defaultSteps.shouldSeeSunriseInfo();
        defaultSteps.shouldSeeSunsetInfo();
        defaultSteps.shouldSeeHumidityInfo();
        defaultSteps.shouldSeeWindInfo();
    }

    @Test
    @Title("Должно отображаться несколько городов")
    @TestCaseId("20")
    public void shouldSeeSeveralCities(){
        String inputString = "Moscow,Omsk,Saratov";

        defaultSteps.openMainPageWithCity(inputString);
        defaultSteps.shouldSeeWidgets(inputString.split(",").length);
    }

    @Test
    @Title("Должно отображаться значение температуры")
    @TestCaseId("")
    public void shouldSeeTemperature(){
        defaultSteps.openMainPageWithCity(city);
        defaultSteps.shouldSeeCurrentTemperature();
    }





    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }
}
