package org.iitwf.selenium.mmpequinox;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.iitwf.mmp.pages.patientmodule.AcceptPatient;
import org.iitwf.mmp.pages.patientmodule.HomePage;
import org.iitwf.mmp.pages.patientmodule.MMPUtility;
import org.iitwf.selenium.lib.FrameworkLibrary;
import org.iitwf.selenium.lib.ScreenshotUtil;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

public class AcceptPatientTests extends FrameworkLibrary {
	
	AcceptPatient accpatient;
	private ExtentTest extentTest;
	
	@Test
	public void validatePatientStatus() throws IOException, InterruptedException {
		
		extentTest = extentReports.createTest("##### Accept Patient Test ####");
		
		launchBrowser(prop.getProperty("patient_url"));
		MMPUtility mmpUtil = new MMPUtility(driver);
		mmpUtil.login(prop.getProperty("patient3_username"), prop.getProperty("patient3_password"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// Wait for up to 10 seconds
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		extentTest.info("Switching to Alert");
		alert = driver.switchTo().alert();
//		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        File destination = new File("alert_screenshot.png");
//        FileHandler.copy(screenshot, destination);
       String actual = alert.getText().trim();
       System.out.println("printing the actual text:" +actual);
       String expected = "Admin Approval is pending.";
       Assert.assertEquals(actual, expected);
       alert.accept();
       Thread.sleep(3000);
       extentTest.info("Launching Admin Url ");
	   launchBrowser(prop.getProperty("admin_url"));
		mmpUtil.adminLogin(prop.getProperty("admin_username"), prop.getProperty("admin_password"));
		accpatient = new AcceptPatient(driver);
		WebElement users = driver.findElement(By.xpath("//span[normalize-space()='Users']"));
		users.click();
		WebElement nameLink = driver.findElement(By.xpath("//tbody/tr[3007]/td/a"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", nameLink);
		nameLink.click();
		accpatient.StatusDropDown();
		ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);	
        String screenshotPath = screenshotUtil.captureScreenshot("Selecting_Accepted");
        extentTest.addScreenCaptureFromPath(screenshotPath,"Selecting Accepted option in Status dropdown");
		accpatient.Submit();
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));  // Wait for up to 10 seconds
		Alert alertAdmin = wait.until(ExpectedConditions.alertIsPresent());
		extentTest.info("Switching to Alert");
		alertAdmin = driver.switchTo().alert();
        String act = alertAdmin.getText().trim();
        String exp = "USER has been updated.";
        Assert.assertEquals(act, exp);
//		ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);	
//       String screenshotPath = screenshotUtil.captureScreenshot("User updated_alert");
//       extentTest.addScreenCaptureFromPath(screenshotPath,"User updated message alert");

		
	}
	

}
