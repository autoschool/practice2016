import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by onegines (Eugene Kirienko)
 */
public interface MyService {

    @GET("/api/weather")
    Call<WeatherResp> weather(@Query("city") String city);
}
