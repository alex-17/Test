package testng.suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class TestConfig {
    @BeforeSuite
    public void testBeforeSuite() {
        System.out.println("BeforeSuite()");
    }
 
    @AfterSuite
    public void testAfterSuite() {
        System.out.println("AfterSuite()");
    }
 
    @BeforeTest
    public void testBeforeTest() {
        System.out.println("BeforeTest()");
    }
 
    @AfterTest
    public void testAfterTest() {
        System.out.println("AfterTest()");
    }
 
}
