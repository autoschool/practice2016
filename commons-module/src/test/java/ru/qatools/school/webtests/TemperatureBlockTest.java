package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.qatools.school.tp.TPInformerRule;
import ru.yandex.qatools.allure.annotations.Title;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import ru.yandex.qatools.allure.annotations.TestCaseId;


public class TemperatureBlockTest {


    public static final String CITY = "Moscow";

    private DefaultSteps defaultSteps;

    @Rule
    public TPInformerRule tms = new TPInformerRule("vananos");

    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();

    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
    }

    @TestCaseId("5")
    @Test
    @Title("Жмем на блок температуры, проверяем что значение и формат температуры были изменены")
    @UseDataProvider("dataProviderNumber")
    public void temperatureBlockShouldChanges() {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.saveTemperatureState(0);
        defaultSteps.clickTemperatureBlock(0);
        defaultSteps.checkThatTemperatureStateWasChanged();
        defaultSteps.saveTemperatureState(0);
        defaultSteps.clickTemperatureBlock(0);
        defaultSteps.checkThatTemperatureStateWasChanged();
    }


    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

}