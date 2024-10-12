package main.java.pages.vlab.vlab1;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

public class ProctorLoginPage extends TestBase {

	private JavascriptExecutor jse = null;

	@FindBy(id = "user_email")
	private WebElement userName;

	@FindBy(id = "user_password")
	private WebElement password;

	@FindBy(xpath = "//button[text()='LOGIN']")
	private WebElement loginButton;

	@FindBy(xpath = "//a[contains(text(),'FORGOT')]")
	private WebElement forgotPassword;

	public ProctorLoginPage() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

	/**
	 * Proctor login page verification
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void verifyProctorLoginPage() throws Exception {
		CommonFunctions.checkCurrentPageTitle("eMed");
		CommonFunctions.logMessage("<-----eMed Proctor Login Page----->");
	}

	/**
	 * Method to enter proctor username
	 * 
	 * @param proctorUser
	 * 
	 * @throws Exception
	 *
	 */

	public void enterProctorUser(String proctorUser) throws Exception {
		CommonFunctions.SendkeysWithoutInputLog(userName, proctorUser, "proctor username");
	}

	/**
	 * Method to enter proctor Password
	 * 
	 * @param proctorPassword
	 * 
	 * @throws Exception
	 *
	 */

	public void enterProctorPassword(String proctorPassword) throws Exception {
		CommonFunctions.SendkeysWithoutInputLog(password, proctorPassword, "proctor password");
	}

	/**
	 * Method to click proctor Login
	 * 
	 * @throws Exception
	 *
	 */

	public void clickProctorLogin() throws Exception {
		CommonFunctions.clickWebelement(loginButton, "proctor login button");
	}

}
