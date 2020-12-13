package Day04;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.jar.JarEntry;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.* ;



public class day4_movie {

//http://www.omdbapi.com/?t=The Orville&=apiKey42bffefc

    @BeforeAll
    public static void setUp(){
        baseURI = "http://www.omdbapi.com";
        //basePath = "/api";  not here

    }

    @AfterAll
    public  static void tesrDown(){
        reset();
    }

    @DisplayName("Search movie or openmovie DB tet")
    @Test
    public void testMovie(){

        given()
                .queryParam("apiKey","42bffefc")
                .queryParam("t", "The Orville").
        when()
                .get().prettyPeek().//
        then()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
                .body("Title", is("The Orville"))
                .body("Ratings[0].Source", is("Internet Movie Database"))
        ;

    }


    @DisplayName("gettin log of response")
    @Test
    public void testGettingTheLog(){

        given()
                .queryParam("apiKey","42bffefc")
                .queryParam("t", "John Wick").
                //.log().all()
                //.log().uri().



        when()
                .get().
       then()
                //.log().all()
                .log().ifValidationFails()


                .statusCode(is(200))
                .body("Plot", containsString("ex-hit-man"))
                .body("Ratings[1].Source", is("Rotten Tomatoes"))
                ;





    }





}
