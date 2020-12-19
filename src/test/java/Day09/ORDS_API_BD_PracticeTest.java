package Day09;

import static io.restassured.RestAssured.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Country;
import testbase.HR_ORDS_TestBase;
import utility.DB_Utility;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


import java.util.List;
import java.util.Map;

public class ORDS_API_BD_PracticeTest extends HR_ORDS_TestBase {

    @DisplayName("GET /countries/{country_id} Compare Result with DB")
    @Test
    public void testResponseMatchDatabaseData() {

        String myCountryID = "AR";
        Country arPOJO = given()
                                    .log().all()
                                    .pathParam("country_id", myCountryID).
                               when()
                                    .log().all()
                                    .get("/countries/{country_id}")
                                    .as(Country.class);

        // here the shorter way of above code
        //Country arPOJO1 = get("/countries/{country_id}", myCountryID).as(Country.class);
        System.out.println("arPOJO1 = " + arPOJO);

        String query = "SELECT * FROM COUNTRIES WHERE COUNTRY_ID = '" + myCountryID + "'  " ;
        System.out.println("query = " + query);
        DB_Utility.runQuery(query);
        Map<String, String> dbResultMap = DB_Utility.getRowMap(1);

// now start validating the actual response to expected result from database
        assertThat( arPOJO.getCountry_id()  ,is(dbResultMap.get("COUNTRY_ID") ) );
        assertThat(arPOJO.getCountry_name() ,is(dbResultMap.get("COUNTRY_NAME") ) );
// save region_id from the map as number
        int expectedRegionId = Integer.parseInt(dbResultMap.get("REGION_ID"));
        assertThat(arPOJO.getRegion_id()    ,equalTo(   expectedRegionId   ) );

    }

    @DisplayName("GET /countries Capture All CountryID and Compare Result with DB")
    @Test
    public void testResponseAllCountryIDsMatchDatabaseData(){

        List<String> allCountriesIds =  get("/countries").jsonPath().getList("items.country_id");
        allCountriesIds.forEach(System.out::println);


        DB_Utility.runQuery("SELECT * FROM COUNTRIES");
        List<String> expectedListFromDB = DB_Utility.getColumnDataAsList("COUNTRY_ID");
        expectedListFromDB.forEach(System.out::println);

        assertThat(allCountriesIds, equalTo(expectedListFromDB));





    }





}
