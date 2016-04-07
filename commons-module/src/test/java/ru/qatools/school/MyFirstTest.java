package ru.qatools.school;

import static ru.qatools.school.steps.UserSteps.user;

/**
 * @author lanwen (Merkushev Kirill)
 */
public class MyFirstTest {
    
    @Test
    public void shouldBeAtYandex() {
        user().goTo(Place.AT_YANDEX)
                .shouldBeAt(Place.AT_YANDEX);
    }

    @Test
    public void shouldBeAtHome() {
        user().goTo(Place.HOME)
                .shouldBeAt(Place.HOME);
    }
    
}
