package main.java.flows.vlab.vlab1;

import encryptusercredentials.EncryptCredentails;
import main.java.base.TestBase;
import main.java.pages.vlab.vlab1.ProctorLandingPage;
import main.java.pages.vlab.vlab1.ProctorLoginPage;
import main.java.pages.vlab.vlab1.ProctorMyAccountPage;
import main.java.utils.CommonFunctions;

public class ProctorFlow extends TestBase {

	/**
	 * 
	 * This method for Proctor Login
	 * 
	 * @param uName
	 * @param password
	 * @throws Exception
	 *
	 */

	public static void ProctorLoginProcess() throws Exception {
		if (env.equalsIgnoreCase("prod")) {
			if (proctorCredentials != null) {
				proctorUserCred = proctorCredentials.split(":")[0];
				proctorPasswordCred = proctorCredentials.split(":")[1];
			} else {
				CommonFunctions.logErrorMessageStopExecution("Proctor creds not provided for the env " + env);
			}
		} else {
			proctorUserCred = new EncryptCredentails()
					.decrypt(CommonFunctions.getPropertyValues().getProperty("proctorusername"));
			proctorPasswordCred = new EncryptCredentails()
					.decrypt(CommonFunctions.getPropertyValues().getProperty("proctorpassword"));
		}
		new ProctorLoginPage().verifyProctorLoginPage();
		new ProctorLoginPage().enterProctorUser(proctorUserCred);
		new ProctorLoginPage().enterProctorPassword(proctorPasswordCred);
		new ProctorLoginPage().clickProctorLogin();
		new ProctorLandingPage().verifyProctorLandingPage();
	}

	/**
	 * 
	 * Proctor account verification method
	 * 
	 * @param proctorEmail
	 * @throws Exception
	 *
	 */

	public static void verifyProctorMyAccount(String proctorEmail) throws Exception {
		new ProctorLandingPage().clickMyAccount();
		new ProctorMyAccountPage().verifyMyAccountPageDisplayed();
		new ProctorMyAccountPage().verifyProctorDetails(proctorEmail);
	}

}
