import org.junit.Test;

public class MainClassTest {
    @Test
    public void testGetLocalNumber(){

        int actual = MainClass.getLocalNumber();
        int expected = 14;

        if(actual == expected){
            System.out.println("Test Passed. Method returs the correct number: " + actual);
        }else{
            System.out.println("Test Failed. Method returs the wrong number: " + actual);
        }

    }
}
