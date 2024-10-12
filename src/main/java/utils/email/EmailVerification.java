package main.java.utils.email;

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
	public static String getEmail() throws Exception {

		String email = CommonFunctions.EmailGenTimeStamp("swaglab-au", "domain.com");
		SESReadMail.generateEmailInbox(email);

		return email;

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


}
