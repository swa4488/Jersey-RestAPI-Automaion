package test;

import java.lang.reflect.Method;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import clients.ApiAction;
import clients.models.response.BasicAuthResponse;

public class ApiTest {

	protected static ExtentReports report;
	static ExtentTest extentTest = null;
	
	@BeforeSuite()
	public static void initializeExtentReport(){
		report = new ExtentReports(System.getProperty("user.dir")+"\\ExtentReportResults.html",true);
	}

	@BeforeMethod
	public static void startTest(Method method)
	{
		extentTest = report.startTest(ApiTest.class.getSimpleName()+":: "+method.getName());
	}

	@AfterMethod
	public void getResult(ITestResult result) throws Exception{
		if(result.getStatus() == ITestResult.FAILURE){
			extentTest.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
			extentTest.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
		}else if(result.getStatus() == ITestResult.SKIP){
			extentTest.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		}
	}

	@AfterMethod
	public static void endTest()
	{
		report.endTest(extentTest);
	}
	
	@AfterSuite()
	public static void flushExtentReport(){
		report.flush();
		report.close();
	}
	
	@Test()
	public void apiTest() {
		ApiAction apiAction = new ApiAction();
		BasicAuthResponse response = apiAction.getBasicPostman("/basic-auth");
		extentTest.log(LogStatus.INFO,"API Test",response.toString());
	}
}
