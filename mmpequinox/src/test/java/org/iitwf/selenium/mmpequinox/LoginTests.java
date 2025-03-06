package org.iitwf.selenium.mmpequinox;

import java.io.IOException;

import org.iitwf.mmp.pages.patientmodule.MMPUtility;
import org.iitwf.selenium.lib.ExcelUtils;
import org.iitwf.selenium.lib.FrameworkLibrary;
import org.iitwf.selenium.lib.ScreenshotUtil;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

public class LoginTests extends FrameworkLibrary {
//			id="username"
//			id="password"
//			name="submit"
//			//h3[normalize-space()='Patient Portal']
//
//			String expectedText = "Patient Portal";
	
	private ExtentTest extentTest;
	
	@DataProvider(name="DP")
	public String[][] feedData() throws IOException
	{
		String inputData[][] = ExcelUtils.getCellData("mmpdata.xlsx");
		return inputData;
	}
	
	@Test(dataProvider="DP")
	public void testLogin(String username,String password) throws Exception
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
