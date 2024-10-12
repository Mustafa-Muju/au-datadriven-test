package main.java.pages.www;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

public class NavicaPage extends TestBase {

	private JavascriptExecutor jse = null;

	@FindBy(xpath = "//button[contains(text(),'Create Navica')]")
	private WebElement navicaCreateAccount;

	@FindBy(xpath = "//button[contains(@aria-label,'Agree And Continue')]")
	private WebElement navicaAgreeTerms;

	@FindBy(xpath = "//input[@placeholder='Email']")
	private WebElement navicaEmail;

	@FindBy(id = "emailVerificationControlSignUp_but_send_code")
	private WebElement navicaSendCode;

	@FindBy(xpath = "//button[contains(text(),'Login with Navica')]")
	private WebElement navicaLogin;

	@FindBy(xpath = "//*[contains(@name,'Email')]")
	private WebElement navicaUserName;

	@FindBy(id = "password")
	private WebElement navicaPassword;

	@FindBy(xpath = "//button[text()='Sign in']")
	private WebElement navicaSignin;

	@FindBy(xpath = "//button[text()='Allow']")
	private WebElement navicaPermissionAllow;

	@FindBy(id = "VerificationCode")
	private WebElement navicaVerificationCodeBox;

	@FindBy(id = "emailVerificationControlSignUp_but_verify_code")
	private WebElement navicaVerifyCode;

	@FindBy(id = "newPassword")
	private WebElement navicaCreatePassword;

	@FindBy(id = "reenterPassword")
	private WebElement navicaConfirmPassword;

	@FindBy(xpath = "//button[@aria-label='Set Password']")
	private WebElement navicaSetPassword;

	@FindBy(id = "scan-license")
	private WebElement navicaScanLicense;

	@FindBy(id = "enter-manually")
	private WebElement navicaEnterManually;

	@FindBy(id = "givenName")
	private WebElement navicaFirstName;

	@FindBy(id = "surname")
	private WebElement navicaLastName;

	@FindBy(id = "date-of-birth")
	private WebElement navicaDOB;

	@FindBy(id = "phone-number")
	private WebElement navicaPhoneNumber;

	@FindBy(id = "streetAddress")
	private WebElement navicaStreetAddress;

	@FindBy(id = "city")
	private WebElement navicaCity;

	@FindBy(id = "postalCode")
	private WebElement navicaPostalCode;

	@FindBy(id = "state")
	private WebElement navicaState;

	@FindBy(xpath = "//button[text()='Next']")
	private WebElement navicaNext;

	@FindBy(id = "extension_gender")
	private WebElement navicaGender;

	@FindBy(id = "extension_ethnicity")
	private WebElement navicaEthnicity;

	@FindBy(id = "extension_race")
	private WebElement navicaRace;

	@FindBy(id = "extension_participantType")
	private WebElement navicaParticipantType;

	@FindBy(xpath = "//button[text()='Create Account']")
	private WebElement navicaSignupAccount;

	@FindBy(xpath = "//a[text()='Click here']")
	private WebElement forgotpassword;

	@FindBy(xpath = "//*[text()='Reset Your Password']")
	private WebElement resetPasswordHeader;

	@FindBy(id = "email")
	private WebElement navicaResetEmail;

	@FindBy(xpath = "//button[text()='Send Code']")
	private WebElement navicaResetCode;

	@FindBy(id = "VerificationCode")
	private WebElement resetVerificationCodeBox;

	@FindBy(id = "emailVerificationControlPwdReset_but_verify_code")
	private WebElement verifyCode;

	@FindBy(id = "newPassword")
	private WebElement newPassword;

	@FindBy(id = "reenterPassword")
	private WebElement reEnterPassword;

	@FindBy(id = "continue")
	private WebElement resetContinue;

	@FindBy(xpath = "//*[text()='Continue']")
	private WebElement verifyEmailContinue;

	public NavicaPage() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

	/**
	 * Method to click Navica create Account
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void clickNavicaCreateAccount() throws Exception {
		CommonFunctions.clickWebelement(navicaCreateAccount, "navica create account button");
	}

	/**
	 * 
	 * @Method to verify the navica landing page displayed successfully without any
	 *         client page authorize issue
	 * 
	 * @throws Exception
	 * 
	 */
	public void verifyNavicaLandingPage() throws Exception {
		CommonFunctions.elementVisibleToCheck(navicaAgreeTerms, 8);
		CommonFunctions.waitForPageLoad(getDriver());

		if (!getDriver().getCurrentUrl().contains("provided+in+the+request+is+not+registered+for+the+client+id")) {
			CommonFunctions.logMessage("Navica page is displayed successfully");
		} else {
			CommonFunctions.logErrorMessageStopExecution(
					"ETIGER-588 - Navica sandbox certificate's issue (affects eMed develop and qa environment)");
		}
	}

	/**
	 * 
	 * Method to click Navica Terms and use
	 * 
	 * @throws Exception
	 * 
	 */

