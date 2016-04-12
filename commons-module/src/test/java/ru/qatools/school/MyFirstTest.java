package ru.qatools.school;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.qatools.school.data.Place;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.qatools.school.steps.UserSteps.user;

/**
 * @author lanwen (Merkushev Kirill)
 */

@RunWith(DataProviderRunner.class)
public class MyFirstTest {

    @DataProvider
    public static List<Object> places() {
        ArrayList<Object> places = new ArrayList<Object>(Arrays.asList(Place.values()));
        places.add(null);
        return places;
    }

    @Test
    @UseDataProvider("places")
    public void verifyPlace(Place place) {
        user().goTo(place)
                .shouldBeAtPlace(place);
    }
}

/*public class MyFirstTest {

    @Test
    public void verifyNullWhenGoToNull() {
        user().goTo(null)
                .shouldBeAtPlace(null);
    }

    @Test
    public void verifyHomeWhenGoToHome() {
        user().goTo(Place.HOME)
                .shouldBeAtPlace(Place.HOME);
    }

    @Test
    public void verifyYandexWhenGoToYandex() {
        user().goTo(Place.AT_YANDEX)
                .shouldBeAtPlace(Place.AT_YANDEX);
    }
}*/