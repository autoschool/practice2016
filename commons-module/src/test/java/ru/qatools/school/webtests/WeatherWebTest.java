package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Rule;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.qatools.school.tp.TPInformerRule;

/**
 * Created by kurau.
 * Changed by onegines (Eugene Kirienko).
 */
public class WeatherWebTest {

    private static final String CITY = "Saint Petersburg";
    private static final String CITY2 = "Moscow";
    private static final String CITY2_BEGIN = "Mosc";

    private DefaultSteps defaultSteps;

    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();

    @Rule
    public TPInformerRule tms = new TPInformerRule("onegines");

    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

}
