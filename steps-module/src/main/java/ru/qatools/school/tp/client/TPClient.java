package ru.qatools.school.tp.client;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

import java.util.List;

/**
 * @author lanwen (Merkushev Kirill)
 */
public class TPClient {

    private static final String ENDPOINT = System.getProperty("tp.api.endpoint", "https://testpalm-school.yandex-team.ru/");
    private static final String TOKEN = System.getProperty("tp.api.token", "6c77c209-adb1-48b0-9792-4731cecdbf63");
    private static final String RUN = System.getProperty("tp.api.run", "571b4711071d88000685cfa7");


    public static void callUpdate(String project, List<TCaseStatus> statuses) {
        RestAssured.given()
                .relaxedHTTPSValidation()
                .log().all()
                .baseUri(ENDPOINT)
                .basePath("/api/")
                .header("TestPalm-Api-Token", TOKEN)
                .contentType(ContentType.JSON)
                .body(statuses)
                .when().log().all().post("/testrun/{project}/{run}/bulk", project, RUN).peek();
    }

    public static class TCaseStatus {
        private TCase testCase;
        private String status;
        private Long duration;

        public TCaseStatus(TCase testCase, String status, Long duration) {
            this.testCase = testCase;
            this.status = status;
            this.duration = duration;
        }
    }

    public static class TCase {
        private String id;

        public TCase(String id) {
            this.id = id;
        }
    }

}
