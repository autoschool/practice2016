package ru.qatools.school.steps;

import ru.qatools.school.data.Place;
import static org.junit.Assert.*;

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

    public UserSteps checkLocation(Place place){
        assertThat(place, this.place);
        return this;
    }

}
