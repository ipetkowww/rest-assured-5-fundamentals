package validating_response_headers;

import constants.URLConstant;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class ValidatableResponseWithHamcrestDemoTests {

    @Test
    public void basicValidatableExampleWithHamcrest() {
        RestAssured.get(URLConstant.GITHUB_BASE_URL)
                .then()
                .statusCode(equalTo(HttpStatus.SC_OK))
                .contentType(containsString(ContentType.JSON.toString()))
                .header("x-ratelimit-limit", equalTo("60"))
                .header("etag", notNullValue());
    }

}
