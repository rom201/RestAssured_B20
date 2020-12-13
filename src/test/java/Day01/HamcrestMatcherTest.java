package Day01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.* ;

public class HamcrestMatcherTest {


    @DisplayName("My 1st test with hamcrestM")
    @Test
    public void test1 () {

        assertThat(1+3, is(4));
        assertThat(1+3, is(4));
        //assertThat("wrong result",4+3,is(8));

        assertThat(1+3, not(is(5)));
        assertThat(1+3, not(equalTo(5)));
        assertThat(1+3, lessThan(5));




    }




}

