package ru.qatools.school;

import org.apache.http.HttpStatus;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

/**
 * @author totallynotkate (Kate Kocijevska)
 */
public class RestassuredTests {

    public static final String BASE_URI = "http://weather.lanwen.ru";
    public static final String BASE_PATH = "api";
    public static final String LIMIT_PARAMETER = "limit";
    public static final String CITIES_RESOURCE = "cities";
    public static final int NEGATIVE_CITIES_LIMIT_VALUE = -1;
    public static final long MAX_INT_PLUS_ONE_LIMIT = (long) Integer.MAX_VALUE + 1;
    public static final int CITIES_LIST_SIZE = 272;

    @Test
    public void shouldGetErrorWhenRequestNegativeCitiesLimit(){
        given().baseUri(BASE_URI)
                .basePath(BASE_PATH)
                .param(LIMIT_PARAMETER, NEGATIVE_CITIES_LIMIT_VALUE)
                .get(CITIES_RESOURCE)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
    @Test
    public void shouldGetErrorWhenRequestMaxIntegerPlusOneCities(){
        given().baseUri(BASE_URI)
                .basePath(BASE_PATH)
                .param(LIMIT_PARAMETER, MAX_INT_PLUS_ONE_LIMIT)
                .get(CITIES_RESOURCE)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
