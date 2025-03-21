package org.iitwf.mmp.pages.patientmodule;

import java.time.Duration;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class EditProfilePage {
	
	public String expectedName;
	public String expectedFName;
	//@FindBy(id="Ebtn")
	//private WebElement editbutton;
	/*private By editButton=By.id("Ebtn");
	private By fname=By.xpath("(//input[@id='fname'])[1]");
	private By age=By.xpath("(//input[@id='age'])[1]");
	private By save=By.id("Sbtn");*/
	private By actualFN=By.xpath("//tbody/tr[2]/td[1]");
	
	protected WebDriver driver;
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
		action.moveToElement(driver.findElement(By.id("Ebtn")));
		action.click();

		//Fname Logic
		WebElement fnameWE = driver.findElement(By.id("fname"));
		action.moveToElement(fnameWE);
		action.sendKeys(fnameWE,Keys.CLEAR);
		String expectedFiName = JavaUtility.generateRandomString("QAAUT");
		action.sendKeys(fnameWE,expectedFiName);
		action.perform();
		String actualFName = fnameWE.getDomProperty("value");
		
		//Age Logic
		WebElement ageWE = driver.findElement(By.id("age"));
		action.moveToElement(ageWE);
		action.sendKeys(ageWE,Keys.CLEAR);
		String ageExpected = JavaUtility.generateRandomDigits(30,99)+"";
		action.sendKeys(ageWE,ageExpected);
		action.perform();
		String ageActual = ageWE.getDomProperty("value");

		WebElement saveButton = driver.findElement(By.id("Sbtn"));
		action.moveToElement(saveButton);
		action.click(saveButton);
		action.perform();
        
		//WebDriverWait wait2 = new WebDriverWait(driver,Duration.ofSeconds(25));
		//wait2.until(ExpectedConditions.domAttributeToBe(fnameWE, "value", expectedFName));
		//JavascriptExecutor js = ((JavascriptExecutor)driver);
		//js.executeScript("arguments[0].click();",fnameWE);
		//WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(15));
		//wait1.until(ExpectedConditions.domAttributeToBe(fnameWE, "value", expectedFName));
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.alertIsPresent());
		
		Alert alrt = driver.switchTo().alert();
		System.out.println("Alert Text " + alrt.getText());
		alrt.accept();
		
		/*WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.domAttributeToBe(fnameWE, "value", expectedFName));
			
			 String actualFName = fnameWE.getDomProperty("value");
				System.out.println(actualFName);
			String actualFName = 	fnameWE.getDomAttribute("value");*/				
	
    expectedName=driver.findElement(actualFN).getAccessibleName();
	
	return actualFName;
	}
	/*public  void editProfileTests()
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
	}*/
	 
		public  String handleAlerts()
		{
			Alert alrt = driver.switchTo().alert();
			String alertTxt = alrt.getText();
			alrt.accept();
			return alertTxt;
		}
		
		public String invalidEditAllFields()
		{
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(By.id("Ebtn")));
			action.click();

			
			WebElement fnameWEb = driver.findElement(By.id("fname"));
			action.moveToElement(fnameWEb);
			action.sendKeys(fnameWEb,Keys.CLEAR);
			expectedFName = "abc1234";
			action.sendKeys(fnameWEb,expectedFName);
			action.perform();
			String actualFName = fnameWEb.getDomProperty("value");
			
			WebElement saveButton = driver.findElement(By.id("Sbtn"));
			action.moveToElement(saveButton);
			action.click(saveButton);
			action.perform();
	        
			
			String actual = "";
			if(expectedFName.equals(actualFName))
			{
				//System.out.println("please enter only alphabets");
				WebElement nameWE = driver.findElement(By.xpath("(//p[@id='firsterr1'])[1]"));
				actual= nameWE.getText();
				
			}
			return actual;
			
			
			
}
		
}		
		

