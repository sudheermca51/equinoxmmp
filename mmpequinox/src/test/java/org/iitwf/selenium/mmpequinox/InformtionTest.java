package org.iitwf.selenium.mmpequinox;
import org.testng.annotations.Test;
import java.io.IOException;
import org.iitwf.mmp.pages.patientmodule.HomePage;
import org.iitwf.mmp.pages.patientmodule.InformationPage;
import org.iitwf.mmp.pages.patientmodule.MMPUtility;
import org.testng.annotations.Listeners;
import org.iitwf.selenium.lib.FrameworkLibrary;
import org.iitwf.selenium.lib.ListenerUtility;
import org.iitwf.selenium.lib.ScreenshotUtil;

import com.aventstack.extentreports.ExtentTest;



@Listeners(ListenerUtility.class)

public class InformtionTest extends FrameworkLibrary{
private ExtentTest extentTest;
InformationPage VIPage;



@Test
public void InformationMessage() throws IOException
{
	extentTest = extentReports.createTest("######## Information Test ########");
	
	launchBrowser(prop.getProperty("patient_url"));
	MMPUtility mmpUtil = new MMPUtility(driver);
	mmpUtil.login(prop.getProperty("patient_username"),prop.getProperty("patient_password"));
	
	HomePage hPage = new HomePage(driver);
	hPage.navigatetoAModule("Information");
	extentTest.info("clicking on Information Title");
	
	VIPage = new InformationPage(driver);
	boolean result = VIPage.validateInformation();
	
	extentTest.info("Display the information");
	
	ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);	
    String screenshotPath = screenshotUtil.captureScreenshot("Information page");
    extentTest.addScreenCaptureFromPath(screenshotPath,"Display the information");

	
		
	}	

}

		

	


