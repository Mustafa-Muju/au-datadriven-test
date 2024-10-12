package test.bat;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import main.java.base.TestBase;
import main.java.flows.csdb.CSDBUserDetailsFlow;
import main.java.flows.vlab.core.CoreLabFlow;
import main.java.flows.vlab.core.CoreUserFlow;
import main.java.flows.vlab.lktransfer.LKTransferFlow;
import main.java.flows.vlab.proctorlab.ProctorAmazonConnectFlow;
import main.java.flows.vlab.proctorlab.ProctorLabFlow;
import main.java.flows.vlab.vlab2.VLab2Flow;
import main.java.flows.www.RegistrationFlow;
import main.java.flows.www.UserVirtualLabFlow;
import main.java.pages.csdb.CSDBAccountDetailsPage;
import main.java.pages.csdb.CSDBSessionDetailsPage;
import main.java.pages.vlab.core.AuthorizationPage;
import main.java.pages.vlab.core.CreateAccountPage;
import main.java.pages.vlab.core.PreFlightQuestionairePage;
import main.java.utils.CommonFunctions;
import main.java.utils.InvokeBrowser;
import main.java.utils.email.EmailVerification;

/**
 * 
 * @author Sheik Amirudeen
 * 
 * @This class contains Vlab procedure scenarios
 *
 */
@Listeners(main.java.utils.reportutil.TestListener.class)
public class Vlab extends TestBase {
	/**
	 * 
	 * @Method runs before each test and provide data sheet sheets
	 * 
	 * @throws Exception
	 */
	@BeforeClass(alwaysRun = true)
	public void executeBeforeTest() throws Exception {
		sheetName.set("corebat");
		testId.set(Thread.currentThread().getId());
		System.out.println(CommonFunctions.capitalize(getSheetName()) + " is started");
	}

	/**
	 * 
	 * This scenario is to verify the proctor is able to connect vlab2 via AWS
	 * Connect and navigate to VLab2
	 */
	@Test(groups = { "corebat", "fullbat" })
	public void a_vlab() throws Exception {
		getMethodData(1);
		if (!env.equalsIgnoreCase("prod")) {
			if (BrowserNeed.equalsIgnoreCase("chrome")) {
				InvokeBrowser.invokeApplication(CommonFunctions.getdata("Platform"));
				ProctorAmazonConnectFlow.AWSConnectProctorLoginFlow();
				ProctorAmazonConnectFlow.AWSConnectVirtualLabPage();

			} else {
				CommonFunctions
						.logMessageSkipExecution("Proctor application works only on chrome browser.\nScenario skipped");
			}
		} else {
			CommonFunctions.logMessageSkipExecution("This Scenario is not applicable for the prod env");
		}
	}

	/**
	 *
	 * This scenario is to Verify whether the main patient is able to take a virtual
	 * lab2 session with proctor
	 */
	@Test(groups = { "corebat", "fullbat" })
	public void b_vlab() throws Exception {
		getMethodData(2);
		if (env.equalsIgnoreCase("stg")) {
			InvokeBrowser.invokeApplication(CommonFunctions.getdata("Platform"));
			navicaEmail.set(EmailVerification.getEmail("email2"));
			RegistrationFlow.startNavicaTestFlow();
			AuthorizationPage.startTestConsentFlow();
			new CreateAccountPage().clickNavicaCreateAccount();
			CoreUserFlow.coreBinaxNowAccountCreationFlow(getNavicaEmail(), CommonFunctions.getdata("Password"));
			
			InvokeBrowser.invokeApplication("AWSConnectProctor");
			ProctorAmazonConnectFlow.AWSConnectProctorLoginFlow();
			ProctorAmazonConnectFlow.AWSConnectVirtualLabPage();
			ProctorLabFlow.skipReadinessChecker();
			CoreUserFlow.navigateToCore(getDriver(), CommonFunctions.getdata("Platform"));
			RegistrationFlow.startNavicaTestFlow();
			AuthorizationPage.startTestConsentFlow();
			new CreateAccountPage().clickNavicaSignIn();
			CoreUserFlow.coreBinaxNowLoginFlow(getNavicaEmail(), CommonFunctions.getdata("Password"));
			new CreateAccountPage().clickContinue();
			CoreUserFlow.startCoreQuestionaire();
			new PreFlightQuestionairePage().verifyCoreVlabPage();
			CoreLabFlow.startLabTest();
			ProctorLabFlow.connectProctorForFirstSession();
			ProctorLabFlow.startFirstSessionBinax();
			CoreLabFlow.startSecondLabTest();
			ProctorLabFlow.connectProctorForSecondSession();
			ProctorLabFlow.startSecondSessionBinax();
			EmailVerification.verifyEmedLabReport(getNavicaEmail());
			InvokeBrowser.invokeApplication("LKTransfer");
			LKTransferFlow.lKUserLogin();
			LKTransferFlow.getAccount("EME_API_Reporting_Test_Rename", "Account No.");
			LKTransferFlow.getEllkyResults(getLabSessnId());
			InvokeBrowser.invokeApplication("CSDB");
			CSDBUserDetailsFlow.loginCSDBPage("csdbusername", "csdbpassword");
			CSDBUserDetailsFlow.navigateCustomerDB(getEmailId());
			new CSDBAccountDetailsPage().clickCSDBCoreSessions();
			new CSDBAccountDetailsPage().validateSessionTableInfo("Core", getLabSessnId(),
					CommonFunctions.getdata("FirstName"), CommonFunctions.getdata("LastName"),
					CommonFunctions.getdata("DateofBirth"), CommonFunctions.getdata("Result"), CommonFunctions.getdata("Procedure"));
			
			new CSDBSessionDetailsPage().verifyTestTakerDetails(CommonFunctions.getdata("FirstName"),
					CommonFunctions.getdata("LastName"), CommonFunctions.getDatefromToday(0),
					CommonFunctions.getdata("Result"));
			new CSDBSessionDetailsPage().verifyRecording();
		} else {
			CommonFunctions.logMessageSkipExecution("This Scenario is not applicable for the " + env + " env");
		}
	}

