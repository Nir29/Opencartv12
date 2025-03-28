package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogInPage extends Basepage {
	public LogInPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtemail;
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	@FindBy(xpath="//input[@value='Login']")
	WebElement btnlogin;
	
	
	
	public void Setemail(String email) {
		txtemail.sendKeys(email);
	}
	public void Setpassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	public void clicklogin() {
		btnlogin.click();
	}
	

}
