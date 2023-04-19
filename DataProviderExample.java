package dataprovider;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;

import java.io.IOException;

public class DataProviderExample {
    public static WebDriver driver;
    @DataProvider(name = "test-data")
    public Object[][] withDataProvider() throws IOException {
        return new Object[][]{
                {"Selenium","Nagpur"},{"Java","Pune"},{"Python","Mumbai"}
        };
    }



    @Parameters({"browserName"})
    @BeforeMethod
    public void beforeMethod(String browserName) throws Exception {
        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\sai\\Downloads\\chromedriver_win32\\chromedriver.exe");
            driver = new ChromeDriver(options);
        } else if (browserName.equalsIgnoreCase("Firefox")) {
            System.setProperty("webdriver.chrome.driver", "firefoxdriver.exe");
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.chrome.driver", "edge.exe");
            driver = new EdgeDriver();
        } else if (browserName.equalsIgnoreCase("Ie")) {
            System.setProperty("webdriver.chrome.driver", "ie.exe");
            driver = new InternetExplorerDriver();
        } else {
            throw new Exception("correct your browser name");
        }
    }

    @Parameters({"courseName", "cityName"})
    @Test(dataProvider = "test-data")
    public void testCase1(String courseName, String cityName) throws InterruptedException {
        driver.get("https://www.google.com/");
        driver.manage().window().maximize();
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys(courseName + "  " + cityName);
        element.sendKeys(Keys.ENTER);

    }
    @AfterMethod
    void afterMethod(){
        driver.close();
    }
}




