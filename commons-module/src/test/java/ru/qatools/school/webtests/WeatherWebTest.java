package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.qatools.school.steps.websteps.WidgetSteps;
import ru.qatools.school.tp.TPInformerRule;
import ru.yandex.qatools.allure.annotations.Title;


public class WeatherWebTest {

    private static final String MOSCOW = "Moscow";
    private static final String SPB = "Saint Petersburg";

    private DefaultSteps defaultSteps;

    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();
//    @Rule
//    public TPInformerRule tms = new TPInformerRule("merkushevio");


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
        defaultSteps.shouldSee(onMainPage().getWeatherWidgets().get(0));
        defaultSteps.shouldSeeTitleWidgetEqualCity(MOSCOW);
    }

    @Test
    @Title("Должны увидеть элементы виджета")
    @ru.yandex.qatools.allure.annotations.TestCaseId("1")
    public void shouldSeeElements() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        // Дописать проверку для отоброжания элементов на виджете используя метод shouldSee(Array elements)
    }

    @Test
    @Title("Должны увидеть главную страницу только с кнопокой добавить виджет")
    @ru.yandex.qatools.allure.annotations.TestCaseId("1")
    public void shouldSeeButtonOnMainPage() {
        defaultSteps.openMainPageWithCity("");
        defaultSteps.shouldSee(onMainPage().getAddWidget());
        defaultSteps.shouldSeeOnlyButtonAddWidget();
    }

    @Test
    @Title("Должны увидеть главную страницу только с кнопкой добавить виджет")
    @ru.yandex.qatools.allure.annotations.TestCaseId("1")
    public void shouldSeeButtonAddWidgetOnMainPageWithoutParameter() {
        defaultSteps.shouldSeeButtonOnlyAddWidgetOnMainPageWithoutParameter();
        defaultSteps.shouldSeeOnlyButtonAddWidget();
    }

    @Test
    @Title("Можем добавлять виджет на главной странице")
    @ru.yandex.qatools.allure.annotations.TestCaseId("2")
    public void shouldSeeAddWidgetOnMainPage() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSeeWidgetAdd(SPB);
    }

    @Test
    @Title("Может удалить виджет на главной странице")
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
    @Title("Можем переименовать виджет")
    @ru.yandex.qatools.allure.annotations.TestCaseId("4")
    public void shouldSeeRenameWidget() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSeeRenameWidget(MOSCOW, SPB);
    }

    @Test
    @Title("Можем очистить название, после чего назначить нове имя")
    @ru.yandex.qatools.allure.annotations.TestCaseId("4")
    public void shouldSeeClearWidgetName() {
        defaultSteps.openMainPageWithCity(SPB);
        defaultSteps.shouldSeeRenameWidget(SPB, "");
        defaultSteps.shouldSeeRenameWidget("", SPB);
    }

    @Test
    @Title("Можем написать не не полное имя виджета")
    @ru.yandex.qatools.allure.annotations.TestCaseId("4")
    public void shouldSeeNotFullNameWidget() {
        defaultSteps.openMainPageWithCity(MOSCOW);
        defaultSteps.shouldSeeRenameWidget(MOSCOW, SPB.substring(0, SPB.length() / 3));
    }


    @Test
    @Title("Можем менять форматы вывода градусов")
    @ru.yandex.qatools.allure.annotations.TestCaseId("9")
    public void shouldSeeChangeFormatDegree() {
        defaultSteps.shouldSeeChangeFormatTemperature();
    }


}



