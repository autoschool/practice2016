package ru.qatools.school;

import ru.qatools.school.data.Place;

import static ru.qatools.school.steps.UserSteps.user;

/**
 * @author lanwen (Merkushev Kirill)
 */
public class MyFirstTest {

    //public void test() {user().goTo(null);}

    public void shouldBeInYa() {
        user().goTo(Place.AT_YANDEX).verifyPlace(Place.AT_YANDEX);
    }

    public void shouldBeAtHome() {
        user().goTo(Place.HOME).verifyPlace(Place.HOME);
    }
}
