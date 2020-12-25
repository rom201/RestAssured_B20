package Day04;


import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.* ;



public class SpsrtanAddingUpdTest {

    @BeforeAll
    public static void setUp(){
        baseURI = "http://54.86.91.200:8000";  // my
        basePath = "/api";
    }

    @AfterAll
    public  static void tearDown(){
        reset();
    }


    @DisplayName("Get all spartans")
    @Test
    public void testGetAllSpartans(){
        given()
                .log().all()
                .auth().basic("admin","admin").
       when()
                .get("/spartans").
       then()
                .log().all()
                .statusCode(is(200))
            ;
    }


    @DisplayName("add 1 data with raw Json Post api/spartans")
    @Test
    public void testAddOneData(){
        String newSpartanStr = "{\n" +
                "        \"name\": \"Odessa1\",\n" +
                "        \"gender\": \"Male\",\n" +
                "        \"phone\": 1607649459\n" +
                "    }";


        System.out.println("newSpartanStr = " + newSpartanStr);

        given()
                .log().all()
                .auth().basic("admin","admin")
                .contentType(ContentType.JSON)
                .body(newSpartanStr).

        when()
                .post("/spartans").
        then()
                .log().all()
                .statusCode(is(201))
                .contentType((ContentType.JSON))
                .body("success",is("A Spartan is Born!"))
                .body("data.gender", is("Male"))
                .body("data.name",is("Odessa1"))
                .body("data.phone",is(1607649459))
                ;


    }


    @DisplayName("add 1 data with map Object Post api/spartans")
    @Test
    public void testAddOneDataMap(){

        Map<String, Object> payLoadMap = new LinkedHashMap<>();

        payLoadMap.put("name", "Tucky2");
        payLoadMap.put("gender", "Male");
        payLoadMap.put("phone", 9123456789L);

        System.out.println("payLoadMap = " + payLoadMap);

        given()
                .log().all()
                .auth().basic("admin","admin")
                .contentType(ContentType.JSON)
                .body(payLoadMap).
        when()
                .post("/spartans").

        then()

                .log().all()
                .statusCode(is(201))
                .contentType((ContentType.JSON))

                .body("success",is("A Spartan is Born!"))
                .body("data.gender", is("Male"))
                .body("data.name",is("Tucky2"))
                .body("data.phone",is(9123456789L));


    }


    @DisplayName("add 1 data with External file to Post api/spartans")
    @Test
    public void testAddOneDataWithJsonFileBody(){

        // Create a file called singleSpartanSchema.json right under root directory
        // with below content
    /*
    {
        "name": "Olivia",
        "gender": "Female",
        "phone": 6549873210
    }
    add below code to point File object to this singleSpartanSchema.json
     */
        File externalJson = new File ("singleSpartanSchema.json");

        given()
                .log().all()
                .auth().basic("admin","admin")
                .contentType(ContentType.JSON)
                .body(externalJson).
        when()
                .post("/spartans").

        then()

                .log().all()
                .statusCode(is(201))
                .contentType((ContentType.JSON))

                .body("success",is("A Spartan is Born!"))
                .body("data.gender", is("Female"))
                .body("data.name",is("Olivia"))
                .body("data.phone",is(6549873210L));



    }




}
