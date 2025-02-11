package stepDefination;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {

		// Write code that will give you place id
		// Execute this code that will give you this place Id

		StepDefination sd = new StepDefination();

//		if (StepDefination.placeID == null) {
//
//			sd.add_place_payload("Rahul", "French", "Asia");
//			sd.user_calls_with_http_request("AddPlaceAPI", "POST");
//			sd.verify_place_id_created_maps_to_using("Rahul", "getPlaceAPI");
//
//		}

	}

}
