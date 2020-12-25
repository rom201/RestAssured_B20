package Day11;


import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import testbase.SpartanAdminTestBase;
import static io.restassured.RestAssured.*;


import static org.hamcrest.Matchers.*;

public class Test_XML_Response extends SpartanAdminTestBase{

    // get xml response for GET /spartans
    @DisplayName("GET /spartans get xml response")
    @Test
    public void testXML(){

        XmlPath xp =
        given()
                .spec(adminReqSpec)
                .accept(ContentType.XML).
        when()
                .get("/spartans").
       then()
                .log().all()
                .statusCode(200)
                //verify first person name is Cherelle
                .body("List.item[0].name", is("Adam Jones"))
                // verify first person id is 602
                //if go to groovy
                //
                .body("List.item[0].id", is("1"))
//                        .body("List.item[0].id.toInteger()", is(1))
                .contentType(ContentType.XML)
                .extract()
                .xmlPath()
        ;
        // get the name of first person in the response
        System.out.println("xp.getString(\"List.item[0].name\") = "
                + xp.getString("List.item[0].name"));
        // get the id of 3rd person
        System.out.println("xp.getString(\"List.item[2].id\") = "
                + xp.getString("List.item[2].id"));

        // get the last item phone number
        System.out.println("xp.getLong(\"List.item[-1].phone\") = "
                + xp.getLong("List.item[-1].phone"));


    }

}
