package ru.qatools.school;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.qatools.school.data.Place;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static ru.qatools.school.steps.UserSteps.user;


/**
 * @author gladnik (Nikolai Gladkov)
 */
@RunWith(DataProviderRunner.class)
public class MyFirstTest {

    @DataProvider
    public static List<Object> places() {
        List<Object> placesList
                = newArrayList(Place.values());
        placesList.add(null);
        return placesList;
    }

    @Test
    public void shouldBeAtNullWhenDefault() {
        user().shouldBeAtPlace(null);
    }

    @Test
    @UseDataProvider("places")
    public void afterGoSomewhereShouldBeThere(Place place) {
        user().goTo(place).shouldBeAtPlace(place);
    }

    @Test
    @UseDataProvider("places")
    public void afterGoSomewhereTwiceShouldBeThere(Place place) {
        user().goTo(place).goTo(place).shouldBeAtPlace(place);
    }

    @Test
    public void afterGoSomewhereElseShouldBeThere() {
        user().goTo(Place.HOME).goTo(Place.AT_YANDEX)
                .shouldBeAtPlace(Place.AT_YANDEX);
    }
}
