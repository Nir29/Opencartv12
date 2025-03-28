package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;

public class BaseClass {
	public WebDriver driver;
	public Logger logger;
	public Properties p;
	@BeforeClass(groups= {"sanity","regression","master"})
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException{
		
		FileReader file=new FileReader("./src//test//resources//config.properties");
	    p=new Properties();
		p.load(file);
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities cb=new DesiredCapabilities();
			if(os.equalsIgnoreCase("windows")) {
				cb.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac")) {
				cb.setPlatform(Platform.MAC);
				
			}
			else {System.out.println("no matching os");
			return;
			}
		
		switch(br.toLowerCase())
		{
		case "chrome": cb.setBrowserName("chrome");break;
		case "edge": cb.setBrowserName("edgebrowser");break;
		default:System.out.println("no matching browser");return;
		
		}
		driver=new RemoteWebDriver(new URL(" http://192.168.1.17:4444"),cb);
		 
		}
		
		switch(br.toLowerCase()) {
		case "chrome":driver=new ChromeDriver();break;
		case "edge":driver=new EdgeDriver();break;
		case "firefox":driver=new FirefoxDriver();break;
		default:System.out.println("invalid browser");return;
		}
		
		
		logger=LogManager.getLogger(this.getClass());
		driver=new ChromeDriver();
		driver.get(p.getProperty("appURL"));
		//driver.get("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
	}
	@AfterClass
	public void teardown() {
		driver.quit();
		
	}
	public String randomestring() {  
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
		
	}
	public String randomNumber() {
		String generatedString=RandomStringUtils.randomNumeric(10);
		return generatedString;
	}
	public String alphanumeric() {
		String generatedString=RandomStringUtils.randomAlphabetic(3);
		String generatedNumber=RandomStringUtils.randomNumeric(3);
		return (generatedString+generatedNumber);
	}

    public String captureScreen(String name) {
        // Generate timestamp for uniqueness
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        // Take a screenshot
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        // Define the target file path
        String targetFilePath = System.getProperty("user.dir") + "\\Screenshots\\" + name + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);

        try {
            // Copy the screenshot to the desired location
            FileUtils.copyFile(sourceFile, targetFile);
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Return null in case of failure
        }

        return targetFilePath; // Return the file path of the saved screenshot
    }
}

