package main.java.pages.www;

import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

public class VLabQuestionairePage extends TestBase {

	private JavascriptExecutor jse = null;

	@FindBy(xpath = "//*[@data-testid='qr-scanner-title']")
	private WebElement scanQRStickerHeader;

	@FindBy(xpath = "//video[@id='scanner']")
	private WebElement scanStickerVideo;

	@FindBy(xpath = "//*[text()='Enter product ID number']")
	private WebElement enterProductId;

	@FindBy(xpath = "//*[text()='Product ID Numbers']")
	private WebElement productIdModule;

	@FindBy(id = "firstDigit")
	private WebElement firstDigit;

	@FindBy(id = "secondDigit")
	private WebElement secondDigit;

	@FindBy(id = "thirdDigit")
	private WebElement thirdDigit;

	@FindBy(xpath = "//*[contains(@class,'ButtonNext')]//ancestor::button")
	private WebElement nextBtn;

	@FindBy(xpath = "//*[text()='Cancel']")
	private WebElement cancelBtn;

	@FindBy(xpath = "//h1[contains(text(),'This test will take')]")
	private WebElement twentyMinutesTestHeader;

	@FindBy(xpath = "//form//div[@data-testid]//p")
	private List<WebElement> preTestQuestions;

	@FindBy(tagName = "label")
	private List<WebElement> answers;

	@FindBy(id = "lastResult")
	private WebElement lastResultDropDown;

	@FindBy(xpath = "//*[@id='lastResult']//div[contains(@id,'react-select')]")
	private List<WebElement> lastResultList;

	@FindBy(xpath = "//*[contains(text(),'Select all of the following')]//following::label")
	private List<WebElement> applicableYouQues;

	@FindBy(id = "airline")
	private WebElement airlineDropDown;

	@FindBy(xpath = "//*[@id='airline']//div[contains(@id,'react-select')]")
	private List<WebElement> airlinesList;

	@FindBy(id = "countries")
	private WebElement countryDropDown;

	@FindBy(xpath = "//*[@id='countries']//div[contains(@id,'react-select')]")
	private List<WebElement> countriesList;

	@FindBy(id = "city")
	private WebElement testCity;

	@FindBy(xpath = "//label[@for='dateOfBirth']")
	private WebElement DOBQues;

	@FindBy(id = "dateOfBirth")
	private WebElement DOBText;

	@FindBy(xpath = "//label[@for='preflight-terms-conditions']")
	private WebElement webelePreTestTerms;

	@FindBy(xpath = "//label[@for='termsConditionAndPolicyAccepted']")
	private WebElement webeleTermsAcceptAndPrivacy;

	@FindBy(xpath = "//*[text()='Join guided session']//parent::button")
	private WebElement joinGuideSession;

	@FindBy(xpath = "//*[contains(@class,'dropdown__indicators')]")
	private WebElement dependentDropdown;

	@FindBy(xpath = "//*[contains(@id,'react-select-2-option')]")
	private List<WebElement> dependentList;

	@FindBy(xpath = "//*[contains(text(),\"Let's make sure\")]")
	private WebElement startVlabTest;

	@FindBy(xpath = "//*[contains(text(),'start the test')]//ancestor::div[contains(@class,'box navigator-option')]")
	private WebElement yesStartVlab;

	@FindBy(xpath = "//*[contains(text(),'connect you with a certified guide')]")
	private WebElement waitForProctor;

	public VLabQuestionairePage() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

	/**
	 *
	 * @Method to verify the Scan QR sticker header
	 * 
	 * @throws Exception
	 * @return
	 */
	public boolean verifyScanQRStickerHeader() throws Exception {

		boolean scanStickerHeader = CommonFunctions.elementVisibleToCheck(scanQRStickerHeader, 10);
		if (scanStickerHeader) {
			CommonFunctions.waitForPageLoad(getDriver());
			CommonFunctions.logMessage("Scan QR sticker header is displayed");
		}
		return scanStickerHeader;
	}

