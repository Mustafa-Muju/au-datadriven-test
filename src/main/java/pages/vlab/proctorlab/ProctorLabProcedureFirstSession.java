package main.java.pages.vlab.proctorlab;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

public class ProctorLabProcedureFirstSession extends TestBase {

	private JavascriptExecutor jse = null;

	@FindBy(xpath = "//*[contains(text(),'Hello and thank you')]")
	private WebElement eleStartTxt;

	@FindBy(xpath = "//*[contains(text(),'Continue asking until')]")
	private WebElement eleContinueAskTxt;

	@FindBy(xpath = "//*[contains(text(),'I can clearly see')]")
	private WebElement eleICanSeeAndHear;

	@FindBy(xpath = "//*[contains(text(),'Test Taker Name')]//parent::td//following-sibling::td")
	private WebElement eleTestTakerName;

	@FindBy(xpath = "//*[contains(text(),'Test Taker DOB')]//parent::td//following-sibling::td")
	private WebElement eleTestTakerDOB;

	@FindBy(xpath = "//*[contains(text(),'Session')]//parent::td//following-sibling::td")
	private WebElement eleSessionId;

	@FindBy(xpath = "//*[contains(text(),'SAY')]//parent::div//span")
	private List<WebElement> eleAgentSay;

	@FindBy(xpath = "//*[contains(text(),'Next')]")
	private WebElement eleNext;

	@FindBy(xpath = "//*[contains(text(),'Yes, continue')]")
	private WebElement eleAconContinue;

	@FindBy(xpath = "//*[contains(text(),'Not Expired')]")
	private WebElement eleNotExpired;

	@FindBy(xpath = "//*[text()='Expired']")
	private WebElement eleExpired;

	@FindBy(xpath = "//*[contains(text(),'Has another test kit')]")
	private WebElement eleHasAnotherKit;

	@FindBy(xpath = "//*[contains(text(),'Does not have another test kit')]")
	private WebElement eleNoAnotherKit;

	@FindBy(xpath = "//*[contains(text(),'Yes,')]")
	private WebElement eleYesIDReady;

	@FindBy(xpath = "//*[contains(text(),'No ID')]")
	private WebElement eleNoId;

	@FindBy(xpath = "//*[contains(text(),'ID Matches') or text()='ID matches']")
	private WebElement eleIdMatches;

	@FindBy(xpath = "//*[contains(text(),'ID Does Not Match') or text()='ID does not match']")
	private WebElement eleIdNotMatches;

	@FindBy(xpath = "//*[contains(text(),'First Name')]//code")
	private WebElement eleFirstName;

	@FindBy(xpath = "//*[contains(text(),'Last Name')]//code")
	private WebElement eleLastName;

	@FindBy(xpath = "//*[contains(text(),'Date of Birth')]//code")
	private WebElement eleDob;

	@FindBy(xpath = "//*[contains(text(),'Start the timer')]")
	private WebElement eleStartTimer;

	@FindBy(xpath = "//*[contains(text(),'Back')]")
	private WebElement eleBack;

	@FindBy(xpath = "//*[contains(text(),'Timer Complete - Next')]")
	private WebElement eleTimerCompleteNxt;

	@FindBy(xpath = "//*[text()='End chat']")
	private WebElement eleEndChat;

	@FindBy(xpath = "//*[contains(text(),'Reset Video Connection')]")
	private WebElement eleResetConnection;

	@FindBy(xpath = "//*[text()='You may now close this session.']")
	private WebElement eleFirstSessionEnded;

	public ProctorLabProcedureFirstSession() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

	/**
	 * 
	 * @This method is verify the start lab test text is displayed or not
	 * 
	 * @throws Exception
	 */
	public void verifyTheLabSession() throws Exception {
		getDriver().switchTo().defaultContent();
		boolean flag = CommonFunctions.elementVisibleToCheck(eleStartTxt, 20);
		if (flag) {
			CommonFunctions.logMessage("Hello and thank you for choosing eMed. is displayed successfully");

		} else {
			CommonFunctions.logErrorMessageStopExecution("Failed, Start lab test text is not displayed!");
		}
	}

	/**
	 * 
	 * @This method is verify the continue asking until the user hear is displayed
	 *       or not
	 * 
	 * @throws Exception
	 */
	public void verifyTheContinueAsk() throws Exception {
		getDriver().switchTo().defaultContent();
		boolean flag = CommonFunctions.elementVisibleToCheck(eleContinueAskTxt, 20);
		if (flag) {
			CommonFunctions.logMessage("Continue asking until the user can hear is displayed successfully");

		} else {
			CommonFunctions
					.logErrorMessageStopExecution("Failed, Continue asking until user can hear text is not displayed!");
		}
	}

