package ru.qatools.school;

import com.jayway.restassured.response.Response;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import org.apache.http.HttpStatus;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.qatools.school.apiData.SuggestJSON;
import ru.qatools.school.apiData.URI;
import ru.qatools.school.tp.TPInformerRule;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.core.Every.everyItem;

/**
 * @author totallynotkate (Kate Kocijevska)
 */
@RunWith(DataProviderRunner.class)
public class SuggestResourceTests {

    @Rule
    public TPInformerRule testpalm = new TPInformerRule("totallynotkate");

    @Test
    @Title("Должны получать в саджесте содержащие запрошенную строку города и код 200")
    @TestCaseId("")
    @DataProvider({"mos", "moscow", "мос", "москва"})
    public void shouldGetCityAnd200(String query){
        Response response = given()
                .baseUri(URI.BASE_URI.getValue())
                .basePath(URI.BASE_PATH.getValue())
                .param(URI.QUERY_PARAM.getValue(), query)
                .get(URI.SUGGEST_RESOURCE.getValue());
        assertThat(Arrays.stream(response.as(SuggestJSON[].class))
                .map(SuggestJSON::getName)
                .map(String::toLowerCase)
                .collect(Collectors.toList())
                ,everyItem(containsString(query)));
        response
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }
}
