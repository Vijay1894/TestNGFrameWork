package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.elementutil.Elementutil;

public class CommonsPage {
	private WebDriver driver ;
	private Elementutil elementutil;
	
	public CommonsPage(WebDriver driver)
	{
		this.driver = driver;
		elementutil = new Elementutil(driver);
	}
	
	
	private By logo = By.className("img-responsive");
	private By footer = By.xpath("//footer//a");
	
	
	public boolean isLogoDisplayed()
	{
		return elementutil.doISElementisDisplayed(logo);
	}
	
	public List<String> getFootersList()
	{
		List<WebElement > footerlist =  elementutil.WaitforElementsPresence(footer, 10);
		System.out.println("Total number of footers :" + footerlist.size());
		List<String > footers = new ArrayList<String>();
		for (WebElement e :footerlist)
		{
			String text = e.getText();
			System.out.println(text);
			footers.add(text);
		}
		return footers;
	}
	
	
	public boolean CheckFooterLink(String footerlink)
	{
		return getFootersList().contains(footerlink);
	}
}
