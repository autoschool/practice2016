package ru.qatools.school.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

/**
 * @author  by ava1on
 */
public class PageMethods {

    private MainPage mainPage;
    private WebDriverWait webDriverWait;

    public PageMethods(WebDriver driver){
        mainPage = new MainPage(driver);
        webDriverWait = new WebDriverWait(driver, 5);
    }

    public void clickOn(HtmlElement element){
        element.click();
    }

    public void clickOnSeveralTimes(HtmlElement element, int times){
        while(times-- > 0)
            element.click();
    }

    public void enterText(String text, HtmlElement field){
        field.clear();
        field.sendKeys(text);
    }

    public void selectItemFromSuggestedList(String city){
        clickOn(findElementByName(city, mainPage.getWeatherWidgets().get(0).getWidgetTitle().getSuggestedCities()));
        webDriverWait.until(ExpectedConditions.not(ExpectedConditions.stalenessOf(mainPage.getWeatherWidgets().get(0))));
    }

    public HtmlElement findElementByName(String item, List<HtmlElement> list){
        for(HtmlElement elem : list)
            if(elem.getText().equals(item))
                return elem;
        return null;
    }
}
