package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LogInPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.Dataproviderclass;

public class TC003_LoginDDT extends BaseClass {
	@Test(dataProvider="loginData",dataProviderClass=Dataproviderclass.class)	
		public void verifytest_login(String eml,String pwd,String result) {
		logger.info("--------tc003 is started");
		try {
	
		HomePage hp=new HomePage(driver);
		hp.ClickMyAccount();
		hp.ClickLogin();
		
		LogInPage lp=new LogInPage(driver);
		lp.Setemail("eml");
		lp.Setpassword("pwd");
		lp.clicklogin();
		
		MyAccountPage mcc=new MyAccountPage(driver);
		boolean targetpage=mcc.isAccountPageExists();
		
		
		if (result.equalsIgnoreCase("valid")) {
			if(targetpage==true) {
				mcc.clickLogout();
				Assert.assertTrue(true);
			}
		}
		else {
			Assert.assertTrue(false);
		}
		if(result.equalsIgnoreCase("invalid")) {
			if(targetpage==true) {
				mcc.clickLogout();
				Assert.assertTrue(false);
			}
		}
		else {
		Assert.assertTrue(true);
	}
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info(result, result, result, result, result, result, result,  result);
	}
}
