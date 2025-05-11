package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.Appconstant.AppConstant;
import com.qa.opencart.Appconstant.AppError;
import com.qa.opencart.Basepackage.BaseTest;

public class LoginpageTest extends BaseTest {

	
	
	// priority = 0 
	@Test
	public void loginpageTitleTest()
	{
		ChainTestListener.log("loginpagetitle new");
		String acttitle = loginpage.getLoginPageTitle();
		Assert.assertEquals(acttitle, AppConstant.LOGIN_PAGE_TITLE,AppError.TITLE_NOT_FOUND_ERROR);
		
		
	}
	
	
	// priority = 0 
	@Test
	public void loginpageurl()
	{
		String acturl =loginpage.getLoginPageurl();
		Assert.assertTrue(acturl.contains(AppConstant.LOGIN_PAGE_URL_FRACTION), AppError.URL_NOT_FOUND_ERROR);
	}
	
	
	// priority = 0 
	 @Test
	public void forgotpwdlinkExistTest()
	{
	 Assert.assertTrue( loginpage.isforgotpwdlinkExist() , AppError.ELEMENT_NOT_FOUND_ERROR);
	}
	  
	 
	 
	 @Test(priority = Integer.MAX_VALUE)
	 public void loginTest()
	 {
		homepage = loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		Assert.assertEquals(homepage.getHomePageTitle(), AppConstant.HOME_PAGE_TITLE , AppError.TITLE_NOT_FOUND_ERROR);
	 }
	
	 
	 @Test(description = "checking logo on login page")
	 public void logoTest()
	 {
		Assert.assertTrue( commonsPage.isLogoDisplayed(),"Logo not found error");
	 }
		@DataProvider
       public Object[][] getFooterData()
		  { 
			return new Object[] [] 
					{
				{"About Us"},
				{"Contact Us"} 
				};
				}
		
	 
	 
	 @Test(dataProvider = "getFooterData")
	 public void FooterTest(String footerLink)
	 {
		Assert.assertTrue(commonsPage.CheckFooterLink(footerLink), "footors not found");
	 }
		
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 

}
