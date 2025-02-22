
package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.Appconstant.AppConstant;
import com.qa.opencart.elementutil.Elementutil;

public class SearchResultpage {
	
	private WebDriver driver;
	private Elementutil elementutil;
	
	public SearchResultpage(WebDriver driver)
	{
		this.driver = driver;
		elementutil = new Elementutil(driver);
	}
	
	private By productResults = By.cssSelector("div.product-thumb");
	
	
	
	public int getproductResultscount()
	{
		int resultcount = elementutil.WaitforElementsPresence(productResults, AppConstant.SHORT_TIME_OUT).size();
		System.out.println("productresults count ==>"+resultcount);
		return resultcount;
	}
	
	public ProductInfopage selectProduct(String productname)
	{
		System.out.println("Prdouctname===>"+productname);
		elementutil.doclick(By.linkText(productname));
		return new ProductInfopage(driver);
	}
}
