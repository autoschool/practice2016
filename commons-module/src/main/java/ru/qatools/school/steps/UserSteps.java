package ru.qatools.school.steps;

import ru.qatools.school.data.Place;
import static org.junit.Assert.assertEquals;


/**
 * @author lanwen (Merkushev Kirill)
 * @author gladnik (Gladkov Nikolai)
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

    public void shouldBeAtPlace(Place place)
    {
        assertEquals(this.place, place);
    }
}
