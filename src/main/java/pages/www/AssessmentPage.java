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

public class AssessmentPage extends TestBase {

	private JavascriptExecutor jse = null;

	@FindBy(id = "header_1")
	private WebElement eligibilityAssessment;

	@FindBy(xpath = "//button[text()='Start Assessment']|//button[text()='Start Now']")
	private WebElement StartAssessment;

	@FindBy(xpath = "//p[contains(text(),'Assessment Complete')]|//p[text()='Questionnaire Complete']")
	private WebElement assessmentComplete;

	@FindBy(xpath = "//label[contains(text(),'Are you ordering')]")
	private WebElement whomYouOrder;

	@FindBy(xpath = "//label[contains(text(),'Are you ordering')]//following-sibling::div//label")
	private List<WebElement> whoYouOrderAnswer;

	@FindBy(id = "label_11")
	private WebElement personYouOrder;

	@FindBy(xpath = "//label[contains(@id,'label_input_11')]")
	private List<WebElement> personYouOrderAnswer;

	@FindBy(xpath = "//input[@data-component='first']")
	private List<WebElement> firstName;

	@FindBy(xpath = "//input[@data-component='last']")
	private List<WebElement> lastName;

	@FindBy(xpath = "//input[contains(@class,'validateLiteDate')]")
	private List<WebElement> dateOfBirth;

	@FindBy(xpath = "//span[contains(@class,'test-taker-first-and-last-name-')]//parent::*[not(@class)]")
	private List<WebElement> answerToWhom;

	@FindBy(xpath = "//*[contains(text(),'Which of these describe') and @id]")
	private List<WebElement> reasonForPurchaseKit;

	@FindBy(xpath = "//*[contains(text(),'following symptoms apply') and @id]")
	private List<WebElement> covidSymptomsQues;

	@FindBy(xpath = "//*[contains(@id,'form-pagebreak-next_')]")
	private List<WebElement> next;

	@FindBy(xpath = "//button[contains(text(),'Submit')]")
	private WebElement submit;

	@FindBy(id = "form-pagebreak-back_51")
	private WebElement back;

	@FindBy(name = "q65_businessName")
	private WebElement businessName;

	@FindBy(name = "q57_addressLine1")
	private WebElement streetAddress1;

	@FindBy(name = "q59_city")
	private WebElement city;

	@FindBy(name = "q62_state")
	private WebElement state;

	@FindBy(name = "q63_zipCode")
	private WebElement zipCode;
	
	@FindBy(xpath = "//*[text()='About Your Purchase']")
	private WebElement eleAbtPurchase;
	
	@FindBy(xpath = "//button[contains(text(),'Submit')]")
	private WebElement eleSubmit;

	public AssessmentPage() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

	/**
	 * @Method to verify the test kit questionnaire header
	 * 
	 * @throws Exception
	 */
	public void verifyTestKitQuestionaireHeader() throws Exception {

		CommonFunctions.elementVisibleToCheck(getDriver().findElement(By.xpath("//iframe[@style]")), 10);
		getDriver().switchTo().frame(getDriver().findElement(By.xpath("//iframe[@style]")));

		boolean testQuesHeader = CommonFunctions.elementVisibleToCheck(eligibilityAssessment, 10);
		if (testQuesHeader) {
			CommonFunctions.waitForPageLoad(getDriver());
			CommonFunctions.logMessage("Purchase test kit questionaire is displayed");
		} else {
			CommonFunctions.logErrorMessageStopExecution("Error purchase test kit questionaire is not displayed");
		}
	}

