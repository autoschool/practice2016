package ru.qatools.school;

import com.jayway.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Test;
import ru.qatools.school.apiData.CityJSON;
import ru.qatools.school.apiData.URI;
import ru.yandex.qatools.allure.annotations.Title;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author totallynotkate (Kate Kocijevska)
 */
public class RestassuredTests {

    public static final int NEGATIVE_CITIES_LIMIT_VALUE = -1;
    public static final long MAX_INT_PLUS_ONE_LIMIT = (long) Integer.MAX_VALUE + 1;

    @Test
    @Title("В ответ на запрос отрицательного количества городов должны получать Bad request")
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
    @Title("В ответ на запрос большего чем Integer.MAX_VALUE количества городов должны получать Bad request")
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
    @Title("Должны получать запрошенное количество городов и статус OK")
    public void shouldGetNumberOfCitiesRequested(){
        int numberOfCitiesRequested = 2;
        Response response = given().baseUri(URI.BASE_URI.getValue())
                .basePath(URI.BASE_PATH.getValue())
                .param(URI.LIMIT_PARAMETER.getValue(), numberOfCitiesRequested)
                .get(URI.CITIES_RESOURCE.getValue());
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        CityJSON[] cities = response.as(CityJSON[].class);
        assertThat(cities.length, is(numberOfCitiesRequested));
    }
}
