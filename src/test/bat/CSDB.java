package test.bat;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import main.java.base.TestBase;
import main.java.flows.csdb.CSDBUserDetailsFlow;
import main.java.flows.elasticpath.ElasticPathFlow;
import main.java.flows.www.RegistrationFlow;
import main.java.flows.www.ShoppingFlow;
import main.java.pages.csdb.CSDBOrderDetailsPage;
import main.java.pages.elasticpath.ElasticPathOrderDetailsPage;
import main.java.utils.CommonFunctions;
import main.java.utils.InvokeBrowser;
import main.java.utils.email.EmailVerification;
import main.java.utils.rest.RestGogomeds;

/**
 * 
 * @author Mohammed Mustafa
 * 
 * @This class contains CSDB BAT scenarios.
 */
@Listeners(main.java.utils.reportutil.TestListener.class)
public class CSDB extends TestBase{
	
	/**
	 * 
	 * @Method runs before each test and provide data sheet sheets
	 * 
	 * @throws Exception
	 */
	@BeforeClass(alwaysRun = true)
	public void executeBeforeTest() throws Exception {
		sheetName.set("csdbbat");
		testId.set(Thread.currentThread().getId());
		System.out.println(CommonFunctions.capitalize(getSheetName()) + " is started");
	}

	/**
	 * 
	 * Validate whether the user can refund the order from the admin tool
	 * 
	 */
	@Test(groups = { "csdbbat","fullbat" })
	public void a_csdb() throws Exception {
		getMethodData(1);
		if(!env.equalsIgnoreCase("prod")) {
			emailId.set(EmailVerification.getEmail("email1"));
			RegistrationFlow.eMedAPIUserCreation(getEmailId(), CommonFunctions.getdata("Password"));
			InvokeBrowser.invokeApplication("WWW");
			RegistrationFlow.getTestFlow();
			RegistrationFlow.eMedGetTestloginFlow(getEmailId(), CommonFunctions.getdata("Password"));
			ShoppingFlow.bookingTestKitFlow();
			InvokeBrowser.invokeApplication("ElasticPath");
			ElasticPathFlow.elasticPathOrderId(getEmailId());
			new ElasticPathOrderDetailsPage().verifyOrderInternalStatus("AWAITING_SHIPMENT");
			RestGogomeds.fulfillmentConfirmation();
			new ElasticPathOrderDetailsPage().verifyOrderInternalStatus("ACCEPTED_SHIPMENT");
			new ElasticPathOrderDetailsPage().verifyOrderStatusInElasticPathPage("Complete", "Paid", "Fulfilled");
			InvokeBrowser.invokeApplication(CommonFunctions.getdata("Platform"));
			CSDBUserDetailsFlow.loginCSDBPage("csdbusername", "csdbpassword");
			new CSDBOrderDetailsPage().fullRefundValidations(CommonFunctions.getdata("RefundReason"));
		} else {
			CommonFunctions.logMessageSkipExecution("This Scenario is not applicable for the prod env");
		}
		
	}
	
	/**
	 * 
	 * Validate whether the user can replace the six pack order from the admin tool
	 */
	@Test(groups = { "csdbbat","fullbat" })
	public void b_csdb() throws Exception {
		getMethodData(2);
		if (!env.equalsIgnoreCase("prod")) {
			emailId.set(EmailVerification.getEmail("email1"));
			RegistrationFlow.eMedAPIUserCreation(getEmailId(), CommonFunctions.getdata("Password"));
			InvokeBrowser.invokeApplication("WWW");
			RegistrationFlow.getTestFlow();
			RegistrationFlow.eMedGetTestloginFlow(getEmailId(), CommonFunctions.getdata("Password"));
			ShoppingFlow.bookingTestKitFlow();
			InvokeBrowser.invokeApplication("ElasticPath");
			ElasticPathFlow.elasticPathOrderId(getEmailId());
			new ElasticPathOrderDetailsPage().verifyOrderInternalStatus("AWAITING_SHIPMENT");
			RestGogomeds.fulfillmentConfirmation();
			new ElasticPathOrderDetailsPage().verifyOrderInternalStatus("ACCEPTED_SHIPMENT");
			InvokeBrowser.invokeApplication(CommonFunctions.getdata("Platform"));
			CSDBUserDetailsFlow.loginCSDBPage("csdbusername", "csdbpassword");
			CSDBUserDetailsFlow.orderReplacementFlow(getEmailId(),
					"195201 - Abbott’s BinaxNOW™ COVID-19 Ag At-Home Test Kit 6 Pack");
			new CSDBOrderDetailsPage().replaceOrderValidations();
		} else {
			CommonFunctions.logMessageSkipExecution("This Scenario is not applicable for the prod env");
		}
	}
	
	/**
	 * 
	 * Validate whether the user can cancel the order from the admin tool
	 *
	 */
	@Test(groups = { "csdbbat","fullbat" })
	public void c_csdb() throws Exception {
		getMethodData(3);
		if (!env.equalsIgnoreCase("prod")) {
			emailId.set(EmailVerification.getEmail("email1"));
			RegistrationFlow.eMedAPIUserCreation(getEmailId(), CommonFunctions.getdata("Password"));
			InvokeBrowser.invokeApplication("WWW");
			RegistrationFlow.getTestFlow();
			RegistrationFlow.eMedGetTestloginFlow(getEmailId(), CommonFunctions.getdata("Password"));
			ShoppingFlow.bookingTestKitFlow();
			InvokeBrowser.invokeApplication("ElasticPath");
			ElasticPathFlow.elasticPathOrderId(getEmailId());
			InvokeBrowser.invokeApplication(CommonFunctions.getdata("Platform"));
			CSDBUserDetailsFlow.loginCSDBPage("csdbusername", "csdbpassword");
			CSDBUserDetailsFlow.csdbOrderCancellation(getEmailId());
		} else {
			CommonFunctions.logMessageSkipExecution("This Scenario is not applicable for the prod env");
		}
	}

}
