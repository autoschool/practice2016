package ru.qatools.school.steps;

import ru.qatools.school.data.Place;

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
        return null;
    }

    public void shouldBeAtPlace(Object o) {
    }
}
