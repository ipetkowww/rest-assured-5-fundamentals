package create_update_delete_requests;

import constants.URLConstant;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.containsString;

public class CustomRequestDemoTests {

    @Test
    void customRequest() {
        // both equivalent to RestAssured.get(...)
        RestAssured.request(Method.GET, URLConstant.GITHUB_BASE_URL)
                .then()
                .statusCode(200)
                .body(containsString("current_user_url"));

        RestAssured.request("GET", URLConstant.GITHUB_BASE_URL)
                .then()
                .statusCode(200)
                .body(containsString("current_user_url"));
    }

    @Test
    void traceExample() {
        RestAssured.request(Method.TRACE, URLConstant.GITHUB_BASE_URL)
                .then()
                .statusCode(405); // Method Not Allowed
    }
}
