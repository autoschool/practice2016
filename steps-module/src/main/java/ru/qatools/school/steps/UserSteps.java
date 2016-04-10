package ru.qatools.school.steps;

import ru.qatools.school.data.Place;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

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

    public void shouldBeInPlace(Place place){
        assertThat(place, is(this.place));
    }
}
