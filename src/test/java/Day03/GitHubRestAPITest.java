package Day03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.* ;




public class GitHubRestAPITest {

    @DisplayName("Test gitHub GET /user/{username}")
    @Test
    public void testGitHub(){


        given()
                .pathParam("username", "rom201").
        when()
                .get("https://api.github.com/users/{username}").
        then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .header("server","GitHub.com")
                .body("login",is("rom201"));






    }




}
