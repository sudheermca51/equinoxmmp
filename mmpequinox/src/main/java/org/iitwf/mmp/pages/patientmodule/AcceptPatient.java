package org.iitwf.mmp.pages.patientmodule;

import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class AcceptPatient {

WebDriver driver;

    HashMap<String,String> hMap = new HashMap<String,String>();
	By usernamelink = By.xpath("//tbody/tr[3007]/td/a");
	By selectstatusDropDown = By.id("sapproval");
	By submitBtn = By.xpath("//input[@value='Submit']");

	public AcceptPatient(WebDriver driver)
	{
		this.driver = driver;
	}

	public void userNameLink() {

		driver.findElement(usernamelink).click();

	}

	public void StatusDropDown() {

		new Select(driver.findElement(selectstatusDropDown)).selectByVisibleText("Accepted");
		hMap.put("Select Status", "Accepted");
	}

	public void Submit() {

		driver.findElement(submitBtn).click();
	}

}
