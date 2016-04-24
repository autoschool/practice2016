package ru.qatools.school.webtests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.pages.MainPage;
import ru.qatools.school.rules.WebDriverRule;
import ru.qatools.school.steps.websteps.DefaultSteps;
import ru.yandex.qatools.allure.annotations.Title;

public class SmokeTests {
    private DefaultSteps defaultSteps;

    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();

    /*@Rule
    public TPInformerRule tms = new TPInformerRule("master2106");
*/
    @Before
    public void initSteps() {
        defaultSteps = new DefaultSteps(webDriverRule.getDriver());
    }

    @Test
    @Title("При открытии главной страницы должен отображаться плюс")
    public void shouldSeeNewWidgetButton(){
        defaultSteps.openMainPage();
        defaultSteps.shouldSeeButtonAddWidget();
    }

    @Test
    @Title("Удаление виджета со страницы")
    public void shouldCanBeDeleted(){
        defaultSteps.openMainPageWithCity("Saratov");
        int count = defaultSteps.getCountWidgets();
        defaultSteps.deleteWidget();
        defaultSteps.shouldSeeWidgets(count-1);
    }
    
    @Test
    @Title("Изменение названия города в виджете")
    public void shouldSeeChangedCity(){
        defaultSteps.openMainPageWithCity("Moscow");
        defaultSteps.writeCityName("Saratov");
        defaultSteps.shouldSeeCurrentCity("Saratov");
    }





    private MainPage onMainPage() {
        return new MainPage(webDriverRule.getDriver());
    }
}
