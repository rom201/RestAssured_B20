package Day01;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.* ;

import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.* ;
import static org.hamcrest.MatcherAssert.assertThat;


public class RestAssuredIntro {


    @DisplayName("My firsrt test with Rest Assured")
    @Test
    public void TestHello(){

        Response response = get("http://54.86.91.200:8000/api/hello");

        System.out.println("response code is " + response.getStatusCode());
        assertThat(response.getStatusCode(), is(200));
        //assertThat(response.getStatusCode(), is(201));

        //pretty print entire payload
        String strPayLoad =  response.prettyPrint();
        assertThat(strPayLoad, is(containsString("Sparta")));

        System.out.println(response.getHeader("Content-Type"));
        System.out.println(response.getContentType());
        System.out.println(response.contentType());

        assertThat(response.contentType(), is("text/plain;charset=UTF-8"));
        assertThat(response.contentType() , startsWith("text") );

        System.out.println(response.getContentType());


        //assertThat(response.contentType(), is(ContentType.TEXT.toString()));
        //assertThat(response.getContentType(),is(ContentType.TEXT));

        assertThat(response.contentType() ,  startsWith( ContentType.TEXT.toString())  );
        assertThat(response.contentType() ,  is( not(ContentType.JSON)   ) );






  }

    @DisplayName("My 2nd test with assured")
    @Test
    public void testString(){

        String str = "Rest Assured is cool so far";

        assertThat(str, is("Rest Assured is cool so far"));


        //assert the str is "Rest Assured IS COOL so far" in case insensitive manner
        assertThat(str, equalToIgnoringCase("Rest Assured IS COOL so far") );

       // assert the str startWith "Rest"
        assertThat(str, startsWith("Rest"));
        assertThat(str, endsWith("ar"));

       // assert the str contains "is cool"
        assertThat(str, containsString("ol"));


       // assert the str contains "IS COOL" case insenstive manner
        assertThat(str, containsStringIgnoringCase("IS COOL"));










   }




}

