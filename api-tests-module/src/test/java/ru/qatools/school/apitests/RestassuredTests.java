package ru.qatools.school.apitests;

import com.jayway.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.ClassRule;
import org.junit.Test;
import ru.qatools.school.api.weather.WeatherResponse;
import ru.qatools.school.rules.RestAssuredWeatherRule;
import ru.yandex.qatools.allure.annotations.Title;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author gladnik (Nikolai Gladkov)
 */
public class RestAssuredTests {

    private static final String MOSCOW = "Moscow";
    private static final String ANOTHER_CITY_FROM_MOSCOW_TIMEZONE = "Saint Petersburg";
    private static final String DEFAULT_REGION = "ru";

    @ClassRule
    public static RestAssuredWeatherRule restAssuredRule = new RestAssuredWeatherRule();

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

    private Response getResponseFromWeatherHandle(String city, String region) {
        return given()
                .log().all()
                .param("city", city)
                .param("region", region)
                .get("weather");
    }
}
