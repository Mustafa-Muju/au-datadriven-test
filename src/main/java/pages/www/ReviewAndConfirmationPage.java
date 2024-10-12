package main.java.pages.www;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

public class ReviewAndConfirmationPage extends TestBase {

	private JavascriptExecutor jse = null;

	@FindBy(xpath = "//h1[text()='Order Placed']")
	private WebElement orderPlaced;

	@FindBy(xpath = "//button[@data-testid='place-order-button']")
	private WebElement placeYOrder;

	@FindBy(xpath = "//p[contains(text(),'Confirmation')]")
	private WebElement confirmNumber;

	public ReviewAndConfirmationPage() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}
	
	/**
	 * Method to click the place your order
	 * 
	 * @throws Exception
	 */

	public void clickPlaceYourOrder() throws Exception {
		CommonFunctions.elementToBeClickable(placeYOrder, "Place your order button");
		WebElement placeYourOrder = getDriver().findElement(By.xpath("//button[@data-testid='place-order-button']"));
		placeYourOrder.click();
		CommonFunctions.logMessage("Place your order button is clicked");
	}
	
	/**
	 * Order placed verification method
	 * 
	 * @throws Exception
	 */

	public void verifyOrderPlaced() throws Exception {
		CommonFunctions.elementIsVisible(orderPlaced);
		confirmationNum.set(CommonFunctions.getTextOfElement(confirmNumber, "confirmation number").split("#")[1]);
		if (!getConfirmationNumber().equals("")) {
			CommonFunctions.logMessage("Confirmation number for the placed order: " + getConfirmationNumber());
		} else {
			CommonFunctions.logErrorMessageStopExecution("No confirmation number is generated");
		}
	}
	
	/**
	 * Method get the order id for order placed
	 *
	 */
	public void getCustomerId() {
		
		eMedId.set(getDriver().getCurrentUrl().split("orderId=")[1]);
		CommonFunctions.logMessage("Order Id for the order placed: " + getEMedId());
	}

}
