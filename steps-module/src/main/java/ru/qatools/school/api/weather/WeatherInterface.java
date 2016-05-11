package ru.qatools.school.api.weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static ru.qatools.school.WebsiteData.CITY_PARAMETER;
import static ru.qatools.school.WebsiteData.REGION_PARAMETER;

/**
 * @author gladnik (Nikolai Gladkov)
 */
public interface WeatherInterface {

    @GET("/api/weather")
    Call<WeatherResponse> getWeather(@Query(CITY_PARAMETER) String city);

    @GET("/api/weather")
    Call<WeatherResponse> getWeather(@Query(CITY_PARAMETER) String city, @Query(REGION_PARAMETER) String region);

}
