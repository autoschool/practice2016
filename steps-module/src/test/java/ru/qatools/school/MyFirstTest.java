package ru.qatools.school;

import ru.qatools.school.data.Place;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static ru.qatools.school.steps.UserSteps.user;

/**
 * @author lanwen (Merkushev Kirill)
 */
public class MyFirstTest {

    public void isPlaceYandex() {
        user().goTo(Place.AT_YANDEX).isPlaseExist(Place.AT_YANDEX);
    }

    public void isPlaceHome() {
        user().goTo(Place.HOME).isPlaseExist(Place.HOME);
    }

    public void isPlaceNull() {
        user().goTo(null).isPlaseExist(null);
    }

}
