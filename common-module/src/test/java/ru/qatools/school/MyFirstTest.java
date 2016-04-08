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

@RunWith(DataProviderRunner.class)
public class MyFirstTest {

    @DataProvider
    public static List<Object> places() {
        return new ArrayList<Object>(Arrays.asList(Place.values()));
    }

    @Test
    @UseDataProvider("places")
    public void forEveryPlaceExpectPlaceWhenGoToPlace(Place place) {
        user().goTo(place)
                .verifyLocation(place);

    }

}
