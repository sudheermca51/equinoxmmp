package org.iitwf.mmp.pages.patientmodule;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchSymptomPage {
	
	protected WebDriver driver;
	@FindBy (xpath="//input[@id='search']")
	private WebElement searchBox;
	@FindBy (xpath="//input[@name='submit']")
	private WebElement searchBtn;
	@FindBy (xpath="//table[@class='table']/tbody/tr/td")
	private WebElement displayedSymp;
	
	
	
	public SearchSymptomPage(WebDriver driver) {
		this.driver = driver;
		if(!driver.getTitle().equals("search Symptoms")) {
			throw new IllegalStateException("This is not Search Symptom Page," +
		                                               "current page is: " + driver.getCurrentUrl());
			
		}
		PageFactory.initElements(driver, this);
	}
	
	public String searchSymptom(String symptom) {
		searchBox.sendKeys(symptom);
		searchBtn.click();
	    return"";
	}
	
    public String invalidsearchSymptom(String symptom) {
    	
    	searchBox.clear();
		searchBox.sendKeys(symptom);
		searchBtn.click();
		try {
		if(displayedSymp==null || !(displayedSymp.isDisplayed())) {
			return "Failed to locate symptom";
		}
		}catch(Exception e) {
			e.getMessage();
		}
		return"";
		
	}


}
