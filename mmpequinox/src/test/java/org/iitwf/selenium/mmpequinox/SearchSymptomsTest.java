package org.iitwf.selenium.mmpequinox;

import java.io.IOException;

import org.iitwf.mmp.pages.patientmodule.HomePage;
import org.iitwf.mmp.pages.patientmodule.MMPUtility;
import org.iitwf.mmp.pages.patientmodule.SearchSymptom;
import org.iitwf.selenium.lib.FrameworkLibrary;
import org.iitwf.selenium.lib.ScreenshotUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;


	public class SearchSymptomsTest extends FrameworkLibrary {

		SearchSymptom symp;
		private ExtentTest extentTest;

		@Test
		public void validateSearchSymptomTest() throws IOException {

			extentTest = extentReports.createTest("#####Validate Search Symptom Test#####");

			launchBrowser(prop.getProperty("patient_url"));

			MMPUtility mmpUtil = new MMPUtility(driver);
			mmpUtil.login(prop.getProperty("patient_username"), prop.getProperty("patient_password"));
			HomePage hPage = new HomePage(driver);
			extentTest.info("Clicking on Search Symptoms Tile");
			hPage.navigatetoAModule("Search Symptoms");
			symp = new SearchSymptom(driver);
			String symptom = symp.symptomText("fever");
			extentTest.info("Entering Symptom: " +symptom);
			ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);
			String screenshotPath = screenshotUtil.captureScreenshot("Search_Symptoms_page_Step 1");
			extentTest.addScreenCaptureFromPath(screenshotPath, "Search Symptoms Landing page");
			symp.search();
			screenshotUtil = new ScreenshotUtil(driver);
			screenshotPath = screenshotUtil.captureScreenshot("Search Symptoms page_Step 2");
			extentTest.addScreenCaptureFromPath(screenshotPath, "Search Symptom results");
			String actualRes = symp.validateText();
			String expectedRes = "fever";
			Assert.assertEquals(actualRes, expectedRes);

		}
	@Test
	public void validateSearchSymptomInvalidTest() throws IOException {

			extentTest = extentReports.createTest("Validate Search Symptom Invalid Test");

			launchBrowser(prop.getProperty("patient_url"));

			MMPUtility mmpUtil = new MMPUtility(driver);
			mmpUtil.login(prop.getProperty("patient_username"), prop.getProperty("patient_password"));
			HomePage hPage = new HomePage(driver);
			extentTest.info("Clicking on Search Symptoms Tile");
			hPage.navigatetoAModule("Search Symptoms");
			symp = new SearchSymptom(driver);
			String symptom = symp.symptomText("xyz");
			extentTest.info("Entering Symptom: " +symptom);
			ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);
			String screenshotPath = screenshotUtil.captureScreenshot("Search_Symptoms_page_Step 1");
			extentTest.addScreenCaptureFromPath(screenshotPath, "Search Symptoms Landing page");
			symp.search();
			screenshotUtil = new ScreenshotUtil(driver);
			screenshotPath = screenshotUtil.captureScreenshot("Search Symptoms page_Step 2");
			extentTest.addScreenCaptureFromPath(screenshotPath, "Search Symptom results");
			if(driver.getPageSource().contains("xyz")) {

				System.out.println("Test case failed");
			}

			else {

				System.out.println("Test case passed");
			}

		}

	}
