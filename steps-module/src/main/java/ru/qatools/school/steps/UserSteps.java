package ru.qatools.school.steps;

import ru.qatools.school.data.Place;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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

    public void checkPlace(Place neededPlace) {
        assertThat(place, is(neededPlace));
    }

}
