package org.iitwf.selenium.mmpequinox;

import java.io.IOException;
import java.time.Duration;

import org.iitwf.mmp.pages.patientmodule.AddPrescription;
import org.iitwf.mmp.pages.patientmodule.HomePage;
import org.iitwf.mmp.pages.patientmodule.MMPUtility;
import org.iitwf.selenium.lib.FrameworkLibrary;
import org.iitwf.selenium.lib.ScreenshotUtil;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

public class AddPrescriptionTests extends FrameworkLibrary {
	
	AddPrescription prescription;
	private ExtentTest extentTest;
	
	@Test
	public void validatePrescription() throws IOException, InterruptedException
	{
		extentTest = extentReports.createTest("######## Prescription Test ########");
					
		launchBrowser(prop.getProperty("doc_url"));
		MMPUtility mmpUtil = new MMPUtility(driver);
		mmpUtil.docLogin(prop.getProperty("doc_username"),prop.getProperty("doc_password"));
		prescription = new AddPrescription(driver);
		//extentTest.info("Entering SSN number");
		Thread.sleep(2000);
		prescription.enterSSN();
		prescription.search();
		ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);	
        String screenshotPath = screenshotUtil.captureScreenshot("Patient_info");
        extentTest.addScreenCaptureFromPath(screenshotPath,"Displaying_Patient_info");
		prescription.name();
		prescription.addPrescription();
		prescription.selectAppointmentDate();
		String presName = prescription.prescriptionName("Diclofenac_0319");
		extentTest.info("Entering Prescription Name -" + " " + presName);
		String presDesc = prescription.prescriptionDescription("For Knee pain");
		extentTest.info("Entering Prescription Description -" + " " + presDesc);
		screenshotUtil = new ScreenshotUtil(driver);	
        screenshotPath = screenshotUtil.captureScreenshot("Prescription_info");
        extentTest.addScreenCaptureFromPath(screenshotPath,"Displaying_Prescription_info");
        prescription.submit();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Wait for up to 10 seconds
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Alert presAlert = driver.switchTo().alert();
		String docactual = presAlert.getText();
		extentTest.info("Getting Actual Alert Text - " + " " + docactual);
		String docexpected ="Prescription has been Added.";
		Assert.assertEquals(docactual, docexpected);
		alert.accept();
    	extentTest.info("Launching Patient Url ");
		launchBrowser(prop.getProperty("patient_url"));
		mmpUtil.login(prop.getProperty("patient2_username"), prop.getProperty("patient2_password"));
		HomePage hPage = new HomePage(driver);
		hPage.navigatetoAModule("Profile");
		prescription.viewHistory();
		prescription.pastPrescription();
		String actual = prescription.prescriptionMedicine();
		String expected = "Diclofenac_0319";
		extentTest.info("Prescription_information_for_Patient ");
		screenshotUtil = new ScreenshotUtil(driver);	
        screenshotPath = screenshotUtil.captureScreenshot("Prescription info_in_patient");
        extentTest.addScreenCaptureFromPath(screenshotPath,"Displaying_Prescription_info_in_patient");
		if(actual.contains(expected)) {
			
			System.out.println("Test case passed");
		}
		
	}
}
