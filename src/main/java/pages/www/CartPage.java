package main.java.pages.www;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

public class CartPage extends TestBase {

	private JavascriptExecutor jse = null;
	
	@FindBy(xpath = "//*[text()='Your Cart']")
	private WebElement yourCartPage;
	
	@FindBy(xpath = "//button[text()='Proceed to Checkout']")
	private WebElement proceedCheckout;

	public CartPage() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}
	
	/**
	 * 
	 * @This method is to check the your cart page loaded successfully
	 * 
	 * @throws Exception 
	 * 
	 */
	public void checkCartPageLoaded() throws Exception {
		CommonFunctions.checkCurrentPageTitle("Checkout Cart - eMed");
		boolean cartPage = CommonFunctions.elementVisibleToCheck(yourCartPage, 10);
		if(cartPage) {
			CommonFunctions.logMessage("<---Your Cart Page is displayed--->");
		} else {
			CommonFunctions.logErrorMessageStopExecution("Error, Your cart page is not displayed..");
		}
	}
	
	/**
	 * Method to click the proceed checkout
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void proceedToCheckout() throws Exception {

		CommonFunctions.elementToBeClickable(proceedCheckout, "proceed to checkout button");
		CommonFunctions.waitForPageLoad(getDriver());
		WebElement proceedToCheckout = getDriver().findElement(By.xpath("//button[text()='Proceed to Checkout']"));
		proceedToCheckout.click();
		CommonFunctions.logMessage("proceed to checkout button is clicked");
	}
}
