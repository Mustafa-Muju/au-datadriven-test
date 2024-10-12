package main.java.pages.www;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

public class MyOrderPage extends TestBase {

	private JavascriptExecutor jse = null;

	@FindBy(xpath = "//p[text()='My Orders']")
	private WebElement myOrderHeader;

	@FindBy(xpath = "//button[text()='Open']")
	private WebElement openOrderTab;

	@FindBy(xpath = "//button[text()='Completed']")
	private WebElement completedOrderTab;

	@FindBy(xpath = "//button[text()='Cancelled']")
	private WebElement canceledOrderTab;

	@FindBy(xpath = "//*[contains(text(),'Order Overview')]")
	private WebElement orderOverviewHeader;

	@FindBy(xpath = "//a[contains(@class,'OrderNumberText')]")
	private List<WebElement> orderNumberList;

	@FindBy(xpath = "//*[contains(text(),'Cancel order')]//parent::a")
	private WebElement cancelOrder;

	@FindBy(xpath = "//button[text()='Cancel the order']")
	private WebElement cancelTheOrder;

	@FindBy(xpath = "//*[contains(text(),'Order Status')]//following-sibling::p")
	private WebElement orderStatus;

	@FindBy(xpath = "//*[contains(text(),'Order No')]//following-sibling::p")
	private WebElement orderNumber;

	@FindBy(xpath = "//*[contains(text(),'Order date')]//following-sibling::p")
	private WebElement orderDate;

	@FindBy(xpath = "//*[text()='Order Cancelled']")
	private WebElement OrderCancelledHeader;

	@FindBy(xpath = "//a[text()='Go to my orders']")
	private WebElement goToMyOrders;

	@FindBy(xpath = "//a[text()='Continue to shop']")
	private WebElement continueToShop;

	public MyOrderPage() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}
	
	/**
	 * Method to verify the My order Page
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void verifyMyOrderPage() throws Exception {

		CommonFunctions.waitForPageLoad(getDriver());
		try {
			if (myOrderHeader.isDisplayed()) {
				CommonFunctions.logMessage("<-----eMed MyOrder Page----->");
			}
		} catch (NoSuchElementException e) {
			CommonFunctions.logErrorMessageStopExecution("My order page is not displayed");
		}
	}
	
	/**
	 * Method to click the Open order
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void clickOpenOrder() throws Exception {
		CommonFunctions.clickWebelement(openOrderTab, "open order tab");
	}
	
	/**
	 * Method to click the Completed order
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void clickCompletedOrder() throws Exception {
		CommonFunctions.clickWebelement(completedOrderTab, "completed order tab");
	}
	
	/**
	 * Method to click the Cancelled order
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void clickCancelledOrder() throws Exception {
		CommonFunctions.clickWebelement(canceledOrderTab, "canceled order tab");
	}
	
	/**
	 * Method to verify the order over view
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void verifyOrderOverviewPage() throws Exception {

		CommonFunctions.waitForPageLoad(getDriver());
		try {
			if (orderOverviewHeader.isDisplayed()) {
				CommonFunctions.logMessage("Order overview page is displayed");
			}
		} catch (NoSuchElementException e) {
			CommonFunctions.logErrorMessageStopExecution("Order overview page is not displayed");
		}
	}
	
	/**
	 * Method to click the order number
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void clickOrderNumber(String orderNum) throws Exception {

		boolean flag = true;
		if (orderNumberList.size() > 0) {
			if (!orderNum.equals(" ")) {
				for (int orderNumber = 0; orderNumber < orderNumberList.size(); orderNumber++) {
					if (orderNumberList.get(orderNumber).getText().contains(orderNum)) {
						CommonFunctions.clickWebelement(orderNumberList.get(orderNumber), "order number");
						flag = false;
						break;
					}
				}
				if (flag) {
					CommonFunctions
							.logErrorMessageStopExecution("Unable to find the order number in open list. So failed!!!");
				}

			} else {
				CommonFunctions.clickWebelement(orderNumberList.get(0), "order number");
			}
		} else {
			CommonFunctions.logErrorMessageStopExecution(
					"Open order list is empty so unable to proceed scenario.. So stopped!!!");
		}
	}

	/**
	 * Method to display the myorder details
	 * 
	 * @param orderNum
	 * @throws Exception
	 */
	public void verifyOrderDetails(String orderNum) throws Exception {
		try {
			if (orderNumber.getText().contains(orderNum)) {
				CommonFunctions.logMessage("Order opened is verified successfully");
				CommonFunctions.logMessage("Order Status: " + orderStatus.getText().trim());
				CommonFunctions.logMessage("Order Number: " + orderNumber.getText().trim());
				orderedDate.set(orderDate.getText().trim());
				CommonFunctions.logMessage("Order Date: " + getOrderedDate());
			}
		} catch (NoSuchElementException e) {
			CommonFunctions.logErrorMessageStopExecution("Unable to find the order number requested");
		}
	}
	
	/**
	 * Method to click the cancel order
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void clickCancelOrder() throws Exception {
		CommonFunctions.clickWebelement(cancelOrder, "cancel order button");
	}
	
	/**
	 * Method to confirm the cancel order
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void confirmCancelOrder() throws Exception {
		CommonFunctions.clickWebelement(cancelTheOrder, "cancel the order button");
	}
	
	/**
	 * Method to verify the order cancel
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void verifyOrderCancel() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		try {
			if (OrderCancelledHeader.isDisplayed()) {
				CommonFunctions.logMessage("Order has successfully canceled");
			}
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Error while canceling the order..");
		}
	}
	
	/**
	 * Method to click Go to My order
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void clickGoToMyOrders() throws Exception {
		CommonFunctions.clickWebelement(goToMyOrders, "go to myorder button");
	}
	
	/**
	 * Method to verify the cancelled order
	 * 
	 * @throws Exception
	 *
	 * 
	 */

	public void verifyCanceledOrder(String orderNum) throws Exception {

		boolean flag = true;
		for (int orderNumber = 0; orderNumber < orderNumberList.size(); orderNumber++) {
			if (orderNumberList.get(orderNumber).getText().contains(orderNum)) {
				CommonFunctions.logMessage("Verified canceled order successfully for order number: " + orderNum);
				flag = false;
				break;
			}
		}
		if (flag) {
			CommonFunctions
					.logErrorMessageStopExecution("Unable to find the order number in canceled list. So failed!!!");
		}
	}
}
