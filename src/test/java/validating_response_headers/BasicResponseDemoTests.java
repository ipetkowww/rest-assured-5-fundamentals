package validating_response_headers;

import constants.URLConstant;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class BasicResponseDemoTests {

    private Response response;

    @BeforeMethod
    public void beforeEachTest() {
        response = RestAssured.get(URLConstant.GITHUB_BASE_URL);
    }

    @Test
    public void convenienceMethods() {
        assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        assertEquals(response.getContentType(), "application/json; charset=utf-8");
    }

    @Test
    public void genericHeader() {
        assertEquals(response.getHeader("server"), "GitHub.com");
        assertEquals(response.getHeader("x-ratelimit-limit"), "60");
    }
}