	/**
	 * Method to click the start Assessment
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void clickStartAssessment() throws Exception {

		WebElement startAssessment = getDriver().findElement(By.xpath("//button[text()='Start Now']"));
		startAssessment.click();
		CommonFunctions.logMessage("start assessment button is clicked");
	}

	/**
	 * @This Method selects for whom the kit is purchased
	 * 
	 * @param whom ===> 0 for myself or 1 for someone
	 * 
	 * @throws Exception
	 */
	public void orderForWhom(String whom) throws Exception {

		CommonFunctions.logMessage(CommonFunctions.getTextOfElement(whomYouOrder, "whom you order question"));

		String[] whomPurchase = whom.split(Pattern.quote("|"));
		switch (whomPurchase[0]) {

		case "0":
			CommonFunctions.clickWebelement(whoYouOrderAnswer.get(0),
					CommonFunctions.getTextOfElement(whoYouOrderAnswer.get(0), "just myself check button"));
			break;

		case "1":
			CommonFunctions.clickWebelement(whoYouOrderAnswer.get(1),
					CommonFunctions.getTextOfElement(whoYouOrderAnswer.get(1), "myself & others check button"));
			break;

		case "2":
			CommonFunctions.clickWebelement(whoYouOrderAnswer.get(2),
					CommonFunctions.getTextOfElement(whoYouOrderAnswer.get(2), "others check button"));
			break;

		default:
			CommonFunctions.logErrorMessageStopExecution("Error while selecting the kit purchase for whom!!");
			break;
		}
	}

	/**
	 * Method to click the next question in assessment
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void clickNextQuestion() throws Exception {
		WebElement nextQuestion = getDriver().findElement(By.xpath("//button[contains(text(),'Next Question')]"));
		CommonFunctions.elementToBeClickable(nextQuestion, "next question button");
		nextQuestion.click();
		CommonFunctions.logMessage("Next question is clicked");
	}

	/**
	 * 
	 * @Method to answer pre-test kit assessment reasons
	 * 
	 * @param covidSymps ===> Need to provide answers based on pipelines.. example:
	 *                   if you have 3 questions to answer then provide their
	 *                   answers with single pipelines like "0|1|2" 0 --> option 1
	 *                   or 1 ---> option 2.. And first reason has multiple symptoms
	 *                   to select that can be selected by providing double pipeline
	 *                   and multiples symptoms selected by separating them with
	 *                   single pipelines pipeline (e.g) 0 | 1 || 0 | 1 | 2. Also
	 *                   three pipelines "|||" to separate the each of the test
	 *                   taker (e.g) 0||0|||0||0|||0||0
	 *
	 * @throws Exception
	 *
	 */

	public void answerTheQuestion(String covidSymps) throws Exception {

		String[] usersCount = covidSymps.split(Pattern.quote("|||"));

		for (int users = 0; users < usersCount.length; users++) {

			String[] rawStr = usersCount[users].split(Pattern.quote("||"));

			String[] ansSplit = rawStr[0].split(Pattern.quote("|"));

			CommonFunctions.logMessage(CommonFunctions.getTextOfElement(answerToWhom.get(users), "answer to whom"));

			// Reasons
			CommonFunctions.logMessage(
					CommonFunctions.getTextOfElement(reasonForPurchaseKit.get(users), "reason for purchase kit"));

			for (int ans = 0; ans < ansSplit.length; ans++) {

				List<WebElement> reasonAnswer = getDriver()
						.findElements(By.xpath("(//*[contains(text(),'Which of these describe') and @id])["
								+ (users + 1) + "]//following-sibling::div//label[contains(@id,'label_input')]"));

				if (!ansSplit[ans].equalsIgnoreCase("no") && ansSplit[ans] != null) {
					int option = Integer.parseInt(ansSplit[ans]);
					CommonFunctions.clickWebelement(reasonAnswer.get(option),
							CommonFunctions.getTextOfElement(reasonAnswer.get(option), "answer check button"));
				}
			}

			// Covid symptoms
			if (rawStr.length == 2) {
				CommonFunctions.logMessage(
						CommonFunctions.getTextOfElement(covidSymptomsQues.get(users), "covid symptoms question"));

				List<WebElement> covidSymptomsAns = getDriver()
						.findElements(By.xpath("(//*[contains(text(),'following symptoms apply') and @id])["
								+ (users + 1) + "]//following-sibling::div//label[contains(@id,'label_input_')]"));

				String[] sympsSplit = rawStr[1].split(Pattern.quote("|"));
				for (int symp = 0; symp < sympsSplit.length; symp++) {
					if (!sympsSplit[symp].equalsIgnoreCase("no")) {
						int sympsOps = Integer.parseInt(sympsSplit[symp]);
						CommonFunctions.clickWebelement(covidSymptomsAns.get(sympsOps), CommonFunctions
								.getTextOfElement(covidSymptomsAns.get(sympsOps), "symptoms answer check button"));
					}
				}
			}
		}
	}

