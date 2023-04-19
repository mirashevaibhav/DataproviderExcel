package rerunfailtestscript;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoClass {
    @Test (retryAnalyzer = RetryExample.class)
    public void testCase1() {
        System.out.println("testCase1");
        Assert.assertTrue(false);
    }
    @Test(retryAnalyzer = RetryExample.class)
    public void testCase2() {
        System.out.println("testCase2");
    }
    @Test(retryAnalyzer = RetryExample.class)
    public void testCase3() {
        System.out.println("testCase3");
        Assert.assertTrue(false);
    }
    @Test(retryAnalyzer = RetryExample.class)
    public void testCase4() {
        System.out.println("testCase4");
    }
    @Test(retryAnalyzer = RetryExample.class)
    public void testCase5() {
        System.out.println("testCase5");
        Assert.assertTrue(false);
    }
}