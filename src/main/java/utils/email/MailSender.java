package main.java.utils.email;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.zeroturnaround.zip.ZipUtil;

import encryptusercredentials.EncryptCredentails;
import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

/**
 * 
 * @author Mohammed Mustafa
 * 
 * @This class sends test execution report via email
 *
 */
public class MailSender extends TestBase {
	
	private static String email_username = "";
	private static String email_password = "";
	private static String email_from = "";
	private static String email_smtp_server = "";
	
	/**
	 * 
	 * @This method to get the configuration values of the sender mail
	 * 
	 * @throws Exception
	 */
	private static void getConfigurations() throws Exception {

		if (System.getenv("JOB_NAME") != null && System.getProperty("os.name").contains("Linux")) {
			email_username = System.getenv("SES_USERNAME");
			email_password = System.getenv("SES_PASSWORD");
			email_from = "";
			email_smtp_server = "email-smtp.us-east-1.amazonaws.com";

		} else {
			email_username = new EncryptCredentails()
					.decrypt(CommonFunctions.getPropertyValues().getProperty("emailusername"));
			email_password = new EncryptCredentails()
					.decrypt(CommonFunctions.getPropertyValues().getProperty("emailpassword"));
			email_from = new EncryptCredentails().decrypt(CommonFunctions.getPropertyValues().getProperty("emailusername"));
			email_smtp_server = "smtp.gmail.com";
		}
	}

	/**
	 * @This method to send the Report
	 * 
	 *
	 * @throws Exception
	 */

	public static void sendReport() throws Exception {

		getConfigurations();

		final String username = email_username;
		final String password = email_password;
		String fromEmail = email_from;
		String toEmail = toReport;

		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", email_smtp_server);
		properties.put("mail.smtp.port", "587");

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		// Start our mail message
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(fromEmail));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail.split(Pattern.quote("|"))[0]));
			if (toEmail.split(Pattern.quote("|")).length == 2) {
				msg.setRecipients(Message.RecipientType.CC,
						InternetAddress.parse(toEmail.split(Pattern.quote("|"))[1]));
			}

			DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
			Date date = new Date();
			msg.setSubject(env.toUpperCase() + " " + tagName.toUpperCase() + " " + determineReportStatus()
			+ ": eMed Automation Report " + dateFormat.format(date));

			Multipart emailContent = new MimeMultipart();

			// Text body part
			MimeBodyPart textBodyPart = new MimeBodyPart();

			// Email body data creation
			String htmlBody = MailBody.emailBodyCreator(testDuration(),CommonFunctions.capitalize(tagName));

			// Email body message creation
			StringBuilder sb = new StringBuilder();
			sb.append(htmlBody);
			textBodyPart.setContent(sb.toString(), "text/html");

			// Attachment body part.
			MimeBodyPart pdfAttachment = new MimeBodyPart();

			File[] attachFiles = new File(extentReportPath.replace("\\eMed.html", "").replace("/eMed.html", ""))
					.listFiles();

			if (attachFiles.length > 0) {
				// attach file
				attachFile(attachFiles[0], emailContent, pdfAttachment);
				if (attachFiles.length > 1) {
					for (int index = 1; index < attachFiles.length; index++) {
						attachFile(attachFiles[index], emailContent, new MimeBodyPart());
					}
				}
			}

			// Attach body parts
			emailContent.addBodyPart(textBodyPart);
			emailContent.addBodyPart(pdfAttachment);

			// Attach multipart to message
			msg.setContent(emailContent);

			Transport.send(msg);
			System.out.println("Report has mailed successfully. Please check your inbox!!!");
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method is used to determine the status of report whether passed or
	 * failed
	 *
	 * @return
	 */
	public static String determineReportStatus() {
		String result = "";

		if (failure == 0) {
			result = "PASSED";
		} else {
			result = "FAILED";
		}

		return result;

	}

	/**
	 * Calculate the duration of the total test execution
	 *
	 * @return
	 * @throws Exception
	 */
	public static String testDuration() {

		String hrs = "00";
		String mins = "00";
		String secs = "00";

		LocalTime end = LocalTime.now();
		String[] timeStamp = Duration.between(startTime, end).toString().replaceAll("P|T", "")
				.replaceAll("\\.(.*)|S", "").replaceAll("M|H", " ").split(" ");

		switch (timeStamp.length) {
		case 1:
			secs = timeStamp[0];
			if (Integer.parseInt(secs) <= 9) {
				secs = "0" + secs;
			}
			break;

		case 2:
			mins = timeStamp[0];
			if (Integer.parseInt(timeStamp[0]) <= 9) {
				mins = "0" + timeStamp[0];
			}

			secs = timeStamp[1];
			if (Integer.parseInt(secs) <= 9) {
				secs = "0" + secs;
			}
			break;

		case 3:
			hrs = timeStamp[0];
			if (Integer.parseInt(timeStamp[0]) <= 9) {
				mins = "0" + timeStamp[0];
			}

			mins = timeStamp[1];
			if (Integer.parseInt(timeStamp[1]) <= 9) {
				mins = "0" + timeStamp[1];
			}

			secs = timeStamp[2];
			if (Integer.parseInt(secs) <= 9) {
				secs = "0" + secs;
			}
			break;

		default:
			break;

		}

		String duration = hrs + "hrs " + mins + "mins " + secs + "secs";
		return duration;
	}

	/**
	 * @This method to attach the file
	 * 
	 * @param file
	 * 
	 * @param multipart
	 * 
	 * @param messageBodyPart
	 *
	 * @throws Exception
	 */

	public static void attachFile(File file, Multipart multipart, MimeBodyPart messageBodyPart)
			throws MessagingException {
		DataSource source = new FileDataSource(file);
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(file.getName());
		multipart.addBodyPart(messageBodyPart);
	}

	/**
	 * @This method for file zip
	 * 
	 *
	 * @throws Exception
	 */

	public static void fileZip() throws Exception {

		ZipUtil.pack(new File(extentReportPath.replace("\\eMed.html", "").replace("/eMed.html", "")),
				new File(System.getProperty("user.dir") + "//test-output//eMedReport//eMedEmailReport.zip"));

		Thread.sleep(2000);
		System.out.println("File zipped successfully");
		CommonFunctions.moveFileToSpecificPath(
				System.getProperty("user.dir") + "//test-output//eMedReport//eMedEmailReport.zip",
				extentReportPath.replace("\\eMed.html", "").replace("/eMed.html", "") + "//eMedEmailQAReport.zip");
	}

}
