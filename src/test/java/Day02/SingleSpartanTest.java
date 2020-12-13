package Day02;

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

public class SingleSpartanTest {

    @BeforeAll
    public static void setUp(){
        baseURI = "http://100.26.101.158:8000";
        basePath = "/api";
    }

    @AfterAll
    public  static void tearDown(){
        reset();
    }

    @DisplayName("Testing GET /spartans/{id} ")
    @Test
    public void test1Spartan(){


        given()
                .accept(ContentType.JSON).
        when()
                .get("/spartans/100").
        then()
                .statusCode(is(200))
                .contentType(ContentType.JSON);

        // another way

        given()
                .accept(ContentType.JSON)
                .pathParam("id",100).  // looks like we define the variable
        when()
                .get("/spartans/{id}").
        then()
                .statusCode(is(200))
                .contentType(ContentType.JSON);

        // easy way


        given()
                .accept(ContentType.JSON).
        when()
                .get("/spartans/{id}", 100).  // parametr here
        then()
                .statusCode(is(200))
                .contentType(ContentType.JSON);


    }

    @DisplayName("Testing GET /spartans/{id} endpoint Payload")
    @Test
    public void test1SpartanPayload(){

        given()
                .accept(ContentType.JSON).
        when()
                .get("/spartans/{id}", 100).
        then()
                .assertThat()
                .statusCode( is(200) )
                .contentType(ContentType.JSON)
                .body("id", is(100))
                .body("name", equalTo("SpartanX"))
                .body("gender", is(equalTo("Female")))
                .body("phone",equalTo(9999999999L))


        ;






    }



}
