package main.java.pages.csdb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import main.java.base.TestBase;
import main.java.utils.CommonFunctions;
import main.java.utils.DefectList;

public class CSDBAccountDetailsPage extends TestBase {

	private JavascriptExecutor jse = null;

	@FindBy(xpath = "//h1[contains(text(),'Account Details')]")
	private WebElement eleAccountDetailsHeader;

	@FindBy(xpath = "//*[text()='CORE Sessions']")
	private WebElement eleCustomerCoreSessions;

	@FindBy(xpath = "//*[text()='Action History']")
	private WebElement eleCustomerActionHistory;

	@FindBy(tagName = "th")
	private List<WebElement> eleCustomerInfoTableHeaders;

	@FindBy(xpath = "//tr//following::tr")
	private List<WebElement> eleCustomerInfoTableRows;

	@FindBy(xpath = "//*[@class='pagination']//strong")
	private WebElement elePages;

	@FindBy(xpath = "//*[contains(text(),'Next')]")
	private WebElement eleTableNextBtn;

	@FindBy(xpath = "//*[text()='First Name']//following-sibling::p")
	private WebElement eleCustomerFirstName;

	@FindBy(xpath = "//*[text()='Last Name']//following-sibling::p")
	private WebElement eleCustomerLastName;

	@FindBy(xpath = "//*[text()='Email Address']//following-sibling::a")
	private WebElement eleCustomerEmailAddress;

	@FindBy(xpath = "//*[text()='Phone Number']//following-sibling::*[text()]")
	private WebElement eleCustomerPhoneNumber;

	@FindBy(xpath = "//*[text()='Date of Birth']//following-sibling::p")
	private WebElement eleCustomerDOB;

	@FindBy(xpath = "//*[text()='Address']//parent::div")
	private WebElement eleCustomerAddress;

	@FindBy(xpath = "//*[text()='Order Date']//following-sibling::h3")
	private WebElement eleCustomerOrderDate;

	@FindBy(xpath = "//*[text()='Subtotal']//parent::div//following-sibling::div")
	private WebElement eleCsdbSubTotal;

	@FindBy(xpath = "//*[text()='Shipping']//parent::div//following-sibling::div")
	private WebElement eleCsdbShippingPrice;

	@FindBy(xpath = "//*[text()='Tax']//parent::div//following-sibling::div")
	private WebElement eleCsdbTax;

	@FindBy(xpath = "//*[text()='Total']//parent::div//following-sibling::div")
	private WebElement eleCsdbTotalPrice;

	@FindBy(xpath = "//a[contains(text(),'Edit order details')]")
	private WebElement eleCsdbEditOrderDetails;

	@FindBy(xpath = "//*[contains(text(),'Send Reset Password Link')]")
	private WebElement eleReSetPasswordLink;

	@FindBy(xpath = "//*[contains(text(),'already been canceled')]")
	private WebElement eleOrderCanceledVerification;

	@FindBy(xpath = "//*[contains(text(),'Log Out')]")
	private WebElement eleLogOut;

	@FindBy(xpath = "//*[contains(text(),'Change Password')]")
	private WebElement eleChangePassword;

	@FindBy(xpath = "(//*[contains(@class,'sc-eHfQar')])")
	private WebElement eleUserEditDetails;

	@FindBy(xpath = "//p[text()='First Name']//*[@class='sc-eHfQar hhRQON']")
	private WebElement eleUserFNameEditDetails;

	@FindBy(xpath = "//p[text()='Last Name']//*[(@class='sc-eHfQar hhRQON')]")
	private WebElement eleUserLNameEditDetails;

	@FindBy(xpath = "//p[text()='Phone Number']//*[(@class='sc-eHfQar hhRQON')]")
	private WebElement eleUserPhNumbEditDetails;

	@FindBy(xpath = "//p[text()='Date of Birth']//*[(@class='sc-eHfQar hhRQON')]")
	private WebElement eleUserDobEditDetails;

	@FindBy(xpath = "//p[text()='Address']//*[(@class='sc-eHfQar hhRQON')]")
	private WebElement eleUserAddEditDetails;

	@FindBy(xpath = "//h1[contains(text(),'Orders')]")
	private WebElement eleOrderDetails;

	@FindBy(xpath = "//a[contains(text(),'Orders')]")
	private WebElement eleCustomerOrders;

	@FindBy(id = "newPassword")
	private WebElement eleNewPassword;

	@FindBy(id = "confirmPassword")
	private WebElement eleConfirmPassword;

	@FindBy(xpath = "//*[contains(text(),'Save')]")
	private WebElement eleSave;

	@FindBy(xpath = "//button[contains(text(),'Change Password')]")
	private WebElement elePasswordsubmit;

	@FindBy(xpath = "//div[contains(@class,'dropdown__placeholder ')]")
	private List<WebElement> eleDropDownList;

	@FindBy(xpath = "//*[contains(@type,'submit')]")
	private WebElement eleEditOrder;

	@FindBy(xpath = "//*[contains(text(),'The order was updated successfully')]")
	private WebElement eleOrderEditStatus;

	@FindBy(xpath = "//main[@id='main']//*[contains(@role,'presentation')]")
	private WebElement eleLoadScreenSpinner;

	@FindBy(xpath = "//*[contains(text(),'Password successfully changed.')]")
	private WebElement elePasswordconfirmationPrompt;

	@FindBy(xpath = "(//span[text()='Test Last'])[1]")
	private WebElement eleOrderUserName;

	@FindBy(xpath = "(//span[@data-test-data='shipping-address']//following-sibling::span)[1]")
	private WebElement eleOrderUserAddress;

	@FindBy(xpath = "(//span[@data-test-data='shipping-address']//following-sibling::span)[2]")
	private WebElement eleOrderBuildingNumber;

	@FindBy(xpath = "(//span[@data-test-data='shipping-address']//following-sibling::span)[3]")
	private WebElement eleOrderCity;

	@FindBy(xpath = "(//span[@data-test-data='shipping-address']//following-sibling::span)[4]")
	private WebElement eleOrderZipCode;

	@FindBy(xpath = "(//span[@data-test-data='shipping-address']//following-sibling::span)[5]")
	private WebElement eleOrderState;

	@FindBy(xpath = "(//span[@data-test-data='shipping-address']//following-sibling::span)[7]")
	private WebElement eleOrderPhoneNumber;

	@FindBy(id = "firstName")
	private WebElement eleEditFirstName;

	@FindBy(id = "lastName")
	private WebElement eleEditLastName;

	@FindBy(id = "phoneNumber")
	private WebElement eleEditPhoneNumber;

	@FindBy(id = "dateOfBirth")
	private WebElement eleEditDob;

	@FindBy(id = "streetAddress")
	private WebElement eleEditStreetAddress;

	@FindBy(id = "unitNumber")
	private WebElement eleEditUnitNumber;

	@FindBy(id = "zipCode")
	private WebElement eleEditZipCode;

	@FindBy(id = "city")
	private WebElement eleEditCity;

	@FindBy(id = "state")
	private WebElement eleEditState;

	@FindBy(xpath = "//*[contains(@class,'dropdown__option')]")
	private List<WebElement> eleStateOptions;

	@FindBy(xpath = "//*[contains(text(),'Account Options')]")
	private WebElement eleAccountOptions;

	@FindBy(xpath = "//*[contains(text(),'Close')]")
	private WebElement eleClosePopup;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement eleSubmit;

	@FindBy(xpath = "//*[text()='Replace without an order']//following-sibling::span")
	private WebElement eleReplaceWithoutAnOrder;

	@FindBy(xpath = "//button[contains(text(),'Replace')]")
	private WebElement eleB2BreplaceButton;

	@FindBy(xpath = "//*[@name='replaceReason']//parent::div//label|//*[@name='replaceReason']//parent::label")
	private List<WebElement> eleB2BreplaceReason;

	@FindBy(xpath = "(//div[contains(@class,'sc-gsTCUz sc-crrsfI fEVToq fepsQY')]//img[@role='presentation'])[3]")
	private WebElement eleOrderQuantity;

