package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
	
	@Test(groups= {"sanity","master"})
	public void verify_account_registration() {
		
		
		HomePage hp=new HomePage(driver);
		hp.ClickMyAccount();
		hp.ClickRegister();
		
		AccountRegistrationPage rp=new AccountRegistrationPage(driver);
		rp.setFistname(randomestring().toUpperCase());
		rp.setLastname(randomestring().toUpperCase());
		rp.setEmail(randomestring()+"@gmail.com");
		rp.setPhono(randomNumber());
		
		String password=alphanumeric();
		rp.SetPassword(password);
		rp.ConfPassword(password);
		rp.Clickbtnagree();
		rp.Clickcontinue();
		String confmsg=rp.getConfirmationMsg();
		
		Assert.assertEquals(confmsg,"Your Account Has Been Created!");
		
		
		
	}
	

}
