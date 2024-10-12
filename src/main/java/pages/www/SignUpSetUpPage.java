package main.java.pages.www;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

public class SignUpSetUpPage extends TestBase {

	private JavascriptExecutor jse = null;

	@FindBy(id = "firstName")
	private WebElement firstName;

	@FindBy(id = "lastName")
	private WebElement lastName;

	@FindBy(id = "dateOfBirth")
	private WebElement dOB;

	@FindBy(xpath = "//div[@id='gender']//div[contains(@class,'dropdown__indicators')]")
	private WebElement gender;

	@FindBy(xpath = "//div[@id='pronoun']//div[contains(@class,'dropdown__indicators')]")
	private WebElement pronoun;

	@FindBy(xpath = "//div[@id='race']//div[contains(@class,'dropdown__indicators')]")
	private WebElement race;

	@FindBy(xpath = "//div[@id='ethnicity']//div[contains(@class,'dropdown__indicators')]")
	private WebElement ethnicity;

	@FindBy(xpath = "//label[@for='hasNoAllergies']")
	private WebElement noAllergies;

	@FindBy(xpath = "//h1[contains(text(),'Setup Your Account')]|//h1[contains(text(),'Account Information')]")
	private WebElement setupAccountHeader;

	@FindBy(id = "zipcode")
	private WebElement zCode;

	@FindBy(xpath = "//div[@id='state']//div[contains(@class,'dropdown__indicators')]")
	private WebElement state;

	@FindBy(id = "city")
	private WebElement city;

	@FindBy(id = "county")
	private WebElement country;

	@FindBy(id = "address")
	private WebElement address;

	@FindBy(id = "unit-number")
	private WebElement unitNumber;

	@FindBy(id = "phoneNumber")
	private WebElement phoneNumber;

	@FindBy(xpath = "//div[@id='documentType']//div[contains(@class,'dropdown__indicators')]")
	private WebElement documentType;

	@FindBy(id = "document-front")
	private WebElement photoIdFront;

	@FindBy(id = "document-front")
	private WebElement photoIdBack;

	@FindBy(id = "selfie")
	private WebElement userSelfie;

	@FindBy(xpath = "//div[contains(@class,'dropdown__option')]")
	private List<WebElement> dropDownList;

	@FindBy(xpath = "//label[@for='signup-terms-checkbox']")
	private WebElement signUpTermsCheckBox;

	@FindBy(xpath = "//label[@for='signup-info-checkbox']")
	private WebElement signUpInfoCheckBox;

	@FindBy(id = "signature-first-name")
	private WebElement signatureFirstName;

	@FindBy(id = "signature-last-name")
	private WebElement signatureLastName;

	@FindBy(xpath = "//h2[text()='Account Created']|//h2[text()='Account Information Saved']")
	private WebElement accountCreationSuccessfulMsg;

	@FindBy(xpath = "//*[@data-testid='home-link']")
	private WebElement accountOkay;

	@FindBy(xpath = "//*[contains(text(),'Please confirm your address')]")
	private WebElement confirmUrAddress;

	@FindBy(id = "entered")
	private WebElement youEnteredAddress;

	@FindBy(id = "address-id-0")
	private WebElement suggestedAddress;

	@FindBy(xpath = "//*[contains(text(),'Continue')]")
	private WebElement confirmAddressContinue;

	public SignUpSetUpPage() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

	/**
	 * Method to verify the SetUp Account Page
	 * 
	 * 
	 * @throws Exception
	 */

	public void verifySetUpAccountPage() throws Exception {
		try {
			if (setupAccountHeader.isDisplayed()) {
				CommonFunctions.logMessage("SetUp account is displayed");
			}
		} catch (NoSuchElementException e) {
			CommonFunctions.logErrorMessageStopExecution("Setup account is not displayed");
		}
	}

	/**
	 * Method to enter the fname in SingUp page
	 * 
	 * @param fname
	 * 
	 * @throws Exception
	 */

	public void enterFirstName(String fname) throws Exception {
		CommonFunctions.SendkeysAttrib(firstName, "value", fname, "first name text");
	}

	/**
	 * Method to enter the lname in SingUp page
	 * 
	 * @param lname
	 * 
	 * @throws Exception
	 */

	public void enterLastName(String lname) throws Exception {
		CommonFunctions.SendkeysAttrib(lastName, "value", lname, "last name text");
	}

	/**
	 * Method to enter the DOB in SingUp page
	 * 
	 * @param DOB
	 * 
	 * @throws Exception
	 */

	public void enterDOB(String DOB) throws Exception {
		dOB.sendKeys(DOB, Keys.ENTER);
		CommonFunctions.logMessage("DOB entered as " + DOB);
	}

	/**
	 * Method to enter the gender in SingUp page
	 * 
	 * @param gend
	 * 
	 * @throws Exception
	 */

