package org.iitwf.mmp.pages.patientmodule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MMPUtility {

	WebDriver driver;
	public MMPUtility(WebDriver driver)
	{
		this.driver= driver;
		
	}
	public void login(String username,String password)
	{

		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		driver.getTitle();
	}
	

	public void launchBrowser(String url )
	{
		driver.manage().window().maximize();
		driver.get(url);

	}
	public void navigateToASearchModule(String moduleName) {
		// TODO Auto-generated method stub
		
		driver.findElement(By.xpath("//span[normalize-space() = '"+moduleName+"']")).click();
	
}
	
	

	public void adminLogin(String username,String password)
	{

		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.name("admin")).click();
		
	}

}
