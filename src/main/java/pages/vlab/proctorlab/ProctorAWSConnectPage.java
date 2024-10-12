package main.java.pages.vlab.proctorlab;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

public class ProctorAWSConnectPage extends TestBase {

	private JavascriptExecutor jse = null;

	@FindBy(id = "wdc_username")
	private WebElement amazonUsername;

	@FindBy(id = "wdc_password")
	private WebElement amazonPassword;

	@FindBy(id = "wdc_login_button")
	private WebElement amazonLoginBtn;

	@FindBy(className = "header-title")
	private WebElement AWSProctorMain;

	public ProctorAWSConnectPage() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

	/**
	 * @This method verify Amazon Connect authenticate page
	 * 
	 * @throws Exception
	 *
	 */

	public void verifyAmazonConnectAuthenticatePage() throws Exception {
		CommonFunctions.checkCurrentPageTitle("emed-develop - AWS Apps Authentication");
		CommonFunctions.logMessage("eMed Amazon Connect Page is displayed");
	}

	/**
	 * 
	 * @This method enter the amazon connect proctor username
	 * 
	 * @param username
	 * 
	 * @throws Exception
	 */
	public void enterAmazonUsername(String username) throws Exception {
		CommonFunctions.SendkeysWithoutInputLog(amazonUsername, username, "Amazon proctor username");
	}

	/**
	 * 
	 * @This method enter the amazon connect proctor password
	 * 
	 * @param password
	 * 
	 * @throws Exception
	 */
	public void enterAmazonPassword(String password) throws Exception {
		CommonFunctions.SendkeysWithoutInputLog(amazonPassword, password, "Amazon proctor password");
	}

	/**
	 * 
	 * @This method clicks the amazon connect proctor login button
	 * 
	 * @throws Exception
	 */
	public void clickAWSProctorLogin() throws Exception {
		CommonFunctions.clickWebelement(amazonLoginBtn, "AWS proctor login");
	}

	/**
	 * @This method verify Amazon Connect Landing Page
	 * 
	 * @throws Exception
	 *
	 */

	public void verifyAmazonConnectLandingPage() throws Exception {
		CommonFunctions.checkCurrentPageTitle("Amazon Connect - Dashboard");
		CommonFunctions.checkCurrentPage(AWSProctorMain, "Amazon Connect");
		CommonFunctions.logMessage("eMed Amazon Connect Proctor Landing Page is displayed");
	}

}
