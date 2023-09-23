package understanding_different_parameter_settings;

import constants.URLConstant;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class PathParamsDemoTests {

    @Test
    public void withoutParamHardcoded() {
        RestAssured.get(URLConstant.REPOSITORY_ENDPOINT + "/ipetkowww/selenium-java")
                .then()
                .statusCode(equalTo(HttpStatus.SC_OK))
                .body("id", equalTo(481502064));
    }

    @Test
    public void withParam() {
        RestAssured.given()
                .pathParams("user", "ipetkowww")
                .pathParams("repoName", "selenium-java")
                .get(URLConstant.REPOSITORY_ENDPOINT + "/{user}/{repoName}")
                .then()
                .statusCode(200)
                .body("id", equalTo(481502064));
    }

    @Test
    public void searchRepoTest() {
        RestAssured.given()
                .param("q", "java")
                .get(URLConstant.SEARCH_REPOS_ENDPOINT)
                .prettyPeek()
                .then()
                .statusCode(200);
    }
}
