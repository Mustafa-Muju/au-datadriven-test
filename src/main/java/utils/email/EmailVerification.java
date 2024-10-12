package main.java.utils.email;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

public class EmailVerification extends TestBase {

	/**
	 * 
	 * @This method to fetch the whole body of the email and remove html tags and
	 *       returns raw body data using Gmail server
	 * 
	 * @param mailSubject
	 * @param codeName
	 * @return
	 * @throws Exception
	 */
	public static String fetchMail(String mailSubject, String codeName) throws Exception {

		String rawMail = Jsoup.parse(MailReader.readMail(mailSubject)).text();
		CommonFunctions.logMessage("Opened the gmail inbox and fetched the email for " + codeName);
		return rawMail;
	}

	/**
	 * 
	 * @This method is used to store multiple email's and get desired domain based
	 *       on requirement
	 * 
	 * @param eName
	 * @return
	 * @throws Exception
	 */
	public static String getEmail(String eName) throws Exception {

		String email1 = CommonFunctions.EmailGenTimeStamp("emed-au", "awslabs.emed.com");
		String email2 = CommonFunctions.EmailGenTimeStamp("emed-au", "awslabs.emed.com");

		Map<String, String> emails = new HashMap<String, String>();
		emails.put("email1", email1);
		emails.put("email2", email2);

		SESReadMail.generateEmailInbox(emails.get(eName));

		return emails.get(eName);

	}

	/**
	 * 
	 * @This method is used to get the raw email body with excluding the html tags
	 *       from the specified mail server
	 * 
	 * @param email
	 * @param mailSubject
	 * @param codeName
	 * 
	 * @return String
	 * @throws Exception
	 * 
	 */
	public static String getEmailBodyFromEmailServer(String email, String mailSubject, String codeName)
			throws Exception {
		String domainServer = email.split("@")[1];
		String labelName = email.split("@")[0];
		String rawBody = "";

		switch (domainServer) {

		case "gmail.com":
			rawBody = fetchMail(mailSubject, codeName);
			break;

		case "hostux.ninja":
			rawBody = AHEMMailRestAPI.fetchMail(labelName, mailSubject, codeName);
			break;

		case "itemedteam.testinator.com":
			rawBody = MaillinatorRestAPI.fetchMail(email, mailSubject, codeName);
			break;

		case "awslabs.emed.com":
			rawBody = SESReadMail.fetchMail(email, mailSubject, codeName);
			break;

		default:
			break;
		}

		return rawBody;
	}

	/**
	 * 
	 * @This method to get the verification code for new eMed user account
	 * 
	 * @throws Exception
	 */
	public static void getVerifyCodeForEmedSignUp() throws Exception {
		try {
			String mail = EmailVerification.getEmailBodyFromEmailServer(getEmailId(), "Verify your email",
					"eMed account creation");
			verifyCode.set(CommonFunctions.regexText("below:(.*)Do", mail).replaceAll("below|:|Do|=|\\s+", "").trim());
			CommonFunctions.logMessage("Verification code from mail server for eMed new user ===> " + getVerifyCode());

		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Unable to fetch the eMed new user verification code..");
		}

	}

	/**
	 * 
	 * @This method to get the verification code for eMed reset password
	 * 
	 * @throws Exception
	 */
	public static void getVerifyCodeForEmedReset() throws Exception {
		try {
			String mail = EmailVerification.getEmailBodyFromEmailServer(getEmailId(), "Reset your password",
					"eMed reset password");
			verifyCode.set(CommonFunctions.regexText("below:(.*)Do", mail).replaceAll("below|:|Do|=|\\s+", "").trim());
			CommonFunctions.logMessage("Verification code from mail server for eMed password reset ===> " + getVerifyCode());

		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Unable to fetch the eMed reset password verification code..");
		}

	}

	/**
	 * 
	 * @This method to get the verification code for navica account creation
	 * 
	 * @throws Exception
	 */
	public static void getVerifyCodeForNavicaSignUp() throws Exception {
		try {
			String mail = EmailVerification.getEmailBodyFromEmailServer(getNavicaEmail(),
					"Please confirm your email address", "navica account creation");
			verifyCode.set(CommonFunctions.regexText("\\d{6,6}", mail).trim());
			CommonFunctions.logMessage("Verification code from mail server for navica new user ===> " + getVerifyCode());

		} catch (Exception e) {
			CommonFunctions
					.logErrorMessageStopExecution("Unable to fetch the navica account creation verification code..");
		}

	}

