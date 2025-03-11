package org.iitwf.selenium.mmpequinox;

import java.io.IOException;
import java.time.Duration;

import org.iitwf.mmp.pages.patientmodule.HomePage;
import org.iitwf.mmp.pages.patientmodule.MMPUtility;
import org.iitwf.mmp.pages.patientmodule.Messages;
import org.iitwf.selenium.lib.FrameworkLibrary;
import org.iitwf.selenium.lib.ScreenshotUtil;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

public class MessageTests extends FrameworkLibrary{
	
	Messages msg;
	private ExtentTest extentTest;
	
	@Test
	public void sendMessage(Alert alert) throws IOException
	{
		extentTest = extentReports.createTest("######## Messages Test ########");
		
		launchBrowser(prop.getProperty("patient_url"));
		MMPUtility mmpUtil = new MMPUtility(driver);
		mmpUtil.login(prop.getProperty("patient_username"),prop.getProperty("patient_password"));
		HomePage hPage = new HomePage(driver);
		extentTest.info("clicking on Messages Tile");
		hPage.navigatetoAModule("Messages");
		
		msg = new Messages(driver);

		
		extentTest.info("Entering Contact Reason");
		msg.contactReason("Running High Fever");
		
		extentTest.info("Entering message details");
		msg.message("Need to get an appointment with Doctor");
		

		//extentTest.info("Entering Contact Reason");
		String reason = msg.contactReason("Running High Fever Since Few Days");
		extentTest.info("Entering Contact Reason -" + " " + reason);
		String message = msg.message("Need to get an appointment with Doctor as soon as possible");
		extentTest.info("Entering Message Details -" + " " + message);
		ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);	
        String screenshotPath = screenshotUtil.captureScreenshot("Message_alert");
        extentTest.addScreenCaptureFromPath(screenshotPath,"Message sent successfuly alert");

		alert.accept();  
		
		ScreenshotUtil screenshotUtil1 = new ScreenshotUtil(driver);	
        String screenshotPath1 = screenshotUtil1.captureScreenshot("Message_alert");
        extentTest.addScreenCaptureFromPath(screenshotPath1,"Message sent successfuly alert");



	}

}