	public void clickNavicaTermsAndUse() throws Exception {
		CommonFunctions.clickWebelement(navicaAgreeTerms, "navica agree terms button");
	}

	public void clickVerifyEmailContinue() throws Exception {
		boolean flag = CommonFunctions.isExist(getDriver(), "//*[text()='Continue']");
		if (flag) {
			CommonFunctions.clickWebelement(verifyEmailContinue, "Verify Email Continue button");
		}
	}

	/**
	 * Method to enter Navica userName
	 * 
	 * @param email
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void enterNavicaCreateEmail(String email) throws Exception {
		CommonFunctions.Sendkeys(navicaEmail, email, "navica signup email box");
	}

	/**
	 * Method to click Navica sendcode
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void clickNavicaSendCode() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		CommonFunctions.actionClick(navicaSendCode, "navica send code button");
	}

	/**
	 * Method to enter Navica verification code
	 * 
	 * @param OTP
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void enterNavicaVerificationCode(String OTP) throws Exception {
		CommonFunctions.Sendkeys(navicaVerificationCodeBox, OTP, "navica verification code box");
	}

	/**
	 * Method to verify Navica verification code
	 * 
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void clickNavicaVerifyCode() throws Exception {
		CommonFunctions.clickWebelement(navicaVerifyCode, "navica verify code button");
	}

	/**
	 * Method to send Navica New Password
	 * 
	 * @param password
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void enterNavicaCreatePassword(String password) throws Exception {
		CommonFunctions.Sendkeys(navicaCreatePassword, password, "navica create password text box");
	}

	/**
	 * Method to confirm Navica New Password
	 * 
	 * @param navicapassword
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void enterNavicaConfirmPassword(String navicapassword) throws Exception {
		CommonFunctions.Sendkeys(navicaConfirmPassword, navicapassword, "navica confirm password text box");
	}

	/**
	 * Method to set Navica Password
	 * 
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void clickNavicaSetPassword() throws Exception {
		CommonFunctions.clickWebelement(navicaSetPassword, "navica set password button");
	}

	/**
	 * Method to click enter Manually
	 * 
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void clickEnterManually() throws Exception {
		boolean flag = CommonFunctions.isExist(getDriver(), "//*[@id='enter-manually']");
		if (flag) {
			CommonFunctions.clickWebelement(navicaEnterManually, "navica enter manually button");
		}
	}

	/**
	 * Method to enter Navica FirstName
	 * 
	 * @param fName
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void enterNavicaFirstName(String fName) throws Exception {
		CommonFunctions.Sendkeys(navicaFirstName, fName, "navica first name text box");
	}

	/**
	 * Method to enter Navica LastName
	 * 
	 * @param lName
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void enterNavicaLastName(String lName) throws Exception {
		CommonFunctions.Sendkeys(navicaLastName, lName, "navica last name text box");
	}

	/**
	 * Method to enter Navica DOB
	 * 
	 * @param dob
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void enterNavicaDOB(String dob) throws Exception {
		CommonFunctions.Sendkeys(navicaDOB, dob, "navica dob text box");
	}

	/**
	 * Method to enter Navica PhoneNumber
	 * 
	 * @param phNumber
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void enterNavicaPhoneNumber(String phNumber) throws Exception {
		CommonFunctions.Sendkeys(navicaPhoneNumber, phNumber, "navica phone number text box");
	}

	/**
	 * Method to enter Navica Address
	 * 
	 * @param Address
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void enterNavicaAddress(String address) throws Exception {
		CommonFunctions.Sendkeys(navicaStreetAddress, address, "navica address text box");
	}

	/**
	 * Method to enter Navica City
	 * 
	 * @param City
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void enterNavicaCity(String city) throws Exception {
		CommonFunctions.Sendkeys(navicaCity, city, "navica city text box");
	}

	/**
	 * Method to enter Navica state
	 * 
	 * @param State
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void selectNavicaState(String state) throws Exception {
		CommonFunctions.selectDropDownValue(navicaState, state, "navica state");
	}

	/**
	 * Method to enter Navica Zipcode
	 * 
	 * @param zipCode
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void enterNavicaZipCode(String zipCode) throws Exception {
		CommonFunctions.Sendkeys(navicaPostalCode, zipCode, "navica zipcode text box");
	}

	/**
	 * Method to next in Navica
	 * 
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void clickNavicaNext() throws Exception {
		CommonFunctions.clickWebelement(navicaNext, "navica next button");
	}

	/**
	 * Method to select gender in Navica
	 * 
	 * @param gender
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void selectNavicaGender(String gender) throws Exception {
		CommonFunctions.selectDropDownText(navicaGender, gender, "navica gender");
	}

	/**
	 * Method to select ethnicity in Navica
	 * 
	 * @param ethnicity
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void selectNavicaEthnicity(String ethnicity) throws Exception {
		CommonFunctions.selectDropDownText(navicaEthnicity, ethnicity, "navica ethnicity");
	}

	/**
	 * Method to select race in Navica
	 * 
	 * @param race
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void selectNavicaRace(String race) throws Exception {
		CommonFunctions.selectDropDownText(navicaRace, race, "navica race");
	}

	/**
	 * Method to select participantType in Navica
	 * 
	 * @param participantType
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void selectNavicaParticipantType(String participantType) throws Exception {
		CommonFunctions.selectDropDownText(navicaParticipantType, participantType, "navica participant type");
	}

	/**
	 * Method to click Navica SetUp Account
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void clickNavicaSignupAccount() throws Exception {
		CommonFunctions.clickWebelement(navicaSignupAccount, "navica create account button");
	}

	/**
	 * Method to click Navica login
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void clickNavicaLogin() throws Exception {
		CommonFunctions.clickWebelement(navicaLogin, "navica login button");
	}

	/**
	 * Method to enter Navica email
	 * 
	 *
	 * @param navicaEmail
	 *
	 * @throws Exception
	 * 
	 */

