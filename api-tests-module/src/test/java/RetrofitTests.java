import org.apache.http.HttpStatus;
import org.junit.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;

import java.io.IOException;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by onegines (Eugene Kirienko)
 */
public class RetrofitTests {

    @Test
    public void simpleTest() throws IOException {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://weather.lanwen.ru")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyService service = retrofit.create(MyService.class);

        Call<WeatherResp> req = service.weather("Moscow");
        Response<WeatherResp> resp = req.execute();

        assertThat(resp.code(), is(HttpStatus.SC_OK));
        assertThat(resp.body().getCity(), equalTo("Moscow"));
        assertThat(resp.body().getTemperatures(), hasSize(4));
        assertThat(resp.body().getTemperatures().get(0).getValue(), equalTo(9.0));
    }

}
