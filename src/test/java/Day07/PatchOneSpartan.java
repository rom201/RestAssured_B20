package Day07;

import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import io.restassured.http.ContentType;
import pojo.Spartan;
import utility.ConfigurationReader;
import utility.SpartanUtil;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;




public class PatchOneSpartan {

    @BeforeAll
    public static void setUp(){
        //RestAssured.filters().add(new AllureRestAssured() ) ;
        baseURI = ConfigurationReader.getProperty("spartan.base_url");
        basePath = "/api" ;
    }
    @AfterAll
    public static void tearDown(){
        reset();
    }
    @DisplayName("Patching 1 data with Java Object")
    @Test
    public void testPath1DataPartialUpdate(){

        Map<String,Object> patchBodyMap = new LinkedHashMap<>();
        patchBodyMap.put("name", "B20 voila");
        patchBodyMap.put("phone", 7123456789L);



        given()
                .auth().basic(ConfigurationReader.getProperty("spartan.admin.username"),
                ConfigurationReader.getProperty("spartan.admin.password"))
                .pathParam("id", 100)
                .contentType(ContentType.JSON)
                .log().all()
                .body(patchBodyMap).
        when()
                .patch("/spartans/{id}").
        then()
                .log().all()
                .statusCode(204);




    }
    @DisplayName("Patching 1 data with POJO")// does not works for now
    @Test
    public void testPath1DataPartialUpdateWithPojo(){

        //Map<String,Object> patchBodyMap = new LinkedHashMap<>();
       // patchBodyMap.put("name", "B20 voila");
        //patchBodyMap.put("phone", 7123456789L);

        Spartan sp = new Spartan("B20 vvvv","",7123456789L);  //constructor no work
        //Spartan sp = new Spartan(); getter and setter also no works
        // map is better option Pojo class need some handeling to ignore empty values





        given()
                .auth().basic(ConfigurationReader.getProperty("spartan.admin.username"),
                ConfigurationReader.getProperty("spartan.admin.password"))
                .pathParam("id", 100)
                .contentType(ContentType.JSON)
                .log().all()
                .body(sp).
        when()
                .patch("/spartans/{id}").
       then()
                .log().all()
                .statusCode(500);




    }


}
