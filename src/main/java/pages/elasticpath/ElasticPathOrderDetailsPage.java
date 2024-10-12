package main.java.pages.elasticpath;

import java.util.List;

import org.json.JSONException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;
import main.java.utils.DefectList;

public class ElasticPathOrderDetailsPage extends TestBase {

	private JavascriptExecutor jse = null;

	@FindBy(xpath = "//*[@title='All orders']")
	private WebElement eleAllOrderHeader;

	@FindBy(xpath = "//a[text()='Orders']//ancestor::a")
	private WebElement eleOrder;

	@FindBy(xpath = "//span[text()='Subtotal']")
	private WebElement eleOrderSubTotal;

	@FindBy(xpath = "//p[contains(text(),'FedEx Priority Overnight')]")
	private WebElement eleOrderShipMethod;

	@FindBy(xpath = "//*[@data-testid='order-summary-shipping']")
	private WebElement eleOrderShipRate;

	@FindBy(xpath = "//*[@data-testid='order-summary-tax']")
	private WebElement eleOrderTax;

	@FindBy(xpath = "(//*[contains(text(),'Name')]//parent::div//following-sibling::div//span[1]|//*[contains(text(),'Name')]//following-sibling::span)[1]")
	private WebElement eleOrderUserName;

	@FindBy(xpath = "(//*[@class='OrderCustomerAndAddresses_address-detail__1M-dE'])[2]")
	private WebElement eleOrderUserAddress;

	@FindBy(xpath = "(//span[@data-test-data='shipping-address']//following-sibling::span)[2]")
	private WebElement eleOrderBuildingNumber;

	@FindBy(xpath = "(//*[@class='OrderCustomerAndAddresses_address-detail__1M-dE'])[6]")
	private WebElement eleCounty;

	@FindBy(xpath = "(//*[contains(@class,'OrderCustomerAndAddresses_address-detail')])[5]")
	private WebElement eleOrderCity;

	@FindBy(xpath = "(//*[contains(@class,'OrderCustomerAndAddresses_address-detail')])[6]")
	private WebElement eleOrderZipCode;

	@FindBy(xpath = "(//*[contains(@class,'OrderCustomerAndAddresses_address-detail')])[7]")
	private WebElement eleOrderState;

	@FindBy(xpath = "(//*[contains(@class,'OrderCustomerAndAddresses_address-detail')])[9]")
	private WebElement eleOrderPhoneNumber;

	@FindBy(xpath = "//*[contains(text(),'FedEx')]")
	private WebElement elasticPathShippingMethod;

	@FindBy(xpath = "(//span[contains(@class,'items-center')])[1]|(//*[contains(text(),'Abbott’s')]//following::div)[6]")
	private WebElement elasticPathSubTotal;

	@FindBy(xpath = "(//*[contains(text(),'FedEx')]//following::div[@class='right-section text-right title'])[1]")
	private WebElement eleShippingCost;

	@FindBy(xpath = "(//*[text()='Tax']//following::div[@class='text-right'])[2]")
	private WebElement elasticPathTax;

	@FindBy(xpath = "//*[contains(@class,'total-price')]")
	private WebElement elasticPathTotal;

	@FindBy(xpath = "//*[contains(text(),'Order ID')]//following::div[1]")
	private WebElement eleElasticPathOrderID;

	@FindBy(xpath = "(//*[contains(text(),'Abbott’s BinaxNOW™ COVID-19 Ag At-Home Test Kit')]//following::div[@class='title'])[2]")
	private WebElement eleElasticPathOrderQuantity;

	@FindBy(xpath = "//*[contains(text(),'Status')]//following-sibling::span")
	private WebElement eleStatus;

	@FindBy(xpath = "//*[contains(text(),'Payment')]//following-sibling::span")
	private WebElement elePayment;

	@FindBy(xpath = "//*[contains(text(),'Shipping')]//following-sibling::span[@class]")
	private WebElement eleShipping;

