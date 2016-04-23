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
 */
@RunWith(DataProviderRunner.class)
public class MyFirstTest {

    @DataProvider
    public static List<Object> getPlaces(){
        ArrayList<Object> places = new ArrayList<Object>(Arrays.asList(Place.values()));
        places.add(null);
        return places;
    }

    @Test
    @Ignore
    @UseDataProvider("getPlaces")
    public void userGoTo(Place place) {
        user().goTo(place)
              .shouldBeInPlace(place);
    }
    
}
