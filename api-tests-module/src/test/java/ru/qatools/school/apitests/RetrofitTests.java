package ru.qatools.school.apitests;

import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import retrofit2.Response;
import ru.qatools.school.api.weather.WeatherInterface;
import ru.qatools.school.api.weather.WeatherResponse;
import ru.qatools.school.rules.RetrofitWeatherRule;
import ru.yandex.qatools.allure.annotations.Title;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;


/**
 * @author gladnik (Nikolai Gladkov)
 */
public class RetrofitTests {

    private static final String MOSCOW = "Moscow";
    private static final String ANOTHER_CITY_FROM_MOSCOW_TIMEZONE = "Saint Petersburg";

    private static WeatherInterface weatherInterface;

    @ClassRule
    public static RetrofitWeatherRule retrofitRule = new RetrofitWeatherRule();

    @BeforeClass
    public static void initInterface() {
        weatherInterface = retrofitRule.getWeatherInterface();
    }

    @Test
    @Title("Должны получать SC_OK при правильном запросе к /api/weather")
    public void shouldGetOkCodeForValidWeatherHandleRequest() throws IOException {
        Response<WeatherResponse> response = weatherInterface.getWeather(MOSCOW).execute();
        assertThat("Код ответа неверный", response.code(), is(HttpStatus.SC_OK));
    }

    @Test
    @Title("Должно совпадать время в городах одного часового пояса")
    public void shouldGetSameTimeForCitiesInSameTimeZone() throws IOException {
        Response<WeatherResponse> moscowResponse = weatherInterface.getWeather(MOSCOW).execute();
        long moscowTime = moscowResponse.body().getDt();

        Response<WeatherResponse> anotherCityResponse
                = weatherInterface.getWeather(ANOTHER_CITY_FROM_MOSCOW_TIMEZONE).execute();
        long timeInAnotherCityFromMoscowTimeZone = anotherCityResponse.body().getDt();

        assertThat("Время не должно отличаться", timeInAnotherCityFromMoscowTimeZone, equalTo(moscowTime));
    }
}
