package ru.qatools.school;

import org.apache.http.HttpStatus;
import org.junit.Test;
import ru.qatools.school.apiData.URI;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

/**
 * @author totallynotkate (Kate Kocijevska)
 */
public class RestassuredTests {

    public static final int NEGATIVE_CITIES_LIMIT_VALUE = -1;
    public static final long MAX_INT_PLUS_ONE_LIMIT = (long) Integer.MAX_VALUE + 1;

    @Test
    public void shouldGetErrorWhenRequestNegativeCitiesLimit(){
        given().baseUri(URI.BASE_URI.getValue())
                .basePath(URI.BASE_PATH.getValue())
                .param(URI.LIMIT_PARAMETER.getValue(), NEGATIVE_CITIES_LIMIT_VALUE)
                .get(URI.CITIES_RESOURCE.getValue())
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
    @Test
    public void shouldGetErrorWhenRequestMaxIntegerPlusOneCities(){
        given().baseUri(URI.BASE_URI.getValue())
                .basePath(URI.BASE_PATH.getValue())
                .param(URI.LIMIT_PARAMETER.getValue(), MAX_INT_PLUS_ONE_LIMIT)
                .get(URI.CITIES_RESOURCE.getValue())
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void shouldGetNumberOfCitiesRequested(){
        int numberOfCitiesRequested = 5;
        given().baseUri(URI.BASE_URI.getValue())
                .basePath(URI.BASE_PATH.getValue())
                .param(URI.LIMIT_PARAMETER.getValue(), numberOfCitiesRequested)
                .get(URI.CITIES_RESOURCE.getValue())
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body("toSet.size()", is(numberOfCitiesRequested));
    }
}
