package ru.qatools.school;

import org.junit.rules.ExternalResource;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by onodee on 03.05.2016.
 */
public class RetrofitRule extends ExternalResource {
    private Retrofit retrofit;
    private WeatherService service;

    protected void before() throws Throwable {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://weather.lanwen.ru")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        service = retrofit.create(WeatherService.class);
    }

    public WeatherService getService() {
        return service;
    }
}
