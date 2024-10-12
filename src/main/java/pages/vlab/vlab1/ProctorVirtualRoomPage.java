package main.java.pages.vlab.vlab1;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.flows.vlab.vlab1.ProctorVirtualLabFlow;
import main.java.flows.www.RegistrationFlow;
import main.java.flows.www.UserVirtualLabFlow;
import main.java.pages.www.LandingPage;
import main.java.utils.CommonFunctions;

public class ProctorVirtualRoomPage extends TestBase {
	private JavascriptExecutor jse = null;

	@FindBy(xpath = "//*[contains(@class,'emed-waiting_room')]//h2")
	private WebElement patientWaiting;

	@FindBy(xpath = "//button[text()='Start a Visit']")
	private WebElement startVisit;

	@FindBy(xpath = "//*[text()='Next']//ancestor::button")
	private WebElement next;

	@FindBy(xpath = "//*[text()='Back']//ancestor::button")
	private WebElement back;

	@FindBy(xpath = "//*[text()='End']//ancestor::button")
	private WebElement end;

	@FindBy(xpath = "//*[text()='Mute']//ancestor::button")
	private WebElement mute;

	@FindBy(xpath = "//*[text()='Escalate']//ancestor::button")
	private WebElement escalate;

	@FindBy(xpath = "//*[contains(text(),'Read the test and input')]")
	private WebElement resultUpdate;

	@FindBy(xpath = "//*[@value='Negative']//following-sibling::label")
	private WebElement negativeStatus;

	@FindBy(xpath = "//*[@value='Positive']//following-sibling::label")
	private WebElement positiveStatus;

	@FindBy(xpath = "//*[@value='Invalid']//following-sibling::label")
	private WebElement invalidStatus;

	@FindBy(xpath = "//*[@value='Other']//following-sibling::label")
	private WebElement noResultStatus;

	@FindBy(xpath = "//p[text()='Submit']//parent::button")
	private WebElement submit;

	@FindBy(xpath = "//*[contains(text(),'Thank you, your session is now over')]")
	private WebElement sessionEnded;

	public ProctorVirtualRoomPage() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

	/**
	 * Method to accept the proctor virtual session
	 * 
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void proctorVirtualAccept() throws Exception {
		boolean tryFlag = false;
		try {
			for (int successTry = 0; successTry < 3; successTry++) {
				CommonFunctions.waitForPageLoad(getDriver());
				CommonFunctions.elementIsVisible(patientWaiting);
				if (!patientWaiting.getText().contains("0")) {
					CommonFunctions.logMessage("Patient is waiting in virtual room");
					CommonFunctions.clickWebelement(startVisit, "start visit button");
					tryFlag = true;
					break;
				}
			}
			if (!tryFlag) {
				throw new Exception();
			}

		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution(
					"Error occured while attending patient\nNo patients available in waiting room");
		}
	}

	/**
	 * proctor virtual session started verification method
	 * 
	 * @param fName
	 * 
	 * @param lName
	 * 
	 * @throws Exception
	 *
	 */

	public void verifySessionStarted(String fName, String lName) throws Exception {
		try {
			WebElement userName = getDriver().findElement(By.xpath("//*[text()='" + fName + " " + lName + "']"));
			if (userName.isDisplayed()) {
				CommonFunctions.logMessage("Virtual lab session has started with official patient");
			}
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution(
					"Error while starting virtual lab session. May be due to invalid patient joined");
		}
	}

	/**
	 * Method to click Next button in proctor
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void clickNextStepButton() throws Exception {
		CommonFunctions.clickWebelement(next, "next step button");
	}

	/**
	 * Method to click the Test Status in proctor
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void selectTestStatus(String status) throws Exception {

		CommonFunctions.elementIsVisible(resultUpdate);
		switch (status) {
		case "1":
			CommonFunctions.clickWebelement(negativeStatus, "negative status");
			break;

		case "2":
			CommonFunctions.clickWebelement(positiveStatus, "positive status");
			break;

		case "3":
			CommonFunctions.clickWebelement(invalidStatus, "invalid status");
			break;

		case "4":
			CommonFunctions.clickWebelement(noResultStatus, "no result obtained status");
			break;

		default:
			CommonFunctions.logErrorMessageStopExecution("Invalid status is selected. Please check the data sheet!!!");
			;
			break;
		}
	}

	/**
	 * Method to click the Submit button in proctor
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void clickSubmitButton() throws Exception {
		CommonFunctions.clickWebelement(submit, "submit status button");
	}

	/**
	 * Method to click the End Call button in proctor
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void clickEndCallButton() throws Exception {
		CommonFunctions.clickWebelement(end, "end call button");
	}

	/**
	 * Method to click the Mute button in proctor
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void clickMuteButton() throws Exception {
		CommonFunctions.clickWebelement(mute, "call mute button");
	}

	/**
	 * Method to End the session in proctor
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void verifySessionEnded() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		CommonFunctions.elementIsVisible(sessionEnded);
		if (sessionEnded.isDisplayed()) {
			CommonFunctions.logMessage("Thank you, your session is now over.\nSession has successfully ended");
		}
	}

	/**
	 * Method is used to verify and create patient in queue for proctor lab session
	 * 
	 * @throws Exception
	 */
	public void verifyPatientInQueue() throws Exception {

		boolean breakFlag = false;
		for (int failTry = 0; failTry < 3; failTry++) {

			if (!breakFlag && failTry != 0) {
				CommonFunctions.logMessage("Patient is not available in queue. so, creating a patient for execution");
				new LandingPage().invokeWWWNewWindow();
				if (failTry == 1) {
					RegistrationFlow.navicaLogin(CommonFunctions.getdata("UserName"),
							CommonFunctions.getdata("Password"));
				} else {
					new LandingPage().verifyLandingPage();
					new LandingPage().clickStartTesting();
				}
				UserVirtualLabFlow.verifyUserAttendSessionAssessment();
			}

			for (int successTry = 0; successTry < 3; successTry++) {
				if (successTry == 0) {
					CommonFunctions.moveToSpecifiedWindow(getDriver(), 0);
				}
				CommonFunctions.elementIsVisible(patientWaiting);
				if (!patientWaiting.getText().contains("0")) {
					breakFlag = true;
					break;
				}
				Thread.sleep(2000);
			}
			if (breakFlag) {
				break;
			}
			if (failTry != 0 && !breakFlag) {
				CommonFunctions.switchNextWindow(getDriver());
				getDriver().close();
				CommonFunctions.moveToSpecifiedWindow(getDriver(), 0);
			}
		}
		if (!breakFlag) {
			CommonFunctions
					.logErrorMessageStopExecution("Tired for two times failed due to patient not available in queue");
		}
	}

	/**
	 * Method is used to verify and create patient in queue for proctor lab session
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	public boolean removePatientInQueueAndAcceptCertifiedPatient() throws Exception {

		boolean flag = true;

		while (flag) {

			CommonFunctions.elementIsVisible(patientWaiting);
			Thread.sleep(3000);
			if (patientWaiting.getText().contains("0")) {
				flag = false;
				break;

			} else {
				ProctorVirtualLabFlow.proctorVirtualAccept();
				if (CommonFunctions.getdata("Dependent").equalsIgnoreCase("Y")) {
					flag = ProctorVirtualLabFlow.verifyCertifiedPatient(getDependentPatientName().split(" ")[0],
							getDependentPatientName().split(" ")[1]);
				} else {
					flag = ProctorVirtualLabFlow.verifyCertifiedPatient(CommonFunctions.getdata("FirstName"),
							CommonFunctions.getdata("LastName"));
				}
			}

		}
		return flag;

	}
}
