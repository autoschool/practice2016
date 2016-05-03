package apitests;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.io.IOException;

import static ApiData.ApiDataPreconditions.*;
import static org.hamcrest.Matchers.*;

/**
 * @author arrumm (Roman Arkhipov) on 28.04.2016.
 */
public class RestAssuredApiTest {

    private RequestSpecification requestSpecs;

    @Before
    public void initService() {
        RestAssured RA = new RestAssured();
<<<<<<< HEAD
        requestSpecs = RA.given().baseUri(BASE_URL).basePath(BASE_PATH);
=======
        requestSpecs = RA.given();
        requestSpecs.baseUri(BASE_URL);
        requestSpecs.basePath(BASE_PATH);
>>>>>>> 6774fddb9726b65f1b142e2db635462f2fc75246
    }

    @Test
    @Title("Should get error 400 with 'limit' parameter illegal type")
<<<<<<< HEAD
    public void shouldGetBadRequestCitiesIncorrectParType() throws IOException {
=======
    public void ShouldGetBadRequestCitiesIncorrectParType() throws IOException {
>>>>>>> 6774fddb9726b65f1b142e2db635462f2fc75246
        requestSpecs.param(LIMIT_PARAM, "DD")
                .expect().statusCode(HttpStatus.SC_BAD_REQUEST)
                .when().get(CITIES_METHOD);
    }

    @Test
    @Title("Should get all cities with 'bu' or 'Bu'")
<<<<<<< HEAD
    public void shouldGetCitiesWithBu() throws IOException {
=======
    public void ShouldGetCitiesWithBu() throws IOException {
>>>>>>> 6774fddb9726b65f1b142e2db635462f2fc75246
        requestSpecs.param(QUERY_PARAM, "bu")
                .expect()
                .statusCode(HttpStatus.SC_OK)
                .and().body("name", everyItem(anyOf(containsString("bu"), containsString("Bu"))))
                .when().get(SUGGEST_METHOD);
    }

}