	@FindBy(xpath = "//*[@class='AlertBannerMain']//*[contains(text(),'The order was successfully created')]")
	private WebElement eleOrderConfirmationAlert;

	@FindBy(xpath = "//*[contains(text(),'We could not get cheapest fulfillment center')]")
	private WebElement eleOrderRejectionAlert;

	@FindBy(xpath = "//*[@type='button']//*[contains(@role,'presentation')]")
	private WebElement eleCloseInvalidAddPopUp;

	@FindBy(xpath = "//*[contains(@src,'placing-order-spinner')]")
	private WebElement eleSpinner;

	@FindBy(xpath = "//p[text()='Please enter a city']")
	private WebElement eleAlertMessage;

	@FindBy(xpath = "//span[text()='Please fill in all the fields before saving the customer information']")
	private WebElement eleAlertPopUp;

	@FindBy(xpath = "//*[@class='sc-eggNIi JiLrb']//following-sibling::button")
	private WebElement eleB2BCloseAlertPopUp;

	@FindBy(xpath = "//button[text()='Cancel']")
	private WebElement eleCancel;

	@FindBy(xpath = "//*[text()='Replace without an order']")
	private WebElement eleB2BPopUpHeader;

	@FindBy(xpath = "//*[@type='button']//*[@role='presentation']")
	private WebElement eleB2BClosePopUp;

	@FindBy(xpath = "//*[text()='Delete Account']")
	private WebElement eleDeleteAcc;

	@FindBy(xpath = "//*[@class='AlertBannerMain']//*[contains(text(),'This customer will lose access to all account information')]")
	private WebElement eleDeleteAccBanner;

	@FindBy(xpath = "//*[@name='checked']//parent::div")
	private List<WebElement> eleDeleteReason;

	@FindBy(xpath = "//button[text()='Delete Account']")
	private WebElement eleDeleteAccButton;

	@FindBy(xpath = "//*[(@class='sc-gsTCUz sc-crrsfI sc-tYoTV fEVToq fepsQY kFxAqa')]//*[@role='presentation']")
	private WebElement eleDelLoader;

	@FindBy(xpath = "//*[contains(text(),'An Email will be sent to the customer')]")
	private WebElement eleDelConfirmPopUp;

	@FindBy(xpath = "//*[contains(text(),'Account deleted successfully')]")
	private WebElement eleDelConfirmAlert;

	@FindBy(xpath = "(//a[@class='sc-kLgntA sc-iktFzd bzbWyi jXJAqO']|//*[text()='Ok'])[2]")
	private WebElement eleOkDel;

	@FindBy(xpath = "(//*[text()='Back'])[2]")
	private WebElement eleBack;

	public CSDBAccountDetailsPage() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

	JavascriptExecutor e = (JavascriptExecutor) getDriver();

	/**
	 * 
	 * @This Method for verifying the CSDB Account Details Page
	 * 
	 * @throws Exception
	 *
	 */
	public void verifyCSDBAccountDetailsPage() throws Exception {
		CommonFunctions.checkCurrentPage(eleAccountDetailsHeader, "Account Details");
		CommonFunctions.logMessage("<-----CSDB Account Details Page----->");
	}

	/**
	 * 
	 * @This Method for checking the cors issue while retrieving the customer
	 *       account
	 * 
	 * @throws Exception
	 *
	 */
	public void verifyCSDBCORSAccount() throws Exception {
		boolean flag = CommonFunctions.isExist(getDriver(), "//h1[contains(text(),'Account Details')]");
		if (flag) {
			CommonFunctions.logMessage("Account was retrieved successfully");
		} else {
			CommonFunctions.logErrorMessageStopExecution(
					"Error CORS issue identified with this account\n " + DefectList.getP2Defect("CSS-84"));
		}
	}

	/**
	 * @This Method to click the CSDB Core Sessions
	 *
	 * @throws Exception
	 */

	public void clickCSDBCoreSessions() throws Exception {
		CommonFunctions.clickWebelement(eleCustomerCoreSessions, "core sessions tab");
	}

	/**
	 * @This Method to click the CSDB Action History
	 *
	 * @throws Exception
	 */

	public void clickCSDBActionHistory() throws Exception {
		CommonFunctions.clickWebelement(eleCustomerActionHistory, "action history tab");
	}

	/**
	 * @This Method to click the CSDB back to menu
	 *
	 * @throws Exception
	 */

	public void clickCSDBBack() throws Exception {
		CommonFunctions.clickJSE(eleBack, "Back button");
	}

	/**
	 * 
	 * @This method to validate the customer details table based on the column
	 *       header
	 * 
	 * @param columnName
	 * @param customerInfo
	 * @throws Exception
	 */
	public void customerInfoTable(String columnName, String customerInfo) throws Exception {
		boolean stopFlag = true;

		String pages = CommonFunctions
				.regexText("of[\\s\\d]+", CommonFunctions.getTextOfElement(elePages, "table pages number"))
				.replaceAll("of|\\s+", "");
		for (int page = 1; page < Integer.parseInt(pages); page++) {

			for (int header = 0; header < eleCustomerInfoTableHeaders.size() && stopFlag; header++) {
				String tableHeaders = CommonFunctions.getTextOfElement(eleCustomerInfoTableHeaders.get(header),
						"customer table header");

				if (tableHeaders.equalsIgnoreCase(columnName)) {

					for (int row = 0; row < eleCustomerInfoTableRows.size(); row++) {
						WebElement eleTableData = CommonFunctions.findElementByXpath(getDriver(),
								"//tr[" + (row + 1) + "]//td[" + (header + 1) + "]", "table data element");
						String tableData = CommonFunctions.getTextOfElement(eleTableData, "customer table info");

						if (tableData.equalsIgnoreCase(customerInfo)) {
							CommonFunctions.logMessage("Expected table result " + customerInfo
									+ " matched the actual result " + tableData + " from the column " + tableHeaders);
							stopFlag = false;
							break;
						}
					}
					break;
				}
			}
			if (Integer.parseInt(pages) != page) {
				if (stopFlag) {
					CommonFunctions.clickWebelement(eleTableNextBtn, "table next button");
					Thread.sleep(2000);
				}
			}

		}

		if (stopFlag) {
			CommonFunctions.logErrorMessage("Error, Unable to find the customer info " + customerInfo
					+ " searched in the column " + columnName);
		}
	}

