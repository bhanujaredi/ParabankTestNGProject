package com.framework.listners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.framework.reports.ExtentReportsClass;
import com.framework.webcommons.WebCommons;
import com.relevantcodes.extentreports.LogStatus;

public class TestListners extends ExtentReportsClass implements ITestListener {
	
	//This class will have all the methods related testng listners top perform the actions on top of the test method


	public void onTestStart(ITestResult result) {
		logger = extent.startTest(result.getMethod().getMethodName()); //start printing process
	}

	public void onTestSuccess(ITestResult result) {
		logger.log(LogStatus.PASS, "Test Case is Passed : "+result.getMethod().getMethodName());
		extent.endTest(logger);
	}

	public void onTestFailure(ITestResult result) {
		logger.log(LogStatus.FAIL, "Test Case is Failed : "+result.getMethod().getMethodName());
		logger.log(LogStatus.FAIL, "Test Case is Failed with error: "+result.getThrowable());
		logger.addScreenCapture(new WebCommons().takeScreenshot(result.getMethod().getMethodName()));		
		extent.endTest(logger);
	}

	public void onTestSkipped(ITestResult result) {
		logger.log(LogStatus.SKIP, "Test Case is Skipped : "+result.getMethod().getMethodName());
		logger.addScreenCapture(new WebCommons().takeScreenshot(result.getMethod().getMethodName()));
		extent.endTest(logger);
	}
	
	public static void logMessage(String message) {
		logger.log(LogStatus.INFO, message);
	}

}
