package testCases;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class TC003_LoginDDTest extends BaseClass{

	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="DataDriven")//Data provider is in different class so added data provider class
	public void verify_loginDDT(String email,String pass,String exp)
	{
		
		logger.info("**** TC003_LoginDDTest started ****");
		try {
			HomePage hp=new HomePage(driver);
			hp.myAccountClick();
			hp.LoginClick();
			
			//Login
			LoginPage lp=new LoginPage(driver);
			lp.setEmailaddress(email);
			lp.setpass(pass);
			lp.clickLogin();
			
			//MyAccount
			MyAccountPage mp=new MyAccountPage(driver);
			boolean targetpage=mp.isMyAccountexists();
			
			
			//Data is valid - login success - test pass  - logout
							//login failed- test fail
			//Data invalid- login success- test fail - logout 
							//login fail- test pass
			if(exp.equalsIgnoreCase("Valid"))
			{
				if(targetpage==true)
				{
					mp.logoutclick();
					Assert.assertTrue(true);
					
				}
				else
				{
					Assert.assertTrue(false);
				}
					
			}
			
			
			if(exp.equalsIgnoreCase("InValid"))
			{
				if(targetpage==true)
				{
					mp.logoutclick();
					Assert.assertTrue(false);
					
				}
				else
				{
					Assert.assertTrue(true);
				}
					
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Assert.fail();
		}
		
		logger.info("**** TC003_LoginDDTest Ended ****");
		
	}
	
}
