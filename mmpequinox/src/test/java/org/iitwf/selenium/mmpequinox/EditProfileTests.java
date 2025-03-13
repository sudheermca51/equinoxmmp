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

	@Test
	public void validateEditFuncAllFields() throws IOException
	{
		extentTest = extentReports.createTest("############Validate Edit Profile Tests########");

	    launchBrowser(prop.getProperty("patient_url"));
	    
		MMPUtility mmpUtil = new MMPUtility(driver);
		mmpUtil.login(prop.getProperty("patient_username"),prop.getProperty("patient_password"));
		HomePage hPage = new HomePage(driver);
		hPage.navigatetoAModule("Profile");
		extentTest.info("Navigating to Profile Page");
		
		
		
		
		ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);	
		String screenshotPath = screenshotUtil.captureScreenshot("EditProfilePage_step1");
		extentTest.addScreenCaptureFromPath(screenshotPath,"Edit_Profile_Landing_Page");


		EditProfilePage editProjObj = new EditProfilePage(driver);

		
		
		String expectedFName=editProjObj.editAllFields();
		String actualFName = editProjObj.fetchProfileDetails();
		System.out.println("actualFName"+actualFName);
		System.out.println("expectedFName"+expectedFName);

		String actualFiName = editProjObj.editAllFields();

		String expectedFiName=editProjObj.expectedFName;

		System.out.println(actualFName);
//		String expectedFName=prop.getProperty("patient_username");
		String expectedfName = editProjObj.expectedFName;
		

		extentTest.info("Expected FName::" + expectedFiName);
		extentTest.info("Actual FName::"+  actualFiName );
		
		

		Assert.assertEquals(actualFName, expectedFName,"Profile page is not update successful");

		Assert.assertEquals(actualFName, expectedfName);


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
