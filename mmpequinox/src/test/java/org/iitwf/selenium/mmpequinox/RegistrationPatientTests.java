package org.iitwf.selenium.mmpequinox;

import org.iitwf.mmp.pages.patientmodule.RegistrationPatientPage;
import org.iitwf.selenium.lib.FrameworkLibrary;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationPatientTests extends FrameworkLibrary {
	
	 
	RegistrationPatientPage RPPage;
	
		
	@Test(description="US_001 Registration of the Page",groups={"US_001","regression","sanity","patientmodule"})
	public void validateRegistration() throws Exception
	{  
		launchBrowser(prop.getProperty("patient_url"));
		RPPage = new RegistrationPatientPage(driver);
		RPPage.clickRegisterButton();
		RPPage.fillData();
		String actual = RPPage.clickOnSaveButton();
		//String actual = RPPage.readSuccessMessage();
		String expected ="Thank you for registering with MMP. ";
		Assert.assertEquals(actual, expected);
	}
	
	
	
}