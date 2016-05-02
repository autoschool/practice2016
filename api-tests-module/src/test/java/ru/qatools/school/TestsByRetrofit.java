package ru.qatools.school;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import ru.qatools.school.Retrofit.CityResp;
import ru.qatools.school.Retrofit.WeatherResp;
import ru.qatools.school.Retrofit.WeatherService;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
* Created by onodee on 27.04.2016.
*/

public class TestsByRetrofit {
    private Retrofit retrofit;
    private WeatherService service;

    @Before
    public void setUpWeatherService() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://weather.lanwen.ru")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        service = retrofit.create(WeatherService.class);
    }

    @Test
    public void shouldSeeCityByRequest() throws IOException {
        Response<WeatherResp> resp = service.weather("Moscow").execute();

        assertThat(resp.code(), is(HttpStatus.SC_OK));
        assertThat(resp.body(), notNullValue());
        assertThat(resp.body().getCity(), equalTo("Moscow"));
    }

    @Test
    public void shouldSeeCitiesByRequest() throws  IOException {
        Response<List<CityResp>> resp = service.suggest("Mo").execute();

        assertThat(resp.code(), is(HttpStatus.SC_OK));
        assertThat(resp.body(), notNullValue());
        assertThat(resp.body(), not(is(empty())));
        assertThat(resp.body(), everyItem(hasProperty("name", startsWith("Mo"))));
    }
}
