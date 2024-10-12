package main.java.utils.email;

import org.jsoup.Jsoup;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

/**
 * 
 * @author Mohammed Mustafa
 *
 */
public class AHEMMailRestAPI extends TestBase {
	private static String bearerToken = "";
	private static String emailIdentity = "";
	private static String emailname = "";

	/**
	 * This method to get the Bearer Token
	 *
	 * @throws Exception
	 *
	 */

	public static void getAHEMBearerToken() throws Exception {

		try {

			RestAssured.baseURI = "https://hostux.ninja/api/auth/token";

			RequestSpecification httpRequest = RestAssured.given().relaxedHTTPSValidation();

			Response response = httpRequest.header("Content-Type", "application/json").body("{}").post();

			JsonPath jsonPathEvaluator = response.jsonPath();

			bearerToken = jsonPathEvaluator.getString("token");

		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Error while generating bearer token in ahem's mail");
		}
	}

	/**
	 * This method to get the Email ID
	 *
	 * @param inboxName
	 *
	 * @throws Exception
	 *
	 */

	public static void getEmailId(String inboxName, String subjectName) throws Exception {

		Response response = null;
		boolean flag = false;
		Thread.sleep(10000);
		try {
			RestAssured.baseURI = "https://hostux.ninja/api/mailbox/" + inboxName + "/email";

			RequestSpecification httpRequest = RestAssured.given().relaxedHTTPSValidation();

			response = httpRequest.header("Authorization", "Bearer " + bearerToken).get();

			JsonPath jsonPathEvaluator = response.jsonPath();

			for (int mailSubject = 0; mailSubject < jsonPathEvaluator.getList("emailId").size(); mailSubject++) {

				if (jsonPathEvaluator.getString("subject[" + mailSubject + "]").equalsIgnoreCase(subjectName)) {
					emailIdentity = jsonPathEvaluator.getString("emailId[" + mailSubject + "]");
					flag = true;
					break;
				}
			}
		} catch (Exception e) {
			System.err.println(response.body().asPrettyString() + "\n" + response.getStatusCode());
			CommonFunctions.logErrorMessageStopExecution("Error while fetching email Id in ahem's mail");
		}

		if (!flag) {
			System.err.println(response.body().asPrettyString() + "\n" + response.getStatusCode());
			CommonFunctions.logErrorMessageStopExecution("Mail not received to inbox. Please trouble shoot the error");
		}

	}

	/**
	 *
	 * @This method to get the email body from the response using ahem's mail
	 *
	 * @param inboxName
	 *
	 * @param emailId
	 *
	 * @throws Exception
	 *
	 */

	public static Response getEmailBody(String inboxName, String emailId) throws Exception {

		Response response = null;
		try {
			RestAssured.baseURI = "https://hostux.ninja/api/mailbox/" + inboxName + "/email/" + emailId + "";

			RequestSpecification httpRequest = RestAssured.given().relaxedHTTPSValidation();

			response = httpRequest.header("Authorization", "Bearer " + bearerToken).get();

			return response;

		} catch (Exception e) {
			System.err.println(response.body().asPrettyString() + "\n" + response.getStatusCode());
			CommonFunctions.logErrorMessageStopExecution("Error while fetching email code in ahem's mail");
			return null;
		}
	}

	/**
	 * 
	 * @This method to fetch the whole body of the email and remove html tags and
	 *       returns raw body data using Ahem's mail server
	 * 
	 * @param emailLabel
	 * @param subjectName
	 * @param codeName
	 * 
	 * @return String
	 * @throws Exception
	 */
	public static String fetchMail(String emailLabel, String subjectName, String codeName) throws Exception {
		getAHEMBearerToken();
		getEmailId(emailLabel, subjectName);
		JsonPath jsonPathEvaluator = getEmailBody(emailLabel, emailIdentity).jsonPath();
		String rawBody = "";

		if (emailname.contains("NAVICA")) {
			rawBody = Jsoup.parse(jsonPathEvaluator.getString("textAsHtml")).text();

		} else {
			rawBody = Jsoup.parse(jsonPathEvaluator.getString("html")).text();
		}
		CommonFunctions.logMessage("Opened the Ahem's mail inbox and fetched the email for " + codeName);
		return rawBody;
	}

}