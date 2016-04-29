package ru.qatools.school;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import ru.qatools.school.entity.City;

import java.util.List;

public class ApiMatchers {


    public static Matcher<String> findInList(List<City> list) {
        return new TypeSafeDiagnosingMatcher<String>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("expected inlist");
            }

            @Override
            protected boolean matchesSafely(String word, Description mismatch) {
                for (City city : list) {
                    if (city.getName().toLowerCase().contains(word.toLowerCase())) {
                        mismatch.appendText("return string was ")
                                .appendText(city.getName());
                        return true;
                    }
                }

                return false;
            }
        };
    }
}
