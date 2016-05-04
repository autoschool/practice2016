package ru.qatools.school.apitests;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.qatools.school.Responses.CitiesResponse;
import ru.qatools.school.Responses.WeatherResponse;
import ru.qatools.school.interfaces.Cities;
import ru.qatools.school.interfaces.Weather;
import ru.yandex.qatools.allure.annotations.Title;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static ru.qatools.school.data.Constants.*;

/**
 * @author ava1on
 */
public class RetrofitAPITests {

    private static Retrofit retrofit;

    @BeforeClass
    public static void initRetrofit(){
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    @Test
    @Title("Должны получить заданное в параметре количество городов в ответе")
    public void shouldReturnRequestedNumberOfCities() throws IOException{
        Cities cities = retrofit.create(Cities.class);
        Call<List<CitiesResponse>> request= cities.citiesList(LIMIT_VALUE);
        Response<List<CitiesResponse>> response = request.execute();
        assertThat("Код ответа неверный", response.code(), is(HttpStatus.SC_OK));
        assertThat("Неверное количество городов в ответе", response.body(), hasSize(LIMIT_VALUE));
    }

    @Test
    @Title("Значение температуры по шкале Фаренгейта должно соответствовать значению по шкале Цельсия")
    public void shouldReturnCompatibleTemperatureValuesForCelsiusAndFahrenheit() throws IOException{
        Weather weather = retrofit.create(Weather.class);
        Call<WeatherResponse> request = weather.weather(CITY_PARAMETER, null);
        Response<WeatherResponse> response = request.execute();
        assertThat("Код ответа неверный", response.code(), is(HttpStatus.SC_OK));
        assertThat("Значение температуры не правильно переведено из шкалы Цельсия в шкалу Фаренгейта",
                response.body().getTemperatures()[2].getValue(),
                Matchers.is(closeTo(response.body().recalculateCelsiusToFahrenheit(), TEMPERATURE_ERROR)));
    }
}
