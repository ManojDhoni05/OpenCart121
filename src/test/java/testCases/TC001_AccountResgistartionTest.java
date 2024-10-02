package testCases;

import static org.testng.Assert.fail;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC001_AccountResgistartionTest extends BaseClass {

	// all these are moved to base class because it will be used in many places
	/*WebDriver driver; 
	
	@BeforeClass
	  public void setup() {
		  driver=new ChromeDriver();
		  driver.manage().deleteAllCookies();
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		  driver.get("https://tutorialsninja.com/demo/");
		  driver.manage().window().maximize();
	  }*/
	
	  @Test(groups={"Regression","Master"})
	  public void verify_Accountregistration() {
		  
		  try {
		  logger.info("** STARTING TC001_AccountResgistartionTest **");
		 HomePage hp=new HomePage(driver);
		 logger.info("My Account link click");
		 hp.myAccountClick();
		 logger.info("Register link click");
		 hp.registerClick();
		 
		 AccountRegistrationPage reg=new AccountRegistrationPage(driver);
		 //reg.setfirstname("John");
		 //reg.setlastname("Bro");
		 logger.info("Providing details");
		 reg.setfirstname(randomString().toUpperCase());
		 reg.setlastname(randomString().toUpperCase());
		 reg.setemail(randomString()+"@gmail.com");// everytime we should different email else it will throw an error, so we should use random
		 reg.settelephone(randomNumber());
		 
		 String pass=randomAlphaNumeric();
		 reg.setpass(pass);
		 reg.setConfirmpass(pass);
		 
		 reg.clickagree();
		 reg.clickContinue();
		 logger.info("Validating message");
		 String confirmmsg=reg.getconfirmmsg();
		 if(confirmmsg.equals("Your Account Has Been Created!"))
		 {
			 Assert.assertTrue(true);
		 }
		 else
		 {
			 logger.error("Error logs");
			 logger.debug("Debug logs");// we should mention debug in log4j2.xml to get debug logs
			 Assert.assertTrue(false);
		 }
		// Assert.assertEquals(confirmmsg, "Your Account Has Been Created!");
		  }
		  catch(Exception e)
		  {
			 Assert.fail();
		  }
		  
		  logger.info("** TEST COMPLETED **");
		 
	  }

	/*  @AfterClass
	  public void teardown() {
		  driver.quit();
	  }*/

	
	
}
