package ru.qatools.school;

import org.junit.Test;

import static ru.qatools.school.data.Place.*;
import static ru.qatools.school.steps.UserSteps.*;


/**
 * @author gladnik (Nikolai Gladkov)
 */
public class MyFirstTest {

    @Test
    public void shouldBeAtHomeWhenGoToHome() {
        user().goTo(HOME).shouldBeAtPlace(HOME);
    }

    @Test
    public void shouldBeAtYandexWhenGoToYandex() {
        user().goTo(AT_YANDEX).shouldBeAtPlace(AT_YANDEX);
    }
}
