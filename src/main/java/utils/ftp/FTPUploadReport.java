package main.java.utils.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import main.java.base.TestBase;

/**
 * @This class upload files from local computer to a remote FTP server using
 *       Apache Commons Net API.
 * 
 * @author Mohammed Mustafa
 */
public class FTPUploadReport extends TestBase {

	private static void showServerReply(FTPClient ftpClient) {
		String[] replies = ftpClient.getReplyStrings();
		if (replies != null && replies.length > 0) {
			for (String aReply : replies) {
				System.out.println("SERVER: " + aReply);
			}
		}
	}

	public static String ftpTransfer() {
		String server = "";
		int port = 21;
		String user = "";
		String pass = "";
		String outputFile = "";

		FTPClient ftpClient = new FTPClient();
		try {

			ftpClient.connect(server, port);
			showServerReply(ftpClient);
			int replyCode = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				System.out.println("Operation failed. Server reply code: " + replyCode);
			}
			boolean success = ftpClient.login(user, pass);
			showServerReply(ftpClient);
			if (!success) {
				System.out.println("Could not login to the server");
			}
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

			// uploads file using an OutputStream
			System.out.println("Start file uploading");
			File localFile = new File(extentReportPath.replace("\\eMed.html", "").replace("/eMed.html", ""));

			String dirName = "eMed" + System.currentTimeMillis();

			boolean created = ftpClient.makeDirectory("/var/www/html/" + dirName);
			showServerReply(ftpClient);
			if (created) {
				System.out.println("CREATED the directory: " + dirName);
			} else {
				System.err.println("COULD NOT create the directory: " + dirName);
			}
			ftpClient.sendSiteCommand("chmod " + "755 " + "/var/www/html/" + dirName);

			for (File file : localFile.listFiles()) {
				InputStream inputStream = new FileInputStream(localFile + "/" + file.getName());

				String remoteFile = file.getName();

				OutputStream outputStream = ftpClient.storeFileStream("/var/www/html/" + dirName + "/" + remoteFile);
				byte[] bytesIn = new byte[4096];
				int read = 0;

				while ((read = inputStream.read(bytesIn)) != -1) {
					outputStream.write(bytesIn, 0, read);
				}
				inputStream.close();
				outputStream.close();

				boolean completed = ftpClient.completePendingCommand();
				if (completed) {
					System.out.println("File is uploaded successfully.");
				}

				ftpClient.sendSiteCommand("chmod " + "755 " + "/var/www/html/" + dirName + "/" + remoteFile);
				outputFile = server + "/" + dirName + "/" + "eMed.html";
			}
			System.out.println("Navigate to url to see file hosted ====> " + outputFile);
			return outputFile;

		} catch (IOException ex) {
			System.out.println("Error: " + ex.getMessage());
			ex.printStackTrace();
			return null;

		} finally {
			try {
				if (ftpClient.isConnected()) {
					ftpClient.logout();
					ftpClient.disconnect();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
