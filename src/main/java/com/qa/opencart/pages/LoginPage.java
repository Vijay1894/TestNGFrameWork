package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.Appconstant.AppConstant;
import com.qa.opencart.Appconstant.AppError;
import com.qa.opencart.elementutil.Elementutil;

public class LoginPage {
	
 private  WebDriver driver;
 private Elementutil elementutil;
	
	public LoginPage(WebDriver driver)
	{
	   this.driver= driver;
	   elementutil = new Elementutil(driver); 
	}
	
	//1. By Locator : Page objects : OR (object Rep)

	private By emailid = By.id("input-email");
	private By Pwd = By.id("input-password");
	private By loginbtn = By.xpath("//input[@value='Login']");
	private By forgotpwd = By.partialLinkText("Forgotten Password");
	
	
	//2. public page actions - Methods (Features )
	
	public String getLoginPageTitle()
	{
		String title = elementutil.waitforTitleis(AppConstant.LOGIN_PAGE_TITLE, AppConstant.DEFAULT_TIME_OUT);
		System.out.println("login page title==>" + title);
		return title;
	}
	
	
	public String getLoginPageurl()
	{
		String url = elementutil.waitforURLContains(AppConstant.LOGIN_PAGE_URL_FRACTION,AppConstant.DEFAULT_TIME_OUT);
		System.out.println("login page title==>" + url);
		return url;
	}
	
	
	public boolean isforgotpwdlinkExist()
	{
		 return elementutil.doISElementisDisplayed(forgotpwd);
	}
	
	public HomePage doLogin(String username , String password )
	{
		System.out.println("Application credentials are : ==>"+username+ " "+password );
		elementutil.WaitforElementvisible(emailid, AppConstant.SHORT_TIME_OUT).sendKeys(username);
		elementutil.doSenkeys(Pwd, password);
		elementutil.doclick(loginbtn);
		return new HomePage(driver);
	}
	
	
}
