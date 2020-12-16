package Day06;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import io.restassured.http.ContentType;
import pojo.Spartan;
import utility.ConfigurationReader;
import utility.SpartanUtil;

import java.util.Map;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;



public class JsonToJavaObject {

    @BeforeAll
    public static void setUp(){
        //RestAssured.filters().add(new AllureRestAssured() ) ;
        baseURI = ConfigurationReader.getProperty("spartan.base_url");
        basePath = "/api" ;
    }
    @AfterAll
    public static void tearDown() {
        reset();
    }

    @DisplayName(" GET one data Save response as Java object")
    @Test
    public void testGet1SpartanData() {



        Response  response = given()
                .auth().basic(ConfigurationReader.getProperty("spartan.admin.username"),
                ConfigurationReader.getProperty("spartan.admin.password"))
                .pathParam("id", 2)
                .log().all().
        when()
                .get("/spartans/{id}").prettyPeek();

        JsonPath jp = response.jsonPath();
        Map<String, Object>  responseMap = jp.getMap("");
        System.out.println("responseMap = " + responseMap);


        /**
         * {
         *     "id": 2,
         *     "name": "Nels",
         *     "gender": "Male",
         *     "phone": 4218971348
         * }
         *
         *
         *
         *
         */



        ;
    }






}
