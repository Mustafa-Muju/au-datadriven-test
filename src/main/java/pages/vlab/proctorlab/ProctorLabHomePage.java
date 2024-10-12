package main.java.pages.vlab.proctorlab;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

public class ProctorLabHomePage extends TestBase {

	private JavascriptExecutor jse = null;

	@FindBy(id = "agent-status-dropdown")
	private WebElement eleProctorStatusDropdownBtn;

	@FindBy(xpath = "//div[contains(@style,'visible')]//iframe")
	private WebElement eleProctorFrame;

	@FindBy(xpath = "//p[contains(@class,'Welcome')]")
	private WebElement eleProctorLoggedIn;

	@FindBy(xpath = "//button[text()='Start']")
	private WebElement eleReadinessCheckStart;

	@FindBy(xpath = "//button[text()='Manual settings']")
	private WebElement eleReadinessCheckManualSettings;

	@FindBy(xpath = "//button[text()='Finish']")
	private WebElement eleReadinessCheckFinish;

	@FindBy(xpath = "//div[contains(@class,'Availablity')]")
	private WebElement openChats;

	@FindBy(xpath = "//div[text()='Offline']")
	private WebElement proctorOffline;

	@FindBy(xpath = "//*[text()='Available']")
	private WebElement proctorAvailable;

	@FindBy(xpath = "//button[@data-testid='ccp-next-contact-button']")
	private WebElement closeContact;

	@FindBy(xpath = "//*[contains(@class,'ParticipantName')]")
	private WebElement eleParticipantName;

	@FindBy(xpath = "//*[contains(text(),'Accept chat')]")
	private WebElement eleAcceptChat;

	@FindBy(xpath = "//*[contains(text(),'Reject chat')]")
	private WebElement eleRejectChat;

	public ProctorLabHomePage() {
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
	 * @This method is used to click start button for the readiness checker
	 * 
	 * @throws Exception
	 */
	public void clickStart() throws Exception {
		CommonFunctions.clickWebelement(eleReadinessCheckStart, "proctor readiness start");
	}

	/**
	 * 
	 * @This method is used to click manual settings button for the readiness
	 *       checker
	 * 
	 * @throws Exception
	 */
	public void clickManualSettings() throws Exception {
		CommonFunctions.clickWebelement(eleReadinessCheckManualSettings, "proctor readiness manual settings");
	}

	/**
	 * 
	 * @This method is used to click finish button for the readiness checker
	 * 
	 * @throws Exception
	 */
	public void clickFinish() throws Exception {
		CommonFunctions.clickWebelement(eleReadinessCheckFinish, "proctor readiness finish");
	}

	/**
	 * 
	 * @This Method verify the proctor is logged in successfully
	 * 
	 * @throws Exception
	 * 
	 */
	public void verifyProctorLoggedIn() throws Exception {

		CommonFunctions.elementVisibleToCheck(eleProctorFrame, 8);
		getDriver().switchTo().frame(getDriver().findElement(By.xpath("//div[contains(@style,'visible')]//iframe")));

		CommonFunctions.waitForPageLoad(getDriver());
		boolean flag = CommonFunctions.elementVisibleToCheck(eleParticipantName, 8);
		if (flag) {
			String participant = CommonFunctions.getTextOfElement(eleParticipantName, "participant name");

			if (participant.equalsIgnoreCase("Missed chat")) {
				clickCloseContact();
			} else if (participant.contains(CommonFunctions.getdata("FirstName"))) {
				CommonFunctions.clickWebelement(eleRejectChat, "reject chat");
				clickCloseContact();
			}
		}

		boolean proctorFlag = CommonFunctions.elementVisibleToCheck(eleProctorLoggedIn, 7);
		try {
			if (proctorFlag) {
				CommonFunctions.logMessage("Proctor has logged in successfully");
			}
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Error proctor has issue while login");
		}
		getDriver().switchTo().defaultContent();
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

		CommonFunctions.clickWebelement(eleProctorStatusDropdownBtn, "proctor status dropdown");
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

	/**
	 * 
	 * @This method to connect the proctor with user for lab session
	 * 
	 * @param customerName
	 * @throws Exception
	 */
	public void connectProctorWithUser(String customerName) throws Exception {
		boolean stopFlag = true;

		CommonFunctions.elementVisibleToCheck(eleProctorFrame, 10);
		getDriver().switchTo().frame(getDriver().findElement(By.xpath("//div[contains(@style,'visible')]//iframe")));

		for (int tries = 0; tries < 3; tries++) {
			String participant = CommonFunctions.getTextOfElement(eleParticipantName, "participant name");

			if (participant.equalsIgnoreCase("Missed chat")) {
				clickCloseContact();
				Thread.sleep(1000);
			}
			participant = CommonFunctions.getTextOfElement(eleParticipantName, "participant name");

			if (participant.equalsIgnoreCase(customerName)) {
				CommonFunctions.clickWebelement(eleAcceptChat, "accept chat");
				stopFlag = false;
				break;
			} else {
				CommonFunctions.clickWebelement(eleRejectChat, "reject chat");
				clickCloseContact();
				Thread.sleep(1000);
			}
		}
		getDriver().switchTo().defaultContent();

		if (stopFlag) {
			CommonFunctions.logErrorMessageStopExecution("Unable to find the right user after 3 tries.");
		}

	}
	
	/**
	 * 
	 * @This method is used to close the previous chat
	 * 
	 * @throws Exception
	 */
	public void closePreviousChat() throws Exception {
		CommonFunctions.elementVisibleToCheck(eleProctorFrame, 10);
		getDriver().switchTo().frame(getDriver().findElement(By.xpath("//div[contains(@style,'visible')]//iframe")));
		clickCloseContact();
		getDriver().switchTo().defaultContent();
	}

}
