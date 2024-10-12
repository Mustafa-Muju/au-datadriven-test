package main.java.flows.www;

import main.java.base.TestBase;
import main.java.pages.www.AssessmentPage;
import main.java.pages.www.CartPage;
import main.java.pages.www.EMedLoginPage;
import main.java.pages.www.LandingPage;
import main.java.pages.www.MyOrderPage;
import main.java.pages.www.PaymentPage;
import main.java.pages.www.ReviewAndConfirmationPage;
import main.java.pages.www.ShippingPage;
import main.java.pages.www.ShoppingPage;
import main.java.utils.CommonFunctions;

public class ShoppingFlow extends TestBase {

	/**
	 * @Method to purchase a kit
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public static void bookingTestKitFlow() throws Exception {
		new LandingPage().clickShopTab();
		new ShoppingPage().verifyShoppingPage();
		new ShoppingPage().clickSixKit();
		new ShoppingPage().clickCheckQualification();
		new AssessmentPage().selectPurchaseReasons(CommonFunctions.getdata("PurchaseReasons"));
		new AssessmentPage().verifyAssessmentCompleted();
		new ShoppingPage().clickAddToCart();
		new ShoppingPage().cartIcon();
		new CartPage().checkCartPageLoaded();
		new CartPage().proceedToCheckout();
		new ShippingPage().selectOrderDeliverAddress();
		new ShippingPage().continueToShipping();
		new ShippingPage().confirmYourAddress();
		new ShippingPage().selectShippingMethod(CommonFunctions.getdata("ShipMethod"));
		new ShippingPage().continueToPayment();
		new PaymentPage().verifyPaymentPage();
		new PaymentPage().getShippingRateDetails();
		new PaymentPage().enterCardHolder(CommonFunctions.getdata("CardHolderName"));
		new PaymentPage().enterCardNumber(CommonFunctions.getdata("CardNumber"));
		new PaymentPage().enterCardExpiryDate(CommonFunctions.getdata("ExpiryDate"));
		new PaymentPage().enterCardCVV(CommonFunctions.getdata("CVV"));
		new PaymentPage().enterCardHolderZipCode(CommonFunctions.getdata("ZipCode"));
		new PaymentPage().clickContinueToReview();
		new ReviewAndConfirmationPage().clickPlaceYourOrder();
		new ReviewAndConfirmationPage().verifyOrderPlaced();
		new ReviewAndConfirmationPage().getCustomerId();
		new EMedLoginPage().clickMyOrder();
		new MyOrderPage().verifyMyOrderPage();
		new MyOrderPage().clickOrderNumber(getConfirmationNumber());
		new MyOrderPage().verifyOrderDetails(getConfirmationNumber());
	}

}
