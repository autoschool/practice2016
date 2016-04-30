package ru.qatools.school.apitests;

import org.apache.http.HttpStatus;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Title;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.everyItem;

/**
 * @author onegines (Eugene Kirienko)
 */
public class RestAssuredTests {

    private static final String MAIN_PAGE = "http://weather.lanwen.ru/";
    private static final String BASE_PATH = "api";

    @Test
    @Title("SUGGEST: Должны получить код 400 после отправки запроса без параметров")
    //@TestCaseId("53")
    public void shouldGetErrorCode400AfterSendNoQueryToSuggest() throws Exception {
        given().baseUri(MAIN_PAGE)
                .basePath(BASE_PATH)
                .get("suggest")
                .then().assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Title("SUGGEST: Должны получить список городов, содержащих в названии строку из запроса")
    //@TestCaseId("42")
    public void ShouldGetSuggestsContainsPartOfName() throws Exception {
        given().baseUri(MAIN_PAGE)
                .basePath(BASE_PATH).param("query", "Saint")
                .get("suggest")
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and().body("name", everyItem(containsString("Saint")));
    }

    @Test
    @Title("WEATHER: Должны получить код 400 после отправки запроса без параметров")
    //@TestCaseId("65")
    public void shouldGetErrorCode400AfterSendNoQueryToWeather() throws Exception {
        given().baseUri(MAIN_PAGE)
                .basePath(BASE_PATH)
                .get("weather")
                .then().assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

}
