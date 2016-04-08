package ru.qatools.school.steps;

import ru.qatools.school.data.Place;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author lanwen (Merkushev Kirill)
 * *changed by aasx (Kulikov Yurii)
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

    public void verifyLocation(Place newPlace) {
        assertThat("Oops, Location is not the one where the user went ",
                this.place, is(newPlace));
    }
}
