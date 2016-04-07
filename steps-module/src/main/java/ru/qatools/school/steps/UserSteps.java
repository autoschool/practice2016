package ru.qatools.school.steps;

import ru.qatools.school.data.Place;
import static org.junit.Assert.assertEquals;

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

    public void shouldBePlace(Place place){
        assertEquals(this.place, place);
    }
}
