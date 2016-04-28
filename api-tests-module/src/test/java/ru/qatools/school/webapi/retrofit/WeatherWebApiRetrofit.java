package ru.qatools.school.webapi.retrofit;


import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.qatools.school.WebApiInterface;
import ru.qatools.school.entity.City;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;


@RunWith(DataProviderRunner.class)
public class WeatherWebApiRetrofit {
    private static final String MAIN_PAGE = "http://weather.lanwen.ru/api/";
    private static final int COUNT_CITIES = 272;
    private static WebApiInterface webApiInterface;

    @BeforeClass
    public static void getWebApiInterface() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MAIN_PAGE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webApiInterface = retrofit.create(WebApiInterface.class);
    }


    @DataProvider
    public static List<Object> limits() {
        List<Object> placesList
                = new ArrayList<Object>();
        placesList.add(null);
        return placesList;
    }

    @Test
    public void shouldSeeListCities() throws IOException {
        Call<List<City>> listCities = webApiInterface.getCities();
        Response<List<City>> response = listCities.execute();
        assertThat("Не получили список городов", response.body().size(), is(COUNT_CITIES));
    }

    @Test
    public void shouldSeeOneCity() throws IOException {
        Call<List<City>> city = webApiInterface.getCitiesLimit("1");
        Response<List<City>> response = city.execute();
        assertThat("Не получили список с одним эелементом", response.body().size(), is(1));
        assertThat("Поле названия города не пустое", response.body().get(0).getName(), is(not(nullValue())));
        assertThat("Поле названия страны не пустое", response.body().get(0).getCountry(), is(not(nullValue())));
    }

}
