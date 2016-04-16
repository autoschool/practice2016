package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.yandex.qatools.allure.annotations.Title;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

@RunWith(DataProviderRunner.class)
public class AddWidgetButtonTest {

    @DataProvider
    public static Object[][] dataProviderNumber() {
        return new Object[][]{
                {1},{10}
        };
    }

    public static final String CITY = "Moscow";

    private DefaultSteps defaultSteps;

    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();

    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
    }

    @Test
    @Title("Жмем на кнопку n раз, должно быть n+1 виджетов")
    @UseDataProvider("dataProviderNumber")
    public void shouldSeeNPlusOneWidgetsAfterNClickByAddButton(int n) {
        defaultSteps.openMainPageWithCity(CITY);
        defaultSteps.clickAddWidgetButtonNTimes(n);
        defaultSteps.widgetsAmountShouldBe(n+1);
    }


    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

}
