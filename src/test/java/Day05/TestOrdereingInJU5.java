package Day05;

// by default is alphabetical order by test Name

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayName;

import  static org.junit.jupiter.api.MethodOrderer.*;


//in the level or class
//@TestMethodOrder(OrderAnnotation.class)
//@TestMethodOrder(Random.class)
@TestMethodOrder(MethodOrderer.DisplayName.class) //
public class TestOrdereingInJU5 {




    @Order(3)
    @DisplayName("3. at")
   @Test
   public  void testA(){
       System.out.println("testing test A ");
   }

    @Order(1)
    @DisplayName("1. ct")
    @Test
    public void testC(){
        System.out.println("running test C");
    }


    @Order(4)
    @DisplayName("4. dt")
    @Test
    public void testD(){
        System.out.println("running test D");
    }

    @Order(2)
    @DisplayName("2. bt")
    @Test
    public void testB(){
        System.out.println("running test B");
    }











}
