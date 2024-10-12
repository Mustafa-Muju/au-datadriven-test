package main.java.utils.awssdk;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

/**
 * 
 * @author Mohammed Mustafa
 * 
 *         This class contains rest api's of eMed account creation flow
 *
 */
public class AWSResteMedAccountCreation extends TestBase {

	private static Map<String, Object> headerMap = new HashMap<String, Object>();
	private static String userAgent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Safari/537.36";

	/**
	 * This method to get the register as new user
	 * 
	 * @throws Exception
	 *
	 */

	public static void registereMedNewUser(String clientId, String username, String password) throws Exception {
		Response response = null;
		try {

			RestAssured.baseURI = "https://cognito-idp.us-east-1.amazonaws.com/";

			headerMap.put("content-type", "application/x-amz-json-1.1");
			headerMap.put("Connection", "keep-alive");
			headerMap.put("Accept-Language", "en-US,en;q=0.9");
			headerMap.put("Accept-Encoding", "gzip, deflate,br");
			headerMap.put("Accept", "*/*");
			headerMap.put("sec-fetch-dest", "empty");
			headerMap.put("sec-fetch-mode", "cors");
			headerMap.put("sec-fetch-site", "cross-site");
			headerMap.put("x-amz-target", "AWSCognitoIdentityProviderService.SignUp");
			headerMap.put("x-amz-user-agent", "aws-amplify/0.1.x js");
			headerMap.put("User-Agent", userAgent);

			RequestSpecification httpRequest = RestAssured.given().config(RestAssured.config().encoderConfig(
					EncoderConfig.encoderConfig().encodeContentTypeAs("application/x-amz-json-1.1", ContentType.JSON)));

			String requestBody = "{\"ClientId\":\"" + clientId + "\",\"Username\":\"" + username + "\",\"Password\":\""
					+ password + "\",\"UserAttributes\":[],\"ValidationData\":null}";

			response = httpRequest.headers(headerMap).body(requestBody).post();

			if (!(response.getStatusCode() == 200)) {
				CommonFunctions.logErrorMessageStopExecution(
						"Request failed with the following response code ==>" + response.getStatusCode());
			}

		} catch (Exception e) {
			System.err.println(response.body().asPrettyString() + "\n" + response.getStatusCode());
			CommonFunctions.logErrorMessageStopExecution("Error while register eMed new user through rest api's");
		}
	}

	/**
	 * This method to verify the registered user by getting the code
	 * 
	 * @throws Exception
	 *
	 */

	public static void verifyeMedUser(String clientId, String username, String verifyCode) throws Exception {
		Response response = null;
		try {

			RestAssured.baseURI = "https://cognito-idp.us-east-1.amazonaws.com/";

			headerMap.put("content-type", "application/x-amz-json-1.1");
			headerMap.put("Connection", "keep-alive");
			headerMap.put("Accept-Language", "en-US,en;q=0.9");
			headerMap.put("Accept-Encoding", "gzip, deflate,br");
			headerMap.put("Accept", "*/*");
			headerMap.put("sec-fetch-dest", "empty");
			headerMap.put("sec-fetch-mode", "cors");
			headerMap.put("sec-fetch-site", "cross-site");
			headerMap.put("x-amz-target", "AWSCognitoIdentityProviderService.ConfirmSignUp");
			headerMap.put("x-amz-user-agent", "aws-amplify/0.1.x js");
			headerMap.put("User-Agent", userAgent);

			RequestSpecification httpRequest = RestAssured.given().config(RestAssured.config().encoderConfig(
					EncoderConfig.encoderConfig().encodeContentTypeAs("application/x-amz-json-1.1", ContentType.JSON)));

			String requestBody = "{\"ClientId\":\"" + clientId + "\",\"ConfirmationCode\":\"" + verifyCode
					+ "\",\"Username\":\"" + username + "\",\"ForceAliasCreation\":true}";

			response = httpRequest.headers(headerMap).body(requestBody).post();

			if (!(response.getStatusCode() == 200)) {
				CommonFunctions.logErrorMessageStopExecution(
						"Request failed with the following response code ==>" + response.getStatusCode());
			}
			

		} catch (Exception e) {
			System.err.println(response.body().asPrettyString() + "\n" + response.getStatusCode());
			CommonFunctions.logErrorMessageStopExecution("Error while verify eMed new user through rest api's");
		}
	}

