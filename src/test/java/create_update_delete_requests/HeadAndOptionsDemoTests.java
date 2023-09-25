package create_update_delete_requests;

import constants.URLConstant;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.equalTo;

public class HeadAndOptionsDemoTests {

    @Test
    public void headTest() {
        RestAssured.head(URLConstant.GITHUB_BASE_URL)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(emptyOrNullString());
    }

    @Test
    public void optionsTest() {
        RestAssured.options(URLConstant.GITHUB_BASE_URL)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT)
                .header("access-control-allow-methods", equalTo("GET, POST, PATCH, PUT, DELETE"))
                .body(emptyOrNullString());
    }
}
