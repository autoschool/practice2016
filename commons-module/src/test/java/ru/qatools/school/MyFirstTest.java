package ru.qatools.school;

import org.junit.Test;
import ru.qatools.school.data.Place;

import static ru.qatools.school.steps.UserSteps.user;

/**
 * @author lanwen (Merkushev Kirill)
 */
public class MyFirstTest {

    @Test
    public void verifyCurrentPlace() {
        user().goTo(Place.AT_YANDEX).checkPlace(Place.AT_YANDEX);
        user().goTo(Place.HOME).checkPlace(Place.HOME);
    }
    
}