	@FindBy(xpath = "//input[@id='flows.internal_status']|//*[@id='internal_status']")
	private WebElement eleInternalStatus;

	@FindBy(xpath = "//input[@id='flows.bag']|//*[@id='bag']")
	private WebElement eleBag;

	@FindBy(xpath = "(//*[contains(@class,'leading-normal')])[2]|//*[contains(text(),'Save')]//parent::button")
	private WebElement eleSaveBtn;

	@FindBy(xpath = "//*[contains(@class,'notification__content')]|//*[contains(text(),'Custom fields are successfully updated')]")
	private WebElement eleSavedChangesNotify;

	@FindBy(xpath = "//button[@data-test-action='payment-captured']|//*[text()='Mark as captured']//parent::button")
	private WebElement elePaymentMarkedAsCaptured;

	@FindBy(xpath = "//*[text()='Successfully marked the transaction as captured']")
	private WebElement eleSuccessfullyPaymentCaptured;

	@FindBy(xpath = "//button[@data-test-action='mark-as-fulfilled']|//*[text()='Mark as fulfilled']//parent::button")
	private WebElement eleShippingMarkedAsFulfilled;

	@FindBy(xpath = "//*[text()='Successfully marked the order as fulfilled']")
	private WebElement eleSuccessfullyShippingFulfilled;

	public ElasticPathOrderDetailsPage() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

	/**
	 * Elastic Path Order detail page verification
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public void verifyElasticPathOrderDetailPage() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());

		switch (env) {

		case "stg":

			CommonFunctions.checkCurrentPageTitle("Welcome to Dr. Watson STG");
			CommonFunctions.logMessage("<-----elasticPath STG Order Detail Page----->");
			break;

		case "dev2":
		case "qa":

			CommonFunctions.checkCurrentPageTitle("Welcome to Dr. Watson Sandbox");
			CommonFunctions.logMessage("<-----elasticPath Sandbox Order Detail Page----->");
			break;

		default:
			CommonFunctions.logErrorMessageStopExecution("Error wwhile displaying the elasic path " + env + ".");
		}

	}

	/**
	 * Method to click the Order
	 * 
	 * 
	 * @throws Exception
	 *
	 */
	public void clickOrder() throws Exception {
		CommonFunctions.clickWebelement(eleOrder, "order tab");
		CommonFunctions.waitForPageLoad(getDriver());
	}

	/**
	 * 
	 * @This method is used to verify the order headers
	 * 
	 * @throws Exception
	 * 
	 */
	public void verifyAllOrderHeaders() throws Exception {
		getDriver().switchTo().frame("react-iframe");
		boolean ordersHeader = CommonFunctions.elementIsVisible(eleAllOrderHeader);
		if (ordersHeader) {
			CommonFunctions.logMessage("All Order Page is displayed");
		} else {
			CommonFunctions.logErrorMessageStopExecution("Error, EP all order page is not displayed.");
		}
	}

	/**
	 * Method to click the order owner
	 * 
	 * @param emailID
	 * 
	 * @throws Exception
	 *
	 */
	public void searchUsername(String emailID) throws Exception {
		boolean hasFrame = getDriver().getPageSource().contains("iframe");
		if (hasFrame) {
			verifyAllOrderHeaders();
		}

		CommonFunctions.waitForPageLoad(getDriver());
		List<WebElement> user = getDriver()
				.findElements(By.xpath("//span[contains(text(),'" + emailID + "')]//ancestor::tr"));
		boolean userName = CommonFunctions.elementIsVisible(user.get(0));
		if (userName) {
			CommonFunctions.clickWebelement(user.get(0), "user");
		} else {
			CommonFunctions.logErrorMessageStopExecution("Error while selecting user in elastic path " + emailID + ".");
		}
		if(!emailID.isEmpty()) {
			getTestData().put("emed email",emailID);
		}

	}

