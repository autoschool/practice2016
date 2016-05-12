package ru.qatools.school;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.qatools.school.utils.deserialization.CitySuggest;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.Arrays;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.matchers.collection.HasSameItemsAsListMatcher.hasSameItemsAsList;

/**
 * @author totallynotkate (Kate Kocijevska)
 */
public class DbTest {

    private DbClient dbClient;

    @Before
    public void before(){
        dbClient = new DbClient();
    }

    @Test
    @Title ("Списки саджестов городов из API и БД должны совпадать")
    @Features("Запросы к DB и API")
    public void shouldGetSameSuggests(){
        List<CitySuggest> dbSuggest = dbClient.getSuggestedCities("mos");
        List<CitySuggest> apiSuggest = Arrays.asList(given().baseUri("http://weather.lanwen.ru")
                .basePath("api")
                .param("query", "mos")
                .get("suggest")
                .as(CitySuggest[].class));
        assertThat("Списки саджестов городов из API и БД должны совпадать", apiSuggest, hasSameItemsAsList(dbSuggest));
    }

    @After
    public void after(){
        dbClient.close();
    }
}
