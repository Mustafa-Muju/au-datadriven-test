package main.java.pages.vlab.core;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.pages.www.NavicaPage;
import main.java.utils.CommonFunctions;

public class CreateAccountPage extends TestBase {

	private static JavascriptExecutor jse = null;

	@FindBy(xpath = "//h4[contains(@class,'has-text')]")
	private WebElement eleSignedMail;

	@FindBy(xpath = "//*[contains(text(),'Continue')]")
	private static WebElement eleContinue;

	@FindBy(xpath = "//a[contains(text(),'Use a different account')]")
	private WebElement eleDiffAC;

	@FindBy(xpath = "(//input[contains(@type,'email')])[1]")
	private static WebElement eleEmail;

	@FindBy(xpath = "(//input[contains(@type,'password')])[2]")
	private static WebElement elePassword;

	@FindBy(xpath = "//button[contains(text(),'Sign In')]")
	private static WebElement eleSignIn;

	@FindBy(xpath = "//a[contains(text(),'Reset password')]")
	private WebElement eleResetPass;

	@FindBy(xpath = "//a[text()='Create account']")
	private WebElement eleCreateAC;

	@FindBy(xpath = "(//input[contains(@type,'email')])[2]")
	private static WebElement eleCreateEmail;

	@FindBy(xpath = "(//input[contains(@type,'password')])[3]")
	private WebElement eleCreatePassword;

	@FindBy(xpath = "//input[contains(@type,'checkbox')]")
	private static WebElement eleCheckBox;

	@FindBy(xpath = "//button[text()='Create account']")
	private static WebElement eleCreatACButton;

	@FindBy(xpath = "//*[contains(text(),'Create NAVICA')]")
	private static WebElement eleNavicaCreateAC;

	@FindBy(xpath = "//a[contains(text(),'Sign in with NAVICA')]")
	private static WebElement eleNavicaSignInAC;

	@FindBy(xpath = "//button[contains(text(),'Save')]")
	private WebElement eleSaveButton;

	@FindBy(xpath = "//button[contains(text(),'Yes')]")
	private WebElement eleYesButton;

	public CreateAccountPage() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

	/**
	 * 
	 * @This method clicks the yes button
	 * 
	 * @throws Exception
	 */

	public void clickYes() throws Exception {
		try {
			boolean flag = CommonFunctions.isExist(getDriver(), "//button[contains(text(),'Yes')]");
			if (flag) {
				CommonFunctions.scrollIntoView(eleYesButton);
				CommonFunctions.clickWebelement(eleYesButton, "Yes button");
			}
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Exception on clicking yes button!");
		}
	}

	/**
	 * 
	 * @This method clicks the save button
	 * 
	 * @throws Exception
	 */

	public void clickSave() throws Exception {
		try {
			boolean flag = CommonFunctions.isExist(getDriver(), "//button[contains(text(),'Save')]");
			if (flag) {
				CommonFunctions.clickWebelement(eleSaveButton, "Save button");
			}
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Exception on clicking save button!");
		}
	}

	/**
	 * @This method verify Consent emed page
	 * 
	 * @throws Exception
	 *
	 */

	public void verifyConfirmationPage() throws Exception {
		CommonFunctions.checkCurrentPageTitle("Sign in with eMed - eMed");
		CommonFunctions.logMessage("Sign in with eMed Page is displayed");
	}

	/**
	 * 
	 * @This method clicks the Continue button
	 * 
	 * @throws Exception
	 */
	public void clickContinue() throws Exception {
		try {
			CommonFunctions.elementVisibleToCheck(eleContinue, 15);
			WebElement Continue = getDriver().findElement(By.xpath("//*[contains(text(),'Continue')]"));
			Continue.click();
			CommonFunctions.logMessage("continue button is clicked");
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Exception on clicking continue button!");
		}
	}