	/**
	 * Method for verifying the shipping address in Elastic path page
	 * 
	 * @throws Exception
	 */
	public void verifyshippingAddressInElasticPathPage() throws Exception {
		CommonFunctions.logMessage("<-----Validating shipping address in Elastic Path page----->");

		String firstName = CommonFunctions.getTextOfElement(eleOrderUserName, "order firstName").split(" ")[0];
		String lastName = CommonFunctions.getTextOfElement(eleOrderUserName, "order lastName").split(" ")[1];
		String address = CommonFunctions.getTextOfElement(eleOrderUserAddress, "order address ");
		String zipcode = CommonFunctions.getTextOfElement(eleOrderZipCode, "order zipCode");
		String city = CommonFunctions.getTextOfElement(eleOrderCity, "city");
		String county = CommonFunctions.getTextOfElement(eleCounty, "Oder County");
		String phNumber = CommonFunctions.getTextOfElement(eleOrderPhoneNumber, "phnNumber").replaceAll("[^a-zA-Z0-9]",
				"");

		if (firstName.contains(CommonFunctions.getdata("FirstName"))) {
			CommonFunctions.logMessage("Order shipping FirstName is same as displayed in Excel: " + firstName);
		} else {

			CommonFunctions.logErrorMessage("Expected shipping FirstName" + CommonFunctions.getdata("FirstName")
					+ " doesn't match the actual FirstName " + firstName);

		}
		if (lastName.contains(CommonFunctions.getdata("LastName"))) {
			CommonFunctions.logMessage("Order shipping LastName is same as displayed in Excel: " + lastName);
		} else {

			CommonFunctions.logErrorMessage("Expected shipping LastName" + CommonFunctions.getdata("LastName")
					+ " doesn't match the actual LastName " + lastName);
		}
		if (address.contains(CommonFunctions.getdata("Address"))) {
			CommonFunctions.logMessage("Order shipping Address is same as displayed in Excel: " + address);
		} else {

			CommonFunctions.logErrorMessage("Expected shipping Address" + CommonFunctions.getdata("Address")
					+ " doesn't match the actual Address " + address);

		}

		if (city.equalsIgnoreCase(CommonFunctions.getdata("City"))) {
			CommonFunctions.logMessage("Order shipping city is same as displayed in Excel: " + city);
		} else {
			CommonFunctions.logErrorMessage("Expected shipping city " + CommonFunctions.getdata("City")
					+ " doesn't match the actual city " + city);
		}

		if (county.equalsIgnoreCase(CommonFunctions.getdata("Country"))) {
			CommonFunctions.logMessage("Order shipping County is same as displayed in Excel: " + county);
		} else {
			CommonFunctions.logErrorMessage("Expected shipping city " + CommonFunctions.getdata("Country")
					+ " doesn't match the actual County " + county);
		}

		if (zipcode.contains(CommonFunctions.getdata("ZipCode"))) {
			CommonFunctions.logMessage("Order shipping Zipcode is same as displayed in Excel: " + zipcode);
		} else {

			CommonFunctions.logErrorMessage("Expected shipping Zipcode" + CommonFunctions.getdata("ZipCode")
					+ " doesn't match the actual ZipCode " + zipcode);
		}

		if (phNumber.contains(CommonFunctions.getdata("PhNumber"))) {
			CommonFunctions.logMessage("Order shipping PhNumber is same as displayed in Excel: " + phNumber);
		} else {
			CommonFunctions.logErrorMessage("Expected shipping PhNumber " + CommonFunctions.getdata("PhNumber")
					+ " doesn't match the actual phone number " + phNumber);
		}

	}

