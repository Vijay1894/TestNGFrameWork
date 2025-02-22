package com.qa.opencart.test;



import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.Appconstant.AppConstant;
import com.qa.opencart.Basepackage.BaseTest;
import com.qa.opencart.elementutil.ExcelUtil;

public class ProductinfoproductTest extends BaseTest {
	
	    @BeforeClass
	    public void productInforSetup()
	    {
	    	loginpage.doLogin("test22@gmail.com", "Vijay@1894");
	    	
	    }
	    
	  //Data drivern approach ---> Paramaterized 
	    
	    @DataProvider
		   public Object[][] getProductData()
		   {
			   return new Object[] [] {
				   {"Macbook", "MacBook Pro"},
				   {"macbook", "MacBook Air"},
				   {"imac", "iMac"},
				   {"samsung", "Samsung SyncMaster 941BW"},
				   {"samsung","Samsung Galaxy Tab 10.1"}
			   };
		   }
	    
	    @Test(dataProvider = "getProductData")
	    public void productSearchHeaderTest(String searchKey , String productName)
	    {
	    	ChainTestListener.log(searchKey + productName);
	    	searchResultpage = homepage.doSearch(searchKey);
	    	productInfopage =	searchResultpage.selectProduct(productName);
	        String actualproductheader = productInfopage.getProductHeader();
	        Assert.assertEquals(actualproductheader, productName);
	        
	    }
	    
	 @Test (priority = Integer.MAX_VALUE)
     public void logoutTest ()
	    {
	    	productInfopage.MyAccount();
	    	homepage.logout();	   
	    	}
	 
	 
	    
//	    @DataProvider
//	   public Object[][] getProductImageData()
//	   {
//		   return new Object[] [] {
//			   {"Macbook", "MacBook Pro", 4},
//			   {"macbook", "MacBook Air", 4},
//			   {"imac", "iMac",3},
//			   {"samsung", "Samsung SyncMaster 941BW",1},
//			   {"samsung","Samsung Galaxy Tab 10.1",7}
//		   };
//	   }
    
	    
	    @DataProvider
		   public Object[][] getProductImageSheetData()
		   {
			   Object productData[][] =ExcelUtil.getTestData(AppConstant.PRODUCT_SHEET_NAME);	
			   return productData;
		   }
	    
	    @Test(dataProvider = "getProductImageSheetData")
	    public void productImagesCountTest(String searchKey , String productname , int ExpectedImagesCount)
	    {
	    	searchResultpage = homepage.doSearch(searchKey);
	    	productInfopage =	searchResultpage.selectProduct(productname);
	        int actualproductimagescount = productInfopage.getProductImagesCount();
	        Assert.assertEquals(actualproductimagescount, ExpectedImagesCount);
	        
	    }
	    
	    
	    @Test
	    public void productinfotest()
	    {
	    	searchResultpage = homepage.doSearch("macbook");
	    	productInfopage =	searchResultpage.selectProduct("MacBook Pro");
	    	
	    Map<String , String> productinfoMap = productInfopage.getProductInfo();
	    productinfoMap.forEach((k,v) ->  System.out.println(k +":"+v)); 
	    
	    
	    	SoftAssert softAssert = new SoftAssert();
	    
	    	softAssert.assertEquals(productinfoMap.get("header"), "MacBook Pro");
	    
	    softAssert.assertEquals(productinfoMap.get("Brand"), "Apple");
	    softAssert.assertEquals(productinfoMap.get("Availability"), "In Stock");
	    softAssert.assertEquals(productinfoMap.get("Product Code"), "Product 18");
	    softAssert.assertEquals(productinfoMap.get("Reward Points"), "800");
	    
	    softAssert.assertEquals(productinfoMap.get("price"), "$2,000.00");
	    softAssert.assertEquals(productinfoMap.get("EXtax"), "$2,000.00");
	    
	    softAssert.assertAll();
	  
	        
	    }
	    
	    
	    
	    
	   
	    
	   

	    
	    
}
