package com.qa.opencart.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.Appconstant.AppConstant;
import com.qa.opencart.Appconstant.AppError;
import com.qa.opencart.Basepackage.BaseTest;

public class HomePageTest extends BaseTest {

	@BeforeClass
	public void homepagesetup() {
		loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}

	@Test
	public void homepageTitleTest() {
		Assert.assertEquals(homepage.getHomePageTitle(), AppConstant.HOME_PAGE_TITLE, AppError.TITLE_NOT_FOUND_ERROR);

	}

	@Test
	public void homepageurltest() {
		Assert.assertTrue(homepage.getHomePageurl().contains(AppConstant.HOME_URL_PAGE_TITLE), AppError.URL_NOT_FOUND_ERROR);
	}

	@Test
	public void logoutLinkExistTest() {
		Assert.assertTrue(homepage.islogoutlinkExist(), AppError.ELEMENT_NOT_FOUND_ERROR);

	}

	@Test
	public void HeasersTest() {
		List<String> actualheaders = homepage.getHeadersList();
		System.out.println("homepage headers:==>" + actualheaders);
	}

	@DataProvider
	public Object[][] getSearchData()
	{
		return new Object [] [] {
			{"macbook", 3},
			{"imac",1},
			{"canon",1},
			{"samsung", 2},
			{"airtel",0}
		};
	}
	
	
	@Test(dataProvider = "getSearchData")
	public void searchTest(String searchkey , int resultCount) {
		searchResultpage = homepage.doSearch(searchkey);
		Assert.assertEquals(searchResultpage.getproductResultscount(), resultCount);
	}

}