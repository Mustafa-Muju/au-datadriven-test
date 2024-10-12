package main.java.pages.vlab.lktransfer;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

public class LKLoginPage extends TestBase {

	private JavascriptExecutor jse = null;

	@FindBy(id = "UserName")
	private WebElement eleLKUsername;

	@FindBy(id = "Password")
	private WebElement eleLKPassword;

	@FindBy(id = "btnSignIn")
	private WebElement eleLKSignIn;

	@FindBy(className = "forgot-pass-login")
	private WebElement eleLKForgotPassword;

	public LKLoginPage() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

	/**
	 * 
	 * @This method to verify the login page is displayed
	 * 
	 * @throws Exception
	 *
	 */

	public void verifyLKLoginPage() throws Exception {
		CommonFunctions.checkCurrentPageTitle("LKTransfer - Sign In");
		CommonFunctions.logMessage("LK Transfer SignIn Page is Displayed");
	}

	/**
	 * 
	 * @This method is to enter the username for the LK Transfer
	 * 
	 * @param username
	 * @throws Exception
	 */
	public void enterLKUsername(String username) throws Exception {
		CommonFunctions.SendkeysWithoutInputLog(eleLKUsername, username, "LK Username");
	}

	/**
	 * 
	 * @This method is to enter the password for the LK Transfer
	 * 
	 * @param password
	 * @throws Exception
	 */
	public void enterLKPassword(String password) throws Exception {
		CommonFunctions.SendkeysWithoutInputLog(eleLKPassword, password, "LK Password");
	}

	/**
	 * 
	 * @This method is used to click the LK SignIn Button
	 * 
	 * @throws Exception
	 */
	public void clickLKSignIn() throws Exception {
		CommonFunctions.clickWebelement(eleLKSignIn, "LK SignIn Button");
	}

}
