package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportUtilityclass {
	

	
	public class ExtentReportManager implements ITestListener {

	    public ExtentSparkReporter sparkReporter;
	    public ExtentReports extent;
	    public ExtentTest test;

	    String repName;

	    @Override
	    public void onStart(ITestContext context) {
	        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	        repName = "Test-Report-" + timeStamp + ".html";
	        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);

	        sparkReporter.config().setDocumentTitle("Opencart Automation Report");
	        sparkReporter.config().setReportName("Opencart Functional Testing");
	        sparkReporter.config().setTheme(Theme.DARK);

	        extent = new ExtentReports();
	        extent.attachReporter(sparkReporter);

	        extent.setSystemInfo("Application", "opencart");
	        extent.setSystemInfo("Module", "Admin");
	        extent.setSystemInfo("Sub Module", "Customers");
	    }
	    
	    

	    
	    public void onTestStart(ITestResult result) {
	        test = extent.createTest(result.getName());
	    }

	    
	    public void onTestSuccess(ITestResult result) {
	        test.log(Status.PASS, result.getName() + " got successfully executed");
	        test.assignCategory(result.getMethod().getGroups()); // to display groups in report
	    }

	   
	    public void onTestFailure(ITestResult result) {
	        test.log(Status.FAIL, result.getName() + " got failed");
	        test.log(Status.INFO, result.getThrowable().getMessage());
	        test.assignCategory(result.getMethod().getGroups());

	        String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
	    }

	   
	    public void onTestSkipped(ITestResult result) {
	        test.log(Status.SKIP, result.getName() + " got skipped");
	        test.log(Status.INFO, result.getThrowable().getMessage());
	        test.assignCategory(result.getMethod().getGroups());
	    }

	    
	    public void onFinish(ITestContext context) {
	        extent.flush();

	        // Open report automatically
	        try {
	            Desktop.getDesktop().browse(new File(".\\reports\\" + repName).toURI());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

	    
	}



