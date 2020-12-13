package Day04;

import org.junit.jupiter.api.*;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;


public class LibraryAppTest {

    private static String myToken;

    @BeforeAll
    public static void setUp(){
        baseURI = "http://library1.cybertekschool.com";
        basePath = "/rest/v1";
    }

    @AfterAll
    public  static void tearDown(){
        reset();
    }

    @DisplayName("Testing PST  /login Endpoint")
    @Test
    public void test1Login(){

        //librarian69@library
        //KNPXrm3S

         myToken =

            given()
                    .log().all()
                    .contentType(ContentType.URLENC)
                    .formParam("email","librarian69@library")
                    .formParam("password","KNPXrm3S").
            when()
                    .post("/login").
            then()
                    .log().all()
                    //.assertThat()
                    .statusCode( is(200))
                    .contentType(ContentType.JSON)
                    .body("token", is( not( emptyString() ) )  )
                    .extract()
                    .jsonPath()
                    .getString("token");
        System.out.println("-----------------------------");
            System.out.println("myToken "+myToken);
// How to extract some data out of response object
        // after doing validation in then section
        // without breaking the chain -->> use extract() method that return
    }



    @DisplayName("Testing GET /dashboard_stats Endpoint")
    @Test
    public void test2Dashboard_stats(){

        given()
                .log().all()
                .header("x-library-token",myToken).
        when()
                .get("/dashboard_stats").
        then()
                .log().all()
                .statusCode(is (200))
                .contentType(ContentType.JSON)
        ;

    }

}
//
//