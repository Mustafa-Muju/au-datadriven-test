package main.java.utils.email;

import java.util.regex.Pattern;

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
public class MaillinatorRestAPI extends TestBase {

	private static String api_token = "";
	private static String emailIdentity = "";
	/**
	 * 
	 * @This method to get the Email ID
	 * 
	 * @param email
	 * @param subjectName
	 * @throws Exception
	 */

	public static void getInbox(String email, String subjectName) throws Exception {

		String inboxName = email.split(Pattern.quote("@"))[0];
		String domainName = email.split(Pattern.quote("@"))[1];

		Response response = null;
		boolean flag = false;
		Thread.sleep(10000);
		try {
			RestAssured.baseURI = "https://mailinator.com/api/v2/domains/" + domainName + "/inboxes/" + inboxName
					+ "?limit=2&sort=descending";

			RequestSpecification httpRequest = RestAssured.given().relaxedHTTPSValidation();

			response = httpRequest.header("Authorization", api_token).get();

			JsonPath jsonPathEvaluator = response.jsonPath();

			for (int mailSubject = 0; mailSubject < jsonPathEvaluator.getList("msgs").size(); mailSubject++) {

				if (jsonPathEvaluator.getString("msgs[" + mailSubject + "].subject").equalsIgnoreCase(subjectName)) {
					emailIdentity = jsonPathEvaluator.getString("msgs[" + mailSubject + "].id");
					jsonPathEvaluator.getString("msgs[" + mailSubject + "].origfrom");
					flag = true;
					break;
				}
			}
		} catch (Exception e) {
			System.err.println(response.body().asPrettyString() + "\n" + response.getStatusCode());
			CommonFunctions.logErrorMessageStopExecution("Error while fetching email id from mailinator");
		}

		if (!flag) {
			System.err.println(response.body().asPrettyString() + "\n" + response.getStatusCode());
			CommonFunctions.logErrorMessageStopExecution("Mail not received to inbox with subject name - "+subjectName+". Please trouble shoot the error");
		}

	}

	/**
	 * 
	 * @This method to get the email body from the response using mailinator email
	 * 
	 * @param email
	 * @param emailId
	 * @return
	 * @throws Exception
	 */
	public static Response getEmailBody(String email, String emailId) throws Exception {

		String inboxName = email.split(Pattern.quote("@"))[0];
		String domainName = email.split(Pattern.quote("@"))[1];

		try {
			RestAssured.baseURI = "https://mailinator.com/api/v2/domains/" + domainName + "/inboxes/" + inboxName
					+ "/messages/" + emailId + "";

			RequestSpecification httpRequest = RestAssured.given().relaxedHTTPSValidation();

			Response response = httpRequest.header("Authorization", api_token).get();

			return response;

		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Error while fetching email id from mailinator");
			return null;
		}
	}

	/**
	 * 
	 * @This method to fetch the verification code from the response using mailinator mail
	 * 
	 * @param email
	 * @param subjectName
	 * @throws Exception
	 */
	public static void fetchCode(String email, String subjectName, String codeName) throws Exception {

		getInbox(email, subjectName);
		JsonPath jsonPathEvaluator = getEmailBody(email, emailIdentity).jsonPath();

		try {
				verifyCode.set(CommonFunctions.regexText("(\\d+)",
						Jsoup.parse(jsonPathEvaluator.getString("parts[0].body")).text()));

			CommonFunctions.logMessage(
					"Verification code fetched from mailinator mail server for " + codeName + " ===> " + getVerifyCode());
			Code.set(getVerifyCode());

		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Error while fetching code using mailinator...");
		}
	}

	/**
	 * 
	 * @This method to fetch the whole body of the email and remove html tags and
	 *       returns raw body data using mailinator mail server
	 * 
	 * @param email
	 * @param subjectName
	 * @return
	 * @throws Exception
	 */
	public static String fetchMail(String email, String subjectName, String codeName) throws Exception {

		getInbox(email, subjectName);
		JsonPath jsonPathEvaluator = getEmailBody(email, emailIdentity).jsonPath();

		String rawBody = Jsoup.parse(jsonPathEvaluator.getString("parts[0].body")).text();
		CommonFunctions.logMessage("Opened the mailinator mail inbox and fetched the email for " + codeName);
		
		return rawBody;
	}

}
