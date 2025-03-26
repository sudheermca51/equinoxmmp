package org.iitwf.mmp.pages.patientmodule;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AddPrescription {
	
WebDriver driver;
	
	HashMap<String,String> hMap = new HashMap<String,String>();
	
	By ssn = By.id("search");
	By searchBtn = By.xpath("//input[@value='search']");
	By nameLink = By.xpath("//div[@id='show']/table/tbody/tr/td/a");
	By addPrescriptionBtn = By.xpath("//input[@value='Add Precription']");
	By selectApptDropDown = By.id("app_date");
	By prescriptionName = By.id("exampleInputcardnumber1");
	By prescriptionDesc = By.name("p_desc");
	By submitBtn = By.xpath("//input[@value='submit']");
	By viewHistoryBtn = By.xpath("//button[text()='View History']");
	By pastPrescriptionBtn = By.xpath("//input[@value='Past Prescription']");
	By presMedicine = By.xpath("//ul[@class='list-inline list-users']/li");
	
	public AddPrescription(WebDriver driver) {
		
		this.driver = driver;
	}
	
	public void enterSSN()
	{

		String ssnValue ="514236634";
		driver.findElement(ssn).sendKeys(ssnValue);
		hMap.put("SSN", ssnValue);
	}
	
	public void search() {
		
		driver.findElement(searchBtn).click();
	}
	
	public void name() {
		
		driver.findElement(nameLink).click();
	}
	
	public void addPrescription() {
		
		driver.findElement(addPrescriptionBtn).click();
	}
	
	public void selectAppointmentDate()
	{
	    new Select(driver.findElement(selectApptDropDown)).selectByVisibleText("04/09/2025");
		hMap.put("SelectAppointmentDate", "04/09/2025");
}
	
	public String prescriptionName(String prescription) {
		
		driver.findElement(prescriptionName).sendKeys(prescription);
		return prescription;
		
	}
	
	public String prescriptionDescription(String description) {
		
		driver.findElement(prescriptionDesc).sendKeys(description);
		return description;
	}
	
	public void submit() {
		
		driver.findElement(submitBtn).click();
	}
	
	public void viewHistory() {
		
		driver.findElement(viewHistoryBtn).click();
	}
	
	public void pastPrescription() {
		
		driver.findElement(pastPrescriptionBtn).click();
	}
	
	public String prescriptionMedicine() {
		
		WebElement presWE = driver.findElement(presMedicine);
		String pres = presWE.getText();
		System.out.println("Printing the Prescription: " +pres);
		return pres;

	}

}
