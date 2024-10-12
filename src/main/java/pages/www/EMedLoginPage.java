package main.java.pages.www;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

public class EMedLoginPage extends TestBase {

	private JavascriptExecutor jse = null;

	@FindBy(xpath = "//h1[text()='Login']")
	private WebElement loginHeader;

	@FindBy(xpath = "//h1[text()='Sign up']")
	private WebElement signupHeader;

	@FindBy(id = "email")
	private WebElement emailInput;

	@FindBy(id = "password")
	private WebElement passwordInput;

	@FindBy(xpath = "//button[text()='Login']")
	private WebElement loginButton;

	@FindBy(css = "button[type='button']")
	private WebElement eMedSignUpTab;

	@FindBy(id = "termsConditionAccepted")
	private WebElement termsConditionsButton;

	@FindBy(xpath = "//button[text()='Create Account']")
	private WebElement createAccountButton;

	@FindBy(xpath = "//h1[text()='Verify your email']")
	private WebElement emailVerifyHeader;

	@FindBy(xpath = "//a[text()='Click here']")
	private WebElement forgotpassword;

	@FindBy(id = "forgot-password-header")
	private WebElement forgetPasswordHeader;

	@FindBy(xpath = "//button[text()='Send Reset Code']")
	private WebElement ResetCode;

	@FindBy(xpath = "//input[@id='code0']")
	private WebElement ResetverificationCodeBox;

	@FindBy(xpath = "//button[text()='Update Password']")
	private WebElement UpdatePassword;

	@FindBy(xpath = "//*[contains(text(),'Your password')]")
	private WebElement verifyUpdatePassword;

	@FindBy(xpath = "//button[contains(@class,'AlertBannerClose')]")
	private WebElement closeUpdatePasswordBanner;

	@FindBy(xpath = "//input[@id='code1']")
	private WebElement verificationCodeBox;

	@FindBy(xpath = "//div[contains(text(),'Email has been successfully verified')]")
	private WebElement successfulAccountCreationNotify;

	@FindBy(xpath = "//*[contains(text(),'I want to get a test.')]//parent::div")
	private WebElement getTest;

	@FindBy(xpath = "//*[contains(text(),'I want to start testing.')]//parent::div")
	private WebElement proctorTestStart;

	@FindBy(css = "button[title='My Account page']")
	private WebElement userAccountIcon;

	@FindBy(xpath = "//span[text()='Sign Out']//ancestor::button")
	private WebElement signOut;

	@FindBy(xpath = "//a[text()='My Orders']")
	private WebElement myOrder;

	@FindBy(xpath = ".//*[contains(text(),'Important Notice')]")
	private WebElement importantNotice;

	@FindBy(className = "no-thanks")
	private WebElement importantNoticeClose;

	public EMedLoginPage() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

	/**
	 * Method to verify the eMed login page
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void verifyLoginPage() throws InterruptedException {
		CommonFunctions.waitForPageLoad(getDriver());
		CommonFunctions.logMessage("<-----eMed Login Page----->");
	}

	/**
	 * Method to verify the eMed SignUp
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void verifySignupTab() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		try {
			if (signupHeader.isDisplayed()) {
				CommonFunctions.logMessage("Signup tab is displayed");
			}
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Signup tab is not displayed");
		}
	}

	/**
	 * Method to verify the eMed PageLogin
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void verifyPageLogin() throws Exception {
		CommonFunctions.elementVisibleToCheck(userAccountIcon, 30);
		try {
			if (userAccountIcon.isDisplayed()) {
				CommonFunctions.logMessage("Page has login successfully\nUser icon is displayed");
			}
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Error while login the page. User icon is not displayed");
		}
	}

	/**
	 * Method to click Forget Password
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void clickforgetPassword() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		CommonFunctions.clickWebelement(forgotpassword, "Forgot your eMed password");
	}

	/**
	 * Method to verify Forget Password page
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void verifyForgotPasswordPage() throws Exception {

		CommonFunctions.waitForPageLoad(getDriver());
		CommonFunctions.switchNextWindow(getDriver());
		CommonFunctions.waitForPageLoad(getDriver());
		try {
			if (forgetPasswordHeader.isDisplayed()) {
				CommonFunctions.logMessage("Page has navigated successfully\nForget Password Page is displayed");
			}
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Error while display ResetPassword/ForgetPasswordPage");
		}
	}

	/**
	 * Method to click Send ReSet Code
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void clickSendResetCode() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		CommonFunctions.clickWebelement(ResetCode, "resend code");
	}

	/**
	 * Method to click reset verification Code
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void resetverificationCode(String vCode) throws Exception {

		CommonFunctions.Sendkeys(ResetverificationCodeBox, vCode, "verification code");
	}

	/**
	 * Method to click Update Password
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void clickUpdatePassword() throws Exception {
		CommonFunctions.clickWebelement(UpdatePassword, "Update password");
	}

	/**
	 * Method to click the Get Test
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void clickGetTest() throws Exception {
		CommonFunctions.clickWebelement(getTest, "get test button");
	}

	/**
	 * Method to click the Start Test
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void clickStartTest() throws Exception {
		CommonFunctions.clickWebelement(proctorTestStart, "start test button");
	}

	/**
	 * Method to click the User Account
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void clickUserAccount() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		CommonFunctions.actionClick(userAccountIcon, "user icon button");
	}

	/**
	 * Method to click the My Order
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void clickMyOrder() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		clickUserAccount();
		CommonFunctions.clickWebelement(myOrder, "myorder button");
	}

	/**
	 * Method to click the SignOut
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void clickSignout() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		clickUserAccount();
		CommonFunctions.clickWebelement(signOut, "signout button");
	}

	/**
	 * Method to enter the email user name
	 * 
	 * @param email
	 * 
	 * @throws Exception
	 *
	 */

