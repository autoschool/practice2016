package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.yandex.qatools.allure.annotations.Title;

public class WeatherWebTest {

    public static final String MOSCOW = "Moscow";
    public static final String PETERSBURG = "Saint Petersburg";

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
    @Title("Должны видеть виджет Москвы")
    public void shouldSeeMoscowCityWidget() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSeeWidgetWithCity(onMainPage().getWeatherWidget().get(0).getWidgetTitle().getCityNameInLastWidget().getText(), MOSCOW);
    }

    @Test
    @Title("Должен добавиться новый виджет")
    public void shouldBeAdditionalCity() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        int numberOfWidgets = onMainPage().getWeatherWidget().size();
        defaultSteps.addNewWidget();
        defaultSteps.shouldSeeThisNumberOfWidgets(numberOfWidgets + 1);
    }

    @Ignore("Не дописан выбор города из контекстного меню")
    @Test
    @Title("Должен измениться город в последнем виджете")
    public void shouldBeRenamedCityInLastWidget() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.addNewWidget();
        defaultSteps.editLastWidget(PETERSBURG);
        defaultSteps.shouldSeeWidgetWithCity(onMainPage().getWeatherWidget().get(0).getWidgetTitle().getCityNameInLastWidget().getText(), PETERSBURG);
    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

}
