package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002_LoginTest extends BaseClass{
	
	@Test(groups={"Sanity","Master"})
	public void verifyLogin() {
		
		logger.info("**** TC002_LoginTest started ****");
		try {
		//Homepage
		HomePage hp=new HomePage(driver);
		hp.myAccountClick();
		hp.LoginClick();
		
		//Login
		LoginPage lp=new LoginPage(driver);
		lp.setEmailaddress(p.getProperty("email"));// getting from config.properties
		lp.setpass(p.getProperty("password"));
		lp.clickLogin();
		
		//MyAccount
		MyAccountPage mp=new MyAccountPage(driver);
		boolean targetpage=mp.isMyAccountexists();
		
		Assert.assertTrue(targetpage);
		
		
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		
		logger.info("**** TC002_LoginTest Completed ****");
	}

}
