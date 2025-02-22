package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.Appconstant.AppConstant;
import com.qa.opencart.elementutil.Elementutil;

public class HomePage {
	
	private WebDriver driver ;
	private Elementutil elementutil;
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		elementutil = new Elementutil(driver);
	}
	
	//1. private by locators :
	private By logout = By.linkText("Logout");
	private By headers = By.cssSelector("div#content >h2");
	private By search = By.name("search");
	private By serachIcon = By.cssSelector("div#search button");
	
	
	//2. public Homepage actions - Methods (Features )
	
		public String getHomePageTitle()
		{
			String title = elementutil.waitforTitleis(AppConstant.HOME_PAGE_TITLE, AppConstant.DEFAULT_TIME_OUT);
			System.out.println("home page title==>" + title);
			return title;
		}
		
		
		public String getHomePageurl()
		{
			
			String url = elementutil.waitforURLContains(AppConstant.HOME_URL_PAGE_TITLE,AppConstant.DEFAULT_TIME_OUT);
			System.out.println("home page title==>" + url);
			return url;
		}
	
		
	public  boolean islogoutlinkExist() {
			
			return elementutil.doISElementisDisplayed(logout);
		}
	
		public void logout() {
		
			if (islogoutlinkExist())
			{
				elementutil.doclick(logout);
		    }
			//pending - Work in progress
		}
		
	


		public List<String> getHeadersList()
		{
	        List<WebElement> list = elementutil.WaitforElementsvisible(headers, AppConstant.DEFAULT_TIME_OUT);
			List<String > headervaluelist = new ArrayList<String>();
			for (WebElement e : list)
			{
				String text = e.getText();
				headervaluelist.add(text); 
			}
			return headervaluelist;
		}
		
		
		
		public SearchResultpage doSearch(String searchkey)
		{
			System.out.println("Search key :" +searchkey );
		    WebElement searchele = 	elementutil.WaitforElementvisible(search, AppConstant.DEFAULT_TIME_OUT);
			elementutil.doSenkeys(searchele,searchkey);
			elementutil.doclick(serachIcon);
			return new SearchResultpage(driver);
		}

	
	
	
}
