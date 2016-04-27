package ru.qatools.school.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by onodee on 27.04.2016.
 */
public interface WeatherService {
    @GET("api/weather")
    Call<WeatherResp> weather(@Query("city") String city);
}
