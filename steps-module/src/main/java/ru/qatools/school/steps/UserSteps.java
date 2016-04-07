package ru.qatools.school.steps;

import org.junit.Assert;
import ru.qatools.school.data.Place;

import static org.hamcrest.CoreMatchers.is;

/**
 * @author lanwen (Merkushev Kirill)
 * @author totallynotkate
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

    public void isUserAtPlace(Place place){
        Assert.assertThat(this.place, is(place));
    }
}
