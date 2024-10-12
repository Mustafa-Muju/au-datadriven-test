package main.java.pages.csdb;

import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

public class CSDBLandingPage extends TestBase {

	private JavascriptExecutor jse = null;

	@FindBy(id = "email")
	private WebElement eleEmailCSDB;

	@FindBy(id = "password")
	private WebElement elePasswordCSDB;

	@FindBy(xpath = "//*[text()='Login']")
	private WebElement eleLoginCSDB;

	@FindBy(xpath = "//*[@id='header']//following::h1")
	private WebElement eleLandingPageHeader;

	@FindBy(xpath = "//input[contains(@placeholder,'search by name, dependent name')]")
	private WebElement eleUserSearchBox;

	@FindBy(xpath = "(//tbody//tr//td)[3]")
	private WebElement eleFirstCustomer;

	@FindBy(xpath = "//tbody//tr//td")
	private List<WebElement> eleUserSearchResults;

	@FindBy(xpath = "//a[contains(text(),'Customer')]")
	private WebElement eleCustomerDatabase;
	
	@FindBy(xpath ="//*[contains(text(),'Show Enterprise')]")
	private WebElement eleShowEnterprise;
	
	@FindBy(xpath = "//*[text()='Orders']")
	private WebElement eleCustomerOrders;

	@FindBy(xpath = "//*[@placeholder='Search by order ID, order number']")
	private WebElement eleCustomerOrdersSearchID;

	@FindBy(xpath = "//*[contains(text(),'Create Customer')]")
	private WebElement eleCreateCustomer;
	
	@FindBy(xpath = "//input[@id='dateOfBirth']")
	private WebElement eleDob; 
	
	@FindBy(id = "zipCode")
	private WebElement eleZipcode;

	@FindBy(xpath = "//*[contains(text(),'Save')]")
	private WebElement eleCustomerCreationSave;

	@FindBy(xpath = "//*[contains(text(),'Next')]")
	private WebElement eleTableNext;

	@FindBy(xpath = "//p[text()='Filter Customer']")
	private WebElement eleFilterCustomer;

	@FindBy(xpath = "(//div[@class='react-datepicker-wrapper']/div/input)[1]")
	private WebElement eleStartDate;

	@FindBy(xpath = "(//div[@class='react-datepicker-wrapper']/div/input)[2]")
	private WebElement eleEndDate;

	@FindBy(xpath = "//button[@aria-label='Close']")
	private WebElement eleStartDateClose;
	
	@FindBy(xpath = "//table/tbody/p[text()='No records found']")
	private WebElement eleNoRecordsFound;
	
	@FindBy(xpath = "//div/p[text()='Filter Customer']")
	private WebElement eleCustomerFilter;
	
	@FindBy(xpath = "(//div[@class='react-datepicker__input-container']/input)[1]")
	private WebElement eleCurrentDateTxtBox;	
	
	@FindBy(xpath = "//div[(@class='react-datepicker__month')]//div[contains(@class,'react-datepicker__day--today')]")
	private WebElement eleCurrentDate;
	
	@FindBy(xpath = "//input[@id='B2B-Replacement']")
	private WebElement eleReplacement;
	
	@FindBy(xpath = "//button/p[text()='Apply']")
	private WebElement eleApply;

	public CSDBLandingPage() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

	/**
	 * This Method for verifying the CSDB Page
	 * 
	 * 
	 * @throws InterruptedException
	 *
	 */

