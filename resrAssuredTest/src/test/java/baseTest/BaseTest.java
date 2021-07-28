package baseTest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import utils.ExtentReportListner;
import utils.FileandEnv;

@Listeners(ExtentReportListner.class)
public class BaseTest extends ExtentReportListner{
	
	@BeforeClass
	public void baseTest() {
		
		System.out.println("Welcome to automation world");
		//RestAssured.baseURI = FileandEnv.envAndFile().get("ServerUrl");
	}

}