	/**
	 * 
	 * @This method is used to validate the session table info for both core, legacy
	 *       and managed profile tab
	 * 
	 * @param sessionType
	 * @param sessionId
	 * @param testTakerName
	 * @param dob
	 * @param result
	 * @throws Exception
	 * 
	 */
	public void validateSessionTableInfo(String sessionType, String sessionId, String testTakerFirstName,
			String testTakerSecondName, String dob, String result, String procedure) throws Exception {
		boolean stopFlag = true;
		Map<String, Integer> colNum = new HashMap<String, Integer>();

		for (int tableMap = 0; tableMap < eleCustomerInfoTableHeaders.size(); tableMap++) {
			String sessionTableHeader = CommonFunctions.getTextOfElement(eleCustomerInfoTableHeaders.get(tableMap),
					"customer session table header");
			colNum.put(sessionTableHeader, (tableMap + 1));
		}

		String pages = CommonFunctions
				.regexText("of[\\s\\d]+", CommonFunctions.getTextOfElement(elePages, "table pages number"))
				.replaceAll("of|\\s+", "");
		for (int page = 0; page < Integer.parseInt(pages) && stopFlag; page++) {

			for (int session = 0; session < eleCustomerInfoTableRows.size() && stopFlag; session++) {
				String tableSessionId = CommonFunctions.getTextOfElement(CommonFunctions.findElementByXpath(getDriver(),
						"//tr[" + (session + 1) + "]//td[" + colNum.get("Session Id") + "]",
						"session Id table data element"), "customer session id");

				if (sessionId.equalsIgnoreCase(tableSessionId)) {

					String tableHasResults = CommonFunctions.getTextOfElement(CommonFunctions.findElementByXpath(
							getDriver(), "//tr[" + (session + 1) + "]//td[" + colNum.get("Has result?") + "]",
							"session Id table data element"), "customer session has result");

					if (tableHasResults.equalsIgnoreCase("yes")) {

						String tableResult = CommonFunctions.getTextOfElement(CommonFunctions.findElementByXpath(
								getDriver(), "//tr[" + (session + 1) + "]//td[" + colNum.get("Result") + "]",
								"session Id table data element"), "customer session results");
						CommonFunctions
								.logMessage("The session Id " + sessionId + " given has the result as " + tableResult);

						String tableStartDateAndTime = CommonFunctions
								.getTextOfElement(
										CommonFunctions.findElementByXpath(getDriver(),
												"//tr[" + (session + 1) + "]//td[" + colNum.get("Start Date & Time")
														+ "]",
												"session Id table data element"),
										"customer session start date and time");

//						CommonFunctions.logMessage("The session Id " + sessionId + " given has the start date and time as "
//								+ tableStartDateAndTime);

						String tableVisitDuration = CommonFunctions.getTextOfElement(CommonFunctions.findElementByXpath(
								getDriver(), "//tr[" + (session + 1) + "]//td[" + colNum.get("Visit Duration") + "]",
								"session Id table data element"), "customer session visit duration");

//						CommonFunctions.logMessage(
//								"The session Id " + sessionId + " given has the visit duration as " + tableVisitDuration);

						String tableTestName = CommonFunctions
								.getTextOfElement(
										CommonFunctions.findElementByXpath(getDriver(),
												"//tr[" + (session + 1) + "]//td[" + colNum.get("Test taker Name")
														+ "]",
												"session Id table data element"),
										"customer session test name");

						String processedName = testTakerFirstName + " " + testTakerSecondName;

						if (tableTestName.equalsIgnoreCase(processedName)) {
							CommonFunctions.logMessage("The session Id " + sessionId
									+ " given has the test taker name as " + tableTestName);
						} else {
							CommonFunctions.logErrorMessage(
									"The session Id " + sessionId + " doesnt match the expected the test taker name "
											+ processedName + " with actual test taker name " + tableTestName);
						}

						String tableDOB = CommonFunctions.getTextOfElement(CommonFunctions.findElementByXpath(
								getDriver(), "//tr[" + (session + 1) + "]//td[" + colNum.get("DOB") + "]",
								"session Id table data element"), "customer session dob");

						if (tableDOB.equalsIgnoreCase(CommonFunctions.getdata("DateofBirth"))) {
							CommonFunctions.logMessage(
									"The session Id " + sessionId + " given has the date of birth as " + tableDOB);
						} else {
							CommonFunctions.logErrorMessage(
									"The session Id " + sessionId + " doesnt match the expected the date of birth "
											+ dob + " with actual date of birth " + tableDOB);
						}

						if (sessionType.equalsIgnoreCase("Core")) {
							String tableProcedure = CommonFunctions.getTextOfElement(CommonFunctions.findElementByXpath(
									getDriver(), "//tr[" + (session + 1) + "]//td[" + colNum.get("Procedure") + "]",
									"session Id table data element"), "customer session procedure");

							if (tableProcedure.equalsIgnoreCase(procedure)) {
								CommonFunctions.logMessage("The session Id " + sessionId
										+ " given has the procedure as " + tableProcedure);
							} else {
								CommonFunctions.logErrorMessage(
										"The session Id " + sessionId + " doesnt match the expected the procedure "
												+ procedure + " with actual procedure " + tableProcedure);
							}

						}

						WebElement sessionLink = CommonFunctions.findElementByXpath(getDriver(),
								"//tr[" + (session + 1) + "]//td[" + colNum.get("Session Id") + "]",
								"session Id table data element");

						CommonFunctions.clickWebelement(sessionLink, "session id link");

						stopFlag = false;
						break;

					} else if (tableHasResults.equalsIgnoreCase("no")) {
						CommonFunctions.logMessage("The session Id " + sessionId + " given has the result ==> "
								+ tableHasResults + ". So Retrying...");
						CommonFunctions.refreshPage(getDriver());
						Thread.sleep(5000);
					}

				}
			}
			if (Integer.parseInt(pages) != page) {
				if (stopFlag) {
					CommonFunctions.clickWebelement(eleTableNextBtn, "table next button");
					Thread.sleep(2000);
				}
			}

		}

		if (stopFlag) {
			CommonFunctions
					.logErrorMessage("Error, Unable to find the session Id " + sessionId + " searched in the column.");
		}
	}

	/**
	 * @apiNote Method is used to verify the CSDB personal details
	 * 
	 * @param customerEmail
	 * 
	 * @throws Exception
	 */
	public void verifyCSDBCustomerPersonalDetails(String customerEmail) throws Exception {
		String firstName = CommonFunctions.getTextOfElement(eleCustomerFirstName, "Customer first name");
		String lastName = CommonFunctions.getTextOfElement(eleCustomerLastName, "Customer last name");
		String emailAddress = CommonFunctions.getTextOfElement(eleCustomerEmailAddress, "Customer email address");
		String[] dob = CommonFunctions.getTextOfElement(eleCustomerDOB, "Customer DOB").split(" ");

		if (firstName.equalsIgnoreCase(CommonFunctions.getdata("FirstName"))) {
			CommonFunctions.logMessage("Customer registered first name matched the CSDB first name ===> " + firstName);
		} else {
			CommonFunctions.logErrorMessage("Customer registered first name " + CommonFunctions.getdata("FirstName")
					+ " not matched CSDB first name " + firstName);
		}

		if (lastName.equalsIgnoreCase(CommonFunctions.getdata("LastName"))) {
			CommonFunctions.logMessage("Customer registered last name matched the CSDB last name ===> " + lastName);
		} else {
			CommonFunctions.logErrorMessage("Customer registered last name " + CommonFunctions.getdata("LastName")
					+ " not matched CSDB first name " + lastName);
		}

		if (emailAddress.equalsIgnoreCase(customerEmail)) {
			CommonFunctions.logMessage(
					"Customer registered email address matched the CSDB email address ===> " + emailAddress);
		} else {
			CommonFunctions.logErrorMessage("Customer registered email address " + customerEmail
					+ " not matched CSDB email address " + emailAddress);
		}

		String processedDOB = CommonFunctions.monthHashMap(CommonFunctions.getdata("DateofBirth").split("/")[0]) + " "
				+ CommonFunctions.getdata("DateofBirth").split("/")[1] + ", "
				+ CommonFunctions.getdata("DateofBirth").split("/")[2];

		if (Integer.parseInt(dob[1].replace(",", "")) <= 9) {
			dob[1] = "0" + dob[1];
		}

		String dateOfBirth = dob[0] + " " + dob[1] + " " + dob[2];

		if (dateOfBirth.equalsIgnoreCase(processedDOB)) {
			CommonFunctions
					.logMessage("Customer registered date of birth matched the CSDB date of birth ===> " + dateOfBirth);
		} else {
			CommonFunctions.logErrorMessage("Customer registered date of birth " + processedDOB
					+ " not matched CSDB date of birth " + dateOfBirth);
		}
	}

	/**
	 * @apiNote Method is used to verify the CSDB personal details
	 * @param customerEmail
	 * @throws Exception
	 */
	public void verifyCSDBCustomerUserId(String customerEmail) throws Exception {
		String emailAddress = CommonFunctions.getTextOfElement(eleCustomerEmailAddress, "Customer email address");
		if (emailAddress.equalsIgnoreCase(customerEmail)) {
			CommonFunctions.logMessage(
					"Customer registered email address matched the CSDB email address ===> " + emailAddress);
		} else {
			CommonFunctions.logErrorMessageStopExecution("Customer registered email address " + customerEmail
					+ " not matched CSDB email address " + emailAddress);
		}

	}

