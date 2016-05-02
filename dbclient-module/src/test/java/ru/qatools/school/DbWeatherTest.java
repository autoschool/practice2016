package ru.qatools.school;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by onodee on 02.05.2016.
 */
public class DbWeatherTest {
    private DbClient dbClient;

    @Before
    public void openConnection(){
        dbClient = new DbClient();
    }

    @After
    public void closeConnecions(){
        dbClient.close();
    }

    @Test
    public void shouldSeeSuggestResponse(){
        List<String> suggest = dbClient.getSuggestByQuery("Ka");
        System.out.println(suggest);
        assertThat(suggest, notNullValue());
        assertThat(suggest, everyItem(startsWith("Ka")));
    }
}
