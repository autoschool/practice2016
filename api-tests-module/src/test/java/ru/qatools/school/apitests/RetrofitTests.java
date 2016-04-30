package ru.qatools.school.apitests;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.qatools.school.apiData.SuggestResp;
import ru.qatools.school.apiData.WeatherApi;
import ru.qatools.school.apiData.WeatherResp;
import ru.yandex.qatools.allure.annotations.Title;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author onegines (Eugene Kirienko)
 */
public class RetrofitTests {

    private static final String MAIN_PAGE = "http://weather.lanwen.ru/";
    private static final String CITY = "Saint Petersburg";
    private static final String QUERY = "Saint";

    private WeatherApi weatherApi;

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(MAIN_PAGE)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Before
    public void init() {
        weatherApi = retrofit.create(WeatherApi.class);
    }

    @Test
    @Title("Должны получить код 400 после отправки запроса без параметров")
    //@TestCaseId("53")
    public void shouldGetErrorCode400AfterSendNoQueryToSuggest() throws IOException {

        Call<List<SuggestResp>> req = weatherApi.suggest();
        Response<List<SuggestResp>> resp = req.execute();

        assertThat("Неправильный код ответа", resp.code(), is(HttpStatus.SC_BAD_REQUEST));

    }

    @Test
    @Title("Должны получить список городов, содержащих в названии строку из запроса")
    //@TestCaseId("42")
    public void ShouldGetSuggestsContainsPartOfName() throws IOException {

        Call<List<SuggestResp>> req = weatherApi.suggest(QUERY);
        Response<List<SuggestResp>> resp = req.execute();

        assertThat("Request status different from expected", resp.code(), is(HttpStatus.SC_OK));
        assertThat("Все города должны содержать строку из запроса", resp.body(), everyItem(hasProperty("name", containsString(QUERY))));

    }

    @Test
    @Title("WEATHER: Должны получить код 400 после отправки запроса без параметров")
    //@TestCaseId("65")
    public void shouldGetErrorCode400AfterSendNoQueryToWeather() throws IOException {

        Call<WeatherResp> req = weatherApi.weather();
        Response<WeatherResp> resp = req.execute();

        assertThat("Неправильный код ответа", resp.code(), is(HttpStatus.SC_BAD_REQUEST));

    }


}
