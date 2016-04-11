package ru.qatools.school.steps;

import org.junit.Assert;
import ru.qatools.school.data.Place;

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

    public UserSteps shouldBeAt(Place place) {
        Assert.assertThat("Wrong place", this.place, is(place));
        return this;
    }

}
