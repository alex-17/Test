package testng.parameterized;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class TestNGTest6_1_0 {
    @Test
    @Parameters(value="number")
    public void parameterIntTest(int number) {
       System.out.println("Parameterized Number is : " + number);
    }
}
