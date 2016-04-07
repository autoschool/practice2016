package ru.qatools.school;

import org.junit.Test;
import static ru.qatools.school.data.Place.*;
import static ru.qatools.school.steps.UserSteps.*;

/**
 * @author kormyshov (Kormyshov Mikhail)
 */
public class MyFirstTest {

    @Test
    public void testPlaceYandex() {
        user().goTo(AT_YANDEX).shouldBePlace(AT_YANDEX);
    }

    @Test
    public void testPlaceHome() {
        user().goTo(HOME).shouldBePlace(HOME);
    }

}
