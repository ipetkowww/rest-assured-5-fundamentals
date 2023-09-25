package leveraging_rest_assured_configuration;

import constants.URLConstant;
import io.restassured.RestAssured;
import io.restassured.listener.ResponseValidationFailureListener;
import org.testng.annotations.Test;

import static io.restassured.config.FailureConfig.failureConfig;
import static io.restassured.config.RedirectConfig.*;
import static org.hamcrest.Matchers.equalTo;

public class ConfigDemoTests {

    @Test
    void failureConfigExample() {

        ResponseValidationFailureListener failureListener =
                (reqSpec, resSpec, response) ->
                        System.out.printf("We have a failure, " +
                                          "response status was %s and the body contained: %s",
                                response.getStatusCode(), response.body().asPrettyString());

        RestAssured.config = RestAssured.config()
                .failureConfig(failureConfig().failureListeners(failureListener));

        RestAssured.get(URLConstant.GITHUB_BASE_URL + "users/ipetkowww")
                .then()
                .body("some.path", equalTo("a thing"));
    }


    @Test
    void maxRedirectsTest() {

        RestAssured.config = RestAssured.config()
                .redirect(redirectConfig().followRedirects(true).maxRedirects(0));

        RestAssured.get(URLConstant.GITHUB_BASE_URL + "repos/twitter/bootstrap")
                .then()
                .statusCode(equalTo(200));
    }
}
