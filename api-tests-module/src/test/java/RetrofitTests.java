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

/**
 * Created by totallynotkate (Kate Kocijevska).
 */
public class RetrofitTests {

    private Retrofit citiesRetrofit = new Retrofit.Builder()
            .baseUrl("http://weather.lanwen.ru")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Test
    public void shouldGetErrorWhenRequestNegativeCitiesLimit() throws IOException {
        Cities cities = citiesRetrofit.create(Cities.class);
        Call<List<CityJSON>> request = cities.cities("-1");
        Response<List<CityJSON>> response = request.execute();
        assertThat(response.code(), is(HttpStatus.SC_BAD_REQUEST));
    }

    @Test
    public void shouldGetCitiesListWhenRequestMaxIntegerPlusOneCities() throws IOException {
        Cities cities = citiesRetrofit.create(Cities.class);
        Call<List<CityJSON>> request = cities.cities(String.valueOf((long)Integer.MAX_VALUE + 1));
        Response<List<CityJSON>> response = request.execute();
        assertThat(response.code(), is(HttpStatus.SC_OK));
        assertThat(response.body().size(), is(272));
    }
}
