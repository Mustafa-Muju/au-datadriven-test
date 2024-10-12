package main.java.pages.vlab.vlab1;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

public class ProctorLandingPage extends TestBase {

	private JavascriptExecutor jse = null;

	@FindBy(xpath = "//*[contains(@alt,'default avatar')]//following-sibling::span")
	private WebElement userAccountName;

	@FindBy(xpath = "//a[contains(text(),'Virtual Waiting Room')]")
	private WebElement virtualWaitRoom;

	@FindBy(xpath = "//a[contains(text(),'Training')]")
	private WebElement training;

	@FindBy(xpath = "//a[contains(text(),'My Account')]")
	private WebElement myAccount;

	@FindBy(xpath = "//*[contains(text(),'Logout')]//parent::button")
	private WebElement signOutButton;

	public ProctorLandingPage() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

	/**
	 * Proctor landing page verification in proctor
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void verifyProctorLandingPage() throws Exception {
		CommonFunctions.elementIsVisible(userAccountName);
		if (userAccountName.isDisplayed()) {
			CommonFunctions.logMessage("<-----eMed Proctor Landing Page----->");
			CommonFunctions.logMessage("Proctor has successfully logged In");
		}
	}

	/**
	 * Method to click virtual waiting room in proctor
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void clickVirtualWaitingRoom() throws Exception {
		CommonFunctions.clickWebelement(virtualWaitRoom, "virtual wait room button");
	}

	/**
	 * Method to click Training in proctor
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void clickTraining() throws Exception {
		CommonFunctions.clickWebelement(training, "training button");
	}

	/**
	 * Method to click MyAccount in proctor
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void clickMyAccount() throws Exception {
		CommonFunctions.clickWebelement(myAccount, "my account button");
	}

	/**
	 * Method to click Signout in proctor
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void clickProctorSignout() throws Exception {
		CommonFunctions.clickWebelement(signOutButton, "proctor signout button");
	}

}
