package ru.qatools.school.apitests;

import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import retrofit2.Response;
import ru.qatools.school.DbClient;
import ru.qatools.school.DbClientRule;
import ru.qatools.school.api.suggest.SuggestCity;
import ru.qatools.school.api.suggest.SuggestInterface;
import ru.qatools.school.api.weather.WeatherInterface;
import ru.qatools.school.api.weather.WeatherResponse;
import ru.qatools.school.rules.RetrofitWeatherRule;
import ru.yandex.qatools.allure.annotations.Title;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;


/**
 * @author gladnik (Nikolai Gladkov)
 */
public class RetrofitTests {

    private static final String MOSCOW = "Moscow";
    private static final String ANOTHER_CITY_FROM_MOSCOW_TIMEZONE = "Saint Petersburg";
    private static final String CITY_NAME_PART = "bur";

    private static WeatherInterface weatherInterface;
    private static SuggestInterface suggestInterface;
    private static DbClient dbClient;

    @ClassRule
    public static RetrofitWeatherRule retrofitRule = new RetrofitWeatherRule();

    @BeforeClass
    public static void initInterfaces() {
        weatherInterface = retrofitRule.getRetrofit().create(WeatherInterface.class);
        suggestInterface = retrofitRule.getRetrofit().create(SuggestInterface.class);
    }

    @ClassRule
    public static DbClientRule dbClientRule = new DbClientRule();

    @BeforeClass
    public static void initDbClient() {
        dbClient = dbClientRule.getDbClient();
    }

    @Test
    @Title("Должны получать SC_OK при правильном запросе к api/weather")
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

    @Test
    @Title("Должны совпадать значения саджеста полученные через API и через базу данных")
    public void shouldGetSameSuggestResponseForApiAndDB() throws IOException {
        List<SuggestCity> apiCities = suggestInterface.getSuggestCities(CITY_NAME_PART).execute().body();
        List<SuggestCity> dbCities = dbClient.getSuggestCitiesForQuery(CITY_NAME_PART);

        assertThat("Ответ API не совпадает с ответом БД", apiCities, equalTo(dbCities));
    }

}
