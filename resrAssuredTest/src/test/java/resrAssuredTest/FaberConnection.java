package resrAssuredTest;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.APIPath;
import utils.GetExcel;
import utils.setExcelData;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import baseTest.BaseTest;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.*;
import org.json.JSONArray;
import org.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class FaberConnection extends BaseTest {


	@Test(priority = 1) // TestNG annotation
	void faber_Create_Invitation() throws IOException, ParseException {


		RestAssured.baseURI = APIPath.apiPath.FABER_INVITES_ALICE;
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.header("Content-Type", "application/json");
		// Sending POST request
		Response response = httpRequest.request(Method.POST, "connections/create-invitation");

		System.out.println("aks:    ====   "+response.asPrettyString());
		
		
		  Map<String, Object[]> invitations= response.jsonPath().getMap("invitation");
		  System.out.println(invitations);
		  
		  Map<String, Object[]> invitationmap = new LinkedHashMap<String, Object[]>();
		  invitationmap.put("@type",new Object[] {invitations.get("@type")});
		  invitationmap.put("@id",new Object[] {invitations.get("@id")});
		  invitationmap.put("recipientKeys",new Object[]
		  {invitations.get("recipientKeys")}); invitationmap.put("label",new Object[]
		  {invitations.get("label")}); invitationmap.put("serviceEndpoint",new Object[]
		  {invitations.get("serviceEndpoint")});
		  
		  JSONObject json = new JSONObject(invitationmap);
		  setExcelData.toexcelsheet(invitationmap);
		  
		  System.out.println("invitation response body " + json.toString());
		  String responseBody = response.getBody().asPrettyString();
		  Reporter.log(responseBody); int status = response.getStatusCode();
		  Assert.assertEquals(status, 200);
		  Assert.assertEquals(responseBody.contains("recipientKeys"), true);
		  Assert.assertEquals(responseBody.contains("invitation"), true);
		  Assert.assertEquals(responseBody.contains("invitation_url"), true);
		 

	}
	
	
	
}