	public void enterLoginEmail(String email) throws Exception {
		CommonFunctions.sendKeysIndividual(emailInput, email, "email login text box");
	}

	/**
	 * Method to enter the password
	 * 
	 * @param password
	 * 
	 * @throws Exception
	 *
	 */

	public void enterLoginPassword(String password) throws Exception {
		CommonFunctions.sendKeysIndividual(passwordInput, password, "password login text box");
	}

	/**
	 * Method to click the login button
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void clickLoginButton() throws Exception {
		WebElement loginButton = getDriver().findElement(By.xpath("//button[text()='Login']"));
		CommonFunctions.elementToBeClickable(loginButton, "login button");
		if (BrowserNeed.equalsIgnoreCase("firefox")) {
			CommonFunctions.robotKeyMoveClick(loginButton, 1, "Login button");
		} else {
			loginButton.click();
			CommonFunctions.logMessage("Login button is clicked");
		}
	}

	/**
	 * Method to click the SignUp button
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void clickSignupButton() throws Exception {
		CommonFunctions.clickWebelement(eMedSignUpTab, "eMed signup button");
	}

	/**
	 * Method to enter the email user name
	 * 
	 * @param email
	 * 
	 * @throws Exception
	 *
	 */

	public void enterSignupEmail(String email) throws Exception {
		CommonFunctions.Sendkeys(emailInput, email, "email signup text box");
	}

	/**
	 * Method to enter the password
	 * 
	 * @param password
	 * 
	 * @throws Exception
	 *
	 */

	public void enterSignupPassword(String password) throws Exception {
		CommonFunctions.Sendkeys(passwordInput, password, "password signup text box");
	}

	/**
	 * Method to click the TermsAndConditions
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void clickTermsAndConditions() throws Exception {
		CommonFunctions.clickJSEString("//input[@id='termsConditionAccepted']", "terms and condition button");
	}

	/**
	 * Method to create Account
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void clickCreateAccount() throws Exception {
		CommonFunctions.elementToBeClickable(createAccountButton, "create account button");
		WebElement createAccBtn = getDriver().findElement(By.xpath("//button[text()='Create Account']"));
		createAccBtn.click();
		CommonFunctions.logMessage("Create account button is clicked");

	}

	/**
	 * Method to verify the SignUpProcess
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void verifySignUpProcess() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		try {
			if (emailVerifyHeader.isDisplayed()) {
				CommonFunctions.logMessage("Signup is successfully completed");
			}
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Error while signup process");
		}
	}

	/**
	 * Email verification code method
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void verificationCode(String vCode) throws Exception {

		CommonFunctions.Sendkeys(verificationCodeBox, vCode, "verification code");
	}

	/**
	 * Email verification Method
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void clickVerifyEmail() throws Exception {
		WebElement verifyEmail = getDriver().findElement(By.xpath("//button[text()='Verify Email']"));
		CommonFunctions.elementToBeClickable(verifyEmail, "verify email button");
		verifyEmail.click();
		CommonFunctions.logMessage("Verify email button is clicked");
	}

	/**
	 * Method to verify the successful SignUp
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void verifySuccessfulSignup() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		CommonFunctions.elementIsVisible(successfulAccountCreationNotify);
		try {
			if (successfulAccountCreationNotify.isDisplayed()) {
				CommonFunctions.logMessage("Email verification has successfully done");
			}
		} catch (NoSuchElementException e) {
			CommonFunctions.logErrorMessageStopExecution("Email verification notification is not displayed");
		}
	}

	/**
	 * Method to verify Reset Password Update
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void verifyResetPasswordUpdate() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		try {
			if (verifyUpdatePassword.isDisplayed()) {
				CommonFunctions.logMessage("Password reset has successfully done");
				CommonFunctions.clickWebelement(closeUpdatePasswordBanner, "close update password banner");
			}
		} catch (NoSuchElementException e) {
			CommonFunctions.logErrorMessageStopExecution("Error while password reset..");
		}

	}

	/**
	 * This Method identifies the important notice popup and closes it
	 * 
	 * @throws Exception
	 */
	public void importantNoticeClose() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		try {
			if (importantNotice.isDisplayed()) {
				CommonFunctions.logMessage("Important notice is displayed");
				CommonFunctions.clickJSE(importantNoticeClose, "important notice pop close");
			}
		} catch (NoSuchElementException e) {
			CommonFunctions.logErrorMessageStopExecution("Error Important notice pop not appeared");
		}

	}

}
