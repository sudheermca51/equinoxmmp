package org.iitwf.selenium.mmpequinox;



import org.iitwf.mmp.pages.patientmodule.HomePage;
import org.iitwf.mmp.pages.patientmodule.MMPUtility;
import org.iitwf.mmp.pages.patientmodule.SearchSymptomPage;
import org.iitwf.selenium.lib.FrameworkLibrary;
import org.iitwf.selenium.lib.ScreenshotUtil;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

public class SearchSymptomTests extends FrameworkLibrary{
	private ExtentTest extentTest ;
	
	@Parameters({"srchsymptom","invalidsrchsymptom"})
	@Test
	public void validateSearchSymptomBox(String srchsymptom,String invalidsrchsymptom) throws Exception  {
		extentTest = extentReports.createTest("############Validate Search Symptom Tests########");
		String rMsg = "";
		launchBrowser(prop.getProperty("patient_url"));
		MMPUtility mmpUtil = new MMPUtility(driver);
		mmpUtil.login(prop.getProperty("patient_username"),prop.getProperty("patient_password"));
		HomePage hPage = new HomePage(driver);
		hPage.navigatetoAModule("Search Symptoms");
		extentTest.info("Navigating to Schedule Appointment");
		SearchSymptomPage srchsymptomobj = new SearchSymptomPage(driver);	
		rMsg = srchsymptomobj.searchSymptom(srchsymptom);
		ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);	
		String screenshotPath = screenshotUtil.captureScreenshot("Valid_Search_Symptom");
        extentTest.addScreenCaptureFromPath(screenshotPath,"Navigation_To_Valid_Search_Symptom");
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(rMsg.isEmpty(), "Failed to search symptom");
		screenshotUtil = new ScreenshotUtil(driver);
		rMsg = srchsymptomobj.invalidsearchSymptom(invalidsrchsymptom);
		screenshotPath = screenshotUtil.captureScreenshot("Invalid_Search_Symptom");
        extentTest.addScreenCaptureFromPath(screenshotPath,"Navigation_To_Invalid_Search_Symptom");
		sa.assertTrue(rMsg.isEmpty(), "Failed to search symptom");
		
	}

}