	/**
	 * @apiNote Method is used to verify the CSDB contact details
	 * @throws Exception
	 */
	public void verifyCustomerContactDetails() throws Exception {

		String phoneNumber = CommonFunctions.getTextOfElement(eleCustomerPhoneNumber, "Customer phone number")
				.replaceAll("[^a-zA-Z0-9]", "");
		String customerFullAddress = CommonFunctions.getTextOfElement(eleCustomerAddress, "Customer address")
				.replaceAll("Address|\\n", " ").trim();

		if (phoneNumber.equalsIgnoreCase(CommonFunctions.getdata("PhNumber"))) {
			CommonFunctions
					.logMessage("Customer registered phone number matched the CSDB phone number ===> " + phoneNumber);
		} else {
			CommonFunctions.logErrorMessage("Customer registered phone number " + CommonFunctions.getdata("PhNumber")
					+ " not matched CSDB phone number " + phoneNumber);
		}

		String processedAddress = CommonFunctions.getdata("Address") + " Apt #"
				+ CommonFunctions.getdata("BuildingNumber") + " " + CommonFunctions.getdata("City") + ", "
				+ CommonFunctions.getdata("State") + " " + CommonFunctions.getdata("ZipCode");

		if (customerFullAddress.equalsIgnoreCase(processedAddress)) {
			CommonFunctions
					.logMessage("Customer registered address matched the CSDB address ===> " + customerFullAddress);
		} else {
			CommonFunctions.logErrorMessage("Customer registered address " + processedAddress
					+ " not matched CSDB address " + customerFullAddress);
		}

	}

	/**
	 * @apiNote Method is used to verify the Customer order details in CSDB
	 * @throws Exception
	 */
	public void verifyCustomerOrderDetails() throws Exception {

		String orderDateSplit[] = CommonFunctions.getTextOfElement(eleCustomerOrderDate, "Customer order date")
				.split("/");
		if (Integer.parseInt(orderDateSplit[0]) <= 9) {
			orderDateSplit[1] = "0" + orderDateSplit[1];
		}
		String orderDate = orderDateSplit[0] + "/" + orderDateSplit[1] + "/" + orderDateSplit[2];
		String subTotal = CommonFunctions.getTextOfElement(eleCsdbSubTotal, "sub total").replace("$", "");
		String shippingCost = CommonFunctions.getTextOfElement(eleCsdbShippingPrice, "shipping cost").replace("$", "");
		String tax = CommonFunctions.getTextOfElement(eleCsdbTax, "tax rate").replace("$", "");
		String totalCost = CommonFunctions.getTextOfElement(eleCsdbTotalPrice, "total order price").replace("$", "");

		if (orderDate.equalsIgnoreCase(getOrderedDate())) {
			CommonFunctions.logMessage("Order placed date matched the CSDB order date ===> " + orderDate);
		} else {
			CommonFunctions.logErrorMessageStopExecution(
					"Order placed date " + getOrderedDate() + " not matched CSDB order date " + orderDate);
		}

		if (subTotal.equalsIgnoreCase(getOrderSubRate())) {
			CommonFunctions.logMessage("Order placed subtotal matched the CSDB order subtotal ===> " + subTotal);
		} else {
			CommonFunctions.logErrorMessageStopExecution(
					"Order placed subtotal " + orderSubRate + " not matched CSDB order subtotal " + subTotal);
		}

		if (shippingCost.equalsIgnoreCase(getOrderShippingRate())) {
			CommonFunctions.logMessage(
					"Order placed shipping price matched the CSDB order shipping price ===> " + shippingCost);
		} else {
			CommonFunctions.logErrorMessageStopExecution("Order placed shipping price " + orderShippingRate
					+ " not matched CSDB order shipping price " + shippingCost);
		}

		if (tax.equalsIgnoreCase(getOrderTaxRate())) {
			CommonFunctions.logMessage("Order placed shipping tax matched the CSDB order shipping tax ===> " + tax);
		} else {
			CommonFunctions.logErrorMessageStopExecution(
					"Order placed shipping tax " + orderTaxRate + " not matched CSDB order shipping tax " + tax);
		}

		if (totalCost.equalsIgnoreCase(getOrderTotalAmount())) {
			CommonFunctions.logMessage("Order placed total price matched the CSDB order total price ===> " + totalCost);
		} else {
			CommonFunctions.logErrorMessageStopExecution("Order placed total price " + orderTotalAmount
					+ " not matched CSDB order total price " + totalCost);
		}
	}

	/**
	 * This method to logout from CSDB
	 * 
	 * @throws Exception
	 */

	public void logOut() throws Exception {
		CommonFunctions.clickWebelement(eleLogOut, "LogOut");
	}

	/**
	 * This method to click the reset password link
	 * 
	 * @throws Exception
	 */

	public void reSetPasswordLink() throws Exception {
		CommonFunctions.clickWebelement(eleAccountOptions, "AccountOptions");
		CommonFunctions.clickWebelement(eleReSetPasswordLink, "Reset Password");
	}

	/**
	 * This Method to validate the Edit orderDetails
	 * 
	 *
	 * @throws Exception
	 */

