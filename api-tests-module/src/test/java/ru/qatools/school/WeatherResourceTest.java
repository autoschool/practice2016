package ru.qatools.school;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.apache.http.HttpStatus;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.qatools.school.apiData.URI;
import ru.qatools.school.tp.TPInformerRule;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.annotations.Title;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

/**
 * @author totallynotkate (Kate Kocijevska)
 */
@RunWith(DataProviderRunner.class)
public class WeatherResourceTest {

    @Rule
    public TPInformerRule testpalm = new TPInformerRule("totallynotkate");

    @Test
    @Title("При запросе города кириллицей или латиницей должны получать погоду для него")
    @TestCaseId("")
    @UseDataProvider("cityOnly")
    public void shouldGetWeatherGivenCityOnly(String requestedCity, String returnedCity) {
        given()
                .baseUri(URI.BASE_URI.getValue())
                .basePath(URI.BASE_PATH.getValue())
                .param(URI.CITY_PARAM.getValue(), requestedCity)
                .get(URI.WEATHER_RESOURCE.getValue())
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body("city", is(returnedCity));
    }

    @Test
    @Title("При запросе города кириллицей или латиницей и региона должны получать погоду для города")
    @TestCaseId("")
    @UseDataProvider("cityAndRegion")
    public void shouldGetWeatherGivenCityAndRegion(String requestedCity, String region, String returnedCity) {
        given()
                .baseUri(URI.BASE_URI.getValue())
                .basePath(URI.BASE_PATH.getValue())
                .param(URI.CITY_PARAM.getValue(), requestedCity)
                .param(URI.REGION_PARAM.getValue(), region)
                .get(URI.WEATHER_RESOURCE.getValue())
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body("city", is(returnedCity));
    }

    @DataProvider
    public static Object[][] cityOnly(){
        return new Object[][] {
                {"Moscow", "Moscow"},
                {"Москва", "Moscow"}
        };
    }

    @DataProvider
    public static Object[][] cityAndRegion(){
        return new Object[][] {
                {"Moscow", "ru", "Moscow"},
                {"Москва", "ru", "Moscow"}
        };
    }
}