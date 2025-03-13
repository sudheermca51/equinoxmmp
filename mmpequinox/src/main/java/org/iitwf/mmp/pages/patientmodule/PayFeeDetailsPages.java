package org.iitwf.mmp.pages.patientmodule;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.iitwf.selenium.lib.ScreenshotUtil;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PayFeeDetailsPages {
	protected WebDriver driver ;
	private By feeSSNXpath = By.xpath("//input[@id='search']");
	private By searchButtonXpath = By.xpath("//input[@value='search']");
	private By searchNameXpath = By.xpath("//a[normalize-space()='John']");
	private By createFeeXpath = By.xpath("//input[@value='Create Fee']");
	//private By dateXpath = By.xpath("//select[@id='app_date']");
	private By selectSerXpath = By.xpath("//select[@id='service']");
	private By amountXpath = By.xpath("(//input[@value='11'])[1]");
	private By submitButtonXpath = By.xpath("(//input[@value='submit'])[1]");
	//private By waitXpath = By.id("alertButton");
	public By outputMsgXpath = By.xpath("//li[contains(text(),'vaccination :......$11')]");
	
	private ScreenshotUtil screenshotUtil;
	
	public PayFeeDetailsPages(WebDriver driver) {
		this.driver = driver;
		if (!driver.getTitle().equals("Schedule Appointments")) {
			throw new IllegalStateException("This is not Fee Details Page," +
					" current page is: " + driver.getCurrentUrl());
		
	}
	screenshotUtil = new ScreenshotUtil(driver);
	}
	
	public HashMap<String, String> payPage() throws IOException
	{
		HashMap<String,String> expectedHMap = new HashMap<String,String>();
		
		WebElement feeSSNWE = driver.findElement(feeSSNXpath);
		feeSSNWE.sendKeys("111222339");
		//expectedHMap.put("ssn",feeSSNWE.getAttribute("value"));
		
		WebElement buttonWE = driver.findElement(searchButtonXpath);
		buttonWE.click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(searchNameXpath));
		element.click();
		
		screenshotUtil.captureScreenshot("PayFeeDetails_Step2_1");
		
		
		WebElement createWE = driver.findElement(createFeeXpath);
		createWE.click();
		
		//Select dateSelect = new Select(driver.findElement(dateXpath));
		//dateSelect.selectByVisibleText("06/07/2023");
		//expectedHMap.put("date",dateSelect.getFirstSelectedOption().getText());
		
		Select serviceSelect = new Select(driver.findElement(selectSerXpath));
		serviceSelect.selectByVisibleText("vaccination");
		expectedHMap.put("service",serviceSelect.getFirstSelectedOption().getText());
		
		screenshotUtil.captureScreenshot("PayFeeDetails_Step2_2");
		
		WebElement amountWE = driver.findElement(amountXpath);
		amountWE.getText();
		expectedHMap.put("amount","$"+ amountWE.getDomProperty("value"));
		
		WebDriverWait submitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement submitWE =submitWait.until(ExpectedConditions.visibilityOfElementLocated(submitButtonXpath));
		submitWE.click();
		
		WebDriverWait waitSwitch = new WebDriverWait(driver, Duration.ofSeconds(10));
		waitSwitch.until(ExpectedConditions.alertIsPresent());
		
		Alert alert=driver.switchTo().alert();
		
		alert.accept();
		
		System.out.println("expectedHMap ::" + expectedHMap);
		return expectedHMap;
		
	}
	public HashMap<String,String> fetchPayFeePageData() throws IOException 
	{
			HashMap<String,String> actualHMap = new HashMap<String,String>();
			
			WebElement outputWE = driver.findElement(outputMsgXpath);
			String expectedoutputMsg = outputWE.getText();
			
			screenshotUtil.captureScreenshot("PayFeeDetails_Step2_3");
			
			System.out.print("Hiiiiiii"+expectedoutputMsg);
			String[] splitvalues=expectedoutputMsg.split("\\:......");
			String actualservice = splitvalues[0].trim();
			String actualamount = splitvalues[1];
			
			System.out.println("######################" + actualservice+"--"+ actualamount);
			
			//String expectedDateArr[] =outputMsgXpath.split("/");
			
			actualHMap.put("service",actualservice);
			actualHMap.put("amount",actualamount);
		    System.out.println("actualHMap ::::" + actualHMap);
		    return actualHMap;
			
		   
		    
		    
		    
		  
			 
	}
	
}
