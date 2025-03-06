package org.iitwf.mmp.pages.patientmodule;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javaprograms.RandomEx;

public class EditProfilePage {
	
	public String expectedFName;

	@FindBy(id="Ebtn")
	private WebElement editbutton;
	
	protected WebDriver driver;
	public EditProfilePage(WebDriver driver)
	{
		this.driver = driver;
		if (!driver.getTitle().equals("profile")) {
			throw new IllegalStateException("This is not Profile Page," +
					" current page is: " + driver.getCurrentUrl());
		}
		PageFactory.initElements(driver, this);
	}
	public String editAllFields()
	{
		Actions action = new Actions(driver);

		action.moveToElement(editbutton);
		action.click();

		//Fname Logic
		WebElement fnameWE = driver.findElement(By.id("fname"));
		action.moveToElement(fnameWE);
		action.sendKeys(Keys.CLEAR);
		expectedFName = JavaUtility.generateRandomString("QAAUT");
<<<<<<< HEAD
		System.out.println(expectedFName);
		action.sendKeys(expectedFName);
=======
		action.sendKeys(expectedFName);
		System.out.println("ExpectedFName :" + expectedFName);
		
		
		
		
>>>>>>> refs/heads/main
		
		action.perform();
		
		

		//Age Logic
		WebElement ageWE = driver.findElement(By.id("age"));
		action.moveToElement(ageWE);
		action.sendKeys(Keys.CLEAR);
		String ageExpected = JavaUtility.generateRandomDigits(30,99)+"";

		String ageActual = ageWE.getDomProperty("value");
		action.sendKeys(ageActual,ageExpected);

		System.out.println("Expected Age :" + ageExpected);
		action.sendKeys(ageExpected);
		
//		 String ageActual = ageWE.getDomProperty("value");
//			action.sendKeys(ageActual,ageExpected);
		

		action.perform();


		WebElement saveButton = driver.findElement(By.id("Sbtn"));
		action.moveToElement(saveButton);
		action.click(saveButton);
		action.perform();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.alertIsPresent());
		
	


		Alert alrt = driver.switchTo().alert();
		System.out.println("Alert Text " + alrt.getText());
		alrt.accept();
		
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
		wait.until(ExpectedConditions.domAttributeToBe(fnameWE, "value", expectedFName));
		
		
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		 js.executeScript("arguments[0].click();",fnameWE);
		 
		
		String actualFName = fnameWE.getDomProperty("value");

		
		  Alert alrt = driver.switchTo().alert(); 
		  System.out.println("Alert Text " + alrt.getText());
		  alrt.accept();
		  
		  wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			wait.until(ExpectedConditions.domAttributeToBe(fnameWE, "value", expectedFName));
			
//			 String actualFName = fnameWE.getDomProperty("value");
//				System.out.println(actualFName);
//				String actualFName = 	fnameWE.getDomAttribute("value");
//				System.out.println(actualFName);
			
			String actualFName = 	fnameWE.getText();
			System.out.println(actualFName);
	  
		  
		  
		  
		 
		 


		return actualFName;

	}
	public  void editProfileTests()
	{
		editbutton.click();
		
		//Update Fname
		
		String expectedfnameValue= RandomEx.generateRandomString("AUTFNAME");
		WebElement fnameWE = driver.findElement(By.id("fname"));
		fnameWE.clear();
		fnameWE.sendKeys(expectedfnameValue);
		
		//Update LName
		
		//Update SSN
		
		//Update Age
		
		
		driver.findElement(By.id("Sbtn")).click();
		handleAlerts();
		
		fnameWE = driver.findElement(By.id("fname"));
		String actualfnameValue= fnameWE.getDomProperty("value");
		
		if(expectedfnameValue.equals(actualfnameValue))
		{
			System.out.println("FName value is updated");
		}
	}
	 
		public  String handleAlerts()
		{
			Alert alrt = driver.switchTo().alert();
			String alertTxt = alrt.getText();
			alrt.accept();
			return alertTxt;
		}
		
		
	
}
