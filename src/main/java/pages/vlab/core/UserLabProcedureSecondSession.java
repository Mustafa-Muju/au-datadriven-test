package main.java.pages.vlab.core;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

public class UserLabProcedureSecondSession extends TestBase {

	private JavascriptExecutor jse = null;

	@FindBy(xpath = "//*[contains(text(),'The timer is finished')]")
	private WebElement eleTimerFinishTxt;

	@FindBy(xpath = "//*[contains(text(),\"I'm ready\")]")
	private WebElement eleImReady;

	public UserLabProcedureSecondSession() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

	/**
	 * 
	 * @This method is verify the timer has finished for lab test
	 * 
	 * @throws Exception
	 */
	public void verifyTimerFinished() throws Exception {
		boolean flag = CommonFunctions.elementVisibleToCheck(eleTimerFinishTxt, 20);
		if (flag) {
			CommonFunctions.logMessage("Timer is finished has displayed successfully");

		} else {
			CommonFunctions.logErrorMessageStopExecution("Failed, Timer didnt finished after the 20 seconds..");
		}
	}

	/**
	 * 
	 * @This method is used to click the button - "I'm ready"
	 * 
	 * @throws Exception
	 */
	public void clickImReady() throws Exception {
		CommonFunctions.clickWebelement(eleImReady, "I'm ready");
	}

}
