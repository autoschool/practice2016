import org.apache.http.HttpStatus;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

/**
 * Created by totallynotkate (Kate Kocijevska).
 */
public class RestassuredTests {
    @Test
    public void shouldGetErrorWhenRequestNegativeCitiesLimit(){
        given().baseUri("http://weather.lanwen.ru")
                .basePath("api")
                .param("limit", -1)
                .get("cities")
                .then()
                .assertThat()
                .statusCode(400);
    }
    @Test
    public void shouldGetCitiesListWhenRequestMaxIntegerPlusOneCities(){
        given().baseUri("http://weather.lanwen.ru")
                .basePath("api")
                .param("limit", (long)Integer.MAX_VALUE + 1)
                .get("cities")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body("toSet.size()", is(272));
    }
}
