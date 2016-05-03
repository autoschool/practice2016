package retrofits;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

import static ApiData.ApiDataPreconditions.*;

/**
 * @author arrumm (Roman Arkhipov) on 29.04.2016.
 */

public interface APITestService {

    @GET("/" + BASE_PATH + "/" + SUGGEST_METHOD)
    Call<List<City>> suggest(@Query(QUERY_PARAM) String city);

    @GET("/" + BASE_PATH + "/" + CITIES_METHOD)
    Call<List<City>> cities(@Query(LIMIT_PARAM) String limit);

}
