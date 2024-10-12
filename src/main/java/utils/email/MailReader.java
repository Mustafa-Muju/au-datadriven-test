package main.java.utils.email;

import javax.mail.*;
import javax.mail.search.FlagTerm;

import org.apache.commons.codec.binary.Base64;

import encryptusercredentials.EncryptCredentails;
import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

/**
 * 
 * @author Mohammed Mustafa
 *
 */
public class MailReader extends TestBase{

	private static Message[] messages;

	/**
	 * This method is used to read the content from the email for the specified
	 * subject searched
	 * 
	 * @param subjectTitle
	 * @return
	 * @throws Exception
	 */
	public static String readMail(String subjectTitle) throws Exception {

		mailFetcher();

		boolean flag = false;
		String emailTextBody = "";
		int count = 0;
		long millis = 0;
		while (messages.length > count) {
			if (messages[count].getSubject().equals(subjectTitle)) {

				DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
				Date date = (Date) formatter.parse(messages[count].getSentDate().toString());
				millis = date.getTime();

				flag = true;
				messages[count].setFlag(Flags.Flag.SEEN, true);
				break;
			}

			messages[count].setFlag(Flags.Flag.SEEN, true);
			count++;
		}

		if (flag) {

			long mailReceivedActual = (CommonFunctions.currentMilliseconds() / 60000) - ((millis / 60000));

			if (10 > mailReceivedActual) {
				emailTextBody = getEmailBody(messages[count]);
			} else {
				CommonFunctions.logErrorMessageStopExecution("No recent emails on subject " + subjectTitle
						+ " for the past ten minutes. Scenario failed...\nLast mail received at "
						+ (mailReceivedActual / 60) + " hrs " + (mailReceivedActual % 60) + " mins ago.");
			}

		} else {
			CommonFunctions.logErrorMessageStopExecution(
					"No new emails on subject " + subjectTitle + " received to the inbox. Scenario failed");
		}

		return emailTextBody;
	}

	/**
	 * This method for mail Fetcher
	 * 
	 * @throws Exception
	 *
	 */

	public static void mailFetcher() throws Exception {

		Thread.sleep(7000);
		Session session = Session.getDefaultInstance(new Properties());
		Store store = session.getStore("imaps");
		store.connect("imap.googlemail.com", 993,
				new EncryptCredentails().decrypt(CommonFunctions.getPropertyValues().getProperty("username")),
				new EncryptCredentails().decrypt(CommonFunctions.getPropertyValues().getProperty("password")));
		Folder inbox = store.getFolder("INBOX");
		inbox.open(Folder.READ_WRITE);

		// Fetch unseen messages from inbox folder
		messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

		// Sort messages from recent to oldest
		Arrays.sort(messages, (m1, m2) -> {
			try {
				return m2.getSentDate().compareTo(m1.getSentDate());
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		});
	}

	/**
	 * This method to get the EmailBody
	 * 
	 * 
	 * @param email
	 * 
	 * @throws Exception
	 *
	 *
	 */

	public static String getEmailBody(Message email) throws IOException, MessagingException {

		String line, emailContentEncoded;
		StringBuffer bufferEmailContentEncoded = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(email.getInputStream()));
		while ((line = reader.readLine()) != null) {
			bufferEmailContentEncoded.append(line);
		}

		emailContentEncoded = bufferEmailContentEncoded.toString();

		if (email.getContentType().toLowerCase().contains("multipart/related")) {

			emailContentEncoded = emailContentEncoded.substring(emailContentEncoded.indexOf("base64") + 6);
			emailContentEncoded = emailContentEncoded.substring(0, emailContentEncoded.indexOf("Content-Type") - 1);

			String emailContentDecoded = new String(new Base64().decode(emailContentEncoded.toString().getBytes()));
			return emailContentDecoded;
		}

		return emailContentEncoded;

	}
	
}