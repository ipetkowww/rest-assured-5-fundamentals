package validating_response_headers;

import constants.URLConstant;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class DemoTest {

    @Test
    public void someTest() {
        RestAssured.get(URLConstant.GITHUB_BASE_URL)
                .then()
                .statusCode(200);
    }
}