	/***
	 * This method is to verify Filter customer start and end dates.
	 * 
	 * @throws Exception
	 */
	public void verifyFilterCustomerDates() throws Exception {
		String dateDD = "", futureDatetime = "";
		CommonFunctions.logMessage("<-----CSDB Login Page----->");
		CommonFunctions.actionClick(eleFilterCustomer, "Filter Customer");
		CommonFunctions.actionClick(eleStartDate, "Start Date");
		dateDD = CommonFunctions.getDateBasedonFormat("d", 1);

		CommonFunctions.logMessage("Attempting to select start date as future date " + dateDD + ".");
		try {
			getDriver().findElement(By.xpath("(//div[text()='" + dateDD + "'])[1]")).click();
			futureDatetime = CommonFunctions.getAttributeOfElement(eleStartDate, "Start Date", "value");
			if (futureDatetime.equals(""))
				CommonFunctions.logMessage("Verified start date can not be greater than current date and time ");
			else {
				CommonFunctions.actionClick(eleStartDate, "Start Date");
				CommonFunctions.actionClick(eleStartDateClose, "Start Date close");
				CommonFunctions.actionClick(eleStartDate, "Start Date");
				CommonFunctions.scrollIntoView(getDriver().findElement(By.xpath("(//div[text()='" + dateDD + "'])[2]")));
				getDriver().findElement(By.xpath("(//div[text()='" + dateDD + "'])[2]")).click();
				futureDatetime = CommonFunctions.getAttributeOfElement(eleStartDate, "Start Date", "value");
				if (futureDatetime.equals(""))
					CommonFunctions.logMessage("Verified start date can not be greater than current date and time ");
				else
					CommonFunctions.logErrorMessageStopExecution(
							"Failed to verify that start date time can't be greater than current date time");
			}
			CommonFunctions.scrollIntoView(eleStartDate);
			CommonFunctions.actionClick(eleStartDate, "Start Date");
			CommonFunctions.logMessage("Attempting to select end date as future date " + dateDD + ".");
			CommonFunctions.actionClick(eleEndDate, "End Date");
			getDriver().findElement(By.xpath("(//div[text()='" + dateDD + "'])[1]")).click();
			futureDatetime = CommonFunctions.getAttributeOfElement(eleEndDate, "End Date", "value");
			if (futureDatetime.equals(""))
				CommonFunctions.logMessage("Verified start date can not be greater than current date and time ");
			else {
				CommonFunctions.actionClick(eleEndDate, "End Date");
				CommonFunctions.actionClick(eleStartDateClose, "End Date close");
				CommonFunctions.actionClick(eleEndDate, "End Date");
				CommonFunctions.scrollIntoView(getDriver().findElement(By.xpath("(//div[text()='" + dateDD + "'])[2]")));
				getDriver().findElement(By.xpath("(//div[text()='" + dateDD + "'])[2]")).click();
				futureDatetime = CommonFunctions.getAttributeOfElement(eleEndDate, "End Date", "value");
				if (futureDatetime.equals(""))
					CommonFunctions.logMessage("Verified end date can not be greater than current date and time ");
				else
					CommonFunctions.logErrorMessageStopExecution(
							"Failed to verify that end date time can not be greater than current date time");
			}
		} catch (NoSuchElementException e) {
			CommonFunctions.logErrorMessageStopExecution(
					"Failed to verify  date time can't be greater than current date time");
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Failed to verify  date time filter.");
		}
	}

	public void verifyCSDBPage() throws InterruptedException {
		CommonFunctions.waitForPageLoad(getDriver());
		CommonFunctions.logMessage("<-----CSDB Login Page----->");
	}

	/**
	 * This Method to enter the CSDB mail id
	 * 
	 * @param email
	 * 
	 * @throws Exception
	 * 
	 *
	 */

	public void enterCSDBLogin(String email) throws Exception {
		CommonFunctions.SendkeysWithoutInputLog(eleEmailCSDB, email, "CSDB username text box");
	}

	/**
	 * This Method to enter the CSDB password
	 * 
	 * @param password
	 * 
	 * @throws Exception
	 * 
	 *
	 */

	public void enterCSDBPassword(String password) throws Exception {
		CommonFunctions.SendkeysWithoutInputLog(elePasswordCSDB, password, "CSDB password text box");
	}

	/**
	 * This Method to click the CSDB login button
	 * 
	 * @throws Exception
	 *
	 */

	public void clickCSDBLogin() throws Exception {
		WebElement loginBtn = CommonFunctions.findElementByXpath(getDriver(), "//*[text()='Login']", "CSDB Login button");
		CommonFunctions.actionClick(loginBtn, "CSDB Login button");
	}

	/**
	 * This Method to click the customer database page
	 * 
	 *
	 * @throws Exception
	 */

	public void clickCustomerDataBase() throws Exception {
		CommonFunctions.actionClick(eleCustomerDatabase, "customer database");
		CommonFunctions.waitForPageLoad(getDriver());
	}
	
