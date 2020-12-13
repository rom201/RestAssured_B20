package Day05;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class HamcrestCollectionSupport {

    @Test
    public void testList(){

        List<Integer> numList = Arrays.asList(4,6,7,8,9,5,88,90);

        // use hamcrest matcher to test the size of this list
        assertThat(numList , hasSize(8) ) ;


       // assertThat(numList, hasItem(89));
        //assertThat(numList, hasItems(88, 3));
        //assertThat(numList, hasItem(89));
        assertThat(numList, everyItem(greaterThan(3)));
        assertThat(numList, everyItem(is(greaterThan(3))));


        List<String> allNames = Arrays.asList("Aaar dood","Boot","Toom","Saaoom");
        assertThat(allNames, hasSize(4));
        assertThat(allNames, hasItems("Boot","Toom"));
        assertThat(allNames, everyItem(containsString("oo")));

        assertThat(allNames, everyItem(containsStringIgnoringCase("OO")));

        assertThat("Ramazan Alic", anyOf(is("Ram"), endsWith("ic")));






    }



}
