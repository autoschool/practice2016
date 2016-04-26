package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.qatools.school.tp.TPInformerRule;

public class WeatherWebTest {

    private static final String MAIN_CITY = "Moscow";
    private static final String SECOND_CITY = "Saint Petersburg";

    private DefaultSteps defaultSteps;

    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();

    @Rule
    public TPInformerRule tms = new TPInformerRule("vladgotodo");

    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
    }

    @Test
    @Title("Должны видеть кнопку добавления виджета и отсутсвие виджета на главной странице сервиса")
    public void shouldSeePlusButton(){
        defaultSteps.openMainPageWithoutCity();
        defaultSteps.shouldSee(onMainPage().getPlusWidgetButton());
        defaultSteps.shouldSeeCountOfWidgets(0);
    }

    @Test
    @Title("Должны видеть картинку погоды")
    public void shouldSeeWeatherPicture() {
        defaultSteps.openMainPageWithCity(MAIN_CITY);
        defaultSteps.shouldSee(onMainPage().getWeatherWidgetList().get(0).getWidgetText().getWeatherImage());
    }

    @Test
    @Title("Должны видеть виджет с новым городом")
    public void shouldChangeWidgetCity() {
        defaultSteps.openMainPageWithCity(MAIN_CITY);
        String newCity = "London";
        defaultSteps.changeCity(onMainPage().getWeatherWidgetList().get(0), newCity);
    }

    @Test
    @Title("Должны видеть виджет на странице города")
    public void shouldSeeWidgetOnMainPage() {
        defaultSteps.openMainPageWithCity(MAIN_CITY);
        defaultSteps.shouldSee(onMainPage().getWeatherWidgetList().get(0));
    }

    @Test
    @Title("Должны видеть в заголовке виджета запрашиваемый город")
    public void shouldSeeWidgetWithOurCity(){
        defaultSteps.openMainPageWithCity(MAIN_CITY);
        defaultSteps.shouldSeeText(onMainPage().getWeatherWidgetList().get(0).getWidgetTitle().getCity(), MAIN_CITY);
    }

    @Test
    @Title("Должны изменить город в заголовке виджета и увидеть новый")
    public void shouldChangeCity() {
        defaultSteps.openMainPageWithCity(MAIN_CITY);
        defaultSteps.shouldSeeText(onMainPage().getWeatherWidgetList().get(0).getWidgetTitle().getCity(), MAIN_CITY);
        defaultSteps.changeCity(onMainPage().getWeatherWidgetList().get(0), SECOND_CITY);
        defaultSteps.shouldSeeText(onMainPage().getWeatherWidgetList().get(0).getWidgetTitle().getCity(), SECOND_CITY);
    }

    @Test
    @Title("Должны видеть на один виджет больше")
    public void shouldSeePlusOneCountOfWidgets(){
        defaultSteps.openMainPageWithCity(MAIN_CITY);
        int widgetCount = onMainPage().getWeatherWidgetList().size();
        defaultSteps.clickElement(onMainPage().getPlusWidgetButton());
        defaultSteps.shouldSeeCountOfWidgets(widgetCount + 1);
    }

    @Test
    @Title("Должны видеть на один виджет меньше")
    public void shouldSeeMinusOneCountOfWidgets(){
        defaultSteps.openMainPageWithCity(MAIN_CITY);
        int widgetCount = onMainPage().getWeatherWidgetList().size();
        defaultSteps.clickElement(onMainPage().getWeatherWidgetList().get(0).getMinusWidgetButton());
        defaultSteps.shouldSeeCountOfWidgets(widgetCount - 1);
    }

    @Test
    @Title("Должны видеть во втором виджете надпись \"What a city?\"")
    public void shouldSeeRequestedCityWidgetAndOneMoreWithDefaulSign() {
        defaultSteps.openMainPageWithCity(MAIN_CITY);
        defaultSteps.clickElement(onMainPage().getPlusWidgetButton());
        defaultSteps.clickElement(onMainPage().getPlusWidgetButton());
        defaultSteps.clickElement(onMainPage().getWeatherWidgetList().get(2).getMinusWidgetButton());
        defaultSteps.shouldSeeText(onMainPage().getWeatherWidgetList().get(1).getWidgetTitle().getCity(), "What a city?");
    }


    @Test
    @Title("Должны видеть температуру в цельсиях")
    public void shouldSeeTempInCelsius() {
        defaultSteps.openMainPageWithCity(MAIN_CITY);
        defaultSteps.shouldSeeText(onMainPage().getWeatherWidgetList().get(0).getWidgetText().getTempUnits(), "°C");
        defaultSteps.shouldSee(onMainPage().getWeatherWidgetList().get(0).getWidgetText().getTempDigits());
    }

    @Test
    @Title("Должны видеть температуру в кельвинах")
    public void shouldSeeTempInKelvinAfterOneClick() {
        defaultSteps.openMainPageWithCity(MAIN_CITY);
        defaultSteps.clickElement(onMainPage().getWeatherWidgetList().get(0).getWidgetText().getTempUnits());
        defaultSteps.shouldSeeText(onMainPage().getWeatherWidgetList().get(0).getWidgetText().getTempUnits(), "°K");
        defaultSteps.shouldSee(onMainPage().getWeatherWidgetList().get(0).getWidgetText().getTempDigits());
    }

    @Test
    @Title("Должны видеть температуру по фарингейту")
    public void shouldSeeTempInFahrenheitAfterTwoClicks() {
        defaultSteps.openMainPageWithCity(MAIN_CITY);
        defaultSteps.clickElement(onMainPage().getWeatherWidgetList().get(0).getWidgetText().getTempUnits());
        defaultSteps.clickElement(onMainPage().getWeatherWidgetList().get(0).getWidgetText().getTempUnits());
        defaultSteps.shouldSeeText(onMainPage().getWeatherWidgetList().get(0).getWidgetText().getTempUnits(), "°F");
        defaultSteps.shouldSee(onMainPage().getWeatherWidgetList().get(0).getWidgetText().getTempDigits());
    }


    @Test
    @Title("Должны видеть температуру в единицах измерения \"кайф\"")
    public void shouldSeeTempInKaifAfterThreeClicks() {
        defaultSteps.openMainPageWithCity(MAIN_CITY);
        defaultSteps.clickElement(onMainPage().getWeatherWidgetList().get(0).getWidgetText().getTempUnits());
        defaultSteps.clickElement(onMainPage().getWeatherWidgetList().get(0).getWidgetText().getTempUnits());
        defaultSteps.clickElement(onMainPage().getWeatherWidgetList().get(0).getWidgetText().getTempUnits());
        defaultSteps.shouldSeeText(onMainPage().getWeatherWidgetList().get(0).getWidgetText().getTempUnits(), "°Kaif");
        defaultSteps.shouldSee(onMainPage().getWeatherWidgetList().get(0).getWidgetText().getTempDigits());
    }

    @Test
    @Title("Должны видеть температуру в цельсиях")
    public void shouldSeeTempInCelsiusAfterFourClicks() {
        defaultSteps.openMainPageWithCity(MAIN_CITY);
        defaultSteps.clickElement(onMainPage().getWeatherWidgetList().get(0).getWidgetText().getTempUnits());
        defaultSteps.clickElement(onMainPage().getWeatherWidgetList().get(0).getWidgetText().getTempUnits());
        defaultSteps.clickElement(onMainPage().getWeatherWidgetList().get(0).getWidgetText().getTempUnits());
        defaultSteps.clickElement(onMainPage().getWeatherWidgetList().get(0).getWidgetText().getTempUnits());
        defaultSteps.shouldSeeText(onMainPage().getWeatherWidgetList().get(0).getWidgetText().getTempUnits(), "°C");
        defaultSteps.shouldSee(onMainPage().getWeatherWidgetList().get(0).getWidgetText().getTempDigits());
    }

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }

}
