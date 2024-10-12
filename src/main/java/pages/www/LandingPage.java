package main.java.pages.www;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import encryptusercredentials.EncryptCredentails;
import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

public class LandingPage extends TestBase {

	private JavascriptExecutor jse = null;

	@FindBy(xpath = "//a[@aria-current='page']")
	private WebElement eMedLogo;

	@FindBy(css = "a[href='/app/login']")
	private WebElement loginButton;

	@FindBy(xpath = "//a[text()='Start testing']")
	private WebElement startTesting;

	@FindBy(xpath = "//span[text()='Start Testing']//parent::a")
	private WebElement footerStartTesting;

	@FindBy(xpath = "//button[@title='My Account page']")
	private WebElement userAccountIcon;

	@FindBy(xpath = "//h1[text()='Login']")
	private WebElement loginHeader;

	public LandingPage() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

	/**
	 * WWW window invoke method
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void invokeWWWNewWindow() throws Exception {
		try {
			CommonFunctions.waitForPageLoad(getDriver());
			CommonFunctions.openNewBrowserTab("https://"
					+ new EncryptCredentails().decrypt(CommonFunctions.getPropertyValues().getProperty("wwwusername"))
					+ ":"
					+ new EncryptCredentails().decrypt(CommonFunctions.getPropertyValues().getProperty("wwwpassword"))
					+ "@" + env + ".emed.com/", getDriver());
			CommonFunctions.waitForPageLoad(getDriver());

		} catch (Exception e) {

			CommonFunctions.logErrorMessageStopExecution("Error while opening the new window");
		}
		if (BrowserNeed.equalsIgnoreCase("safari")) {
			CommonFunctions.robotBrowserAuthenticate(
					new EncryptCredentails().decrypt(CommonFunctions.getPropertyValues().getProperty("wwwusername")),
					new EncryptCredentails().decrypt(CommonFunctions.getPropertyValues().getProperty("wwwpassword")));
		}
	}

	/**
	 * Method to verify the Landing Page
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void verifyLandingPage() throws Exception {
		CommonFunctions.checkCurrentPageTitle("Homepage");
		CommonFunctions.logMessage("<-----eMed Landing Page----->");
	}

	/**
	 * Method to click the Login Tab
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void clickLoginTab() throws Exception {

		CommonFunctions.waitForPageLoad(getDriver());
		for (int tries = 0; tries < 5; tries++) {

			loginButton.click();
			CommonFunctions.logMessage("Login tab is clicked");

			boolean tryFlag = CommonFunctions.elementVisibleToCheck(loginHeader, 5);
			if (tryFlag) {
				break;
			} else {
				CommonFunctions.logMessage("Login page not navigated. So trying to click again");
			}

		}
	}

	/**
	 * Method to verify and click login tab
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void logintabverify() throws Exception {
		verifyLandingPage();
		clickLoginTab();
	}

	/**
	 * This method clicks the shop
	 * 
	 * @throws Exception
	 */
	public void clickShopTabOnly() throws Exception {
		WebElement shopTab = getDriver().findElement(By.xpath("//p[text()='Shop']//parent::a"));
		CommonFunctions.elementToBeClickable(shopTab, "shop tab");
		shopTab.click();
	}

	/**
	 * Method to click the shop tab
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void clickShopTab() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		CommonFunctions.elementIsVisible(userAccountIcon);
		WebElement shopTab = getDriver().findElement(By.xpath("//p[text()='Shop']//parent::a"));
		CommonFunctions.elementToBeClickable(shopTab, "shop tab");
		if (userAccountIcon.isDisplayed()) {
			getDriver().findElement(By.xpath("//p[text()='Shop']//parent::a")).click();
			CommonFunctions.logMessage("Shop tab is clicked");
		}
	}

	/**
	 * Method to click the Start Testing
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void clickStartTesting() throws Exception {
		CommonFunctions.clickWebelement(startTesting, "start testing button");
	}

	/**
	 * Method to click the Footer Start Testing
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void clickFooterStartTesting() throws Exception {
		CommonFunctions.clickWebelement(footerStartTesting, "footer start testing button");
	}

}
