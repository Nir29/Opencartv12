package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegistrationPage extends Basepage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
		
	}

	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstname;
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastname;
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtPhno;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfpassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement btnagree;
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btncontinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement getconfirmationmsg;
	

	
	public void setFistname(String fname) {
		txtFirstname.sendKeys(fname);
	}
	public void setLastname(String lname) {
		txtLastname.sendKeys(lname);
	}
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	public void setPhono(String phono) {
		txtPhno.sendKeys(phono);
	}
	public void SetPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	public void ConfPassword(String confpwd) {
		txtConfpassword.sendKeys(confpwd);
		
	}
	public void Clickbtnagree() {
		btnagree.click();
	}
	public void Clickcontinue() {
		btncontinue.click();
	}
	
	WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));{
	mywait.until(ExpectedConditions.elementToBeClickable(btncontinue)).click();

	}
	
	public String getConfirmationMsg() {
		try {
		return getconfirmationmsg.getText();
		}
		catch(Exception e) {
			return(e.getMessage()); 
		}
		
	}
	
	
	
	
	
	
	
	
}