	/**
	 * 
	 * @This method clicks the Use different user button
	 * 
	 * @throws Exception
	 */
	public void clickDifferentUser() throws Exception {
		CommonFunctions.clickWebelement(eleDiffAC, "Use different user button");
	}

	/**
	 * 
	 * @This method clicks the Use of Terms and conditions
	 * 
	 * @throws Exception
	 */
	public void clickTermsAndConditions() throws Exception {
		try {
			WebElement Continue = getDriver().findElement(By.xpath("//input[contains(@type,'checkbox')]"));
			Continue.click();
			CommonFunctions.logMessage("Terms and condition checkbox is clicked");
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Exception on clicking Checkbox!");
		}
	}

	/**
	 * @This method verify Profile page
	 * 
	 * @throws Exception
	 *
	 */

	public void verifyProfile() throws Exception {
		CommonFunctions.checkCurrentPageTitle("Your Profile - eMed");
		CommonFunctions.logMessage("Your Profile - eMed Page is displayed");
	}

	/**
	 * 
	 * @This Method to enter core email
	 *
	 * @param coreEmail
	 *
	 * @throws Exception
	 * 
	 */

	public void enterCoreEmail(String coreEmail) throws Exception {
		try {
			CommonFunctions.Sendkeys(eleEmail, coreEmail, "Core Username");
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Exception entering email in core username text box");
		}
	}

	/**
	 * 
	 * @This Method to enter core password
	 * 
	 * @param corePassword
	 *
	 * @throws Exception
	 * 
	 */

	public void enterCorePassword(String corePassword) throws Exception {
		try {
			CommonFunctions.Sendkeys(elePassword, corePassword, "Core Password");
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Exception entering password in core password text box");
		}
	}

	/**
	 * 
	 * @This Method to enter core new email
	 *
	 * @param coreEmail
	 *
	 * @throws Exception
	 * 
	 */

	public void enterCoreNewEmail(String coreEmail) throws Exception {
		try {
			WebElement email = getDriver().findElement(By.xpath("(//input[contains(@type,'email')])[2]"));
			email.clear();
			email.sendKeys(coreEmail);
			CommonFunctions.logMessage(coreEmail + " email entered in core username text box");
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Exception entering email in core username text box");
		}
	}

	/**
	 * 
	 * @This Method to enter core new password
	 * 
	 * @param corePassword
	 *
	 * @throws Exception
	 * 
	 */

	public void enterCoreNewPassword(String corePassword) throws Exception {
		try {
			WebElement password = getDriver().findElement(By.xpath("(//input[contains(@type,'password')])[3]"));
			password.clear();
			password.sendKeys(corePassword);
			CommonFunctions.logMessage(corePassword + " password entered in core password text box");
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Exception entering password in core password text box");
		}
	}

	/**
	 * 
	 * @This Method to click Core SignIn
	 *
	 * @throws Exception
	 * 
	 */

	public void clickCoreSignIn() throws Exception {
		CommonFunctions.elementVisibleToCheck(getDriver().findElement(By.xpath("//button[contains(text(),'Sign In')]")),
				20);
		try {
			WebElement eleSignIn = getDriver().findElement(By.xpath("//button[contains(text(),'Sign In')]"));
			eleSignIn.click();
			CommonFunctions.logMessage("signIn button is clicked");
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Exception on clicking signIn button!");
		}
	}

	/**
	 * 
	 * @This Method to click core create account
	 *
	 * @throws Exception
	 * 
	 */

	public void clickCreateAccount() throws Exception {
		try {
			CommonFunctions.clickJSEString("//a[text()='Create account']", "create account");
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Exception on clicking create account link!");
		}
		CommonFunctions.waitForPageLoad(getDriver());
	}

	/**
	 * @This Method to click core confirm button
	 * 
	 * @throws Exception
	 * 
	 */

	public void clickConfirmButton() throws Exception {
		WebElement eleConfirmBtn = CommonFunctions.getShadowDomElement(getDriver(),
				"amplify-button[data-test='form-section-form-section-button']>button", "confirm button");
		CommonFunctions.clickWebelement(eleConfirmBtn, "confirm button");
	}

