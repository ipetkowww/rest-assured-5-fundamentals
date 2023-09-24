package validating_response_body;

import constants.URLConstant;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class BasicResponseBodyDemoTests {

    @Test
    public void jsonPathReturnsMap() {
        Response response = RestAssured.get(URLConstant.RATE_LIMIT_ENDPOINT);

        JsonPath jsonPath = response.body().jsonPath();

        Map<String, String> fullJson = jsonPath.get();
        Map<String, String> subMap = jsonPath.get("resources");
        Map<String, String> subMap2 = jsonPath.get("resources.core");

        int coreLimit = jsonPath.get("resources.core.limit");
        int graphqlRemaining = jsonPath.get("resources.graphql.remaining");

        System.out.println(fullJson);
        System.out.println("=");
        System.out.println(subMap);
        System.out.println("=");
        System.out.println(subMap2);
        System.out.println("=");
        System.out.println(coreLimit);
        System.out.println("=");
        System.out.println(graphqlRemaining);

        assertEquals(coreLimit, 60);
        assertEquals(graphqlRemaining, 0);
    }

    @Test
    public void nestedBodyValidation() {
        RestAssured.get(URLConstant.RATE_LIMIT_ENDPOINT)
                .then()
                .rootPath("resources.core")
                    .body("limit", equalTo(60))
                    .body("remaining", lessThan(60))
                    .body("reset", notNullValue())
                .rootPath("resources.search")
                    .body("limit", equalTo(10))
                    .body("resource", equalTo("search"))
                    .body("remaining", lessThanOrEqualTo(10))
                .noRootPath()
                    .body("resources.graphql.limit", equalTo(0));

    }
}
