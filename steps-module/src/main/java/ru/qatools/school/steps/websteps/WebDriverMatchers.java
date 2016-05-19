package ru.qatools.school.steps.websteps;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by merkushevio on 29.04.2016.
 */

public class WebDriverMatchers {

    public static Matcher<String> stringMatcher(String regexp) {
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

    public static Matcher<String> hasItem(List<WebElement> list) {
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
}
