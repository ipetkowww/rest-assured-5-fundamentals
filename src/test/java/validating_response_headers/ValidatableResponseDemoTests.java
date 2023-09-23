package validating_response_headers;

import constants.URLConstant;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

public class ValidatableResponseDemoTests {

    @Test
    public void basicValidatableExample() {
        RestAssured.get(URLConstant.GITHUB_BASE_URL)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .header("x-ratelimit-limit", "60")
                .header("server", "GitHub.com");
    }
}
