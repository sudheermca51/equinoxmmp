package org.iitwf.selenium.mmpequinox;

import java.io.FileNotFoundException;
import java.util.HashMap;


import org.iitwf.mmp.pages.patientmodule.SearchSymptomsPage;
import org.iitwf.mmp.pages.patientmodule.MMPUtility;
import org.iitwf.selenium.lib.FrameworkLibrary;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchSymptomsTest extends FrameworkLibrary{


	@Test
	public void validateSearchSymptoms() throws FileNotFoundException, InterruptedException
	{
		launchBrowser(prop.getProperty("patient_url")); 
		MMPUtility mmpUtil = new MMPUtility(driver);
		mmpUtil.login(prop.getProperty("patient_username"),prop.getProperty("patient_password"));
		mmpUtil.navigateToASearchModule("Search Symptoms");
		SearchSymptomsPage searchSymObj = new SearchSymptomsPage (driver);
		HashMap<String, String> expectedHMap = searchSymObj.symptomsPage();
		HashMap<String, String> actualHMap = searchSymObj.fetchsymptomsPageData();
		Assert.assertEquals(expectedHMap,actualHMap);
	}





}
