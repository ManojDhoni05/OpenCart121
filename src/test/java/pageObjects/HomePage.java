package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath="//span[normalize-space()='My Account']") public static WebElement myAccount;
	//@FindBy(xpath="//a[normalize-space()='Register']") WebElement Registerlink;
	public By Registerlink=By.xpath("//a[normalize-space()='Register']");
	
	@FindBy(linkText = "Login") WebElement Loginbtn;
	
	public static void myAccountClick()
	{
		//sol1
		myAccount.click();
		
		//sol2
		//myAccount.submit();
		
		//sol3
		//Actions act=new Actions(driver);
		//act.moveToElement(myAccount).click().perform();
		
		//sol4
		//WebDriverWait wb=new WebDriverWait(driver, Duration.ofSeconds(10));
		//wb.until(ExpectedConditions.elementToBeClickable(myAccount)).click();
		
		//sol5
		//JavascriptExecutor js=(JavascriptExecutor)driver; 
		//js.executeScript("arguments[0].click();", myAccount);
		
		//sol6
		//myAccount.sendKeys(Keys.RETURN);
		
	}
	
	public void registerClick()
	{
		
		//Registerlink.click();
		driver.findElement(Registerlink).click();
	}
	
	public void LoginClick()
	{
		
		//Registerlink.click();
		Loginbtn.click();
	}
	
	
}
