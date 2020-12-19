package Day07;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;
import pojo.BookCategory;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class LibraryAppTestD7 {

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


    @DisplayName("1.Testing PST  /login Endpoint")
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



    @DisplayName("2.Testing GET /dashboard_stats Endpoint")
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

    @DisplayName("3.Save the result Get Dashboard stat as Map Object")
    @Test
    public void testGetDashBoardStatAsMap(){


        JsonPath jp = given()
                                .log().all()
                                .header("x-library-token",myToken).
                        when()
                                .get("/dashboard_stats")
                                .jsonPath();

        //get responcse as Map

        Map<String,Object> responseJsonMap = jp.getMap("");//all response
        System.out.println("responseJsonMap = " + responseJsonMap);

    }

    @DisplayName("4.Save /get_bookcategories response as POJO")
    @Test
    public void testGeBookCategoryAsPojo() {


        JsonPath jp = given()
                            .log().all()
                            .header("x-library-token", myToken).
                        when()
                            .get("/get_book_categories").prettyPeek()
                            .jsonPath();

        List<BookCategory> allCategory = jp.getList("", BookCategory.class);
        allCategory.forEach(System.out::println);

        BookCategory num5BC = jp.getObject("[4]", BookCategory.class);
        System.out.println("num5BC = " + num5BC);


    }





}
//
//