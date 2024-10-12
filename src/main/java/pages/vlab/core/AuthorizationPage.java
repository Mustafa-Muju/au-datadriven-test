package main.java.pages.vlab.core;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

public class AuthorizationPage extends TestBase {

	private JavascriptExecutor jse = null;

	@FindBy(xpath = "//*[contains(text(),'Authorization to Disclose')]")
	private WebElement eleAuthorize;

	@FindBy(xpath = "//footer//a[contains(text(),'Allow')]")
	private static WebElement eleAllow;

	@FindBy(xpath = "//a[text()='Cancel']")
	private static WebElement eleCancel;

	public AuthorizationPage() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

	/**
	 * @This method calls various methods for functional execution
	 * 
	 * @throws Exception
	 *
	 */

	public static void startTestConsentFlow() throws Exception {
		verifyAuthorizationPage("Consent - eMed");
		clickAllow();
	}

	/**
	 * @This method verify results authenticate page
	 * 
	 * @throws Exception
	 *
	 */

	public static void verifyAuthorizationPage(String title) throws Exception {
		CommonFunctions.checkCurrentPageTitle(title);
		CommonFunctions.logMessage(title + " Page is displayed");
	}

	/**
	 * 
	 * @This method clicks the allow button
	 * 
	 * @throws Exception
	 */
	public static void clickAllow() throws Exception {
		try {
			WebElement allow = getDriver().findElement(By.xpath("//footer//a[contains(text(),'Allow')]"));
			CommonFunctions.elementToBeClickable(allow, "allow button");
			allow.click();
			CommonFunctions.logMessage("Allow button is clicked");
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Exception on clicking allow button!");
		}
	}

	/**
	 * 
	 * @This method clicks the Cancel button
	 * 
	 * @throws Exception
	 */
	public void clickCancel() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		CommonFunctions.clickWebelement(eleCancel, "Cancel button");
	}

	/**
	 * @This method verify results authenticate page
	 * 
	 * @throws Exception
	 *
	 */

	public static void verifyConfirmAccountPage() throws Exception {
		CommonFunctions.checkCurrentPageTitle("Sign in with eMed - eMed");
		CommonFunctions.logMessage("Sign in with eMed Page is displayed");
	}

}
