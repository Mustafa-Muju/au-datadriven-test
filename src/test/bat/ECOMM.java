package test.bat;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import main.java.base.TestBase;
import main.java.flows.elasticpath.ElasticPathFlow;
import main.java.flows.vlab.core.CoreUserFlow;
import main.java.flows.www.CancelationFlow;
import main.java.flows.www.RegistrationFlow;
import main.java.flows.www.ShoppingFlow;
import main.java.pages.elasticpath.ElasticPathOrderDetailsPage;
import main.java.pages.vlab.core.AuthorizationPage;
import main.java.pages.vlab.core.CreateAccountPage;
import main.java.pages.vlab.core.PreFlightQuestionairePage;
import main.java.pages.www.EMedLoginPage;
import main.java.utils.CommonFunctions;
import main.java.utils.InvokeBrowser;
import main.java.utils.email.EmailVerification;
import main.java.utils.rest.RestGogomeds;

/**
 * 
 * @author Mohammed Mustafa
 * 
 * @This class contains eMed eComm BAT scenarios.
 */
@Listeners(main.java.utils.reportutil.TestListener.class)
public class ECOMM extends TestBase {

	private static boolean emedAccount = false;

	/**
	 * 
	 * @Method runs before each test and provide data sheet sheets
	 * 
	 * @throws Exception
	 */
	@BeforeClass(alwaysRun = true)
	public void executeBeforeTest() throws Exception {
		sheetName.set("ecommbat");
		testId.set(Thread.currentThread().getId());
		System.out.println(CommonFunctions.capitalize(getSheetName()) + " is started");
	}

	/**
	 * 
	 * Verify the user is able to create an emed account and password reset in eCommerce site.
	 * 
	 **/
	@Test(groups = { "ecommbat", "fullbat" })
	public void a_ecomm() throws Exception {
		getMethodData(1);
		InvokeBrowser.invokeApplication(CommonFunctions.getdata("Platform"));
		emailId.set(EmailVerification.getEmail("email1"));
		RegistrationFlow.getTestFlow();
		RegistrationFlow.eMedAccountSignUpFlow(getEmailId(), CommonFunctions.getdata("Password"));
		RegistrationFlow.fillRegisterSetup();
		if (!env.equalsIgnoreCase("prod")) {
			new EMedLoginPage().clickSignout();
		}
		emedAccount = true;
		CommonFunctions.logMessage("<----Forgot Password Flow Started---->");
		RegistrationFlow.getTestFlow();
		RegistrationFlow.eMedAccountForgetPasswordFlow(getEmailId(), CommonFunctions.getdata("ResetPassword"));
		RegistrationFlow.getTestFlow();
		RegistrationFlow.eMedGetTestloginFlow(getEmailId(), CommonFunctions.getdata("ResetPassword"));
		new EMedLoginPage().verifyPageLogin();
	}

	/**
	 * 
	 * This scenario is to verify the user is able to purchase a kit and cancel the order in ecommerce site
	 * 
	 */
	@Test(groups = { "ecommbat", "fullbat" })
	public void b_ecomm() throws Exception {
		getMethodData(2);
		if (!env.equalsIgnoreCase("prod")) {
			if (!emedAccount) {
				emailId.set(EmailVerification.getEmail("email1"));
				RegistrationFlow.eMedAPIUserCreation(getEmailId(), CommonFunctions.getdata("Password"));
				emedAccount = true;
			}

			if (emedAccount) {
				InvokeBrowser.invokeApplication(CommonFunctions.getdata("Platform"));
				RegistrationFlow.getTestFlow();
				RegistrationFlow.eMedGetTestloginFlow(getEmailId(), CommonFunctions.getdata("Password"));
				ShoppingFlow.bookingTestKitFlow();
				CancelationFlow.cancelOpenOrderFlow();
				new EMedLoginPage().clickSignout();
			} else {
				CommonFunctions.logMessageSkipExecution("No eMed account is created successfully.\nScenario skipped");
			}
		} else {
			CommonFunctions.logMessageSkipExecution("This Scenario is not applicable for the prod env");
		}

	}
	
