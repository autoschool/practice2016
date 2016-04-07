package ru.qatools.school;

import static ru.qatools.school.steps.UserSteps.user;
import ru.qatools.school.data.Place;
import org.junit.Test;

/**
 * @author lanwen (Merkushev Kirill)
 */
public class MyFirstTest {
    
	@Test
    public void test() {
        user().goTo(Place.AT_YANDEX).
				shouldBeAt(Place.AT_YANDEX).
				goTo(Place.HOME).
				shouldBeAt(Place.HOME);
    }
    
}
