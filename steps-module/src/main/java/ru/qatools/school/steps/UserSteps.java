package ru.qatools.school.steps;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import ru.qatools.school.data.Place;
import static org.junit.Assert.assertThat;

/**
 * @author lanwen (Merkushev Kirill)
 */
public class UserSteps {
    class PlaceMatcher extends BaseMatcher<Place>{
        public boolean matches(Object o) {
            return o != null;
        }

        public void describeTo(Description description) {
            description.appendText("Place should not be null");
        }
    }
    
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



    public UserSteps walkTo(String street) {
        assertThat(street, new BaseMatcher<String>() {
            public void describeTo(Description description) {
            }
            public boolean matches(Object o){
                if (o == null) return false;
                if (o.equals("")) return false;
                return true;
            }
        });
        return this;
    }
    public UserSteps ordersCoffee() {
        return this;
    }
    public UserSteps pays() {
        return this;
    }
    public UserSteps shouldSeeCup() {
        return this;
    }


}
