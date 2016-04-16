package ru.qatools.school.webtests;

/* @author arrumm (Arkhipov Roman) */

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.yandex.qatools.allure.annotations.Title;

public class WeatherWebTest {

    public static final String MOSCOW = "Moscow";

    public static final String TOMSK = "Tomsk";

    private DefaultSteps defaultSteps;

    //для использования before&after класса WebDriverRule
    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();

    //инит класса степов с подготовленным веб-драйвером перед тестами
    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
    }

    @Test
    @Title("Должны видеть виджет для города в URL на главной странице")
    public void shouldSeeWidgetWithCityInURLOnMainPage() {
        //драйвер грузит страницу по url с городом в строке
        defaultSteps.openMainPageWithCity(TOMSK);
        //получаем драйвер, ищем виджет, берем первый, ищем в нем заголовок, получаем город
        defaultSteps.expectedElementTextIsSameToText(
                onMainPage().getWeatherWidget().get(0).getWidgetTitle().getNameOfCity(),
                TOMSK);
    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

}