package ru.qatools.school.matchers;

import org.hamcrest.*;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

/**
 * @author gladnik (Nikolai Gladkov)
 */
public class HasTextSafeMatcher extends TypeSafeMatcher<WebElement> {

    private final Matcher<String> textMatcher;

    private HasTextSafeMatcher(Matcher<String> textMatcher) {
        this.textMatcher = textMatcher;
    }

    public boolean matchesSafely(WebElement item) {
        try {
            return this.textMatcher.matches(item.getText());
        } catch (WebDriverException var3) {
            return false;
        }
    }

    public void describeTo(Description description) {
        description.appendText("element text ").appendDescriptionOf(this.textMatcher);
    }

    protected void describeMismatchSafely(WebElement item, Description mismatchDescription) {
        mismatchDescription.appendText("text of element ").appendValue(item).appendText(" was ").appendValue(item.getText());
    }

    @Factory
    public static Matcher<WebElement> hasText(Matcher<String> textMatcher) {
        return new HasTextSafeMatcher(textMatcher);
    }

    @Factory
    public static Matcher<WebElement> hasText(String text) {
        return new HasTextSafeMatcher(Matchers.is(text));
    }
}
