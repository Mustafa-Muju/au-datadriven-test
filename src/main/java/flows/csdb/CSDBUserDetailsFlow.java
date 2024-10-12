package main.java.flows.csdb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import encryptusercredentials.EncryptCredentails;
import main.java.base.TestBase;
import main.java.pages.csdb.CSDBAccountDetailsPage;
import main.java.pages.csdb.CSDBLandingPage;
import main.java.pages.csdb.CSDBOrderDetailsPage;
import main.java.pages.www.EMedLoginPage;
import main.java.pages.www.SignUpSetUpPage;
import main.java.utils.CommonFunctions;

public class CSDBUserDetailsFlow extends TestBase {

	/**
	 * This Method for CSDB Login page
	 * 
	 * @throws Exception
	 *
	 */

	public static void loginCSDBPage(String username, String Password) throws Exception {
		new CSDBLandingPage().verifyCSDBPage();
		new CSDBLandingPage().enterCSDBLogin(
				new EncryptCredentails().decrypt(CommonFunctions.getPropertyValues().getProperty(username)));
		new CSDBLandingPage().enterCSDBPassword(
				new EncryptCredentails().decrypt(CommonFunctions.getPropertyValues().getProperty(Password)));
		new CSDBLandingPage().clickCSDBLogin();

	}

	/**
	 * This Method has CSDB Customer order flow
	 * 
	 * @param usermail
	 * 
	 * @throws Exception
	 *
	 */
	public static void navigateCustomerDB(String usermail) throws Exception {
		new CSDBLandingPage().verifyLandingPage();
		new CSDBLandingPage().clickCustomerDataBase();
		new CSDBLandingPage().enterCSDBUserSearch(usermail);
		new CSDBLandingPage().clickCSDBFirstCustomer();
	}

	/***
	 * This method has Date validation of Filer Customer
	 * 
	 * @throws Exception
	 */
	public static void navigateFilterCustomer() throws Exception {
		new CSDBLandingPage().verifyLandingPage();
		new CSDBLandingPage().clickCustomerDataBase();
		new CSDBLandingPage().verifyFilterCustomerDates();
	}

	/**
	 * This Method has to click CSDB Customer order First ID Flow
	 * 
	 * @param usermail
	 * 
	 * @throws Exception
	 *
	 */
	public static void orderIdFlow() throws Exception {
		new CSDBLandingPage().clickCSDBUserOrderId();
		new CSDBLandingPage().enterCSDBUserOrderId(getOrderId());
		new CSDBOrderDetailsPage().clickCSDBFirstOrder();
	}

	/**
	 * This Method for verifying Customer user id after eMed registration
	 * 
	 * @param usermail
	 * 
	 * @throws Exception
	 *
	 */

	public static void verifyCustomerUserId(String usermail) throws Exception {
		navigateCustomerDB(usermail);
		new CSDBAccountDetailsPage().verifyCSDBCustomerUserId(usermail);

	}

	/**
	 * This Method for verifying Customer registration after eMed registration
	 * 
	 * @param usermail
	 * 
	 * @throws Exception
	 *
	 */

	public static void verifyCustomerRegistration(String usermail) throws Exception {
		navigateCustomerDB(usermail);
		new CSDBAccountDetailsPage().verifyCSDBCustomerPersonalDetails(usermail);
		new CSDBAccountDetailsPage().verifyCustomerContactDetails();

	}

	/**
	 * This Method for verifying Customer order details after purchasing the KIT
	 * 
	 * @param usermail
	 * 
	 * @throws Exception
	 *
	 */

	public static void verifyCustomerAccountAndOrderDetailsFlow(String usermail) throws Exception {
		navigateCustomerDB(usermail);
		new CSDBAccountDetailsPage().verifyCSDBCustomerPersonalDetails(usermail);
		new CSDBAccountDetailsPage().verifyCustomerContactDetails();
		orderIdFlow();
		new CSDBOrderDetailsPage().verifyCSDBOrderDetailPage();
		new CSDBOrderDetailsPage().verifyCustomerOrderDetails();
	}