	/**
	 * @Method to verify the Scan QR sticker video frame
	 * 
	 * @throws Exception
	 */
	public void verifyScanQRStickerVideoFrame() throws Exception {

		boolean qrScanVideoFrame = CommonFunctions.elementVisibleToCheck(scanStickerVideo, 10);
		if (qrScanVideoFrame) {
			CommonFunctions.logMessage("Scan QR video frame is displayed");
		} else {
			CommonFunctions.logErrorMessageStopExecution("Error scan qr video frame is not displayed");
		}
	}

	/**
	 * This method is used to click the enter product id number
	 * 
	 * @throws Exception
	 */
	public void clickEnterProductId() throws Exception {
		CommonFunctions.clickWebelement(enterProductId, "Enter product id number");
	}

	/**
	 * @Method to verify the enter product id number module
	 * 
	 * @throws Exception
	 */
	public void verifyProductIdModule() throws Exception {

		boolean productIDModule = CommonFunctions.elementVisibleToCheck(productIdModule, 10);
		if (productIDModule) {
			CommonFunctions.logMessage("Product Id module is displayed");
		} else {
			CommonFunctions.logErrorMessageStopExecution("Error product Id module is not displayed");
		}
	}

	/**
	 * This method is used to enter the first digits product id number
	 * 
	 * @throws Exception
	 */
	public void enterFirstDigitProductId(String productNumberOne) throws Exception {
		CommonFunctions.selNumKeyPress(firstDigit, productNumberOne, "first digit product number");
	}

	/**
	 * This method is used to enter the Second digits product id number
	 * 
	 * @throws Exception
	 */
	public void enterSecondDigitProductId(String productNumberTwo) throws Exception {
		CommonFunctions.selNumKeyPress(secondDigit, productNumberTwo, "second digit product number");
	}

	/**
	 * This method is used to enter the third digits product id number
	 * 
	 * @throws Exception
	 */
	public void enterthirdDigitProductId(String productNumberThree) throws Exception {
		CommonFunctions.selNumKeyPress(thirdDigit, productNumberThree, "third digit product number");
	}

	/**
	 * This method is used to click next button for product id digit number
	 * 
	 * @throws Exception
	 */
	public void clickNextProductDigit() throws Exception {
		CommonFunctions.clickWebelement(nextBtn, "next product digit button");
	}

	/**
	 * @Method to verify the VLab test questionnaire header
	 * 
	 * @throws Exception
	 */
	public void verifyTestKitQuestionaireHeader() throws Exception {

		boolean testQuesHeader = CommonFunctions.elementVisibleToCheck(twentyMinutesTestHeader, 15);
		if (testQuesHeader) {
			CommonFunctions.waitForPageLoad(getDriver());
			CommonFunctions.logMessage("Purchase test kit questionaire is displayed");
		} else {
			CommonFunctions.logErrorMessageStopExecution("Error purchase test kit questionaire is not displayed");
		}
	}

	/**
	 * Method to check Navica Permission
	 * 
	 *
	 * @throws Exception
	 * 
	 */

	public void preTestDOB(String DOB) throws Exception {
		CommonFunctions.logMessage(CommonFunctions.getTextOfElement(DOBQues, "DOB pretest question"));
		DOBText.sendKeys(DOB, Keys.ENTER);
		CommonFunctions.logMessage("DOB entered as " + DOB);
	}

