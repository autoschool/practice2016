package ru.qatools.school;

import org.junit.Test;
import ru.qatools.school.data.Place;

import static ru.qatools.school.steps.UserSteps.user;

/**
 * @author lanwen (Merkushev Kirill)
 */

public class MyFirstTest {

    @Test
    public void verifyNullWhenGoToNull() {
        user().goTo(null)
                .shouldBeAtPlace(null);
    }

    @Test
    public void verifyHomeWhenGoToHome() {
        user().goTo(Place.HOME)
                .shouldBeAtPlace(Place.HOME);
    }

    @Test
    public void verifyYandexWhenGoToYandex() {
        user().goTo(Place.AT_YANDEX)
                .shouldBeAtPlace(Place.AT_YANDEX);
    }
}