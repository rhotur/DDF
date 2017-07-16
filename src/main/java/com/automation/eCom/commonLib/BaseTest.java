package com.automation.eCom.commonLib;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.lift.find.InputFinder;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseTest {
	
	
	
 protected WebDriver driver;
 
 protected int rowCount;
 protected XSSFSheet sheet;
 protected Properties prop;
 protected ExtentReports report;
 protected ExtentTest test;
 public BaseTest(){
	 report= new ExtentReports("src\\test\\java\\com\\automation\\eCom\\reports\\RegisterReport.html");
 }
 	@BeforeTest
 	@Parameters("Browser")
	public void launchBrowser(String browser){
 		
 		if(browser.equalsIgnoreCase("FF")){
 			System.setProperty("webdriver.gecko.driver", "src\\test\\java\\com\\automation\\eCom\\utils\\geckodriver.exe");
 			driver = new FirefoxDriver();
 		}else if(browser.equalsIgnoreCase("Chrome")){
 			System.setProperty("webdriver.chrome.driver", "src\\test\\java\\com\\automation\\eCom\\utils\\chromedriver.exe");
 			driver = new ChromeDriver();
 		}
		
		driver.manage().window().maximize();
		driver.get("http://demo.nopcommerce.com/");
	}
	@AfterTest
	public void closeBrowser(){
		driver.quit();
	}
	public void readDataFromExcel(String filePath, String sheetName) throws IOException{
		FileInputStream fis = new FileInputStream(filePath);
		XSSFWorkbook wb= new XSSFWorkbook(fis);
		sheet= wb.getSheet(sheetName);
		rowCount=sheet.getLastRowNum();
	}
	 public Boolean IsElementPresent(By locator)
	    {
	        //Set the timeout to something low
	    	driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
	        try
	        {
	            driver.findElement(locator);
	            //If element is found set the timeout back and return true
	            driver.manage().timeouts().implicitlyWait(20000, TimeUnit.MILLISECONDS);
	           // logger.log(LogStatus.INFO, "Elemenet present");
	            return true;
	        }
	        catch (NoSuchElementException e)
	        {
	            //If element isn't found, set the timeout and return false
	        	driver.manage().timeouts().implicitlyWait(20000, TimeUnit.MILLISECONDS);
	        	//logger.log(LogStatus.ERROR, "Elemenet does not exist");
	            return false;
	        }
	    }

}
