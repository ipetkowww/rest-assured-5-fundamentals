package leveraging_rest_assured_configuration;

import constants.URLConstant;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ResponseSpecificationDemoTests {

    @BeforeClass
    void setup() {
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(404)
                .expectContentType(ContentType.JSON)
                .build();
    }

    @AfterClass
    void cleanup() {
        RestAssured.responseSpecification = null;
    }

    // or
    ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .expectContentType(ContentType.JSON)
            .build();

    @Test
    void testWithSpecOne() {
        RestAssured.get(URLConstant.GITHUB_BASE_URL + "non/existing/url")
                .then()
                .spec(responseSpec);

        // + more custom verifications
    }

    @Test
    void testWithSpecTwo() {
        RestAssured.get(URLConstant.GITHUB_BASE_URL + "non/existing/url")
                .then()
                .spec(responseSpec);
        // + more custom verifications
    }
}
