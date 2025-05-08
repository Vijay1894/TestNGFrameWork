package com.qaautomation.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.opencart.Appconstant.AppConstant;
import com.qa.opencart.exception.FrameworkException;

public class DriverFactory {
	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static String highlight;
	
	public DriverFactory(WebDriver driver)
	{
		this.driver = driver;
	}

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver intiDriver(Properties prop) {
		String browsername = prop.getProperty("browser");
		System.out.println("browser name is :" + browsername);

		optionsManager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight");
		
		boolean remoteExecution = Boolean.parseBoolean(prop.getProperty("remote"));
		
		switch ((browsername).trim().toLowerCase()) {
		case "edge":
			if (remoteExecution)  // run tcs on remote/grid
			{
				initRemoteDriver("edge");
			}
			else //local execution
			{
				
				tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			}
			
			break;

		case "Firefox":
			
			if (remoteExecution)  // run tcs on remote/grid
			{
				initRemoteDriver("Firefox");
			}
			else //local execution
			{
				
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			}
			break;
		case "chrome":
			
			if (remoteExecution)  // run tcs on remote/grid
			{
				initRemoteDriver("chrome");
			}
			else //local execution
			{
				
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
				// driver = new ChromeDriver(optionsManager.getChromeOptions());
				
			}
			break;

		default:
			System.out.println("pls pass the valid browser name.." + browsername);
			throw new FrameworkException("==Invalid browser name ===");

		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();

	}
	
	/**
	 * init the remote driver with selenium grid 
	 * 
	 *@parm browserName
	 */
	private void initRemoteDriver(String browserName)
	{
		System.out.println("Running test on grid : " + browserName);
		try
		{
		switch (browserName.toLowerCase().trim()) {
		case "chrome" :
			tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),optionsManager.getChromeOptions()));
			break;
		case "firefox" :
			tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),optionsManager.getFirefoxOptions()));
			break;
			
		case "edge" :
			tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),optionsManager.getEdgeOptions()));
			break;
			

		default:
			System.out.println("broswersi not supported on GRID ..." + browserName);
			break;
		}
		}
		catch (MalformedURLException e)
		
		{
			e.printStackTrace();
		}
		
		
	}

	/**
	 * get Driver using thread local
	 * @return
	 */
	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * This method is used to initialize the properties from.Properties file
	 * 
	 * @return
	 */

	
	//supply environment name using maven command line  arguments 
	// mvn clean install -Denv="qa"--> -D -passing command line 
	// mvn clean intall
 	

	public Properties intiprop() {
		
	String envname = System.getProperty("env");
	System.out.println("running test suite on env: "+ envname);
	
	FileInputStream ip = null ;
	prop = new Properties();
	
	try {
		
	if (envname == null)
	{
		System.out.println("no env is passed, Hence running test suite on qa environment...");
		ip=new FileInputStream(AppConstant.CONFIG_PRO_QA_PATH);
	}
	else {
		switch ((envname.trim().toLowerCase()))
		{
		case "qa":
			ip = new FileInputStream(AppConstant.CONFIG_PRO_QA_PATH);
			break ; 
		case "stage":
			ip = new FileInputStream(AppConstant.CONFIG_PRO_stage_PATH);
			break ; 
		case "dev":
			ip = new FileInputStream(AppConstant.CONFIG_PRO_dev_PATH);
			break ;
		case "UAT":
			ip = new FileInputStream(AppConstant.CONFIG_PRO_UAT_PATH);
			break ;
			
			default :
				System.out.println("pls pass the right environment name " +envname);
				throw new FrameworkException("==invalid environment==");
		}
	}
	
			prop.load(ip);
		}
	
	catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return prop;
	}
	
	
	
	
	/**
	 * TakesScreenshot
	 * @return 
	 * 
	 */
	
	public static String getScreenshot()
	{
		File srcfile = (( TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE); // temp directory 
		String path = System.getProperty("user.dir")+ "/screenshot/" +" "+System.currentTimeMillis()+".png";
		File destination = new File (path);
		
		
		try {
			FileHandler.copy(srcfile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	
	public static File getScreenshotFile()
	{
		File srcfile = (( TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE); // temp directory 
		return srcfile;
	}
	
	public static byte[] getScreenshotbyte()
	{
		return  (( TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BYTES); // temp directory 

	}
	
	public static String getScreenshotBase64()
	{
		return  (( TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BASE64); // temp directory 

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
