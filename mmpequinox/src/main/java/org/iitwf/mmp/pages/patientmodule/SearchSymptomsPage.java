package org.iitwf.mmp.pages.patientmodule;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchSymptomsPage {
	protected WebDriver driver ;
	private By searchSymptomXpath = By.xpath("//input[@id='search']");
	private By buttonXpath = By.xpath("//input[@name='submit']");
	

public SearchSymptomsPage(WebDriver driver) {
	this.driver = driver;
	
}
public HashMap<String, String> symptomsPage()
{
	HashMap<String,String> expectedHMap = new HashMap<String,String>();
	WebElement searchWE = driver.findElement(searchSymptomXpath);
	searchWE.sendKeys("fever");
	expectedHMap.put("search",searchWE.getDomProperty("value"));
	
	WebElement buttonWE = driver.findElement(buttonXpath);
	buttonWE.click();
	System.out.println("expectedHMap ::" + expectedHMap);
	return expectedHMap;
}
public HashMap<String,String> fetchsymptomsPageData() throws FileNotFoundException, InterruptedException
{
		HashMap<String,String> actualHMap = new HashMap<String,String>();
		
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//th[text()='Symptoms']/ancestor::table[@class='table']/tbody/tr[1]/td[1]")));
		element.getText();
		actualHMap.put("search",element.getText());
		System.out.println("actualHMap ::::" + element.getText());
	    return actualHMap;
		
		
		 
}

}
		



