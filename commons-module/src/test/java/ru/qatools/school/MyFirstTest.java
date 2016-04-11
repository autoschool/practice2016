package ru.qatools.school;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.qatools.school.data.Place;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.qatools.school.steps.UserSteps.user;

/**
 * @author lanwen (Merkushev Kirill)
 * @author onegines (Kirienko Evgeni)
 */

@RunWith(DataProviderRunner.class)
public class MyFirstTest {

    @DataProvider
    public static List<Object> places() {
        ArrayList<Object> places = new ArrayList<Object>(Arrays.asList(Place.values()));
        return places;
    }

    @Test
    @UseDataProvider("places")
    public void shouldBeAtPlace(Place place) {
        user().goTo(place)
                .shouldBeAt(place);
    }

    @Test
    public void shouldBeAtNull() {
        user().goTo(null)
                .shouldBeAt(null);
    }

    /*
        Always FAIL test. Should be @Ignore in real.
     */
    @Ignore
    @Test
    public void shouldBeAtYandexAfterGoToHome() {
        user().goTo(Place.HOME)
                .shouldBeAt(Place.AT_YANDEX);
    }
}
