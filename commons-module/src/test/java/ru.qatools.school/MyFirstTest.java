package ru.qatools.school;

import org.junit.Test;
import ru.qatools.school.data.Place;

import static ru.qatools.school.steps.UserSteps.user;

public class MyFirstTest {
    @Test
    public void testIncorrectPlace() {
        user().goTo(Place.HOME).shouldSeeCurrentPlace(Place.AT_YANDEX);
    }

    @Test
    public void testCorrectPlace() {
        user().goTo(Place.HOME).shouldSeeCurrentPlace(Place.HOME);
    }

    @Test
    public void testLongSequence() {
        user().goTo(Place.HOME).goTo(Place.HOME).goTo(Place.AT_YANDEX).
                goTo(Place.AT_YANDEX).shouldSeeCurrentPlace(Place.AT_YANDEX);
    }

}
