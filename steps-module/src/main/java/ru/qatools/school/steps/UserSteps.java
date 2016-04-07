package ru.qatools.school.steps;

import ru.qatools.school.data.Place;
import org.junit.Assert;
import static org.hamcrest.core.Is.is;


public class UserSteps {
    
    private Place place;

    private UserSteps() {
    }

    public static UserSteps user() {
        return new UserSteps();
    }

    public UserSteps shouldSeeCurrentPlace(Place place) {
        Assert.assertThat("Incorrect place", this.place, is(place));
        return this;
    }

    public UserSteps goTo(Place place) {
        this.place = place;
        return this;
    }
}
