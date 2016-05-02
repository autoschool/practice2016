package ru.qatools.school;

import org.apache.http.HttpStatus;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.tp.TPInformerRule;
import ru.yandex.qatools.allure.annotations.TestCaseId;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


/**
 * Created by onodee on 30.04.2016.
 */
public class TestsByRestAssured {

    @Rule
    public TPInformerRule tms = new TPInformerRule("onodee87");

    @Test
    @TestCaseId("20")
    public void shouldSeeCityByRequest() throws Exception {
        given().baseUri("http://weather.lanwen.ru")
                .basePath("api").param("city", "Moscow")
                .get("weather")
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .and().body("city", equalTo("Moscow"));
    }

    @Test
    @TestCaseId("23")
    public void shouldSeeCitiesByRequest() throws Exception {
        given().baseUri("http://weather.lanwen.ru")
                .basePath("api").param("query", "Mo")
                .get("suggest")
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .and().body("name", everyItem(startsWith("Mo")));
    }
}
