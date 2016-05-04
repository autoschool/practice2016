package ru.qatools.school;

import org.apache.http.HttpStatus;
import org.junit.Rule;
import org.junit.Test;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
* Created by onodee on 27.04.2016.
*/

public class TestsByRetrofit {

    @Rule
    public RetrofitRule serviceRule = new RetrofitRule();

    @Test
    public void shouldSeeCityByRequest() throws IOException {
        Response<WeatherResp> resp = serviceRule.getService().weather("Moscow").execute();

        assertThat(resp.code(), is(HttpStatus.SC_OK));
        assertThat(resp.body(), notNullValue());
        assertThat(resp.body().getCity(), equalTo("Moscow"));
    }

    @Test
    public void shouldSeeCitiesByRequest() throws  IOException {
        Response<List<CityResp>> resp = serviceRule.getService().suggest("Mo").execute();

        assertThat(resp.code(), is(HttpStatus.SC_OK));
        assertThat(resp.body(), notNullValue());
        assertThat(resp.body(), not(is(empty())));
        assertThat(resp.body(), everyItem(hasProperty("name", startsWith("Mo"))));
    }
}
