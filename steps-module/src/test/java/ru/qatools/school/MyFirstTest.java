package ru.qatools.school;

import org.junit.Test;
import ru.qatools.school.data.Place;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static ru.qatools.school.steps.UserSteps.user;

/**
 * @author lanwen (Merkushev Kirill)
 */
public class MyFirstTest {

    @Test
    public void whenPlaceIsYandex() {
        user().goTo(Place.AT_YANDEX).ensureThatPlaseIsEqualToExpectedValue(Place.AT_YANDEX);
    }

    @Test
    public void whenPlaceIsHome() {
        user().goTo(Place.HOME).ensureThatPlaseIsEqualToExpectedValue(Place.HOME);
    }

    @Test
    public void whenPlaceIsNull() {
        user().goTo(null).ensureThatPlaseIsEqualToExpectedValue(null);
    }

}
