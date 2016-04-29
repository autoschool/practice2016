package apitests;

import org.apache.http.HttpStatus;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.io.IOException;

import static ApiData.ApiDataPreconditions.*;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * @author arrumm (Roman Arkhipov) on 28.04.2016.
 */
public class RestAssuredApiTest {

    @Test
    @Title("Should get error 400 with 'limit' parameter illegal type")
    public void ShouldGetBadRequestCitiesIncorrectParType() throws IOException {
        given().baseUri(BASE_URL)
                .basePath(BASE_PATH).param(LIMIT_PARAM, "DD")
                .get(CITIES_METHOD)
                .then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @Title("Should get all cities with 'bu' or 'Bu'")
    public void ShouldGetCitiesWithBu() throws IOException {
        given().baseUri(BASE_URL)
                .basePath(BASE_PATH).param(QUERY_PARAM, "bu")
                .get(SUGGEST_METHOD)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .and().body("name", everyItem(anyOf(containsString("bu"), containsString("Bu"))));
    }

}
