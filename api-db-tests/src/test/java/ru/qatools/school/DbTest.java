package ru.qatools.school;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.qatools.school.apiData.CitySuggest;
import ru.qatools.school.apiData.URI;
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

    private static final String PARTIAL_CITY_NAME = "mos";
    private DbClient dbClient;

    @Before
    public void before(){
        dbClient = new DbClient();
    }

    @Test
    @Title ("Списки саджестов городов из API и БД должны совпадать")
    @Features("Запросы к БД и API")
    public void shouldGetSameSuggests(){
        List<CitySuggest> dbSuggest = dbClient.getSuggestedCities(PARTIAL_CITY_NAME);
        List<CitySuggest> apiSuggest = Arrays.asList(given().baseUri(URI.BASE_URI.getValue())
                .basePath(URI.BASE_PATH.getValue())
                .param(URI.QUERY_PARAMETER.getValue(), PARTIAL_CITY_NAME)
                .get(URI.SUGGEST_RESOURCE.getValue())
                .as(CitySuggest[].class));
        assertThat("Списки саджестов городов из API и БД должны совпадать", apiSuggest, hasSameItemsAsList(dbSuggest));
    }

    @After
    public void after(){
        dbClient.close();
    }
}
