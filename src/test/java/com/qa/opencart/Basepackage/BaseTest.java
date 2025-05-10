package com.qa.opencart.Basepackage;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.aventstack.chaintest.service.ChainPluginService;
import com.qa.opencart.pages.CommonsPage;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfopage;
import com.qa.opencart.pages.SearchResultpage;
import com.qaautomation.opencart.factory.DriverFactory;
//protected -- > works with package and outside of package 

@Listeners(ChainTestListener.class)
public class BaseTest {
	
	WebDriver driver;
	DriverFactory df;
	protected Properties prop;
	
	
	protected LoginPage loginpage;
	protected HomePage homepage;
	protected SearchResultpage searchResultpage;
	protected ProductInfopage productInfopage;
	protected CommonsPage commonsPage;
	
	
	
	@Parameters({"browser","browserversion","testname"})
	@BeforeTest(description = "setup: init the driver and properties")
	
	public void setup(String browserName , String browserVersion, String testName)
	{
		df = new DriverFactory(driver);
		prop = df.intiprop();
		if (browserName!= null)
		{
			prop.setProperty("browser", browserName);
			prop.setProperty("browserversion", browserVersion);
			prop.setProperty("testname", testName);
			}
		
	    driver = df.intiDriver(prop);
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		searchResultpage =new SearchResultpage(driver);
		productInfopage = new ProductInfopage(driver);
		commonsPage = new CommonsPage(driver);
		
		ChainPluginService.getInstance().addSystemInfo("Build#", "1.0");
		ChainPluginService.getInstance().addSystemInfo("headless#", prop.getProperty("headless"));
		ChainPluginService.getInstance().addSystemInfo("Incognito#", prop.getProperty("incognito"));	
		ChainPluginService.getInstance().addSystemInfo("owner#", "Vijay");
	}
	
	
	@AfterMethod
	public void attachScreenshot(ITestResult result)
	{
		
		if (!result.isSuccess()) // only for failure  test case
		{
		//ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
			//ChainTestListener.embed(DriverFactory.getScreenshotbyte(), "");
			ChainTestListener.embed(DriverFactory.getScreenshotBase64(), "image/png");
		}
		
	}
	
	
	@AfterTest
	public void tearDown()
	{
	 driver.quit();
	}

}
