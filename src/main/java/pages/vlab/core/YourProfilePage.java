package main.java.pages.vlab.core;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

public class YourProfilePage extends TestBase {

	private JavascriptExecutor jse = null;

	@FindBy(xpath = "//select")
	private WebElement eleTest;

	@FindBy(xpath = "//button[text()='Save']")
	private WebElement eleSave;

	@FindBy(xpath = "//h1[contains(text(),'Who is taking this test')]")
	private WebElement eleTitle;

	@FindBy(xpath = "//button[text()='Yes']")
	private WebElement eleYes;

	@FindBy(xpath = "//select[@data-path='testTaker']")
	private WebElement eleTestTakerDropDown;

	public YourProfilePage() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

	/**
	 * @This method verify Consent eMed page
	 * 
	 * @throws Exception
	 *
	 */

	public void verifyProfilePage() throws Exception {
		CommonFunctions.checkCurrentPageTitle("Your Profile - eMed");
		CommonFunctions.logMessage("Your Profile Page is displayed");
	}

	/**
	 * 
	 * @This method is used to select whom the test taker Is.
	 * 
	 * @param testTaker
	 * @throws Exception
	 * 
	 */
	public void selectTestTaker(String testTaker) throws Exception {
		CommonFunctions.selectDropDownValue(eleTestTakerDropDown, testTaker, "test taker");
	}

	/**
	 * 
	 * @This method clicks Save button
	 * 
	 * @throws Exception
	 */
	public void clickSave() throws Exception {
		CommonFunctions.clickWebelement(eleSave, "Save button");
	}

	/**
	 * @This method click the button yes
	 * 
	 * @throws Exception
	 *
	 */

	public void clickYes() throws Exception {
		CommonFunctions.clickWebelement(eleYes, "Yes button");
	}

}
