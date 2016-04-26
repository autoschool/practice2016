package ru.qatools.school.pages;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.qatools.school.pages.blocks.WeatherWidget;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Created by ava1on
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
        field.sendKeys(Keys.ENTER);
    }

    public String getCurrentDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("h a, dd MMM yy (XXX)", Locale.ENGLISH);
        return sdf.format(new Date());
    }

    public void selectElementFromSuggestedList(String city){
        HtmlElement element = findElementByName(city, mainPage.getWeatherWidget().get(0).getWidgetTitle().getSugesstedCities());
        clickOn(element);
        webDriverWait.until(ExpectedConditions.not(ExpectedConditions.stalenessOf(mainPage.getWeatherWidget().get(0))));
    }

    public WeatherWidget findWidgetByName(String city){
        List<WeatherWidget> widgets=mainPage.getWeatherWidget();
        for (WeatherWidget element : widgets){
            if (element.getWidgetTitle().getCityName().getText().equals(city))
                return element;
        }
        return null;
    }

    public HtmlElement findElementByName(String item, List<HtmlElement> list){
        for(HtmlElement elem : list)
            if(elem.getText().equals(item))
                return elem;
        return null;
    }

    public Matcher<HtmlElement> regexMatcher(String part){
        return new TypeSafeDiagnosingMatcher<HtmlElement>() {
            @Override
            protected boolean matchesSafely(HtmlElement element, Description description) {
                Pattern pattern = Pattern.compile(".*"+part+".*");
                java.util.regex.Matcher matcher = pattern.matcher(element.getText());
                if (matcher.matches())
                    return true;
                description.appendText("was ").appendValue(element.getText());
                return false;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Название города должно содержать строку ").appendValue(part);
            }
        };
    }
}
