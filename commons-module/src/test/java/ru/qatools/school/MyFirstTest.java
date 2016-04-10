package ru.qatools.school;

import org.junit.Test;
import ru.qatools.school.data.Place;

import static ru.qatools.school.steps.UserSteps.user;

/**
 * @author lanwen (Merkushev Kirill)
 */
public class MyFirstTest {
    @Test
    public void goToYandexAndFindThemselvesAtYandex() {
        user().goTo(Place.AT_YANDEX).shouldAppearIn(Place.AT_YANDEX);
    }

    @Test
    public void goHomeAndFindThemselvesAtHome() {
        user().goTo(Place.HOME).shouldAppearIn(Place.HOME);
    }

}