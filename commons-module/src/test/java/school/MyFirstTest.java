package school;

import ru.qatools.school.data.Place;
import static ru.qatools.school.steps.UserSteps.user;

/**
 * @author lanwen (Merkushev Kirill)
 */
public class MyFirstTest {
    
    public void test() {
        user().checkLocation(null).goTo(Place.AT_YANDEX).checkLocation(Place.AT_YANDEX);
    }
    
}
