package ru.qatools.school.webtests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.yandex.qatools.allure.annotations.Title;

import static org.hamcrest.Matchers.greaterThan;

public class WeatherWebTest {

    public static final String MOSCOW = "Moscow";
    public static final String ANOTHER_CITY = "Saint Petersburg";

    private DefaultSteps defaultSteps;

    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();

    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
    }

    @Test
    @Title("Должны видеть виджет на главной странице")
    public void shouldSeeWidgetOnMainPage() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSee(onMainPage().getWeatherWidget().get(0));
    }

    @Test
    @Title("Должен отображаться верный город")
    public void shouldSeeCorrectCity(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSeeText(onMainPage().getWeatherWidget().get(0).getWidgetTitle().getCity().getText(), MOSCOW);
    }

    @Test
    @Title("Должен добавляться еще один город")
    public void shouldBeAbleToAddCity(){
        defaultSteps.openMainPageWithCity(MOSCOW);
        int initialNumberOfCities = onMainPage().getWeatherWidget().size();
        defaultSteps.addNewCity(ANOTHER_CITY);
        int newNumberOfCities = onMainPage().getWeatherWidget().size();
        Assert.assertThat("Городов после добавления нового должно стать больше",
                newNumberOfCities, greaterThan(initialNumberOfCities));
    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

}
