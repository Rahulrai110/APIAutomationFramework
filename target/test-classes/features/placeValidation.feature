Feature: Validating Place API's

@AddPlace @Regression
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
Given Add place payload "<name>" "<language>" "<address>"
When user calls "AddPlaceAPI" with "POST" http request
Then the API call is successful with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And Verify place_Id created maps to  "<name>" using "getPlaceAPI"

Examples:
		| name 	| language	| address							|
		|AAhouse| English 	| World cross center	|
#		|BBhouse| Spanish		| Sea cross center		|


@DeletePlace @Regression
Scenario: Verify if delete place functionality is working

Given DeletePlace Palyload
When user calls "deletePlaceAPI" with "POST" http request
Then the API call is successful with status code 200
And "status" in response body is "OK"


