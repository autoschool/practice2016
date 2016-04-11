package ru.qatools.school;

import org.junit.Ignore;
import org.junit.Test;
import ru.qatools.school.data.Place;

import static ru.qatools.school.steps.UserSteps.user;

/**
 * @author lanwen (Merkushev Kirill)
 * @author onegines
 */
public class MyFirstTest {

    @Test
    public void shouldBeAtYandex() {
        user().goTo(Place.AT_YANDEX)
                .shouldBeAt(Place.AT_YANDEX);
    }

    @Test
    public void shouldBeAtHome() {
        user().goTo(Place.HOME)
                .shouldBeAt(Place.HOME);
    }

    @Test
    public void shouldBeAtNull() {
        user().goTo(null)
                .shouldBeAt(null);
    }

    /*
        Always FAIL test. Should be @Ignore in real.
     */
    @Ignore
    @Test
    public void shouldBeAtYandexAfterGoToHome() {
        user().goTo(Place.HOME)
                .shouldBeAt(Place.AT_YANDEX);
    }

}
