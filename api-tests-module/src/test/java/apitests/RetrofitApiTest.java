package apitests;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofits.APITestService;
import retrofits.City;
import ru.qatools.school.tp.TPInformerRule;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.annotations.Title;

import java.io.IOException;
import java.util.List;

import static ApiData.ApiDataPreconditions.BASE_URL;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author arrumm (Roman Arkhipov) on 28.04.2016.
 */
public class RetrofitApiTest {

    private APITestService service;

    @Rule
    public TPInformerRule tms = new TPInformerRule("arrumm");

    @Before
    public void initService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(APITestService.class);
    }

    @Test
    @Title("Should get error 400 with 'limit' parameter String type")
    @TestCaseId("14")
<<<<<<< HEAD
    public void shouldGetErrorLimitIsString() throws IOException {
=======
    public void ShouldGetErrorLimitIsString() throws IOException {
>>>>>>> 6774fddb9726b65f1b142e2db635462f2fc75246

        Call<List<City>> request = service.cities("DD");
        Response<List<City>> response = request.execute();
        assertThat("Request status different from expected", response.code(), is(HttpStatus.SC_BAD_REQUEST));

    }

    @Test
    @Title("Should get all cities with 'bu' or 'Bu'")
    @TestCaseId("15")
<<<<<<< HEAD
    public void shouldGetCitiesWithBu() throws IOException {
=======
    public void ShouldGetCitiesWithBu() throws IOException {
>>>>>>> 6774fddb9726b65f1b142e2db635462f2fc75246

        Call<List<City>> request = service.suggest("bu");
        Response<List<City>> response = request.execute();
        assertThat("Request status different from expected", response.code(), is(HttpStatus.SC_OK));
<<<<<<< HEAD
        assertThat("At least one of suggested cities don't contain 'bu'",
=======
        assertThat("Some city don't contain 'bu'",
>>>>>>> 6774fddb9726b65f1b142e2db635462f2fc75246
                response.body(),
                everyItem(hasProperty("name", anyOf(containsString("bu"), containsString("Bu")))));

    }


}
