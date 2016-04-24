package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Rule;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.qatools.school.tp.TPInformerRule;

/**
 * Created by vananos on 4/20/16.
 */
public class BaseWeatherAppTest {
    public static final String CITY = "Moscow";
    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();
    @Rule
    public TPInformerRule tms = new TPInformerRule("vananos");
    protected DefaultSteps defaultSteps;

    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
    }

    protected MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }
}
