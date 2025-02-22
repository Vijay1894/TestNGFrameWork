package com.qa.opencart.elementutil;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qaautomation.opencart.factory.DriverFactory;

public class Elementutil {

	private WebDriver driver;
	private Javascriptutility js;
	
	public Elementutil(WebDriver driver)
	{
		this.driver= driver;
		js = new Javascriptutility(driver);
	}
	
	private void nullcheck(CharSequence... value) {
		
		if (value == null)
		{
			throw new RuntimeException("===value/prop/attribute can not be null===");
		}
		
	}

	public WebElement getElement(By locator)
	{
		WebElement element = driver.findElement(locator);
		gethighlight(element);
		return element;
	}
	
	private WebElement gethighlight(WebElement element )
	{
		if (Boolean.parseBoolean(DriverFactory.highlight))
		{
			js.flash(element);
		}
		return element;
	}
	
	public List<WebElement> getElements(By locator)
	{
		return driver.findElements(locator);
	}
	
	
	public  void doSenkeys(By locator, String value)
    {
  	  getElement(locator).sendKeys(value);
    }
	
	public  void doSenkeys(By locator, CharSequence... value)
    {
		nullcheck(value);
		WebElement element = getElement(locator);
		 element.clear();
	  	 element.sendKeys(value);
    }
	
	
	public  void doSenkeys(WebElement element,CharSequence...value )
    {
  	  nullcheck(value);
  	  element.clear();
  	  element.sendKeys(value);
    }
	
	
	public  void doSenkeys(String locatorType ,String locatorValue,CharSequence...value )
    {
  	  nullcheck(value);
  	WebElement element = getElement(locatorType ,locatorValue);
  	  element.clear();
  	  element.sendKeys(value);
    }
	
	
	
	
	private WebElement getElement(String locatorType, String locatorValue) {
		// TODO Auto-generated method stub
		return null;
	}

	public void doclick(By locator)
	{
		getElement(locator).click();
	}
	
	public String doElementGetText(By locator)
	{
		return getElement(locator).getText();
	}
	
	
	public  boolean doISElementisDisplayed(By locator)
	{
		try {
		 return getElement(locator).isDisplayed();
		}
		catch (Exception e )
		{
			System.out.println("element is not displayed");
			return false ;
		}
	}
	
	public  List<WebElement> getelements (By locator)
	{
		return driver.findElements(locator);
	}
	
	public  boolean isElementdisplayed(By locator)
	{
		if(getelements(locator).size() == 1)
		{
			System.out.println("element is available on the page one time ");
			return true;
		}
		else 
		{
			System.out.println("element is not  available on the page one time ");
			return false;
		}
		
	}
	
	
	
	public  void doSelectDropDownByIndex(By locator, int index )
	{
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
		
	}
	
	public  void doSelectDropDownByVisibleText(By locator, String visibletext )
	{
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(visibletext);
		
	}
	
	public void doSelectDropDownByValue(By locator, String value )
	{
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(value);
		
	}
	
	public void printgetDropDownOptionstext(By locator)
	{
		Select select = new Select(getElement(locator));
		List<WebElement> list = select.getOptions();
		System.out.println(list.size());
		
		for(WebElement e :list)
		{
			System.out.println(e.getText());
		}
	}
	
	public  List<String> printgetDropDownOptionstextlist(By locator)
	{
		Select select = new Select(getElement(locator));
		List<WebElement> list = select.getOptions();
		System.out.println(list.size());
		
		List<String > OptionsValuelist = new ArrayList<String>();
		
		for (WebElement e : list)
		{
			String text = e.getText();
			OptionsValuelist.add(text);
		}
		
		return OptionsValuelist;
		
	}
	
	
	public int getDropdownoptionscount(By locator)
	{
		Select select = new Select(getElement(locator));
		List<WebElement> Optionssize = select.getOptions();
		System.out.println(Optionssize.size());
		return Optionssize.size();
	}
	