	/**
	 * This Method for verifying Customer order details from orders tab after
	 * purchasing the KIT
	 * 
	 * @param usermail
	 * 
	 * @throws Exception
	 *
	 */
	public static void verifyCustomerOrderDetailsFlow(String usermail) throws Exception {
		navigateCustomerDB(usermail);
		orderIdFlow();
		new CSDBOrderDetailsPage().verifyCSDBOrderDetailPage();
		new CSDBOrderDetailsPage().verifyCustomerOrderDetails();
	}

	/**
	 * This Method is to verify Customer order details via order admin role AQA-113
	 * 
	 * @param usermail
	 * 
	 * @throws Exception
	 *
	 */

	public static void verifyOrderDetailsForOrderAdmin(String usermail) throws Exception {
		new CSDBLandingPage().verifyLandingPage();
		new CSDBLandingPage().validateCustomer();
		new CSDBLandingPage().enterCSDBUserOrderId(getOrderId());
		new CSDBOrderDetailsPage().clickCSDBFirstOrder();
		new CSDBOrderDetailsPage().verifyCSDBOrderDetailPage();
		new CSDBOrderDetailsPage().verifyCustomerOrderDetails();
		new CSDBOrderDetailsPage().moreOptions();
		new CSDBOrderDetailsPage().cancelOrder();
		new CSDBOrderDetailsPage().cancelOrderConfirmation();
		orderIdFlow();
		new CSDBOrderDetailsPage().verifyingCancelOrderStatus();
		new CSDBAccountDetailsPage().logOut();
		new CSDBLandingPage().verifyLandingPage();
	}

	/**
	 * 
	 * @This method is to cancel order from CSDB
	 * 
	 * @throws Exception
	 */
	public static void customerDBOrderCancelation() throws Exception {
		new CSDBOrderDetailsPage().moreOptions();
		new CSDBOrderDetailsPage().cancelOrder();
		new CSDBOrderDetailsPage().cancelOrderConfirmation();
	}

	/**
	 * 
	 * @This method is to Validate whether login as agent is able to cancel the
	 *       order
	 * 
	 * @throws Exception
	 */
	public static void csdbOrderCancellation(String usermail) throws Exception {
		navigateCustomerDB(usermail);
		orderIdFlow();
		new CSDBOrderDetailsPage().moreOptions();
		new CSDBOrderDetailsPage().cancelOrder();
		new CSDBOrderDetailsPage().cancelOrderConfirmation();
		new CSDBOrderDetailsPage().verifyingCancelOrderStatus();
		new CSDBOrderDetailsPage().verifyMoreOptionDisappearsAfterCancelation();
	}

	/**
	 * 
	 * @This method is for signupAccountSetupPersonal in CSDB
	 * 
	 * @throws Exception
	 */
	public static void csdbsignupAccountSetupPersonal(String email, String password) throws Exception {
		new CSDBLandingPage().verifyLandingPage();
		new CSDBLandingPage().clickCustomerDataBase();
		new CSDBLandingPage().clickCreateCustomer();
		new SignUpSetUpPage().enterFirstName(CommonFunctions.getdata("FirstName"));
		new SignUpSetUpPage().enterLastName(CommonFunctions.getdata("LastName"));
		new EMedLoginPage().enterLoginEmail(email);
		new EMedLoginPage().enterLoginPassword(password);
		new CSDBLandingPage().enterDob(CommonFunctions.getdata("DateofBirth"));
	}

