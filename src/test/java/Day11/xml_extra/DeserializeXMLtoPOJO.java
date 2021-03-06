package Day11.xml_extra;

import static io.restassured.RestAssured.*;

import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.config.XmlPathConfig;
import org.junit.jupiter.api.Test;
import testbase.SpartanAdminTestBase;



public class DeserializeXMLtoPOJO extends SpartanAdminTestBase{


    @Test
    public void getAllSpartanAsXMLSaveFirstToPojo() {

        XmlPath xp =
                with()
                        .spec(adminReqSpec)
                        .accept(ContentType.XML).
                        get("/spartans")
                        //.prettyPeek()
                        .xmlPath();

        Item pojo1 = xp.getObject("List.item[1]", Item.class);
        System.out.println("pojo1 = " + pojo1);

        SpartanXml pojo2 = xp.getObject("List.item[0]", SpartanXml.class);
        System.out.println("pojo2 = " + pojo2);


    }

}
