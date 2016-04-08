package ru.qatools.school;

import org.junit.Test;
import ru.qatools.school.data.Place;

import static ru.qatools.school.steps.UserSteps.user;

/**
 * @author lanwen (Merkushev Kirill)
 * changed by aasx (Kulikov Yurii)
 */
public class MyFirstTest {
    @Test
   public void expectNullWhenGoToNull() {
        user().goTo(null)
                .verifyLocation(null);
    }
    @Test
    public void expectYandexWhenGoToYandex() {
        user().goTo(Place.AT_YANDEX)
                .verifyLocation(Place.AT_YANDEX);
    }
    @Test
    public void expectHomeWhenGoToHome(){
        user().goTo(Place.HOME)
                .verifyLocation(Place.HOME);
    }



}
