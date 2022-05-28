package com.framework.webcommons;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.framework.reports.ExtentReportsClass;
import com.framework.utils.Utilities;
import com.framework.webdriver.WebDriverClass;
import com.relevantcodes.extentreports.LogStatus;

public class WebCommons {

	// This class will have all the common methods to perform actions on web
	// application

	public WebDriver driver = WebDriverClass.getDriver();

	// Method to scroll to element
	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
	}

	// Method to click
	public void click(WebElement element) {
		scrollToElement(element);
		element.click();
	}

	// Method to enter text in textbox
	public void enterInfo(WebElement element, String value) {
		scrollToElement(element);
		element.clear();
		element.sendKeys(value);
	}

	// Method to select checkbox
	public void selectCheckbox(WebElement element) {
		scrollToElement(element);
		if (!element.isSelected())
			element.click();
	}
	
	//Method to select option from dropdown
	public void selectDropdown(WebElement element,String method, String option) {
		scrollToElement(element);
		Select s = new Select(element);
		if(method.equalsIgnoreCase("visibleText")) {
			s.selectByVisibleText(option);
		}else if(method.equalsIgnoreCase("value")) {
			s.selectByValue(option);
		}else if(method.equalsIgnoreCase("index")) {
			s.selectByIndex(Integer.parseInt(option));
		}		
	}
	
	//Method to perform double click
	public void doubleClick(WebElement element) {
		scrollToElement(element);
		Actions action = new Actions(driver);
		action.doubleClick(element).build().perform();
	}
	
	//Method to take screenshot and return the screenshot path to attach in the report
	public String takeScreenshot(String screenshotName) {
		TakesScreenshot screenshot = (TakesScreenshot)driver; //take screenshot
		File scrFile = screenshot.getScreenshotAs(OutputType.FILE);//convert into file
		String path = System.getProperty("user.dir")+"\\Screenshots\\"+screenshotName+".png";
		try {
			FileUtils.copyFile(scrFile, new File(path)); // copy the file into specific folder
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	//Method to generate uniqueid
	public String getUniqueId() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyyhhmmss");
		String uniqueId = sdf.format(Calendar.getInstance().getTime());
		return uniqueId;
	}
	
	//Method to get page title
	public String getPageTitle() {
		return driver.getTitle();
	}
		
	//Method to get element
	public String getElementText(WebElement element) {
		return element.getText();
	}
	
	//Method to get element attribute value
	public String getElementAttValue(WebElement element, String attribute) {
		return element.getAttribute(attribute);
	}
	
	//Method to wait (implicit)
	public void waitForNextline() {
		driver.manage().timeouts().implicitlyWait(Utilities.WAIT_TIME, TimeUnit.SECONDS);
	}
	
	//Method to wait for Element
	public void waitForElement(By locator) {
		WebDriverWait wait = new WebDriverWait(driver,Utilities.WAIT_TIME);
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, 0));
	}
	
	//Method to switch to alert
	public void switchToAlert() {
		driver.switchTo().alert();
	}
	
	//Method to switch to frame
	public void switchToFrame(String framename) {
		driver.switchTo().frame(framename);
	}
	
	//Method to verify availability of element
	public boolean checkAvailability(WebElement element) {
		return element.isDisplayed();
	}
	
	//Method to print custom messaged
	public void report(String message) {
		ExtentReportsClass.logger.log(LogStatus.INFO, message);
	}
}
