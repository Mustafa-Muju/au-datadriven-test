package main.java.pages.elasticpath;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

public class ElasticPathLoginPage extends TestBase {

	private JavascriptExecutor jse = null;

	@FindBy(id = "email")
	private WebElement elasticuserName;

	@FindBy(id = "password")
	private WebElement elasticpassword;

	@FindBy(xpath = "//span[text()='Login']")
	private WebElement elasticloginButton;

	public ElasticPathLoginPage() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

	/**
	 * Elastic Path login page verification
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void verifyElasticPathLoginPage() throws Exception {
		CommonFunctions.checkCurrentPageTitle("ember10");
		CommonFunctions.logMessage("<-----elasticPath Login Page----->");
	}

	/**
	 * Method to enter Elastic username
	 * 
	 * @param ElasticrUser
	 * 
	 * @throws Exception
	 *
	 */

	public void enterElasticUser(String ElasticUser) throws Exception {
		CommonFunctions.SendkeysWithoutInputLog(elasticuserName, ElasticUser, "elastic username");
	}

	/**
	 * Method to enter elastic Password
	 * 
	 * @param elasticPassword
	 * 
	 * @throws Exception
	 *
	 */

	public void enterElasticPassword(String elasticPassword) throws Exception {
		CommonFunctions.SendkeysWithoutInputLog(elasticpassword, elasticPassword, "elastic password");
	}

	/**
	 * Method to click elastic Login
	 * 
	 * @throws Exception
	 *
	 */

	public void clickElasticLogin() throws Exception {
		CommonFunctions.clickWebelement(elasticloginButton, "elastic login button");
	}

}