	/**
	 * 
	 * @This method is for User AccountSetupContact in CSDB
	 * 
	 * @throws Exception
	 */
	public static void csdbsignupAccountSetupContact() throws Exception {
		new CSDBLandingPage().enterZipCode(CommonFunctions.getdata("ZipCode"));
		new SignUpSetUpPage().selectState(CommonFunctions.getdata("State"));
		new SignUpSetUpPage().enterCity(CommonFunctions.getdata("City"));
		new SignUpSetUpPage().enterCountry(CommonFunctions.getdata("Country"));
		new SignUpSetUpPage().enterAddress(CommonFunctions.getdata("Address"));
		new SignUpSetUpPage().enterBuildingNumber(CommonFunctions.getdata("BuildingNumber"));
		new SignUpSetUpPage().enterPhoneNumber(CommonFunctions.getdata("PhNumber"));
		new CSDBLandingPage().clickSave();

		boolean flag = CommonFunctions.isExist(getDriver(), "(//img[contains(@role,'presentation')]/parent::div)[3]");
		if (flag) {
			new WebDriverWait(getDriver(), 30).until(ExpectedConditions.invisibilityOf(
					getDriver().findElement(By.xpath("(//img[contains(@role,'presentation')]/parent::div)[3]"))));
			CommonFunctions.logMessage("Buffer icon has been handled");
		} 
		
		boolean flag1 = CommonFunctions.isExist(getDriver(), "//*[contains(text(),'Customer was added successfully')]");
		if (flag1) {
			WebElement eleSuccessAlert = getDriver()
					.findElement(By.xpath("//*[contains(text(),'Customer was added successfully')]"));
			CommonFunctions.logMessage(
					"User creation status ===>" + CommonFunctions.getTextOfElement(eleSuccessAlert, "Success Alert"));
		}
		else {
			WebElement elefailureAlert = getDriver().findElement(By.xpath(
					"//*[contains(text(),'Please fill in all of the required fields to continue the save process')]"));
			CommonFunctions.logErrorMessageStopExecution("User creation failue. Retrieved status ===>"
					+ CommonFunctions.getTextOfElement(elefailureAlert, "Failure Alert"));
		}
	}

	/**
	 * @This method is to search and verify Enterprise user
	 * 
	 * @param headerType
	 * 
	 * @param value
	 * 
	 * @throws Exception
	 */
	public static void searchEnterpriseUser(String headerType, String value) throws Exception {
		new CSDBLandingPage().verifyLandingPage();
		new CSDBLandingPage().clickCustomerDataBase();
		new CSDBLandingPage().clickShowEnterprise();
		CommonFunctions.waitForPageLoad(getDriver());
		new CSDBLandingPage().csdbCustomertableSort(headerType, value);
	}

	/**
	 * 
	 * @This method is to create User from CSDB
	 * 
	 * @throws Exception
	 */
	public static void customerCreation(String email, String password) throws Exception {
		csdbsignupAccountSetupPersonal(email, password);
		csdbsignupAccountSetupContact();
	}

	/**
	 * 
	 * @This method is to Validate OrderReplacement
	 * 
	 * @throws Exception
	 */
	public static void orderReplacementFlow(String usermail,String kit) throws Exception {
		navigateCustomerDB(usermail);
		orderIdFlow();
		new CSDBOrderDetailsPage().moreOptions();
		new CSDBOrderDetailsPage().clickReplaceOrderButton();
		new CSDBOrderDetailsPage().selectReplaceOrderKit(kit);
		new CSDBOrderDetailsPage().selectReplaceReasonsFromDropDown("1|3");
		new CSDBOrderDetailsPage().clickReplaceOrderSubmitButton();
		new CSDBOrderDetailsPage().clickConfirmReplaceOrderButton();
	}

	/**
	 * 
	 * @This method is to Validate OrderReplacement Confirmation popup
	 * 
	 * @throws Exception
	 */
	public static void orderReplaceConfirmationBanner(String usermail) throws Exception {
		navigateCustomerDB(usermail);
		orderIdFlow();
		new CSDBOrderDetailsPage().replaceOrderValidations();
	}

