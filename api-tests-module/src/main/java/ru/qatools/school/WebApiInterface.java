package ru.qatools.school;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.qatools.school.entity.City;
import ru.qatools.school.entity.Widget;

import java.util.List;


public interface WebApiInterface {

    @GET("cities")
    Call<List<City>> getCities();

    @GET("init")
    Call<List<City>> getInit();

    @GET("cities")
    Call<List<City>> getCitiesLimit(@Query("limit") String limit);

    @GET("suggest")
    Call<List<City>> getListSuggest(@Query("query") String query);

    @GET("weather")
    Call<Widget> getWeatherWidget(@Query("city") String city, @Query("region") String region);
}