	/**
	 * 
	 * @This method clicks the submit button
	 * 
	 * @throws Exception
	 */
	public void clickCreateAccountSubmit() throws Exception {
		try {
			WebElement eleCreateAC = getDriver().findElement(By.xpath("//button[@type='submit']"));
			eleCreateAC.click();
			CommonFunctions.logMessage("Submit button is clicked");
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Exception on clicking Submit button!");
		}
		CommonFunctions.waitForPageLoad(getDriver());
	}

	/**
	 * 
	 * @This method clicks the create account button
	 * 
	 * @throws Exception
	 */
	public void clickCompleteCreateAccount() throws Exception {
		try {
			WebElement eleCreateAC = getDriver().findElement(By.xpath("//button[text()='Create account']"));
			eleCreateAC.click();
			CommonFunctions.logMessage("Create account button is clicked");
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Exception on clicking create account button!");
		}
		CommonFunctions.waitForPageLoad(getDriver());
	}

	/**
	 * @This Method to enter the core verification code
	 * 
	 * @throws Exception
	 * 
	 */

	public void enterCoreVerifyCode(String verifyCode) throws Exception {
		CommonFunctions.elementVisibleToCheck(getDriver().findElement(By.cssSelector("amplify-authenticator")),
				20);
		WebElement eleVerificationCode = CommonFunctions.getShadowDomElement(getDriver(), "#code", "verification code");
		CommonFunctions.Sendkeys(eleVerificationCode, verifyCode, "verify code");
	}

	/**
	 * 
	 * @This method for eMedAccountSignUpFlow
	 * 
	 * @param Gender
	 * @param Ethinicity
	 * @param Race
	 * 
	 * @throws Exception
	 *
	 */

	public void enterCoreGender() throws Exception {
		new NavicaPage().selectNavicaGender(CommonFunctions.getdata("Gender"));
		CommonFunctions.waitForPageLoad(getDriver());
		new NavicaPage().selectNavicaEthnicity(CommonFunctions.getdata("Ethinicity"));
		CommonFunctions.waitForPageLoad(getDriver());
		new NavicaPage().selectNavicaRace(CommonFunctions.getdata("Race"));
		clickCreateAccountSubmit();
	}

	/**
	 * 
	 * @This Method to enter first name for core
	 * 
	 * @param fname
	 *
	 * @throws Exception
	 * 
	 */

	public void enterCoreFirstName(String fname) throws Exception {
		try {
			WebElement firstnames = getDriver().findElement(By.xpath("//input[@data-path='firstName']"));
			CommonFunctions.Sendkeys(firstnames, fname, "core first name");
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Exception entering first name in core first name text box");
		}
	}

	/**
	 * Method to enter last name for core
	 * 
	 * @param lname
	 *
	 * @throws Exception
	 * 
	 */

	public void enterCoreLastName(String lname) throws Exception {
		try {
			WebElement lastnames = getDriver().findElement(By.xpath("//input[@data-path='lastName']"));
			CommonFunctions.Sendkeys(lastnames, lname, "core last name");
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Exception entering last name in core last name text box");
		}
	}

	/**
	 * Method to enter DOB for core
	 * 
	 * @param date
	 *
	 * @throws Exception
	 * 
	 */

	public void enterCoreDOB(String dob) throws Exception {
		try {
			WebElement eleDob = getDriver().findElement(By.xpath("//input[@data-path='dob']"));
			if (System.getProperty("os.name").contains("OS X")) {
				eleDob.sendKeys(Keys.COMMAND + "a");
			} else {
				eleDob.sendKeys(Keys.CONTROL + "a");
			}
			eleDob.sendKeys(dob);
			CommonFunctions.logMessage(dob + " entered in core date of birth text box");
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Exception entering dob in core date of birth text box");
		}
	}

