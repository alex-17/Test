package testng.parameterized;

import java.util.Vector;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class TestNGTest6_2 {
    @Test(dataProvider = "Data-Provider-Function")
    public void parameterIntTest(Class clzz, String[] number) {
       System.out.println("Parameterized Number is : " + number[0]);
       System.out.println("Parameterized Number is : " + number[1]);
    }
 
    //This function will provide the parameter data
    @DataProvider(name = "Data-Provider-Function")
    public Object[][] parameterIntTestProvider() {
        return new Object[][]{
           {Vector.class, new String[] {"java.util.AbstractList", 
                                                 "java.util.AbstractCollection"}},
           {String.class, new String[] {"1", "2"}},
           {Integer.class, new String[] {"1", "2"}}
           };
    }
}
