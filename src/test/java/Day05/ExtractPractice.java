package Day05;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;
import io.restassured.http.ContentType;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat;


public class ExtractPractice {


    /*
        extract() method of RestAssured
        enable you to extract data after validation
        in then section of the method chaining
        */
    @BeforeAll
    public static void setUp(){
        baseURI = "http://54.86.91.200:8000";
        basePath = "/api" ;
    }
    @AfterAll
    public static void tearDown(){
        reset();
    }
    @DisplayName("Testing GET /api/spartans/search with Basic auth")
    @Test
    public void testSearchAndExtractData(){

       JsonPath jp =  given()
                            .log().all()
                            .auth().basic("admin","admin")
                            .queryParam("nameContains", "iv")
                            .queryParam("gender","Female").
                   when()
                            .get("/spartans/search").
                   then()
                            .log().all()
                            .assertThat()
                            .statusCode(is(200))
                            .extract()
                            .jsonPath();

        List<String> allNames = jp.getList("content.name");

        System.out.println("allNames = " + allNames);
        int numOfElement = jp.getInt("numberOfElements");

        System.out.println("numOfElement = " + numOfElement);

        assertThat(numOfElement, equalTo(allNames.size()));

        assertThat(allNames, hasSize(numOfElement));









    }









}