	public void enterNavicaEmail(String navicaEmail) throws Exception {
		 CommonFunctions.Sendkeys(navicaUserName, navicaEmail, "navica username text box");
	}

	/**
	 * Method to enter Navica navicapassword
	 * 
	 * @param navicapassword
	 *
	 * @throws Exception
	 * 
	 */

	public void enterNavicaPassword(String navicapassword) throws Exception {
		CommonFunctions.Sendkeys(navicaPassword, navicapassword, "navica password text box");
	}

	/**
	 * Method to click Navica SignIn
	 * 
	 *
	 * @throws Exception
	 * 
	 */

	public void clickNavicaSignIn() throws Exception {
		CommonFunctions.clickWebelement(navicaSignin, "navica signin button");
	}

	/**
	 * Method to check Navica Permission
	 * 
	 *
	 * @throws Exception
	 * 
	 */

	public void checkNavicaPermission() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		boolean flag = CommonFunctions.isExist(getDriver(), "//*[@id='emed-permissions-modal' and @style]");
		if (flag) {
			try {
				CommonFunctions.clickWebelement(navicaPermissionAllow, "navica permission allow button");
			} catch (Exception e) {

			}
		}
	}

	/**
	 * Method to click Navica Forget Password
	 * 
	 *
	 * @throws Exception
	 * 
	 */

	public void clickNavicaforgetPassword() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		CommonFunctions.clickWebelement(forgotpassword, "Forgot navica/reset password");
	}

	/**
	 * Method to verify the Navica Forget Password Page
	 * 
	 *
	 * @throws Exception
	 * 
	 */

	public void verifyNavicaForgotPasswordPage() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		CommonFunctions.switchNextWindow(getDriver());
		CommonFunctions.elementIsVisible(resetPasswordHeader);
		try {
			if (resetPasswordHeader.isDisplayed()) {
				CommonFunctions.logMessage("Page has navigated successfully\nNavica Forget Password Page is displayed");
			}
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Error while display the ResetPassword/ForgetPasswordPage");
		}
	}

	/**
	 * Method to enter the Navica Reset Email
	 * 
	 * @param email
	 * 
	 *
	 * @throws Exception
	 * 
	 */

	public void enterNavicaResetEmail(String email) throws Exception {
		CommonFunctions.Sendkeys(navicaResetEmail, email, "navica email text box");
	}

	/**
	 * Method to click the Navica send Reset code
	 * 
	 * 
	 *
	 * @throws Exception
	 * 
	 */

	public void clickNavicaSendResetCode() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		CommonFunctions.clickWebelement(navicaResetCode, "send code");
	}

	/**
	 * Method to enter the Navica verification code
	 * 
	 * @param vCode
	 * 
	 *
	 * @throws Exception
	 * 
	 */

	public void enterResetVerificationCode(String vCode) throws Exception {
		CommonFunctions.Sendkeys(resetVerificationCodeBox, vCode, "reset verification code");
	}

	/**
	 * Method to click the verify code
	 * 
	 *
	 * @throws Exception
	 * 
	 */

	public void clickVerifyCode() throws Exception {
		CommonFunctions.clickWebelement(verifyCode, "verify code button");
	}

	/**
	 * Method to enter Navica New Password
	 * 
	 * @param password
	 * 
	 *
	 * @throws Exception
	 * 
	 */

	public void enterNavicaNewPassword(String password) throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		CommonFunctions.Sendkeys(newPassword, password, "navica new password");
	}

	/**
	 * Method to enter Navica Re enter Password
	 * 
	 * @param password
	 * 
	 *
	 * @throws Exception
	 * 
	 */

	public void enterNavicaReenterPassword(String password) throws Exception {
		CommonFunctions.Sendkeys(reEnterPassword, password, "navica reenter password");
	}

	/**
	 * Method to click Reset to Continue
	 * 
	 * @throws Exception
	 * 
	 */

	public void clickNavicaResetContinue() throws Exception {
		CommonFunctions.clickWebelement(resetContinue, "navica reset continue button");
	}

}
