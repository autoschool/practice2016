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

    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
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

    @Test
    @Title("Должны увидеть страницу с кнопокой добавить виджет")
    @ru.yandex.qatools.allure.annotations.TestCaseId("1")
    public void shouldSeeButtonOnMainPage() {
        defaultSteps.openMainPageWithCity("");
        defaultSteps.shouldSeeButtonAddWidgetOnMainPage();
    }

//    @Test
//    @Title("Не указав город должны увидеть только кнопку добавления виджета")
//    @ru.yandex.qatools.allure.annotations.TestCaseId("2")
//    public void shouldSeeOnlyButtonAddWidget() {
//        defaultSteps.openMainPageWithCity("");
//        defaultSteps.shouldSeeOnlyButtonAddWidget();
//    }

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
        defaultSteps.addWidgetOnMainPage(MOSCOW);
        defaultSteps.shouldSeeWidgetRemove();
    }

    @Test
    @Title("Должны увидеть автозаполнение при не полном наборе названия виджета")
    @ru.yandex.qatools.allure.annotations.TestCaseId("10")
    public void shouldAutoCompleteCity() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldAutocompliteCity(SPB);
    }

    @Test
    @Title("Должны увидеть переименование названия виджета")
    @ru.yandex.qatools.allure.annotations.TestCaseId("4")
    public void shouldSeeRenameWidget() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSeeRenameWidget(MOSCOW, SPB);
    }

    @Test
    @Title("Должны меняться форматы вывода градуса")
    @ru.yandex.qatools.allure.annotations.TestCaseId("9")
    public void shouldSeeChangeFormatDegree() {
        defaultSteps.openMainPageWithCity(SPB);
        defaultSteps.shouldSeeChangeFormatDegree();
    }

    @Test
    @Title("Открываем виджет, единица измерения температуры Цельсий")
    @ru.yandex.qatools.allure.annotations.TestCaseId("8")
    public void shouldSeeTemperatureCelcium() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSeeTemperatureCelcium();
    }

    @Test
    @Title("Должны увидеть надпись показателя рассвета")
    @ru.yandex.qatools.allure.annotations.TestCaseId("8")
    public void shouldSeeTitleSunrise() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSeeTitleSunrise();
    }

    @Test
    @Title("Должны увидеть надпись показателя заката")
    @ru.yandex.qatools.allure.annotations.TestCaseId("8")
    public void shouldSeeTitleSunset() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSeeTitleSunset();
    }

    @Test
    @Title("Должны увидеть надпись показателя скорости ветра")
    @ru.yandex.qatools.allure.annotations.TestCaseId("8")
    public void shouldSeeTitleWind() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSeeTitleWind();
    }

    @Test
    @Title("Должны увидеть надпись показателя влажности")
    @ru.yandex.qatools.allure.annotations.TestCaseId("8")
    public void shouldSeeTitleHumidity() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSeeTitleHumidity();
    }

    @Test
    @Title("Должны увидеть картинку для показателя рассвета")
    @ru.yandex.qatools.allure.annotations.TestCaseId("8")
    public void shouldSeeImageSunrise() {
        defaultSteps.openMainPageWithCity(SPB);
        defaultSteps.shouldSeeImageSunrise();
    }

    @Test
    @Title("Должны увидеть картинку для показателя рассвета")
    @ru.yandex.qatools.allure.annotations.TestCaseId("8")
    public void shouldSeeImageSunset() {
        defaultSteps.openMainPageWithCity(SPB);
        defaultSteps.shouldSeeImageSunset();
    }

    @Test
    @Title("Должны увидеть картинку для показателя рассвета")
    @ru.yandex.qatools.allure.annotations.TestCaseId("8")
    public void shouldSeeImageWind() {
        defaultSteps.openMainPageWithCity(SPB);
        defaultSteps.shouldSeeImageWind();
    }

    @Test
    @Title("Должны увидеть картинку для показателя рассвета")
    @ru.yandex.qatools.allure.annotations.TestCaseId("8")
    public void shouldSeeImageHumidity() {
        defaultSteps.openMainPageWithCity(SPB);
        defaultSteps.shouldSeeImageHumidity();
    }

    @Test
    @Title("Должны увидеть основную картинку виджета")
    public void shouldSeeMainImage() {

    }

    @Test
    @Title("Формат времени для значения поля рассвет должен быть 'xx:xx'")
    @ru.yandex.qatools.allure.annotations.TestCaseId("8")
    public void shouldSeeFormatTimeSunrise() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSeeFormatTimeSunrise();
    }

    @Test
    @Title("Формат времени для значения поля закат должен быть 'xx:xx'")
    @ru.yandex.qatools.allure.annotations.TestCaseId("8")
    public void shouldSeeFormatTimeSunset() {
        defaultSteps.openMainPageWithCity(SPB);
        defaultSteps.shouldSeeFormatTimeSunset();
    }

    @Test
    @Title("Формат времени для значения поля скорость ветра должен быть 'xx m/s'")
    @ru.yandex.qatools.allure.annotations.TestCaseId("8")
    public void shouldSeeFormatSpeedWind() {
        defaultSteps.openMainPageWithCity(SPB);
        defaultSteps.shouldSeeFormatSpeedWind();
    }

    @Test
    @Title("Формат времени для значения поля влажность должен быть 'xx %'")
    @ru.yandex.qatools.allure.annotations.TestCaseId("8")
    public void shouldSeeFormatHumidity() {
        defaultSteps.openMainPageWithCity(SPB);
        defaultSteps.shouldSeeFormatHumidity();
    }


}



