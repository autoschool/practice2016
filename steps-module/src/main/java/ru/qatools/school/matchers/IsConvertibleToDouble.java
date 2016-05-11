package ru.qatools.school.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * @author gladnik (Nikolai Gladkov)
 */
public class IsConvertibleToDouble extends TypeSafeMatcher<String> {

    public boolean matchesSafely(String text) {
        try {
            Double.parseDouble(text);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public void describeTo(Description description) {
        description.appendText("string is convertible to Double");
    }

    protected void describeMismatchSafely(String text, Description mismatchDescription) {
        mismatchDescription.appendText("string ").appendValue(text).appendText(" is not convertible to Double");
    }

    @Factory
    public static Matcher<String> isDouble() {
        return new IsConvertibleToDouble();
    }
}
