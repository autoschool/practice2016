package ru.qatools.school.pages;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.blocks.WeatherWidget;

import java.util.List;
import java.util.regex.Pattern;

public class MainPageMethods {

    private MainPage mainPage;

    public MainPageMethods(WebDriver driver) {
        mainPage = new MainPage(driver);
    }

    public void clickOnElement(WebElement element) {
        element.click();
    }

    public void sendChars(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
        element.sendKeys(Keys.RETURN);
    }

    public void addWidget() {
        mainPage.getAddWidget().click();
    }

    public WebElement findElement(List<WebElement> list, String name) {
        for (WebElement element : list) {
            if (element.getText().equalsIgnoreCase(name)) {
                return element;
            }
        }
        return null;
    }

    public void renameWidget(String oldName, String newName) {
        WebElement element = findElement(getAllPlaces(), oldName);
        clickOnElement(element);
        sendChars(mainPage.getEditPlace(), newName);
    }

    public int countWidgets() {
        return mainPage.getWeatherWidgets().size();
    }

    public List<WeatherWidget> getAllWidgets() {
        return mainPage.getWeatherWidgets();
    }

    public List<WebElement> getAllPlaces() {
        return mainPage.getPlaces();
    }

    public List<WebElement> getTitleValues() {
        return mainPage.getTitleValues();
    }

    public void autoComplete(String city) {
        mainPage.getPlaces().get(0).click();
        mainPage.getEditPlace().clear();
        mainPage.getEditPlace().sendKeys(city.substring(0, city.length()/2));
        WebElement element = null;
        List<WebElement> widget = mainPage.getAutoCompleteValues();
        for (WebElement elem : widget) {
            if (elem.getText().equals(city)) {
                element = elem;
            }
        }
        element.click();
    }

    public Matcher<String> stringMatcher(String regexp) {
        return new TypeSafeDiagnosingMatcher<String>() {
            @Override
            protected boolean matchesSafely(String s, Description description) {
                Pattern pattern = Pattern.compile(regexp);
                java.util.regex.Matcher matcher = pattern.matcher(s);
                description.appendText("String is " + s);
                return matcher.matches();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("expected string format");
            }
        };
    }

    public Matcher<String> hasItem(List<WebElement> list) {
        return new TypeSafeDiagnosingMatcher<String>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("expected list");
            }

            @Override
            protected boolean matchesSafely(String word, Description mismatch) {
                for (WebElement widget : list) {
                    if (widget.getText().equals(word)) {
                        mismatch.appendText("return string was ")
                                .appendText(widget.getText());
                        return true;
                    }
                }

                return false;
            }
        };
    }

    public MainPage getMainPage() {
        return mainPage;
    }
}
