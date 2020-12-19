package Day06;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import io.restassured.http.ContentType;
import pojo.Spartan;
import pojo.SpartanRead;
import utility.ConfigurationReader;
import utility.SpartanUtil;

import java.util.List;
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

        SpartanRead sp = jp.getObject("", SpartanRead.class);
        System.out.println("sp = " + sp);


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

    }


    @DisplayName("Get All Data and Save Response JsonArray As Java Object")
    @Test
    public void getOneSpartanAndSaveResponseJsonAsJavaObject() {

        Response  response = given()
                .auth().basic(ConfigurationReader.getProperty("spartan.admin.username"),
                        ConfigurationReader.getProperty("spartan.admin.password")).
         when()
                    .get("/spartans");



        JsonPath jp = response.jsonPath();
        List<SpartanRead> allSpartanPOJOs = jp.getList("", SpartanRead.class);
        //System.out.println("allSpartanPOJOs = " + allSpartanPOJOs);
        allSpartanPOJOs.forEach(System.out::println);






    }


    }
