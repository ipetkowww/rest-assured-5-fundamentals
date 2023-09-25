package create_update_delete_requests;

import constants.URLConstant;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class OtherMethodsDemoTests {

    private static final String TOKEN = "your token goes here ";

    @Test(description = "Create a repo")
    void postTest() {
        RestAssured
                .given()
                .auth()
                .oauth2(TOKEN)
//                    .header("Authorization", "token " + TOKEN)
                    .body("{\"name\": \"delete-me\"}")
                .when()
                    .post(URLConstant.USER_REPOS_ENDPOINT)
                .then()
                .statusCode(201);
    }

    @Test(description = "Update a repo")
    void patchTest() {
        RestAssured
                .given()
                    .header("Authorization", "token " + TOKEN)
                    .body("{\"name\": \"deleteme-patched\"}")
                .when()
                    .patch("https://api.github.com/repos/ipetkowww/deleteme")
                .then()
                .statusCode(200);
    }

    @Test(description = "Delete a repo")
    void deleteTest() {
        RestAssured
                .given()
                    .header("Authorization", "token " + TOKEN)
                .when()
                    .delete("https://api.github.com/repos/ipetkowww/deleteme-patched")
                .then()
                    .statusCode(204);
    }
}
