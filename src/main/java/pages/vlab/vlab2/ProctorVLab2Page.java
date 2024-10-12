package main.java.pages.vlab.vlab2;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

public class ProctorVLab2Page extends TestBase {

	private JavascriptExecutor jse = null;

	@FindBy(id = "agent-status-dropdown")
	private WebElement proctorStatusDropdownBtn;

	@FindBy(xpath = "//div[contains(@style,'visible')]//iframe")
	private WebElement proctorFrame;

	@FindBy(xpath = "//p[contains(@class,'Welcome')]")
	private WebElement proctorLoggedIn;

	@FindBy(xpath = "//div[contains(@class,'Availablity')]")
	private WebElement openChats;

	@FindBy(xpath = "//div[text()='Offline']")
	private WebElement proctorOffline;

	@FindBy(xpath = "//*[text()='Available']")
	private WebElement proctorAvailable;

	@FindBy(xpath = "//button[@data-testid='ccp-next-contact-button']")
	private WebElement closeContact;

	public ProctorVLab2Page() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

	/**
	 * @This method verify VLab2 Proctor Center page
	 * 
	 * @throws Exception
	 *
	 */

	public void verifyVLab2ProctorPage() throws Exception {
		CommonFunctions.checkCurrentPageTitle("Proctor Center");
		CommonFunctions.logMessage("VLab2 Proctor Center Page is displayed");
	}

	/**
	 * 
	 * @This Method verify the proctor is logged in successfully
	 * 
	 * @throws Exception
	 * 
	 */
	public void verifyProctorLoggedIn() throws Exception {

		CommonFunctions.elementVisibleToCheck(proctorFrame, 10);
		getDriver().switchTo().frame(getDriver().findElement(By.xpath("//div[contains(@style,'visible')]//iframe")));

		boolean flag = CommonFunctions.elementVisibleToCheck(closeContact, 10);
		if (flag) {
			clickCloseContact();
		}

		CommonFunctions.elementVisibleToCheck(proctorLoggedIn, 10);
		try {
			if (proctorLoggedIn.isDisplayed()) {
				CommonFunctions.logMessage("Proctor has logged in successfully");
			}
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Error proctor has issue while login");
		}
	}

	/**
	 * 
	 * @This Method is used to select the proctor status type
	 * 
	 * @param statusType --> type of proctor status (e.g offline - Proctor is
	 *                   offline and available - Proctor is Available)
	 * 
	 * @throws Exception
	 * 
	 */
	public void selectProctorStatus(String statusType) throws Exception {

		CommonFunctions.clickWebelement(proctorStatusDropdownBtn, "proctor status dropdown");
		switch (statusType.toLowerCase()) {

		case "offline":
			CommonFunctions.clickWebelement(proctorOffline, "proctor offline");
			break;

		case "available":
			CommonFunctions.clickWebelement(proctorAvailable, "proctor available");
			break;

		default:
			CommonFunctions.logErrorMessageStopExecution(
					"Error unsupported type of status is given.\nPlease check the data sheet..");
			break;
		}
	}

	/**
	 * 
	 * @Method to click the close contact button
	 * 
	 * @throws Exception
	 */
	public void clickCloseContact() throws Exception {
		CommonFunctions.clickWebelement(closeContact, "Close contact");
	}

}
