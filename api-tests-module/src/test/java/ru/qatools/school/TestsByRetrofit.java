package ru.qatools.school;

import org.apache.http.HttpStatus;
import org.junit.Test;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import ru.qatools.school.Retrofit.WeatherResp;
import ru.qatools.school.Retrofit.WeatherService;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by onodee on 27.04.2016.
 */
public class TestsByRetrofit {

    @Test
    public void simpleTest() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://weather.lanwen.ru")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        WeatherService service = retrofit.create(WeatherService.class);
        Call<WeatherResp> req = service.weather("Moscow");
        Response<WeatherResp> resp = req.execute();

        assertThat(resp.code(), is(HttpStatus.SC_OK));
        assertThat(resp.body().getCity(), equalTo("Moscow"));
        assertThat(resp.body().getTemperatures(), hasSize(4));
        assertThat(resp.body().getTemperatures().get(0).getValue(),
                equalTo(9.0));
    }
}
