package ru.qatools.school;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.qatools.school.data.Place;
import ru.yandex.qatools.allure.annotations.Features;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.qatools.school.steps.UserSteps.user;


/**
 * @author gladnik (Nikolai Gladkov)
 */
@RunWith(DataProviderRunner.class)
public class MyFirstTest {

    @DataProvider
    public static List<Object> places() {
        List<Object> placesList
                = new ArrayList<Object>(Arrays.asList(Place.values()));
        placesList.add(null);
        return placesList;
    }

    @Features("Интеграционные тесты")
    @Test
    public void shouldBeAtNullWhenDefault() {
        user().shouldBeAtPlace(null);
    }

    @Features("Интеграционные тесты")
    @Test
    @UseDataProvider("places")
    public void afterGoSomewhereShouldBeThere(Place place) {
        user().goTo(place).shouldBeAtPlace(place);
    }

    @Features("Интеграционные тесты")
    @Test
    @UseDataProvider("places")
    public void afterGoSomewhereTwiceShouldBeThere(Place place) {
        user().goTo(place).goTo(place).shouldBeAtPlace(place);
    }

    @Features("Интеграционные тесты")
    @Test
    public void afterGoSomewhereElseShouldBeThere() {
        user().goTo(Place.HOME).goTo(Place.AT_YANDEX)
                .shouldBeAtPlace(Place.AT_YANDEX);
    }
}
