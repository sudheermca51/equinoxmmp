package org.iitwf.selenium.mmpequinox;

import java.io.IOException;

import org.iitwf.mmp.pages.patientmodule.EditProfilePage;
import org.iitwf.mmp.pages.patientmodule.HomePage;
import org.iitwf.mmp.pages.patientmodule.MMPUtility;
import org.iitwf.selenium.lib.FrameworkLibrary;
import org.iitwf.selenium.lib.ScreenshotUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

public class EditProfileTests extends FrameworkLibrary {

	private ExtentTest extentTest;

	@Test(priority=1)
	public void validateEditFuncAllFields() throws IOException
	{
		extentTest = extentReports.createTest("############Validate Edit Profile Tests########");
		MMPUtility mmpUtil = new MMPUtility(driver);
		extentTest.info("Launching Browser");
		launchBrowser(prop.getProperty("patient_url"));
	    mmpUtil.login(prop.getProperty("patient_username"),prop.getProperty("patient_password"));
		HomePage hPage = new HomePage(driver);
		hPage.navigateToAProfileModule("Profile");
		extentTest.info("Navigating to Profile Page");
		
		ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);	
		String screenshotPath = screenshotUtil.captureScreenshot("EditProfilePage_step1");
		extentTest.addScreenCaptureFromPath(screenshotPath,"Edit_Profile_Landing_Page");

		extentTest.info("####################Validate Edit Profile Test######################");
		EditProfilePage editProjObj = new EditProfilePage(driver);
		String actualFName = editProjObj.editAllFields();

		String expectedFName=editProjObj.expectedName;

		screenshotUtil = new ScreenshotUtil(driver);	
		screenshotPath = screenshotUtil.captureScreenshot("EditProfilePage_step2");
		extentTest.addScreenCaptureFromPath(screenshotPath,"Edit_Profile_Landing_Page");

		System.out.println("ActualFName::"+ actualFName);
		System.out.println("ExpectedFName::"+ expectedFName);
		
		extentTest.info("Expected FName::" + expectedFName);
		extentTest.info("Actual FName::"+  actualFName );
		
		
		Assert.assertEquals(actualFName, expectedFName);

	}
   @Test(priority=2)
	public void invalidEditProfileTest() throws IOException 

	{
		extentTest = extentReports.createTest("############InValidate Edit Profile Tests########");
		MMPUtility mmpUtil = new MMPUtility(driver);
		extentTest.info("Launching Browser");
	    launchBrowser(prop.getProperty("patient_url"));
	    mmpUtil.login(prop.getProperty("patient_username"),prop.getProperty("patient_password"));
		HomePage hPage = new HomePage(driver);
		hPage.navigateToAProfileModule("Profile");
		extentTest.info("Navigating to Profile Page");

		ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);	
		String screenshotPath = screenshotUtil.captureScreenshot("EditProfilePage_step3");
		extentTest.addScreenCaptureFromPath(screenshotPath,"Edit_Profile_Landing_Page");

		extentTest.info("####################InValidate Edit Profile Test#####################");
		EditProfilePage editProjObj = new EditProfilePage(driver);
		String errorMsg = editProjObj.invalidEditAllFields();

		screenshotUtil = new ScreenshotUtil(driver);	
		screenshotPath = screenshotUtil.captureScreenshot("EditProfilePage_step4");
		extentTest.addScreenCaptureFromPath(screenshotPath,"Edit_Profile_Landing_Page");

		System.out.println("Error Message is:"+ errorMsg );

		extentTest.info("Error Message is::" +errorMsg );

   }
	
}




/**
 * 1. update the firstname by passing randomtext value.
 * 	  expected and actual 
 *    compare expected vs actual
 *  
 * 2. LAstName
 * 3. SSN
 * 4. Address
 * 
 * SoftAssert 
 * 
 * FluentWait
 * Breakpoint by modifying the logic
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