	public void selectGender(String gend) throws Exception {
		gender.click();
		CommonFunctions.iterateElementClick(dropDownList, gend);
	}

	/**
	 * Method to enter the Pronoun in SingUp page
	 * 
	 * @param Pronoun
	 * 
	 * @throws Exception
	 */

	public void selectPreferredPronouns(String Pronoun) throws Exception {
		pronoun.click();
		CommonFunctions.iterateElementClick(dropDownList, Pronoun);
	}

	/**
	 * Method to enter the Race in SingUp page
	 * 
	 * @param Race
	 * 
	 * @throws Exception
	 */

	public void selectRaces(String Race) throws Exception {
		race.click();
		CommonFunctions.iterateElementClick(dropDownList, Race);
	}

	/**
	 * Method to enter the Ethinicity in SingUp page
	 * 
	 * @param Ethinicity
	 * 
	 * @throws Exception
	 */

	public void selectEthnicity(String Ethinicity) throws Exception {
		ethnicity.click();
		CommonFunctions.iterateElementClick(dropDownList, Ethinicity);
	}

	/**
	 * Method to click No Allergies in SingUp page
	 * 
	 * 
	 * @throws Exception
	 */

	public void clickNoAllergiesCheckBox() throws Exception {
		CommonFunctions.clickWebelement(noAllergies, "no allergies");
	}

	/**
	 * Method to click Proceed To Contact in SingUp page
	 * 
	 * 
	 * @throws Exception
	 */

	public void clickProceedToContact() throws Exception {
		WebElement proceedToContact = getDriver()
				.findElement(By.xpath("//button[contains(text(),'Proceed to Contact Info')]"));
		CommonFunctions.elementToBeClickable(proceedToContact, "proceed to contact button");
		proceedToContact.click();
		CommonFunctions.logMessage("Proceed to contact button is clicked");
	}

	/**
	 * Method to enter the zipCode in SingUp page
	 * 
	 * @param zipCode
	 * 
	 * @throws Exception
	 */

	public void enterZipCode(String zipCode) throws Exception {
		CommonFunctions.SendkeysAttrib(zCode, "value", zipCode, "zip code text");
	}

	/**
	 * Method to enter the State in SingUp page
	 * 
	 * @param State
	 * 
	 * @throws Exception
	 */

	public void selectState(String State) throws Exception {
		state.click();
		CommonFunctions.iterateElementClick(dropDownList, State);
	}

	/**
	 * Method to enter the City in SingUp page
	 * 
	 * @param City
	 * 
	 * @throws Exception
	 */

	public void enterCity(String City) throws Exception {
		CommonFunctions.SendkeysAttrib(city, "value", City, "city text");
	}

	/**
	 * Method to enter the Country in SingUp page
	 * 
	 * @param Country
	 * 
	 * @throws Exception
	 */

	public void enterCountry(String Country) throws Exception {
		CommonFunctions.SendkeysAttrib(country, "value", Country, "country text");
	}

	/**
	 * Method to enter the Address in SingUp page
	 * 
	 * @param Address
	 * 
	 * @throws Exception
	 */

	public void enterAddress(String Address) throws Exception {
		CommonFunctions.SendkeysAttrib(address, "value", Address, "address text");
	}

	/**
	 * Method to enter the UnitNumber in SingUp page
	 * 
	 * @param UnitNumber
	 * 
	 * @throws Exception
	 */

	public void enterBuildingNumber(String UnitNumber) throws Exception {
		CommonFunctions.SendkeysAttrib(unitNumber, "value", UnitNumber, "address text");
	}

	/**
	 * Method to enter the pNumber in SingUp page
	 * 
	 * @param pNumber
	 * 
	 * @throws Exception
	 */

	public void enterPhoneNumber(String pNumber) throws Exception {
		CommonFunctions.SendkeysAttrib(phoneNumber, "value", pNumber, "phone number text");
	}

	/**
	 * Method to upload ID in SingUp page
	 * 
	 * 
	 * @throws Exception
	 */

	public void clickProceedUploadId() throws Exception {
		WebElement proceedToUploadId = getDriver()
				.findElement(By.xpath("//button[contains(text(),'Proceed to upload id')]"));
		CommonFunctions.elementToBeClickable(proceedToUploadId, "proceed to uploadId button");
		proceedToUploadId.click();
		CommonFunctions.logMessage("Proceed upload Id button is clicked");
	}

	/**
	 * Method to select DocumentType in SingUp page
	 * 
	 * @param docType
	 * 
	 * @throws Exception
	 */

	public void selectDocumentType(String docType) throws Exception {
		documentType.click();
		CommonFunctions.iterateElementClick(dropDownList, docType);
	}

