package ru.qatools.school.apiData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

/**
 * Created by totallynotkate (Kate Kocijevska).
 */
public interface WeatherAPI {
    @GET("api/cities")
    Call<List<CityJSON>> cities(@Query("limit") String limit);
}