	/**
	 * 
	 * @Method for attending virtual lab test question
	 * 
	 * @param vlabQuestioniare --> Y|Y|Y|Y||N|Y|Y - applicable inputs are provided
	 *                         first like Y or N with single pipeline based on
	 *                         questions and other inputs are provided after the
	 *                         double pipeline like Y or N with single pipeline
	 *                         based on questions count and previous result status
	 *                         by same separating with double pipeline
	 *                         (e.g)applicaleInput||otherInputs||previousResult
	 * 
	 * @throws Exception
	 */
	public void virtualLabTestQuestion(String vlabQuestioniare, String airInfo) throws Exception {

		String[] splitInfo = vlabQuestioniare.split(Pattern.quote("||"));
		String[] testSplit = splitInfo[0].split(Pattern.quote("|"));

		try {

			for (int ques = 0; ques < testSplit.length; ques++) {
				CommonFunctions.logMessage("Pre-Test Assess " + (ques + 1) + ": "
						+ CommonFunctions.getTextOfElement(preTestQuestions.get(ques), "pre-test questions"));

				WebElement preTestAnswer = null;
				if (!testSplit[ques].equalsIgnoreCase("no")) {
					if (testSplit[ques].equalsIgnoreCase("Y")) {
						preTestAnswer = getDriver().findElement(By
								.xpath("(//form//div[@data-testid])[" + (ques + 1) + "]//label[contains(@for,'-1')]"));

					} else if (testSplit[ques].equalsIgnoreCase("N")) {
						preTestAnswer = getDriver().findElement(By
								.xpath("(//form//div[@data-testid])[" + (ques + 1) + "]//label[contains(@for,'-0')]"));
					}
				}

				String answer = CommonFunctions.getTextOfElement(preTestAnswer, "pretest answer check button");
				CommonFunctions.clickWebelement(preTestAnswer, answer);
			}

			if (splitInfo.length == 3) {
				if (testSplit.length == 4) {
					if (testSplit[3].equalsIgnoreCase("N")) {
						CommonFunctions.logMessage(CommonFunctions.getTextOfElement(
								getDriver().findElement(By.xpath("//label[@for='lastResult']")), "Last result"));
						lastResultDropDown.click();
						CommonFunctions.iterateElementClick(lastResultList, splitInfo[2]);
					}
				}
			}

			/**
			 * Attend the applicable questions
			 */
			if (splitInfo.length == 2 || splitInfo.length == 3) {
				CommonFunctions.logMessage("Select all of the following that apply to you:");
				String[] applicableSplit = splitInfo[1].split(Pattern.quote("|"));

				int quesCount = 0;
				for (String applicable : applicableSplit) {
					if (applicable.equalsIgnoreCase("Y")) {
						CommonFunctions.clickWebelement(applicableYouQues.get(quesCount), CommonFunctions
								.getTextOfElement(applicableYouQues.get(quesCount), "pretest answer check button"));
					}
					quesCount++;
				}
				if (applicableSplit[0].equalsIgnoreCase("Y")) {
					attendAirlineQuestions(airInfo);
				}
			}
		} catch (IndexOutOfBoundsException e) {
			CommonFunctions.logErrorMessageStopExecution(
					"Error while attending the vlab questionaire.. Please check the questions availables!");
		}

	}

	/**
	 * 
	 * @Method to fill the airline travel user info for vlab test
	 * 
	 * @param airlineTravelInfo ---> Provide airline name , country traveling and
	 *                          city that you will be taking the vlab test with
	 *                          separating each by single pipeline.
	 *                          (e.g)airlineName|country|testCity
	 * 
	 * @throws Exception
	 */
	public void attendAirlineQuestions(String airlineTravelInfo) throws Exception {

		String[] testSplit = airlineTravelInfo.split(Pattern.quote("|"));
		if (testSplit.length == 1 || testSplit.length == 2 || testSplit.length == 3) {
			CommonFunctions.logMessage(CommonFunctions
					.getTextOfElement(getDriver().findElement(By.xpath("//label[@for='airline']")), "Airline name"));
			airlineDropDown.click();
			CommonFunctions.iterateElementClick(airlinesList, testSplit[0]);
		}

		if (testSplit.length == 2 || testSplit.length == 3) {
			CommonFunctions.logMessage(CommonFunctions.getTextOfElement(
					getDriver().findElement(By.xpath("//label[@for='countries']")), "Country travel name"));
			countryDropDown.click();
			CommonFunctions.iterateElementClick(countriesList, testSplit[1]);
		}

		if (testSplit.length == 3) {
			CommonFunctions.logMessage(CommonFunctions
					.getTextOfElement(getDriver().findElement(By.xpath("//label[@for='city']")), "City test take name"));
			CommonFunctions.sendKeysIndividual(testCity, testSplit[2], "Test taking city");

		}

	}

