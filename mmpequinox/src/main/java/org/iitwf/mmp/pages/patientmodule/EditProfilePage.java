package org.iitwf.mmp.pages.patientmodule;

import static org.testng.Assert.assertEquals;

import java.io.IOException;


import org.iitwf.selenium.lib.ScreenshotUtil;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;




public class EditProfilePage {

	protected WebDriver driver;
	
	
	private static ScreenshotUtil screenshotUtil;
	
	public EditProfilePage(WebDriver driver)
	{
		this.driver = driver;
		
		if (!driver.getTitle().equals("profile")) {
			throw new IllegalStateException("This is not profile Page," +
					" current page is: " + driver.getCurrentUrl());
			
	}
		screenshotUtil = new ScreenshotUtil(driver);	
		}
	


	public String editAllFields() throws InterruptedException, IOException
	{
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.id("Ebtn")));
		action.click();
      
		
		//Fname Logic
		WebElement fnameWE=driver.findElement(By.id("fname"));
		action.moveToElement(fnameWE);
		action.sendKeys(fnameWE,Keys.CLEAR);
		String expectedFName = JavaUtility.generateRandomString("Kanchu");
		action.sendKeys(fnameWE,expectedFName);
		String actualFName = fnameWE.getDomProperty("value");
		action.perform();
		String screenshotPathfname = screenshotUtil.captureScreenshot("FirstName Page1");      
		
		//Lname Logic
		WebElement lnameWE=driver.findElement(By.id("lname"));
		action.moveToElement(lnameWE);
		action.sendKeys(lnameWE,Keys.CLEAR);
		String expectedLName = JavaUtility.generateRandomString("Chetu");
		action.sendKeys(lnameWE,expectedLName);
		String actualLName = lnameWE.getDomProperty("value");
		action.perform();
		String screenshotPathlname = screenshotUtil.captureScreenshot("LastName Page1");     
		
		//Age Logic
		WebElement ageWE = driver.findElement(By.id("age"));
		action.moveToElement(ageWE);
		action.sendKeys(ageWE,Keys.CLEAR);
		String ageExpected = JavaUtility.generateRandomDigits(10,99)+"";
		String ageActual = ageWE.getDomProperty("value");
		action.sendKeys(ageActual,ageExpected);
		action.perform();
		String screenshotPath1 = screenshotUtil.captureScreenshot("age textbox");     	

		WebElement saveButton = driver.findElement(By.id("Sbtn"));
		action.moveToElement(saveButton);
		action.click(saveButton);
		action.perform();
		String screenshotPath2 = screenshotUtil.captureScreenshot("save button textbox");  
		return actualFName;



	}
	public  void alretTest() throws IOException {
		    Alert alrt=driver.switchTo().alert();
			String text=alrt.getText();
			assertEquals(text, "Your Profile has been updated.");
			alrt.accept();
			String screenshotPath3 = screenshotUtil.captureScreenshot("alret ");  
	}
		
	
}
