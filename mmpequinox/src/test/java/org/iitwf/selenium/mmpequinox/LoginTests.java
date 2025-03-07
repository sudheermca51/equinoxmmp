package org.iitwf.selenium.mmpequinox;

import java.io.IOException;

import org.iitwf.mmp.pages.patientmodule.HomePage;
import org.iitwf.mmp.pages.patientmodule.MMPUtility;
import org.iitwf.selenium.lib.ExcelUtils;
import org.iitwf.selenium.lib.FrameworkLibrary;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTests extends FrameworkLibrary {
//			id="username"
//			id="password"
//			name="submit"
//			//h3[normalize-space()='Patient Portal']
//
//			String expectedText = "Patient Portal";
	
	@DataProvider(name="DP")
	public String[][] feedData() throws IOException
	{
		String inputData[][] = ExcelUtils.getCellData("mmpdata.xlsx");
		return inputData;
	}
	
	@Test(dataProvider="DP")
	public void testLogin(String username,String password)
	{
		launchBrowser(prop.getProperty("patient_url")); 
		MMPUtility mmpUtil = new MMPUtility(driver);
		//mmpUtil.login(username,password);
		mmpUtil.login(prop.getProperty("patient_username"),prop.getProperty("patient_password"));
		HomePage hPage = new HomePage(driver);
		hPage.navigatetoAModule("Profile");
		String actualText = driver.findElement(By.xpath("//h3[normalize-space()='Patient Portal']")).getText();
		String expectedText = "Patient Portal";
		Assert.assertEquals(actualText, expectedText);
		//launchBrowser(prop.getProperty("patient_url"));
		
		 
	}

}
