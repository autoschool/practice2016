package ru.qatools.school.apitests;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.qatools.school.apitests.data.City;
import ru.qatools.school.apitests.data.WeatherInfo;
import ru.qatools.school.apitests.interfaces.WeatherAPI;
import ru.yandex.qatools.allure.annotations.Title;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Gavrilov_IS on 11.05.2016.
 */
public class RetrofitAPITest {
    private final String BASE_URL = "http://weather.lanwen.ru/api/";
    private WeatherAPI weather;
    private final String CITY = "Moscow";

    Retrofit client = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Before
    public void init() {
        weather = client.create(WeatherAPI.class);
    }

    @Test
    @Title("Проверяем, что запрос обработан и город в ответе соответствует запрошенному")
    public void shouldGetInfoForCityInRequest() throws IOException {
        Response<WeatherInfo> response = weather.getWeather(CITY).execute();
        assertThat("Успешная обработка запроса", response.code(), is(HttpStatus.SC_OK));
        assertThat("Город в ответе должен соответствовать запрошенному", response.body().getCity(), is(CITY));

        System.out.println(response.headers().toString());
        System.out.println("respinse 0 = " + response.body().getListOfTemperatures().get(0).toString());
        System.out.println("respinse 0 = " + response.body().getListOfTemperatures().get(1).toString());
        System.out.println("respinse 0 = " + response.body().getListOfTemperatures().get(2).toString());
    }

    @Test
    @Title("Запрос на /cities с параметром limit=5 вернул 5 записей")
    public void shouldGetExpectedCountOfCities() throws IOException {
        Response<List<City>> response = weather.getCitiesWithLimit(5).execute();
        assertThat("Успешная обработка запроса", response.code(), is(HttpStatus.SC_OK));
        assertEquals("Город в ответе должен соответствовать запрошенному", response.body().size(), 5);
    }

}
