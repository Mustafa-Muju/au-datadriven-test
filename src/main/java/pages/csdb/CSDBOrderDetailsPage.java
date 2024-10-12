package main.java.pages.csdb;

import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import main.java.base.TestBase;
import main.java.flows.csdb.CSDBUserDetailsFlow;
import main.java.utils.CommonFunctions;
import main.java.utils.DefectList;

public class CSDBOrderDetailsPage extends TestBase {

	private JavascriptExecutor jse = null;

	@FindBy(xpath = "//input[contains(@placeholder,'Search by order ID')]")
	private WebElement eleSearchOrderId;

	@FindBy(xpath = "(//*[text()='Order ID']//following::tbody//td)[3]")
	private WebElement eleCustomerFirstOrder;

	@FindBy(xpath = "//a//*[text()='Order Details']")
	private WebElement eleOrderDetailsBreadCrumb;

	@FindBy(xpath = "//*[text()='Order Id']//following-sibling::p")
	private WebElement eleCustomerOrderNumber;

	@FindBy(xpath = "//*[text()='Order Date']//following-sibling::p")
	private WebElement eleCustomerOrderDate;

	@FindBy(xpath = "//*[text()='Subtotal']//parent::div//following-sibling::div")
	private WebElement eleCsdbSubTotal;

	@FindBy(xpath = "//*[text()='Shipping']//parent::div//following-sibling::div")
	private WebElement eleCsdbShippingPrice;

	@FindBy(xpath = "//*[text()='Tax']//parent::div//following-sibling::div")
	private WebElement eleCsdbTax;

	@FindBy(xpath = "//*[text()='Total']//parent::div//following-sibling::div")
	private WebElement eleCsdbTotalPrice;

	@FindBy(xpath = "//a[contains(text(),'More Options')]")
	private WebElement eleMoreOptions;

	@FindBy(xpath = "//*[contains(text(),'Refund order')]")
	private WebElement eleCsdbEditOrderRefund;
	
	@FindBy(xpath= "//label[@for='refundPartial']")
	private WebElement eleCsdbEditPartialRefund;

	@FindBy(id = "refundAmount")
	private WebElement eleCsdbRefundAmount;

	@FindBy(xpath = "//*[contains(text(),'Yes, refund order')]")
	private WebElement eleSubmitRefundOrder;

	@FindBy(xpath = "//p[contains(text(),'Resend Prescription')]")
	private WebElement eleCsdbEditReplaceOrder;

	@FindBy(xpath = "//p[contains(text(),'Cancel order')]//parent::a")
	private WebElement eleCsdbEditCancelOrder;

	@FindBy(xpath = "(//*[contains(text(),'Status')]//parent::div)[1]")
	private WebElement eleCancelStatus;

	@FindBy(xpath = "//*[contains(text(),'cancel this order?')]")
	private WebElement eleCancelVerificationPrompt;

	@FindBy(xpath = "//*[contains(text(),'Yes, cancel order')]")
	private WebElement eleCancelOrder;

	@FindBy(xpath = "//*[contains(text(),'Unable to cancel order')]")
	private WebElement eleUnableToCancel;

	@FindBy(xpath = "//*[contains(text(),'This order is no longer eligible for cancellation.')]")
	private WebElement eleCancelButtonGreyOut;

	@FindBy(xpath = "//*[contains(text(),'Replace Order')]//following::div//button")
	private static WebElement eleReplaceOrderSubmit;

	@FindBy(xpath = "//*[contains(text(),'Replace order')]")
	private static WebElement eleReplaceOrder;

	@FindBy(xpath = "//div[@id='products[0].product']//parent::div[contains(@class,'outer-container')]")
	private static WebElement eleSelectKit;

	@FindBy(xpath = "//*[contains(@id,'react-select-2-option')]|//*[contains(@id,'react-select-7-option')]")
	private static List<WebElement> eleKitOptions;

