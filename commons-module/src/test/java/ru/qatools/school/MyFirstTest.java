package ru.qatools.school;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.qatools.school.data.Place;

import static ru.qatools.school.steps.UserSteps.user;

/**
 * @author lanwen (Merkushev Kirill)
 * @author totallynotkate
 */
@RunWith(DataProviderRunner.class)
public class MyFirstTest {

    @DataProvider
    public static java.lang.Object[][] places() {
        return new java.lang.Object[][]{
                {Place.AT_YANDEX},
                {Place.HOME},
        };
    }

    @Test
    @UseDataProvider("places")
    public void goToPlace(Place place) {
        user().goTo(place).shouldBeAtPlace(place);
    }

}