	/**
	 * Method to enter address for core
	 * 
	 * @param address
	 *
	 * @throws Exception
	 * 
	 */

	public void enterCoreAddress(String address) throws Exception {
		try {
			WebElement addr = getDriver().findElement(By.xpath("//input[@data-path='address1']"));
			CommonFunctions.Sendkeys(addr, address, "core address");
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Exception entering address in core address text box");
		}
	}

	/**
	 * Method to enter city for core
	 * 
	 * @param navicapassword
	 *
	 * @throws Exception
	 * 
	 */

	public void enterCoreCity(String city) throws Exception {
		try {
			WebElement cityName = getDriver().findElement(By.xpath("//input[@data-path='city']"));
			CommonFunctions.Sendkeys(cityName, city, "core city");
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Exception entering cityName in core cityName text box");
		}
	}

	/**
	 * Method to enter zipCode for core
	 * 
	 * @param navicapassword
	 *
	 * @throws Exception
	 * 
	 */

	public void enterCoreZipCode(String zipCode) throws Exception {
		try {
			WebElement postalCode = getDriver().findElement(By.xpath("//input[@data-path='postalCode']"));
			CommonFunctions.Sendkeys(postalCode, zipCode, "core zipcode");
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Exception entering zipCode in core zipCode text box");
		}
	}

	/**
	 * Method to enter Phone for core
	 * 
	 * @param navicapassword
	 *
	 * @throws Exception
	 * 
	 */

	public void enterCorePhoneNumber(String phoneNo) throws Exception {
		try {
			WebElement phNum = getDriver().findElement(By.xpath("//input[@data-path='phone']"));
			CommonFunctions.Sendkeys(phNum, phoneNo, "core phone number");
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Exception entering phone in core phone text box");
		}
	}

	/**
	 * Method to enter state for core
	 * 
	 * @param State
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void selectCoreState(String states) throws Exception {
		try {
			WebElement stateList = getDriver().findElement(By.xpath("//select[@data-path='state']"));
			CommonFunctions.selectDropDownText(stateList, states, "State");
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("\nThe state is not avilable pls refer the report\n");
		}
	}

	/**
	 * Method to select gender for core
	 * 
	 * @param gender
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void selectCoreGender(String genders) throws Exception {
		try {
			WebElement sex = getDriver().findElement(By.xpath("//select[@data-path='demographics.gender']"));
			CommonFunctions.selectDropDownText(sex, genders, "Genders");
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("\nThe Gender is not avilable pls refer the report\n");
		}
	}

	/**
	 * Method to select ethnicity for core
	 * 
	 * @param ethnicity
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void selectCoreEthnicity(String ethnicity) throws Exception {
		try {
			WebElement ethnic = getDriver().findElement(By.xpath("//select[@data-path='demographics.ethnicity']"));
			CommonFunctions.selectDropDownText(ethnic, ethnicity, "Ethnicity");
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("\nThe Ethnicity is not avilable pls refer the report\n");
		}
	}

	/**
	 * Method to select race for core
	 * 
	 * @param race
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void selectCoreRace(String race) throws Exception {
		try {
			WebElement origin = getDriver().findElement(By.xpath("//select[@data-path='demographics.race']"));
			CommonFunctions.selectDropDownText(origin, race, "Race");
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("\nThe Race is not avilable pls refer the report\n");
		}
	}

	/**
	 * @This Method to click the navica create account button
	 * 
	 * @throws Exception
	 * 
	 */

	public void clickNavicaCreateAccount() throws Exception {
		CommonFunctions.clickWebelement(eleNavicaCreateAC, "navica account create button");
	}

	/**
	 * @This Method to click the navica signIn button
	 * 
	 * @throws Exception
	 * 
	 */

	public void clickNavicaSignIn() throws Exception {
		CommonFunctions.clickWebelement(eleNavicaSignInAC, "navica signin button");
	}

}
