package stepDefination;

import static io.restassured.RestAssured.given;

import static org.junit.Assert.*;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {

	ResponseSpecification resspec;
	RequestSpecification res;
	Response response;
	TestDataBuild data = new TestDataBuild();
	JsonPath js;
	static String placeID;

	@Given("Add place payload {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException {

		// Write code here that turns the phrase above into concrete actions
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		res = given().spec(requestSpeicification()).body(data.addPlacePayload(name, language, address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpmethod) {

		// Write code here that turns the phrase above into concrete actions

		// Constructor will be called with value of resource which you pass
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		// response =
		// res.when().post(resourceAPI.getResource()).then().spec(resspec).extract().response();

		if (httpmethod.equalsIgnoreCase("POST"))
			response = res.when().post(resourceAPI.getResource());
		else if (httpmethod.equalsIgnoreCase("GET"))
			response = res.when().get(resourceAPI.getResource());

		String responseString = response.asString();
		System.out.println(responseString);
	}

	@Then("the API call is successful with status code {int}")
	public void the_api_call_is_successful_with_status_code(Integer int1) {

		// Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {

		// Write code here that turns the phrase above into concrete actions
		assertEquals(getJsonPath(response, key), value);
	}

	@Then("Verify place_Id created maps to  {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {

		// Prepare requestSpec
		placeID = (String) getJsonPath(response, "place_id");
		res = given().spec(requestSpeicification()).queryParam("place_id", placeID);
		user_calls_with_http_request(resource, "GET");
		String actualName = (String) getJsonPath(response, "name");
		assertEquals(actualName, expectedName);

	}

	@Given("DeletePlace Palyload")
	public void delete_place_palyload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		
		res=given().spec(requestSpeicification()).body(data.deletePlacePayload(placeID));
		
	}


}
