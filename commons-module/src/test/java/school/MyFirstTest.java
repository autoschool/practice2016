package school;

import org.junit.Test;
import ru.qatools.school.data.Place;
import static ru.qatools.school.steps.UserSteps.user;

/**
 * @author lanwen (Merkushev Kirill)
 */
public class MyFirstTest {

    @Test
    public void shouldBeInYndxAsGoneToYndx() {
        user().goTo(Place.AT_YANDEX).expectSameLocation(Place.AT_YANDEX);
    }

    @Test
    public void shouldBeAtNullAsGoneToNull() {
        user().goTo(null).expectSameLocation(null);
    }
    
    @Test
    public void shouldBeAtHomeAsGoToHome() {
        user().goTo(Place.HOME).expectSameLocation(Place.HOME);
    }
}
