package main.java.pages.swaglab;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

public class SwagLabLandingPage extends TestBase {

	private JavascriptExecutor jse = null;

	public SwagLabLandingPage() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

	@FindBy(id = "user-name")
	private WebElement username;

	@FindBy(id = "password")
	private WebElement password;

	@FindBy(id = "login-button")
	private WebElement loginBtn;

	/**
	 * Swag Labs landing page verification
	 * 
	 * @throws Exception
	 *
	 */

	public void verifySwagLoginPage() throws Exception {
		CommonFunctions.checkCurrentPageTitle("Swag Labs");
		CommonFunctions.logMessage("<-----Swag Labs Landing Page----->");
	}

	/**
	 * 
	 * @This method enter the username of the swagslab
	 * 
	 * @param user
	 * @throws Exception
	 */
	public void enterUsername(String user) throws Exception {
		CommonFunctions.Sendkeys(username, user, "username field");
	}

	/**
	 * 
	 * @This method enters the password of the swagslab
	 * 
	 * @param passKey
	 * @throws Exception
	 */
	public void enterPassword(String passKey) throws Exception {
		CommonFunctions.Sendkeys(password, passKey, "password field");
	}

	/**
	 * 
	 * @This method clicks the login button of swagslab
	 * 
	 * @throws Exception
	 */
	public void clickLoginButton() throws Exception {
		CommonFunctions.clickWebelement(loginBtn, "login button");
	}

}
