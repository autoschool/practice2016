package ru.qatools.school;

import org.junit.Test;
import org.junit.runner.RunWith;
import ru.qatools.school.data.Place;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import static ru.qatools.school.steps.UserSteps.user;

@RunWith(DataProviderRunner.class)
public class myFirstSecondTest {

    @DataProvider
    public static Object[][] places() {
        return new Object[][]{
                {Place.HOME},
                {Place.AT_YANDEX}
        };
    }

    @Test
    @UseDataProvider("places")
    public void forEveryPlaceExpectPlaceWhenGoToPlace(Place place) {
        user().goTo(place)
                .verifyLocation(place);

    }


}

