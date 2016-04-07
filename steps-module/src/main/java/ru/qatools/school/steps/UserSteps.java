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
        this.place = place;
        return this;
    }

    public UserSteps shouldBe(Place place) {
        this.place = place;
        if (this.place == "Yandex"){
            return True;
        }
        else{
            return False;
        }
        return this;
    }
}
