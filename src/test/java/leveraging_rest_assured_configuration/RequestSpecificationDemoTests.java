package leveraging_rest_assured_configuration;

import constants.URLConstant;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class RequestSpecificationDemoTests {

    static RequestSpecification spec = new RequestSpecBuilder().setBaseUri(URLConstant.GITHUB_BASE_URL).build();

    // alternatively
    @BeforeSuite
    void setup() {
        RestAssured.requestSpecification =
                new RequestSpecBuilder()
                        .setBaseUri(URLConstant.GITHUB_BASE_URL).build();
    }

    @Test
    void testUsingLocalRequestSpec() {
        RestAssured
                .given()
                .spec(spec)
                .when()
                    .get()
                .then()
                    .statusCode(200);
    }
}
