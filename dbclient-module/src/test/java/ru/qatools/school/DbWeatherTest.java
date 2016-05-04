package ru.qatools.school;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by onodee on 02.05.2016.
 */
public class DbWeatherTest {
    private DbClient dbClient;

    @Rule
    public RetrofitRule serviceRule = new RetrofitRule();

    @Before
    public void openConnection() {
        dbClient = new DbClient();
    }

    @After
    public void closeConnecions() {
        dbClient.close();
    }

    @Test
    public void shouldSeeSuggestResponse() throws IOException{
        List<String> suggestDb = dbClient.getSuggestByQuery("Ka");
        List<CityResp> suggestApi = serviceRule.getService().suggest("Ka").execute().body();

        assertThat(suggestDb, notNullValue());
        assertThat(suggestApi, notNullValue());
        assertThat(suggestApi, everyItem(hasProperty("name", isOneOf(suggestDb))));
    }

    @Test
    public void shouldSeeCityById() {
        String response = dbClient.getCityById(1);
        assertThat(response, notNullValue());
        assertThat(response, is("Saint Petersburg"));
    }
}
