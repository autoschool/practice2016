package ru.qatools.school;

import ru.qatools.school.data.Place;
import static ru.qatools.school.steps.UserSteps.user;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(DataProviderRunner.class)
public class MyFirstTest{
    @DataProvider
    public static List<Object> expectedPlaces() {
        List<Object> expectedPlaces = new ArrayList<Object>(Arrays.asList(Place.values()));
        expectedPlaces.add(null);
        return expectedPlaces;
    }

    @Test
    @UseDataProvider("expectedPlaces")
    public void checkLocationChanging(Place place) {
        user().goTo(place).checkUserLocation(place);
    }
}