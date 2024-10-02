package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	

	public LoginPage(WebDriver driver)
	{
		super(driver);	
	}
	
	@FindBy(xpath="//input[@id='input-email']") WebElement eMailAddress;
	@FindBy(xpath="//input[@id='input-password']") WebElement password;
	@FindBy(xpath="//input[@value='Login']") WebElement login;
	
	
	public void setEmailaddress(String email)
	{
		eMailAddress.sendKeys(email);
	}
	
	public void setpass(String pass)
	{
		password.sendKeys(pass);
	}
	
	public void clickLogin()
	{
		login.click();
	}

}
