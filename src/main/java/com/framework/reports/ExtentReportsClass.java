package com.framework.reports;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportsClass {
	
	//This class will have all the methods to generate html reports
	
	public static ExtentReports extent ;
	public static ExtentTest logger;
	
	@BeforeSuite(alwaysRun=true)
	public static void setupReport() {
		extent = new ExtentReports(System.getProperty("user.dir")+"\\Reports\\AutomationReport.html"); //Create empty extent report
		extent.addSystemInfo("Project Name", "Parabank").addSystemInfo("Developer", "Bharath"); // Add project details in report dashboard
	}
	
	@AfterSuite(alwaysRun=true)
	public static void teardownReport() {
		extent.flush(); // stop printing
		extent.close(); //Close report
	}
}
