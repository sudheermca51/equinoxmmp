package org.iitwf.selenium.mmpequinox;

import java.io.IOException;

import org.iitwf.mmp.pages.patientmodule.MMPUtility;
import org.iitwf.selenium.lib.FrameworkLibrary;
import org.iitwf.selenium.lib.ScreenshotUtil;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;


	public class SearchSymptoms extends FrameworkLibrary {

		private ExtentTest extentTest;

		@Test
		public void validateSearchSymptomTest() throws IOException {

			extentTest = extentReports.createTest("Validate Search Symptom Test");

			launchBrowser(prop.getProperty("patient_url"));

			MMPUtility mmpUtil = new MMPUtility(driver);
			mmpUtil.login(prop.getProperty("patient_username"), prop.getProperty("patient_password"));
			driver.findElement(By.xpath("//span[normalize-space()='Search Symptoms']")).click();
			extentTest.info("Navigating to Search Symptoms page");

			ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);
			String screenshotPath = screenshotUtil.captureScreenshot("Search Symptoms page_Step 1");
			extentTest.addScreenCaptureFromPath(screenshotPath, "Search Symptoms Landing page");

			//Entering the Symptoms
			driver.findElement(By.id("search")).sendKeys("fever");
			//click on Search button
			driver.findElement(By.xpath("//input[@value='Search']")).click();

			screenshotUtil = new ScreenshotUtil(driver);
			screenshotPath = screenshotUtil.captureScreenshot("Search Symptoms page_Step 2");
			extentTest.addScreenCaptureFromPath(screenshotPath, "Search Symptom results");

			String actualRes = driver.findElement(By.xpath("//table[@class='table']/tbody/tr/td[1]")).getText();
			String expectedRes = "fever";
			Assert.assertEquals(actualRes, expectedRes);

		}
	@Test
	public void validateSearchSymptomInvalidTest() throws IOException {

			extentTest = extentReports.createTest("Validate Search Symptom Invalid Test");

			launchBrowser(prop.getProperty("patient_url"));

			MMPUtility mmpUtil = new MMPUtility(driver);
			mmpUtil.login(prop.getProperty("patient_username"), prop.getProperty("patient_password"));
			driver.findElement(By.xpath("//span[normalize-space()='Search Symptoms']")).click();
			extentTest.info("Navigating to Search Symptoms page");

			ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);
			String screenshotPath = screenshotUtil.captureScreenshot("Search Symptoms page_Step 1");
			extentTest.addScreenCaptureFromPath(screenshotPath, "Search Symptoms Landing page");

			//Entering the Symptoms
			driver.findElement(By.id("search")).sendKeys("xyz");
			//click on Search button
			driver.findElement(By.xpath("//input[@value='Search']")).click();

			screenshotUtil = new ScreenshotUtil(driver);
			screenshotPath = screenshotUtil.captureScreenshot("Search Symptoms Invalid_Step 2");
			extentTest.addScreenCaptureFromPath(screenshotPath, "Search Symptoms Invalid results");

			if(driver.getPageSource().contains("xyz")) {

				System.out.println("Test case failed");
			}

			else {

				System.out.println("Test case passed");
			}

		}

	}
