package testng.ignor;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestIgnore {
    @Test //default enable=true
    public void test1() {
        System.out.println("test1()");
        Assert.assertEquals(true, true);
    }
 
    @Test(enabled = true)
    public void test2() {
        System.out.println("test2()");
        Assert.assertEquals(true, true);
    }
 
    @Test(enabled = false)
    public void test3() {
        System.out.println("test3()");
        Assert.assertEquals(true, true);
    }
}
