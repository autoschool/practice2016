package ru.qatools.school;

import org.apache.http.HttpStatus;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.qatools.school.apiData.CityJSON;
import ru.qatools.school.apiData.WeatherAPI;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author totallynotkate (Kate Kocijevska).
 */
public class RetrofitTests {

    private static final String BASE_URL = "http://weather.lanwen.ru";
    private static final String NEGATIVE_LIMIT = "-1";
    private static final long MAX_INT_PLUS_ONE_LIMIT = (long) Integer.MAX_VALUE + 1;
    private static final int CITIES_LIST_SIZE = 272;

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Test
    public void shouldGetErrorWhenRequestNegativeCitiesLimit() throws IOException {
        WeatherAPI weather = retrofit.create(WeatherAPI.class);
        Response<List<CityJSON>> response = weather.cities(NEGATIVE_LIMIT).execute();
        assertThat(response.code(), is(HttpStatus.SC_BAD_REQUEST));
    }

    @Test
    public void shouldGetErrorWhenRequestMaxIntegerPlusOneCities() throws IOException {
        WeatherAPI weather = retrofit.create(WeatherAPI.class);
        Response<List<CityJSON>> response = weather.cities(String.valueOf(MAX_INT_PLUS_ONE_LIMIT)).execute();
        assertThat(response.code(), is(HttpStatus.SC_BAD_REQUEST));
    }
}
