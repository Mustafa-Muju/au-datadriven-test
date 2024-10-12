package main.java.pages.www;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

public class PaymentPage extends TestBase {

	private JavascriptExecutor jse = null;

	@FindBy(xpath = "//*[contains(text(),'Please select and enter')]")
	private WebElement paymentPageHeader;

	@FindBy(id = "cardHolderName")
	private WebElement cardHolderName;

	@FindBy(xpath = "//input[@aria-label='Credit or debit card number']")
	private WebElement cardNumber;

	@FindBy(xpath = "//input[@aria-label='Credit or debit card expiration date']")
	private WebElement cardExpiryDate;

	@FindBy(xpath = "//input[@placeholder='CVC']")
	private WebElement cardCVVNumber;

	@FindBy(id = "cardHolderZipCode")
	private WebElement cardHolderZip;

	@FindBy(xpath = "(//*[@data-testid='order-summary-subtotal'])[2]")
	private WebElement orderSubTotal;

	@FindBy(xpath = "(//*[@data-testid='order-summary-shipping'])[2]")
	private WebElement orderShipRate;

	@FindBy(xpath = "(//*[@data-testid='order-summary-tax'])[2]")
	private WebElement orderTax;

	@FindBy(xpath = "(//*[@data-testid='order-summary-total'])[2]")
	private WebElement orderTotal;

	@FindBy(xpath = "//span[text()='Continue To Review']//ancestor::button")
	private WebElement continueToReview;

	public PaymentPage() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

	/**
	 * Method to verify the payment page is displayed successfully
	 * 
	 * @throws Exception
	 */
	public void verifyPaymentPage() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		try {
			if (paymentPageHeader.isDisplayed()) {
				CommonFunctions.logMessage("Payment page is displayed successfully");
			}
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Error while proceeding payment page");
		}
	}

	/**
	 * Method for enter the Card Holder Name
	 * 
	 * @param cardHoldName
	 * 
	 * @throws Exception
	 */

	public void enterCardHolder(String cardHoldName) throws Exception {
		CommonFunctions.Sendkeys(cardHolderName, cardHoldName, "card holder name");
	}

	/**
	 * Method for enter the Card Number
	 * 
	 * @param cNumber
	 * 
	 * @throws Exception
	 */

	public void enterCardNumber(String cNumber) throws Exception {
		Thread.sleep(1000);
		getDriver().switchTo().frame(getDriver().findElement(By.xpath("//iframe[contains(@title,'Secure card number')]")));
		CommonFunctions.sendKeysIndividual(cardNumber, cNumber, "card number");
		getDriver().switchTo().defaultContent();
	}

	/**
	 * Method for enter the Card Expiry Date
	 * 
	 * @param expiryDate
	 * 
	 * @throws Exception
	 */

	public void enterCardExpiryDate(String expiryDate) throws Exception {
		getDriver().switchTo().frame(getDriver().findElement(By.xpath("//iframe[contains(@title,'Secure expiration date')]")));
		CommonFunctions.Sendkeys(cardExpiryDate, expiryDate, "expiry date");
		getDriver().switchTo().defaultContent();
	}

	/**
	 * Method for enter the Card CVV
	 * 
	 * @param CVV
	 * 
	 * @throws Exception
	 */

	public void enterCardCVV(String CVV) throws Exception {
		getDriver().switchTo().frame(getDriver().findElement(By.xpath("//iframe[contains(@title,'Secure CVC')]")));
		CommonFunctions.Sendkeys(cardCVVNumber, CVV, "CVV number");
		getDriver().switchTo().defaultContent();
	}

	/**
	 * Method for enter the Card zipcode
	 * 
	 * @param zipCode
	 * 
	 * @throws Exception
	 */

	public void enterCardHolderZipCode(String zipCode) throws Exception {
		CommonFunctions.Sendkeys(cardHolderZip, zipCode, "zip code");
	}

	/**
	 * Method to click the continue to Preview
	 * 
	 * @throws Exception
	 */

	public void clickContinueToReview() throws Exception {
		CommonFunctions.elementToBeClickable(continueToReview, "continue review button");
		WebElement continueToReview = getDriver()
				.findElement(By.xpath("//span[text()='Continue To Review']//ancestor::button"));
		if (BrowserNeed.equalsIgnoreCase("firefox")) {
			CommonFunctions.robotKeyMoveClick(continueToReview, 1, "Continue review button");
		} else {
			continueToReview.click();
			CommonFunctions.logMessage("Continue review button is clicked");
		}
	}

	/**
	 * Method is used to get the shipping details from the order summary displayed
	 * 
	 * @throws Exception
	 */
	public void getShippingRateDetails() throws Exception {

		CommonFunctions.logMessage("Order summary details");
		orderSubRate.set(CommonFunctions.getTextOfElement(orderSubTotal, "order subtotal").replace("$", ""));
		String shippingMethodPrice = CommonFunctions.getTextOfElement(orderShipRate, "order ship rate").replace("$",
				"");
		orderTaxRate.set(CommonFunctions.getTextOfElement(orderTax, "order tax rate").replace("$", ""));
		orderTotalAmount.set(CommonFunctions.getTextOfElement(orderTotal, "order total").replace("$", ""));

		if (shippingMethodPrice.equalsIgnoreCase(getOrderShippingRate())) {
			CommonFunctions.logMessage("Order shipping cost " + getOrderShippingRate());
		} else {
			CommonFunctions
					.logErrorMessageStopExecution("Error while validating shipping method price " + getOrderShippingRate());
		}

		CommonFunctions.logMessage("Order kit subtotal cost " + getOrderSubRate());
		CommonFunctions.logMessage("Order tax rate " + getOrderTaxRate());
		CommonFunctions.logMessage("Order total cost " + getOrderTotalAmount());
	}
}
