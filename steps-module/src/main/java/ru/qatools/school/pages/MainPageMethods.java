package ru.qatools.school.pages;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.qatools.school.pages.blocks.WeatherWidget;
import java.util.List;

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

    public WeatherWidget findWidget(String city) {
        List<WeatherWidget> list = allWidgets();
        for (WeatherWidget widget : list) {
            if (widget.getInplace().getText().equalsIgnoreCase(city)) {
                return widget;
            }
        }
        return null;
    }

    public void renameWidget(String oldName, String newName) {
        List<WeatherWidget> list = mainPage.getPlaces();
        for (WeatherWidget widget : list) {
            if (widget.getInplace().getText().equalsIgnoreCase(oldName)) {
                clickOnElement(widget.getInplace());
                sendChars(mainPage.getEditInplace(), newName);
            }
        }
    }

    public int countWidgets() {
        return mainPage.getWeatherWidget().size();
    }

    public List<WeatherWidget> allWidgets() {
        return mainPage.getWeatherWidget();
    }

    public Matcher<String> hasItem(String city) {
        final List<WeatherWidget> list = mainPage.getPlaces();
        return new TypeSafeDiagnosingMatcher<String>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("expected place");
            }

            @Override
            protected boolean matchesSafely(String city, Description mismatch) {
                for (WeatherWidget widget : list) {
                    if (widget.getInplace().getText() != city) {
                        mismatch.appendText("return city was ")
                                .appendValue(widget.getInplace().getText());
                        return false;
                    }
                }

                return true;
            }
        };
    }
}
