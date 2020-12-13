package Day04;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.* ;


public class SpartanUpdatingTest {

    @BeforeAll
    public static void setUp(){
        baseURI = "http://54.86.91.200:8000";  // my
        basePath = "/api";
    }

    @AfterAll
    public  static void tearDown(){
        reset();
    }

    @DisplayName("Testing PUT /api/spartans/{id} with string body ")
    @Test
    public void testUpdatingSingleSpartan(){


       String payLoad = " {\n" +
               "                \"name\": \"Olivia\",\n" +
               "                \"gender\": \"Female\",\n" +
               "                \"phone\": 6549873210\n" +
               "        }  ";


       given()
               .log().all()
               .auth().basic("admin","admin")
               .contentType(ContentType.JSON)
               .pathParam("id", 1)
               .body(payLoad).
       when()
               .put("/spartans/{id}").
       then()
               .log().all()
               .statusCode(is(204))
               .header("Date", is(notNullValue()))
                .body( emptyString())
               ;


    }



    @DisplayName("Testing PATCH /api/spartans/{id} with string body ")
    @Test
    public void testPatchSingleSpartan(){


        String patchBody = " {\"name\": \"OliviaB20\"}  ";

        given()
                .log().all()
                .auth().basic("admin","admin")
                .contentType(ContentType.JSON)
                .pathParam("id", 2)
                .body(patchBody).
        when()
                .patch("/spartans/{id}").
        then()
                .log().all()
                .statusCode(is(204))
                .body(emptyString())

                ;


    }


    @DisplayName("Testing DELETE /api/spartans/{id} with string body ")
    @Test
    public void testDeleteSingleSpartan(){

        given()
                .log().all()
                .auth().basic("admin","admin")
                .pathParam("id", 2).

        when()
                .delete("/spartans/{id}").
        then()
                .log().all()
                .statusCode(is(204))
                .body(emptyString())

        ;


    }












}
