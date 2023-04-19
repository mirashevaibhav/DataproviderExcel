package rerunfailtestscript;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryExample implements IRetryAnalyzer {
    private int retryCount = 0;
    private static int MaxRetryCount = 3;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (retryCount < MaxRetryCount) {
            retryCount++;
            return true;
        }
        return false;
    }
}
