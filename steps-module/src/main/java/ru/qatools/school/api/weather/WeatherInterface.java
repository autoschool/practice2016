package ru.qatools.school.api.weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author gladnik (Nikolai Gladkov)
 */
public interface WeatherInterface {

    @GET("/api/weather")
    Call<WeatherResponse> getWeather(@Query("city") String city);

    @GET("/api/weather")
    Call<WeatherResponse> getWeather(@Query("city") String city, @Query("region") String region);

}
