package com.automation.eCom.scripts;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.automation.eCom.commonLib.BaseTest;
import com.relevantcodes.extentreports.LogStatus;

public class Register extends BaseTest{
	public Register() throws IOException{
		readDataFromExcel("src\\test\\java\\com\\automation\\eCom\\resouces\\InputTestData.xlsx","Reg");
		prop= new Properties();
		prop.load(new FileInputStream("src\\test\\java\\com\\automation\\eCom\\OR\\Repo.properties"));
		
	}
	@Test
	public void executeTest() throws InterruptedException{
		System.out.println(rowCount);
		//WebElement regLink=driver.findElement(By.linkText("Register1"));
		
		for(int i=1;i<=rowCount;i++){
			
			test=report.startTest("Register");
			if(IsElementPresent(By.linkText(prop.getProperty("registerLink")))){
				test.log(LogStatus.PASS, "Register Link is present and step is passed");
				driver.findElement(By.linkText(prop.getProperty("registerLink"))).click();
			}else{
				test.log(LogStatus.FAIL, "Register Link is present and step is passed");
			}
			Thread.sleep(5000);
			String personalDetails=driver.findElement(By.xpath(prop.getProperty("yourPersonalDetails"))).getText();
			if(personalDetails.equalsIgnoreCase("Your Personal Details")){
				test.log(LogStatus.PASS, "TExt is matching, step passed");
			}else{
				test.log(LogStatus.FAIL, "Text is  not matching and step failed");
			}
			if(IsElementPresent(By.id("FirstName"))){
				System.out.println("Radio button is present and step passed");
				driver.findElement(By.id("FirstName")).sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
				Thread.sleep(5000);
			}
		}
		
		
	}
	
	
}
