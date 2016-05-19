package ru.qatools.school.apitests;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.DbClient;
import ru.qatools.school.apidata.SuggestResp;
import ru.qatools.school.apidata.WeatherResp;
import ru.qatools.school.tp.TPInformerRule;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.matchers.collection.HasSameItemsAsListMatcher.hasSameItemsAsList;

/**
 * @author onegines (Eugene Kirienko)
 */
public class RestAssuredTest {

    private static final String MAIN_PAGE = "http://weather.lanwen.ru/";
    private static final String BASE_PATH = "api";

    private static final String CITYNAME = "Chita";
    private static final String CITYNAMEBEGIN = "Saint";
    private static final String CITYNAMEPART = "ain";

    private RequestSpecification reqSpec;

    private DbClient dbClient;

    @Rule
    public TPInformerRule tms = new TPInformerRule("onegines");

    @Before
    public void init() {
        dbClient = new DbClient();

        reqSpec = new RequestSpecBuilder().build()
                .baseUri(MAIN_PAGE)
                .basePath(BASE_PATH);
    }

    @After
    public void close() {
        dbClient.close();
    }

    @Test
    @Features("Suggest")
    @Title("Должны получить код 400 после отправки запроса саджеста без параметров")
    @TestCaseId("53")
    public void shouldGetErrorCode400AfterSendNoQueryToSuggest() throws Exception {
        given().spec(reqSpec)
                .get("suggest")
                .then().assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Features("Suggest")
    @Title("Должны получить список городов, содержащих в названии строку из запроса")
    @TestCaseId("42")
    public void shouldGetSuggestsContainsPartOfName() throws Exception {
        given().spec(reqSpec)
                .param("query", CITYNAMEBEGIN)
                .get("suggest")
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and().body("name", everyItem(containsString(CITYNAMEBEGIN)));
    }

    @Test
    @Features("Suggest")
    @Title("Должны совпадать списки (от API и DB) саджестов, содержащих в названии города строку из запроса")
    @TestCaseId("75")
    public void shouldMatchSuggestsFromApiAndDb() throws Exception {

        List<SuggestResp> respApi = asList(
                given().spec(reqSpec)
                        .param("query", CITYNAMEPART)
                        .expect().statusCode(HttpStatus.SC_OK)
                        .get("suggest")
                        .as(SuggestResp[].class)
        );

        List<SuggestResp> respDb = dbClient.getSuggestCitiesByNamePart(CITYNAMEPART);

        assertThat("Списки из API и DB должны совпадать", respApi, hasSameItemsAsList(respDb));

    }

    @Test
    @Features("Weather")
    @Title("Должны получить погоду в городе с названием, указанным в запросе")
    @TestCaseId("55")
    public void shouldGetWeatherWithCityName() throws Exception {

        WeatherResp resp =
                given().spec(reqSpec)
                        .param("city", CITYNAME)
                        .expect().statusCode(HttpStatus.SC_OK)
                        .get("weather")
                        .as(WeatherResp.class);

        assertThat("Неправильный город", resp.getCity(), is(CITYNAME));

    }

    @Test
    @Features("Weather")
    @Title("Должны получить погоду в городе с названием '0'")
    @TestCaseId("60")
    public void shouldGetWeatherWithCityName0() throws Exception {

        WeatherResp resp =
                given().spec(reqSpec)
                        .param("city", "0")
                        .expect().statusCode(HttpStatus.SC_OK)
                        .get("weather")
                        .as(WeatherResp.class);

        assertThat("Неправильный город", resp.getCity(), is("0"));

    }

    @Test
    @Features("Weather")
    @Title("Должны получить код 400 после отправки запроса погоды без параметров")
    @TestCaseId("65")
    public void shouldGetErrorCode400AfterSendNoQueryToWeather() throws Exception {
        given().spec(reqSpec)
                .get("weather")
                .then().assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

}
