package main.java.pages.vlab.core;

import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

public class PreFlightQuestionairePage extends TestBase {

	private JavascriptExecutor jse = null;

	@FindBy(xpath = "//label[text()='Yes']//input[@type='radio' ]")
	private static WebElement eleYes;

	@FindBy(xpath = "//label[text()='No']//input[@type='radio' ]")
	private static WebElement eleNo;

	@FindBy(xpath = "//select[@data-path='previousTest']")
	private static WebElement eleSelectLastResult;

	@FindBy(xpath = "//input[@type='checkbox']")
	private static List<WebElement> elePurpose;

	@FindBy(xpath = "//label[@class='label']")
	private static List<WebElement> elePreTestQuestions;

	@FindBy(xpath = "//*[@data-action]//parent::label")
	private static List<WebElement> eleApplicableYouQues;

	@FindBy(xpath = "//select[@data-path='cruiserLine']")
	private static WebElement eleCruiseDropDown;

	@FindBy(xpath = "//select[@data-path='cruiserLine']/option")
	private static List<WebElement> eleCruiseList;

	@FindBy(xpath = "//select[@data-path='travelAirline']")
	private static WebElement eleAirlineDropDown;

	@FindBy(xpath = "//select[@data-path='travelAirline']/option")
	private static List<WebElement> eleAirlinesList;

	@FindBy(xpath = "//input[@data-path='travelTestCountry']")
	private static WebElement eleCountry;

	@FindBy(xpath = "//input[@data-path='travelTestCity']")
	private static WebElement eleTestCity;

	@FindBy(xpath = "//button[text()='Submit']")
	private static WebElement eleSubmit;

	@FindBy(xpath = "//*[contains(text(),\"Let's make sure\")]")
	private WebElement startVlabTest;

	public PreFlightQuestionairePage() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

	/**
	 * 
	 * @Method for attending virtual lab test question
	 * 
	 * @param vlabQuestioniare --> Y|Y|Y||no||N|Y|Y - default input
	 * 
	 * @throws Exception
	 */
	public void fillCoreQuestionaire(String vlabQuestioniare, String airInfo) throws Exception {

		String[] splitInfo = vlabQuestioniare.split(Pattern.quote("||"));
		String[] testSplit = splitInfo[0].split(Pattern.quote("|"));

		try {

			for (int ques = 0; ques < testSplit.length; ques++) {
				CommonFunctions.logMessage("Pre-Test Assess " + (ques + 1) + ": "
						+ CommonFunctions.getTextOfElement(elePreTestQuestions.get(ques), "pre-test questions"));

				List<WebElement> preTestAnswer = null;
				if (testSplit[ques].equalsIgnoreCase("Y")) {
					preTestAnswer = getDriver().findElements(By.xpath("//input//parent::label[text()='Yes']"));

				} else if (testSplit[ques].equalsIgnoreCase("N")) {
					preTestAnswer = getDriver().findElements(By.xpath("//input//parent::label[text()='No']"));
				}

				String answer = CommonFunctions.getTextOfElement(preTestAnswer.get(ques),
						"pretest answer check button");
				CommonFunctions.clickWebelement(preTestAnswer.get(ques), answer);
			}

			/**
			 * Select the last results for the covid test
			 * 
			 * options -> no, yes-positive, yes-negative and yes-unknown
			 * 
			 */
			if (splitInfo.length == 2 || splitInfo.length == 3) {
				CommonFunctions.logMessage(CommonFunctions.getTextOfElement(
						getDriver().findElement(By.xpath("//label[contains(text(),'Have you had')]")), "Last result"));
				CommonFunctions.selectDropDownValue(eleSelectLastResult, splitInfo[1], "Last covid test result");
			}

			/**
			 * Select the applicable questions
			 */
			if (splitInfo.length == 3) {
				CommonFunctions.logMessage("Select all of the following that apply to you:");
				String[] applicableSplit = splitInfo[2].split(Pattern.quote("|"));

				int quesCount = 0;
				for (String applicable : applicableSplit) {
					if (applicable.equalsIgnoreCase("Y")) {
						CommonFunctions.clickWebelement(eleApplicableYouQues.get(quesCount), CommonFunctions
								.getTextOfElement(eleApplicableYouQues.get(quesCount), "pretest answer check button"));
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
	 * @param airlineTravelInfo ---> Provide cruise name, Provide airline name ,
	 *                          country traveling and city that you will be taking
	 *                          the vlab test with separating each by single
	 *                          pipeline. (e.g)cruise|airlineName|country|testCity
	 * 
	 * @throws Exception
	 */
	public static void attendAirlineQuestions(String airlineTravelInfo) throws Exception {
		try {
			String[] testSplit = airlineTravelInfo.split(Pattern.quote("|"));
			if (testSplit.length == 1 || testSplit.length == 2 || testSplit.length == 3 || testSplit.length == 4) {
				CommonFunctions.waitForPageLoad(getDriver());
				WebElement cruise = getDriver().findElement(By.xpath("//select[@data-path='cruiserLine']"));
				CommonFunctions.selectDropDownText(cruise, testSplit[0], "travel cruise");

			}
			if (testSplit.length == 2 || testSplit.length == 3 || testSplit.length == 4) {

				WebElement airline = getDriver().findElement(By.xpath("//select[@data-path='travelAirline']"));
				CommonFunctions.selectDropDownText(airline, testSplit[1], "travel airline");

			}

			if (testSplit.length == 3 || testSplit.length == 4) {
				WebElement city = getDriver().findElement(By.xpath("//input[@data-path='travelTestCity']"));
				CommonFunctions.sendKeysIndividual(city, testSplit[2], "city");

			}

			if (testSplit.length == 4) {
				WebElement country = getDriver().findElement(By.xpath("//input[@data-path='travelTestCountry']"));
				CommonFunctions.sendKeysIndividual(country, testSplit[3], "country");

			}

		} catch (IndexOutOfBoundsException e) {
			CommonFunctions
					.logErrorMessageStopExecution("Error while tavel mode.. Please check the options available!");
		}
	}

	/**
	 * @This method is used to click the Submit button
	 * 
	 * @throws Exception
	 */
	public void clickSubmit() throws Exception {
		CommonFunctions.clickWebelement(eleSubmit, "Submit button");
	}

	/**
	 * 
	 * @Method is to verify the Binax VLab test page
	 * 
	 * @throws Exception
	 */
	public void verifyCoreVlabPage() throws Exception {
		CommonFunctions.checkCurrentPageTitle("BinaxNOW Covid-19 at Home Test");
		boolean startVlabTesting = CommonFunctions.elementVisibleToCheck(startVlabTest, 10);
		if (startVlabTesting) {
			CommonFunctions.logMessage("Start VLab test page is displayed");
		} else {
			CommonFunctions.logErrorMessageStopExecution("Error Vlab page is not displayed!");
		}
	}

}