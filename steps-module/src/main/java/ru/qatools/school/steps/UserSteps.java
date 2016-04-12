package ru.qatools.school.steps;

import org.junit.Assert;
import ru.qatools.school.data.Place;

import static org.hamcrest.CoreMatchers.is;

/**
 * @author lanwen (Merkushev Kirill)
 * @author totallynotkate (Kate Kocijevska)
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

    public UserSteps shouldBeAtPlace(Place place){
        Assert.assertThat(String.format("User at wrong place: expected %1$s but found %2$s", place, this.place),
                this.place, is(place));
        return this;
    }
}
