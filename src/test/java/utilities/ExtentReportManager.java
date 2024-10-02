package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.BaseClass;

public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkReporter;// UI of the report
	public ExtentReports extent;// common info like tester name browser name
	public ExtentTest test;// status pass fail screenshots
	
	String repName;
	public void onStart(ITestContext context) {
//		SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
//		Date dt=new Date();
//		String currentTimeStamp=df.format(dt);
	
		//three above lines are added below in one line
		String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		repName="Test-Report-"+timestamp+".html";
		
		sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName);//report store location
		   
		sparkReporter.config().setDocumentTitle("OpenCart Auomation Report");//title
		sparkReporter.config().setDocumentTitle("OpenCart Functional Tetsing");//name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);//combine ui along with give common info
		
		extent.setSystemInfo("Application name", "OpenCart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		
		String os=context.getCurrentXmlTest().getParameter("os");// getting the dynamic value from xml file
		extent.setSystemInfo("Operting System", os);
		
		String browser=context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includeGroups=context.getCurrentXmlTest().getIncludedGroups();// getting all the group names from xml 
		if(!includeGroups.isEmpty())
		{
			extent.setSystemInfo("Groups", includeGroups.toString());
		}
		
		
		  }

		//public void onTestStart(ITestResult result) {
			
			
			
		 // }

		
		
		public void onTestSuccess(ITestResult result) {
			test=extent.createTest(result.getName());// create a new entry in report
			test.assignCategory(result.getMethod().getGroups());// to display groups in reports
			test.log(Status.PASS, result.getName()+" got successfully executed");
			
		  }

		
		public void onTestFailure(ITestResult result) {
			test=extent.createTest(result.getTestClass().getName());// create a new entry report, getting class name and test ase name
			test.assignCategory(result.getMethod().getGroups());// to display groups in reports
			
			test.log(Status.FAIL, result.getName()+" got failed");
			test.log(Status.FAIL, result.getThrowable().getMessage());
			
			try {
				String imgpath=new BaseClass().captureScreen(result.getName());// directly creating object(BaseClass()) and calling capture method 
				//in the above line we are again creating object for base class this will again cerate a driver, thos will  make conflicit so make the driver as staic in base class
				test.addScreenCaptureFromPath(imgpath);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			  }

		 
		public void onTestSkipped(ITestResult result) {
			test=extent.createTest(result.getTestClass().getName());// create a new entry in report
			test.assignCategory(result.getMethod().getGroups());// which group it belongs to
			test.log(Status.SKIP, result.getName()+" got Skipped");
			test.log(Status.INFO, result.getThrowable().getMessage());
			
			  }
		
		public void onFinish(ITestContext context) {
			extent.flush();
			
			//to open the report automatically once the execution is done
			String pathOfReport=System.getProperty("user.dir")+"\\reports\\"+repName;
			File extentReport=new File(pathOfReport);
			try {
				Desktop.getDesktop().browse(extentReport.toURI());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			//send email automatically once the execution completes, not adding it for now
			
			
			  }


}
