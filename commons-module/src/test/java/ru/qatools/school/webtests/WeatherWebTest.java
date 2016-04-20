package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.qatools.school.tp.TPInformerRule;
import ru.yandex.qatools.allure.annotations.Title;


public class WeatherWebTest {

    private static final String MOSCOW = "Moscow";
    private static final String SPB = "Saint Petersburg";

    private DefaultSteps defaultSteps;

    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();
    @Rule
    public TPInformerRule tms = new TPInformerRule("merkushevio");


    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
    }

    @Test
    @ru.yandex.qatools.allure.annotations.TestCaseId("1")
    @Title("Должны видеть виджет на главной странице")
    public void shouldSeeWidgetOnMainPage() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSee(onMainPage().getWeatherWidget().get(0));
    }

    @Test
    @Title("Должны увидеть виджет с именем города на главной странице")
    @ru.yandex.qatools.allure.annotations.TestCaseId("1")
    public void shouldSeeWidgetWithTitleCity() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSeeTitleWidgetEqualCity(MOSCOW);
    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

    @Test
    @Title("Должны видеть добавленный виджет на главной странице")
    @ru.yandex.qatools.allure.annotations.TestCaseId("2")
    public void shouldSeeAddWidgetOnMainPage() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSeeWidgetAdd(SPB);
    }

    @Test
    @Title("Должны увидеть виджеты, кроме удаленного на главной странице")
    @ru.yandex.qatools.allure.annotations.TestCaseId("3")
    public void shouldSeeRemoveWidgetOnMainPage() {
        defaultSteps.openMainPageWithCity(SPB);
        defaultSteps.shouldSeeWidgetRemove();
    }

    @Test
    @Title("Должны увидеть автозаполнение при не полном наборе названия")
    @ru.yandex.qatools.allure.annotations.TestCaseId("4")
    public void shouldAutoCompleteCity() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldAutocompliteCity(SPB);
    }

    @Test
    @Title("Должны меняться форматы вывода градуса")
    @ru.yandex.qatools.allure.annotations.TestCaseId("6")
    public void shouldSeeChangeFormatDegree() {
        defaultSteps.openMainPageWithCity(SPB);
        defaultSteps.shouldSeeChangeFormatDegree();
    }

    @Test
    @Title("Открываем виджет, единица измерения температуры Цельсий")
    @ru.yandex.qatools.allure.annotations.TestCaseId("5")
    public void shouldSeeTemperatureCelcium() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSeeTemperatureCelcium();
    }

}


