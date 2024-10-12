package main.java.pages.www;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

public class ShippingPage extends TestBase {

	private JavascriptExecutor jse = null;

	@FindBy(xpath = "//button[text()='Continue to shipping']")
	private WebElement continueShopping;

	@FindBy(xpath = "//button[text()='Continue to payment']")
	private WebElement continuePayment;

	@FindBy(xpath = "//h3[text()='FedEx First Overnight']//parent::div//ancestor::label")
	private WebElement fedxFirst;

	@FindBy(xpath = "//h3[text()='FedEx Priority Overnight']//parent::div//ancestor::label")
	private WebElement fedxPriority;

	@FindBy(xpath = "//h3[text()='FedEx 2Day']//parent::div//ancestor::label")
	private WebElement Fedx2Day;

	@FindBy(xpath = "(//*[contains(text(),'Choose your shipping method')]//parent::div//label)[1]")
	private WebElement firstShippingMethod;

	@FindBy(xpath = "//input[@id='entered']")
	private WebElement youEnteredAddress;

	@FindBy(xpath = "//footer//following::button[contains(text(),'Continue')]")
	private WebElement confirmAddressContinue;

	public ShippingPage() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

	/**
	 * @This method select the order delivery address
	 * 
	 * @throws Exception
	 */
	public void selectOrderDeliverAddress() throws Exception {
		CommonFunctions.elementVisibleToCheck(youEnteredAddress, 20);
		try {
			youEnteredAddress.sendKeys(Keys.SPACE);
			CommonFunctions.logMessage("You entered address is clicked");
		} catch (NoSuchElementException e) {
			CommonFunctions.logErrorMessageStopExecution("Error while clicking the element you entered address");
		}
	}

	/**
	 * Method to click the continue to shipping in shipping page
	 * 
	 * 
	 * @throws Exception
	 */

	public void continueToShipping() throws Exception {

		CommonFunctions.elementToBeClickable(continueShopping, "continue shopping button");
		WebElement continueToShopping = getDriver().findElement(By.xpath("//button[text()='Continue to shipping']"));
		continueToShopping.click();
		CommonFunctions.logMessage("Continue shipping button is clicked");
	}

	/**
	 * @This method confirm the address given by the user
	 * @throws Exception
	 */
	public void confirmYourAddress() throws Exception {
		boolean flag = CommonFunctions.isExist(getDriver(), "//*[contains(text(),'Please confirm your address')]");
		if (flag) {
			CommonFunctions.logMessage("Confirm your address popup is displayed");
			CommonFunctions.clickWebelement(confirmAddressContinue, "confirm address continue button");
		}
	}

	/**
	 * Method to select the shipping method in shipping page
	 * 
	 * @param shipType
	 * 
	 * 
	 * @throws Exception
	 */

	public void selectShippingMethod(String shipType) throws Exception {

		switch (shipType) {

		case "0":
			CommonFunctions.clickWebelement(firstShippingMethod, "first shipping");
			orderShippingRate.set(CommonFunctions.getTextOfElement(firstShippingMethod, "first shipping"));
			break;

		case "1":
			CommonFunctions.clickWebelement(fedxFirst, "fedx first");
			orderShippingRate.set(CommonFunctions.getTextOfElement(fedxFirst, "fedx first"));
			break;

		case "2":
			CommonFunctions.clickWebelement(fedxPriority, "fedx priority");
			orderShippingRate.set(CommonFunctions.getTextOfElement(fedxPriority, "fedx priority"));
			break;

		case "3":
			CommonFunctions.clickWebelement(Fedx2Day, "Fedx 2Day");
			orderShippingRate.set(CommonFunctions.getTextOfElement(Fedx2Day, "Fedx 2Day"));
			break;

		default:
			CommonFunctions.logErrorMessageStopExecution("The shipping method selected is not exist " + shipType);
			break;
		}	
		orderShippingMethod.set(CommonFunctions.regexText("^[\\s\\S]+\\$", getOrderShippingRate()).replaceAll("\\$|\n", "")
				.trim());
		CommonFunctions.logMessage("Shipping method selected " + getOrderShippingMethod());

		orderShippingRate.set(CommonFunctions.regexText("\\$(\\d+?[\\.\\d]+)", getOrderShippingRate()).replace("$", ""));
		CommonFunctions.logMessage("Cost of shipping method selected " + getOrderShippingRate());

	}

	/**
	 * Method to click continue to payment in shipping page
	 * 
	 * 
	 * @throws Exception
	 */

	public void continueToPayment() throws Exception {
		CommonFunctions.elementToBeClickable(continuePayment, "continue payment button");
		WebElement continueToPayment = getDriver().findElement(By.xpath("//button[text()='Continue to payment']"));
		continueToPayment.click();
		CommonFunctions.logMessage("continue payment button is clicked");
	}

}