	/**
	 * 
	 * @This method is to replace & Verify an order without an existing order for
	 *       CSDB B2B-Replacement
	 * 
	 * @param usermail
	 * 
	 * @param b2bKitType
	 * 
	 * @param quantity
	 * 
	 * @throws Exception
	 * 
	 */
	public static void eMedB2BReplacement(String usermail, String b2bKitType, int quantity) throws Exception {
		new CSDBLandingPage().verifyLandingPage();
		new CSDBLandingPage().clickCustomerDataBase();
		new CSDBLandingPage().enterCSDBUserSearch(usermail);
		new CSDBLandingPage().clickCSDBFirstCustomer();
		CommonFunctions.waitForPageLoad(getDriver());
		CommonFunctions.logMessage("<-----CSDB Customer Details Page----->");
		new CSDBAccountDetailsPage().verifyCSDBCustomerPersonalDetails(usermail);
		new CSDBAccountDetailsPage().verifyCustomerContactDetails();
		new CSDBAccountDetailsPage().B2BReplaceOrder(b2bKitType, quantity);
	}

	/**
	 * @This method is to validate B2B-Replacement, alert pop-ups and profile
	 *       verification for customer profile with no personal/contact data
	 * 
	 * @param usermail
	 * 
	 * @throws Exception
	 */
	public static void eMedB2BUserAndAlertVerification(String usermail) throws Exception {
		new CSDBLandingPage().verifyLandingPage();
		new CSDBLandingPage().clickCustomerDataBase();
		new CSDBLandingPage().enterCSDBUserSearch(usermail);
		new CSDBLandingPage().clickCSDBFirstCustomer();
		CommonFunctions.waitForPageLoad(getDriver());
		CommonFunctions.logMessage("<-----CSDB Customer Details Page----->");
		new CSDBAccountDetailsPage().verifyDOBEmptyIssue();
		new CSDBAccountDetailsPage().emptyProfileB2BCheck();
		new CSDBAccountDetailsPage().updateUserProfile();
		new CSDBAccountDetailsPage().verifyCSDBCustomerPersonalDetails(usermail);
	}

	/**
	 * @This method is to delete and verify eComm user profile
	 * 
	 * @throws Exception
	 * 
	 */
	public static void eCommDeleteProfile(String usermail) throws Exception {
		new CSDBLandingPage().verifyLandingPage();
		new CSDBLandingPage().clickCustomerDataBase();
		new CSDBLandingPage().enterCSDBUserSearch(usermail);
		new CSDBLandingPage().clickCSDBFirstCustomer();
		CommonFunctions.waitForPageLoad(getDriver());
		CommonFunctions.logMessage("<-----CSDB Customer Details Page----->");
		new CSDBAccountDetailsPage().deleteUserAcc(usermail);
		new CSDBAccountDetailsPage().verifyCSDBFirstCustomer(usermail);
	}

	/**
	 * @This method is to validate B2B-Replacement for date filter
	 * 
	 * @param usermail
	 * 
	 * @throws Exception
	 */
	public static void eMedB2BUserDateFilter(String CurrentDate) throws Exception {
		new CSDBLandingPage().verifyLandingPage();
		new CSDBLandingPage().clickCustomerDataBase();
		new CSDBLandingPage().clickCustomerFilter();
		new CSDBLandingPage().enterCSDBUserFilterByCurrentDate(CurrentDate, CurrentDate);
		CommonFunctions.waitForPageLoad(getDriver());
		new CSDBLandingPage().enterCSDBUserSearch(getEmailId());
		new CSDBLandingPage().clickCSDBFirstCustomer();
	}

	/**
	 * 
	 * @This method is to trigger email prescription
	 * 
	 * @param usermail
	 * 
	 * @throws Exception
	 * 
	 */
	public static void triggerPrescription(String usermail, String orderId) throws Exception {
		CommonFunctions.logMessage("<=======Prescription trigger process is initiated=======>");
		navigateCustomerDB(usermail);
		orderIdFlow();
		new CSDBOrderDetailsPage().verifyCSDBOrderDetailPage();
		new CSDBOrderDetailsPage().verifyCSDBOrderId(orderId);
		new CSDBOrderDetailsPage().moreOptions();
		new CSDBOrderDetailsPage().emailPrescription();
	}
}
