package main.java.utils.email;

import org.jsoup.Jsoup;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

public class SESReadMail extends TestBase {

	private static String emailSessionId = "";
	private static String messageId = "";

	/**
	 * 
	 * @This method to generate the email address and return the session id for the
	 *       inbox created
	 * 
	 * @param email
	 * @throws Exception
	 */
	public static void generateEmailInbox(String email) throws Exception {
		Response response = null;

		try {

			RestAssured.baseURI = "https://6j8oaw2fr7.execute-api.us-east-1.amazonaws.com/v0/create";

			RequestSpecification httpRequest = RestAssured.given();

			response = httpRequest.queryParam("address", email).get();

			JsonPath jsonPathEvaluator = response.jsonPath();

			if (jsonPathEvaluator.getString("message").contains("email address")) {
				emailSessionId = jsonPathEvaluator.getString("sessionid");
			} else {

			}

		} catch (Exception e) {
			System.err.println(response.body().asPrettyString() + "\n" + response.getStatusCode());
			CommonFunctions
					.logErrorMessageStopExecution("Error while generating email inbox using SES read email service");
		}
	}

	/**
	 * 
	 * @This method to get the email inbox created and get the message id
	 * 
	 * @param email
	 * @param subjectName
	 * @throws Exception
	 */
	public static void getInbox(String email, String subjectName) throws Exception {
		Response response = null;
		boolean flag = true;
		int count = 0;

		while (flag && count < 20) {

			try {

				RestAssured.baseURI = "https://6j8oaw2fr7.execute-api.us-east-1.amazonaws.com/v0/" + email + "";

				RequestSpecification httpRequest = RestAssured.given().urlEncodingEnabled(false);

				response = httpRequest.queryParam("sessionid", emailSessionId).get();

				JsonPath jsonPathEvaluator = response.jsonPath();

				for (int mailSubject = 0; mailSubject < jsonPathEvaluator.getList("Items").size(); mailSubject++) {
					if (jsonPathEvaluator.getString("Items[" + mailSubject + "].isNew").equalsIgnoreCase("true")) {

						if (jsonPathEvaluator.getString("Items[" + mailSubject + "].commonHeaders.subject")
								.equalsIgnoreCase(subjectName)) {
							messageId = jsonPathEvaluator.getString("Items[" + mailSubject + "].messageId");
							flag = false;
							break;
						}
					}
				}

			} catch (Exception e) {
				System.err.println(response.body().asPrettyString() + "\n" + response.getStatusCode());
				CommonFunctions.logErrorMessageStopExecution(
						"Error while fetching the email inbox created using SES read email service");
			}

			Thread.sleep(10000);
			count++;

		}

		if (flag) {
			System.err.println(response.body().asPrettyString() + "\n" + response.getStatusCode());
			CommonFunctions.logErrorMessageStopExecution("Mail not received to inbox with subject name - " + subjectName
					+ ". Please trouble shoot the error");
		}
	}

	/**
	 * 
	 * @This method to get the email content for the message id requested
	 * 
	 * @param email
	 * @return
	 * @throws Exception
	 */
	public static String getEmailContent(String email) throws Exception {
		Response response = null;
		String content = "";

		try {

			RestAssured.baseURI = "https://6j8oaw2fr7.execute-api.us-east-1.amazonaws.com/v0/" + email + "/" + messageId
					+ "";

			RequestSpecification httpRequest = RestAssured.given().urlEncodingEnabled(false);

			response = httpRequest.queryParam("sessionid", emailSessionId).get();

			content = response.getBody().asPrettyString();

		} catch (Exception e) {
			System.err.println(response.body().asPrettyString() + "\n" + response.getStatusCode());
			CommonFunctions
					.logErrorMessageStopExecution("Error while generating email inbox using SES read email service");
		}
		return content;
	}

	/**
	 * 
	 * @This method to fetch the whole body of the email and remove html tags and
	 *       returns raw body data using AWS SES Read mail server
	 * 
	 * @param email
	 * @param subjectName
	 * @param codeName
	 * 
	 * @return
	 * @throws Exception
	 * 
	 */
	public static String fetchMail(String email, String subjectName, String codeName) throws Exception {

		Thread.sleep(5000);
		generateEmailInbox(email);
		getInbox(email, subjectName);

		String rawBody = Jsoup.parse(CommonFunctions.regexText("html[\\s\\S]+\\/html", getEmailContent(email))).text();

		CommonFunctions.logMessage("Opened the AWS SES Read mail inbox and fetched the email for " + codeName);

		return rawBody;
	}

}
