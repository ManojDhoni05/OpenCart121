package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);// constructor should be called using super(), in our case it's parameterized so passing driver
		
	}

	


@FindBy(xpath="//input[@id='input-firstname']") WebElement firstName;
@FindBy(xpath="//input[@id='input-lastname']") WebElement lastName;
@FindBy(xpath="//input[@id='input-email']") WebElement eMail;

@FindBy(xpath="//input[@id='input-telephone']") WebElement telephone;
@FindBy(xpath="//input[@id='input-password']") WebElement password;
@FindBy(xpath="//input[@id='input-confirm']") WebElement passwordConfirm;
@FindBy(xpath="//input[@name='agree']") WebElement agree;
	
@FindBy(xpath="//input[@value='Continue']") WebElement continuebtn;
@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement alertmsg;


public void setfirstname(String name)
{
	firstName.sendKeys(name);
}


public void setlastname(String name)
{
	lastName.sendKeys(name);
}

public void setemail(String name)
{
	eMail.sendKeys(name);
}

public void settelephone(String name)
{
	telephone.sendKeys(name);
}

public void setpass(String name)
{
	password.sendKeys(name);
}

public void setConfirmpass(String name)
{
	passwordConfirm.sendKeys(name);
}

public void clickagree()
{
	agree.click();
}

public void clickContinue()
{
	continuebtn.click();
}

public String getconfirmmsg()
{
	String txt;
	try {
	txt=alertmsg.getText();
	}
	catch(Exception e)
	{
	txt=e.getMessage();
	}
	return txt;
}


}
