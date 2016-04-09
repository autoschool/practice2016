package ru.qatools.school;

import org.junit.Test;
import ru.qatools.school.data.Place;

import static ru.qatools.school.steps.UserSteps.user;

/**
 * @author lanwen (Merkushev Kirill)
 */
public class MyFirstTest {
    
    @Test
    public void shouldBeHomeWhenGoToHome() {
        user().goTo(Place.HOME).shouldBeInRightPlace(Place.HOME);
    }

    @Test
    public void shouldBeAtYandexWhenGoToYandex(){
        user().goTo(Place.AT_YANDEX).shouldBeInRightPlace(Place.AT_YANDEX);
    }
}
