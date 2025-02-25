package org.iitwf.selenium.mmpequinox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.iitwf.mmp.pages.patientmodule.MMPUtility;
import org.iitwf.selenium.lib.FrameworkLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SanitySuite extends FrameworkLibrary {
	/**
	 * 
	 * Validate all the modules are deployed successfuly
	 * Display the following output message:
	 * 
	 * 	"Following are the modules deployed successfully and Sanity Tests are passed!!!!!."
	 * 
	 */


	 
	@Test
	public void performHealthCheckTests()
	{	
		MMPUtility mmpUtil = new MMPUtility(driver);
		mmpUtil.login(prop.getProperty("patient_username"),prop.getProperty("patient_password"));
		ArrayList<String> expectedMenuArrayList = readMenuTitles();
		ArrayList<String> actualMenuArrayList = fetchMenuTitlesFromUI();
		System.out.println("Expected Array List" + expectedMenuArrayList);
		System.out.println("Actual Array List" + actualMenuArrayList);
		
		Assert.assertTrue(actualMenuArrayList.equals(expectedMenuArrayList));
		driver.close();
		
		   
	}
	public   ArrayList<String> fetchMenuTitlesFromUI()
	{
		List<WebElement> actualList = driver.findElements(By.xpath("//div[@class='sidebar-holder']/ul/li/a/span"));
		ArrayList<String> actualMenuArrayList = new ArrayList<String>();
		for(int i = 0;i<actualList.size();i++)
		{
			actualList.get(i).click();
		    actualMenuArrayList.add(actualList.get(i).getText().trim());
		}
		Arrays.sort(actualMenuArrayList.toArray());
		 
		  
		return actualMenuArrayList;
	}
 	public static ArrayList<String> readMenuTitles()
	{

		ArrayList<String> expectedMenuArrayList= new ArrayList<String>();
		expectedMenuArrayList.add("HOME");
		expectedMenuArrayList.add("Profile");
		expectedMenuArrayList.add("Schedule Appointment");
		expectedMenuArrayList.add("Information");
		expectedMenuArrayList.add("Fees");
		expectedMenuArrayList.add("Search Symptoms");
		expectedMenuArrayList.add("Messages");
		expectedMenuArrayList.add("Logout");
		Arrays.sort(expectedMenuArrayList.toArray());
		return expectedMenuArrayList;
	}
	 
}
 