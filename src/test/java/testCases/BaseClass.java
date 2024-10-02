package testCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

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

public class BaseClass {

public static WebDriver driver; 
public Logger logger;
public Properties p; 
	@SuppressWarnings("deprecation")
	@Parameters({"os","browser"})
	@BeforeClass(groups={"Sanity","Master","Regression"})
	  public void setup(String os,String browser) throws IOException {
		
		FileInputStream fi=new FileInputStream(System.getProperty("user.dir")+"//src/test//resources//config.properties");
		//FileReader fr=new FileReader("./src//test//resources//config.properties");// same as inoutstream
		p=new Properties();
		p.load(fi);
		
		logger= LogManager.getLogger(this.getClass());// It will get the log file and store it in logger variable
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities=new DesiredCapabilities();
			
			//capabilities.setPlatform(Platform.WIN11);
			//os
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
			}
			else
			{
				System.out.println("No matching");
				return;
			}
			
			//browser remote
			switch(browser.toLowerCase())
			{
			case "chrome":
				capabilities.setBrowserName("chrome"); break;
			case "edge":
				capabilities.setBrowserName("MicrosoftEdge"); break;
			default:
				System.out.println("Invalid browser"); return;
			
			}
			
			//hub url- passing and coverting it to URL format
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
			
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
		switch(browser.toLowerCase())
		{
		case "chrome":
			driver=new ChromeDriver(); break;
		case "edge":
			driver=new EdgeDriver(); break;
		case "firefox":
			driver=new FirefoxDriver(); break;
		default:
			System.out.println("Invalid browser"); return;
		
		}
		}
		  
		  driver.manage().deleteAllCookies();
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 // driver.get("https://tutorialsninja.com/demo/");
		  driver.get(p.getProperty("appUrl"));//reading url from config.properties file
		  driver.manage().window().maximize();
	  }
	
	@AfterClass(groups={"Sanity","Master","Regression"})
	  public void teardown() {
		  driver.quit();
	  }
	
	
	public String captureScreen(String tname)
	{
		String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot ts=(TakesScreenshot) driver;
		File soruceFile=ts.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenShots\\"+tname+"_"+timeStamp+".png";
		File targetFile=new File(targetFilePath);
		
		soruceFile.renameTo(targetFile);
		
		return targetFilePath;// to copy the path to report
	}
	
	public String randomString()
	{
		String albhabet=RandomStringUtils.randomAlphabetic(5);
		return albhabet;
	}
	
	public String randomNumber()
	{
		String number=RandomStringUtils.randomNumeric(10);
		return number;
	}
	
	public String randomAlphaNumeric()
	{
		String albhabet=RandomStringUtils.randomAlphabetic(5);
		String number=RandomStringUtils.randomNumeric(10);
		return (albhabet+"@"+number);
	}
}
