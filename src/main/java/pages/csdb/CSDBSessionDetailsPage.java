package main.java.pages.csdb;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

public class CSDBSessionDetailsPage extends TestBase {

	private JavascriptExecutor jse = null;

	@FindBy(xpath = "//*[@data-testid='cutomerName']//following-sibling::p")
	private WebElement eleTestTakerName;
	
	@FindBy(xpath = "//*[@data-testid='certifiedGuide']//following-sibling::p")
	private WebElement eleCertifiedGuide;
	
	@FindBy(xpath = "//*[@data-testid='date']//following-sibling::p")
	private WebElement eleSessionDate;
	
	@FindBy(xpath = "//*[@data-testid='time']//following-sibling::p")
	private WebElement eleSessionTime;
	
	@FindBy(xpath = "//*[@data-testid='length']//following-sibling::p")
	private WebElement eleSessionLength;
	
	@FindBy(xpath = "//*[@data-testid='result']//following-sibling::p")
	private WebElement eleSessionResult;
	
	@FindBy(tagName = "video")
	private WebElement eleSessionVideo;
	
	@FindBy(id = "videoHls")
	private WebElement eleVideoFrame;


	public CSDBSessionDetailsPage() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}
	
	/**
	 * 
	 * @This method is to verify the test taker details
	 * 
	 * @param lName
	 * @param fName
	 * @param date
	 * @param result
	 * 
	 * @throws Exception
	 * 
	 */
	public void verifyTestTakerDetails(String lName, String fName,String date,String result) throws Exception {
		String testTakerName = CommonFunctions.getTextOfElement(eleTestTakerName, "test taker full name");
		
		String processedName = lName +" " +fName;
		
		if(testTakerName.equalsIgnoreCase(processedName)) {
			CommonFunctions
			.logMessage("The test taker name has matched the expected result " + testTakerName);
		} else {
			CommonFunctions.logErrorMessage(
					"The expected test taker name "+processedName+" didnt matched the actual test taker name "+testTakerName);
		}
		
		String testTakenDate = CommonFunctions.getTextOfElement(eleSessionDate, "test taken date");
		
		if(testTakenDate.equalsIgnoreCase(date)) {
			CommonFunctions
			.logMessage("The test taken date has matched the expected result " + testTakenDate);
		} else {
			CommonFunctions.logErrorMessage(
					"The expected test taken date "+date+" didnt matched the actual test taken date "+testTakenDate);
		}
		
		String testTakenResult = CommonFunctions.getTextOfElement(eleSessionResult, "test taken result");
		
		if(testTakenResult.equalsIgnoreCase(result)) {
			CommonFunctions
			.logMessage("The test taken result has matched the expected result " + testTakenResult);
		} else {
			CommonFunctions.logErrorMessage(
					"The expected test taken result "+result+" didnt matched the actual test taken result "+testTakenResult);
		
		}
		
	}
		/**
		 * 
		 * @This method is to verify if recording is available for the session. 
		 * @throws Exception
		 */
		public void verifyRecording() throws Exception {
			CommonFunctions.elementIsVisible(eleVideoFrame);
			getDriver().switchTo().frame(eleVideoFrame);
			boolean flag= CommonFunctions.elementIsVisible(eleSessionVideo);
			if(flag) {
				CommonFunctions
				.logMessage("Recording is Available for the session.");
			} else {
				CommonFunctions.logErrorMessage(
						"Session recording is NOT Available.");
			}
			getDriver().switchTo().defaultContent();

		
	}

}
