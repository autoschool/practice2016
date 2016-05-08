package ru.qatools.school;

import org.apache.http.HttpStatus;
import org.junit.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.qatools.school.apiData.CityJSON;
import ru.qatools.school.apiData.URI;
import ru.qatools.school.apiData.WeatherAPI;
import ru.yandex.qatools.allure.annotations.Title;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.is;
import static ru.qatools.school.utils.HttpCodeMatcher.responseHasHttpStatus;
import static ru.qatools.school.utils.ResponseArraySizeMatcher.responseHasArraySize;

/**
 * @author totallynotkate (Kate Kocijevska).
 */
public class RetrofitTests {

    private static final String NEGATIVE_LIMIT = "-1";
    private static final long MAX_INT_PLUS_ONE_LIMIT = (long) Integer.MAX_VALUE + 1;

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URI.BASE_URI.getValue())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Test
    @Title("В ответ на запрос отрицательного количества городов должны получать Bad request")
    public void shouldGetErrorWhenRequestNegativeCitiesLimit() throws IOException {
        WeatherAPI weather = retrofit.create(WeatherAPI.class);
        Response<List<CityJSON>> response = weather.cities(NEGATIVE_LIMIT).execute();
        assertThat("В ответ на запрос отрицательного количества городов должны получать Bad request",
                response.code(), is(HttpStatus.SC_BAD_REQUEST));
    }

    @Test
    @Title("В ответ на запрос большего чем Integer.MAX_VALUE количества городов должны получать Bad request")
    public void shouldGetErrorWhenRequestMaxIntegerPlusOneCities() throws IOException {
        WeatherAPI weather = retrofit.create(WeatherAPI.class);
        Response<List<CityJSON>> response = weather.cities(String.valueOf(MAX_INT_PLUS_ONE_LIMIT)).execute();
        assertThat("В ответ на запрос большего чем Integer.MAX_VALUE количества городов должны получать Bad request",
                response.code(), is(HttpStatus.SC_BAD_REQUEST));
    }

    @Test
    @Title("Должны получать запрошенное количество городов и статус OK")
    public void shouldGetNumberOfCitiesRequested() throws IOException {
        int numberOfCitiesRequested = 2;
        WeatherAPI weather = retrofit.create(WeatherAPI.class);
        Response<List<CityJSON>> response = weather.cities(String.valueOf(numberOfCitiesRequested)).execute();
        assertThat("Должны получать запрошенное количество городов и статус OK",
                response, both(responseHasHttpStatus(HttpStatus.SC_OK))
                .and(responseHasArraySize(numberOfCitiesRequested)));
    }
}
