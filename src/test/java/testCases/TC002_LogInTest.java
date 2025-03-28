package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LogInPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LogInTest extends BaseClass {
	@Test(groups={"regression","master"})
	public void verifyLogin(){
		logger.info(" ----  tc002 is started");
		try {
		String email=p.getProperty("Email");
		String pwd=p.getProperty("Password");
		HomePage hp=new HomePage(driver);
		hp.ClickMyAccount();
		hp.ClickLogin();
		
		LogInPage lp=new LogInPage(driver);
		lp.Setemail(email);
		lp.Setpassword(pwd);
		lp.clicklogin();
		
	
		
		MyAccountPage mcc=new MyAccountPage(driver);
		boolean targetpage=mcc.isAccountPageExists();
		
		Assert.assertEquals(targetpage, true);
		}
		catch(Exception e) {
			Assert.fail();
		}
		
		
	}

}
