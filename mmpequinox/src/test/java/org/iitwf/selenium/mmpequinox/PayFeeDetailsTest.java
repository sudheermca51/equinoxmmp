package org.iitwf.selenium.mmpequinox;

import java.io.IOException;
import java.util.HashMap;


import org.iitwf.mmp.pages.patientmodule.PayFeeDetailsPages;
import org.iitwf.mmp.pages.patientmodule.HomePage;
import org.iitwf.mmp.pages.patientmodule.MMPUtility;
import org.iitwf.selenium.lib.FrameworkLibrary;
import org.iitwf.selenium.lib.ScreenshotUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

public class PayFeeDetailsTest extends FrameworkLibrary{

	private ExtentTest extentTest;
	@Test
	public void validatePayFeeDetails() throws IOException 
	{
		extentTest = extentReports.createTest("############Validate PayFeeDetails Tests########");
		
		launchBrowser(prop.getProperty("admin_url")); 
		MMPUtility mmpUtil = new MMPUtility(driver);
		mmpUtil.login(prop.getProperty("admin_username"),prop.getProperty("admin_password"));
		HomePage hPage = new HomePage(driver);
		hPage.navigatetoAModule("Patients");
		extentTest.info("Navigating to Patients Module");
		
		ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);	
        String screenshotPath = screenshotUtil.captureScreenshot("PayFeeDetails_Page1");
        extentTest.addScreenCaptureFromPath(screenshotPath,"Navigation_To_PayFeeDetails_Page");
		
		PayFeeDetailsPages  payfeeObj = new PayFeeDetailsPages (driver);
		HashMap<String, String> expectedHMap = payfeeObj.payPage();
		extentTest.info("Expected HMap: " + expectedHMap);
		
		mmpUtil.launchBrowser(prop.getProperty("patient_url"));
		mmpUtil.login(prop.getProperty("patient_username"),prop.getProperty("patient_password"));
		HomePage homePage = new HomePage(driver);
		homePage.navigatetoAModule("Fees");
		extentTest.info("Navigating to Fees Module");
		
		screenshotUtil = new ScreenshotUtil(driver);	
        screenshotPath = screenshotUtil.captureScreenshot("PayFeeDetails_Page2");
        extentTest.addScreenCaptureFromPath(screenshotPath,"PayFeeDetails_Completed");
        
		HashMap<String, String> actualHMap = payfeeObj.fetchPayFeePageData();
		extentTest.info("Actual HMap: " + actualHMap);
		
		Assert.assertEquals(expectedHMap,actualHMap,"Paying Fee is unsuccessful");
	}
	
	
}
