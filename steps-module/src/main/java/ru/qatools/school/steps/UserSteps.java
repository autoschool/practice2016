package ru.qatools.school.steps;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.hamcrest.CoreMatchers;
import ru.qatools.school.data.Place;

/**
 * @author lanwen (Merkushev Kirill)
 */
public class UserSteps {

    private Place place;

    private UserSteps() {
    }

    public static UserSteps user() {
        return new UserSteps();
    }

    public UserSteps goTo(Place place) {
        this.place = place;
        return this;
    }

    public UserSteps ensureThatPlaseIsEqualToExpectedValue(Place place) {
        assertThat(this.place, is(equalTo(place)));
        return this;
    }
}
