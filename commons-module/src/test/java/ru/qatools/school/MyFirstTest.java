package ru.qatools.school;

import org.junit.Test;
import ru.qatools.school.data.Place;

import static ru.qatools.school.steps.UserSteps.user;

/**
 * @author lanwen (Merkushev Kirill)
 */
public class MyFirstTest {


    @Test
    public void test() {
        user().goTo(Place.AT_YANDEX).shouldSeeCurrentPlace();
        user().goTo(Place.HOME).shouldSeeCurrentPlace();
    }
    
}
