package ru.qatools.school;

import org.junit.Test;
import ru.qatools.school.data.Place;

import static ru.qatools.school.steps.UserSteps.user;

/**
 * @author lanwen (Merkushev Kirill)
 */

public class MyFirstTest {

    //Отправляем user в место "null" и проверяем, оказался ли он в "null".
    //Send the user to the place "null" and verify is he in the "null".
    @Test
    public void verifyNullWhenGoToNull() {
        user().goTo(null)
                .shouldBeAtPlace(null);
    }

    //Отправляем user в место "HOME" и проверяем, оказался ли он в "HOME".
    //Send the user to the place "HOME" and verify is he in the "HOME".
    @Test
    public void verifyHomeWhenGoToHome() {
        user().goTo(Place.HOME)
                .shouldBeAtPlace(Place.HOME);
    }

    //Отправляем user в место "AT_YANDEX" и проверяем, оказался ли он в "AT_YANDEX".
    //Send the user to the place "AT_YANDEX" and verify is he in the "AT_YANDEX".
    @Test
    public void verifyYandexWhenGoToYandex() {
        user().goTo(Place.AT_YANDEX)
                .shouldBeAtPlace(Place.AT_YANDEX);
    }
}