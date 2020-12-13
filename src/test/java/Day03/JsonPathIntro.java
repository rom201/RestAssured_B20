package Day03;

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

public class JsonPathIntro {
    @BeforeAll
    public static void setUp(){
        baseURI = "http://100.26.101.158:8000";
        basePath = "/api";
    }

    @AfterAll
    public  static void tearDown(){
        reset();
    }


    @DisplayName("Extracting data out of Spartan Json Object")
    @Test
    public  void test1SpartanPayload(){

    Response response = given()
                            .pathParam("id", 100).
                        when()
                            .get("spartans/{id}")
                            .prettyPeek();

    //response.prettyPrint();
        JsonPath jp = response.jsonPath();
        int idS = jp.getInt("id");
        String nameS = jp.getString("name");
        String genderS = jp.getString("gender");
        long phoneS = jp.getLong("phone");

        System.out.println("id = " + idS);
        System.out.println("name = " + nameS);
        System.out.println("gender = " + genderS);
        System.out.println("phone = " + phoneS);


    }

    @DisplayName("Extracting data out of all Spartans")
    @Test
    public  void test2ExtractAllSpartansData(){

        // Response response = get ("/spartans");
        // JsonPath jp = response.jsonPath();

        JsonPath jp = get("/spartans").jsonPath();
        System.out.println("jp.getString(\"name[0]\") = "
                + jp.getString("name[0]"));
        System.out.println("jp.getLong(\"phone[0]\") = "
                + jp.getLong("phone[0]"));

        System.out.println("jp.getString(\"gender[6]\") = "
                + jp.getString("gender[6]"));
// last guy
        System.out.println("jp.getString(\"name[-1]\") = "
                + jp.getString("name[-1]"));



        //getting all data from response

        List<String> list = jp.getList("name");
        System.out.println(list);

    }
    // query parametrs http://100.26.101.158:8000/api/spartans/search?nameContains=de&gender=Male

    @DisplayName("Testing extract data ")
    @Test
    public void testSearch(){



    JsonPath jp =  given()
                        .queryParam("nameContains", "de")
                        .queryParam("gender","Male").
                 when()
                        .get("/spartans/search")
                        .jsonPath();
        System.out.println("first guy name is  "
                + jp.getString("content[0].name"));
        System.out.println("3d guy phone number "
                + jp.getLong("content[2].phone"));

        System.out.println("all names "+ jp.getList("content.name") );

        System.out.println("value of empty parameter  "
                +jp.getBoolean("pageable.sort.empty"));



    }







}