	/**
	 * 
	 * @This method to get the verification code for navica reset password
	 * 
	 * @throws Exception
	 * @return code
	 */
	public static void getVerifyCodeForNavicaReset() throws Exception {
		try {
			String mail = EmailVerification.getEmailBodyFromEmailServer(getNavicaEmail(), "Password reset for NAVICA",
					"navica reset password");
			verifyCode.set(CommonFunctions.regexText("\\d{6,6}", mail).trim());
			CommonFunctions.logMessage("Verification code from mail server for navica password reset ===> " + getVerifyCode());

		} catch (Exception e) {
			CommonFunctions
					.logErrorMessageStopExecution("Unable to fetch the navica password reset verification code..");
		}

	}
	
	/**
	 * 
	 * @This method to verify the eMed session lab report
	 * 
	 * @throws Exception
	 */
	public static void verifyEmedLabReport(String email) throws Exception {

		String msgMail = EmailVerification.getEmailBodyFromEmailServer(email, "Your eMed Lab Report",
					"Your eMed Lab Report email");

		CommonFunctions.logMessage(
				"Your eMed Lab Report email has successfully received.\n************Email Verification>>>>Your eMed Lab Report Details<<<<*****************");

		String customerName = CommonFunctions.regexText("Hi(\\s\\w+),?", msgMail).replaceAll("Hi|,|\\s+", "").trim();

		String processedName = CommonFunctions.getdata("FirstName").split(Pattern.quote("|"))[0];
		if (processedName.equalsIgnoreCase(customerName)) {
			CommonFunctions.logMessage("Customer name is same as displayed in lab test: " + customerName);
		} else {
			CommonFunctions.logErrorMessage("Expected customer name " + processedName
					+ " doesn't match the actual customer name in lab test " + customerName);
		}
	}
	
	/**
	 * 
	 * @This method to validate the eMed order shipped email
	 * 
	 * @throws Exception
	 */
	public static void verifyOrderShippedEmail() throws Exception {
		String msgMail = "";
		try {
			msgMail = EmailVerification.getEmailBodyFromEmailServer(getEmailId(), "Emed Order Shipped",
					"eMed order shipped");

		} catch (Exception e) {
			CommonFunctions
					.logErrorMessageStopExecution("Error gogomed order approval mail is not received to email inbox..");
		}

		CommonFunctions.logMessage(
				"eMed Order shipped email has successfully received.\n************Email Verification>>>>Order shipping Details<<<<*****************");

		String orderNumber = CommonFunctions.regexText("#(.\\d+)", msgMail).replaceAll("#", "").trim();
		String customerFirstName = CommonFunctions.regexText("Hi(.*?),", msgMail).replaceAll("Hi|,|\\s+", "").trim();
		String trackingNumber = CommonFunctions.regexText("Tracking Number:(.*?)\\[https", msgMail)
				.replaceAll("Tracking Number:|\\[https|\\s+", "").trim();

		if (getConfirmationNumber().equalsIgnoreCase(orderNumber)) {
			CommonFunctions.logMessage("Order number is same as displayed in eMed Webpage: " + orderNumber);
		} else {
			CommonFunctions.logErrorMessage("Expected order number " + getConfirmationNumber()
					+ " doesn't match the actual order number in eMed Webpage " + orderNumber);
		}

		String processedName = CommonFunctions.getdata("FirstName").split(Pattern.quote("|"))[0];
		if (processedName.equalsIgnoreCase(customerFirstName)) {
			CommonFunctions.logMessage("Customer name is same as displayed in eMed Webpage: " + customerFirstName);
		} else {
			CommonFunctions.logErrorMessage("Expected customer name " + processedName
					+ " doesn't match the actual customer name in eMed Webpage " + customerFirstName);
		}

		// if (customerOrderTrackNumber.equalsIgnoreCase(trackingNumber)) {
//		CommonFunctions.logMessage("Tracking number for order shipped is same as while used for approved: " + trackingNumber);
//	} else {
//		CommonFunctions.logErrorMessage("Expected tracking number " + customerOrderTrackNumber
//				+ " doesn't match the actual tracking number while approved " + trackingNumber);
//	}

		CommonFunctions.logMessage("Tracking number for the order shipped " + trackingNumber);
	}

}
