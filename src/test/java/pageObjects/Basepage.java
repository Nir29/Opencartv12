package pageObjects;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Basepage {
	WebDriver driver;
	
	public Basepage(WebDriver driver) {
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
	}
	
	

}
