package ru.qatools.school.steps;

import ru.qatools.school.data.Place;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author lanwen (Merkushev Kirill)
 * @author bahek091 (Gavrilov Ivan)
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

    public void shoudBeHere(Place currentPlace){
        assertThat("we are in " + this.place + ", but expected place was " + currentPlace, this.place, is(currentPlace));
    }
}