	/**
	 * 
	 * @This method is used to verify the test taker information from banner
	 * 
	 * @param testTaker
	 * @param dob
	 * 
	 * @throws Exception
	 * 
	 */
	public void verifyUserDetails(String testTaker, String dob) throws Exception {
		String testTakerName = CommonFunctions.getTextOfElement(eleTestTakerName, "test taker name");

		if (testTakerName.equalsIgnoreCase(testTaker)) {
			CommonFunctions.logMessage(
					"Expected test taker name " + testTaker + " matched the actual test taker name " + testTakerName);
		} else {
			CommonFunctions.logErrorMessage("Expected test taker name " + testTaker
					+ " doesn't matched the actual test taker name " + testTakerName);
		}

		String customerDOB[] = CommonFunctions.getTextOfElement(eleTestTakerDOB, "test taker dob").replaceAll("\\(|\\)", "").split(" ");
		
		String dateOfBirth = customerDOB[0].split("-")[1]+"/"+customerDOB[0].split("-")[2]+"/"+customerDOB[0].split("-")[0];
		
		String age = Integer.toString(CommonFunctions.calculateAgeFromDob(customerDOB[0]));

		if (dateOfBirth.equalsIgnoreCase(dob)) {
			CommonFunctions.logMessage("Expected test taker date of birth " + dob
					+ " matched the actual test taker date of birth " + dateOfBirth);
		} else {
			CommonFunctions.logErrorMessage("Expected test taker date of birth " + dob
					+ " doesn't matched the actual test taker date of birth " + dateOfBirth);
		}
		
		if (customerDOB[1].equalsIgnoreCase(age)) {
			CommonFunctions.logMessage("Expected test taker age " + age
					+ " matched the actual test taker age " + customerDOB[1]);
		} else {
			CommonFunctions.logErrorMessage("Expected test taker age " + age
					+ " doesn't matched the actual test taker age " + customerDOB[1]);
		}

		String sessionId = CommonFunctions.getTextOfElement(eleSessionId, "test taker session id");

		if (sessionId.equalsIgnoreCase(getLabSessnId())) {
			CommonFunctions.logMessage("Expected test taker session Id " + getLabSessnId()
					+ " matched the actual test taker session Id " + sessionId);
		} else {
			CommonFunctions.logErrorMessage("Expected test taker session Id " + getLabSessnId()
					+ " doesn't matched the actual test taker session Id " + sessionId);
		}
	}

	/**
	 * 
	 * @This method is used to click back button
	 * 
	 * @throws Exception
	 */
	public void clickBack() throws Exception {
		CommonFunctions.clickWebelement(eleBack, "back button");
	}

	/**
	 * 
	 * @This method is used to click end chat of the lab test
	 * 
	 * @throws Exception
	 */
	public void clickEndChat() throws Exception {
		CommonFunctions.clickWebelement(eleEndChat, "end chat");
	}

	/**
	 * 
	 * @This method is used to click reset connection for the audio and video of lab
	 *       test
	 * 
	 * @throws Exception
	 */
	public void clickResetConnection() throws Exception {
		CommonFunctions.clickWebelement(eleResetConnection, "reset connection");
	}

	/**
	 * 
	 * @This method is used to print the word of the agent for the lab test
	 * 
	 * @throws Exception
	 */
	public void printAgentWord() throws Exception {
		Thread.sleep(3000);
		try {
			for (int word = 0; word < eleAgentSay.size(); word++) {
				String agentWord = CommonFunctions.getTextOfElement(eleAgentSay.get(word), "Agent word");
				CommonFunctions.logMessage("Agent says ==> " + agentWord);
			}
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Error, Failed to get the word of the agent..");
		}
	}

	/**
	 * 
	 * @This method is used to click the button - "I can clearly see and hear"
	 * 
	 * @throws Exception
	 */
	public void clickIHearAndSee() throws Exception {
		CommonFunctions.clickWebelement(eleICanSeeAndHear, "I hear and see");
	}

	/**
	 * 
	 * @This method is used to click the button - "Next"
	 * 
	 * @throws Exception
	 */
	public void clickNextStep() throws Exception {
		CommonFunctions.clickWebelement(eleNext, "Next button");
	}

	/**
	 * 
	 * @This method is used to click the button - "Not Expired"
	 * 
	 * @throws Exception
	 */
	public void clickNotExpired() throws Exception {
		CommonFunctions.clickWebelement(eleNotExpired, "not expired");
	}

	/**
	 * 
	 * @This method is used to click the button - "Expired"
	 * 
	 * @throws Exception
	 */
	public void clickExpired() throws Exception {
		CommonFunctions.clickWebelement(eleExpired, "expired");
	}

