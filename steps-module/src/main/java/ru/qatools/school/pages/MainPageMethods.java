package ru.qatools.school.pages;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.qatools.school.pages.blocks.WeatherWidget;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainPageMethods {

    private MainPage mainPage;
    private WebDriver driver;

    public MainPageMethods(WebDriver driver) {
        this.driver = driver;
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

    public WebElement findWidget(String city) {
        WebElement element = (new WebDriverWait(driver, 3))
                .until((ExpectedCondition<WebElement>) d -> {
                    List<WebElement> widgets = mainPage.getPlaces();
                    for (WebElement widget : widgets) {
                        if (widget.getText().equals(city)) {
                            return widget;
                        }
                    }
                    return null;
                });
        return element;
    }

    public void renameWidget(String oldName, String newName) {
        WebElement element = findWidget(oldName);
        clickOnElement(element);
        sendChars(mainPage.getEditPlace(), newName);
    }

    public int countWidgets() {
        return mainPage.getWeatherWidget().size();
    }

    public List<WeatherWidget> allWidgets() {
        return mainPage.getWeatherWidget();
    }

    public void autoComplete(String city) {
        mainPage.getPlaces().get(0).click();
        mainPage.getEditPlace().clear();
        mainPage.getEditPlace().sendKeys(city.substring(0, city.length()/2));
        WebElement element = (new WebDriverWait(driver, 5))
                .until((ExpectedCondition<WebElement>) d -> {
                    List<WebElement> widgets = mainPage.getAutoCompleteValues();
                    for (WebElement widget : widgets) {
                        if (widget.getText().equals(city)) {
                            return widget;
                        }
                    }
                    return null;
                });
        element.click();
    }

    public Matcher<String> hasItem(String city) {
        final List<WebElement> list = mainPage.getPlaces();
        return new TypeSafeDiagnosingMatcher<String>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("expected place");
            }

            @Override
            protected boolean matchesSafely(String city, Description mismatch) {
                for (WebElement widget : list) {
                    if (widget.getText().equals(city)) {
                        mismatch.appendText("return city was ")
                                .appendValue(widget.getText());
                        return true;
                    }
                }

                return false;
            }
        };
    }
}
