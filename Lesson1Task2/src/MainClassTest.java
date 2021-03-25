import org.junit.Test;

public class MainClassTest {
    @Test
    public void testGetClassNumber(){

        int actual = MainClass.getClassNumber();
        int expected = 45;
        if(actual > expected){
            System.out.println("Test Passed. " + actual + " > " + expected);
        }else{
            System.out.println("Test Failed. " + actual + " < " + expected);
        }
    }
}