	/**
	 * Method for verifying the shipping amount in Elastic path page
	 * 
	 * @throws Exception
	 */
	public void verifyshippingAmmountInElasticPathPage() throws Exception {
		CommonFunctions.logMessage("<-----Validating shipping Amount in Elastic Path page----->");

		String shippingmethodelaticpath = CommonFunctions.getTextOfElement(elasticPathShippingMethod,
				"order Shipping Method");

		String shippingSubtotalelaticpath = CommonFunctions.regexText("[\\d?.\\?\\d]+",
				CommonFunctions.getTextOfElement(elasticPathSubTotal, "order SubTotal").replace("$", ""));

		String shippingtaxelasticpath = CommonFunctions.regexText("[\\d?.\\?\\d]+",
				CommonFunctions.getTextOfElement(elasticPathTax, "order Tax ").replace("$", ""));

		String shippingTotalelasticpath = CommonFunctions.regexText("[\\d?.\\?\\d]+",
				CommonFunctions.getTextOfElement(elasticPathTotal, "order buildingNumber").replace("$", ""));

		if (shippingmethodelaticpath.contains(getOrderShippingMethod())) {
			CommonFunctions.logMessage("Order shipping method matched ===> " + shippingmethodelaticpath);
		} else {
			CommonFunctions.logErrorMessage("Expected shipping method " + getOrderShippingMethod()
					+ " doesn't match the actual shipping method " + shippingmethodelaticpath);
		}

		if (shippingSubtotalelaticpath.contains(getOrderSubRate())) {
			CommonFunctions.logMessage("Order shipping method orderSubRate matched ===> " + shippingSubtotalelaticpath);
		} else {
			CommonFunctions.logErrorMessage("Expected shipping orderSubRate " + getOrderSubRate()
					+ " doesn't match the actual shipping orderSubRate " + shippingSubtotalelaticpath);
		}

		if (shippingtaxelasticpath.contains(getOrderTaxRate())) {
			CommonFunctions.logMessage("Order shipping method tax matched ===> " + shippingtaxelasticpath);
		} else {
			CommonFunctions.logErrorMessage("Expected shipping tax " + getOrderTaxRate()
					+ " doesn't match the actual shipping tax " + shippingtaxelasticpath);
		}

		if (shippingTotalelasticpath.contains(getOrderTotalAmount())) {
			CommonFunctions.logMessage("Order shipping Total matched ===> " + shippingTotalelasticpath);
		} else {
			CommonFunctions.logErrorMessage("Expected shipping Total " + getOrderTotalAmount()
					+ " doesn't match the actual shipping Total " + shippingTotalelasticpath);
		}
	}

	/**
	 * Method to get Order ID in Elastic path
	 * 
	 * s
	 * 
	 * @throws Exception
	 *
	 */

	public void getOrderIdInElasticPath() throws Exception {

		orderId.set(CommonFunctions.getTextOfElement(eleElasticPathOrderID, "Elasticpath Order Id"));
		CommonFunctions.logMessage("Order Id from the elastic path: " + getOrderId());
		if(!getOrderId().isEmpty()) {
			getTestData().put("order Id",getOrderId());
		}

	}

	/**
	 * @This Method is to verify Retrieved OrderId with ElasticPath's OrderId
	 * 
	 * @note orderId value is stored based on executed scenario
	 * 
	 * @throws Exception
	 */

	public void verifyElasticPathOrderID() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		CommonFunctions.logMessage("<-----OrderId Verification----->");
		String elasticPathOrderId = CommonFunctions.getTextOfElement(eleElasticPathOrderID, "Elasticpath Order Id");

