package ru.qatools.school.apitests;

import org.junit.Test;
import ru.qatools.school.Responces.CitiesResponse;
import ru.yandex.qatools.allure.annotations.Title;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static ru.qatools.school.data.Constants.*;

/**
 * @author ava1on
 */
public class RestAssuredAPITests {

    @Test
    @Title("Должны получить заданное в параметре количество городов в ответе")
    public void shouldReturnRequestedNumberOfCities(){
        CitiesResponse[] citiesArray =
                given().
                        baseUri(BASE_URL).
                        param(LIMIT_PARAMETER, LIMIT_VALUE).
                when().
                        get(CITIES_REQUEST).as(CitiesResponse[].class);
        assertThat("Неверное количество городов в ответе", citiesArray.length, is(LIMIT_VALUE));
    }
}
