package Day10;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import testbase.SpartanAdminTestBase;

public class JsonSchemaValidation extends SpartanAdminTestBase {


    @DisplayName("Testing the structure of GET /api/spartans/{id} response")
    @Test
    public void testGetSingleSpartanSchema() {

        given()
                .spec(adminReqSpec)
                .pathParam("id",36).
        when()
                .get("/spartans/{id}").
        then()
                .log().body()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("singleSpartanSchema.json") ) ;
    }


}