	/**
	 * This method update the profile data for eMed account
	 * 
	 * @throws Exception
	 *
	 */

	public static void updateProfile(String env, String email, String token) throws Exception {
		Response response = null;
		try {

			if (!env.equalsIgnoreCase("prod")) {
				RestAssured.baseURI = "https://internal-api." + env + ".emed.com/myself/profile";
			} else {
				RestAssured.baseURI = "https://internal-api.emed.com/myself/profile";
			}

			headerMap.put("content-type", "application/json");
			headerMap.put("Authorization", "Bearer " + token);
			headerMap.put("Connection", "keep-alive");
			headerMap.put("Accept-Language", "en-US,en;q=0.9");
			headerMap.put("Accept-Encoding", "gzip, deflate,br");
			headerMap.put("Accept", "*/*");
			headerMap.put("User-Agent", userAgent);

			RequestSpecification httpRequest = RestAssured.given();

			String profileDate = CommonFunctions.getDateBasedonFormat("dd-MM-yyyy", 0);

			String requestBody = "{\"firstName\":\"" + CommonFunctions.getdata("FirstName") + "\",\"lastName\":\""
					+ CommonFunctions.getdata("LastName") + "\",\"email\":\"" + email
					+ "\",\"birthdate\":\"1998-01-03T18:30:00.000Z\",\"sex\":\""
					+ CommonFunctions.getdata("Gender").toLowerCase() + "\",\"phoneNumber\":\"+1"
					+ CommonFunctions.getdata("PhNumber") + "\",\"address\":{\"streetAddress\":\""
					+ CommonFunctions.getdata("Address") + "\",\"city\":\"" + CommonFunctions.getdata("City")
					+ "\",\"county\":\"New Castle County\",\"state\":\"" + CommonFunctions.getdata("State")
					+ "\",\"zipCode\":\"" + CommonFunctions.getdata("ZipCode") + "\",\"unitNumber\":\""
					+ CommonFunctions.getdata("BuildingNumber") + "\"},\"createdAt\":\"" + profileDate
					+ "\",\"updatedAt\":\"" + profileDate
					+ "\",\"race\":\"Asian\",\"ethnicity\":\"Hispanic or Latino\",\"navicaId\":null,\"b2bCode\":null"
					+ ""
					+ ",\"acceptedDiscloseResultsOn\":null,\"dateOfBirth\":\"1998-01-03T18:30:00.000Z\",\"gender\":\""
					+ CommonFunctions.getdata("Gender").toLowerCase() + "\",\"zipcode\":\""
					+ CommonFunctions.getdata("ZipCode") + "\",\"state\":\"" + CommonFunctions.getdata("State")
					+ "\",\"city\":\"" + CommonFunctions.getdata("City")
					+ "\",\"county\":\"New Castle County\",\"unitNumber\":\""
					+ CommonFunctions.getdata("BuildingNumber") + "\"}";

			response = httpRequest.headers(headerMap).body(requestBody).put();

			if (!(response.getStatusCode() == 200)) {
				CommonFunctions.logErrorMessageStopExecution(
						"Request failed with the following response code ==>" + response.getStatusCode());
			}

		} catch (Exception e) {
			System.err.println(response.body().asPrettyString() + "\n" + response.getStatusCode());
			CommonFunctions
					.logErrorMessageStopExecution("Error while update eMed new user profile data through rest api's");
		}
	}

}
