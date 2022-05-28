package com.application.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.application.pages.AccountOverviewPage;
import com.application.pages.LoginPage;
import com.application.pages.RegistrationPage;
import com.framework.utils.ReadExcel;
import com.framework.webdriver.WebDriverClass;

public class ApplicationTest extends WebDriverClass{
		
	@Test(priority =1,groups= {"Smoke"})
	public void verifyApplicationLogo() {
		LoginPage loginpage = LoginPage.getLoginPage();
		loginpage.verifyLogo();
	}
	
	@Test(priority =2,groups= {"Smoke"})
	public void verifyApplicationLogoCaption() {
		LoginPage loginpage = LoginPage.getLoginPage();
		loginpage.verifyLogoCaption();
	}
	
	@Test(priority=3,dataProvider="logindata",groups= {"Smoke","Regression"})
	public void verfiyApplicationLoginFunctionality(String username,String password) {
		LoginPage loginpage = LoginPage.getLoginPage();
		AccountOverviewPage aoapage = AccountOverviewPage.getAccountOverviewPage();
		loginpage.verifyLoginFunctionality(username, password);
		aoapage.verifySuccessfulLogin();		
	}
	
	@Test(priority=4,groups= {"Sanity"})
	public void verfiyApplicationRegistrationPage() {
		LoginPage loginpage = LoginPage.getLoginPage();
		RegistrationPage regpage = RegistrationPage.getRegistrationPage();
		loginpage.getRegistrationPage();
		regpage.verifyRegistrationPage();
	}
		
	@DataProvider(name="logindata")
	public String [][] readData(){
		String [][] data = ReadExcel.readData("TestData.xlsx", "Sheet1");
		return data;
	}

}
