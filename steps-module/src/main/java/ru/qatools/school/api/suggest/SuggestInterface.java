package ru.qatools.school.api.suggest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

import static ru.qatools.school.WebsiteData.QUERY_PARAMETER;

/**
 * @author gladnik (Nikolai Gladkov)
 */
public interface SuggestInterface {

    @GET("/api/suggest")
    Call<List<SuggestCity>> getSuggestCities(@Query(QUERY_PARAMETER) String city);

}