	@FindBy(xpath = "//*[contains(text(),'Yes, replace order')]")
	private static WebElement eleConfirmReplaceOrder;

	@FindBy(xpath = "//*[contains(text(),'This order has been replaced')]")
	private static WebElement eleValidateReplaceOrder;

	@FindBy(xpath = "//*[contains(text(),'Partial refund')]")
	private WebElement elePartialrefundvalidation;

	@FindBy(xpath = "//*[contains(text(),'Refund total amount:   ')]")
	private WebElement eleFullrefundvalidation;

	@FindBy(xpath = "//*[contains(text(),'Refund order')]")
	private WebElement eleRefundOrder;

	@FindBy(xpath = "//*[contains(text(),'Email Prescription(s) to Customer?')]")
	private WebElement eleEmailPrescription;

	@FindBy(xpath = "//*[contains(text(),'Send Prescription(s)')]")
	private WebElement eleSendEmailPrescription;

	@FindBy(xpath = "//*[contains(text(),'Email was sent successfully')]")
	private WebElement eleEmailPrescriptionSuccessful;

	@FindBy(xpath = "//*[text()='Close']//ancestor::button")
	private WebElement eleCloseEmailPrescribeSuccessPopup;

	@FindBy(xpath = "//*[contains(text(),'Email Receipt to Customer')]")
	private WebElement eleEmailReceipt;

	@FindBy(xpath = "//*[contains(text(),'Send Receipt')]")
	private WebElement eleSendReceipt;

	@FindBy(xpath = "//*[contains(text(),'Receipt email was sent successfully')]")
	private WebElement eleEmailReceiptSuccessful;

	@FindBy(xpath = "//*[@name='reason']//parent::div//label")
	private List<WebElement> eleRefundReasons;

	@FindBy(xpath = "//*[contains(text(),'This order was refunded')]")
	private WebElement eleBannerRefundReason;

	@FindBy(xpath = "//*[@name='replaceReason']//parent::div//label|//*[@name='replaceReason']//parent::label")
	private List<WebElement> eleReplaceReasons;

	@FindBy(xpath = "//*[contains(text(),'This order was replaced for the following reasons: ')]")
	private WebElement eleB2BReplacementBanner;

	@FindBy(xpath = "//p[contains(text(),'Abbott’s BinaxNOW™ COVID-19 Ag At-Home Test Kit')]")
	private WebElement eleOrderQuantity;

	@FindBy(xpath = "//a[contains(text(),'Edit order details')]")
	private WebElement eleEditOrderDetails;

	@FindBy(xpath = "//button[text()='Edit order']")
	private WebElement eleEditOrder;

	@FindBy(xpath = "//div[contains(text(),'updated successfully')]")
	private WebElement eleOrderSuccessDetails;

	@FindBy(id = "address.streetAddress")
	private WebElement eleStreetAddress;

	@FindBy(id = "address.city")
	private WebElement eleCity;

	@FindBy(id = "address.zipCode")
	private WebElement eleZipCode;

	@FindBy(xpath = "//div[text()='Action History']")
	private WebElement eleActionHistory;
	
	@FindBy(xpath = "//table/tbody/tr/td[1]")
	private WebElement eleFirstHistory;

	public CSDBOrderDetailsPage() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

	/**
	 * This Method to search the CSDB user
	 * 
	 *
	 * @throws Exception
	 */

	public void enterCSDBOrderId(String orderId) throws Exception {
		CommonFunctions.Sendkeys(eleSearchOrderId, orderId, "CSDB patient order Id");
	}

	/**
	 * This Method to search the CSDB user
	 * 
	 *
	 * @throws Exception
	 */

	public void enterCSDBUserSearch(String orderId) throws Exception {
		CommonFunctions.Sendkeys(eleSearchOrderId, orderId, "CSDB patient order Id");
	}

	/**
	 * This Method click the CSDB First customer order
	 * 
	 * @throws Exception
	 */