	/**
	 * 
	 * @This method DOB assessment answering method
	 * 
	 * @param DOB - provide dob and for multiple test taker provide each dob with a
	 *            single pipeline seaprating each (e.g)01/03/1998|01/02/1998
	 * 
	 * @throws Exception
	 */
	public void answerTheQuestionsDob(String DOB) throws Exception {
		try {
			String[] dob = DOB.split(Pattern.quote("|"));

			for (int testTaker = 0; testTaker < dob.length; testTaker++) {
				CommonFunctions.Sendkeys(dateOfBirth.get(testTaker), dob[testTaker],
						"test taker " + (testTaker + 1) + " date of birth");
				dateOfBirth.get(testTaker).sendKeys(Keys.ENTER);
			}
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution(
					"(Blocker) Unable to proceed to purchase a kit due to next question button not working and date of birth questionnaire field is missing in dev2 env ETIGER-315 ");
		}

	}

	/**
	 * Method answers the test name for the kit purchase assessment
	 * 
	 * @throws Exception
	 */
	public void answerTestName() throws Exception {

		String[] fName = CommonFunctions.getdata("FirstName").split(Pattern.quote("|"));
		String[] lName = CommonFunctions.getdata("LastName").split(Pattern.quote("|"));

		for (int testTaker = 0; testTaker < fName.length; testTaker++) {
			CommonFunctions.Sendkeys(firstName.get(testTaker), fName[testTaker],
					"test taker " + (testTaker + 1) + " first name");
			CommonFunctions.Sendkeys(lastName.get(testTaker), lName[testTaker],
					"test taker " + (testTaker + 1) + " last name");
		}

	}

	/**
	 * 
	 * @This Method fills the bussiness bulk order purchase details
	 * 
	 * @throws Exception
	 */
	public void businessBulkOrderFillUp() throws Exception {
		CommonFunctions.Sendkeys(businessName, CommonFunctions.getdata("FirstName"), "Business name");
		CommonFunctions.Sendkeys(streetAddress1, CommonFunctions.getdata("Address"), "Street address 1");
		CommonFunctions.Sendkeys(city, CommonFunctions.getdata("City"), "City");
		CommonFunctions.selectDropDownValue(state, CommonFunctions.getdata("State"), "State");
		CommonFunctions.Sendkeys(zipCode, CommonFunctions.getdata("ZipCode"), "Zipcode");
	}

	/**
	 * 
	 * @Method to click the next button
	 * 
	 * @param btnNum
	 * @throws Exception
	 */
	public void clickNextButton(int btnNum) throws Exception {
		Thread.sleep(1000);
		CommonFunctions.clickWebelement(next.get(btnNum), "Next button");
	}

	/**
	 * @Method to click the submit button
	 * 
	 * @throws Exception
	 */
	public void clickSubmitButton() throws Exception {
		CommonFunctions.clickWebelement(submit, "Submit button");
	}
	
