package ru.qatools.school.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.qatools.school.Responses.CitiesResponse;

import java.util.List;

import static ru.qatools.school.data.Constants.LIMIT_PARAMETER;

/**
 * @author ava1on
 */
public interface Cities {
    @GET("cities")
    Call<List<CitiesResponse>> citiesList(@Query(LIMIT_PARAMETER) int limitValue);
}
