package ru.qatools.school.apitests;

import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.qatools.school.DbClient;
import ru.qatools.school.apidata.SuggestResp;
import ru.qatools.school.apidata.WeatherApi;
import ru.qatools.school.apidata.WeatherResp;
import ru.qatools.school.tp.TPInformerRule;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Title;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static ru.yandex.qatools.matchers.collection.HasSameItemsAsListMatcher.hasSameItemsAsList;

/**
 * @author onegines (Eugene Kirienko)
 */
public class RetrofitTest {

    private static final String MAIN_PAGE = "http://weather.lanwen.ru/";
    private static final String CITYNAMEBEGIN = "Saint";
    private static final String CITYNAMEPART = "ain";

    private WeatherApi weatherApi;
    private DbClient dbClient;

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(MAIN_PAGE)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Rule
    public TPInformerRule tms = new TPInformerRule("onegines");

    @Before
    public void init() {
        weatherApi = retrofit.create(WeatherApi.class);
        dbClient = new DbClient();
    }

    @After
    public void close() {
        dbClient.close();
    }

    @Test
    @Features("Suggest")
    @Title("Должны получить код 400 после отправки запроса саджеста без параметров")
    //@TestCaseId("53")
    public void shouldGetErrorCode400AfterSendNoQueryToSuggest() throws IOException {

        Response<List<SuggestResp>> resp = weatherApi.suggest().execute();

        assertThat("Неправильный код ответа", resp.code(), is(HttpStatus.SC_BAD_REQUEST));

    }

    @Test
    @Features("Suggest")
    @Title("Должны получить список городов, содержащих в названии строку из запроса")
    //@TestCaseId("42")
    public void shouldGetSuggestsContainsPartOfName() throws IOException {

        Response<List<SuggestResp>> resp = weatherApi.suggest(CITYNAMEBEGIN).execute();

        assertThat("Request status different from expected", resp.code(), is(HttpStatus.SC_OK));
        assertThat("Все города должны содержать строку из запроса", resp.body(), everyItem(hasProperty("name", containsString(CITYNAMEBEGIN))));

    }

    @Test
    @Features("Suggest")
    @Title("Должны совпадать списки (от API и DB) саджестов, содержащих в названии города строку из запроса")
    //@TestCaseId("75")
    public void shouldMatchSuggestsFromApiAndDb() throws IOException {

        Response<List<SuggestResp>> respApi = weatherApi.suggest(CITYNAMEPART).execute();
        List<SuggestResp> respDb = dbClient.getSuggestCitiesByNamePart(CITYNAMEPART);

        assertThat("Неправильный код ответа", respApi.code(), is(HttpStatus.SC_OK));
        assertThat("Списки из API и DB должны совпадать", respApi.body(), hasSameItemsAsList(respDb));

    }

    @Test
    @Features("Weather")
    @Title("Должны получить код 400 после отправки запроса погоды без параметров")
    //@TestCaseId("65")
    public void shouldGetErrorCode400AfterSendNoQueryToWeather() throws IOException {

        Response<WeatherResp> resp = weatherApi.weather().execute();

        assertThat("Неправильный код ответа", resp.code(), is(HttpStatus.SC_BAD_REQUEST));

    }


}