	public  void doSearch (By searchField, By suggestions, String searchkey, String actualValue) throws InterruptedException
	{
		
		getElement(searchField).sendKeys(searchkey);
        Thread.sleep(2000);
        List<WebElement> sugglist = getElements(suggestions);
	
		System.out.println(sugglist.size()); 
		boolean flag = false;
		for (WebElement e : sugglist)
		{
			 String text = e.getText();
			 System.out.println(text);
			 
			 
			 if ( text.contains(actualValue))
			 {
				 e.click();
				 flag = true;
				 break;
			 }
			 
		}
		if(flag)
		{
			System.out.println("is available and clicked");
		}
		else
		{
			System.out.println("is not available");
		}
			
		
	}
	
	
	public  boolean isPageLoaded(long timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		String isPageLoaded = 	wait.until(ExpectedConditions.jsReturnsValue("return document.readState =='complete'")).toString();
		System.out.println(isPageLoaded);
		return Boolean.parseBoolean(isPageLoaded);
		}
	//*******************Wait Utils ********************
	
	
	/**
	 * An expectation for checking that an element is present on the DOM of a page. 
	 * This does notnecessarily mean that the element is visible.
	 * @param Timeouts
	 * @return
	 */
	public  WebElement WaitforElementPresence(By locator , long Timeouts)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Timeouts));
	  WebElement element =  wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	  gethighlight(element);
		return element;
	}
	
	
	/**
	 * An expectation for checking that there is at least one element present on a web page.
	 * @param locator
	 * @param Timeouts
	 * @return
	 */
	public  List<WebElement> WaitforElementsPresence(By locator , long Timeouts)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Timeouts));
		try {
	  return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		}
		catch (TimeoutException  e)
		{
			return Collections.emptyList();
		}
	}
	
	
	
	
	/**
	 * An expectation for checking that an element is present on the DOM of a page and visible..
	 * Visibility means that the element is not only displayed but also has a height and width that is greater than 0
	 * @param Timeouts
	 * @return
	 */
	
	public WebElement  WaitforElementvisible(By locator , long Timeouts)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Timeouts));
	  WebElement element =  wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	   gethighlight(element);
		return element;
	}
	
	public List<WebElement> WaitforElementsvisible(By locator , long Timeouts)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Timeouts));
		try {
	  return   wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}
		
		catch (TimeoutException e )
		{
			return Collections.emptyList();
		}
	
	}
	
	
	
	
	//=========title ========
	
	/**
	 * An expectation for checking that the title contains a case-sensitive substring.
	 * @param fractionTitle
	 * @param Timeout
	 * @return
	 */
	public  String waitforTitleContains(String fractionTitle,long Timeout)
	{
		
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Timeout));
	
	try {
	if (wait.until(ExpectedConditions.titleContains(fractionTitle)))
	{
		return driver.getTitle();
	}
	}
	catch (Exception e)
	{
		System.out.println("title is not found after" + Timeout +"seconds");
	}
	return null;

}
	/**
	 * An expectation for checking the title of a page.
	 * @param Title
	 * @param Timeout
	 * @param titleNotFoundError 
	 * @return
	 */
	
	
	public  String waitforTitleis(String Title,long Timeout)
	{
		
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Timeout));
	
	try {
	if (wait.until(ExpectedConditions.titleContains(Title)))
	{
		return driver.getTitle();
	}
	}
	catch (Exception e)
	{
		System.out.println("title is not found after" + Timeout +"seconds");
	}
	return null;

}
	
	
	public  String   waitforURLContains(String fractionurl,int  Timeout)
	{
		
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Timeout));
	
	try {
	if (wait.until(ExpectedConditions.urlContains(fractionurl)))
	{
		return driver.getCurrentUrl();
	}
	}
	catch (Exception e)
	{
		System.out.println("url  is not found after" + Timeout +"seconds");
	}
	return null;

}
	
	
	

	

	
	
	
	
	
	
	
	
	
	
}
