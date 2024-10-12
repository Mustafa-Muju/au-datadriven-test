package main.java.pages.vlab.proctorlab;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

public class ProctorLabProcedureSecondSession extends TestBase {

	private JavascriptExecutor jse = null;

	@FindBy(xpath = "//*[contains(text(),'The timer is finished')]")
	private WebElement eleTimerFinishTxt;

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

	@FindBy(xpath = "//*[contains(text(),'They see two (2) pink lines')]")
	private WebElement eleTwoPinkLines;

	@FindBy(xpath = "//*[contains(text(),'They do not see two (2) pink lines')]")
	private WebElement eleNoTwoPinkLines;

	@FindBy(xpath = "//*[contains(text(),'They see a single pink line')]")
	private WebElement eleSinglePinkLine;

	@FindBy(xpath = "//*[contains(text(),\"They don't see any pink lines\")]")
	private WebElement eleNoPinkLine;

	@FindBy(xpath = "//*[contains(text(),'They see a blue line')]")
	private WebElement eleBlueLine;

	@FindBy(xpath = "//*[contains(text(),\"I'm ready\")]")
	private WebElement eleImReady;

	@FindBy(xpath = "//*[contains(text(),'Back')]")
	private WebElement eleBack;

	@FindBy(xpath = "//*[contains(text(),'SAY')]//parent::div//span")
	private List<WebElement> eleAgentSay;

	@FindBy(xpath = "//*[contains(text(),'Next')]")
	private WebElement eleNext;

	@FindBy(xpath = "//*[contains(text(),'Yes - blue and pink lines')]")
	private WebElement eleYesBlueAndPinkLine;

	@FindBy(xpath = "//*[contains(text(),'Submit **Positive COVID** Result')]")
	private WebElement eleSubmitPositive;

	@FindBy(xpath = "//*[contains(text(),'Yes - single blue line')]")
	private WebElement eleYesBlueLine;

	@FindBy(xpath = "//*[contains(text(),'Submit **Negative COVID** Result')]")
	private WebElement eleSubmitNegative;

	@FindBy(xpath = "//*[contains(text(),'Yes - Single Pink Line')]")
	private WebElement eleYesPinkLine;

	@FindBy(xpath = "//*[contains(text(),'Submit **INVALID** Test Result')]")
	private WebElement eleSubmitInvalid;

	@FindBy(xpath = "//*[contains(text(),'No - Next')]")
	private WebElement eleNoNxt;

	@FindBy(xpath = "//input[@value='0']//parent::label")
	private WebElement eleNegativeResult;

	@FindBy(xpath = "//input[@value='1']//parent::label")
	private WebElement elePositiveResult;

	@FindBy(xpath = "//input[@value='-1']//parent::label")
	private WebElement eleInvalidResult;

	@FindBy(xpath = "//input[@value='']//parent::label")
	private WebElement eleOtherResult;

	@FindBy(xpath = "//*[@name='agree' and @value='true']//parent::label")
	private WebElement eleYesAgree;

	@FindBy(xpath = "//*[@name='agree' and @value='true']//parent::label")
	private WebElement eleNoAgree;

	@FindBy(xpath = "//*[contains(text(),'Submit Results')]")
	private WebElement eleSubmitResults;

	@FindBy(xpath = "//*[contains(text(),'Finish')]")
	private WebElement eleFinish;

	@FindBy(xpath = "//*[text()='You may now end this session.']")
	private WebElement eleSecondSessionEnded;

