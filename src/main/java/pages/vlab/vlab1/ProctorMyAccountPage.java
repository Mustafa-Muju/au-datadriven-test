package main.java.pages.vlab.vlab1;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

public class ProctorMyAccountPage extends TestBase {

	private JavascriptExecutor jse = null;

	@FindBy(xpath = "//h1[text()='My Account']")
	private WebElement myAccountHeader;

	@FindBy(xpath = "//img[@alt='eMed logo']//ancestor::div//following-sibling::div//img//following-sibling::span//span[not(@class)]")
	private WebElement userAccountName;

	@FindBy(xpath = "//*[text()='Details']//following::p[contains(@class,'emed-text--bold')]")
	private List<WebElement> myAccountDetails;

	public ProctorMyAccountPage() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

	/**
	 * Proctor Account verification method
	 * 
	 * @throws Exception
	 *
	 */

	public void verifyMyAccountPageDisplayed() throws Exception {
		CommonFunctions.elementIsVisible(myAccountHeader);
		try {
			if (myAccountHeader.isDisplayed()) {
				CommonFunctions.logMessage("My Account page is displayed");
			}
		} catch (NoSuchElementException e) {
			CommonFunctions.logErrorMessageStopExecution("Error while displaying my account page");
		}
	}

	/**
	 * Method to get proctor name
	 * 
	 * @throws Exception
	 *
	 */

	public String getProctorName() throws Exception {
		return CommonFunctions.getTextOfElement(userAccountName, "proctor user name");
	}

	/**
	 * Method to verify the proctor details
	 * 
	 * @throws Exception
	 *
	 */

	public void verifyProctorDetails(String proctorEmail) throws Exception {

		String[] name = getProctorName().split(" ");
		String firstName = CommonFunctions.getTextOfElement(myAccountDetails.get(0), "first name");
		String lastName = CommonFunctions.getTextOfElement(myAccountDetails.get(1), "last name");
		String emailAddress = CommonFunctions.getTextOfElement(myAccountDetails.get(2), "email address");

		if (name[0].equalsIgnoreCase(firstName)) {
			CommonFunctions.logMessage("Expected first name is matched the actual first name..");
		} else {
			CommonFunctions.logErrorMessage("Expected first name is not matched the actual first name..");
		}

		if (name[1].equalsIgnoreCase(lastName)) {
			CommonFunctions.logMessage("Expected last name is matched the actual last name..");
		} else {
			CommonFunctions.logErrorMessage("Expected last name is not matched the actual last name..");
		}

		if (proctorEmail.equalsIgnoreCase(emailAddress)) {
			CommonFunctions.logMessage("Expected email address is matched the actual email address..");
		} else {
			CommonFunctions.logErrorMessage("Expected email address is not matched the actual email address..");
		}
	}

}
