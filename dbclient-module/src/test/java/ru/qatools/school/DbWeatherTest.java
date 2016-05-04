package ru.qatools.school;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

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
    public void shouldSeeSuggestResponse() {
        List<String> suggestDb = dbClient.getSuggestByQuery("Mo");
        List<String> suggestApi = dbClient.getSuggestByQuery("Mo");

        assertThat(suggestDb, notNullValue());
        assertThat(suggestApi, notNullValue());
        assertThat(suggestApi, containsInAnyOrder(suggestDb));
    }

    @Test
    public void shouldSeeCityById() {
        String response = dbClient.getCityById(1);
        assertThat(response, notNullValue());
        assertThat(response, is("Saint Petersburg"));
    }
}