	public ProctorLabProcedureSecondSession() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
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
	 * @This method is used to print the word of the agent for the lab test
	 * 
	 * @throws Exception
	 */
	public void printAgentWord() throws Exception {
		Thread.sleep(3500);
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
	 * @This method is used to click the button - "Next"
	 * 
	 * @throws Exception
	 */
	public void clickNextStep() throws Exception {
		CommonFunctions.clickWebelement(eleNext, "Next button");
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
	 * @This method is used to click the button - "They see two (2) pink lines"
	 * 
	 * @throws Exception
	 */
	public void clickTwoPinkLines() throws Exception {
		CommonFunctions.clickWebelement(eleTwoPinkLines, "two pink lines");
	}

	/**
	 * 
	 * @This method is used to click the button - "They do not see two (2) pink
	 *       lines"
	 * 
	 * @throws Exception
	 */
	public void clickNoTwoPinkLines() throws Exception {
		CommonFunctions.clickWebelement(eleNoTwoPinkLines, "no two pink lines");
	}

	/**
	 * 
	 * @This method is used to click the button - "They see a single pink line"
	 * 
	 * @throws Exception
	 */
	public void clickSinglePinkLine() throws Exception {
		CommonFunctions.clickWebelement(eleSinglePinkLine, "single pink line");
	}

	/**
	 * 
	 * @This method is used to click the button - "They don't see any pink lines"
	 * 
	 * @throws Exception
	 */
	public void clickNoPinkLine() throws Exception {
		CommonFunctions.clickWebelement(eleNoPinkLine, "no pink line");
	}

	/**
	 * 
	 * @This method is used to click the button - "They see a blue line"
	 * 
	 * @throws Exception
	 */
	public void clickBlueLine() throws Exception {
		CommonFunctions.clickWebelement(eleBlueLine, "blue line");
	}

	/**
	 * 
	 * @This method is used to click the button - "Yes - blue and pink lines"
	 * 
	 * @throws Exception
	 */
	public void clickYesBlueAndPinkLine() throws Exception {
		CommonFunctions.clickWebelement(eleYesBlueAndPinkLine, "yes both blue and pink line");
	}

	/**
	 * 
	 * @This method is used to click the button - "Submit **Positive COVID** Result"
	 * 
	 * @throws Exception
	 */
	public void clickSubmitPositive() throws Exception {
		CommonFunctions.clickWebelement(eleSubmitPositive, "submit positive");
	}

	/**
	 * 
	 * @This method is used to click the button - "Yes - single blue line"
	 * 
	 * @throws Exception
	 */
	public void clickYesBlueLine() throws Exception {
		CommonFunctions.clickWebelement(eleYesBlueLine, "yes single blue line");
	}

	/**
	 * 
	 * @This method is used to click the button - "Submit **Negative COVID** Result"
	 * 
	 * @throws Exception
	 */
	public void clickSubmitNegative() throws Exception {
		CommonFunctions.clickWebelement(eleSubmitNegative, "submit negative");
	}

	/**
	 * 
	 * @This method is used to click the button - "Yes - Single Pink Line"
	 * 
	 * @throws Exception
	 */
	public void clickYesPinkLine() throws Exception {
		CommonFunctions.clickWebelement(eleYesPinkLine, "yes single pink line");
	}

	/**
	 * 
	 * @This method is used to click the button - "Submit **INVALID** Test Result"
	 * 
	 * @throws Exception
	 */
	public void clickSubmitInvalid() throws Exception {
		CommonFunctions.clickWebelement(eleSubmitInvalid, "submit invalid");
	}

	/**
	 * 
	 * @This method is used to click the button - "No - Next"
	 * 
	 * @throws Exception
	 */
	public void clickNoNext() throws Exception {
		CommonFunctions.clickWebelement(eleNoNxt, "no next");
	}

	/**
	 * 
	 * @This method to select the test lab result
	 * 
	 * @param result
	 * 
	 * @throws Exception
	 */
	public void submitTestResult(String result) throws Exception {

		CommonFunctions.logMessage("What are the test-taker's interpretation of the test results?");

		switch (result.toLowerCase()) {
		case "negative":
			CommonFunctions.clickWebelement(eleNegativeResult, "submit negative result");
			break;

		case "positive":
			CommonFunctions.clickWebelement(elePositiveResult, "submit positive result");
			break;

		case "invalid":
			CommonFunctions.clickWebelement(eleInvalidResult, "submit invalid result");
			break;

		case "other":
			CommonFunctions.clickWebelement(eleOtherResult, "submit other result");
			break;

		default:
			CommonFunctions.logErrorMessageStopExecution("Error, Invalid result type has selected..");
			break;

		}
	}

	/**
	 * 
	 * @This method is used to confirm the result submitted
	 * 
	 * @param confirm
	 * @throws Exception
	 */
	public void confirmTheResults(String confirm) throws Exception {

		CommonFunctions.logMessage("Do you agree with the test-taker's interpretation of these results?");

		switch (confirm.toLowerCase()) {
		case "yes":
			CommonFunctions.clickWebelement(eleYesAgree, "yes");
			break;

		case "no":
			CommonFunctions.clickWebelement(eleNoAgree, "no");
			break;

		default:
			CommonFunctions.logErrorMessageStopExecution("Error, Invalid confirmation type is selected..");
			break;

		}
	}

	/**
	 * 
	 * @This method is used to click the button - "Submit Results"
	 * 
	 * @throws Exception
	 */
	public void clickSubmitResults() throws Exception {
		CommonFunctions.clickWebelement(eleSubmitResults, "submit results");
	}

	/**
	 * 
	 * @This method is used to click the button - "Finish"
	 * 
	 * @throws Exception
	 */
	public void clickFinish() throws Exception {
		CommonFunctions.clickWebelement(eleFinish, "finish");
	}

	/**
	 * 
	 * @This method is to verify the second session has ended
	 * 
	 * @throws Exception
	 */
	public void verifySecondSessionEnded() throws Exception {
		boolean flag = CommonFunctions.elementVisibleToCheck(eleSecondSessionEnded, 10);
		if (flag) {
			CommonFunctions.logMessage("Second session has ended successfully.");
		} else {
			CommonFunctions.logErrorMessageStopExecution("Error, Second session has not ended successfully.");
		}
	}

}
