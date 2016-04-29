package ru.qatools.school.webtests;

import org.apache.http.HttpStatus;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

/**
 * Created by thebaldsoprano on 28.04.16.
 */
public class WeatherBackEndTest {

    public static final String SUGGEST_PHRASE = "Saint";

    @Test
    public void apiSuggestIsDbSuggest() throws Exception {

        Connection connection = DriverManager.getConnection("jdbc:mysql://db.weather.lanwen.ru:3306/weather", "autoschool", "ya2016");
        DSLContext create = DSL.using(connection, SQLDialect.MYSQL);

        List<Integer> ids = create.select()                                 //this block fetches specified
                .from(table("City"))                                        //data from DB and initializes
                .where(field("name").like("%" + SUGGEST_PHRASE + "%"))      //List with it
                .limit(500)
                .fetch().getValues("id", Integer.class);

        given().baseUri("http://weather.lanwen.ru")                         //this block gets same data using API as List
                .basePath("/api").param("query", SUGGEST_PHRASE)            //and compares it with List got from DB
                .get("/suggest")
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .and().body("id", hasSize(ids.size()))
                .and().body("id", is(ids));
    }
}
