package ru.qatools.school.apitests;

import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.qatools.school.DbClient;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static ru.qatools.school.data.Constants.*;

/**
 * @author ava1on
 */
public class DbAPITest {
    private DbClient dbClient;

    @Before
    public void setupDbConnection(){
        dbClient = new DbClient();
    }

    @After
    public void closeDbConnection(){
        dbClient.close();
    }

    @Test
    @Title("Списки городов, полученные через API и Database, должны совпадать")
    public void apiResponseShouldMatchDatabaseData(){
        List<Integer> ids =
                given()
                    .baseUri(BASE_URL)
                    .param(QUERY_PARAMETER, CITYNAME_PART)
                .when()
                    .get(SUGGEST_REQUEST)
                .then()
                    .assertThat().statusCode(HttpStatus.SC_OK)
                    .and().extract().body().path("id");
        assertThat("Полученные через API данные не соответствуют Database", ids,
                is(dbClient.getCitiesByNamePart(CITYNAME_PART)));
    }
}
