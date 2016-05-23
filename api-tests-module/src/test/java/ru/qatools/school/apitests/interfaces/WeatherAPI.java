package ru.qatools.school.apitests.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.qatools.school.apitests.data.City;
import ru.qatools.school.apitests.data.WeatherInfo;

import java.util.List;


/**
 * @author Gavrilov_IS
 */
public interface WeatherAPI {
    @GET("cities")
    Call<List<City>> getCities();

    @GET("cities")
    Call<List<City>> getCitiesWithLimit(@Query ("limit") int lim);

    @GET("suggest")
    Call<List<City>> getSuggest(@Query ("query") String str);

    @GET("weather")
    Call<WeatherInfo> getWeather(@Query("city") String city);
}

