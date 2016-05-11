package ru.qatools.school.apitests;

import com.jayway.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import ru.qatools.school.DbClient;
import ru.qatools.school.DbClientRule;
import ru.qatools.school.api.suggest.SuggestCity;
import ru.qatools.school.api.weather.WeatherResponse;
import ru.qatools.school.rules.RestAssuredWeatherRule;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.Arrays;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static ru.qatools.school.WebsiteData.*;

/**
 * @author gladnik (Nikolai Gladkov)
 */
public class RestAssuredTests {

    private static final String MOSCOW = "Moscow";
    private static final String ANOTHER_CITY_FROM_MOSCOW_TIMEZONE = "Saint Petersburg";
    private static final String DEFAULT_REGION = "ru";
    private static final String CITY_NAME_PART = "bur";

    private static DbClient dbClient;

    @ClassRule
    public static RestAssuredWeatherRule restAssuredRule = new RestAssuredWeatherRule();
    @ClassRule
    public static DbClientRule dbClientRule = new DbClientRule();

    @BeforeClass
    public static void initDbClient() {
        dbClient = dbClientRule.getDbClient();
    }

    @Test
    @Title("Должны получать SC_OK при правильном запросе к /api/weather")
    public void shouldGetOkCodeForValidWeatherHandleRequest() {
        getResponseFromWeatherHandle(MOSCOW, DEFAULT_REGION)
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @Title("Должно совпадать время в городах одного часового пояса")
    public void shouldGetSameTimeForCitiesInSameTimeZone() {
        Response moscowResponse = getResponseFromWeatherHandle(MOSCOW, DEFAULT_REGION);
        long moscowTime = moscowResponse.as(WeatherResponse.class).getDt();

        Response anotherCityResponse = getResponseFromWeatherHandle(ANOTHER_CITY_FROM_MOSCOW_TIMEZONE, DEFAULT_REGION);
        long timeInAnotherCityFromMoscowTimeZone = anotherCityResponse.as(WeatherResponse.class).getDt();

        assertThat("Время не должно отличаться", timeInAnotherCityFromMoscowTimeZone, equalTo(moscowTime));
    }

    @Test
    @Title("Должны совпадать значения саджеста полученные через API и через базу данных")
    public void shouldGetSameSuggestResponseForApiAndDB() {
        Response apiResponse =
                given()
                        .param(QUERY_PARAMETER, CITY_NAME_PART)
                        .get(SUGGEST_HANDLE);

        List<SuggestCity> apiCities = Arrays.asList(apiResponse.as(SuggestCity[].class));
        List<SuggestCity> dbCities = dbClient.getSuggestCitiesForQuery(CITY_NAME_PART);

        assertThat("Ответ API не совпадает с ответом БД", apiCities, equalTo(dbCities));
    }

    private Response getResponseFromWeatherHandle(String city, String region) {
        return given()
                .param(CITY_PARAMETER, city)
                .param(REGION_PARAMETER, region)
                .get(WEATHER_HANDLE);
    }
}
