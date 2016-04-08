package ru.qatools.school;

import org.junit.Test;
import ru.qatools.school.data.Place;

import static ru.qatools.school.steps.UserSteps.user;

/**
 * @author lanwen (Merkushev Kirill)
 */
public class MyFirstTest {


    @Test
    public void shouldBeAtYandexAfterGoToYandex() {
        user().goTo(Place.AT_YANDEX).shouldSeeCurrentPlace(Place.AT_YANDEX);
    }

    @Test
    public void shouldBeAtHomeAfterGoToHome() {
        user().goTo(Place.HOME).shouldSeeCurrentPlace(Place.HOME);
    }

    @Test
    public void shouldBeNullIfPlaceNull() {
        user().goTo(null).shouldSeeCurrentPlace(null);
    }
}
