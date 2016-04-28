package ru.qatools.school.webapi.restassured;

import org.apache.http.HttpStatus;
import org.junit.Test;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class WeatherWebApiRest {

    private static final String MAIN_PAGE = "http://weather.lanwen.ru";
    private static final String BASE_PATH = "api";
    private static final String WEATHER = "weather";
    private static final String CITIES = "cities";
    private static final String INIT = "init";
    private static final int COUNT_CITIES = 272;
    private static final String CITY = "Moscow";
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int NEGATIVE_NUMBER = -3;
    private static final String SPEC_SYMBOLS = "%20";


    @Test
    public void shouldSeeCitiesCityRecord() {
        given().baseUri(MAIN_PAGE).basePath(BASE_PATH).param("city", "Moscow")
                .get(WEATHER)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .and().body("city", equalTo("Moscow"))
                .and().body("temperatures", hasSize(4));

    }

    @Test
    public void shouldSeeInitCitiesRecords() {
        given().baseUri(MAIN_PAGE).basePath(BASE_PATH)
                .get(CITIES)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .and().body("size()", is(COUNT_CITIES));
    }

    @Test
    public void shouldSeeCitiesListCities() {
        given().baseUri(MAIN_PAGE).basePath(BASE_PATH)
                .get(CITIES)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .and().body("size()", is(COUNT_CITIES));
    }

    @Test
    public void shouldSeeCitiesLimitOneCity() {
        given().baseUri(MAIN_PAGE).basePath(BASE_PATH).param("limit", ONE)
                .get(CITIES)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .and().body("size()", is(ONE));
    }



}