	/**
	 * 
	 * This scenario is to verify the user is able to purchase a kit and fulfill the order successfully
	 * 
	 */
	@Test(groups = { "ecommbat","fullbat" })
	public void c_ecomm() throws Exception {
		getMethodData(3);
		if (!env.equalsIgnoreCase("prod")) {
			if (!emedAccount) {
				emailId.set(EmailVerification.getEmail("email1"));
				RegistrationFlow.eMedAPIUserCreation(getEmailId(), CommonFunctions.getdata("Password"));
				emedAccount = true;
			}

			if (emedAccount) {
				InvokeBrowser.invokeApplication("WWW");
				RegistrationFlow.getTestFlow();
				RegistrationFlow.eMedGetTestloginFlow(getEmailId(), CommonFunctions.getdata("Password"));
				ShoppingFlow.bookingTestKitFlow();
				InvokeBrowser.invokeApplication("ElasticPath");
				ElasticPathFlow.elasticPathLoginFlow();
				ElasticPathFlow.elasticPathOrderverifications(getEmailId());
				new ElasticPathOrderDetailsPage().getOrderIdInElasticPath();
				new ElasticPathOrderDetailsPage().verifyOrderInternalStatus("AWAITING_SHIPMENT");
				RestGogomeds.fulfillmentConfirmation();
				new ElasticPathOrderDetailsPage().verifyOrderInternalStatus("ACCEPTED_SHIPMENT");
				new ElasticPathOrderDetailsPage().verifyOrderStatusInElasticPathPage("Complete", "Paid", "Fulfilled");
				new ElasticPathOrderDetailsPage().verifyEPBagEmailSentStatus("SHIPPING_CONFIRMATION");
				EmailVerification.verifyOrderShippedEmail();
			} else {
				CommonFunctions.logMessageSkipExecution("No eMed account is created successfully.\nScenario skipped");
			}
		} else {
			CommonFunctions.logMessageSkipExecution("This Scenario is not applicable for the prod env");
		}
		
	}

	/**
	 * 
	 * This scenario is to verify the user is able to create new navica account and fill the questionnaire
	 */
	@Test(groups = { "ecommbat", "fullbat" })
	public void d_ecomm() throws Exception {
		getMethodData(4);
		InvokeBrowser.invokeApplication(CommonFunctions.getdata("Platform"));
		navicaEmail.set(EmailVerification.getEmail("email2"));
		RegistrationFlow.startNavicaTestFlow();
		AuthorizationPage.startTestConsentFlow();
		new CreateAccountPage().clickNavicaCreateAccount();
		CoreUserFlow.coreBinaxNowAccountCreationFlow(getNavicaEmail(), CommonFunctions.getdata("Password"));
		if (env.equalsIgnoreCase("stg") || env.equalsIgnoreCase("prod")) {
			new CreateAccountPage().clickContinue();
			CoreUserFlow.startCoreQuestionaire();
			new PreFlightQuestionairePage().verifyCoreVlabPage();
		}

	}

	/**
	 * 
	 * This scenario is to verify whether the patient is able to reset the navica
	 * account password
	 */
	//@Test(groups = { "ecommbat", "fullbat" })
	public void e_ecomm() throws Exception {
		getMethodData(5);
		CommonFunctions
				.logMessageSkipExecution("Scenario skips as Navica reset password flow is not available in sdlc.");
		InvokeBrowser.invokeApplication(CommonFunctions.getdata("Platform"));
		RegistrationFlow.startNavicaTestFlow();
		AuthorizationPage.startTestConsentFlow();
		RegistrationFlow.navicaForgetPasswordFlow(getNavicaEmail(), CommonFunctions.getdata("ResetPassword"));
		new EMedLoginPage().verifyPageLogin();
	}
}