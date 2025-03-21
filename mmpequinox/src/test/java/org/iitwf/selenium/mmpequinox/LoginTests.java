package org.iitwf.selenium.mmpequinox;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;


import org.iitwf.mmp.pages.patientmodule.HomePage;

import org.iitwf.mmp.pages.patientmodule.JavaUtility;

import org.iitwf.mmp.pages.patientmodule.MMPUtility;
import org.iitwf.selenium.lib.ExcelUtils;
import org.iitwf.selenium.lib.FrameworkLibrary;
import org.iitwf.selenium.lib.ScreenshotUtil;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

public class LoginTests extends FrameworkLibrary {
 

	private ExtentTest extentTest;

	@Test(priority=1)
	public void testLogin()
	{
		extentTest = extentReports.createTest("######## Valid Login credentials ########"); 
		MMPUtility mmpUtil = new MMPUtility(driver);
		extentTest.info("Launching Browser");
		launchBrowser(prop.getProperty("patient_url"));
		mmpUtil.login(prop.getProperty("patient_username"),prop.getProperty("patient_password"));
		extentTest.info("Entered Valid Login Credentials");
		String actualText = driver.findElement(By.xpath("//h3[normalize-space()='Patient Portal']")).getText();
		extentTest.info("Getting actual text - " + " " + actualText);
		String expectedText = "Patient Portal";
		extentTest.info("Expected text - " + " " + expectedText);
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualText, expectedText);
		
		sa.assertAll();
		
		
	}
	

	@Test(priority=2)
 	public void testInvalidLogin()
	{
		 
		extentTest = extentReports.createTest("######## Invalid Login credentials ########");
		MMPUtility mmpUtil = new MMPUtility(driver);
		extentTest.info("Launching Browser");
		launchBrowser(prop.getProperty("patient_url"));
		mmpUtil.login("patient_username","patient_password");
		extentTest.info("Entered Invalid Login Credentials");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Wait for up to 10 seconds
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		String actualText = alert.getText();
		extentTest.info("Getting actual text - " + " " + actualText);
		alert.accept();
		String expectedText = "Wrong username and password.";
		extentTest.info("Expected text - " + " " + expectedText);
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualText.trim(), expectedText.trim());
		sa.assertAll();
	}


	@DataProvider(name="DP")

	public String[][] feedData() throws IOException
	{
		String inputData[][] = ExcelUtils.getCellData("mmpdata.xlsx");
		return inputData;
	}

	

	@DataProvider(name="DBDP")
	public String[][] feedDBData() throws IOException, ClassNotFoundException, SQLException
	{
		String inputData[][] = JavaUtility.getDBValues("root","root","mmp","login","localhost" );
		return inputData;
	}

	@Test(dataProvider="DBDP")
	public void testLoginDB(String username,String password) throws Exception
	{
		extentTest = extentReports.createTest("############Validate LogIn Tests########");
		launchBrowser(prop.getProperty("patient_url")); 
		MMPUtility mmpUtil = new MMPUtility(driver);
		launchBrowser(prop.getProperty("patient_url"));
		mmpUtil.login(username,password);
		String actualText = driver.findElement(By.xpath("//h3[normalize-space()='Patient Portal']")).getText();
		ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);	
		String screenshotPath = screenshotUtil.captureScreenshot("Home_Page");
		extentTest.addScreenCaptureFromPath(screenshotPath,"Navigation_To_Home_Page");
		String expectedText = "Patient Portal";
		Assert.assertEquals(actualText, expectedText);
	}




}
