package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.qatools.school.tp.TPInformerRule;
import ru.yandex.qatools.allure.annotations.Title;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import ru.yandex.qatools.allure.annotations.TestCaseId;


@RunWith(DataProviderRunner.class)
public class RemoveButtonTest {

    @DataProvider
    public static Object[][] dataProviderNumber() {
        return new Object[][]{
                {0},{1}
        };
    }

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

    @TestCaseId("3")
    @Test
    @Title("Жмем на кнопку добавления n раз, Затем удаляем средний виджет")
    @UseDataProvider("dataProviderNumber")
    public void shouldSeeNWidgetsAfterOneClickByRemoveButton(int n) {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.clickAddWidgetButtonNTimes(n);
        defaultSteps.clickRemoveWidgetButton(n/2);
        defaultSteps.widgetsAmountShouldBe(n);
    }


    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

}