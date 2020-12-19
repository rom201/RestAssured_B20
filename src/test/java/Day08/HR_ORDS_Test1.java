package Day08;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Region;
import pojo.Country;
import testbase.HR_ORDS_TestBase;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class HR_ORDS_Test1 extends HR_ORDS_TestBase {




    @DisplayName("Test GET /countries/{country_id} to POJO")
    @Test
    public void testCountryResponseToPOJO() {

    Response response = given()
                                .pathParam("country_id", "AR").
                        when()
                                .get("/countries/{country_id}").prettyPeek();

        Country ar = response.as(Country.class) ;
        System.out.println("Argentina = " + ar);
        Country ar1 = response.jsonPath().getObject("",Country.class);
        System.out.println("Argentina with jsonPath = " + ar1);


    }


    @DisplayName("Test GET /countries to List of POJO")
    @Test
    public void testAllCountriesResponseToListOfPOJO(){

        Response response = get("/countries").prettyPeek() ;

        List<Country> countryList = response.jsonPath().getList("items", Country.class) ;
        countryList.forEach(System.out::println);






    }











}