	public void validateEditOrderDetails() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());

		try {
			new WebDriverWait(getDriver(), 10).until(ExpectedConditions.visibilityOf(eleCsdbEditOrderDetails));

			CommonFunctions.logErrorMessage("Edit order detail is displayed for Order Read Only role in CSDB ");
		} catch (Exception e) {
			CommonFunctions.logMessage("Edit order detail is not displayed for Order Read Only role in CSDB ");
		}

	}

	/**
	 * This Method to validate the Edit orderDetails
	 * 
	 *
	 * @throws Exception
	 */

	public void validatingUserReadOnlyRoleDetails() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());

		try {
			new WebDriverWait(getDriver(), 5).until(ExpectedConditions.visibilityOf(eleReSetPasswordLink));

			CommonFunctions.logErrorMessage("ResetPasswordLink is displayed for User Read Only role in CSDB ");
		} catch (Exception e) {
			CommonFunctions.logMessage("ResetPasswordLink is not displayed for User Read Only role in CSDB ");
		}

		try {
			new WebDriverWait(getDriver(), 5).until(ExpectedConditions.visibilityOf(eleChangePassword));

			CommonFunctions.logErrorMessage("Change Password is displayed for User Read Only role in CSDB ");
		} catch (Exception e) {
			CommonFunctions.logMessage("Change Password is not displayed for User Read Only role in CSDB ");
		}

		try {
			new WebDriverWait(getDriver(), 5).until(ExpectedConditions.visibilityOf(eleUserEditDetails));

			CommonFunctions.logErrorMessage("User Edit Details is displayed for User Read Only role in CSDB ");
		} catch (Exception e) {
			CommonFunctions.logMessage("User Edit Details is not displayed for User Read Only role in CSDB ");
		}

		try {
			new WebDriverWait(getDriver(), 5).until(ExpectedConditions.visibilityOf(eleOrderDetails));

			CommonFunctions.logErrorMessage("Order Detail Header is displayed for User Read Only role in CSDB ");
		} catch (Exception e) {
			CommonFunctions.logMessage("Order Detail Header is not displayed for User Read Only role in CSDB ");
		}

	}

	/**
	 * Method to edit firstName in csdb
	 * 
	 * 
	 * @throws Exception
	 */

	public void editFirstAndLastName(String fName, String lName) throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		CommonFunctions.clickWebelement(eleUserFNameEditDetails, "First Name");
		CommonFunctions.sendKeysWithDeleteAll(eleEditFirstName, fName, "edit first name");

		CommonFunctions.clickWebelement(eleUserLNameEditDetails, "Last Name");
		CommonFunctions.sendKeysWithDeleteAll(eleEditLastName, lName, "edit last name");
	}

	/**
	 * Method to edit PhoneNumber in csdb
	 * 
	 * 
	 * @throws Exception
	 */

	public void editPhoneNumb(String phNumber) throws Exception {

		CommonFunctions.clickWebelement(eleUserPhNumbEditDetails, "Phone Number");
		CommonFunctions.sendKeysWithDeleteAll(eleEditPhoneNumber, phNumber, "edit phone number");
	}

	/**
	 * Method to edit DOB in csdb
	 * 
	 * 
	 * @throws Exception
	 */

	public void editDob(String dob) throws Exception {

		CommonFunctions.clickWebelement(eleUserDobEditDetails, "DOB");
		CommonFunctions.sendKeysWithDeleteAll(eleEditDob, dob, "edit date of birth");
		eleEditDob.sendKeys(Keys.ENTER);
	}

	/**
	 * Method to edit streetAddress in csdb
	 * 
	 * 
	 * @throws Exception
	 */

	public void editstreetAddress(String street) throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		CommonFunctions.clickWebelement(eleUserAddEditDetails, "Address");
		CommonFunctions.sendKeysWithDeleteAll(eleEditStreetAddress, street, "edit street");
	}

	/**
	 * Method to edit UnitNumber in csdb
	 * 
	 * 
	 * @throws Exception
	 */

	public void editUnitNumb(String unitNumber) throws Exception {
		CommonFunctions.sendKeysWithDeleteAll(eleEditUnitNumber, unitNumber, "edit unit number");
	}

	/**
	 * Method to edit Zipcode in csdb
	 * 
	 * 
	 * @throws Exception
	 */

	public void editZipcode(String zipCode) throws Exception {
		CommonFunctions.sendKeysWithDeleteAll(eleEditZipCode, zipCode, "edit zip code");
	}

	/**
	 * Method to edit state in csdb
	 * 
	 * @param state
	 * 
	 * @throws Exception
	 */

	public void editState(String state) throws Exception {
		CommonFunctions.clickWebelement(eleEditState, "State");
		CommonFunctions.iterateElementClick(eleStateOptions, state);
	}

	/**
	 * Method to edit city in csdb
	 * 
	 * 
	 * @throws Exception
	 */

	public void editCity(String city) throws Exception {
		CommonFunctions.sendKeysWithDeleteAll(eleEditCity, city, "edit city");
		CommonFunctions.clickWebelement(eleSave, "Save");
	}

	/**
	 * @This method is to edit and enter user details in CSDB Customer Details page
	 * 
	 * @throws Exception
	 * 
	 */
	public void updateUserProfile() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());

		CommonFunctions.clickWebelement(eleUserFNameEditDetails, "First Name");
		CommonFunctions.sendKeysWithDeleteAll(eleEditFirstName, CommonFunctions.getdata("FirstName"),
				"edit first name");

		CommonFunctions.clickWebelement(eleUserLNameEditDetails, "Last Name");
		CommonFunctions.sendKeysWithDeleteAll(eleEditLastName, CommonFunctions.getdata("LastName"), "edit last name");

		CommonFunctions.clickWebelement(eleUserPhNumbEditDetails, "Phone Number");
		CommonFunctions.sendKeysWithDeleteAll(eleEditPhoneNumber, CommonFunctions.getdata("PhNumber"),
				"edit phone number");

		CommonFunctions.clickWebelement(eleUserDobEditDetails, "DOB");
		CommonFunctions.sendKeysWithDeleteAll(eleEditDob, CommonFunctions.getdata("DateofBirth"), "edit date of birth");

		CommonFunctions.clickWebelement(eleUserAddEditDetails, "Address");
		CommonFunctions.sendKeysWithDeleteAll(eleEditStreetAddress, CommonFunctions.getdata("Address"), "edit street");
		CommonFunctions.sendKeysWithDeleteAll(eleEditUnitNumber, CommonFunctions.getdata("BuildingNumber"),
				"edit unit number");
		CommonFunctions.sendKeysWithDeleteAll(eleEditZipCode, CommonFunctions.getdata("ZipCode"), "edit zip code");
		CommonFunctions.clickWebelement(eleEditState, "State");
		CommonFunctions.iterateElementClick(eleStateOptions, CommonFunctions.getdata("State"));

		CommonFunctions.clickWebelement(eleSave, "Save");

		boolean flag = CommonFunctions.isExist(getDriver(), "//p[text()='Please enter a city']");
		String alertMessage = CommonFunctions.getTextOfElement(eleAlertMessage, "alert message");
		if (flag) {
			CommonFunctions.logMessage("<========User personal details failure alert validation message: 1 - "
					+ alertMessage + "========>");
			CommonFunctions.clickWebelement(eleSave, "Save");
			CommonFunctions.logMessage("<========User personal details failure alert validation message: 2 - "
					+ CommonFunctions.getTextOfElement(eleAlertPopUp, "alert pop-up") + "========>");
		}
		CommonFunctions.clickWebelement(eleB2BCloseAlertPopUp, "alert message pop-up close button");

		if (CommonFunctions.elementIsInVisible(eleAlertPopUp)) {
			CommonFunctions.logMessage("Fill mandatory fileds alert pop-up is closed successfully");
		} else {
			CommonFunctions.logErrorMessage("Fill mandatory fields alert pop-up failed to close due to DefectID - "
					+ DefectList.getP2Defect("CSS-485"));
		}
		CommonFunctions.logMessage("<========Retrying to enter City name========>");
		CommonFunctions.sendKeysWithDeleteAll(eleEditCity, CommonFunctions.getdata("City"), "edit city");
		CommonFunctions.clickWebelement(eleSave, "Save");
		CommonFunctions.waitForPageLoad(getDriver());
		CommonFunctions.clickWebelement(eleUserFNameEditDetails, "First Name");
		CommonFunctions.clickWebelement(eleCancel, "cancel button");

		String editedFName = CommonFunctions.getTextOfElement(eleCustomerFirstName, "Customer first name");
		if (editedFName.equalsIgnoreCase(CommonFunctions.getdata("FirstName"))) {
			CommonFunctions.logMessage("Customer details are updated successfully");
		} else {
			CommonFunctions.logErrorMessage("Customer details failed to update at first save due to DefectID - "
					+ DefectList.getP2Defect("CSS-486"));
		}

	}

	/**
	 * This Method to validate the Edit orderDetails
	 * 
	 *
	 * @throws Exception
	 */

	public void orderEditDetails() throws Exception {
		editFirstAndLastName(CommonFunctions.getdata("EditedFirstName"), CommonFunctions.getdata("EditedLastName"));
		editPhoneNumb(CommonFunctions.getdata("EditedPhNumber"));
		editDob(CommonFunctions.getdata("EditedDateofBirth"));
		editstreetAddress(CommonFunctions.getdata("EditedAddress"));
		editUnitNumb(CommonFunctions.getdata("EditedBuildingNumber"));
		editZipcode(CommonFunctions.getdata("EditedZipCode"));
		editState(CommonFunctions.getdata("EditedState"));
		editCity(CommonFunctions.getdata("EditedCity"));
	}

	/**
	 * This Method to validate the Edit orderDetails for Super admin role
	 * 
	 *
	 * @throws Exception
	 */

	public void superAdminorderEditDetails() throws Exception {
		CommonFunctions.clickWebelement(eleCsdbEditOrderDetails, "CSDB edit Order Details");
		WebElement superfirstName = getDriver().findElement(By.id("address.firstName"));
		superfirstName.sendKeys(Keys.CONTROL + "a");
		superfirstName.sendKeys(Keys.DELETE);
		CommonFunctions.Sendkeys(superfirstName, CommonFunctions.getdata("EditedFirstName"), "Edited First name");
		WebElement superlastName = getDriver().findElement(By.id("address.lastName"));
		superlastName.sendKeys(Keys.CONTROL + "a");
		superlastName.sendKeys(Keys.DELETE);
		CommonFunctions.Sendkeys(superlastName, CommonFunctions.getdata("EditedLastName"), "Edited Last name");
		WebElement superstreetAddress = getDriver().findElement(By.id("address.streetAddress"));
		superstreetAddress.sendKeys(Keys.CONTROL + "a");
		superstreetAddress.sendKeys(Keys.DELETE);
		CommonFunctions.Sendkeys(superstreetAddress, CommonFunctions.getdata("EditedAddress"), "Edited Address");
		WebElement city = getDriver().findElement(By.id("address.city"));
		city.sendKeys(Keys.CONTROL + "a");
		city.sendKeys(Keys.DELETE);
		CommonFunctions.Sendkeys(city, CommonFunctions.getdata("EditedCity"), "Edited City");
		WebElement zipCode = getDriver().findElement(By.id("address.zipCode"));
		zipCode.sendKeys(Keys.CONTROL + "a");
		zipCode.sendKeys(Keys.DELETE);
		CommonFunctions.Sendkeys(zipCode, CommonFunctions.getdata("EditedZipCode"), "Edited ZipCode");
		CommonFunctions.clickWebelement(eleEditOrder, "CSDB edit Order");

		CommonFunctions.isExist(getDriver(), "//*[contains(text(),'The order was updated successfully')]");
		String banner = CommonFunctions.getTextOfElement(eleOrderEditStatus, "Success banner");
		if (banner.equalsIgnoreCase("The order was updated successfully")) {
			CommonFunctions.logMessage("Update status: " + banner);
		} else {
			CommonFunctions.logErrorMessage("Expected text ===>" + banner
					+ "<=== failed to match with the actual text ===>The order was updated successfully");
		}

		boolean flag = CommonFunctions.isExist(getDriver(), "//main[@id='main']//*[contains(@role,'presentation')]");
		if (flag) {
			new WebDriverWait(getDriver(), 30).until(ExpectedConditions.invisibilityOf(
					getDriver().findElement(By.xpath("//main[@id='main']//*[contains(@role,'presentation')]"))));
			CommonFunctions.logMessage("Load screen spinner has been handled successfully");
		} else {
			CommonFunctions.logMessage("Failed to handle load screen spinner");
		}
	}

	/**
	 * This Method to change password in csdb
	 * 
	 *
	 * @throws Exception
	 */

	public void changePassword() throws Exception {

		CommonFunctions.clickWebelement(eleAccountOptions, "AccountOptions");
		CommonFunctions.clickWebelement(eleChangePassword, "changePassword");
		CommonFunctions.SendkeysAttrib(eleNewPassword, "value", CommonFunctions.getdata("PasswordChange"),
				"New Password");
		CommonFunctions.SendkeysAttrib(eleConfirmPassword, "value", CommonFunctions.getdata("PasswordChange"),
				"Confirm Password");
		CommonFunctions.clickWebelement(elePasswordsubmit, "passwordsubmit");
		clickClosePop();
		try {
			new WebDriverWait(getDriver(), 2).until(ExpectedConditions.visibilityOf(elePasswordconfirmationPrompt));
			CommonFunctions.logMessage("Password successfully changed confirmation prompt is displayed in CSDB ");
		} catch (Exception e) {
			CommonFunctions
					.logErrorMessage("Password successfully changed confirmation prompt is not displayed in CSDB ");
		}

		try {
			new WebDriverWait(getDriver(), 2).until(ExpectedConditions.visibilityOf(eleOrderDetails));

			CommonFunctions.logErrorMessage("Order Detail Header is displayed for User admin role in CSDB ");
		} catch (Exception e) {
			CommonFunctions.logMessage("Order Detail Header is not displayed for User admin role in CSDB ");
		}

	}

	/**
	 * This method for closing the change password pop-up
	 *
	 * @throws Exception
	 */
	public void clickClosePop() throws Exception {
		boolean flag1 = CommonFunctions.elementIsVisible(eleSpinner);
		if (flag1) {
			boolean flag2 = CommonFunctions.elementIsInVisible(eleSpinner);
			if (flag2) {
				if (CommonFunctions.isExist(getDriver(), "//*[contains(text(),'Close')]")) {
					CommonFunctions.logErrorMessage(
							"ChangePassword popup is not closed due to\n" + DefectList.getP2Defect("CSS-204"));
					CommonFunctions.clickWebelement(eleClosePopup, "Close pop");

				} else {
					CommonFunctions
							.logMessage("ChangePassword popup closed successfully while submitting password changes");

				}
			}
		} else {
			CommonFunctions.logErrorMessage("ChangePassword action does not happened");
		}
	}

	/**
	 * 
	 * @This method is to select the reasons for B2B-Replacement
	 * 
	 * @param allReasons
	 * @throws Exception
	 * 
	 */
	public void B2BReasonSelector(String allReasons) throws Exception {
		String[] spltReason = allReasons.split(Pattern.quote("|"));

		if (spltReason.length > 0) {
			for (int reason = 0; reason < spltReason.length; reason++) {
				int reasonNum = Integer.parseInt(spltReason[reason]);
				List<WebElement> eleB2breasonText = getDriver()
						.findElements(By.xpath("//input[@name='replaceReason']"));
				CommonFunctions.clickWebelement(eleB2BreplaceReason.get(reasonNum),
						"Replace reason --> " + eleB2breasonText.get(reasonNum).getAttribute("value"));
				getListReasons().add(eleB2breasonText.get(reasonNum).getAttribute("value"));
			}
		} else {
			CommonFunctions.logErrorMessageStopExecution("Error, Reason is not displayed for replacing the order.");
		}

	}

	/**
	 * @This method is to select the reasons for delete-account
	 * 
	 * @param allReasons
	 * 
	 * @throws Exception
	 */
	public void delReasonSelector(String allReasons) throws Exception {
		String[] spltReason = allReasons.split(Pattern.quote("|"));

		if (spltReason.length > 0) {
			for (int reason = 0; reason < spltReason.length; reason++) {
				int reasonNum = Integer.parseInt(spltReason[reason]);
				List<WebElement> eleDelreasonText = getDriver().findElements(By.xpath("//input[@name='checked']"));
				CommonFunctions.clickWebelement(eleDeleteReason.get(reasonNum),
						"Delete reason --> " + eleDelreasonText.get(reasonNum).getAttribute("value"));
				getListReasons().add(eleDelreasonText.get(reasonNum).getAttribute("value"));
			}
		} else {
			CommonFunctions.logErrorMessageStopExecution("Error, Reason is not displayed for deleting user account.");
		}

	}

	/**
	 * @This method is to increment the quantity of test kit
	 * 
	 * @param quantity
	 * 
	 * @throws Exception
	 * 
	 */
	public void orderQuantityIncrementor(int quantity) throws Exception {
		try {
			if (eleOrderQuantity.isEnabled()) {
				for (int i = 1; i < quantity; i++) {
					CommonFunctions.clickWebelement(eleOrderQuantity, "Order Quantity");
				}
				CommonFunctions.logMessage("Test Kit for quantity of: " + quantity + ", been selected");
			}
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Failed to increment the order quantity");
		}
	}

	/**
	 * 
	 * @This method is to replace customize quantity of test kit for B2B-Replacement
	 * 
	 * @param b2bKitType
	 * 
	 * @param quantity
	 * 
	 * @throws Exception
	 */
	public void B2BReplaceOrder(String b2bKitType, int quantity) throws Exception {
		CommonFunctions.clickWebelement(eleAccountOptions, "AccountOptions");
		CommonFunctions.clickWebelement(eleReplaceWithoutAnOrder, "ReplaceWithoutAnOrder");

		new WebDriverWait(getDriver(), 30).until(ExpectedConditions
				.invisibilityOf(getDriver().findElement(By.xpath("//p[text()='Loading information']"))));

		new CSDBOrderDetailsPage().selectReplaceOrderKit(b2bKitType);
		orderQuantityIncrementor(quantity);
		B2BReasonSelector("1|3");

		CommonFunctions.clickWebelement(eleB2BreplaceButton, "B2B-ReplaceButton");
		boolean flag1 = CommonFunctions.isExist(getDriver(),
				"//*[@class='sc-gsTCUz sc-crrsfI jtBuJx dyzBsk']//*[@role='presentation']");
		if (flag1) {
			new WebDriverWait(getDriver(), 30).until(ExpectedConditions.invisibilityOf(getDriver().findElement(
					By.xpath("//*[@class='sc-gsTCUz sc-crrsfI jtBuJx dyzBsk']//*[@role='presentation']"))));
		}
	}

	/**
	 * 
	 * @This method is to validate successful B2B-Replacement for priority address
	 * 
	 * @throws Exception
	 * 
	 */
	public void priorityAddressValidation() throws Exception {
		boolean flag = CommonFunctions.isExist(getDriver(),
				"//*[@class='AlertBannerMain']//*[contains(text(),'The order was successfully created')]");
		if (flag) {
			CommonFunctions.logMessage("Order Status of priority address: "
					+ CommonFunctions.getTextOfElement(eleOrderConfirmationAlert, "orderAcceptAlert"));
		} else {
			CommonFunctions.logErrorMessageStopExecution(
					"Order failed due to: Defect-ID - " + DefectList.getP2Defect("CSS-250"));
		}
	}

	/**
	 * 
	 * @This method is to validate the error alert banner of B2B-Replacement for
	 *       invalid address. And to update the invalid address with valid address
	 * 
	 * @throws Exception
	 * 
	 */
	public void invalidAddressValidation() throws Exception {
		boolean flag = CommonFunctions.isExist(getDriver(),
				"//*[contains(text(),'We could not get cheapest fulfillment center')]");
		if (flag) {
			CommonFunctions.logMessage("Invalid address Alert banner -->"
					+ CommonFunctions.getTextOfElement(eleOrderRejectionAlert, "orderRejecttAlert")
					+ "<-- is displayed succesfully");
		} else {
			CommonFunctions.logErrorMessageStopExecution(
					"Alert banner of B2B-Replacement for Invalid Address failed to display. Refer Defect-ID: "
							+ DefectList.getP2Defect("CSS-249"));
		}
		CommonFunctions.logMessage("<======Updating with valid address======>");
		CommonFunctions.clickWebelement(eleCloseInvalidAddPopUp, "Invalid address pop-up");
		CommonFunctions.clickWebelement(eleUserAddEditDetails, "Edit address icon");
		CommonFunctions.sendKeysWithDeleteAll(eleEditZipCode, CommonFunctions.getdata("EditedZipCode"), "Zip code");
		CommonFunctions.clickWebelement(eleSave, "Save");

		boolean flag1 = CommonFunctions.isExist(getDriver(),
				"//*[@class='sc-gsTCUz sc-crrsfI jtBuJx dyzBsk']//*[@role='presentation']");
		if (flag1) {
			new WebDriverWait(getDriver(), 30).until(ExpectedConditions.invisibilityOf(getDriver().findElement(
					By.xpath("//*[@class='sc-gsTCUz sc-crrsfI jtBuJx dyzBsk']//*[@role='presentation']"))));
		}
		CommonFunctions.logMessage("<======Retrying with Valid Address======>");
	}

	/**
	 * @This method is to validate successful B2B-Replacement for standard address
	 * 
	 * @throws Exception
	 * 
	 */
	public void standardAddressValidation() throws Exception {
		boolean flag = CommonFunctions.isExist(getDriver(),
				"//*[@class='AlertBannerMain']//*[contains(text(),'The order was successfully created')]");
		if (flag) {
			CommonFunctions.logMessage("Order status of standard address: "
					+ CommonFunctions.getTextOfElement(eleOrderConfirmationAlert, "orderAcceptAlert"));
		} else {
			CommonFunctions.logErrorMessageStopExecution("B2B-Replacement failed for Standard Address");
		}
	}

	/**
	 * @This method is to delete eComm user profile using CSDB-AccountOptions
	 * 
	 * @throws Exception
	 * 
	 */
	public void deleteUserAcc(String username) throws Exception {
		CommonFunctions.clickWebelement(eleAccountOptions, "AccountOptions");
		CommonFunctions.clickWebelement(eleDeleteAcc, "delete account");
		boolean flag = CommonFunctions.isExist(getDriver(),
				"//*[@class='AlertBannerMain']//*[contains(text(),'This customer will lose access to all account information')]");
		if (flag) {
			CommonFunctions.logMessage("Delete Account Banner is displayed: "
					+ CommonFunctions.getTextOfElement(eleDeleteAccBanner, "delete account banner"));
			delReasonSelector("0|4");
		} else {
			CommonFunctions.logErrorMessageStopExecution("Account delete banner failed to display");
		}
		CommonFunctions.clickWebelement(eleDeleteAccButton, "delete account button");

		boolean flag1 = CommonFunctions.isExist(getDriver(),
				"//*[(@class='sc-gsTCUz sc-crrsfI sc-tYoTV fEVToq fepsQY kFxAqa')]//*[@role='presentation']");
		if (flag1) {
			new WebDriverWait(getDriver(), 30).until(ExpectedConditions.invisibilityOf(getDriver().findElement(By.xpath(
					"//*[(@class='sc-gsTCUz sc-crrsfI sc-tYoTV fEVToq fepsQY kFxAqa')]//*[@role='presentation']"))));
			CommonFunctions.logMessage("Delete user confirmation Alert: "
					+ CommonFunctions.getTextOfElement(eleDelConfirmAlert, "delete confirmation alert"));
			CommonFunctions.clickWebelement(eleOkDel, "delete Ok button");
		}

		boolean flag2 = CommonFunctions.isExist(getDriver(),
				"//*[@class='sc-gsTCUz sc-crrsfI jtBuJx dyzBsk']//*[@role='presentation']");
		if (flag2) {
			new WebDriverWait(getDriver(), 30).until(ExpectedConditions.invisibilityOf(getDriver().findElement(
					By.xpath("//*[@class='sc-gsTCUz sc-crrsfI jtBuJx dyzBsk']//*[@role='presentation']"))));
		}
	}

	/**
	 * @This method is to validate B2B-Replacement for customer profile with no
	 *       personal/contact data
	 * 
	 * @throws Exception
	 * 
	 */
	public void emptyProfileB2BCheck() throws Exception {
		CommonFunctions.clickWebelement(eleAccountOptions, "AccountOptions");
		CommonFunctions.clickWebelement(eleReplaceWithoutAnOrder, "ReplaceWithoutAnOrder");

		new WebDriverWait(getDriver(), 30).until(ExpectedConditions
				.invisibilityOf(getDriver().findElement(By.xpath("//p[text()='Loading information']"))));

		if (CommonFunctions.elementIsVisible(eleB2BPopUpHeader)) {
			CommonFunctions.logMessage("B2B-Replacement pop-up header is displayed successfully");
			CommonFunctions.clickWebelement(eleB2BClosePopUp, "B2B pop-up close button");
		} else {
			CommonFunctions
					.logErrorMessageStopExecution("B2B-Replacement pop-up header failed to display due to DefectID - "
							+ DefectList.getP2Defect("CSS-278"));
		}

	}

	/**
	 * This Method verify the CSDB customer
	 * 
	 *
	 * @throws Exception
	 */
	public void verifyCSDBFirstCustomer(String userEmailId) throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		if (eleCustomerEmailAddress.getText().equalsIgnoreCase(userEmailId)) {
			CommonFunctions.logMessage(
					"Verified the customer has been created with the mail id " + eleCustomerEmailAddress.getText());
		} else if (eleCustomerEmailAddress.getText().contains("deleted")
				&& eleCustomerEmailAddress.getText().contains(userEmailId)) {
			CommonFunctions.logMessage("Customer has been deleted successfully - " + eleCustomerEmailAddress.getText());
		} else {
			CommonFunctions
					.logErrorMessageStopExecution("Error, Customer is not available with this id - " + userEmailId);
		}

	}

	/***
	 * This method is for verifying the order of Sessions Table Headers
	 * 
	 * @param webele
	 * @param objName
	 * @param headersArr
	 * @throws Exception
	 */
	public void verifySessionsTableHeaders(WebElement webele, String objName, String[] headersArr) throws Exception {
		String header = "";
		boolean flag1 = CommonFunctions.elementIsVisible(webele);
		if (flag1) {
			CommonFunctions.actionClick(webele, objName);
			CommonFunctions.logMessage("" + objName + " table header are in following order: ");
			CommonFunctions.waitForPageLoad(getDriver());
			for (int i = 1; i <= headersArr.length; i++) {
				header = CommonFunctions.getTextOfElement(CommonFunctions.findElementByXpath(getDriver(),
						"(//tr[@role='row'])[1]/th[" + i + "]/div", headersArr[i - 1]), headersArr[i - 1]);
				if (header.equals(headersArr[i - 1]))
					CommonFunctions.logMessage(header + " is displayed.");
				else
					CommonFunctions.logErrorMessageStopExecution(
							"Failed to verify order of the header elements of " + objName + ".");
			}
		} else {
			CommonFunctions.logErrorMessage("Failed to click " + objName + ".");
		}
	}

	/**
	 * 
	 * @This method to check the date of birth is displayed empty when the user
	 *       profile not updated
	 * 
	 * @throws Exception
	 */
	public void verifyDOBEmptyIssue() throws Exception {
		String dob = "";
		try {
			dob = eleCustomerDOB.getText();
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Error date of birth element is not displayed.");
		}

		if (dob.equalsIgnoreCase("")) {
			CommonFunctions.logMessage(
					"Date of birth displayed empty due to profile details not updated test successfully passed.");
		} else if (dob.equalsIgnoreCase("January 1, 1970")) {
			CommonFunctions.logErrorMessage(
					"Date of birth displayed as " + dob + " test failed \n" + DefectList.getP2Defect("CSS-496"));
		} else {
			CommonFunctions.logErrorMessage(
					"Date of birth should display empty when the user profile is not updated but has text " + dob
							+ ". Test failed!");
		}

	}

	/***
	 * This method is for verifying default sorting of Order Date & Time of core
	 * session are in descending order
	 * 
	 * @throws Exception
	 */
	public void verifySessionSortingDesc() throws Exception {
		String[] dateElements = new String[20];
		int j = 0;

		clickCSDBCoreSessions();
		CommonFunctions.waitForPageLoad(getDriver());
		List<WebElement> webElements = getDriver().findElements(By.xpath("//table/tbody/tr/td[1]"));

		WebElement webelement = CommonFunctions.findElementByXpath(getDriver(), "(//div[@class='sc-hJJQhR jkZEL'])[1]",
				"Sessions Start Date and Time");
		webelement.click();
		webelement.click();

		for (WebElement webElement : webElements) {
			dateElements[j] = webElement.getText();
			j++;
		}

		for (int i = 0; i < dateElements.length - 1; i++) {
			if (Integer.parseInt(dateElements[i].substring(6, 10)) >= Integer
					.parseInt(dateElements[i + 1].substring(6, 10))) {
			} else
				CommonFunctions.logErrorMessageStopExecution("Year Sorting Failed at row " + (i + 1) + " of values "
						+ dateElements[i] + " and " + dateElements[i + 1] + ".");
		}
		CommonFunctions.logMessage("Years are sorted in descending order.");

		// Check order of month if fails Stop Test
		for (int i = 0; i < dateElements.length - 1; i++) {
			if (Integer.parseInt(dateElements[i].substring(0, 2)) >= Integer
					.parseInt(dateElements[i + 1].substring(0, 2))) {
			} else if (Integer.parseInt(dateElements[i].substring(6, 10)) > Integer
					.parseInt(dateElements[i + 1].substring(6, 10))) {
			} else
				CommonFunctions.logErrorMessageStopExecution("Months Sorting Failed at row " + (i + 1) + " of values "
						+ dateElements[i] + " and " + dateElements[i + 1] + ".");
		}
		CommonFunctions.logMessage("Months are sorted in descending order.");

		// Check order of day if fails Stop Test
		for (int i = 0; i < dateElements.length - 1; i++) {
			if (Integer.parseInt(dateElements[i].substring(3, 5)) >= Integer
					.parseInt(dateElements[i + 1].substring(3, 5))) {
			} else if (Integer.parseInt(dateElements[i].substring(0, 2)) > Integer
					.parseInt(dateElements[i + 1].substring(0, 2))) {
			} else if (Integer.parseInt(dateElements[i].substring(6, 10)) > Integer
					.parseInt(dateElements[i + 1].substring(6, 10))) {
			} else
				CommonFunctions.logErrorMessageStopExecution("Day Sorting Failed at row " + (i + 1) + " of values "
						+ dateElements[i] + " and " + dateElements[i + 1] + ".");
		}
		CommonFunctions.logMessage("Days are sorted in descending order.");

		// Check order of hours if fails Stop Test
		for (int i = 0; i < dateElements.length - 1; i++) {
			if (Integer.parseInt(dateElements[i].substring(10, 13).trim().replaceAll(":", "")) >= Integer
					.parseInt(dateElements[i + 1].substring(10, 13).trim().replaceAll(":", ""))) {
			} else if (Integer.parseInt(dateElements[i].substring(3, 5)) >= Integer
					.parseInt(dateElements[i + 1].substring(3, 5))) {
			} else if (Integer.parseInt(dateElements[i].substring(0, 2)) > Integer
					.parseInt(dateElements[i + 1].substring(0, 2))) {
			} else if (Integer.parseInt(dateElements[i].substring(6, 10)) > Integer
					.parseInt(dateElements[i + 1].substring(6, 10))) {
			} else
				CommonFunctions.logErrorMessageStopExecution("Hour Sorting Failed at row " + (i + 1) + " of values "
						+ dateElements[i] + " and " + dateElements[i + 1] + ".");
		}
		CommonFunctions.logMessage("Hours are sorted in descending order.");

		// Check order of minutes if fails stop test
		for (int i = 0; i < dateElements.length - 1; i++) {
			if (Integer.parseInt(dateElements[i].substring(13, 16).trim().replaceAll(":", "")) >= Integer
					.parseInt(dateElements[i + 1].substring(13, 16).trim().replaceAll(":", ""))) {
			} else if (Integer.parseInt(dateElements[i].substring(10, 13).trim().replaceAll(":", "")) >= Integer
					.parseInt(dateElements[i + 1].substring(10, 13).trim().replaceAll(":", ""))) {
			} else if (Integer.parseInt(dateElements[i].substring(3, 5)) >= Integer
					.parseInt(dateElements[i + 1].substring(3, 5))) {
			} else
				CommonFunctions.logErrorMessageStopExecution("Minutes Sorting Failed at row " + (i + 1) + " of values "
						+ dateElements[i] + " and " + dateElements[i + 1] + ".");
		}
		CommonFunctions.logMessage("Minutes are sorted in descending order.");
	}

	/**
	 * 
	 * @This method is to validate the reasons for Refund and Replacement
	 * 
	 * @param allReasons
	 * @throws Exception
	 * 
	 */
	public void validatingReasons(String allReasons, String options) throws Exception {
		boolean flag = false;
		String[] xlsReason = allReasons.split(Pattern.quote("|"));
		List<WebElement> appReasonList = null;
		getListReasons().clear();
		int allReason = 0;
		if (options.equalsIgnoreCase("Refund")) {
			allReason = (getDriver().findElements(By.xpath("//input[@name='reason']//following::label")).size()) - 2;
			appReasonList = getDriver().findElements(By.xpath("//input[@name='reason']//following::label"));
			CommonFunctions.logMessage("--- Validating Refund reasons ----");
		} else if (options.equalsIgnoreCase("Replace")) {
			CommonFunctions.logMessage("--- Validating Replace reasons ----");
			allReason = (getDriver().findElements(By.xpath("//input[@name='replaceReason']//following::label")).size());
			appReasonList = getDriver().findElements(By.xpath("//input[@name='replaceReason']//following::label"));
		}
		try {
			if (allReason > 0) {
				for (int reason = 0; reason < allReason; reason++) {
					getListReasons().add(appReasonList.get(reason).getText());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			for (int i = 0; i < xlsReason.length; i++) {
				for (int j = 0; j < getListReasons().size(); j++) {
					if (xlsReason[i].equalsIgnoreCase(getListReasons().get(j))) {
						CommonFunctions.logMessage((i + 1) + ". " + getListReasons().get(j) + "- matched.");
						flag = false;
						break;
					} else {
						flag = true;
					}
				}
				if (flag) {
					CommonFunctions.logErrorMessage((i + 1) + ". " + getListReasons().get(i) + "- NOT matched.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		clickCSDBBack();
		if (options.equalsIgnoreCase("Replace")) {
			CommonFunctions.clickJSE(eleClosePopup, "Close pop");
		}
	}

}
