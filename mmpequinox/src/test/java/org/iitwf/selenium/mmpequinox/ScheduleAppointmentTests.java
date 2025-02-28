package org.iitwf.selenium.mmpequinox;

import java.io.IOException;
import java.util.HashMap;
import org.iitwf.mmp.pages.patientmodule.HomePage;
import org.iitwf.mmp.pages.patientmodule.MMPUtility;
import org.iitwf.mmp.pages.patientmodule.ScheduleAppointmentPage;
import org.iitwf.selenium.lib.FrameworkLibrary;
import org.iitwf.selenium.lib.ListenerUtility;
import org.iitwf.selenium.lib.ScreenshotUtil;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;


@Listeners(ListenerUtility.class)
public class ScheduleAppointmentTests extends FrameworkLibrary{

	/*
	 * 
	 * Monday,Tuesday,Thursday - 9 pm 
	 * 
	 * 
	Formatted Date:::February/20/2025
	Same Year 
	Same Month -> Click on the date - 2
	----------------------------------------
	Formatted Date:::April/20/2025 - split("/") - [0] - April,[1]- 20,[2]-2025
	Same Year 
	Different Month - Expected Year - April -> Click on next button
	Different Month - Expected Year - December -> till i see the month as December 
	---------------------------------------------------------------------------------
	Formatted Date: April/20/2026
	Different Year - Click on next button till 2026 is displayed 
	Different Month - Click on next button till April is displayed 
	click on 20th as date 
	---------------------------------------------------------------------
	 */

	private ExtentTest extentTest;
	 
	@Test
	public void validateBookAppointmentTests() throws IOException 
	{
        extentTest = extentReports.createTest("############Validate Schedule Appointment Tests########");
        
        launchBrowser(prop.getProperty("patient_url"));
        
		MMPUtility mmpUtil = new MMPUtility(driver);
		mmpUtil.login(prop.getProperty("patient_username"),prop.getProperty("patient_password"));
		HomePage hPage = new HomePage(driver);
 
		hPage.navigatetoAModule("Schedule Appointment");
        extentTest.info("Navigating to Schedule Appointment");
        
        ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);	
        String screenshotPath = screenshotUtil.captureScreenshot("ScheduleAppointment_Page1");
        extentTest.addScreenCaptureFromPath(screenshotPath,"Navigation_To_Schedule_Appointment_Page");

		ScheduleAppointmentPage sPage = new ScheduleAppointmentPage(driver);
		HashMap<String,String> expectedHMap= sPage.bookAppointment(60,"MMMM/d/YYYY","Cardiologist","Charlie");
        extentTest.info("Expected HMap: " + expectedHMap);

		
		HashMap<String,String> actualHMap = hPage.fetchAppointmentDetails();
		extentTest.info("actualHMap : " + actualHMap);
		
		screenshotUtil = new ScreenshotUtil(driver);	
        screenshotPath = screenshotUtil.captureScreenshot("ScheduleAppointment_Page2");
        extentTest.addScreenCaptureFromPath(screenshotPath,"Schedule_Appointment_Completed");

		Assert.assertEquals(actualHMap, expectedHMap,"Booking is unsuccessful");		 
	}
}


