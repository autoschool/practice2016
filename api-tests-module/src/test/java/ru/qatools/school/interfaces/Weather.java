package ru.qatools.school.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.qatools.school.Responses.WeatherResponse;

import static ru.qatools.school.data.Constants.CITY_PARAMETER;
import static ru.qatools.school.data.Constants.REGION_PARAMETER;

/**
 * @author ava1on
 */
public interface Weather {
    @GET("weather")
    Call<WeatherResponse> weather(@Query(CITY_PARAMETER) String city, @Query(REGION_PARAMETER) String region);
}