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
 * @author lanwen (Merkushev Kirill).
 * @author bahek091 (Gavrilov Ivan)
 */
@RunWith(DataProviderRunner.class)
public class MyFirstTest {

    @DataProvider
    public static List<Object> listOfPlaces() {
        List<Object> placesList
                = new ArrayList<Object>(Arrays.asList(Place.values()));
        placesList.add(null);
        return placesList;
    }

    @Test
    @UseDataProvider("listOfPlaces")
    public void goToPlaceAndShoudBeHere(Place curPlace){
        user().goTo(curPlace)
                .shoudBeHere(curPlace);

    }


}
