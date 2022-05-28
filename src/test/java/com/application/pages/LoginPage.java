package com.application.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.framework.utils.ReadPropFile;
import com.framework.webcommons.WebCommons;
import com.framework.webdriver.WebDriverClass;

public class LoginPage extends WebCommons{
	
	//Login page Elements
	
	@FindBy(xpath="//img[@class='logo']")
	private WebElement logo;
	
	@FindBy(xpath="//p[@class='caption']")
	private WebElement caption;
	
	@FindBy(xpath="//input[@name='username']")
	private WebElement usernameTxtb;
	
	@FindBy(xpath="//input[@name='password']")
	private WebElement passwordTxtb;
	
	@FindBy(xpath="//input[@value='Log In']")
	private WebElement loginBtn;
	
	@FindBy(xpath="//a[text()='Forgot login info?']")
	private WebElement forgotPasswordLink;
	
	@FindBy(xpath="//a[text()='Register']")
	private WebElement registerLink;
	
	//Login page action
	
	public void verifyLogo() {
		Assert.assertTrue(checkAvailability(logo));
		report("Logo successfully displayed");
	}
	
	public void verifyLogoCaption() {
	Assert.assertEquals(getElementText(caption), ReadPropFile.readData("Config.properties").getProperty("caption"));
		report("Logo caption matching");
	}
	
	
	public void verifyLoginFunctionality(String username, String password) {
		enterInfo(usernameTxtb, username);
		enterInfo(passwordTxtb, password);
		click(loginBtn);
		report("User logged in into the application");
	}
	
	public void getForgotPasswordPage() {
		click(forgotPasswordLink);
		report("Clicked on forgot password link");
	}
	
	public void getRegistrationPage() {
		click(registerLink);
		report("Clicked on regiostration link");
	}
	
	public static LoginPage getLoginPage() {
		return PageFactory.initElements(WebDriverClass.getDriver(), LoginPage.class);
	}

}
