package resrAssuredTest;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import utils.APIPath;
import utils.GetExcel;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import baseTest.BaseTest;
import org.json.simple.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class AliceAcceptFaberCon extends BaseTest{
	static ExtentReports extent = new ExtentReports();

	@Test(priority = 2) //TestNG annotation
	void postTest() throws FileNotFoundException {
		RestAssured.baseURI = APIPath.apiPath.FABER_TO_ALICE;
		RequestSpecification httpRequest = RestAssured.given();

		//Here we created data through json  object that we can send along with POST request
		JSONObject requestParams = new JSONObject();
	
		requestParams.put(toJsonData(0, 0, 0), toJsonData(0, 0, 1));
		requestParams.put(toJsonData(0, 1, 0), toJsonData(0, 1, 1));
		
		JSONArray ja = new JSONArray();
		ja.add(toJsonData(0, 2, 1));
		requestParams.put(toJsonData(0, 2, 0), ja);
		
		requestParams.put(toJsonData(0, 3, 0), toJsonData(0, 3, 1));

		String val = toJsonData(0, 4, 1);
		System.out.println(val);
		requestParams.put(toJsonData(0, 4, 0), toJsonData(0, 4, 1));
		System.out.println("cell data is==========="+requestParams);
		System.out.println(requestParams.get(toJsonData(0, 4, 0)));
		// specifying headers here, stating that the request body is json
		httpRequest.header("Content-Type", "application/json");

		// add the json body to the request payload
		httpRequest.body(requestParams);

		// Sending POST request
		Response response = httpRequest.request(Method.POST, "connections/receive-invitation");

		//capturing response body to perform validations
		ResponseBody responseBody = response.getBody();
		//Reporter.log(responseBody); // to log the response
		System.out.println("res body as string: "+responseBody);
		JsonPath jpath = response.jsonPath();
		String userid = jpath.get("UserId");
		//Assert.assertEquals(userid, "rp01");
		// to capture response code through getStatusCode method.
		int status = response.getStatusCode();

		//Assert.assertEquals(status, 201);
		//Assert.assertEquals(responseBody.contains("Rajesh"), true);
		//Assert.assertEquals(responseBody.contains("QA"), true);
		//Assert.assertEquals(responseBody.contains("25"), true);
	}

	String toJsonData(int a,int b,int c) throws FileNotFoundException
	{
		String id = GetExcel.getData(a, b, c);
		return id;
	}
}