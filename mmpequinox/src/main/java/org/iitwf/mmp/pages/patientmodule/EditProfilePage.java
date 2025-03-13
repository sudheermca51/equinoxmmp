package org.iitwf.mmp.pages.patientmodule;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class EditProfilePage {
	
	public String expectedFName;

	protected WebDriver driver;
	private By editButton=By.id("Ebtn");
	private By fname=By.id("fname");
	private By age=By.xpath("(//input[@id='age'])[1]");
	private By save=By.id("Sbtn");
	private By actualFN=By.xpath("//tbody/tr[2]/td[1]");
	
	//@FindBy(id="Ebtn")
	//private WebElement editbutton;
	
	
	public EditProfilePage(WebDriver driver)
	{
		this.driver = driver;
		if (!driver.getTitle().equals("profile")) {
			throw new IllegalStateException("This is not Profile Page," +
					" current page is: " + driver.getCurrentUrl());
		}
		//PageFactory.initElements(driver, this);
	}
	public String editAllFields()
	{
		Actions action = new Actions(driver);


		action.moveToElement(driver.findElement(editButton));
        action.click();

		//Fname Logic
		WebElement fnameWE = driver.findElement(fname);
		action.moveToElement(fnameWE);

		action.sendKeys(fnameWE,Keys.CLEAR);
		String expectedName = JavaUtility.generateRandomString("QAAUT");
		action.sendKeys(fnameWE,expectedName);
		action.perform();
		String expectedFName= fnameWE.getDomProperty("value");

		action.sendKeys(Keys.CLEAR);
		expectedFName = JavaUtility.generateRandomString("QAAUT");

		System.out.println(expectedFName);
		action.sendKeys(expectedFName);

		action.sendKeys(expectedFName);
		System.out.println("ExpectedFName :" + expectedFName);
		action.perform();
		//Age Logic
		WebElement ageWE = driver.findElement(age);
		action.moveToElement(ageWE);

		action.sendKeys(ageWE,Keys.CLEAR);
		String ageExpected = JavaUtility.generateRandomDigits(11,35)+"";
		action.sendKeys(ageWE,ageExpected);
		String ageActual = ageWE.getDomProperty("value");

		action.sendKeys(Keys.CLEAR);
		String agesExpected = JavaUtility.generateRandomDigits(30,99)+"";

		String agesActual = ageWE.getDomProperty("value");
		action.sendKeys(ageActual,ageExpected);

		System.out.println("Expected Age :" + agesExpected);
		action.sendKeys(agesExpected);
		
//		 String ageActual = ageWE.getDomProperty("value");
//			action.sendKeys(ageActual,ageExpected);
		action.perform();


		WebElement saveButton = driver.findElement(save);
		action.moveToElement(saveButton);
		action.click(saveButton);
		action.perform();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.alertIsPresent());
		
       WebDriverWait waitSwitch = new WebDriverWait(driver, Duration.ofSeconds(10));
		waitSwitch.until(ExpectedConditions.alertIsPresent());
		
		Alert alert=driver.switchTo().alert();
		
		alert.accept();

		return expectedFName;

		
		
		
		
		
		
		
		

		
	
		  
		 
//			 String actualFName = fnameWE.getDomProperty("value");
//				System.out.println(actualFName);
//				String actualFName = 	fnameWE.getDomAttribute("value");
//				System.out.println(actualFName);
			
			
		  
		  
		  
		 
		 


	}
	
	public String fetchProfileDetails()
	{
			
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//WebElement actualName =wait.until(ExpectedConditions.visibilityOfElementLocated(actualFN));
		String actualFName=driver.findElement(actualFN).getAccessibleName();

		return actualFName;
	
	}
	
	/*public  String editProfileTests()
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
		return actualfnameValue;
	}*/
	 
		public  String handleAlerts()
		{
			Alert alrt = driver.switchTo().alert();
			String alertTxt = alrt.getText();
			alrt.accept();
			return alertTxt;
		}
		
		
	
}
