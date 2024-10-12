package main.java.pages.vlab.vlab2;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

public class BinaxExpressPage extends TestBase {

	private JavascriptExecutor jse = null;

	@FindBy(name = "password")
	private WebElement expressPasscode;

	@FindBy(xpath = "//input[@value='Submit']")
	private WebElement expressSubmit;

	@FindBy(xpath = "//a[contains(text(),'STG')]")
	private WebElement expressStgEnv;

	@FindBy(xpath = "//a[contains(text(),'PROD')]")
	private WebElement expressProdEnv;

	@FindBy(xpath = "//a[contains(text(),'QA')]")
	private WebElement expressQAEnv;

	@FindBy(xpath = "//*[contains(text(),'Share results with eMed Labs?')]")
	private WebElement ftfShareResults;

	@FindBy(xpath = "//*[contains(text(),'Allow')]")
	private WebElement allowResults;

	public BinaxExpressPage() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

	/**
	 * 
	 * Method to verify the eMed express landing page
	 * 
	 * @throws Exception
	 *
	 */

	public void verifyLandingPage() throws Exception {
		CommonFunctions.checkCurrentPageTitle("eMed Express");
		CommonFunctions.logMessage("<-----eMed Express Page----->");
	}

	/**
	 * 
	 * Method to enter the express passcode
	 * 
	 * @param passcode
	 * 
	 * @throws Exception
	 *
	 */

	public void enterExpressPasscode(String passcode) throws Exception {
		CommonFunctions.sendKeysIndividual(expressPasscode, passcode, "express passcode text box");
	}

	/**
	 * 
	 * Method to click the express submit button
	 * 
	 * @throws Exception
	 *
	 */

	public void clickExpressSubmit() throws Exception {
		CommonFunctions.clickJSE(expressSubmit, "express submit button");
	}

	/**
	 * 
	 * @This method is to select the binax express environment
	 * 
	 * @throws Exception
	 * 
	 */
	public void selectBinaxEnvironment() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		switch (env.toLowerCase()) {

		case "stg":
			CommonFunctions.clickJSE(expressStgEnv, "express STG button");
			break;

		case "qa":
			CommonFunctions.clickJSE(expressQAEnv, "express QA button");
			break;

		case "prod":
			CommonFunctions.clickJSE(expressProdEnv, "express PROD button");
			break;

		default:
			CommonFunctions.logErrorMessageStopExecution("Error, unknown environment has been choosed...");
			break;

		}
	}

	/**
	 * 
	 * @This method is to confirm the share results with eMed labs
	 * 
	 * @throws Exception
	 */
	public void eMedConfirmShareResults() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		boolean ftfResults = CommonFunctions.elementVisibleToCheck(allowResults, 10);
		if (ftfResults) {
			CommonFunctions.logMessage("Share results with eMed Labs popup is displayed");
			CommonFunctions.clickWebelement(allowResults, "Allow results");
		}
	}

	/**
	 * 
	 * @This Method to click the express submit button after submitting the details
	 *       required for binax session
	 * 
	 * @throws Exception
	 *
	 */
	public void clickExpressSubmitForOtherDetails() throws Exception {
		WebElement submit = getDriver().findElement(By.xpath("//button[text()='Submit']"));
		CommonFunctions.elementToBeClickable(submit, "proceed to create account button");
		CommonFunctions.actionClick(submit, "submit binax other details");
	}

}
