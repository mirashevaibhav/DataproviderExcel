package dataprovider;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DataProviderWithExcel {
    public static WebDriver driver;
    @DataProvider(name = "excel-data")
    public Object[][] excelData() throws IOException {
        Object[][] arrayObj = getExcelData("C:\\Users\\sai\\Documents\\sheet1.xlsx","Sheet1");
            return arrayObj;
    }

    public String[][] getExcelData(String fileName, String sheetName) throws IOException {
        String[][] data=null;
            FileInputStream mis = new FileInputStream(fileName);
            XSSFWorkbook wb = new XSSFWorkbook(mis);
            XSSFSheet sh = wb.getSheet(sheetName);
            XSSFRow row = sh.getRow(0);
            int noOfRow = sh.getPhysicalNumberOfRows();
            int noOfCol = row.getLastCellNum();
            Cell cell;
            data = new String[noOfRow - 1][noOfCol];
            for (int i = 1; i < noOfRow; i++) {
                for (int j = 0; j < noOfCol; j++) {
                    row = sh.getRow(i);
                    cell = row.getCell(j);
                    data[i - 1][j] = cell.getStringCellValue();

                }
            }

            return data;
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


    @Test(dataProvider = "excel-data")
    public void testCase1(String courseName, String cityName) throws InterruptedException {
        driver.get("https://www.google.com/");
        driver.manage().window().maximize();
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys(courseName + "  " + cityName);
        element.sendKeys(Keys.ENTER);
        driver.close();
    }
}



