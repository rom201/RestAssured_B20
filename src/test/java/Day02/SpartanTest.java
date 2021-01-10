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

public class SpartanTest {


    @BeforeAll
    public static void setUp(){

        baseURI = "http://100.26.101.158:8000";
        basePath = "/api";
        // base URI + basePath + what ever you need
    }


    @AfterAll
    public  static void tearDown(){
        //RestAssured.reset();
        reset();


    }






    @DisplayName("Testing /api/spartans endpoint")
    @Test
    public void testGetAllSpartan() {

        // this is INSTENSE from Akbar
        Response response = get("/spartans");
        response.prettyPrint();
        assertThat(response.getStatusCode(), is(200));
        assertThat(response.getContentType(), is(ContentType.JSON.toString()));
    }



    @DisplayName("Testing /api/spartans endpoint XML")
    @Test
    public void testGetAllSpartanXML(){

        /**
         * given
         * --request specification
         * info about request
         * base url, base path
         * header, query params, path variable, auth. and autoriz., loggin,
         * cookie, body(payload)
         *
         * when
         * --this where you send the rquest
         * get, post, put
         * we get response object
         *
         * then
         * --validatable response object
         * validate status code , headre, rayload
         * response time
         *
         */


        given()
                .header("accept","application/xml").
        when()
                .get("/spartans").
        then()
 //               .assertThat()  // make more readable
                .statusCode(200)
 //               .and()   // just for readability, optional
                .header("Content-Type","application/xml");



        given()
                .accept(ContentType.XML).
        when()
                .get("/spartans").
        then()
                .statusCode( is(200))
                .and()
                .contentType(ContentType.XML);



        }


}
