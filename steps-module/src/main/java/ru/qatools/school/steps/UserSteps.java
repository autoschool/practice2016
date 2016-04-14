package ru.qatools.school.steps;
import ru.qatools.school.data.Place;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

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

    public void checkUserLocation(Place place){
        assertThat("Wrong user's location", this.place, is(place));
    }
}