	public void clickCSDBFirstOrder() throws Exception {
		CommonFunctions.actionClick(eleCustomerFirstOrder, "CSDB first order");
	}

	/**
	 * This Method for verifying the CSDB Order Details Page
	 * 
	 * @throws Exception
	 *
	 */

	public void verifyCSDBOrderDetailPage() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		CommonFunctions.elementIsVisible(eleOrderDetailsBreadCrumb);
		CommonFunctions.logMessage("<-----CSDB Order Details Page----->");
	}
	
	/**
	 * 
	 * @This method is to verify CSDB OrderId
	 * 
	 * @param orderId
	 * 
	 * @throws Exception
	 * 
	 */
	public void verifyCSDBOrderId(String orderId) throws Exception {
		String orderNumber = CommonFunctions.getTextOfElement(eleCustomerOrderNumber, "Customer order number");
		if (orderNumber.equalsIgnoreCase(orderId)) {
			CommonFunctions.logMessage("Order placed id matched the CSDB order id ===> " + orderNumber);
		} else {
			CommonFunctions.logErrorMessage("Order placed id " + orderId + " not matched CSDB order id " + orderNumber);
		}
	}

	/**
	 * This method to click the more option to perform cancel / refund / replace
	 * order
	 * 
	 * @throws Exception
	 */

	public void moreOptions() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		CommonFunctions.clickWebelement(eleMoreOptions, "More options");
	}

	/**
	 * This method will refund the order in csdb
	 * 
	 * @throws Exception
	 */

	public void refundOrder() throws Exception {
		CommonFunctions.clickWebelement(eleCsdbEditOrderRefund, "Refund Order");
	}

	/**
	 * This method will cancel the order in csdb
	 * 
	 * @throws Exception
	 */

	public void cancelOrder() throws Exception {
		CommonFunctions.clickWebelement(eleCsdbEditCancelOrder, "Cancel Order");
	}

	/**
	 * This method will confirm the order cancellation
	 * 
	 * @throws Exception
	 */

	public void cancelOrderConfirmation() throws Exception {
		CommonFunctions.clickWebelement(eleCancelOrder, "Cancel Order Confirmation");
	}

	/**
	 * @apiNote Method is used to verify the Customer order details in CSDB
	 * @throws Exception
	 */
	public void verifyCustomerOrderDetails() throws Exception {

		String orderDateSplit[] = CommonFunctions.getTextOfElement(eleCustomerOrderDate, "Customer order date").split("/");
		if (Integer.parseInt(orderDateSplit[1]) <= 9 && orderDateSplit[1].length() == 1) {
			orderDateSplit[1] = "0" + orderDateSplit[1];
		}
		String orderDate = orderDateSplit[0] + "/" + orderDateSplit[1] + "/" + orderDateSplit[2];

		String orderNumber = CommonFunctions.getTextOfElement(eleCustomerOrderNumber, "Customer order number");
		String subTotal = CommonFunctions.getTextOfElement(eleCsdbSubTotal, "sub total").replace("$", "");
		String shippingCost = CommonFunctions.getTextOfElement(eleCsdbShippingPrice, "shipping cost").replace("$", "");
		String tax = CommonFunctions.getTextOfElement(eleCsdbTax, "tax rate").replace("$", "");
		String totalCost = CommonFunctions.getTextOfElement(eleCsdbTotalPrice, "total order price").replace("$", "");

		if (orderNumber.equalsIgnoreCase(getOrderId())) {
			CommonFunctions.logMessage("Order placed id matched the CSDB order id ===> " + orderNumber);
		} else {
			CommonFunctions.logErrorMessage("Order placed id " + getOrderId() + " not matched CSDB order id " + orderNumber);
		}

		if (orderDate.equalsIgnoreCase(getOrderedDate())) {
			CommonFunctions.logMessage("Order placed date matched the CSDB order date ===> " + orderDate);
		} else {
			CommonFunctions
					.logErrorMessage("Order placed date " + getOrderedDate() + " not matched CSDB order date " + orderDate);
		}

		if (subTotal.equalsIgnoreCase(getOrderSubRate())) {
			CommonFunctions.logMessage("Order placed subtotal matched the CSDB order subtotal ===> " + subTotal);
		} else {
			CommonFunctions.logErrorMessage(
					"Order placed subtotal " + orderSubRate + " not matched CSDB order subtotal " + subTotal);
		}

		if (shippingCost.equalsIgnoreCase(getOrderShippingRate())) {
			CommonFunctions.logMessage(
					"Order placed shipping price matched the CSDB order shipping price ===> " + shippingCost);
		} else {
			CommonFunctions.logErrorMessage("Order placed shipping price " + orderShippingRate
					+ " not matched CSDB order shipping price " + shippingCost);
		}

		if (tax.equalsIgnoreCase(getOrderTaxRate())) {
			CommonFunctions.logMessage("Order placed shipping tax matched the CSDB order shipping tax ===> " + tax);
		} else {
			CommonFunctions.logErrorMessage(
					"Order placed shipping tax " + orderTaxRate + " not matched CSDB order shipping tax " + tax);
		}

		if (totalCost.equalsIgnoreCase(getOrderTotalAmount())) {
			CommonFunctions.logMessage("Order placed total price matched the CSDB order total price ===> " + totalCost);
		} else {
			CommonFunctions.logErrorMessage("Order placed total price " + orderTotalAmount
					+ " not matched CSDB order total price " + totalCost);
		}
	}

	/**
	 * 
	 * @This method is used to verify the order is not eligible for cancellation
	 * 
	 * @throws Exception
	 */
	public void verifyUnableToCancelOrder() throws Exception {
		boolean noCancel = CommonFunctions.elementVisibleToCheck(eleUnableToCancel, 10);
		if (noCancel) {
			CommonFunctions.logMessage("Order is not eligible for the cancellation ===> Passed");
		} else {
			CommonFunctions.logErrorMessageStopExecution("Error, User is able to cancel the order...");
		}

	}

	/**
	 * This method to validate the more options
	 * 
	 * @throws Exception
	 */

	public void validatingMoreOptions() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		try {
			new WebDriverWait(getDriver(), 10).until(ExpectedConditions.visibilityOf(eleMoreOptions));
			CommonFunctions.logErrorMessage("More options are available for Order Read Only role in CSDB");
		} catch (Exception e) {
			CommonFunctions.logMessage("More options are not available for Order Read Only role in CSDB");
		}
	}

	/**
	 * This method will confirm the after order cancellation the more option
	 * disappears
	 * 
	 * @throws Exception
	 */
	public void verifyMoreOptionDisappearsAfterCancelation() throws Exception {

		boolean moreOpts = CommonFunctions.elementVisibleToCheck(eleMoreOptions, 10);
		if (!moreOpts) {
			CommonFunctions.logMessage("More option not displayed after the order has been canceled ==> Passed");
		} else {
			CommonFunctions.logErrorMessage("Error, More option get displayed after the order has been canceled.");
		}
	}

	/**
	 * This method is to validate the cancel order status
	 * 
	 * @throws Exception
	 */

	public void verifyingCancelOrderStatus() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		Thread.sleep(2000);
		String CancelOrderStatus = CommonFunctions.getTextOfElement(eleCancelStatus, "cancel Status");
		if (CancelOrderStatus.contains("Canceled")) {
			CommonFunctions.logMessage("Order is cancelled successfully");
		} else {
			CommonFunctions.logErrorMessage("Order is not cancelled successfully");
		}
	}

	/**
	 * Method to verify the once prescription has been approved, the User should no
	 * longer be able to cancel an order.
	 * 
	 * 
	 * @throws Exception
	 */

	public void verifyCancelButtonGreyOut() throws Exception {
		try {
			new WebDriverWait(getDriver(), 5).until(ExpectedConditions.visibilityOf(eleCancelButtonGreyOut));

			CommonFunctions.logMessage(
					"Once the order has been ACCEPTED_SHIPMENT, the cancel order button has get greyed out successfully");
		} catch (Exception e) {
			CommonFunctions.logErrorMessage(
					"Error, Cancel order button is still enabled after the order has been ACCEPTED_SHIPMENT.");
		}
	}

	/**
	 * This Method to check the partial refund deduction
	 * 
	 *
	 * @throws Exception
	 */

	public void partialRefundConfirmation() throws Exception {

		try {
			CommonFunctions.elementIsVisible(elePartialrefundvalidation);
			CommonFunctions.logMessage("Partial refund is completed sucessfully");
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution(DefectList.getP2Defect("CSS-23"));
		}
	}

	/**
	 * This Method to validate the full refund status
	 * 
	 *
	 * @throws Exception
	 */

	public void refundValidations() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		boolean fullRefund = CommonFunctions.isExist(getDriver(), "//*[contains(text(),'refunded')]");
		if (fullRefund) {
			CommonFunctions.logMessage("Full refund status is updated in CSDB");
		} else {
			CommonFunctions.logErrorMessageStopExecution("Full refund status is not updated in CSDB ");
		}
	}

	/**
	 * This Method to validate the partial refund status
	 * 
	 *
	 * @throws Exception
	 */
	public void verifyPartialRefundStatus(String refundReason) throws Exception {

		CSDBUserDetailsFlow.navigateCustomerDB(getEmailId());
		CSDBUserDetailsFlow.orderIdFlow();
		verifyOrderCSDBStatus();
		moreOptions();
		verifyCancelButtonGreyOut();		
		partialRefund(refundReason);
		new CSDBLandingPage().verifyLandingPage();
		partialRefundConfirmation();
		new CSDBOrderDetailsPage().verifyRefundReasonsFromBanner();
	}

	/**
	 * 
	 * @This Method to click the email prescription
	 *
	 * @throws Exception
	 */
	public void emailPrescription() throws Exception {
		try {
			CommonFunctions.elementIsVisible(eleEmailPrescription);
			CommonFunctions.clickWebelement(eleEmailPrescription, "Email Prescription");
			CommonFunctions.clickWebelement(eleSendEmailPrescription, "Send Email Prescription");

			boolean prescriptionSendSuccessful = CommonFunctions.elementVisibleToCheck(eleEmailPrescriptionSuccessful, 15);
			if (prescriptionSendSuccessful) {
				CommonFunctions.logMessage("Prescription email is send successfully");
			} else {
				CommonFunctions.logErrorMessage("Failed to display popup for sending the prescription email");
			}
			CommonFunctions.clickWebelement(eleCloseEmailPrescribeSuccessPopup, "close email prescribe success popup");

		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Error while performing the email prescription");
		}
	}

	/**
	 * 
	 * @This method to perform the itemized receipt action
	 * 
	 * @throws Exception
	 */
	public void itemizedReceipt() throws Exception {
		try {
			CommonFunctions.elementIsVisible(eleEmailReceipt);
			CommonFunctions.clickWebelement(eleEmailReceipt, "Email itemized receipt");
			CommonFunctions.clickWebelement(eleSendReceipt, "Send receipt email");

			boolean receiptSendSuccessful = CommonFunctions.elementVisibleToCheck(eleEmailReceiptSuccessful, 15);
			if (receiptSendSuccessful) {
				CommonFunctions.logMessage("Receipt email is send successfully");
			} else {
				CommonFunctions.logErrorMessage("Failed to display popup for sending the receipt email");
			}

		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Error while performing the email itemized receipt");
		}
	}

	/**
	 * This Method to click the email prescription
	 * 
	 *
	 * @throws Exception
	 */

	public void selectReplaceOrderKit(String kitType) throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		CommonFunctions.actionClick(eleSelectKit, "Selecting KIT");
		Thread.sleep(2000);
		CommonFunctions.iterateElementClickIgnoreCase(eleKitOptions, kitType);
	}

	/**
	 * This Method to click the OrderReplace submit
	 * 
	 *
	 * @throws Exception
	 */

	public void clickReplaceOrderSubmitButton() throws Exception {

		CommonFunctions.clickWebelement(eleReplaceOrderSubmit, "OrderPlace");

	}

	/**
	 * This Method to click the OrderReplace
	 * 
	 *
	 * @throws Exception
	 */

	public void clickReplaceOrderButton() throws Exception {

		CommonFunctions.clickWebelement(eleReplaceOrder, "OrderPlace");

	}

	/**
	 * This Method to click the ConfirmReplaceOrder
	 * 
	 *
	 * @throws Exception
	 */

	public void clickConfirmReplaceOrderButton() throws Exception {

		CommonFunctions.clickWebelement(eleConfirmReplaceOrder, "confirmReplaceOrder");

	}

	/**
	 * This Method to validate the replace order
	 * 
	 *
	 * @throws Exception
	 */

	public void replaceOrderValidations() throws Exception {
		try {
			boolean replacementOrder = CommonFunctions.elementVisibleToCheck(eleValidateReplaceOrder, 20);
			if (replacementOrder) {
				CommonFunctions.logMessage("The Order is replaced successfully");
			}
		} catch (Exception e) {
			CommonFunctions.logErrorMessage("The Order is not replaced successfully");
		}

	}

	/**
	 * 
	 * @This method is to verify the order status in csdb
	 * 
	 * @param statusValue
	 * @throws Exception
	 * 
	 */
	public void verifyOrderCSDBStatus() throws Exception {
		CommonFunctions.logMessage("*************Validating Order Status in CSDB*************");
		String OrderStatus = CommonFunctions.getTextOfElement(eleCancelStatus, "cancel Status");
		if (OrderStatus.contains("Complete")) {
			CommonFunctions.logMessage("Order status in CSDB is => :" + OrderStatus);
		} else {
			CommonFunctions.logErrorMessage("Order status in CSDB is => :" + OrderStatus);
		}
	}

	/**
	 * This Method to click order refund in csdb
	 * 
	 *
	 * @throws Exception
	 */

	public void partialRefund(String refundReasons) throws Exception {

		CommonFunctions.clickWebelement(eleCsdbEditOrderRefund, "csdbEditOrderRefund");
		CommonFunctions.clickWebelement(eleCsdbEditPartialRefund, "csdbEditPartialRefund");
		new CSDBOrderDetailsPage().selectRefundReasonsFromDropDown(refundReasons);
		CommonFunctions.Sendkeys(eleCsdbRefundAmount, "25", "PartialRefundAmount");
		CommonFunctions.clickWebelement(eleSubmitRefundOrder, "submitRefundOrder");
	}

	/**
	 * This Method to click order refund in csdb
	 * 
	 *
	 * @throws Exception
	 */

	public void fullRefundValidations(String refundReasons) throws Exception {
		CSDBUserDetailsFlow.navigateCustomerDB(getEmailId());
		CSDBUserDetailsFlow.orderIdFlow();
		verifyOrderCSDBStatus();
		moreOptions();
		verifyCancelButtonGreyOut();
		CommonFunctions.clickWebelement(eleRefundOrder, "refundOrder");
		new CSDBOrderDetailsPage().selectRefundReasonsFromDropDown(refundReasons);
		CommonFunctions.clickWebelement(eleFullrefundvalidation, "fullrefundvalidation");
		CommonFunctions.clickWebelement(eleSubmitRefundOrder, "submitRefundOrder");
		refundValidations();
		new CSDBOrderDetailsPage().verifyRefundReasonsFromBanner();
	}

	/**
	 * 
	 * @This method is select the reasons from drop-down list while refund the order
	 * 
	 * @param allReasons
	 * @throws Exception
	 * 
	 */
	public void selectRefundReasonsFromDropDown(String allReasons) throws Exception {
		String[] spltReason = allReasons.split(Pattern.quote("|"));

		if (spltReason.length > 0) {
			for (int reason = 0; reason < spltReason.length; reason++) {
				int reasonNum = Integer.parseInt(spltReason[reason]);
				CommonFunctions.clickWebelement(eleRefundReasons.get(reasonNum),
						"Refund reason --> " + eleRefundReasons.get(reasonNum).getAttribute("for"));
				getListReasons().add(eleRefundReasons.get(reasonNum).getAttribute("for"));
			}
		} else {
			CommonFunctions.logErrorMessageStopExecution("Error, Reason is not displayed for refunding the order.");
		}

	}

	/**
	 * 
	 * @This method to verify the reasons displayed in banner while selected at
	 *       order refund
	 * 
	 * @throws Exception
	 * 
	 */
	public void verifyRefundReasonsFromBanner() throws Exception {
		String[] bannerReasons = CommonFunctions.getTextOfElement(eleBannerRefundReason, "banner refund reason")
				.replaceAll("This order was refunded for the following reasons|:|\\.", "").trim().split(",");
		int count = 0;

		for (String reason : bannerReasons) {
			if (getListReasons().contains(reason.trim())) {
				CommonFunctions.logMessage("Actual refunded reason matched the expected reason ===> " + reason.trim());
			} else {
				CommonFunctions.logErrorMessage("Actual refunded reason " + reason.trim()
						+ " doesn't matched the expected reason ===> " + getListReasons().get(count));
			}
			count++;
		}

	}

	/**
	 * @This method is to validate banner details for B2B-Replacement
	 * 
	 * @throws Exception
	 * 
	 */
	public void B2BOrderBannerVerification() throws Exception {

	if (CommonFunctions.elementIsVisible(eleB2BReplacementBanner)) {
		CommonFunctions.logMessage("B2B-Replacement Banner: "
				+ CommonFunctions.getTextOfElement(eleB2BReplacementBanner, "B2BReplacementBanner"));
	} else {
		CommonFunctions.logErrorMessage("B2B-Replacement Banner failed to display");
	}
	}

	/**
	 * 
	 * @This method is select the reasons from drop-down list while replace the
	 *       order
	 * 
	 * @param allReasons
	 * @throws Exception
	 * 
	 */

	public void selectReplaceReasonsFromDropDown(String allReasons) throws Exception {
		String[] spltReason = allReasons.split(Pattern.quote("|"));

		if (spltReason.length > 0) {
			for (int reason = 0; reason < spltReason.length; reason++) {
				int reasonNum = Integer.parseInt(spltReason[reason]);
				CommonFunctions.clickWebelement(eleReplaceReasons.get(reasonNum),
						"Replace reason --> " + eleReplaceReasons.get(reasonNum).getAttribute("for"));
				getListReasons().add(eleReplaceReasons.get(reasonNum).getAttribute("for"));
			}
		} else {
			CommonFunctions.logErrorMessageStopExecution("Error, Reason is not displayed for replacing the order.");
		}

	}

	/**
	 * @This method is to validate order details for B2B-Replacement
	 * 
	 * @throws Exception
	 * 
	 */
	public void B2BOrderVerification() throws Exception {
		new CSDBLandingPage().clickCSDBUserOrderId();
		new CSDBOrderDetailsPage().clickCSDBFirstOrder();
		CommonFunctions.waitForPageLoad(getDriver());
		CommonFunctions.logMessage("<-----CSDB Oder Details Page----->");

		if (CommonFunctions.elementIsVisible(eleB2BReplacementBanner)) {
			CommonFunctions.logMessage("B2B-Replacement Banner: "
					+ CommonFunctions.getTextOfElement(eleB2BReplacementBanner, "B2BReplacementBanner"));
		} else {
			CommonFunctions.logErrorMessage("B2B-Replacement Banner failed to display");
		}
		String orderDateSplit[] = CommonFunctions.getTextOfElement(eleCustomerOrderDate, "Customer order date").split("/");

		if (Integer.parseInt(orderDateSplit[1]) <= 9 && orderDateSplit[1].length() == 1) {
			orderDateSplit[1] = "0" + orderDateSplit[1];
		}

		orderedDate.set(orderDateSplit[0] + "/" + orderDateSplit[1] + "/" + orderDateSplit[2]);
		orderId.set(CommonFunctions.getTextOfElement(eleCustomerOrderNumber, "Customer order number"));
		
		if(!getOrderId().isEmpty()) {
			getTestData().put("order Id",getOrderId());
		}
		
		orderShippingRate.set(CommonFunctions.getTextOfElement(eleCsdbShippingPrice, "shipping cost").replace("$", ""));
		orderTaxRate.set(CommonFunctions.getTextOfElement(eleCsdbTax, "tax rate").replace("$", ""));
		orderTotalAmount.set(CommonFunctions.getTextOfElement(eleCsdbTotalPrice, "total order price").replace("$", ""));

		String quantityValue = CommonFunctions.getTextOfElement(eleOrderQuantity, "CSDB oder Quantity");
		orderQuantity.set(quantityValue.split("\\)")[0].replaceAll("\\(|\\s+", ""));

		if (getOrderTotalAmount().equalsIgnoreCase("0.00")) {
			CommonFunctions.logMessage("Order placed total price matched the CSDB order total price ===> 0.00");
		} else {
			CommonFunctions.logErrorMessageStopExecution(
					"Order placed total price " + orderTotalAmount + " not matched CSDB order total price 0.00");
		}

	}

	/**
	 * This Method to edit order details field in csdb
	 * 
	 *
	 * @throws Exception
	 */

	public void editingOrderDetails(String usermail) throws Exception {
		CSDBUserDetailsFlow.navigateCustomerDB(usermail);
		new CSDBAccountDetailsPage().verifyCSDBCustomerPersonalDetails(usermail);
		new CSDBAccountDetailsPage().verifyCustomerContactDetails();
		CSDBUserDetailsFlow.orderIdFlow();
		CommonFunctions.logMessage("------------- Editing the order details -------------");
		CommonFunctions.clickWebelement(eleEditOrderDetails, "Edit Order details button");

		CommonFunctions.sendKeysWithDeleteAll(eleStreetAddress, CommonFunctions.getdata("EditedAddress"),
				"Edit address");
		CommonFunctions.sendKeysWithDeleteAll(eleCity, CommonFunctions.getdata("EditedCity"), "Edit City");
		CommonFunctions.sendKeysWithDeleteAll(eleZipCode, CommonFunctions.getdata("EditedZipCode"), "Edit Zip Code");
		eleEditOrder.click();
		CommonFunctions.logMessage("Edit order button clicked!");
		boolean flag = CommonFunctions.elementIsVisible(eleOrderSuccessDetails);
		if (flag) {
			CommonFunctions.logMessage("The order details was updated successfully!");
		} else {
			CommonFunctions.logErrorMessageStopExecution("The order details was NOT updated!");
		}
	}

	/**
	 * @This method is to validate the hyperlink of the orders
	 * 
	 * @throws Exception
	 * 
	 */
	public void orderHyperlinkValidation() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		CSDBUserDetailsFlow.navigateCustomerDB(getEmailId());
		CommonFunctions.clickWebelement(eleActionHistory, "Action History tab");
		CommonFunctions.clickWebelement(eleFirstHistory, "Click the order");
	}

}