	/**
	 * 
	 * @This method is used to click the button - "Has another test kit"
	 * 
	 * @throws Exception
	 */
	public void clickAnotherKit() throws Exception {
		CommonFunctions.clickWebelement(eleHasAnotherKit, "Has another kit");
	}

	/**
	 * 
	 * @This method is used to click the button - "Does not have another test kit"
	 * 
	 * @throws Exception
	 */
	public void clickNoAnotherKit() throws Exception {
		CommonFunctions.clickWebelement(eleNoAnotherKit, "No another kit");
	}

	/**
	 * 
	 * @This method is used to click the button - "Yes, ID Ready button"
	 * 
	 * @throws Exception
	 */
	public void clickIdReady() throws Exception {
		CommonFunctions.clickWebelement(eleYesIDReady, "yes id ready");
	}

	/**
	 * 
	 * @This method is used to click the button - "No ID"
	 * 
	 * @throws Exception
	 */
	public void clickNoId() throws Exception {
		CommonFunctions.clickWebelement(eleNoId, "No Id");
	}

	/**
	 * 
	 * @This method is used to click the button - "ID Matches"
	 * 
	 * @throws Exception
	 */
	public void clickIdMatches() throws Exception {
		CommonFunctions.clickWebelement(eleIdMatches, "ID Matches");
	}

	/**
	 * 
	 * @This method is used to click the button - "ID Does Not Match"
	 * 
	 * @throws Exception
	 */
	public void clickIdNotMatches() throws Exception {
		CommonFunctions.clickWebelement(eleIdNotMatches, "ID Not Matches");
	}

	/**
	 * 
	 * @This method is to click Yes,Continue button under ID-page of ACON procedure
	 * 
	 * @throws Exception
	 * 
	 */
	public void clickYesContinue() throws Exception {
		CommonFunctions.clickWebelement(eleAconContinue, "Yes, Continue");
	}

	/**
	 * 
	 * @This method is used to verify the test taker information for Id
	 * 
	 * @param fName
	 * @param lName
	 * @param dob
	 * 
	 * @throws Exception
	 */
	public void verifyUserForId(String fName, String lName, String dob) throws Exception {
		String firstName = CommonFunctions.getTextOfElement(eleFirstName, "first name");
		String lastName = CommonFunctions.getTextOfElement(eleLastName, "last name");
		String[] customerDOB = CommonFunctions.getTextOfElement(eleDob, "dob").split("-");

		if (firstName.equalsIgnoreCase(fName)) {
			CommonFunctions.logMessage("Expected test taker first name " + fName
					+ " matched the actual test taker first name " + firstName + " for Id verification.");
		} else {
			CommonFunctions.logErrorMessage("Expected test taker first name " + fName
					+ " doesn't matched the actual test taker first name " + firstName + " for Id verification.");
		}

		if (lastName.equalsIgnoreCase(lName)) {
			CommonFunctions.logMessage("Expected test taker last name " + lName
					+ " matched the actual test taker last name " + lastName + " for Id verification.");
		} else {
			CommonFunctions.logErrorMessage("Expected test taker last name " + lName
					+ " doesn't matched the actual test taker last name " + lastName + " for Id verification.");
		}
		
		String dateOfBirth = customerDOB[1]+"/"+customerDOB[2]+"/"+customerDOB[0];

		if (dateOfBirth.equalsIgnoreCase(dob)) {
			CommonFunctions.logMessage("Expected test taker date of birth " + dob
					+ " matched the actual test taker date of birth " + dateOfBirth + " for Id verification.");
		} else {
			CommonFunctions.logErrorMessage("Expected test taker date of birth " + dob
					+ " doesn't matched the actual test taker date of birth " + dateOfBirth + " for Id verification.");
		}
	}

	/**
	 * 
	 * @This method is used to click the button - "Start the timer"
	 * 
	 * @throws Exception
	 */
	public void clickStartTimer() throws Exception {
		CommonFunctions.clickWebelement(eleStartTimer, "Start timer");
	}

	/**
	 * 
	 * @This method is used to click the button - "Timer Complete - Next"
	 * 
	 * @throws Exception
	 */
	public void clickQuidelTimerCompleteNext() throws Exception {
		CommonFunctions.clickWebelement(eleTimerCompleteNxt, "timer complete next");
	}

	/**
	 * 
	 * @This method is to verify the first session has ended
	 * 
	 * @throws Exception
	 */
	public void verifyFirstSessionEnded() throws Exception {
		boolean flag = CommonFunctions.elementVisibleToCheck(eleFirstSessionEnded, 10);
		if (flag) {
			CommonFunctions.logMessage("First session has ended successfully.");
		} else {
			CommonFunctions.logErrorMessageStopExecution("Error, First session has not ended successfully.");
		}
	}

}