	/**
	 *
	 * This scenario is to Verify whether the patient as dependent is able to take a
	 * virtual lab2 session with proctor
	 */
	@Test(groups = { "corebat", "fullbat" })
	public void c_vlab() throws Exception {
		getMethodData(3);
		CommonFunctions.logMessageSkipExecution("Manual scenario skipped to test the audio and video..");

	}

	/**
	 *
	 * Validate whether the new eMed customer is able to navigate to binax session
	 *
	 */
	@Test(groups = { "corebat", "fullbat" })
	public void d_vlab() throws Exception {
		getMethodData(4);
		InvokeBrowser.invokeApplication(CommonFunctions.getdata("Platform"));
		emailId.set(EmailVerification.getEmail("email1"));
		VLab2Flow.binaxLoginFlow();
		if (env.equalsIgnoreCase("stg") || env.equalsIgnoreCase("prod")) {
			AuthorizationPage.startTestConsentFlow();
			CoreUserFlow.coreAccountCreationFlow(getEmailId(), CommonFunctions.getdata("Password"));
			CoreUserFlow.startCoreQuestionaire();
			new PreFlightQuestionairePage().verifyCoreVlabPage();
		} else {
			RegistrationFlow.eMedAccountSignUpFlow(getEmailId(), CommonFunctions.getdata("Password"));
			RegistrationFlow.fillRegisterSetup();
			VLab2Flow.fillOtherDetailsGetBinaxSession();
			UserVirtualLabFlow.verifyUserAttendSessionAssessment();
		}
	}

	/**
	 *
	 * This scenario is to Verify whether the patient as eMed user is able to take a
	 * binax virtual session with proctor
	 *
	 */
	@Test(groups = { "corebat", "fullbat" })
	public void e_vlab() throws Exception {
		getMethodData(5);
		if (env.equalsIgnoreCase("stg")) {
			InvokeBrowser.invokeApplication("AWSConnectProctor");
			ProctorAmazonConnectFlow.AWSConnectProctorLoginFlow();
			ProctorAmazonConnectFlow.AWSConnectVirtualLabPage();
			ProctorLabFlow.skipReadinessChecker();
			CoreUserFlow.navigateToCore(getDriver(), CommonFunctions.getdata("Platform"));
			VLab2Flow.binaxLoginFlow();
			if (env.equalsIgnoreCase("stg") || env.equalsIgnoreCase("prod")) {
				AuthorizationPage.startTestConsentFlow();
				CoreUserFlow.coreLoginFlow(getEmailId(), CommonFunctions.getdata("Password"));
				CoreUserFlow.confirmLoginFlow();
				CoreUserFlow.startCoreQuestionaire();
			} else {
				RegistrationFlow.eMedGetTestloginFlow(getEmailId(), CommonFunctions.getdata("Password"));
				UserVirtualLabFlow.verifyUserAttendSessionAssessment();
			}
			new PreFlightQuestionairePage().verifyCoreVlabPage();
			CoreLabFlow.startLabTest();
			ProctorLabFlow.connectProctorForFirstSession();
			ProctorLabFlow.startFirstSessionBinax();
			CoreLabFlow.startSecondLabTest();
			ProctorLabFlow.connectProctorForSecondSession();
			ProctorLabFlow.startSecondSessionBinax();
			EmailVerification.verifyEmedLabReport(getEmailId());
			InvokeBrowser.invokeApplication("CSDB");
			CSDBUserDetailsFlow.loginCSDBPage("csdbusername", "csdbpassword");
			CSDBUserDetailsFlow.navigateCustomerDB(getEmailId());
			new CSDBAccountDetailsPage().clickCSDBCoreSessions();
			new CSDBAccountDetailsPage().validateSessionTableInfo("Core", getLabSessnId(),
					CommonFunctions.getdata("FirstName"), CommonFunctions.getdata("LastName"),
					CommonFunctions.getdata("DateofBirth"), CommonFunctions.getdata("Result"), CommonFunctions.getdata("Procedure"));
			
			new CSDBSessionDetailsPage().verifyTestTakerDetails(CommonFunctions.getdata("FirstName"),
					CommonFunctions.getdata("LastName"), CommonFunctions.getDatefromToday(0),
					CommonFunctions.getdata("Result"));
			new CSDBSessionDetailsPage().verifyRecording();
		} else {
			CommonFunctions.logMessageSkipExecution("This Scenario is not applicable for the " + env + " env");
		}

	}
}
