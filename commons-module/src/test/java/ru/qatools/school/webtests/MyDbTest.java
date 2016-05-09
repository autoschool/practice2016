package ru.qatools.school.webtests;

import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.qatools.school.ApiData.retrofits.APITestService;
import ru.qatools.school.ApiData.retrofits.City;
import ru.qatools.school.DbClient;
import ru.yandex.qatools.allure.annotations.Title;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static ru.qatools.school.ApiData.ApiDataPreconditions.BASE_URL;
import static ru.yandex.qatools.matchers.collection.HasSameItemsAsListMatcher.hasSameItemsAsList;


/**
 * @author arrumm 09.05.2016.
 */
public class MyDbTest {

    private APITestService service;
    private DbClient MyClient;

    private static final String SUGGEST_STRING = "bu";

    @Before
    public void initService() {
        MyClient = new DbClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(APITestService.class);
    }

    @Test
    @Title("Should get equal lists both by query to database and by api method")
    //@TestCaseId("14")
    public void shouldGetCitiesWithBuApiDb() throws IOException {

        List<String> cities = MyClient.getCitiesNameContains(SUGGEST_STRING);

        Call<List<City>> request = service.suggest("bu");
        Response<List<City>> response = request.execute();
        assertThat("Request status different from expected", response.code(), is(HttpStatus.SC_OK));

        List<String> names = response.body().stream().map(City::getName).collect(Collectors.toList());

        assertThat("Cities from api don't match cities from db",
                cities,
                hasSameItemsAsList(names));

    }

    @After
    public void closeConnection() {
        MyClient.close();
    }

}
