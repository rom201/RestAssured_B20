package Day05;

import io.restassured.http.ContentType;
import utility.ConfigurationReader;
import org.junit.jupiter.api.*;
import utility.SpartanUtil;

import java.util.Map;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;
import org.junit.jupiter.api.DisplayName;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class SpartanE2E_HappyPath {

    private static Map<String, Object> payLoadMap;
    private static int newId;



    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("spartan.base_url");
        basePath = "/api";
        payLoadMap = SpartanUtil.getRandomSpartanPayLoad();

    }

    @AfterAll
    public static void tearDown() {
        reset();
    }

    @DisplayName("1.Testing POST/api/spartans endpoint")
    @Test
    public void testAddData(){


       newId =
        given()
                .auth().basic(ConfigurationReader.getProperty("spartan.admin.username"),
                ConfigurationReader.getProperty("spartan.admin.password"))
                .contentType(ContentType.JSON)
                .body(payLoadMap)
                .log().all().
       when()
                .post("/spartans").
       then()
                .log().all()
                .assertThat()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("data.name", is (payLoadMap.get("name")))
                .body("data.gender", is (payLoadMap.get("gender")))
                .body("data.phone", equalTo (payLoadMap.get("phone")))
        .extract()
                .jsonPath()
                .getInt("data.id")

                ;
        System.out.println("newID = "+ newId);

    }


    @DisplayName("2. Testing GET /api/spartans/{id} Endpoint")
    @Test
    public void testGet1SpartanData() {

        given()
                .auth().basic(ConfigurationReader.getProperty("spartan.admin.username"),
                ConfigurationReader.getProperty("spartan.admin.password"))
                .pathParam("id", newId)
                .log().all().

        when()
                .get("/spartans/{id}").
                then()
                .log().all()
                .assertThat()
                .statusCode(is(200))
                .contentType(ContentType.JSON)

                .body("id", is(newId))
                .body("name", is(payLoadMap.get("name")))
                .body("gender", is(payLoadMap.get("gender")))
                .body("phone", equalTo(payLoadMap.get("phone")))
                ;
    }



    @DisplayName("3. Testing PUT /api/spartans/{id} Endpoint")
    @Test
    public void testUpdate1SpartanData() {
        // We want to have different payload so we can update
        // Option is rerun the utility method to override
        // existing map object with newly generated faker map object
        payLoadMap = SpartanUtil.getRandomSpartanPayLoad();
//        System.out.println("payloadMap = " + payloadMap);
        given()
                .auth().basic("admin", "admin")
                .pathParam("id", newId)
                .contentType(ContentType.JSON)
                .body(payLoadMap) // updated payload
                .log().all().
        when()
                .put("/spartans/{id}").
        then()
                .log().all()
                .assertThat()
                .statusCode(is(204))
                .body(emptyString())
        ;
        // in order to make sure the update actually happened
        // i want to make another get request to this ID
        given()
                .auth().basic("admin", "admin")
                .pathParam("id", newId)
                .log().all().
                when()
                .get("/spartans/{id}").
                then()
                .log().all()
                .assertThat()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
                .body("id", is(newId))
                .body("name", is(payLoadMap.get("name")))
                .body("gender", is(payLoadMap.get("gender")))
                .body("phone", is(payLoadMap.get("phone")))
        ;

    }

    @DisplayName("4. Testing DELETE /api/spartans/{id} Endpoint")
    @Test
    public void testDelete1SpartanData(){

        given()
                .auth().basic("admin","admin")
                .pathParam("id" , newId)
                .log().all().
        when()
                .delete("/spartans/{id}").
        then()
                .log().all()
                .assertThat()
                .statusCode( is(204) )
                .body( emptyString() ) ;

        // in order to make sure the delete actually happened
        // i want to make another get request to this ID expect 404
        given()
                .auth().basic("admin","admin")
                .pathParam("id" , newId)
                .log().all().
        when()
                .get("/spartans/{id}").
        then()
                .log().all()
                .assertThat()
                .statusCode( is (404) ) ;

    }






}