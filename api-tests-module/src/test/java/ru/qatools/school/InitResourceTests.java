package ru.qatools.school;

import org.apache.http.HttpStatus;
import org.junit.Rule;
import org.junit.Test;
import ru.qatools.school.apiData.URI;
import ru.qatools.school.tp.TPInformerRule;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.annotations.Title;

import static com.jayway.restassured.RestAssured.given;

/**
 * @author totallynotkate (Kate Kocijevska)
 */
public class InitResourceTests {
    @Rule
    public TPInformerRule testpalm = new TPInformerRule("totallynotkate");

    @Test
    @Title("В ответ на запрос к init должны получать код 200")
    @TestCaseId("52")
    public void shouldGet200(){
        given()
                .baseUri(URI.BASE_URI.getValue())
                .basePath(URI.BASE_PATH.getValue())
                .get(URI.INIT_RESOURCE.getValue())
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }
}
