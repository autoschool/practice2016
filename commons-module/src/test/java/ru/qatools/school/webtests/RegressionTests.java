package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.qatools.school.tp.TPInformerRule;
import ru.yandex.qatools.allure.annotations.Title;

public class RegressionTests {
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
    @Title("Должна отображаться картинка с погодой")
    @ru.yandex.qatools.allure.annotations.TestCaseId("17")
    public void shouldBeWeatherImage(){
        defaultSteps.openMainPageWithCity(city);
        defaultSteps.shouldSeeWeatherImage();
    }

    @Test
    @Title("Должно отображаться название города по умолчанию для URL с пустым параметром cities")
    public void should(){

    }

    @Test
    @Title("Должно отображаться название города по умолчанию")
    @ru.yandex.qatools.allure.annotations.TestCaseId("21")
    public void shouldSeeDefaultCity(){
        defaultSteps.openMainPageWithCity("");
        defaultSteps.shouldSeeCurrentCity("What a city?");
    }



    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }
}
