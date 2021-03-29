import org.junit.Assert;
import org.junit.Test;

    public class MainClassTest {

        @Test
        public void testGetClassString() {
            String string = MainClass.getClassString();

            if((string.contains("Hello") || (string.contains("hello")))){
                System.out.println("Test passed. String contains substring");
            } else {
                Assert.fail("Test Failed. String does not contain substring");
            }
        }

    }

