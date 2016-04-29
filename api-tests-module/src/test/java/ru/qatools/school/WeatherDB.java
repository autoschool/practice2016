package ru.qatools.school;

import com.google.gson.Gson;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.*;
import ru.qatools.school.entity.City;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class WeatherDB {

    private static DbClient dbClient;
    private static final String MAIN_PAGE = "http://weather.lanwen.ru/api/";
    private static WebApiInterface webApiInterface;
    private static final String city = "Moscow";


    @BeforeClass
    public static void getDbClient() {
        dbClient = new DbClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MAIN_PAGE)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        webApiInterface = retrofit.create(WebApiInterface.class);
    }


    @Test
    public void shouldSeeResultFromAPIEqualResultFromDB() throws IOException {
        String cityDb = dbClient.getCityById(3);
        Call<List<City>> callSuggest = webApiInterface.getListSuggest(city);
        Response<List<City>> response = callSuggest.execute();
        assertThat("Вывод от метода suggest по запросу " + city + " не совпадает с выводом из базы данных", cityDb,
                equalTo(response.body().get(0).getName()));
    }




    @AfterClass
    public static void closeDbClient() {
        dbClient.close();
    }

}
