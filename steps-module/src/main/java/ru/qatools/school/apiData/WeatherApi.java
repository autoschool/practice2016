package ru.qatools.school.apiData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

/**
 * @author onegines (Eugene Kirienko)
 */
public interface WeatherApi {

    @GET("/api/suggest")
    Call<List<SuggestResp>> suggest();

    @GET("/api/suggest")
    Call<List<SuggestResp>> suggest(@Query("query") String query);

    @GET("/api/cities")
    Call<List<CitiesResp>> cities();

    @GET("/api/cities")
    Call<List<CitiesResp>> cities(@Query("limit") int limit);

    @GET("/api/weather")
    Call<WeatherResp> weather();

    @GET("/api/weather")
    Call<WeatherResp> weather(@Query("city") String city);
}
