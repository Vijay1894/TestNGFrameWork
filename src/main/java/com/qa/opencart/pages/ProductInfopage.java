package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.elementutil.Elementutil;

public class ProductInfopage {
	
	private WebDriver driver;
	private Elementutil elementutil;
	private Map<String,String>productMap;
	

	public ProductInfopage(WebDriver driver) {
		this.driver= driver;
		elementutil = new Elementutil(driver);
	}
	
    private By productheader = By.tagName("h1");
     private By MyAccount = By.xpath("/html/body/nav/div/div[2]/ul/li[2]/a/span[1]");
     private By productimages = By.cssSelector("ul.thumbnails img");
     private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
    private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	

	public String getProductHeader()
	{
		
		String header =elementutil.doElementGetText(productheader);
		System.out.println("product header:==>"+ header);
		return header;
	}
	
	
	public HomePage MyAccount()
	{
		elementutil.doclick(MyAccount);
	   return new HomePage(driver);	
	   
	}
	
	public int getProductImagesCount()
	{
		int imagescount = elementutil.WaitforElementsPresence(productimages, 5).size();
		System.out.println(getProductHeader() + ": Images Count="+imagescount );
		return imagescount;
	}
	
	/**
	 * get the full product information : header, imagescount, meta data , price data 
	 * @return
	 */
	public Map<String, String> getProductInfo()
	{ 
		//productMap = new HashMap<String , String>();
		//productMap = new LinkedHashMap<String , String>(); // ---> order proper order 
		productMap = new TreeMap<String , String>();   //---> order maintain alphabetic order ( sorted order)
		productMap.put("header", getProductHeader());
		productMap.put("imagescount", getProductImagesCount()+"");
		getProductMetaData();
		getProductPriceData();
		
		
		return productMap;
	}
	
//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: In Stoc
	
	private void getProductMetaData()
	{
		List<WebElement> MetaList = elementutil.WaitforElementsPresence(productMetaData, 5);
		for (WebElement e :MetaList )
		{
			String Metatext = e.getText();
			String meta[]=Metatext.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			
			productMap.put(metaKey, metaValue);
			
			
		}
	}
	
	
//	$2,000.00
//	Ex Tax: $2,000.00
	
	
	private void getProductPriceData()
	{
		List<WebElement> PriceList =elementutil.WaitforElementsPresence(productPriceData, 5);
		String productprice = PriceList.get(0).getText().trim();
		String ProductExTax = PriceList.get(1).getText().split(":")[1].trim();
		productMap.put("price", productprice);
		productMap.put("EXtax", ProductExTax);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
