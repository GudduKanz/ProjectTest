import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import PageObjectModel.PageTest;
import Testdataengine.Excelsheet;
import Utilities.Browserclass;


public class Testscripte {
	public static WebDriver driver;
	ExtentReports report;//global variables
	ExtentTest logger;
  @Test(priority=0,dataProvider="Test")
  public void Signin(String user,String pass) throws IOException, InterruptedException {
	  report=new ExtentReports("D:\\ResultReport.html",true);
	  logger=report.startTest("BusinessVerifyLogin");
	  PageTest obj = PageFactory.initElements(driver, PageTest.class);
	  obj.login(user, pass);
	  logger.log(LogStatus.INFO, "Logins the application successfully");
	  
  }
  
  @Test(priority=1)
  public void Share() {
	  PageTest obj1 = PageFactory.initElements(driver, PageTest.class);
	  obj1.Shareanupdate("Happy to be hear");

  }
  @BeforeClass
  public void beforeMethod() throws InterruptedException {
	  driver = Browserclass.Browserfact("chrome", "https://check.youthhub.co.nz/");
  }

  @AfterMethod
  public void afterMethod(ITestResult result) {
	  if (result.getStatus() == ITestResult.FAILURE) {
	  logger.log(LogStatus.FAIL, "Test failed " + result.getThrowable());
	  } else if (result.getStatus() == ITestResult.SKIP) {
	  logger.log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
	  } else {
	 logger.log(LogStatus.PASS, "Test passed");
	 report.endTest(logger);
	  }
  }
  @AfterTest
  public void endReport()
  {
  report.flush();
  report.close();
  }
  @DataProvider
  public Object[][] Test() {
	  Excelsheet RR = new Excelsheet("C:\\Users\\Guddu\\eclipse-workspace\\ProjectTest\\src\\main\\java\\TestData\\Login Data.xlsx");
  int rows = RR.getrowgount(0);
  	   Object [][] data = new Object [rows][2];
  	   for(int i=0;i<rows;i++)
  	   {
  		   data[i][0] = RR.getData(0, i, 0);
  		   data[i][1]= RR.getData(0, i,1);
  		
  		   
  		   }
  	   return data;
  	  }
}
