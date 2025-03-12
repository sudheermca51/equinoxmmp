package org.iitwf.mmp.pages.patientmodule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchSymptom {
	
	WebDriver driver;
	
	By symptomText = By.id("search");
	By searchBtn = By.xpath("//input[@value='Search']");
	By displayedResult = By.xpath("//table[@class='table']/tbody/tr/td[1]");
	
	public SearchSymptom(WebDriver driver) {
		
		this.driver = driver;
	}
	
	public String symptomText(String symptom)
	{
		driver.findElement(symptomText).sendKeys(symptom);
		return symptom;
	}
	
	public void search()
	{
		driver.findElement(searchBtn).click();
	}
	
	public String validateText()
	{
		return driver.findElement(displayedResult).getText();
		
	}
	
	

}
