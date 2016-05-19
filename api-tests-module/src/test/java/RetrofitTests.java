import org.apache.http.HttpStatus;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.qatools.school.apiData.Cities;
import ru.qatools.school.apiData.CityJSON;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RetrofitTests {

    private static final String BASE_URL = "http://weather.lanwen.ru";
    public static final int POSITIVE_CITIES_LIMIT = 1;
    public static final int CITIES_LIST_ONE = 1;
    private static final String NEGATIVE_CITIES_LIMIT = "-1";

    private Retrofit citiesRetrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Test
    public void shouldGetCitiesListWhenRequestPositiveCitiesLimit() throws IOException {
        Cities cities = citiesRetrofit.create(Cities.class);
        Call<List<CityJSON>> request = cities.cities(String.valueOf(POSITIVE_CITIES_LIMIT));
        Response<List<CityJSON>> response = request.execute();
        assertThat(response.code(), is(HttpStatus.SC_OK));
        assertThat(response.body().size(), is(CITIES_LIST_ONE));
    }

    @Test
    public void shouldGetErrorWhenRequestNegativeCitiesLimit() throws IOException {
        Cities cities = citiesRetrofit.create(Cities.class);
        Call<List<CityJSON>> request = cities.cities(NEGATIVE_CITIES_LIMIT);
        Response<List<CityJSON>> response = request.execute();
        assertThat(response.code(), is(HttpStatus.SC_BAD_REQUEST));
    }
}