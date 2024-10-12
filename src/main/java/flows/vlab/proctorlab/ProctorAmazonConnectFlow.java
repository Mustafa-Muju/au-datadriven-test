package main.java.flows.vlab.proctorlab;

import encryptusercredentials.EncryptCredentails;
import main.java.base.TestBase;
import main.java.pages.vlab.proctorlab.ProctorAWSConnectPage;
import main.java.pages.vlab.proctorlab.ProctorLabHomePage;
import main.java.utils.CommonFunctions;

public class ProctorAmazonConnectFlow extends TestBase {

	/**
	 * @This flows does a amazon connect proctor login
	 * 
	 * @throws Exception
	 */
	public static void AWSConnectProctorLoginFlow() throws Exception {
		new ProctorAWSConnectPage().verifyAmazonConnectAuthenticatePage();
		if (env.equalsIgnoreCase("prod")) {
			if (proctorCredentials != null) {
				proctorUserCred = proctorCredentials.split(":")[0];
				proctorPasswordCred = proctorCredentials.split(":")[1];
			} else {
				CommonFunctions.logErrorMessageStopExecution("Proctor creds not provided for the env " + env);
			}
		} else {
			proctorUserCred = new EncryptCredentails()
					.decrypt(CommonFunctions.getPropertyValues().getProperty("labproctorusername"));
			proctorPasswordCred = new EncryptCredentails()
					.decrypt(CommonFunctions.getPropertyValues().getProperty("labproctorpassword"));
		}
		new ProctorAWSConnectPage().enterAmazonUsername(proctorUserCred);
		new ProctorAWSConnectPage().enterAmazonPassword(proctorPasswordCred);
		new ProctorAWSConnectPage().clickAWSProctorLogin();
		new ProctorAWSConnectPage().verifyAmazonConnectLandingPage();
	}

	/**
	 * @This flows takes to the amazon proctor vlab page
	 * 
	 * @throws Exception
	 */
	public static void AWSConnectVirtualLabPage() throws Exception {
		String labUrl = "";
		if (env.equalsIgnoreCase("prod")) {
			labUrl = "https://lab.emed.com/proctor";
		} else {
			labUrl = "https://lab." + env + ".emed.com/proctor";
		}
		getDriver().get(labUrl);
		new ProctorLabHomePage().verifyVLab2ProctorPage();
	}

}