	/**
	 * Method to select the dependent patient from the dropdown shown
	 * 
	 * @throws Exception
	 */
	public void selectDependentPatient() throws Exception {

		CommonFunctions.logMessage("Who will be taking the test?");
		CommonFunctions.actionClick(dependentDropdown, "dependent dropdown");
		CommonFunctions.elementIsVisible(dependentList.get(0));
		if (dependentList.size() > 1) {
			int dependentNum = dependentList.size() - 1;
			dependentPatientName.set(CommonFunctions.getTextOfElement(dependentList.get(dependentNum),
					"dependent patient"));
			CommonFunctions.logMessage("Patient selected for the session " + getDependentPatientName());
			CommonFunctions.actionClick(dependentList.get(dependentNum), "dependent patient");

		} else {
			CommonFunctions.logErrorMessageStopExecution("Dependent patient is not available for this navica account");
		}

	}
	
	/**
	 * @This method is used to click the authorization
	 * 
	 * @throws Exception
	 */
	public void clickPreTestAuthorization() throws Exception {
		CommonFunctions.clickWebelement(webelePreTestTerms, "Pre-test terms and authorization");
	}

	/**
	 * @This method is used to click the terms of use and privacy policy
	 * 
	 * @throws Exception
	 */
	public void clickTermsAccept() throws Exception {
		CommonFunctions.clickWebelement(webeleTermsAcceptAndPrivacy, "Terms of use and Privacy policy");
	}

	/**
	 * @This method is used to click the terms and authorization in pre-test
	 * 
	 * @throws Exception
	 */
	public void clickPreTestTermsAndPrivacy() throws Exception {
		boolean flag = CommonFunctions.isExist(getDriver(), "//label[@for='preflight-terms-conditions']");
		if (flag) {
			clickPreTestAuthorization();
			boolean termsFlag = CommonFunctions.isExist(getDriver(), "//label[@for='termsConditionAndPolicyAccepted']");
			if(termsFlag) {
				clickTermsAccept();
			}
		}
	}

	/**
	 * Method to click the Pretest continue in Proctor
	 * 
	 *
	 * @throws Exception
	 * 
	 */

	public void clickJoinGuideSession() throws Exception {
		CommonFunctions.elementToBeClickable(joinGuideSession, "Join guide session button");
		WebElement preAssessContinue = getDriver().findElement(By.xpath("//*[text()='Join guided session']//parent::button"));
		CommonFunctions.elementToBeClickable(preAssessContinue, "Join guide session clickable button");
		if (BrowserNeed.equalsIgnoreCase("firefox")) {
			CommonFunctions.robotKeyMoveClick(preAssessContinue, 1, "Join guide session button");
		} else {
			preAssessContinue.click();
			CommonFunctions.logMessage("Join guide session button is clicked");
		}

	}

	/**
	 * This method is used to click the yes, start the vlab test
	 * 
	 * @throws Exception
	 */
	public void clickYesStartVlab() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		CommonFunctions.clickWebelement(yesStartVlab, "Let's start the test");
	}

	/**
	 * Method to verify the Virtual Lab session in Proctor
	 * 
	 *
	 * @throws Exception
	 * 
	 */

	public void verifyVirtualLabSession() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		CommonFunctions.elementVisibleToCheck(waitForProctor, 25);
		CommonFunctions.logMessage("Virtual lab page is displayed and waiting for certified proctor");
	}
}