package main.java.pages.csdb;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

public class CSDBMyAccountPage extends TestBase {

	private JavascriptExecutor jse = null;

	@FindBy(xpath = "//a/span[contains(text(),'Forgot your eMed password?')]")
	private WebElement eleForgotPassword;

	@FindBy(xpath = "//input[@id='email']")
	private WebElement eleEnterEmail;

	@FindBy(xpath = "//button[text()='Send Reset Code']")
	private WebElement eleResetCodeButton;

	@FindBy(xpath = "//input[@id='code0']")
	private WebElement eleResetCode;

	@FindBy(xpath = "//input[@id='password']")
	private WebElement eleResetPassword;

	@FindBy(xpath = "//button[text()='Update Password']")
	private WebElement eleUpdatePassword;

	@FindBy(xpath = "//a[contains(text(),'User Management')]")
	private WebElement eleUserManagement;

	@FindBy(xpath = "//table/tbody/tr[1]/td[3]")
	private WebElement eleMail;

	@FindBy(xpath = "//a[text()='My Account']")
	private WebElement eleMyAccount;

	@FindBy(xpath = "(//div/p[text()='Edit'])[2]")
	private WebElement elePasswordEdit;

	@FindBy(xpath = "//input[@id='oldPassword']")
	private WebElement eleOldPassword;

	@FindBy(id = "newPassword")
	private WebElement eleNewPassword;

	@FindBy(id = "confirmPassword")
	private WebElement eleConfirmPassword;

	@FindBy(xpath = "//button[text()='Save']")
	private WebElement eleSave;

	public CSDBMyAccountPage() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

	/**
	 * This Method to click the Forgot password
	 * 
	 *
	 * @throws Exception
	 */

	public void clickForgotPassword() throws Exception {
		CommonFunctions.clickWebelement(eleForgotPassword, "Forgot Password");
	}

	/**
	 * This Method is used to send the verification code to given CSDB user mail id
	 * 
	 *
	 * @throws Exception
	 */

	public void enterCSDBUserMail(String mailId) throws Exception {
		CommonFunctions.Sendkeys(eleEnterEmail, mailId, "CSDB mail");
		CommonFunctions.clickWebelement(eleResetCodeButton, "ResetCode Button");
	}

	/**
	 * This Method to enter verification code
	 * 
	 *
	 * @throws Exception
	 */

	public void enterCSDBUserMailCode(String code) throws Exception {
		CommonFunctions.Sendkeys(eleResetCode, code, "CSDB code text box");
	}

	/**
	 * This Method to enter csdb password
	 * 
	 *
	 * @throws Exception
	 */

	public void enterCSDBUserPassword(String code) throws Exception {
		CommonFunctions.Sendkeys(eleResetPassword, code, "CSDB code text box");
	}

	/**
	 * This Method to click the CSDB update password button
	 * 
	 *
	 * @throws Exception
	 */

	public void clickCSDBUserUpdatePassword() throws Exception {
		CommonFunctions.clickWebelement(eleUpdatePassword, "Update Password");
	}

	/**
	 * This method to click My Account from CSDB
	 * 
	 * @throws Exception
	 */

	public void clickMyAccount() throws Exception {
		CommonFunctions.clickWebelement(eleMyAccount, "MyAccount");
	}

	/**
	 * This method to click Edit for password from CSDB
	 * 
	 * @throws Exception
	 */

	public void clickEdit() throws Exception {
		CommonFunctions.clickWebelement(elePasswordEdit, "Edit for password change");
	}

	/**
	 * This method used to change the password from CSDB
	 * 
	 * @throws Exception
	 */

	public void changePasswordInCSDBMyAccount() throws Exception {
		CommonFunctions.Sendkeys(eleOldPassword, CommonFunctions.getdata("Password"), "Old Password");
		CommonFunctions.Sendkeys(eleNewPassword, CommonFunctions.getdata("PasswordChange"), "New Password");
		CommonFunctions.Sendkeys(eleConfirmPassword, CommonFunctions.getdata("PasswordChange"), "Confirm Password");
		CommonFunctions.clickWebelement(eleSave, "Save");
		boolean flag = CommonFunctions.isExist(getDriver(), "//*[contains(text(),'Changed password successfuly')]");
		if (flag) {
			CommonFunctions.logMessage("Password successfuly changed!");
			CommonFunctions.waitForPageLoad(getDriver());
		} else {
			CommonFunctions.logErrorMessageStopExecution("Password NOT changed!");
		}
	}
}
