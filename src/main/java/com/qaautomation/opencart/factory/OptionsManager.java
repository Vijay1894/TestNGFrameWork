package com.qaautomation.opencart.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	private Properties prop;
	
	private EdgeOptions eo;
	private FirefoxOptions fo;
	private ChromeOptions co;
	
	
	public OptionsManager(Properties prop)
	{
		this.prop= prop;
	}
	
	public EdgeOptions getEdgeOptions()
	{
		eo= new EdgeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim()))
		{
			System.out.println("===Running in Headless Mode==");
			eo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) 
		{
			System.out.println("===Running in incoginto Mode==");
			eo.addArguments("--inprivate");
		}
		if (Boolean.parseBoolean(prop.getProperty("remote")))
		{
			eo.setCapability("browserName", "edge");
		}
		return eo;
	}
	
	
	public ChromeOptions getChromeOptions()
	{
		co= new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim()))
		{
			System.out.println("===Running in Headless Mode==");
			co.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) 
		{
			System.out.println("===Running in incoginto Mode==");
			co.addArguments("--incognito");
		}
		if (Boolean.parseBoolean(prop.getProperty("remote")))
		{
			co.setCapability("browserName", "chrome");
			co.setBrowserVersion(prop.getProperty("browserversion").trim());
			
			
			Map<String ,Object>selenoidOptions =new HashMap<>();
			selenoidOptions.put("screenResolution", "1280x1024x24");
			selenoidOptions.put("enabledVNC", true);
			
			co.setCapability("selenoid:options",selenoidOptions );
			
			
			
		}
		return co;
	}

	public FirefoxOptions getFirefoxOptions()
	{
		fo= new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim()))
		{
			System.out.println("===Running in Headless Mode==");
			fo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) 
		{
			System.out.println("===Running in incoginto Mode==");
			fo.addArguments("--incognito");
		}
		if (Boolean.parseBoolean(prop.getProperty("remote")))
		{
			fo.setCapability("browserName", "firefox");
            fo.setBrowserVersion(prop.getProperty("browserversion").trim());
			
			
			Map<String ,Object>selenoidOptions =new HashMap<>();
			selenoidOptions.put("screenResolution", "1280x1024x24");
			selenoidOptions.put("enabledVNC", true);
			fo.setCapability("selenoid:options",selenoidOptions );
		}
		return fo;
	}

}