	/**
	 * 
	 * @This method is select the given purchase reasons from the purchase form
	 * 
	 * @param allReasons
	 * @throws Exception
	 * 
	 */
	public void selectPurchaseReasons(String allReasons) throws Exception {
		
		CommonFunctions.elementIsVisible(eleAbtPurchase);
		CommonFunctions.logMessage("<----- About Your Purchase Page displaying ----->");

		String[] xlsReason = allReasons.split(Pattern.quote("|"));
		List<WebElement> appReasonList = null;
		getListReasons().clear();
		int allReason = 0;
		allReason = (getDriver().findElements(By.xpath("//label/input[@name='reasons']")).size());
		appReasonList = getDriver().findElements(By.xpath("//label/input[@name='reasons']"));
		CommonFunctions.logMessage("<--- selecting purchase reasons ---->");

		try {
			if (allReason > 0) {
				for (int reason = 0; reason < allReason; reason++) {
					getListReasons().add(appReasonList.get(reason).getAttribute("value").trim());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			for (int i = 0; i < xlsReason.length; i++) {
				for (int j = 0; j < getListReasons().size(); j++) {
					if (xlsReason[i].equalsIgnoreCase(getListReasons().get(j))) {
						getDriver().findElements(By.xpath("//label/input[@name='reasons']")).get(j).click();
						CommonFunctions.logMessage((i + 1) + ". " + getListReasons().get(j) + "- selected.");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		CommonFunctions.clickWebelement(eleSubmit, "Purchase reasons submit");
	}

	/**
	 * 
	 * @This method answers the purchase kit questionnaire
	 * 
	 * @param whom       ===> 0 ---> Myself, 1 ---> Someone, 2 ---> Business... For
	 *                   someone we have option yes --> 0 or no --> 1. This option
	 *                   can be provided by adding single pipeline (e.g) 0|0
	 * @param DOB
	 * @param covidSymps ===> Need to provide answers based on pipelines.. example:
	 *                   if you have 3 questions to answer then provide their
	 *                   answers with single pipelines like "0|1|2" 0 --> option 1
	 *                   or 1 ---> option 2.. And first reason has multiple symptoms
	 *                   to select that can be selected by providing double pipeline
	 *                   and multiples symptoms selected by separating them with
	 *                   single pipelines pipeline (e.g) 0 | 1 || 0 | 1 | 2. Also
	 *                   three pipelines "|||" to separate the each of the test
	 *                   taker (e.g) 0||0|||0||0|||0||0
	 * 
	 * @throws Exception
	 * 
	 */
	public void kitPurchaseQuestionaire(String whom, String DOB, String covidSymps) throws Exception {

		verifyTestKitQuestionaireHeader();

		// Whom you order
		orderForWhom(whom);

		clickNextButton(0);

		String[] whomPurchase = whom.split(Pattern.quote("|"));

		if (whomPurchase[0].equals("0") || whomPurchase[0].equals("1") || whomPurchase[0].equals("2")) {

			// Enter test user name
			answerTestName();

			clickNextButton(1);

			// DOB
			answerTheQuestionsDob(DOB);

			// Covid reasons & symptoms
			answerTheQuestion(covidSymps);

		} else if (whomPurchase[0].equals("3")) {
			businessBulkOrderFillUp();
		}

		clickNextButton(3);

		clickSubmitButton();
	}

	/**
	 * 
	 * @Method to verify the assessment completed
	 * 
	 * @throws Exception
	 *
	 */

	public void verifyAssessmentCompleted() throws Exception {

		CommonFunctions.elementVisibleToCheck(assessmentComplete, 10);
		CommonFunctions.waitForPageLoad(getDriver());
		boolean assessmentSuccess = CommonFunctions.elementVisibleToCheck(
				getDriver().findElement(
						By.xpath("//p[contains(text(),'Assessment Complete')]|//p[text()='Questionnaire Complete']")),
				10);

		if (assessmentSuccess) {
			CommonFunctions.logMessage("Assessment completed successfully");
			CommonFunctions.waitForPageLoad(getDriver());
			closeAssessmentComplete();
		} else {
			CommonFunctions.logErrorMessageStopExecution("Error failed to complete the kit assessment!!");
		}
	}

	/**
	 * 
	 * @Method to close the Assessment
	 * 
	 * @throws Exception
	 *
	 */

	public void closeAssessmentComplete() throws Exception {
		CommonFunctions.elementIsVisible(getDriver().findElement(By.xpath("//button[contains(text(),'Close')]")));
		WebElement closeAssess = getDriver().findElement(By.xpath("//button[contains(text(),'Close')]"));
		CommonFunctions.elementToBeClickable(closeAssess, "Close button");
		closeAssess.click();
		CommonFunctions.logMessage("Close assessment is clicked");
	}
}
