package Day01;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Day1 hello test")
public class HelloTest {

    @BeforeAll
    public static void runBefoAll(){
        System.out.println("BeforeAll running");
    }

    @BeforeEach
    public void runBeforeEach() {
        System.out.println("BeforeEach running");
    }


    @DisplayName("My 1st test")
    @Test
    public void test1 () {
        Assertions.assertEquals(4, 1 + 3);
        System.out.println("Test1 running");
    }

    @Disabled
    @DisplayName("My 2nd test")
    @Test
    public void test2 () {
        assertEquals(12, 4 * 3);
        System.out.println("Test2 running");
    }

    @AfterEach
    public void runAfterEach() {
        System.out.println("AfterEach running");
    }

    @AfterAll
    public static void runAfterAll () {
        System.out.println("AfterAll running");
        }



}
