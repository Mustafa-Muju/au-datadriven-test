package main.java.pages.elasticpath;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

public class ElasticPathLandingPage extends TestBase {

	private JavascriptExecutor jse = null;

	public ElasticPathLandingPage() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

	@FindBy(xpath = "//div[text()='Dr. Watson STG']")
	private WebElement elasticStg;

	@FindBy(xpath = "//div[text()='Dr. Watson Sandbox']")
	private WebElement elasticdev2;

	/**
	 * Elastic Path landing page verification
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void verifyElasticPathLandingPage() throws Exception {
		CommonFunctions.checkCurrentPageTitle("Your stores");
		CommonFunctions.logMessage("<-----elasticPath Landing Page----->");
	}

	/**
	 * Elastic Path Env selection
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void selectingElasticPathEnv() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		switch (env) {

		case "stg":

			CommonFunctions.clickWebelement(elasticStg, "elastic stg portal");
			break;

		case "dev2":
		case "qa":
			CommonFunctions.clickWebelement(elasticdev2, "elastic sandbox portal");

			break;

		default:
			CommonFunctions.logErrorMessageStopExecution("Error while selecting the elastic path " + env + ".");
		}

	}

}
