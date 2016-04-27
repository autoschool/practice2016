import org.apache.http.HttpStatus;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by onegines (Eugene Kirienko)
 */
public class RestassuredTests {

    @Test
    public void simpleTest() throws Exception {
        given().baseUri("http://weather.lanwen.ru")
                .basePath("api").param("city", "Moscow")
                .get("weather")
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .and().body("city", equalTo("Moscow"))
                .and().body("temperatures", hasSize(4))
                .and().body("temperatures[0].value",
                equalTo(12.24f));
    }

}
