package ru.qatools.school.steps;

import ru.qatools.school.data.Place;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

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
    public void verifyLocation(Place newPlace){
        /*except that our this.place that we get after goTo
          equal to newPlace (left part) */
        assertThat(newPlace, is(this.place));

    }
}
