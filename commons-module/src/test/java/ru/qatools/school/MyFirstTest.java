package ru.qatools.school;

import org.junit.Test;
import ru.qatools.school.data.Place;

import static ru.qatools.school.steps.UserSteps.user;

/**
 * @author lanwen (Merkushev Kirill)
 */
public class MyFirstTest {

    @Test
    public void shouldBeAtYandexWhenGoToYandex() {
        user().goTo(Place.AT_YANDEX).checkPlace(Place.AT_YANDEX);
    }

    @Test
    public void shouldBeAtHomeWhenGoHome() {
        user().goTo(Place.HOME).checkPlace(Place.THE_MIDDLE_OF_NOWHERE);
    }

    @Test
    public void shouldBeInTheMiddleOfNowhereWhenGoToTheMIddleOfNowhere() {
        user().goTo(Place.THE_MIDDLE_OF_NOWHERE).checkPlace(Place.THE_MIDDLE_OF_NOWHERE);
    }
}