		if (elasticPathOrderId.contains(getOrderId())) {
			CommonFunctions.logMessage("Order-ID Matched ===> " + elasticPathOrderId);
		} else {
			CommonFunctions.logErrorMessage("ElasticPath Order-Id - " + elasticPathOrderId
					+ "failed to match with Retrieved Order ID - " + getOrderId());
		}
	}

	/**
	 * 
	 * @This method to click the button mark as payment captured
	 * 
	 * @throws Exception
	 */
	public void clickMarkAsCapturedForPayment() throws Exception {
		CommonFunctions.clickWebelement(elePaymentMarkedAsCaptured, "payment marked as captured");
		CommonFunctions.logMessage(getDriver().switchTo().alert().getText() + "\nOK is clicked");
		getDriver().switchTo().alert().accept();

		boolean flag = CommonFunctions.elementIsVisible(eleSuccessfullyPaymentCaptured);
		if (flag) {
			CommonFunctions.logMessage("Successfully marked the transaction as captured");
		} else {
			CommonFunctions.logErrorMessageStopExecution("Failed to successfully marked the transaction as captured");
		}
	}

	/**
	 * 
	 * @This method to click the button mark as fulfilled for shipping
	 * 
	 * @throws Exception
	 */
	public void clickMarkAsFulfilledForShipping() throws Exception {
		CommonFunctions.clickJSE(eleShippingMarkedAsFulfilled, "shipping marked as fulfilled");
		CommonFunctions.logMessage(getDriver().switchTo().alert().getText() + "\nOK is clicked");
		getDriver().switchTo().alert().accept();

		boolean flag = CommonFunctions.elementIsVisible(eleSuccessfullyShippingFulfilled);
		if (flag) {
			CommonFunctions.logMessage("Successfully marked the order as fulfilled");
		} else {
			CommonFunctions.logErrorMessageStopExecution("Failed to successfully marked the order as fulfilled");
		}
	}

	/**
	 * Method for verifying the shipping Method in Elastic path page
	 * 
	 * @throws Exception
	 */
	public void verifyshippingMethodInElasticPathPage() throws Exception {
		CommonFunctions.logMessage(
				"**************************Validating shipping Amount in Elastic Path page *****************************");

		String shippingmethodelaticpath = CommonFunctions.getTextOfElement(elasticPathShippingMethod,
				"order Shipping Method");
		if (shippingmethodelaticpath.contains(getOrderShippingMethod())) {
			CommonFunctions.logMessage("Order shipping method matched ===> " + shippingmethodelaticpath);
		} else {
			CommonFunctions.logErrorMessage("Expected shipping method " + getOrderShippingMethod()
					+ " doesn't match the actual shipping method " + shippingmethodelaticpath);
		}
	}

	/**
	 * This Method is to verify the status in Elastic path page
	 * 
	 * @throws Exception
	 */
	public void verifyOrderStatusInElasticPathPage(String shipComplete, String payStatus, String shippStatus)
			throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		CommonFunctions.logMessage("*************Validating Status in Elastic Path page*************");
		String statusComplete = CommonFunctions.getTextOfElement(eleStatus, "status");
		String paymentStatus = CommonFunctions.getTextOfElement(elePayment, "payment");
		String shippingStatus = CommonFunctions.getTextOfElement(eleShipping, "shipping");

		if (statusComplete.contains(shipComplete) && (paymentStatus.contains(payStatus))
				&& (shippingStatus.contains(shippStatus))) {
			CommonFunctions.logMessage(
					"ElasticPath = Order, shipping, and payment status' are updated after approving process in GoGomeds ");
		} else {
			CommonFunctions.logErrorMessageStopExecution(DefectList.getP2Defect("ETIGER-406"));

		}
	}

	/**
	 * 
	 * @This method to update the internal status of the order purchased
	 * 
	 * @throws Exception
	 * 
	 */
	public void updateInternalStatus(String statusValue) throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		CommonFunctions.SendkeysAttrib(eleInternalStatus, "value", statusValue, "internal status");
		CommonFunctions.actionClick(eleSaveBtn, "save button");
		boolean notify = CommonFunctions.elementVisibleToCheck(eleSavedChangesNotify, 8);
		if (notify) {
			CommonFunctions.logMessage("Internal order status has changed successfully as " + statusValue);
		} else {
			CommonFunctions.logErrorMessageStopExecution("Error internal order status has not changed...");
		}
	}

	/**
	 * 
	 * @This method is to verify the internal status of the order purchased
	 * 
	 * @param statusValue
	 * @throws Exception
	 * 
	 */
	public void verifyOrderInternalStatus(String statusValue) throws Exception {

		int i=0;
		String internStatus="";
		
		CommonFunctions.waitForPageLoad(getDriver());
		CommonFunctions.logMessage("*************Validating Order Internal Status*************");
		
		while(true){
			CommonFunctions.refreshPage(getDriver());
			internStatus = CommonFunctions.getAttributeOfElement(eleInternalStatus, "internal status", "value");
			if (!internStatus.equalsIgnoreCase("") || i==20) {
				CommonFunctions.logMessage("Internal order status has available in elastic path ==> " + internStatus);
				break;
			} else {			
				Thread.sleep(10000);
			}
			i++;			
		}		
		CommonFunctions.logMessage("Number of wait cycles of 10 seconds used to get Internal Status in elastic path: "+i);
		
		i=0;
		while(true) {
			CommonFunctions.refreshPage(getDriver());
			internStatus = CommonFunctions.getAttributeOfElement(eleInternalStatus, "internal status", "value");
			if(internStatus.equalsIgnoreCase(statusValue) || i==7) {
				CommonFunctions.logMessage("Internal order status has matched successfully ==> " + internStatus);
				break;								
			}else {
				Thread.sleep(10000);								
			}
			i++;
		}		
		CommonFunctions.logMessage("Number of wait cycles of 10 seconds used to match the Internal Status in elastic path: "+i);
		
		if(!internStatus.equalsIgnoreCase(statusValue)) 
			CommonFunctions.logErrorMessageStopExecution("Internal Status available in incorrect Actual: " +internStatus+". Expected: "+statusValue+".");
	
	}

	/**
	 * 
	 * @This method is to verify the presence of ggm_batch_id in Bag elastice path
	 * 
	 * @param statusValue
	 * @throws Exception
	 * 
	 */
	public String verifyGogomedBatchId() throws Exception {
		int i=0;
		String bagData="";
		
		CommonFunctions.waitForPageLoad(getDriver());
		CommonFunctions.logMessage("*************Validating presence of Gogomed ggm_batch_id in Bag*************");
		
		while(true){
			CommonFunctions.refreshPage(getDriver());
			bagData = CommonFunctions.getAttributeOfElement(eleBag, "ggm_batch_id", "value");
			if (bagData.contains("ggm_batch_id") || i==20) {
				CommonFunctions.logMessage("ggm_batch_id is available in Bag");
				break;
			} else {			
				Thread.sleep(10000);
			}
			i++;			
		}		
		CommonFunctions.logMessage("Number of wait cycles of 10 seconds used to get ggm_batch_id from Bag in elastic path: "+i);
		
		if(!bagData.contains("ggm_batch_id")) 
			CommonFunctions.logErrorMessageStopExecution("ggm_batch_id not availabe in Bag after waiting for 200 seconds");
		
		return bagData.substring(bagData.length()-23,bagData.length()-2);
	
	}

	
	/**
	 * 
	 * @This method to get the gogomeds order Id from elastic path bag
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public List<String> getGogomedsOrderIdFromBag() throws InterruptedException {
		CommonFunctions.waitForPageLoad(getDriver());
		List<String> bagValue = CommonFunctions
				.getArrayValueFromJSON(CommonFunctions.getAttributeOfElement(eleBag, "bags", "value"), "ggmOrderIds");
		return bagValue;
	}

	/**
	 * 
	 * @This method to verify the order status is approved to specified status
	 * 
	 * @param status
	 * @throws Exception
	 * 
	 */
	public void verifyEPBagEmailSentStatus(String status) throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		List<String> bagData = null;
		int i=0;
		try {
			
			while(true){
				bagData = CommonFunctions.getArrayValueFromJSON(CommonFunctions.getAttributeOfElement(eleBag, "bags", "value"),
						"sentEmails");
				if (bagData.contains("sentEmails") || i==10) {
					if (bagData.contains(status.toUpperCase())) {
						CommonFunctions.logMessage("Order email sent status ==> " + status.toUpperCase());
						break;
					} else {
						CommonFunctions.logErrorMessageStopExecution("Expected email sent status " + status.toUpperCase()
								+ " doesnt match the actual email sent status " + bagData);
					}
				} else {
					CommonFunctions.refreshPage(getDriver());
					Thread.sleep(10000);
				}
				i++;		
			}

		} catch (JSONException e) {
			System.err.println(CommonFunctions.getAttributeOfElement(eleBag, "bags", "value"));
			CommonFunctions.logErrorMessageStopExecution(
					"Sent email json property is not available pls check the bag in elastic path..");
		}

	}

	/**
	 * This Method is to verify the Order status in Elastic path page
	 * 
	 * @throws Exception
	 */
	public void verifyPaymentOrderStatusInElasticPathPage() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		CommonFunctions.logMessage("*************Validating Status in Elastic Path page*************");
		String paymentStatus = CommonFunctions.getTextOfElement(elePayment, "payment");
		if (paymentStatus.contains("Unpaid")) {
			CommonFunctions.logMessage(
					"ElasticPath payment status is => : Unpaid while purchasing Order through invalid payment card");
		} else {
			CommonFunctions.logErrorMessageStopExecution(
					"Elastic path status is => " + paymentStatus + " while purchasing through invalid payment card");

		}
	}

	/**
	 * 
	 * @This method is to validate B2B-Replacement Order Amount details with
	 *       Elastic-Path
	 * 
	 * @throws Exception
	 */
	public void B2BAmountVerification() throws Exception {
		CommonFunctions.logMessage("<-----Validating B2B-Replacement oder amount details with Elastic-Path----->");

		String shippingCost = CommonFunctions.regexText("[\\d?.\\?\\d]+",
				CommonFunctions.getTextOfElement(eleShippingCost, "order Shipping Cost").replace("$", ""));

		String shippingtaxelasticpath = CommonFunctions.regexText("[\\d?.\\?\\d]+",
				CommonFunctions.getTextOfElement(elasticPathTax, "order Tax ").replace("$", ""));

		String shippingTotalelasticpath = CommonFunctions.regexText("[\\d?.\\?\\d]+",
				CommonFunctions.getTextOfElement(elasticPathTotal, "order total cost").replace("$", ""));

		String elasticPathOrderQuantity = CommonFunctions
				.getTextOfElement(eleElasticPathOrderQuantity, "elasticPath order quantity")
				.replaceAll("Quantity|:|\\s+", "");

		if (shippingCost.contains(getOrderShippingRate())) {
			CommonFunctions.logMessage("Order shipping cost matched ===> " + shippingCost);
		} else {
			CommonFunctions.logErrorMessage("Expected shipping cost " + getOrderShippingRate()
					+ " doesn't match the actual shipping cost " + shippingCost);
		}

		if (shippingtaxelasticpath.contains(getOrderTaxRate())) {
			CommonFunctions.logMessage("Order shipping tax matched ===> " + shippingtaxelasticpath);
		} else {
			CommonFunctions.logErrorMessage("Expected shipping tax " + getOrderTaxRate()
					+ " doesn't match the actual shipping tax " + shippingtaxelasticpath);
		}

		if (shippingTotalelasticpath.contains(getOrderTotalAmount())) {
			CommonFunctions.logMessage("Order shipping Total matched ===> " + shippingTotalelasticpath);
		} else {
			CommonFunctions.logErrorMessage("Expected shipping Total " + getOrderTotalAmount()
					+ " doesn't match the actual shipping Total " + shippingTotalelasticpath);
		}

		if (elasticPathOrderQuantity.contains(getOrderQuantity())) {
			CommonFunctions.logMessage("Order Qauantity Matched. Quantity= " + elasticPathOrderQuantity);
		} else {
			CommonFunctions.logErrorMessage("CSDB order quantity: " + getOrderQuantity()
					+ " failed to match with elastic path's order quantity: " + elasticPathOrderQuantity);
		}

	}

}