	/**
	 * Method to upload ID Front in SingUp page
	 * 
	 * 
	 * @throws Exception
	 */

	public void uploadIdFront() throws InterruptedException {
		CommonFunctions.scrollIntoView(photoIdFront);
		photoIdFront.sendKeys(uploadFilePath + "LicenseFront.jpg");
		Thread.sleep(5000);
	}

	/**
	 * Method to upload User Selfie in SingUp page
	 * 
	 * 
	 * @throws Exception
	 */

	public void uploadUserSelfie() throws InterruptedException {
		userSelfie.sendKeys(uploadFilePath + "UserSelfie.png");
		Thread.sleep(5000);
	}

	/**
	 * Method to click proceed To Terms in SingUp page
	 * 
	 * 
	 * @throws Exception
	 */

	public void clickProceedToTerms() throws Exception {
		WebElement proceedToTerms = getDriver().findElement(By.xpath("//button[contains(text(),'Proceed to terms')]"));
		CommonFunctions.elementToBeClickable(proceedToTerms, "proceed to terms button");
		proceedToTerms.click();
	}

	/**
	 * Method to click SignUp Terms in SingUp page
	 * 
	 * 
	 * @throws Exception
	 */

	public void clickSignUpTerms() throws Exception {
		CommonFunctions.clickWebelement(signUpTermsCheckBox, "signup terms checkbox");

	}

	/**
	 * Method to click SignUp Info in SingUp page
	 * 
	 * 
	 * @throws Exception
	 */

	public void clickSignUpInfo() throws Exception {
		CommonFunctions.clickWebelement(signUpInfoCheckBox, "signup info checkbox");

	}

	/**
	 * Method to enter the FirstNameSignature in SingUp page
	 * 
	 * @param fName
	 * 
	 * @throws Exception
	 */

	public void enterFirstNameSignature(String fName) throws Exception {
		CommonFunctions.Sendkeys(signatureFirstName, fName, "signature first name");
	}

	/**
	 * Method to enter the LastNameSignature in SingUp page
	 * 
	 * @param lName
	 * 
	 * @throws Exception
	 */

	public void enterLastNameSignature(String lName) throws Exception {
		CommonFunctions.Sendkeys(signatureLastName, lName, "signature last name");
	}

	/**
	 * Method to click proceed to Create Account in SingUp page
	 * 
	 * 
	 * @throws Exception
	 */

	public void clickProceedToCreateAccount() throws Exception {
		WebElement proceedToCreateAccount = getDriver().findElement(By.cssSelector("button[type=submit]"));
		CommonFunctions.elementToBeClickable(proceedToCreateAccount, "proceed to create account button");
		if (BrowserNeed.equalsIgnoreCase("firefox")) {
			CommonFunctions.robotKeyMoveClick(proceedToCreateAccount, 1, "Proceed to create account button");
		} else {
			CommonFunctions.actionClick(proceedToCreateAccount, "Proceed to create account button");
		}
	}

	/**
	 * 
	 * @This method confirm the address given by the user
	 * 
	 * @param address ---> 0 for user entered address and 1 for application
	 *                suggested address
	 * @throws Exception
	 */
	public void confirmYourAddress(String address) throws Exception {
		boolean flag = CommonFunctions.isExist(getDriver(), "//*[contains(text(),'Please confirm your address')]");
		if (flag) {
			CommonFunctions.logMessage("Confirm your address popup is displayed");

			switch (address) {
			case "0":
				youEnteredAddress.sendKeys(Keys.SPACE);
				CommonFunctions.logMessage("You entered address is selected");
				break;

			case "1":
				suggestedAddress.sendKeys(Keys.SPACE);
				CommonFunctions.logMessage("Suggested address is selected");
				break;
			}
			CommonFunctions.clickWebelement(confirmAddressContinue, "confirm address continue button");
		}
	}

	/**
	 * Method to verify the eMed Account is created successfully
	 * 
	 * 
	 * @throws Exception
	 */

	public void verifyEMEDAccountCreateSuccess() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		try {
			if (accountCreationSuccessfulMsg.isDisplayed()) {
				CommonFunctions.logMessage("eMed Account is successfully created");
			}
		} catch (NoSuchElementException e) {
			CommonFunctions.logErrorMessageStopExecution("Error while creating emed account");
		}
	}

	/**
	 * Method to click AccountOkay in SingUp page
	 * 
	 * 
	 * @throws Exception
	 */

	public void clickAccountOkay() throws Exception {
		CommonFunctions.elementToBeClickable(accountOkay, "account okay button");
		WebElement accountOkay = getDriver().findElement(By.xpath("//*[@data-testid='home-link']"));
		CommonFunctions.elementToBeClickable(accountOkay, "account okay button");
		accountOkay.click();
		CommonFunctions.logMessage("Account okay button is clicked");
	}

}
