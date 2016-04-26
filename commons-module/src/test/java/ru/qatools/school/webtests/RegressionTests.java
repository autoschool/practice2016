package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.qatools.school.tp.TPInformerRule;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.annotations.Title;

public class RegressionTests {
    private String city1 = "Moscow";
    private String city2 = "Omsk";
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
    @Title("Должна отображаться картинка с погодой")
    @TestCaseId("17")
    public void shouldSeeWeatherImage(){
        defaultSteps.openMainPageWithCity(city1);
        defaultSteps.shouldSeeWeatherImage();
    }

    @Test
    @Title("Должна отображаться информация о рассвете")
    @TestCaseId("23")
    public void shouldSeeSunriseInfo(){
        defaultSteps.openMainPageWithCity(city1);
        defaultSteps.shouldSeeSunriseInfo();
    }

    @Test
    @Title("Должна отображаться информация о закате")
    @TestCaseId("42")
    public void shouldSeeSunsetInfo(){
        defaultSteps.openMainPageWithCity(city1);
        defaultSteps.shouldSeeSunsetInfo();
    }

    @Test
    @Title("Должна отобржааться информация о ветре")
    @TestCaseId("24")
    public void shouldSeeWindInfo(){
        defaultSteps.openMainPageWithCity(city1);
        defaultSteps.shouldSeeWindInfo();
    }

    @Test
    @Title("Должна отображаться информация о влажности")
    @TestCaseId("25")
    public void shouldSeeHumidityInfo(){
        defaultSteps.openMainPageWithCity(city1);
        defaultSteps.shouldSeeHumidityInfo();
    }

    @Test
    @Title("Должно отображаться название города по умолчанию для URL с пустым параметром cities")
    public void shouldSeeDefaultName(){
        defaultSteps.openMainPageWithCity("");
        defaultSteps.shouldSeeCurrentCity("What a city?");
    }

    @Test
    @Title("Должно отображаться название города по умолчанию")
    @TestCaseId("21")
    public void shouldSeeDefaultCity(){
        defaultSteps.openMainPageWithCity("");
        defaultSteps.shouldSeeCurrentCity("What a city?");
    }

    @Test
    @Title("Название города должно прописаться в параметры URL")
    @TestCaseId("39")
    public void shouldBeAddedOneCityInURLParameters(){
        defaultSteps.openMainPage();
        defaultSteps.addOneWidget();
        defaultSteps.writeCityName(city1);
        defaultSteps.shouldSeeURLWithThisCity(city1);
    }

    @Test
    @Title("Должны видеть два города в URL")
    @TestCaseId("40")
    public void shouldSeeTwoCitiesInURL(){
        defaultSteps.openMainPageWithCity(city1);
        defaultSteps.addOneWidget();
        defaultSteps.writeCityName(city2);
        defaultSteps.shouldSeeURLParameterValue(city2 + "," +city1);
    }

    @Test
    @Title("Должны увидеть название города по умолчанию, если вбить пустую строку")
    @TestCaseId("41")
    public void shouldSeeDefaultCityIfSendEmptyName(){
        defaultSteps.openMainPageWithCity(city1);
        defaultSteps.writeCityName("");
        defaultSteps.shouldSeeCurrentCity("What a city?");
    }

    @Test
    @Title("Должны видеть измененный город в URL")
    @TestCaseId("38")
    public void shouldSeeChangedCityInURL(){
        defaultSteps.openMainPageWithCity(city1);
        defaultSteps.writeCityName(city2);
        defaultSteps.shouldSeeURLWithThisCity(city2);
    }


    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }
}
