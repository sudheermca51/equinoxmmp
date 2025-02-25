package org.iitwf.selenium.lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class FrameworkLibrary {
	
	protected Properties prop;
	protected WebDriver driver;
    protected ExtentReports extentReports;
    

	String environment,browser;
	
	@BeforeSuite
	public void setUp()
	{
		loadExtentReportConfig();
	}
	@BeforeTest
	public void readProperties() throws IOException
	{
		
		loadProperties("config//mmp_global.properties");
		environment = prop.getProperty("environment");
		browser = prop.getProperty("browser");
		if(environment.equals("dev"))
		{
			loadProperties("config//mmp_dev.properties");
		}
		else 
		{
			loadProperties("config//mmp_qa.properties");
		}
		
		System.out.println("patient username::: "+prop.getProperty("patient_username"));
        // Set up ExtentReports
		 
		 
       
	}
	public void loadExtentReportConfig()
	{
		
		String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/ExtentReports_"+timestamp+".html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
	}
	public void loadProperties(String filePath) throws IOException
	{
		File f = new File(filePath);
		FileInputStream fis = new FileInputStream(f);
		prop = new Properties();
		prop.load(fis);
	}
	@BeforeClass
	public void invokeDriverInstance()
	{
		 
		 if(browser.equals("chrome"))
		 {
			 driver = new ChromeDriver();
			 driver.manage().window().maximize();
			
		 }
		 else
		 {
			 driver = new EdgeDriver();
			 driver.manage().window().maximize();
			
		 }
	}
	public void launchBrowser(String url)
	{
		driver.get(url);
	}
	@AfterSuite
	public void tearDown()
	{
        // Close the WebDriver
        if (driver != null) {
            driver.quit();
        }
        // Flush the reports
        if (extentReports != null) {
            extentReports.flush();
        }

      
		
	}
	

}