	/**
	 * @This method is to click Show Enterprise option
	 * 
	 * @throws Exception 
	 *  
	 */
	public void clickShowEnterprise() throws Exception {
		CommonFunctions.clickWebelement(eleShowEnterprise, "Show Enterprise option");
		CommonFunctions.waitForPageLoad(getDriver());
	}

	/**
	 * This Method to click the customer filter database page
	 * 
	 *
	 * @throws Exception
	 */

	public void clickCustomerFilter() throws Exception {
		CommonFunctions.actionClick(eleCustomerFilter, "customer filter");
		CommonFunctions.waitForPageLoad(getDriver());
	}

	/**
	 * This Method verify the CSDB user list is displayed or not
	 * 
	 *
	 * @throws Exception
	 */

	public void verifyCustomerDataList() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		CommonFunctions.elementIsVisible(eleFirstCustomer);
		try {
			if (eleFirstCustomer.isDisplayed()) {
				CommonFunctions.logMessage("Customer database List is displayed");
			}
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("No Data displayed in customer database page");
		}
	}

	/**
	 * This Method to verify the Landing Page
	 * 
	 *
	 * @throws Exception
	 */

	public void verifyLandingPage() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		try {
			if (eleLandingPageHeader.isDisplayed()) {
				CommonFunctions.logMessage("Successfully navigated to Customer Database Page");
			}
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Error while navigating to CSDB Page");
		}
	}

	/**
	 * This Method to search the CSDB user
	 * 
	 *
	 * @throws Exception
	 */

	public void enterCSDBUserSearch(String searchValue) throws Exception {
		CommonFunctions.Sendkeys(eleUserSearchBox, searchValue, "CSDB patient search text box");
		eleUserSearchBox.sendKeys(Keys.ENTER);
		CommonFunctions.waitForPageLoad(getDriver());
	}

	/**
	 * This Method to search the CSDB user by range of date
	 * 
	 *
	 * @throws Exception
	 */

	public void enterCSDBUserFilterByCurrentDate(String previousDate, String currentDate) throws Exception {
		CommonFunctions.clickWebelement(eleCurrentDateTxtBox, "Date TxtBox");
		CommonFunctions.clickJSE(eleCurrentDate, "Current date");
		CommonFunctions.logMessage("Select Current Date");
		CommonFunctions.scrollIntoView(eleReplacement);
		CommonFunctions.clickWebelement(eleReplacement, "Replacement");
		CommonFunctions.clickWebelement(eleApply, "Apply");
	}

	/**
	 * This Method click the CSDB First customer
	 * 
	 *
	 * @throws Exception
	 */

	public void clickCSDBFirstCustomer() throws Exception {

		CommonFunctions.waitForPageLoad(getDriver());
		if(eleUserSearchResults.size()>0) {
			CommonFunctions.actionClick(eleUserSearchResults.get(2), "CSDB first customer");
		} else {
			CommonFunctions.logErrorMessageStopExecution("Failed, No results found in the CSDB customer search table..");
		}
		
	}

	/**
	 * This Method to click the CSDB Order
	 * 
	 *
	 * @throws Exception
	 */

	public void clickCSDBUserOrderId() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		CommonFunctions.clickWebelement(eleCustomerOrders, "Orders");

	}

	/**
	 * This Method to click the save button for customer creation in CSDB
	 * 
	 *
	 * @throws Exception
	 */

	public void clickSave() throws Exception {

		CommonFunctions.clickWebelement(eleCustomerCreationSave, "Save");

	}
	
	/**
	 * @This Method is to enter DOB
	 * 
	 * @param dob
	 */
	public void enterDob(String dob) {
		CommonFunctions.sendKeysIndividual(eleDob, dob, "Date of birth"); 
	}

	/**
	 * Method to enter the zipCode in SingUp page
	 * 
	 * @param zipCode
	 * 
	 * @throws Exception
	 */

	public void enterZipCode(String zipCode) throws Exception {
		CommonFunctions.sendKeysWithDeleteAll(eleZipcode, zipCode, "zip code text");
	}

	/**
	 * This Method to click the Create Customer
	 * 
	 *
	 * @throws Exception
	 */

	public void clickCreateCustomer() throws Exception {

		CommonFunctions.clickWebelement(eleCreateCustomer, "CreateCustomer");

	}

	/**
	 * This Method to search the CSDB Order Using ID
	 * 
	 *
	 * @throws Exception
	 */

	public void enterCSDBUserOrderId(String searchValue) throws Exception {
		CommonFunctions.Sendkeys(eleCustomerOrdersSearchID, searchValue, "CSDB Order ID search box");
	}

	/**
	 * This Method click the CSDB First customer
	 * 
	 *
	 * @throws Exception
	 */

	public void clickCSDBFirstList() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		CommonFunctions.actionClick(eleFirstCustomer, "CSDB first customer");
	}

	/**
	 * This Method to validate the Customer icon
	 * 
	 *
	 * @throws Exception
	 */

	public void validateCustomer() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());

		try {
			new WebDriverWait(getDriver(), 10).until(ExpectedConditions.visibilityOf(eleCustomerDatabase));

			CommonFunctions.logErrorMessage("Customer menu is displayed ");
		} catch (Exception e) {
			CommonFunctions.logMessage("Customer menu is not displayed ");
			new CSDBLandingPage().clickCSDBUserOrderId();
		}

	}

	/**
	 * This Method to validate the Order Icon
	 * 
	 *
	 * @throws Exception
	 */

	public void validateOrderIcon() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());

		try {
			new WebDriverWait(getDriver(), 10).until(ExpectedConditions.visibilityOf(eleCustomerOrders));

			CommonFunctions.logErrorMessage("Customer Order is displayed for User Read Only role in CSDB ");
		} catch (Exception e) {
			CommonFunctions.logMessage("Customer Order is not displayed for User Read Only role in CSDB ");
			new CSDBLandingPage().clickCustomerDataBase();
		}

	}

	/**
	 * This Method to validate the Customer icon
	 * 
	 *
	 * @throws Exception
	 */

	public void validateEditOrderDetails() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());

		try {
			new WebDriverWait(getDriver(), 10).until(ExpectedConditions.visibilityOf(eleCustomerDatabase));

			CommonFunctions.logErrorMessage("Customer menu is displayed ");
		} catch (Exception e) {
			CommonFunctions.logMessage("Customer menu is not displayed ");
			new CSDBLandingPage().clickCSDBUserOrderId();
		}

	}

	/**
	 * 
	 * @This Method to check the ADMIN tool - Search box is case sensitive
	 * 
	 * @param mString
	 * @param rowsToValidate
	 * 
	 * @throws Exception
	 * 
	 */
	public void adminToolSearchValidation(String mString, int rowsToValidate) throws Exception {
		new CSDBLandingPage().verifyLandingPage();
		new CSDBLandingPage().clickCustomerDataBase();

		for (int inputs = 0; inputs < mString.split(Pattern.quote("|")).length; inputs++) {
			int cycles = 0;
			int rCount = 0;
			boolean stopFlag = true;

			String matchString = mString.split(Pattern.quote("|"))[inputs];
			new CSDBLandingPage().enterCSDBUserSearch(matchString);
			Thread.sleep(2000);

			boolean exist = CommonFunctions.isExist(getDriver(), "//*[text()='No records found']");
			if (!exist) {

				while (stopFlag && cycles < 10) {
					
					boolean flag = CommonFunctions.isExist(getDriver(), "//*[contains(text(),'Next') and not(contains(@class,'disabled'))]");
					if (flag && (!stopFlag)) {
						CommonFunctions.clickWebelement(eleTableNext, "table next button");
						Thread.sleep(5000);
					}

					List<WebElement> rows = getDriver().findElements(By.xpath("//tbody//tr"));
					for (int row = 0; row < rows.size(); row++) {

						String firstNameCellData = CommonFunctions.getTextOfElement(
								getDriver().findElement(By.xpath("((//tbody//tr)[" + (row + 1) + "]//td)[1]")),
								"first name data");
						String lastNameCellData = CommonFunctions.getTextOfElement(
								getDriver().findElement(By.xpath("((//tbody//tr)[" + (row + 1) + "]//td)[2]")),
								"last name data");
						String fullNameCellData = firstNameCellData + " " + lastNameCellData;
						String emailNameCellData = CommonFunctions.getTextOfElement(
								getDriver().findElement(By.xpath("((//tbody//tr)[" + (row + 1) + "]//td)[4]")),
								"email data");
						String phoneNoCellData = CommonFunctions.getTextOfElement(
								getDriver().findElement(By.xpath("((//tbody//tr)[" + (row + 1) + "]//td)[5]")),
								"phone number data").replaceAll("\\(|\\)|-|\\s+", "");

						rCount++;

						boolean fullName = Pattern.compile(Pattern.quote(matchString), Pattern.CASE_INSENSITIVE)
								.matcher(fullNameCellData).find();
						boolean firstName = Pattern.compile(Pattern.quote(matchString), Pattern.CASE_INSENSITIVE)
								.matcher(firstNameCellData).find();
						boolean lastName = Pattern.compile(Pattern.quote(matchString), Pattern.CASE_INSENSITIVE)
								.matcher(lastNameCellData).find();
						boolean email = Pattern.compile(Pattern.quote(matchString), Pattern.CASE_INSENSITIVE)
								.matcher(emailNameCellData).find();
						boolean phoneNumber = Pattern.compile(Pattern.quote(matchString), Pattern.CASE_INSENSITIVE)
								.matcher(phoneNoCellData).find();

						if (firstName) {
							CommonFunctions.logMessage("Expected first name matched the actual first name value ===> "
									+ firstNameCellData);
						} else if (lastName) {
							CommonFunctions.logMessage(
									"Expected last name matched the actual last name value ===> " + lastNameCellData);
						} else if (fullName) {
							CommonFunctions.logMessage(
									"Expected full name matched the actual full name value ===> " + fullNameCellData);
						} else if (email) {
							CommonFunctions.logMessage(
									"Expected email matched the actual email value ===> " + emailNameCellData);
						} else if (phoneNumber) {
							CommonFunctions
									.logMessage("Expected phone number matched the actual phone number value ===> "
											+ phoneNoCellData);

						} else {
							CommonFunctions.logErrorMessage(
									"Expected value " + matchString + " doesn't match any values in columns ("
											+ fullNameCellData + ", " + firstNameCellData + ", " + lastNameCellData
											+ ", " + emailNameCellData + ", " + phoneNoCellData + ")");
						}

						if (rowsToValidate == rCount) {
							stopFlag = false;
							break;
						}

					}
					
					cycles++;

				}
			} else {
				CommonFunctions.logErrorMessage("No Record found for the value ====> " + matchString);
			}
		}
	}
	
	/**
	 * @This method is to sort and match the value with CSDB customer table
	 * 
	 * @param headerType
	 * 
	 * @param value
	 * 
	 * @throws Exception
	 */
	public void csdbCustomertableSort(String headerType, String value) throws Exception {
		new CSDBLandingPage().clickCustomerDataBase(); 
		CommonFunctions.waitForPageLoad(getDriver()); 
		boolean flag = CommonFunctions.isExist(getDriver(), "//div[contains(text(),'First Name')]"); 
		if (flag) { 
			CommonFunctions.logMessage("<===Customer Details page===>"); 
		} else { 
			CommonFunctions.logErrorMessage("Failed to redirect to Customer details page"); 
		} 

		List<WebElement> tableHeader = getDriver().findElements(By.tagName("th")); 
		int a = 0; 
		int stopCount = 0; 
		boolean flag2 = true; 

		for (int i = 0; i <= tableHeader.size(); i++) { 
			if (tableHeader.get(i).getText().equalsIgnoreCase(headerType)) { 
				a = i + 1; 
				break; 
			} 
		} 
		while (flag2 && stopCount < 5) { 
			List<WebElement> tableRow = getDriver().findElements(By.xpath("//tbody//tr")); 
			for (int j = 1; j <= tableRow.size(); j++) { 
				String tableCell = CommonFunctions 
						.getTextOfElement(getDriver().findElement(By.xpath("//tr[" + j + "]//td[" + a + "]")), ""); 
				if (tableCell.equalsIgnoreCase(value)) { 
					CommonFunctions.logMessage("value matched with ===>" + value); 
					flag2 = false; 
					break; 
				}
			} 
			if (flag2) { 
				CommonFunctions.clickWebelement(eleTableNext, "Next button of Table");
				CommonFunctions.waitForPageLoad(getDriver());
				stopCount++; 
			} 
		}
		if(flag2) {
			CommonFunctions.logErrorMessageStopExecution("System failed to match with expected value ===>"+value);
		}
	} 

}
