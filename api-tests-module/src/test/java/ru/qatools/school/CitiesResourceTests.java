package ru.qatools.school;

import com.jayway.restassured.response.Response;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import org.apache.http.HttpStatus;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.qatools.school.apiData.CitiesJSON;
import ru.qatools.school.apiData.URI;
import ru.qatools.school.tp.TPInformerRule;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.annotations.Title;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author totallynotkate (Kate Kocijevska)
 */
@RunWith(DataProviderRunner.class)
public class CitiesResourceTests {

    @Rule
    public TPInformerRule testpalm = new TPInformerRule("totallynotkate");

    public static final int NEGATIVE_CITIES_LIMIT_VALUE = -1;
    public static final long MAX_INT_PLUS_ONE_LIMIT = (long) Integer.MAX_VALUE + 1;

    @Test
    @Title("В ответ на запрос отрицательного количества городов должны получать Bad request")
    public void shouldGetErrorWhenRequestNegativeCitiesLimit(){
        given().baseUri(URI.BASE_URI.getValue())
                .basePath(URI.BASE_PATH.getValue())
                .param(URI.LIMIT_PARAM.getValue(), NEGATIVE_CITIES_LIMIT_VALUE)
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
                .param(URI.LIMIT_PARAM.getValue(), MAX_INT_PLUS_ONE_LIMIT)
                .get(URI.CITIES_RESOURCE.getValue())
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Title("Должны получать запрошенное количество городов и статус OK")
    @TestCaseId("")
    @DataProvider({"0", "1", "2"})
    public void shouldGetNumberOfCities(int requestedNumber){
        Response response = given().baseUri(URI.BASE_URI.getValue())
                .basePath(URI.BASE_PATH.getValue())
                .param(URI.LIMIT_PARAM.getValue(), requestedNumber)
                .get(URI.CITIES_RESOURCE.getValue());
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        CitiesJSON[] cities = response.as(CitiesJSON[].class);
        assertThat("Должны получать запрошенное количество городов и статус OK", cities.length, is(requestedNumber));
    }
}
