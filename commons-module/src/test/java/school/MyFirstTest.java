package school;

import org.junit.Test;
import ru.qatools.school.data.Place;
import static ru.qatools.school.steps.UserSteps.user;

/**
 * @author lanwen (Merkushev Kirill)
 */
public class MyFirstTest {

    @Test
    public void beInYndx() {
        user().goTo(Place.AT_YANDEX).expectSameLocation(Place.AT_YANDEX);
    }

    @Test
    public void beAtNull() {
        user().goTo(null).expectSameLocation(null);
    }
    
    @Test
    public void beAtHome() {
        user().goTo(Place.HOME).expectSameLocation(Place.HOME);
    }
}
